package com.tencent.mobileqq.msf.core.net.a;

import java.net.Socket;

public abstract interface e
{
  public abstract String a(int paramInt);
  
  public abstract void a();
  
  public abstract void a(int paramInt, boolean paramBoolean);
  
  public abstract void a(Socket paramSocket);
  
  public abstract void a(byte[] paramArrayOfByte, Socket paramSocket, int paramInt);
  
  public abstract boolean b(int paramInt, boolean paramBoolean);
}