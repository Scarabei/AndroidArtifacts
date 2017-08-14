package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzar extends zzp {
   private final zzv zzaD;

   public zzar(int var1, String var2, zzv var3, zzu var4) {
      super(var1, var2, var4);
      this.zzaD = var3;
   }

   protected final zzt zza(zzn var1) {
      String var2;
      try {
         var2 = new String(var1.data, zzam.zza(var1.zzy));
      } catch (UnsupportedEncodingException var3) {
         var2 = new String(var1.data);
      }

      return zzt.zza(var2, zzam.zzb(var1));
   }

   // $FF: synthetic method
   protected final void zza(Object var1) {
      String var2 = (String)var1;
      this.zzaD.zzb(var2);
   }
}
