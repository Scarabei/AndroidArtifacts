package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdk extends zzec {
   private static volatile String zzrk = null;
   private static final Object zzrl = new Object();

   public zzdk(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 29);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      this.zzro.zzbt = "E";
      if (zzrk == null) {
         Object var1 = zzrl;
         synchronized(zzrl) {
            if (zzrk == null) {
               zzrk = (String)this.zzrx.invoke((Object)null, this.zzpJ.getContext());
            }
         }
      }

      zzax var6 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzbt = zzbt.zza(zzrk.getBytes(), true);
      }
   }
}
