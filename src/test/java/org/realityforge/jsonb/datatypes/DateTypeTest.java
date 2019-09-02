package org.realityforge.jsonb.datatypes;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.annotation.Nonnull;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;
import org.realityforge.guiceyloops.shared.ValueUtil;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@SuppressWarnings( { "WeakerAccess", "unused" } )
public class DateTypeTest
{
  @Test
  public void serializeDateTypes()
  {
    final Date date = ValueUtil.createDate( 2001, 10, 20 );
    final Date dateTime = ValueUtil.createDate( 2001, 10, 20, 16, 30, 0 );

    assertSerializedForm( Type1.class, new Type1( null ), "{\"value\":null}" );
    assertSerializedForm( Type1.class, new Type1( date ), "{\"value\":\"2001-10-20\"}" );
    assertSerializedForm( Type1.class, new Type1( dateTime ), "{\"value\":\"2001-10-20\"}" );
    assertSerializedForm( Type2.class, new Type2( null ), "{\"value\":null}" );
    assertSerializedForm( Type2.class, new Type2( date ), "{\"value\":\"2001-10-20T00:00:00\"}" );
    assertSerializedForm( Type2.class, new Type2( dateTime ), "{\"value\":\"2001-10-20T16:30:00\"}" );

    assertSerializedForm( Type3.class, new Type3( null ), "{\"value\":[null]}" );
    assertSerializedForm( Type3.class, new Type3( date ), "{\"value\":[\"2001-10-20\"]}" );
    assertSerializedForm( Type3.class, new Type3( dateTime ), "{\"value\":[\"2001-10-20\"]}" );
    assertSerializedForm( Type4.class, new Type4( null ), "{\"value\":[null]}" );
    assertSerializedForm( Type4.class, new Type4( date ), "{\"value\":[\"2001-10-20T00:00:00\"]}" );
    assertSerializedForm( Type4.class, new Type4( dateTime ), "{\"value\":[\"2001-10-20T16:30:00\"]}" );
  }

  private <T> void assertSerializedForm( @Nonnull final Class<T> type,
                                         @Nonnull final T input,
                                         @Nonnull final String expected )
  {
    final Jsonb jsonb = JsonbBuilder.create();
    final String json = jsonb.toJson( input );
    assertEquals( json, expected );
    assertEquals( jsonb.toJson( jsonb.fromJson( json, type ) ), json );
  }

  public static class Type1
  {
    @JsonbProperty( nillable = true )
    @JsonbTypeSerializer( DateSerializer.class )
    @JsonbTypeDeserializer( DateDeserializer.class )
    public Date value;

    public Type1()
    {
    }

    Type1( final Date value )
    {
      this.value = value;
    }
  }

  public static class Type2
  {
    @JsonbProperty( nillable = true )
    @JsonbTypeSerializer( DateTimeSerializer.class )
    @JsonbTypeDeserializer( DateTimeDeserializer.class )
    public Date value;

    public Type2()
    {
    }

    Type2( final Date value )
    {
      this.value = value;
    }
  }

  public static class Type3
  {
    @JsonbTypeSerializer( DateIterableSerializer.class )
    @JsonbTypeDeserializer( DateListDeserializer.class )
    public Collection<Date> value;

    public Type3()
    {
    }

    Type3( final Date value )
    {
      this.value = Collections.singleton( value );
    }
  }

  public static class Type4
  {
    @JsonbTypeSerializer( DateTimeIterableSerializer.class )
    @JsonbTypeDeserializer( DateTimeListDeserializer.class )
    public Collection<Date> value;

    public Type4()
    {
    }

    Type4( final Date value )
    {
      this.value = Collections.singleton( value );
    }
  }
}
