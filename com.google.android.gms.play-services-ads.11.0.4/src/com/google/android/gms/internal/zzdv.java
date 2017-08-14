package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdv extends zzec {
   private final StackTraceElement[] zzrs;

   public zzdv(zzdb var1, String var2, String var3, zzax var4, int var5, int var6, StackTraceElement[] var7) {
      super(var1, var2, var3, var4, var5, 45);
      this.zzrs = var7;
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      if (this.zzrs != null) {
         String var1 = (String)this.zzrx.invoke((Object)null, this.zzrs);
         zzcz var2 = new zzcz(var1);
         zzax var3 = this.zzro;
         synchronized(this.zzro) {
            this.zzro.zzbG = var2.zzqy;
            if (var2.zzqz.booleanValue()) {
               this.zzro.zzbO = var2.zzqA.booleanValue() ? 0 : 1;
            }

         }
      }
   }
}
