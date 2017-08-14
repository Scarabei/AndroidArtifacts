package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzalk;
import com.google.android.gms.internal.zzalx;
import com.google.android.gms.internal.zzami;
import com.google.android.gms.internal.zzamm;
import com.google.android.gms.internal.zzanx;
import com.google.android.gms.internal.zzaos;
import java.util.HashMap;
import java.util.Map;

final class zzp implements Runnable {
   // $FF: synthetic field
   private Map zzaeu;
   // $FF: synthetic field
   private boolean zzaev;
   // $FF: synthetic field
   private String zzaew;
   // $FF: synthetic field
   private long zzaex;
   // $FF: synthetic field
   private boolean zzaey;
   // $FF: synthetic field
   private boolean zzaez;
   // $FF: synthetic field
   private String zzaeA;
   // $FF: synthetic field
   private Tracker zzaeB;

   zzp(Tracker var1, Map var2, boolean var3, String var4, long var5, boolean var7, boolean var8, String var9) {
      this.zzaeB = var1;
      this.zzaeu = var2;
      this.zzaev = var3;
      this.zzaew = var4;
      this.zzaex = var5;
      this.zzaey = var7;
      this.zzaez = var8;
      this.zzaeA = var9;
      super();
   }

   public final void run() {
      if (Tracker.zza(this.zzaeB).zzjE()) {
         this.zzaeu.put("sc", "start");
      }

      Map var10000 = this.zzaeu;
      GoogleAnalytics var12 = this.zzaeB.zzku();
      zzbo.zzcG("getClientId can not be called from the main thread");
      zzaos.zzc(var10000, "cid", var12.zzji().zzkJ().zzli());
      String var1;
      double var2;
      if ((var1 = (String)this.zzaeu.get("sf")) != null && zzaos.zza(var2 = zzaos.zza(var1, 100.0D), (String)this.zzaeu.get("cid"))) {
         this.zzaeB.zzb("Sampling enabled. Hit sampled out. sample rate", var2);
      } else {
         zzalx var13 = Tracker.zzb(this.zzaeB);
         if (this.zzaev) {
            zzaos.zzb(this.zzaeu, "ate", var13.zzjZ());
            zzaos.zzb(this.zzaeu, "adid", var13.zzkg());
         } else {
            this.zzaeu.remove("ate");
            this.zzaeu.remove("adid");
         }

         zzalk var3 = Tracker.zzc(this.zzaeB).zzkW();
         zzaos.zzb(this.zzaeu, "an", var3.zzjG());
         zzaos.zzb(this.zzaeu, "av", var3.zzjH());
         zzaos.zzb(this.zzaeu, "aid", var3.zzhl());
         zzaos.zzb(this.zzaeu, "aiid", var3.zzjI());
         this.zzaeu.put("v", "1");
         this.zzaeu.put("_v", zzami.zzafL);
         zzaos.zzb(this.zzaeu, "ul", Tracker.zzd(this.zzaeB).zzlA().getLanguage());
         zzaos.zzb(this.zzaeu, "sr", Tracker.zze(this.zzaeB).zzlB());
         if (!this.zzaew.equals("transaction") && !this.zzaew.equals("item") && !Tracker.zzf(this.zzaeB).zzlL()) {
            Tracker.zzg(this.zzaeB).zze(this.zzaeu, "Too many hits sent too quickly, rate limiting invoked");
         } else {
            long var4;
            if ((var4 = zzaos.zzbC((String)this.zzaeu.get("ht"))) == 0L) {
               var4 = this.zzaex;
            }

            if (this.zzaey) {
               zzanx var14 = new zzanx(this.zzaeB, this.zzaeu, var4, this.zzaez);
               Tracker.zzh(this.zzaeB).zzc("Dry run enabled. Would have sent hit", var14);
            } else {
               String var6 = (String)this.zzaeu.get("cid");
               HashMap var7;
               zzaos.zza(var7 = new HashMap(), "uid", this.zzaeu);
               zzaos.zza(var7, "an", this.zzaeu);
               zzaos.zza(var7, "aid", this.zzaeu);
               zzaos.zza(var7, "av", this.zzaeu);
               zzaos.zza(var7, "aiid", this.zzaeu);
               zzamm var8 = new zzamm(0L, var6, this.zzaeA, !TextUtils.isEmpty((CharSequence)this.zzaeu.get("adid")), 0L, var7);
               long var9 = Tracker.zzi(this.zzaeB).zza(var8);
               this.zzaeu.put("_s", String.valueOf(var9));
               zzanx var11 = new zzanx(this.zzaeB, this.zzaeu, var4, this.zzaez);
               Tracker.zzj(this.zzaeB).zza(var11);
            }
         }
      }
   }
}
