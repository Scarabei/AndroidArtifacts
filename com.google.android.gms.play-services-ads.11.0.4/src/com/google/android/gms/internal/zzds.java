package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzds extends zzec {
   private long zzrq = -1L;

   public zzds(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 12);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      zzme var1 = zzmo.zzER;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var1)).booleanValue()) {
         zzds var5 = this;
         this.zzro.zzbi = -1L;
         if (this.zzrq == -1L) {
            this.zzrq = ((Long)this.zzrx.invoke((Object)null, this.zzpJ.getContext())).longValue();
         }

         zzax var2 = this.zzro;
         synchronized(this.zzro) {
            var5.zzro.zzbi = var5.zzrq;
         }
      } else {
         this.zzro.zzbi = -1L;
         this.zzro.zzbi = (Long)this.zzrx.invoke((Object)null, this.zzpJ.getContext());
      }
   }
}
