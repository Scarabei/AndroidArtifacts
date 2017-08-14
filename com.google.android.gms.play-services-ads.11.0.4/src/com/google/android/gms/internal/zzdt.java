package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdt extends zzec {
   private static volatile String zzaY = null;
   private static final Object zzrl = new Object();

   public zzdt(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 1);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      this.zzro.zzaY = "E";
      if (zzaY == null) {
         Object var1 = zzrl;
         synchronized(zzrl) {
            if (zzaY == null) {
               zzaY = (String)this.zzrx.invoke((Object)null);
            }
         }
      }

      zzax var6 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzaY = zzaY;
      }
   }
}
