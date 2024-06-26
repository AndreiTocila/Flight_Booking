/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.hcl.kafka.dto;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SeatReservationDTO extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -748379938904054091L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SeatReservationDTO\",\"namespace\":\"com.hcl.kafka.dto\",\"fields\":[{\"name\":\"bookingId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"numberOfSeats\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SeatReservationDTO> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SeatReservationDTO> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SeatReservationDTO> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SeatReservationDTO> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SeatReservationDTO> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SeatReservationDTO to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SeatReservationDTO from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SeatReservationDTO instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SeatReservationDTO fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String bookingId;
  private long numberOfSeats;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SeatReservationDTO() {}

  /**
   * All-args constructor.
   * @param bookingId The new value for bookingId
   * @param numberOfSeats The new value for numberOfSeats
   */
  public SeatReservationDTO(java.lang.String bookingId, java.lang.Long numberOfSeats) {
    this.bookingId = bookingId;
    this.numberOfSeats = numberOfSeats;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return bookingId;
    case 1: return numberOfSeats;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: bookingId = value$ != null ? value$.toString() : null; break;
    case 1: numberOfSeats = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'bookingId' field.
   * @return The value of the 'bookingId' field.
   */
  public java.lang.String getBookingId() {
    return bookingId;
  }


  /**
   * Sets the value of the 'bookingId' field.
   * @param value the value to set.
   */
  public void setBookingId(java.lang.String value) {
    this.bookingId = value;
  }

  /**
   * Gets the value of the 'numberOfSeats' field.
   * @return The value of the 'numberOfSeats' field.
   */
  public long getNumberOfSeats() {
    return numberOfSeats;
  }


  /**
   * Sets the value of the 'numberOfSeats' field.
   * @param value the value to set.
   */
  public void setNumberOfSeats(long value) {
    this.numberOfSeats = value;
  }

  /**
   * Creates a new SeatReservationDTO RecordBuilder.
   * @return A new SeatReservationDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.SeatReservationDTO.Builder newBuilder() {
    return new com.hcl.kafka.dto.SeatReservationDTO.Builder();
  }

  /**
   * Creates a new SeatReservationDTO RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SeatReservationDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.SeatReservationDTO.Builder newBuilder(com.hcl.kafka.dto.SeatReservationDTO.Builder other) {
    if (other == null) {
      return new com.hcl.kafka.dto.SeatReservationDTO.Builder();
    } else {
      return new com.hcl.kafka.dto.SeatReservationDTO.Builder(other);
    }
  }

  /**
   * Creates a new SeatReservationDTO RecordBuilder by copying an existing SeatReservationDTO instance.
   * @param other The existing instance to copy.
   * @return A new SeatReservationDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.SeatReservationDTO.Builder newBuilder(com.hcl.kafka.dto.SeatReservationDTO other) {
    if (other == null) {
      return new com.hcl.kafka.dto.SeatReservationDTO.Builder();
    } else {
      return new com.hcl.kafka.dto.SeatReservationDTO.Builder(other);
    }
  }

  /**
   * RecordBuilder for SeatReservationDTO instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SeatReservationDTO>
    implements org.apache.avro.data.RecordBuilder<SeatReservationDTO> {

    private java.lang.String bookingId;
    private long numberOfSeats;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.hcl.kafka.dto.SeatReservationDTO.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.bookingId)) {
        this.bookingId = data().deepCopy(fields()[0].schema(), other.bookingId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.numberOfSeats)) {
        this.numberOfSeats = data().deepCopy(fields()[1].schema(), other.numberOfSeats);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing SeatReservationDTO instance
     * @param other The existing instance to copy.
     */
    private Builder(com.hcl.kafka.dto.SeatReservationDTO other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.bookingId)) {
        this.bookingId = data().deepCopy(fields()[0].schema(), other.bookingId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.numberOfSeats)) {
        this.numberOfSeats = data().deepCopy(fields()[1].schema(), other.numberOfSeats);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'bookingId' field.
      * @return The value.
      */
    public java.lang.String getBookingId() {
      return bookingId;
    }


    /**
      * Sets the value of the 'bookingId' field.
      * @param value The value of 'bookingId'.
      * @return This builder.
      */
    public com.hcl.kafka.dto.SeatReservationDTO.Builder setBookingId(java.lang.String value) {
      validate(fields()[0], value);
      this.bookingId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'bookingId' field has been set.
      * @return True if the 'bookingId' field has been set, false otherwise.
      */
    public boolean hasBookingId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'bookingId' field.
      * @return This builder.
      */
    public com.hcl.kafka.dto.SeatReservationDTO.Builder clearBookingId() {
      bookingId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'numberOfSeats' field.
      * @return The value.
      */
    public long getNumberOfSeats() {
      return numberOfSeats;
    }


    /**
      * Sets the value of the 'numberOfSeats' field.
      * @param value The value of 'numberOfSeats'.
      * @return This builder.
      */
    public com.hcl.kafka.dto.SeatReservationDTO.Builder setNumberOfSeats(long value) {
      validate(fields()[1], value);
      this.numberOfSeats = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'numberOfSeats' field has been set.
      * @return True if the 'numberOfSeats' field has been set, false otherwise.
      */
    public boolean hasNumberOfSeats() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'numberOfSeats' field.
      * @return This builder.
      */
    public com.hcl.kafka.dto.SeatReservationDTO.Builder clearNumberOfSeats() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SeatReservationDTO build() {
      try {
        SeatReservationDTO record = new SeatReservationDTO();
        record.bookingId = fieldSetFlags()[0] ? this.bookingId : (java.lang.String) defaultValue(fields()[0]);
        record.numberOfSeats = fieldSetFlags()[1] ? this.numberOfSeats : (java.lang.Long) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SeatReservationDTO>
    WRITER$ = (org.apache.avro.io.DatumWriter<SeatReservationDTO>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SeatReservationDTO>
    READER$ = (org.apache.avro.io.DatumReader<SeatReservationDTO>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.bookingId);

    out.writeLong(this.numberOfSeats);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.bookingId = in.readString();

      this.numberOfSeats = in.readLong();

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.bookingId = in.readString();
          break;

        case 1:
          this.numberOfSeats = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










