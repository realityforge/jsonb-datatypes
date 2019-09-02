package org.realityforge.jsonb.datatypes;

public final class DateTimeIterableSerializer
  extends AbstractTemporalIterableSerializer
{
  public DateTimeIterableSerializer()
  {
    super( Constants.DATE_TIME_FORMAT );
  }
}
