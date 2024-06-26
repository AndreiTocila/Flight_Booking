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
public class NotificationDTO extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1405357686971291228L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NotificationDTO\",\"namespace\":\"com.hcl.kafka.dto\",\"fields\":[{\"name\":\"userEmail\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"notificationMessage\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<NotificationDTO> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<NotificationDTO> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<NotificationDTO> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<NotificationDTO> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<NotificationDTO> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this NotificationDTO to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a NotificationDTO from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a NotificationDTO instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static NotificationDTO fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String userEmail;
  private java.lang.String notificationMessage;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NotificationDTO() {}

  /**
   * All-args constructor.
   * @param userEmail The new value for userEmail
   * @param notificationMessage The new value for notificationMessage
   */
  public NotificationDTO(java.lang.String userEmail, java.lang.String notificationMessage) {
    this.userEmail = userEmail;
    this.notificationMessage = notificationMessage;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return userEmail;
    case 1: return notificationMessage;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: userEmail = value$ != null ? value$.toString() : null; break;
    case 1: notificationMessage = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'userEmail' field.
   * @return The value of the 'userEmail' field.
   */
  public java.lang.String getUserEmail() {
    return userEmail;
  }


  /**
   * Sets the value of the 'userEmail' field.
   * @param value the value to set.
   */
  public void setUserEmail(java.lang.String value) {
    this.userEmail = value;
  }

  /**
   * Gets the value of the 'notificationMessage' field.
   * @return The value of the 'notificationMessage' field.
   */
  public java.lang.String getNotificationMessage() {
    return notificationMessage;
  }


  /**
   * Sets the value of the 'notificationMessage' field.
   * @param value the value to set.
   */
  public void setNotificationMessage(java.lang.String value) {
    this.notificationMessage = value;
  }

  /**
   * Creates a new NotificationDTO RecordBuilder.
   * @return A new NotificationDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.NotificationDTO.Builder newBuilder() {
    return new com.hcl.kafka.dto.NotificationDTO.Builder();
  }

  /**
   * Creates a new NotificationDTO RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NotificationDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.NotificationDTO.Builder newBuilder(com.hcl.kafka.dto.NotificationDTO.Builder other) {
    if (other == null) {
      return new com.hcl.kafka.dto.NotificationDTO.Builder();
    } else {
      return new com.hcl.kafka.dto.NotificationDTO.Builder(other);
    }
  }

  /**
   * Creates a new NotificationDTO RecordBuilder by copying an existing NotificationDTO instance.
   * @param other The existing instance to copy.
   * @return A new NotificationDTO RecordBuilder
   */
  public static com.hcl.kafka.dto.NotificationDTO.Builder newBuilder(com.hcl.kafka.dto.NotificationDTO other) {
    if (other == null) {
      return new com.hcl.kafka.dto.NotificationDTO.Builder();
    } else {
      return new com.hcl.kafka.dto.NotificationDTO.Builder(other);
    }
  }

  /**
   * RecordBuilder for NotificationDTO instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NotificationDTO>
    implements org.apache.avro.data.RecordBuilder<NotificationDTO> {

    private java.lang.String userEmail;
    private java.lang.String notificationMessage;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.hcl.kafka.dto.NotificationDTO.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.userEmail)) {
        this.userEmail = data().deepCopy(fields()[0].schema(), other.userEmail);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.notificationMessage)) {
        this.notificationMessage = data().deepCopy(fields()[1].schema(), other.notificationMessage);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing NotificationDTO instance
     * @param other The existing instance to copy.
     */
    private Builder(com.hcl.kafka.dto.NotificationDTO other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.userEmail)) {
        this.userEmail = data().deepCopy(fields()[0].schema(), other.userEmail);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.notificationMessage)) {
        this.notificationMessage = data().deepCopy(fields()[1].schema(), other.notificationMessage);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'userEmail' field.
      * @return The value.
      */
    public java.lang.String getUserEmail() {
      return userEmail;
    }


    /**
      * Sets the value of the 'userEmail' field.
      * @param value The value of 'userEmail'.
      * @return This builder.
      */
    public com.hcl.kafka.dto.NotificationDTO.Builder setUserEmail(java.lang.String value) {
      validate(fields()[0], value);
      this.userEmail = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'userEmail' field has been set.
      * @return True if the 'userEmail' field has been set, false otherwise.
      */
    public boolean hasUserEmail() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'userEmail' field.
      * @return This builder.
      */
    public com.hcl.kafka.dto.NotificationDTO.Builder clearUserEmail() {
      userEmail = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'notificationMessage' field.
      * @return The value.
      */
    public java.lang.String getNotificationMessage() {
      return notificationMessage;
    }


    /**
      * Sets the value of the 'notificationMessage' field.
      * @param value The value of 'notificationMessage'.
      * @return This builder.
      */
    public com.hcl.kafka.dto.NotificationDTO.Builder setNotificationMessage(java.lang.String value) {
      validate(fields()[1], value);
      this.notificationMessage = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'notificationMessage' field has been set.
      * @return True if the 'notificationMessage' field has been set, false otherwise.
      */
    public boolean hasNotificationMessage() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'notificationMessage' field.
      * @return This builder.
      */
    public com.hcl.kafka.dto.NotificationDTO.Builder clearNotificationMessage() {
      notificationMessage = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NotificationDTO build() {
      try {
        NotificationDTO record = new NotificationDTO();
        record.userEmail = fieldSetFlags()[0] ? this.userEmail : (java.lang.String) defaultValue(fields()[0]);
        record.notificationMessage = fieldSetFlags()[1] ? this.notificationMessage : (java.lang.String) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<NotificationDTO>
    WRITER$ = (org.apache.avro.io.DatumWriter<NotificationDTO>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<NotificationDTO>
    READER$ = (org.apache.avro.io.DatumReader<NotificationDTO>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.userEmail);

    out.writeString(this.notificationMessage);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.userEmail = in.readString();

      this.notificationMessage = in.readString();

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.userEmail = in.readString();
          break;

        case 1:
          this.notificationMessage = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










