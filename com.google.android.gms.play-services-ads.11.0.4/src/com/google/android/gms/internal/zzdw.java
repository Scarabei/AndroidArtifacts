package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdw extends zzec {
   public zzdw(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 51);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      zzax var1 = this.zzro;
      synchronized(this.zzro) {
         String var2 = (String)this.zzrx.invoke((Object)null);
         zzda var3 = new zzda(var2);
         this.zzro.zzbJ = var3.zzqB;
         this.zzro.zzbK = var3.zzqC;
      }
   }
}
