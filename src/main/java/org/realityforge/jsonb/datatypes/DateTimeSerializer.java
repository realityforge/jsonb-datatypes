package org.realityforge.jsonb.datatypes;

public final class DateTimeSerializer
  extends AbstractDateSerializer
{
  public DateTimeSerializer()
  {
    super( "YYYY-MM-dd'T'HH:mm:ss" );
  }
}
