package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.zzn;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzacs extends zzd implements zzadt {
   private static zzacs zzWr;
   private static final zzup zzWs = new zzup();
   private final Map zzWt = new HashMap();
   private boolean zzWu;
   private boolean zzuj;

   public static zzacs zzgO() {
      return zzWr;
   }

   public zzacs(Context var1, zzv var2, zziv var3, zzuq var4, zzaje var5) {
      super(var1, var3, (String)null, var4, var5, var2);
      zzWr = this;
   }

   public final void zza(zzadj var1) {
      zzbo.zzcz("loadAd must be called on the main UI thread.");
      if (TextUtils.isEmpty(var1.zzvR)) {
         zzafr.zzaT("Invalid ad unit id. Aborting.");
         zzagz.zzZr.post(new zzact(this));
      } else {
         this.zzWu = false;
         this.zzsP.zzvR = var1.zzvR;
         super.zza(var1.zzSz);
      }
   }

   public final void zza(zzafg var1, zznb var2) {
      if (var1.errorCode != -2) {
         zzagz.zzZr.post(new zzacu(this, var1));
      } else {
         this.zzsP.zzvZ = var1;
         if (var1.zzXN == null) {
            this.zzsP.zzvZ = zzc(var1);
         }

         this.zzsP.zzwt = 0;
         com.google.android.gms.ads.internal.zzbt var10000 = this.zzsP;
         com.google.android.gms.ads.internal.zzbs.zzby();
         zzafg var4 = this.zzsP.zzvZ;
         Context var3 = this.zzsP.zzqD;
         zzadw var6 = new zzadw(var3, var4, this);
         String var10002 = String.valueOf(var6.getClass().getName());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "AdRenderer: ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("AdRenderer: ");
         }

         zzafr.zzaC(var10001);
         var6.zzgp();
         var10000.zzvW = var6;
      }
   }

   @Nullable
   public final zzadz zzav(String var1) {
      zzadz var2;
      if ((var2 = (zzadz)this.zzWt.get(var1)) == null) {
         try {
            Object var3 = this.zzsX;
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(var1)) {
               var3 = zzWs;
            }

            var2 = new zzadz(((zzuq)var3).zzah(var1), this);
            this.zzWt.put(var1, var2);
         } catch (Exception var4) {
            String var10001 = String.valueOf(var1);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Fail to instantiate adapter ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Fail to instantiate adapter ");
            }

            zzafr.zzc(var10000, var4);
         }
      }

      return var2;
   }

   public final boolean zza(zzaff var1, zzaff var2) {
      return true;
   }

   protected final boolean zza(zzir var1, zzaff var2, boolean var3) {
      return false;
   }

   public final void pause() {
      zzbo.zzcz("pause must be called on the main UI thread.");
      Iterator var1 = this.zzWt.keySet().iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();

         try {
            zzadz var3;
            if ((var3 = (zzadz)this.zzWt.get(var2)) != null && var3.zzgW() != null) {
               var3.zzgW().pause();
            }
         } catch (RemoteException var4) {
            String var10001 = String.valueOf(var2);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Fail to pause adapter: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Fail to pause adapter: ");
            }

            zzafr.zzaT(var10000);
         }
      }

   }

   public final void resume() {
      zzbo.zzcz("resume must be called on the main UI thread.");
      Iterator var1 = this.zzWt.keySet().iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();

         try {
            zzadz var3;
            if ((var3 = (zzadz)this.zzWt.get(var2)) != null && var3.zzgW() != null) {
               var3.zzgW().resume();
            }
         } catch (RemoteException var4) {
            String var10001 = String.valueOf(var2);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Fail to resume adapter: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Fail to resume adapter: ");
            }

            zzafr.zzaT(var10000);
         }
      }

   }

   public final void destroy() {
      zzbo.zzcz("destroy must be called on the main UI thread.");
      Iterator var1 = this.zzWt.keySet().iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();

         try {
            zzadz var3;
            if ((var3 = (zzadz)this.zzWt.get(var2)) != null && var3.zzgW() != null) {
               var3.zzgW().destroy();
            }
         } catch (RemoteException var4) {
            String var10001 = String.valueOf(var2);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Fail to destroy adapter: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Fail to destroy adapter: ");
            }

            zzafr.zzaT(var10000);
         }
      }

   }

   public final void setImmersiveMode(boolean var1) {
      zzbo.zzcz("setImmersiveMode must be called on the main UI thread.");
      this.zzuj = var1;
   }

   public final void zzgP() {
      zzbo.zzcz("showAd must be called on the main UI thread.");
      if (!this.isLoaded()) {
         zzafr.zzaT("The reward video has not loaded.");
      } else {
         this.zzWu = true;
         zzadz var1;
         if ((var1 = this.zzav(this.zzsP.zzvY.zzMI)) != null && var1.zzgW() != null) {
            try {
               var1.zzgW().setImmersiveMode(this.zzuj);
               var1.zzgW().showVideo();
               return;
            } catch (RemoteException var3) {
               zzafr.zzc("Could not call showVideo.", var3);
            }
         }

      }
   }

   public final boolean isLoaded() {
      zzbo.zzcz("isLoaded must be called on the main UI thread.");
      return this.zzsP.zzvV == null && this.zzsP.zzvW == null && this.zzsP.zzvY != null && !this.zzWu;
   }

   public final void onRewardedVideoAdOpened() {
      this.zza(this.zzsP.zzvY, false);
      this.zzar();
   }

   public final void onRewardedVideoStarted() {
      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzMG != null) {
         com.google.android.gms.ads.internal.zzbs.zzbS();
         com.google.android.gms.internal.zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY, this.zzsP.zzvR, false, this.zzsP.zzvY.zzMG.zzLQ);
      }

      this.zzav();
   }

   public final void onRewardedVideoAdClosed() {
      this.zzap();
   }

   protected final void zzap() {
      this.zzsP.zzvY = null;
      super.zzap();
   }

   public final void zzc(@Nullable zzaee var1) {
      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzMG != null) {
         com.google.android.gms.ads.internal.zzbs.zzbS();
         com.google.android.gms.internal.zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY, this.zzsP.zzvR, false, this.zzsP.zzvY.zzMG.zzLR);
      }

      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzXN != null && !TextUtils.isEmpty(this.zzsP.zzvY.zzXN.zzMh)) {
         var1 = new zzaee(this.zzsP.zzvY.zzXN.zzMh, this.zzsP.zzvY.zzXN.zzMi);
      }

      this.zza(var1);
   }

   public final void zzgQ() {
      this.onAdClicked();
   }

   public final void onRewardedVideoAdLeftApplication() {
      this.zzaq();
   }

   public final void onContextChanged(@NonNull Context var1) {
      Iterator var2 = this.zzWt.values().iterator();

      while(var2.hasNext()) {
         zzadz var3 = (zzadz)var2.next();

         try {
            var3.zzgW().zzk(zzn.zzw(var1));
         } catch (RemoteException var5) {
            zzafr.zzb("Unable to call Adapter.onContextChanged.", var5);
         }
      }

   }

   private static zzafg zzc(zzafg var0) {
      zzafr.v("Creating mediation ad response for non-mediated rewarded ad.");

      String var1;
      String var2;
      try {
         var1 = zzabt.zzb(var0.zzXY).toString();
         JSONObject var3;
         (var3 = new JSONObject()).put("pubid", var0.zzUj.zzvR);
         var2 = var3.toString();
      } catch (JSONException var6) {
         zzafr.zzb("Unable to generate ad state for non-mediated rewarded video.", var6);
         return new zzafg(var0.zzUj, var0.zzXY, (zzub)null, var0.zzvX, 0, var0.zzXR, var0.zzXS, var0.zzXL, var0.zzXX);
      }

      zzua var7 = new zzua(var1, (String)null, Arrays.asList("com.google.ads.mediation.admob.AdMobAdapter"), (String)null, (String)null, Collections.emptyList(), Collections.emptyList(), var2, (String)null, Collections.emptyList(), Collections.emptyList(), (String)null, (String)null, (String)null, (List)null, (String)null, Collections.emptyList(), (String)null);
      List var10002 = Arrays.asList(var7);
      zzme var5 = zzmo.zzEL;
      zzub var4 = new zzub(var10002, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1L, 0, 1, (String)null, 0, -1, -1L, false);
      return new zzafg(var0.zzUj, var0.zzXY, var4, var0.zzvX, var0.errorCode, var0.zzXR, var0.zzXS, var0.zzXL, var0.zzXX);
   }

   // $FF: synthetic method
   static void zza(zzacs var0, int var1) {
      var0.zze(1);
   }
}
