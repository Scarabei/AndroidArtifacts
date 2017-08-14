package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class zzdu extends zzec {
   private List zzrr = null;

   public zzdu(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 31);
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      this.zzro.zzbu = -1L;
      this.zzro.zzbv = -1L;
      if (this.zzrr == null) {
         this.zzrr = (List)this.zzrx.invoke((Object)null, this.zzpJ.getContext());
      }

      if (this.zzrr != null && this.zzrr.size() == 2) {
         zzax var1 = this.zzro;
         synchronized(this.zzro) {
            this.zzro.zzbu = ((Long)this.zzrr.get(0)).longValue();
            this.zzro.zzbv = ((Long)this.zzrr.get(1)).longValue();
         }
      }
   }
}
