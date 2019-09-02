package org.realityforge.jsonb.datatypes;

import javax.annotation.Nonnull;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.util.Date;

public abstract class AbstractDateSerializer
  extends AbstractTemporalSerializer
  implements JsonbSerializer<Date>
{
  AbstractDateSerializer( @Nonnull final String format )
  {
    super( format );
  }

  @Override
  public void serialize( @Nonnull final Date value,
                         @Nonnull final JsonGenerator generator,
                         @Nonnull final SerializationContext context )
  {
    formatDate( generator, value );
  }
}
