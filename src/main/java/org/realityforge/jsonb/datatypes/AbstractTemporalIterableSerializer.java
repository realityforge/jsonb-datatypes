package org.realityforge.jsonb.datatypes;

import javax.annotation.Nonnull;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.util.Date;

abstract class AbstractTemporalIterableSerializer
  extends AbstractTemporalSerializer
  implements JsonbSerializer<Iterable<Date>>
{
  AbstractTemporalIterableSerializer( @Nonnull final String format )
  {
    super( format );
  }

  @Override
  public void serialize( @Nonnull final Iterable<Date> values,
                         @Nonnull final JsonGenerator generator,
                         @Nonnull final SerializationContext context )
  {
    generator.writeStartArray();
    for ( final Date value : values )
    {
      if ( null == value )
      {
        generator.writeNull();
      }
      else
      {
        formatDate( generator, value );
      }
    }
    generator.writeEnd();
  }
}
