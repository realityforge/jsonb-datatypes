package org.realityforge.jsonb.datatypes;

public final class DateIterableSerializer
  extends AbstractTemporalIterableSerializer
{
  public DateIterableSerializer()
  {
    super( "YYYY-MM-dd" );
  }
}
