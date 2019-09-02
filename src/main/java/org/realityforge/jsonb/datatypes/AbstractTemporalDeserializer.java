package org.realityforge.jsonb.datatypes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.json.bind.JsonbException;

abstract class AbstractTemporalDeserializer
{
  @Nonnull
  private final String _format;
  @Nonnull
  private final DateFormat _formatter;

  AbstractTemporalDeserializer( @Nonnull final String format )
  {
    _format = format;
    _formatter = new SimpleDateFormat( _format, Locale.getDefault() );
  }

  @Nonnull
  final Date parseValue( @Nonnull final String value )
  {
    try
    {
      return _formatter.parse( value );
    }
    catch ( ParseException e )
    {
      throw new JsonbException( "Failed to parse date value '" + value + "' expected to be in format " + _format, e );
    }
  }
}
