package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdx extends zzec {
   private static volatile Long zzrt = null;
   private static final Object zzrl = new Object();

   public zzdx(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 33);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      if (zzrt == null) {
         Object var1 = zzrl;
         synchronized(zzrl) {
            if (zzrt == null) {
               zzrt = (Long)this.zzrx.invoke((Object)null);
            }
         }
      }

      zzax var6 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzbw = zzrt;
      }
   }
}
