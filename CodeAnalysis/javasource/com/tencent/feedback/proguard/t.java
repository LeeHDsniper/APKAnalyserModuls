package com.tencent.feedback.proguard;

import android.content.Context;
import android.util.SparseArray;
import com.tencent.feedback.common.b;
import com.tencent.feedback.upload.d;
import java.util.ArrayList;
import java.util.List;

public final class t
{
  private static t a = null;
  private w b = null;
  private boolean c = false;
  private int d = 0;
  private d e = null;
  private Runnable f = null;
  private List<s> g = new ArrayList(5);
  private SparseArray<com.tencent.feedback.upload.e> h = new SparseArray(5);
  private List<x> i = new ArrayList(5);
  
  private t(Context paramContext)
  {
    e = new r(paramContext);
    f = new u(paramContext);
    b.b().a(f);
  }
  
  public static t a(Context paramContext)
  {
    try
    {
      if ((a == null) && (paramContext != null)) {
        a = new t(paramContext);
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
  
  /* Error */
  public static com.tencent.feedback.upload.e a()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 32	com/tencent/feedback/proguard/t:a	Lcom/tencent/feedback/proguard/t;
    //   6: ifnull +15 -> 21
    //   9: getstatic 32	com/tencent/feedback/proguard/t:a	Lcom/tencent/feedback/proguard/t;
    //   12: invokespecial 84	com/tencent/feedback/proguard/t:f	()Lcom/tencent/feedback/upload/e;
    //   15: astore_0
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: aconst_null
    //   22: astore_0
    //   23: goto -7 -> 16
    //   26: astore_0
    //   27: ldc 2
    //   29: monitorexit
    //   30: aload_0
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   15	8	0	localE	com.tencent.feedback.upload.e
    //   26	5	0	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	16	26	finally
  }
  
  /* Error */
  private com.tencent.feedback.upload.e f()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 59	com/tencent/feedback/proguard/t:h	Landroid/util/SparseArray;
    //   6: ifnull +29 -> 35
    //   9: aload_0
    //   10: getfield 59	com/tencent/feedback/proguard/t:h	Landroid/util/SparseArray;
    //   13: invokevirtual 88	android/util/SparseArray:size	()I
    //   16: ifle +19 -> 35
    //   19: aload_0
    //   20: getfield 59	com/tencent/feedback/proguard/t:h	Landroid/util/SparseArray;
    //   23: iconst_0
    //   24: invokevirtual 92	android/util/SparseArray:valueAt	(I)Ljava/lang/Object;
    //   27: checkcast 94	com/tencent/feedback/upload/e
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: areturn
    //   35: aconst_null
    //   36: astore_1
    //   37: goto -6 -> 31
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	t
    //   30	7	1	localE	com.tencent.feedback.upload.e
    //   40	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	31	40	finally
  }
  
  private boolean g()
  {
    try
    {
      boolean bool = c;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private x[] h()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 61	com/tencent/feedback/proguard/t:i	Ljava/util/List;
    //   6: ifnull +36 -> 42
    //   9: aload_0
    //   10: getfield 61	com/tencent/feedback/proguard/t:i	Ljava/util/List;
    //   13: invokeinterface 99 1 0
    //   18: ifle +24 -> 42
    //   21: aload_0
    //   22: getfield 61	com/tencent/feedback/proguard/t:i	Ljava/util/List;
    //   25: iconst_0
    //   26: anewarray 101	com/tencent/feedback/proguard/x
    //   29: invokeinterface 105 2 0
    //   34: checkcast 107	[Lcom/tencent/feedback/proguard/x;
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: aconst_null
    //   43: astore_1
    //   44: goto -6 -> 38
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	t
    //   37	7	1	arrayOfX	x[]
    //   47	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	38	47	finally
  }
  
  public final void a(int paramInt)
  {
    try
    {
      d = paramInt;
      com.tencent.feedback.common.e.b("rqdp{  step }%d", new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public final void a(int paramInt, com.tencent.feedback.upload.e paramE)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 59	com/tencent/feedback/proguard/t:h	Landroid/util/SparseArray;
    //   6: ifnull +15 -> 21
    //   9: aload_2
    //   10: ifnonnull +14 -> 24
    //   13: aload_0
    //   14: getfield 59	com/tencent/feedback/proguard/t:h	Landroid/util/SparseArray;
    //   17: iload_1
    //   18: invokevirtual 124	android/util/SparseArray:remove	(I)V
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: aload_0
    //   25: getfield 59	com/tencent/feedback/proguard/t:h	Landroid/util/SparseArray;
    //   28: iload_1
    //   29: aload_2
    //   30: invokevirtual 128	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   33: aload_2
    //   34: aload_0
    //   35: invokevirtual 131	com/tencent/feedback/proguard/t:c	()Lcom/tencent/feedback/upload/d;
    //   38: invokeinterface 134 2 0
    //   43: pop
    //   44: goto -23 -> 21
    //   47: astore_2
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_2
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	t
    //   0	52	1	paramInt	int
    //   0	52	2	paramE	com.tencent.feedback.upload.e
    // Exception table:
    //   from	to	target	type
    //   2	9	47	finally
    //   13	21	47	finally
    //   24	44	47	finally
  }
  
  public final void a(final s paramS)
  {
    if (paramS == null) {}
    for (;;)
    {
      return;
      try
      {
        if (g == null) {
          g = new ArrayList();
        }
        if (g.contains(paramS)) {
          continue;
        }
        g.add(paramS);
        final int j = e();
        if (g())
        {
          com.tencent.feedback.common.e.b("rqdp{  add listener should notify app first run! }%s", new Object[] { paramS.toString() });
          b.b().a(new Runnable()
          {
            public final void run()
            {
              paramS.f();
            }
          });
        }
        if (j < 2) {
          continue;
        }
        com.tencent.feedback.common.e.b("rqdp{  add listener should notify app start query!} %s", new Object[] { paramS.toString() });
        b.b().a(new Runnable()
        {
          public final void run()
          {
            paramS.d();
            if (j >= 3)
            {
              com.tencent.feedback.common.e.b("rqdp{  query finished should notify}", new Object[0]);
              paramS.e();
            }
          }
        });
      }
      finally {}
    }
  }
  
  public final void a(w paramW)
  {
    x[] arrayOfX = h();
    if (arrayOfX != null)
    {
      int k = arrayOfX.length;
      int j = 0;
      for (;;)
      {
        if (j < k)
        {
          x localX = arrayOfX[j];
          try
          {
            localX.a(paramW);
            j += 1;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              if (!com.tencent.feedback.common.e.a(localThrowable)) {
                localThrowable.printStackTrace();
              }
              com.tencent.feedback.common.e.d("rqdp{  com strategy changed error }%s", new Object[] { localThrowable.toString() });
            }
          }
        }
      }
    }
  }
  
  public final void a(x paramX)
  {
    if (paramX != null) {}
    try
    {
      if ((i != null) && (!i.contains(paramX))) {
        i.add(paramX);
      }
      return;
    }
    finally
    {
      paramX = finally;
      throw paramX;
    }
  }
  
  protected final void a(boolean paramBoolean)
  {
    try
    {
      c = true;
      com.tencent.feedback.common.e.b("rqdp{  isFirst }%b", new Object[] { Boolean.valueOf(true) });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final w b()
  {
    try
    {
      w localW = b;
      return localW;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public final void b(s paramS)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 54	com/tencent/feedback/proguard/t:g	Ljava/util/List;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +7 -> 15
    //   11: aload_1
    //   12: ifnonnull +6 -> 18
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: aload_0
    //   19: getfield 54	com/tencent/feedback/proguard/t:g	Ljava/util/List;
    //   22: aload_1
    //   23: invokeinterface 140 2 0
    //   28: ifeq -13 -> 15
    //   31: aload_0
    //   32: getfield 54	com/tencent/feedback/proguard/t:g	Ljava/util/List;
    //   35: aload_1
    //   36: invokeinterface 191 2 0
    //   41: pop
    //   42: goto -27 -> 15
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	t
    //   0	50	1	paramS	s
    //   6	2	2	localList	List
    // Exception table:
    //   from	to	target	type
    //   2	7	45	finally
    //   18	42	45	finally
  }
  
  public final d c()
  {
    try
    {
      d localD = e;
      return localD;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  public final s[] d()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 54	com/tencent/feedback/proguard/t:g	Ljava/util/List;
    //   6: ifnull +36 -> 42
    //   9: aload_0
    //   10: getfield 54	com/tencent/feedback/proguard/t:g	Ljava/util/List;
    //   13: invokeinterface 99 1 0
    //   18: ifle +24 -> 42
    //   21: aload_0
    //   22: getfield 54	com/tencent/feedback/proguard/t:g	Ljava/util/List;
    //   25: iconst_0
    //   26: anewarray 194	com/tencent/feedback/proguard/s
    //   29: invokeinterface 105 2 0
    //   34: checkcast 196	[Lcom/tencent/feedback/proguard/s;
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: aconst_null
    //   43: astore_1
    //   44: goto -6 -> 38
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	t
    //   37	7	1	arrayOfS	s[]
    //   47	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	38	47	finally
  }
  
  public final int e()
  {
    try
    {
      int j = d;
      return j;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}