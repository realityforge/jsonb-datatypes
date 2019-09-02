package org.realityforge.jsonb.datatypes;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeSerializer;
import org.realityforge.guiceyloops.shared.ValueUtil;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@SuppressWarnings( { "WeakerAccess", "EqualsWhichDoesntCheckParameterClass", "unused" } )
public class DateTypeTest
{
  @Test
  public void serializeDateTypes()
  {
    final Date date = ValueUtil.createDate( 2001, 10, 20 );
    final Date dateTime = ValueUtil.createDate( 2001, 10, 20, 16, 30, 0 );

    assertSerializedForm( new Type1( null ), "{\"value\":null}" );
    assertSerializedForm( new Type1( date ), "{\"value\":\"2001-10-20\"}" );
    assertSerializedForm( new Type1( dateTime ), "{\"value\":\"2001-10-20\"}" );
    assertSerializedForm( new Type2( null ), "{\"value\":null}" );
    assertSerializedForm( new Type2( date ), "{\"value\":\"2001-10-20T00:00:00\"}" );
    assertSerializedForm( new Type2( dateTime ), "{\"value\":\"2001-10-20T16:30:00\"}" );

    assertSerializedForm( new Type3( null ), "{\"value\":[null]}" );
    assertSerializedForm( new Type3( date ), "{\"value\":[\"2001-10-20\"]}" );
    assertSerializedForm( new Type3( dateTime ), "{\"value\":[\"2001-10-20\"]}" );
    assertSerializedForm( new Type4( null ), "{\"value\":[null]}" );
    assertSerializedForm( new Type4( date ), "{\"value\":[\"2001-10-20T00:00:00\"]}" );
    assertSerializedForm( new Type4( dateTime ), "{\"value\":[\"2001-10-20T16:30:00\"]}" );
  }

  private void assertSerializedForm( @Nonnull final Object input, @Nonnull final String expected )
  {
    final String json = JsonbBuilder.create().toJson( input );
    assertEquals( json, expected );
  }

  public static class Type1
  {
    @JsonbProperty( nillable = true )
    @JsonbTypeSerializer( DateSerializer.class )
    public Date value;

    public Type1()
    {
    }

    Type1( final Date value )
    {
      this.value = value;
    }

    @Override
    public boolean equals( final Object obj )
    {
      return Objects.equals( value, ( (Type1) obj ).value );
    }
  }

  public static class Type2
  {
    @JsonbProperty( nillable = true )
    @JsonbTypeSerializer( DateTimeSerializer.class )
    public Date value;

    public Type2()
    {
    }

    Type2( final Date value )
    {
      this.value = value;
    }

    @Override
    public boolean equals( final Object obj )
    {
      return Objects.equals( value, ( (Type2) obj ).value );
    }
  }

  public static class Type3
  {
    @JsonbTypeSerializer( DateIterableSerializer.class )
    public Collection<Date> value;

    public Type3()
    {
    }

    Type3( final Date value )
    {
      this.value = Collections.singleton( value );
    }

    @Override
    public boolean equals( final Object obj )
    {
      return Objects.equals( value, ( (Type3) obj ).value );
    }
  }

  public static class Type4
  {
    @JsonbTypeSerializer( DateTimeIterableSerializer.class )
    public Collection<Date> value;

    public Type4()
    {
    }

    Type4( final Date value )
    {
      this.value = Collections.singleton( value );
    }

    @Override
    public boolean equals( final Object obj )
    {
      return Objects.equals( value, ( (Type4) obj ).value );
    }
  }
}
