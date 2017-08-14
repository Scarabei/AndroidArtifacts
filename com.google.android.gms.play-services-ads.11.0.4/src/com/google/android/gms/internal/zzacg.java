package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzacg extends zzack {
   private final Object mLock = new Object();
   @Nullable
   private SharedPreferences zzWi;

   public final void zza(Context var1, zzabu var2, zzaje var3) {
      Context var7 = var1;
      zzacg var6 = this;
      synchronized(this.mLock) {
         if (var6.zzWi == null) {
            var6.zzWi = var7.getSharedPreferences("google_ads_flags_meta", 0);
         }
      }

      long var4 = this.zzWi.getLong("js_last_update", 0L);
      long var10000 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis() - var4;
      zzme var15 = zzmo.zzFm;
      if (var10000 < ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var15)).longValue()) {
         zze(var2);
      } else {
         zzach var9 = new zzach(this, var1, var2);
         zzaje var8 = var3;
         JSONObject var10 = new JSONObject();

         try {
            var10.put("js", var8.zzaP);
            zzme var12 = zzmo.zzFn;
            var10.put("mf", com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var12));
            var10.put("cl", "162978962");
            var10.put("rapid_rc", "dev");
            var10.put("rapid_rollup", "HEAD");
         } catch (JSONException var13) {
            zzafr.zzb("Unable to populate SDK Core Constants parameters.", var13);
            zze(var2);
            return;
         }

         var2.zzUN.zza(new zzaci(this, var9, var10, var2), new zzacj(this, var2));
      }
   }

   private final void zzgN() {
      this.zzWi.edit().putLong("js_last_update", com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis()).apply();
   }

   // $FF: synthetic method
   static void zza(zzacg var0) {
      var0.zzgN();
   }
}
