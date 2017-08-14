package com.google.android.gms.ads.internal.js;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbl;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzakm;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzqk;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzrm;
import com.google.android.gms.internal.zzwv;
import com.google.android.gms.internal.zzzn;
import org.json.JSONObject;

@zzzn
public final class zze implements zza {
   private final zzaka zzJH;

   public zze(Context var1, zzaje var2, @Nullable zzcu var3, com.google.android.gms.ads.internal.zzv var4) throws zzakm {
      this.zzJH = zzbs.zzbA().zza(var1, new zziv(), false, false, var3, var2, (zznb)null, (zzbl)null, var4, zzig.zzde());
      this.zzJH.getWebView().setWillNotDraw(true);
   }

   private static void runOnUiThread(Runnable var0) {
      zzji.zzds();
      if (zzaiy.zzil()) {
         var0.run();
      } else {
         zzagz.zzZr.post(var0);
      }
   }

   public final void zza(String var1, JSONObject var2) {
      runOnUiThread(new zzf(this, var1, var2));
   }

   public final void zzi(String var1, String var2) {
      runOnUiThread(new zzg(this, var1, var2));
   }

   public final void zzac(String var1) {
      String var2 = String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", var1);
      runOnUiThread(new zzh(this, var2));
   }

   public final void zzae(String var1) {
      runOnUiThread(new zzi(this, var1));
   }

   public final void zzad(String var1) {
      runOnUiThread(new zzj(this, var1));
   }

   public final void zza(String var1, zzrd var2) {
      this.zzJH.zziw().zza(var1, var2);
   }

   public final void zzb(String var1, zzrd var2) {
      this.zzJH.zziw().zzb(var1, var2);
   }

   public final void zza(zzb var1) {
      this.zzJH.zziw().zza((zzakf)(new zzk(this, var1)));
   }

   public final void zzb(String var1, JSONObject var2) {
      this.zzJH.zzb(var1, var2);
   }

   public final void zza(zzim var1, com.google.android.gms.ads.internal.overlay.zzw var2, zzqk var3, com.google.android.gms.ads.internal.overlay.zzag var4, boolean var5, zzrm var6, com.google.android.gms.ads.internal.zzw var7, zzwv var8) {
      this.zzJH.zziw().zza(var1, var2, var3, var4, false, (zzrm)null, new com.google.android.gms.ads.internal.zzw((zzaet)null), (zzwv)null, (zzaet)null);
   }

   public final zzaj zzeY() {
      return new zzak(this);
   }

   public final void destroy() {
      this.zzJH.destroy();
   }

   // $FF: synthetic method
   static zzaka zza(zze var0) {
      return var0.zzJH;
   }
}
