package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdm extends zzec {
   public zzdm(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 5);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      this.zzro.zzbb = -1L;
      this.zzro.zzbc = -1L;
      int[] var1 = (int[])this.zzrx.invoke((Object)null, this.zzpJ.getContext());
      zzax var2 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzbb = (long)var1[0];
         this.zzro.zzbc = (long)var1[1];
      }
   }
}
