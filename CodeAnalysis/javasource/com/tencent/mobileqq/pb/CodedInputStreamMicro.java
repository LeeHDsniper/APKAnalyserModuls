package com.tencent.mobileqq.pb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public final class CodedInputStreamMicro
{
  private static final int BUFFER_SIZE = 4096;
  private static final int DEFAULT_RECURSION_LIMIT = 64;
  private static final int DEFAULT_SIZE_LIMIT = 67108864;
  private final byte[] buffer;
  private int bufferPos;
  private int bufferSize;
  private int bufferSizeAfterLimit;
  private int currentLimit = Integer.MAX_VALUE;
  private final InputStream input;
  private int lastTag;
  private int recursionDepth;
  private int recursionLimit = 64;
  private int sizeLimit = 67108864;
  private int totalBytesRetired;
  
  private CodedInputStreamMicro(InputStream paramInputStream)
  {
    buffer = new byte['က'];
    bufferSize = 0;
    bufferPos = 0;
    input = paramInputStream;
  }
  
  private CodedInputStreamMicro(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    buffer = paramArrayOfByte;
    bufferSize = (paramInt1 + paramInt2);
    bufferPos = paramInt1;
    input = null;
  }
  
  public static int decodeZigZag32(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }
  
  public static long decodeZigZag64(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }
  
  public static CodedInputStreamMicro newInstance(InputStream paramInputStream)
  {
    return new CodedInputStreamMicro(paramInputStream);
  }
  
  public static CodedInputStreamMicro newInstance(byte[] paramArrayOfByte)
  {
    return newInstance(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static CodedInputStreamMicro newInstance(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new CodedInputStreamMicro(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  static int readRawVarint32(InputStream paramInputStream)
    throws IOException
  {
    int i = 0;
    int j = 0;
    int k;
    for (;;)
    {
      k = i;
      if (i >= 32) {
        break;
      }
      k = paramInputStream.read();
      if (k == -1) {
        throw InvalidProtocolBufferMicroException.truncatedMessage();
      }
      j |= (k & 0x7F) << i;
      if ((k & 0x80) == 0) {
        return j;
      }
      i += 7;
    }
    do
    {
      k += 7;
      if (k >= 64) {
        break;
      }
      i = paramInputStream.read();
      if (i == -1) {
        throw InvalidProtocolBufferMicroException.truncatedMessage();
      }
    } while ((i & 0x80) != 0);
    return j;
    throw InvalidProtocolBufferMicroException.malformedVarint();
  }
  
  private void recomputeBufferSizeAfterLimit()
  {
    bufferSize += bufferSizeAfterLimit;
    int i = totalBytesRetired + bufferSize;
    if (i > currentLimit)
    {
      bufferSizeAfterLimit = (i - currentLimit);
      bufferSize -= bufferSizeAfterLimit;
      return;
    }
    bufferSizeAfterLimit = 0;
  }
  
  private boolean refillBuffer(boolean paramBoolean)
    throws IOException
  {
    if (bufferPos < bufferSize) {
      throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
    }
    if (totalBytesRetired + bufferSize == currentLimit)
    {
      if (paramBoolean) {
        throw InvalidProtocolBufferMicroException.truncatedMessage();
      }
      return false;
    }
    totalBytesRetired += bufferSize;
    bufferPos = 0;
    if (input == null) {}
    for (int i = -1;; i = input.read(buffer))
    {
      bufferSize = i;
      if ((bufferSize != 0) && (bufferSize >= -1)) {
        break;
      }
      throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + bufferSize + "\nThe InputStream implementation is buggy.");
    }
    if (bufferSize == -1)
    {
      bufferSize = 0;
      if (paramBoolean) {
        throw InvalidProtocolBufferMicroException.truncatedMessage();
      }
      return false;
    }
    recomputeBufferSizeAfterLimit();
    i = totalBytesRetired + bufferSize + bufferSizeAfterLimit;
    if ((i > sizeLimit) || (i < 0)) {
      throw InvalidProtocolBufferMicroException.sizeLimitExceeded();
    }
    return true;
  }
  
  public void checkLastTagWas(int paramInt)
    throws InvalidProtocolBufferMicroException
  {
    if (lastTag != paramInt) {
      throw InvalidProtocolBufferMicroException.invalidEndTag();
    }
  }
  
  public int getBytesUntilLimit()
  {
    if (currentLimit == Integer.MAX_VALUE) {
      return -1;
    }
    int i = totalBytesRetired;
    int j = bufferPos;
    return currentLimit - (i + j);
  }
  
  public boolean isAtEnd()
    throws IOException
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (bufferPos == bufferSize)
    {
      bool1 = bool2;
      if (!refillBuffer(false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public void popLimit(int paramInt)
  {
    currentLimit = paramInt;
    recomputeBufferSizeAfterLimit();
  }
  
  public int pushLimit(int paramInt)
    throws InvalidProtocolBufferMicroException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferMicroException.negativeSize();
    }
    paramInt = totalBytesRetired + bufferPos + paramInt;
    int i = currentLimit;
    if (paramInt > i) {
      throw InvalidProtocolBufferMicroException.truncatedMessage();
    }
    currentLimit = paramInt;
    recomputeBufferSizeAfterLimit();
    return i;
  }
  
  public boolean readBool()
    throws IOException
  {
    return readRawVarint32() != 0;
  }
  
  public ByteStringMicro readBytes()
    throws IOException
  {
    int i = readRawVarint32();
    if ((i <= bufferSize - bufferPos) && (i > 0))
    {
      ByteStringMicro localByteStringMicro = ByteStringMicro.copyFrom(buffer, bufferPos, i);
      bufferPos = (i + bufferPos);
      return localByteStringMicro;
    }
    return ByteStringMicro.copyFrom(readRawBytes(i));
  }
  
  public double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(readRawLittleEndian64());
  }
  
  public int readEnum()
    throws IOException
  {
    return readRawVarint32();
  }
  
  public int readFixed32()
    throws IOException
  {
    return readRawLittleEndian32();
  }
  
  public long readFixed64()
    throws IOException
  {
    return readRawLittleEndian64();
  }
  
  public float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(readRawLittleEndian32());
  }
  
  public void readGroup(MessageMicro<?> paramMessageMicro, int paramInt)
    throws IOException
  {
    if (recursionDepth >= recursionLimit) {
      throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
    }
    recursionDepth += 1;
    paramMessageMicro.mergeFrom(this);
    checkLastTagWas(WireFormatMicro.makeTag(paramInt, 4));
    recursionDepth -= 1;
  }
  
  public int readInt32()
    throws IOException
  {
    return readRawVarint32();
  }
  
  public long readInt64()
    throws IOException
  {
    return readRawVarint64();
  }
  
  public void readMessage(MessageMicro<?> paramMessageMicro)
    throws IOException
  {
    int i = readRawVarint32();
    if (recursionDepth >= recursionLimit) {
      throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
    }
    i = pushLimit(i);
    recursionDepth += 1;
    paramMessageMicro.mergeFrom(this);
    checkLastTagWas(0);
    recursionDepth -= 1;
    popLimit(i);
  }
  
  public byte readRawByte()
    throws IOException
  {
    if (bufferPos == bufferSize) {
      refillBuffer(true);
    }
    byte[] arrayOfByte = buffer;
    int i = bufferPos;
    bufferPos = (i + 1);
    return arrayOfByte[i];
  }
  
  public byte[] readRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferMicroException.negativeSize();
    }
    if (totalBytesRetired + bufferPos + paramInt > currentLimit)
    {
      skipRawBytes(currentLimit - totalBytesRetired - bufferPos);
      throw InvalidProtocolBufferMicroException.truncatedMessage();
    }
    if (paramInt <= bufferSize - bufferPos)
    {
      localObject = new byte[paramInt];
      System.arraycopy(buffer, bufferPos, localObject, 0, paramInt);
      bufferPos += paramInt;
      return localObject;
    }
    if (paramInt < 4096)
    {
      localObject = new byte[paramInt];
      i = bufferSize - bufferPos;
      System.arraycopy(buffer, bufferPos, localObject, 0, i);
      bufferPos = bufferSize;
      refillBuffer(true);
      while (paramInt - i > bufferSize)
      {
        System.arraycopy(buffer, 0, localObject, i, bufferSize);
        i += bufferSize;
        bufferPos = bufferSize;
        refillBuffer(true);
      }
      System.arraycopy(buffer, 0, localObject, i, paramInt - i);
      bufferPos = (paramInt - i);
      return localObject;
    }
    int m = bufferPos;
    int n = bufferSize;
    totalBytesRetired += bufferSize;
    bufferPos = 0;
    bufferSize = 0;
    Object localObject = new ArrayList();
    int i = paramInt - (n - m);
    while (i > 0)
    {
      arrayOfByte1 = new byte[Math.min(i, 4096)];
      int j = 0;
      while (j < arrayOfByte1.length)
      {
        if (input == null) {}
        for (int k = -1; k == -1; k = input.read(arrayOfByte1, j, arrayOfByte1.length - j)) {
          throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        totalBytesRetired += k;
        j += k;
      }
      j = arrayOfByte1.length;
      ((ArrayList)localObject).add(arrayOfByte1);
      i -= j;
    }
    byte[] arrayOfByte1 = new byte[paramInt];
    i = n - m;
    System.arraycopy(buffer, m, arrayOfByte1, 0, i);
    paramInt = 0;
    while (paramInt < ((ArrayList)localObject).size())
    {
      byte[] arrayOfByte2 = (byte[])((ArrayList)localObject).get(paramInt);
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, i, arrayOfByte2.length);
      i += arrayOfByte2.length;
      paramInt += 1;
    }
    return arrayOfByte1;
  }
  
  public int readRawLittleEndian32()
    throws IOException
  {
    return readRawByte() & 0xFF | (readRawByte() & 0xFF) << 8 | (readRawByte() & 0xFF) << 16 | (readRawByte() & 0xFF) << 24;
  }
  
  public long readRawLittleEndian64()
    throws IOException
  {
    int i = readRawByte();
    int j = readRawByte();
    int k = readRawByte();
    int m = readRawByte();
    int n = readRawByte();
    int i1 = readRawByte();
    int i2 = readRawByte();
    int i3 = readRawByte();
    long l = i;
    return (j & 0xFF) << 8 | l & 0xFF | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }
  
  public int readRawVarint32()
    throws IOException
  {
    int i = readRawByte();
    if (i >= 0) {}
    int k;
    do
    {
      return i;
      i &= 0x7F;
      j = readRawByte();
      if (j >= 0) {
        return i | j << 7;
      }
      i |= (j & 0x7F) << 7;
      j = readRawByte();
      if (j >= 0) {
        return i | j << 14;
      }
      i |= (j & 0x7F) << 14;
      k = readRawByte();
      if (k >= 0) {
        return i | k << 21;
      }
      j = readRawByte();
      k = i | (k & 0x7F) << 21 | j << 28;
      i = k;
    } while (j >= 0);
    int j = 0;
    for (;;)
    {
      if (j >= 5) {
        break label133;
      }
      i = k;
      if (readRawByte() >= 0) {
        break;
      }
      j += 1;
    }
    label133:
    throw InvalidProtocolBufferMicroException.malformedVarint();
  }
  
  public long readRawVarint64()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = readRawByte();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw InvalidProtocolBufferMicroException.malformedVarint();
  }
  
  public int readSFixed32()
    throws IOException
  {
    return readRawLittleEndian32();
  }
  
  public long readSFixed64()
    throws IOException
  {
    return readRawLittleEndian64();
  }
  
  public int readSInt32()
    throws IOException
  {
    return decodeZigZag32(readRawVarint32());
  }
  
  public long readSInt64()
    throws IOException
  {
    return decodeZigZag64(readRawVarint64());
  }
  
  public String readString()
    throws IOException
  {
    int i = readRawVarint32();
    if ((i <= bufferSize - bufferPos) && (i > 0))
    {
      String str = new String(buffer, bufferPos, i, "UTF-8");
      bufferPos = (i + bufferPos);
      return str;
    }
    return new String(readRawBytes(i), "UTF-8");
  }
  
  public int readTag()
    throws IOException
  {
    if (isAtEnd())
    {
      lastTag = 0;
      return 0;
    }
    lastTag = readRawVarint32();
    if (lastTag == 0) {
      throw InvalidProtocolBufferMicroException.invalidTag();
    }
    return lastTag;
  }
  
  public int readUInt32()
    throws IOException
  {
    return readRawVarint32();
  }
  
  public long readUInt64()
    throws IOException
  {
    return readRawVarint64();
  }
  
  public void resetSizeCounter()
  {
    totalBytesRetired = 0;
  }
  
  public int setRecursionLimit(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Recursion limit cannot be negative: " + paramInt);
    }
    int i = recursionLimit;
    recursionLimit = paramInt;
    return i;
  }
  
  public int setSizeLimit(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("Size limit cannot be negative: " + paramInt);
    }
    int i = sizeLimit;
    sizeLimit = paramInt;
    return i;
  }
  
  public boolean skipField(int paramInt)
    throws IOException
  {
    switch (WireFormatMicro.getTagWireType(paramInt))
    {
    default: 
      throw InvalidProtocolBufferMicroException.invalidWireType();
    case 0: 
      readInt32();
      return true;
    case 1: 
      readRawLittleEndian64();
      return true;
    case 2: 
      skipRawBytes(readRawVarint32());
      return true;
    case 3: 
      skipMessage();
      checkLastTagWas(WireFormatMicro.makeTag(WireFormatMicro.getTagFieldNumber(paramInt), 4));
      return true;
    case 4: 
      return false;
    }
    readRawLittleEndian32();
    return true;
  }
  
  public void skipMessage()
    throws IOException
  {
    int i;
    do
    {
      i = readTag();
    } while ((i != 0) && (skipField(i)));
  }
  
  public void skipRawBytes(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw InvalidProtocolBufferMicroException.negativeSize();
    }
    if (totalBytesRetired + bufferPos + paramInt > currentLimit)
    {
      skipRawBytes(currentLimit - totalBytesRetired - bufferPos);
      throw InvalidProtocolBufferMicroException.truncatedMessage();
    }
    if (paramInt <= bufferSize - bufferPos) {
      bufferPos += paramInt;
    }
    for (;;)
    {
      return;
      int i = bufferSize - bufferPos;
      totalBytesRetired += i;
      bufferPos = 0;
      bufferSize = 0;
      while (i < paramInt)
      {
        if (input == null) {}
        for (int j = -1; j <= 0; j = (int)input.skip(paramInt - i)) {
          throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        i += j;
        totalBytesRetired = (j + totalBytesRetired);
      }
    }
  }
}