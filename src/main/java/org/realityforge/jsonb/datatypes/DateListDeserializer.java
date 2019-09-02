package org.realityforge.jsonb.datatypes;

public final class DateListDeserializer
  extends AbstractTemporalListDeserializer
{
  public DateListDeserializer()
  {
    super( Constants.DATE_FORMAT );
  }
}
