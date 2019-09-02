package org.realityforge.jsonb.datatypes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;
import javax.json.bind.JsonbException;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import org.eclipse.yasson.internal.JsonbParser;
import org.eclipse.yasson.internal.JsonbRiParser;

abstract class AbstractTemporalListDeserializer
  extends AbstractTemporalDeserializer
  implements JsonbDeserializer<List<Date>>
{
  AbstractTemporalListDeserializer( @Nonnull final String format )
  {
    super( format );
  }

  @Override
  public List<Date> deserialize( @Nonnull final JsonParser parser,
                                 @Nonnull final DeserializationContext ctx,
                                 @Nonnull final Type rtType )
  {
    final List<Date> list = new ArrayList<>();
    final JsonParser.Event event = ( (JsonbParser) parser ).getCurrentLevel().getLastEvent();
    if ( JsonParser.Event.VALUE_NULL == event )
    {
      return null;
    }
    else
    {
      deserializeInternal( (JsonbParser) parser, list );
    }
    return list;
  }

  private void deserializeInternal( @Nonnull final JsonbParser parser, final List<Date> list )
  {
    parser.moveTo( JsonParser.Event.START_ARRAY );
    final JsonbRiParser.LevelContext parserContext = parser.getCurrentLevel();
    while ( parser.hasNext() )
    {
      final JsonParser.Event event = parser.next();
      switch ( event )
      {
        case VALUE_STRING:
          list.add( parseValue( parser.getString() ) );
          break;
        case VALUE_NULL:
          list.add( null );
          break;
        case END_ARRAY:
          return;
        default:
          throw new JsonbException( "Unexpected json token " + event );
      }
    }
  }
}
