package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ee;

final class zzad implements zzdi {
   // $FF: synthetic field
   private zzy zzbDX;

   private zzad(zzy var1) {
      this.zzbDX = var1;
      super();
   }

   public final void zzbw(int var1) {
      if (!zzy.zze(this.zzbDX)) {
         zzy.zza(this.zzbDX, 0L);
      }

   }

   // $FF: synthetic method
   public final void onSuccess(Object var1) {
      ee var3 = (ee)var1;
      com.google.android.gms.internal.zzbq var5;
      if (var3.zzbLH != null) {
         var5 = var3.zzbLH;
      } else {
         com.google.android.gms.internal.zzbn var4 = var3.zzlB;
         (var5 = new com.google.android.gms.internal.zzbq()).zzlB = var4;
         var5.zzlA = null;
         var5.zzlC = var4.version;
      }

      zzy.zza(this.zzbDX, var5, var3.zzbLG, true);
   }

   // $FF: synthetic method
   zzad(zzy var1, zzz var2) {
      this(var1);
   }
}
