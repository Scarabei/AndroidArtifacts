package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbgw implements ThreadFactory {
   private final String zzaKe;
   private final int mPriority;
   private final AtomicInteger zzaKf;
   private final ThreadFactory zzaKg;

   public zzbgw(String var1) {
      this(var1, 0);
   }

   private zzbgw(String var1, int var2) {
      this.zzaKf = new AtomicInteger();
      this.zzaKg = Executors.defaultThreadFactory();
      this.zzaKe = (String)zzbo.zzb(var1, "Name must not be null");
      this.mPriority = 0;
   }

   public final Thread newThread(Runnable var1) {
      Thread var2;
      Thread var10000 = var2 = this.zzaKg.newThread(new zzbgx(var1, 0));
      String var3 = this.zzaKe;
      int var4 = this.zzaKf.getAndIncrement();
      var10000.setName((new StringBuilder(13 + String.valueOf(var3).length())).append(var3).append("[").append(var4).append("]").toString());
      return var2;
   }
}
