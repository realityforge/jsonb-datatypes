package org.realityforge.jsonb.datatypes;

public final class DateTimeListDeserializer
  extends AbstractTemporalListDeserializer
{
  public DateTimeListDeserializer()
  {
    super( Constants.DATE_TIME_FORMAT );
  }
}
