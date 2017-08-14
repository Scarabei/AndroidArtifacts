package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdr extends zzec {
   private static volatile Long zzrp = null;
   private static final Object zzrl = new Object();

   public zzdr(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 22);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      if (zzrp == null) {
         Object var1 = zzrl;
         synchronized(zzrl) {
            if (zzrp == null) {
               zzrp = (Long)this.zzrx.invoke((Object)null);
            }
         }
      }

      zzax var6 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzbr = zzrp;
      }
   }
}
