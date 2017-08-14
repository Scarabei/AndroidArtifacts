package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public final class zzdi extends zzec {
   public zzdi(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 3);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      zzax var1 = this.zzro;
      synchronized(this.zzro) {
         this.zzro.zzaZ = -1L;
         this.zzro.zzaZ = (Long)this.zzrx.invoke((Object)null, this.zzpJ.getContext());
      }
   }
}
