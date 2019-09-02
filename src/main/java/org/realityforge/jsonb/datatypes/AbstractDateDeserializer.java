package org.realityforge.jsonb.datatypes;

import java.lang.reflect.Type;
import java.util.Date;
import javax.annotation.Nonnull;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import org.eclipse.yasson.internal.JsonbParser;

abstract class AbstractDateDeserializer
  extends AbstractTemporalDeserializer
  implements JsonbDeserializer<Date>
{
  AbstractDateDeserializer( @Nonnull final String format )
  {
    super( format );
  }

  @Override
  public Date deserialize( @Nonnull final JsonParser parser,
                           @Nonnull final DeserializationContext ctx,
                           @Nonnull final Type rtType )
  {
    final JsonParser.Event event = ( (JsonbParser) parser ).getCurrentLevel().getLastEvent();
    if ( JsonParser.Event.VALUE_NULL == event )
    {
      return null;
    }
    else
    {
      return parseValue( parser.getString() );
    }
  }
}
