package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzu {
   public static void zza(Context var0, AdOverlayInfoParcel var1, boolean var2) {
      if (var1.zzPm == 4 && var1.zzPf == null) {
         if (var1.zzPe != null) {
            var1.zzPe.onAdClicked();
         }

         zzbs.zzbw();
         zza.zza(var0, var1.zzPd, var1.zzPl);
      } else {
         Intent var3;
         (var3 = new Intent()).setClassName(var0, "com.google.android.gms.ads.AdActivity");
         var3.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", var1.zzvT.zzaaQ);
         var3.putExtra("shouldCallOnOverlayOpened", var2);
         AdOverlayInfoParcel.zza(var3, var1);
         if (!com.google.android.gms.common.util.zzq.zzse()) {
            var3.addFlags(524288);
         }

         if (!(var0 instanceof Activity)) {
            var3.addFlags(268435456);
         }

         zzbs.zzbz();
         zzagz.zzb(var0, var3);
      }
   }
}
