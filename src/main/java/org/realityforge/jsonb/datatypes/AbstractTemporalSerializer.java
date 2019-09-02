package org.realityforge.jsonb.datatypes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.json.stream.JsonGenerator;

abstract class AbstractTemporalSerializer
{
  @Nonnull
  private final DateFormat _formatter;

  AbstractTemporalSerializer( @Nonnull final String format )
  {
    _formatter = new SimpleDateFormat( format, Locale.getDefault() );
  }

  final void formatDate( @Nonnull final JsonGenerator generator, @Nonnull final Date value )
  {
    generator.write( _formatter.format( value ) );
  }
}
