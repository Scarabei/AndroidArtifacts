package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdy extends zzec {
   public zzdy(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 48);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      this.zzro.zzbH = Integer.valueOf(2);
      boolean var1 = ((Boolean)this.zzrx.invoke((Object)null, this.zzpJ.getApplicationContext())).booleanValue();
      zzax var2 = this.zzro;
      synchronized(this.zzro) {
         if (var1) {
            this.zzro.zzbH = Integer.valueOf(1);
         } else {
            this.zzro.zzbH = Integer.valueOf(0);
         }

      }
   }
}
