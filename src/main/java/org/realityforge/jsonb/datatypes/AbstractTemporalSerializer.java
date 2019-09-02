package org.realityforge.jsonb.datatypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.stream.JsonGenerator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

abstract class AbstractTemporalSerializer
{
  @Nonnull
  private final DateFormat _formatter;

  AbstractTemporalSerializer( @Nonnull final String format )
  {
    _formatter = new SimpleDateFormat( format, Locale.getDefault() );
  }

  final void formatDate( @Nonnull final JsonGenerator generator, @Nullable final Date value )
  {
    if ( null == value )
    {
      generator.writeNull();
    }
    else
    {
      generator.write( _formatter.format( value ) );
    }
  }
}
