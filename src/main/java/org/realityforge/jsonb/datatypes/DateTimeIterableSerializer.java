package org.realityforge.jsonb.datatypes;

public final class DateTimeIterableSerializer
  extends AbstractTemporalIterableSerializer
{
  public DateTimeIterableSerializer()
  {
    super( "YYYY-MM-dd'T'HH:mm:ss" );
  }
}
