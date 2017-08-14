package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdo extends zzec {
   private long startTime;

   public zzdo(zzdb var1, String var2, String var3, zzax var4, long var5, int var7, int var8) {
      super(var1, var2, var3, var4, var7, 25);
      this.startTime = var5;
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      long var1 = ((Long)this.zzrx.invoke((Object)null)).longValue();
      zzax var3 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzbY = var1;
         if (this.startTime != 0L) {
            this.zzro.zzbn = var1 - this.startTime;
            this.zzro.zzbs = this.startTime;
         }

      }
   }
}
