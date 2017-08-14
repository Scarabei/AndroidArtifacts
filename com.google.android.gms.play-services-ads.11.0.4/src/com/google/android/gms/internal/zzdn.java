package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdn extends zzec {
   private static volatile Long zzbF = null;
   private static final Object zzrl = new Object();

   public zzdn(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 44);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      if (zzbF == null) {
         Object var1 = zzrl;
         synchronized(zzrl) {
            if (zzbF == null) {
               zzbF = (Long)this.zzrx.invoke((Object)null);
            }
         }
      }

      zzax var6 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzbF = zzbF;
      }
   }
}
