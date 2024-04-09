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
public class PaymentDTO extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7371316321314774889L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PaymentDTO\",\"namespace\":\"com.hcl.kafka.dto\",\"fields\":[{\"name\":\"bookingId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"ibanClient\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"ibanOperator\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"price\",\"type\":\"double\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PaymentDTO> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PaymentDTO> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PaymentDTO> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PaymentDTO> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PaymentDTO> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PaymentDTO to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PaymentDTO from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PaymentDTO instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PaymentDTO fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String bookingId;
  private java.lang.String ibanClient;
  private java.lang.String ibanOperator;
  private double price;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PaymentDTO() {}

  /**
   * All-args constructor.
   * @param bookingId The new value for bookingId
   * @param ibanClient The new value for ibanClient
   * @param ibanOperator The new value for ibanOperator
   * @param price The new value for price
   */
  public PaymentDTO(java.lang.String bookingId, java.lang.String ibanClient, java.lang.String ibanOperator, java.lang.Double price) {
    this.bookingId = bookingId;
    this.ibanClient = ibanClient;
    this.ibanOperator = ibanOperator;
    this.price = price;
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
    case 1: return ibanClient;
    case 2: return ibanOperator;
    case 3: return price;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: bookingId = value$ != null ? value$.toString() : null; break;
    case 1: ibanClient = value$ != null ? value$.toString() : null; break;
    case 2: ibanOperator = value$ != null ? value$.toString() : null; break;
    case 3: price = (java.lang.Double)value$; break;
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
   * Gets the value of the 'ibanClient' field.
   * @return The value of the 'ibanClient' field.
   */
  public java.lang.String getIbanClient() {
    return ibanClient;
  }


  /**
   * Sets the value of the 'ibanClient' field.
   * @param value the value to set.
   */
  public void setIbanClient(java.lang.String value) {
    this.ibanClient = value;
  }

  /**
   * Gets the value of the 'ibanOperator' field.
   * @return The value of the 'ibanOperator' field.
   */
  public java.lang.String getIbanOperator() {
    return ibanOperator;
  }


  /**
   * Sets the value of the 'ibanOperator' field.
   * @param value the value to set.
   */
  public void setIbanOperator(java.lang.String value) {
    this.ibanOperator = value;
  }

  /**
   * Gets the value of the 'price' field.
   * @return The value of the 'price' field.
   */
  public double getPrice() {
    return price;
  }


  /**
   * Sets the value of the 'price' field.
   * @param value the value to set.
   */
  public void setPrice(double value) {
    this.price = value;
  }

  /**
   * Creates a new PaymentDTO RecordBuilder.
   * @return A new PaymentDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.PaymentDTO.Builder newBuilder() {
    return new com.hcl.kafka.dto.PaymentDTO.Builder();
  }

  /**
   * Creates a new PaymentDTO RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PaymentDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.PaymentDTO.Builder newBuilder(com.hcl.kafka.dto.PaymentDTO.Builder other) {
    if (other == null) {
      return new com.hcl.kafka.dto.PaymentDTO.Builder();
    } else {
      return new com.hcl.kafka.dto.PaymentDTO.Builder(other);
    }
  }

  /**
   * Creates a new PaymentDTO RecordBuilder by copying an existing PaymentDTO instance.
   * @param other The existing instance to copy.
   * @return A new PaymentDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.PaymentDTO.Builder newBuilder(com.hcl.kafka.dto.PaymentDTO other) {
    if (other == null) {
      return new com.hcl.kafka.dto.PaymentDTO.Builder();
    } else {
      return new com.hcl.kafka.dto.PaymentDTO.Builder(other);
    }
  }

  /**
   * RecordBuilder for PaymentDTO instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PaymentDTO>
    implements org.apache.avro.data.RecordBuilder<PaymentDTO> {

    private java.lang.String bookingId;
    private java.lang.String ibanClient;
    private java.lang.String ibanOperator;
    private double price;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.hcl.kafka.dto.PaymentDTO.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.bookingId)) {
        this.bookingId = data().deepCopy(fields()[0].schema(), other.bookingId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.ibanClient)) {
        this.ibanClient = data().deepCopy(fields()[1].schema(), other.ibanClient);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.ibanOperator)) {
        this.ibanOperator = data().deepCopy(fields()[2].schema(), other.ibanOperator);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.price)) {
        this.price = data().deepCopy(fields()[3].schema(), other.price);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing PaymentDTO instance
     * @param other The existing instance to copy.
     */
    private Builder(com.hcl.kafka.dto.PaymentDTO other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.bookingId)) {
        this.bookingId = data().deepCopy(fields()[0].schema(), other.bookingId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ibanClient)) {
        this.ibanClient = data().deepCopy(fields()[1].schema(), other.ibanClient);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ibanOperator)) {
        this.ibanOperator = data().deepCopy(fields()[2].schema(), other.ibanOperator);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.price)) {
        this.price = data().deepCopy(fields()[3].schema(), other.price);
        fieldSetFlags()[3] = true;
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
    public com.hcl.kafka.dto.PaymentDTO.Builder setBookingId(java.lang.String value) {
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
    public com.hcl.kafka.dto.PaymentDTO.Builder clearBookingId() {
      bookingId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'ibanClient' field.
      * @return The value.
      */
    public java.lang.String getIbanClient() {
      return ibanClient;
    }


    /**
      * Sets the value of the 'ibanClient' field.
      * @param value The value of 'ibanClient'.
      * @return This builder.
      */
    public com.hcl.kafka.dto.PaymentDTO.Builder setIbanClient(java.lang.String value) {
      validate(fields()[1], value);
      this.ibanClient = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'ibanClient' field has been set.
      * @return True if the 'ibanClient' field has been set, false otherwise.
      */
    public boolean hasIbanClient() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'ibanClient' field.
      * @return This builder.
      */
    public com.hcl.kafka.dto.PaymentDTO.Builder clearIbanClient() {
      ibanClient = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'ibanOperator' field.
      * @return The value.
      */
    public java.lang.String getIbanOperator() {
      return ibanOperator;
    }


    /**
      * Sets the value of the 'ibanOperator' field.
      * @param value The value of 'ibanOperator'.
      * @return This builder.
      */
    public com.hcl.kafka.dto.PaymentDTO.Builder setIbanOperator(java.lang.String value) {
      validate(fields()[2], value);
      this.ibanOperator = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'ibanOperator' field has been set.
      * @return True if the 'ibanOperator' field has been set, false otherwise.
      */
    public boolean hasIbanOperator() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'ibanOperator' field.
      * @return This builder.
      */
    public com.hcl.kafka.dto.PaymentDTO.Builder clearIbanOperator() {
      ibanOperator = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'price' field.
      * @return The value.
      */
    public double getPrice() {
      return price;
    }


    /**
      * Sets the value of the 'price' field.
      * @param value The value of 'price'.
      * @return This builder.
      */
    public com.hcl.kafka.dto.PaymentDTO.Builder setPrice(double value) {
      validate(fields()[3], value);
      this.price = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'price' field has been set.
      * @return True if the 'price' field has been set, false otherwise.
      */
    public boolean hasPrice() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'price' field.
      * @return This builder.
      */
    public com.hcl.kafka.dto.PaymentDTO.Builder clearPrice() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaymentDTO build() {
      try {
        PaymentDTO record = new PaymentDTO();
        record.bookingId = fieldSetFlags()[0] ? this.bookingId : (java.lang.String) defaultValue(fields()[0]);
        record.ibanClient = fieldSetFlags()[1] ? this.ibanClient : (java.lang.String) defaultValue(fields()[1]);
        record.ibanOperator = fieldSetFlags()[2] ? this.ibanOperator : (java.lang.String) defaultValue(fields()[2]);
        record.price = fieldSetFlags()[3] ? this.price : (java.lang.Double) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PaymentDTO>
    WRITER$ = (org.apache.avro.io.DatumWriter<PaymentDTO>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PaymentDTO>
    READER$ = (org.apache.avro.io.DatumReader<PaymentDTO>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.bookingId);

    out.writeString(this.ibanClient);

    out.writeString(this.ibanOperator);

    out.writeDouble(this.price);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.bookingId = in.readString();

      this.ibanClient = in.readString();

      this.ibanOperator = in.readString();

      this.price = in.readDouble();

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.bookingId = in.readString();
          break;

        case 1:
          this.ibanClient = in.readString();
          break;

        case 2:
          this.ibanOperator = in.readString();
          break;

        case 3:
          this.price = in.readDouble();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










