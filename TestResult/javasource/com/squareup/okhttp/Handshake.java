package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public final class Handshake
{
  private final String cipherSuite;
  private final List<Certificate> localCertificates;
  private final List<Certificate> peerCertificates;
  
  private Handshake(String paramString, List<Certificate> paramList1, List<Certificate> paramList2)
  {
    cipherSuite = paramString;
    peerCertificates = paramList1;
    localCertificates = paramList2;
  }
  
  public static Handshake get(String paramString, List<Certificate> paramList1, List<Certificate> paramList2)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("cipherSuite == null");
    }
    return new Handshake(paramString, Util.immutableList(paramList1), Util.immutableList(paramList2));
  }
  
  public static Handshake get(SSLSession paramSSLSession)
  {
    String str = paramSSLSession.getCipherSuite();
    if (str == null) {
      throw new IllegalStateException("cipherSuite == null");
    }
    try
    {
      Object localObject = paramSSLSession.getPeerCertificates();
      if (localObject != null)
      {
        localObject = Util.immutableList((Object[])localObject);
        paramSSLSession = paramSSLSession.getLocalCertificates();
        if (paramSSLSession == null) {
          break label77;
        }
        paramSSLSession = Util.immutableList(paramSSLSession);
        return new Handshake(str, (List)localObject, paramSSLSession);
      }
    }
    catch (SSLPeerUnverifiedException localSSLPeerUnverifiedException)
    {
      for (;;)
      {
        List localList = null;
        continue;
        localList = Collections.emptyList();
        continue;
        label77:
        paramSSLSession = Collections.emptyList();
      }
    }
  }
  
  public String cipherSuite()
  {
    return cipherSuite;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Handshake)) {}
    do
    {
      return false;
      paramObject = (Handshake)paramObject;
    } while ((!cipherSuite.equals(cipherSuite)) || (!peerCertificates.equals(peerCertificates)) || (!localCertificates.equals(localCertificates)));
    return true;
  }
  
  public int hashCode()
  {
    return ((cipherSuite.hashCode() + 527) * 31 + peerCertificates.hashCode()) * 31 + localCertificates.hashCode();
  }
  
  public List<Certificate> localCertificates()
  {
    return localCertificates;
  }
  
  public Principal localPrincipal()
  {
    if (!localCertificates.isEmpty()) {
      return ((X509Certificate)localCertificates.get(0)).getSubjectX500Principal();
    }
    return null;
  }
  
  public List<Certificate> peerCertificates()
  {
    return peerCertificates;
  }
  
  public Principal peerPrincipal()
  {
    if (!peerCertificates.isEmpty()) {
      return ((X509Certificate)peerCertificates.get(0)).getSubjectX500Principal();
    }
    return null;
  }
}
