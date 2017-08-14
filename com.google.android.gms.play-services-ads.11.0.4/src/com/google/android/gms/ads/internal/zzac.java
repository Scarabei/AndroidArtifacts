package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzafj;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzac {
   private final Object mLock = new Object();
   private Context mContext;

   public final void zza(Context var1, zzaje var2, String var3, @Nullable Runnable var4) {
      this.zza(var1, var2, true, (zzafj)null, var3, (String)null, var4);
   }

   final void zza(Context var1, zzaje var2, boolean var3, @Nullable zzafj var4, String var5, @Nullable String var6, @Nullable Runnable var7) {
      boolean var10000;
      if (var4 == null) {
         var10000 = true;
      } else {
         long var11 = var4.zzhi();
         long var14 = zzbs.zzbF().currentTimeMillis() - var11;
         zzme var13 = zzmo.zzFQ;
         var10000 = var14 > ((Long)zzbs.zzbL().zzd(var13)).longValue() || !var4.zzhj();
      }

      if (var10000) {
         if (var1 == null) {
            zzafr.zzaT("Context not provided to fetch application settings");
         } else if (TextUtils.isEmpty(var5) && TextUtils.isEmpty(var6)) {
            zzafr.zzaT("App settings could not be fetched. Required parameters missing");
         } else {
            this.mContext = var1;
            com.google.android.gms.ads.internal.js.zzl var8 = zzbs.zzbz().zze(var1, var2);
            zzad var9 = new zzad(this, var7);
            zzagz.zzZr.post(new zzae(this, var8, var9, var5, var6, var3, var1));
         }
      }
   }

   // $FF: synthetic method
   static Object zza(zzac var0) {
      return var0.mLock;
   }

   // $FF: synthetic method
   static Context zzb(zzac var0) {
      return var0.mContext;
   }
}
