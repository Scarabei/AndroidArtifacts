package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.android.gms.internal.zzaaf;
import com.google.android.gms.internal.zzaaz;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafh;
import com.google.android.gms.internal.zzafj;
import com.google.android.gms.internal.zzafp;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagt;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzahe;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzajm;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzbha;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzij;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzky;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzon;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zztp;
import com.google.android.gms.internal.zzuc;
import com.google.android.gms.internal.zzuj;
import com.google.android.gms.internal.zzuq;
import com.google.android.gms.internal.zzzn;
import com.google.android.gms.internal.zzzq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public abstract class zzd extends zza implements com.google.android.gms.ads.internal.overlay.zzw, zzbl, zzuc {
   protected final zzuq zzsX;
   private transient boolean zzsY;

   public zzd(Context var1, zziv var2, String var3, zzuq var4, zzaje var5, zzv var6) {
      this(new zzbt(var1, var2, var3, var5), var4, (zzbi)null, var6);
   }

   private zzd(zzbt var1, zzuq var2, @Nullable zzbi var3, zzv var4) {
      super(var1, (zzbi)null, var4);
      this.zzsX = var2;
      this.zzsY = false;
   }

   public boolean zza(zzir var1, zznb var2) {
      if (!this.zzaz()) {
         return false;
      } else {
         zzbs.zzbz();
         Context var7 = this.zzsP.zzqD;
         zzgz var8;
         Bundle var3 = (var8 = zzbs.zzbD().zzA(var7)) == null ? null : zzagz.zza(var8);
         this.zzsO.cancel();
         this.zzsP.zzwt = 0;
         zzafj var4 = null;
         zzme var6 = zzmo.zzFO;
         if (((Boolean)zzbs.zzbL().zzd(var6)).booleanValue()) {
            var4 = zzbs.zzbD().zzhD();
            zzac var10000 = zzbs.zzbV();
            String var9 = this.zzsP.zzvR;
            zzaje var14 = this.zzsP.zzvT;
            var7 = this.zzsP.zzqD;
            zzac var12 = var10000;
            String var11 = var4 != null ? var4.zzhl() : null;
            var12.zza(var7, var14, false, var4, var11, var9, (Runnable)null);
         }

         zzaaf var5 = this.zza(var1, var3, var4);
         var2.zzh("seq_num", var5.zzSC);
         var2.zzh("request_id", var5.zzSM);
         var2.zzh("session_id", var5.zzSD);
         if (var5.zzSA != null) {
            var2.zzh("app_version", String.valueOf(var5.zzSA.versionCode));
         }

         zzbt var17 = this.zzsP;
         zzbs.zzbv();
         zzij var15 = this.zzsS.zztp;
         Context var13 = this.zzsP.zzqD;
         Object var16;
         if (var5.zzSz.extras.getBundle("sdk_less_server_data") != null) {
            var16 = new zzaaz(var13, var5, this, var15);
         } else {
            var16 = new zzzq(var13, var5, this, var15);
         }

         ((zzafp)var16).zzhL();
         var17.zzvV = (zzafp)var16;
         return true;
      }
   }

   public final void zzb(zzaff var1) {
      super.zzb(var1);
      if (var1.zzMG != null) {
         zzafr.zzaC("Disable the debug gesture detector on the mediation ad frame.");
         if (this.zzsP.zzvU != null) {
            this.zzsP.zzvU.zzci();
         }

         zzafr.zzaC("Pinging network fill URLs.");
         zzbs.zzbS();
         zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, var1, this.zzsP.zzvR, false, var1.zzMG.zzLO);
         if (var1.zzXN != null && var1.zzXN.zzMd != null && var1.zzXN.zzMd.size() > 0) {
            zzafr.zzaC("Pinging urls remotely");
            zzbs.zzbz().zza(this.zzsP.zzqD, var1.zzXN.zzMd);
         }
      } else {
         zzafr.zzaC("Enable the debug gesture detector on the admob ad frame.");
         if (this.zzsP.zzvU != null) {
            this.zzsP.zzvU.zzch();
         }
      }

      if (var1.errorCode == 3 && var1.zzXN != null && var1.zzXN.zzMc != null) {
         zzafr.zzaC("Pinging no fill URLs.");
         zzbs.zzbS();
         zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, var1, this.zzsP.zzvR, false, var1.zzXN.zzMc);
      }

   }

   protected boolean zza(@Nullable zzaff var1, zzaff var2) {
      if (var1 != null && var1.zzMJ != null) {
         var1.zzMJ.zza((zzuc)null);
      }

      if (var2.zzMJ != null) {
         var2.zzMJ.zza((zzuc)this);
      }

      int var3 = 0;
      int var4 = 0;
      if (var2.zzXN != null) {
         var3 = var2.zzXN.zzMn;
         var4 = var2.zzXN.zzMo;
      }

      this.zzsP.zzwr.zzg(var3, var4);
      return true;
   }

   public void onAdClicked() {
      if (this.zzsP.zzvY == null) {
         zzafr.zzaT("Ad state was null when trying to ping click URLs.");
      } else {
         if (this.zzsP.zzvY.zzXN != null && this.zzsP.zzvY.zzXN.zzMa != null) {
            zzbs.zzbS();
            zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY, this.zzsP.zzvR, false, this.zzb((List)this.zzsP.zzvY.zzXN.zzMa));
         }

         if (this.zzsP.zzvY.zzMG != null && this.zzsP.zzvY.zzMG.zzLM != null) {
            zzbs.zzbS();
            zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, this.zzsP.zzvY, this.zzsP.zzvR, false, this.zzsP.zzvY.zzMG.zzLM);
         }

         super.onAdClicked();
      }
   }

   final boolean zza(zzaff var1) {
      boolean var3 = false;
      zzir var2;
      if (this.zzsQ != null) {
         var2 = this.zzsQ;
         this.zzsQ = null;
      } else {
         var2 = var1.zzSz;
         var3 = var1.zzSz.extras != null ? var2.extras.getBoolean("_noRefresh", false) : false;
      }

      return this.zza(var2, var1, var3);
   }

   protected boolean zza(zzir var1, zzaff var2, boolean var3) {
      if (!var3 && this.zzsP.zzcc()) {
         if (var2.zzMg > 0L) {
            this.zzsO.zza(var1, var2.zzMg);
         } else if (var2.zzXN != null && var2.zzXN.zzMg > 0L) {
            this.zzsO.zza(var1, var2.zzXN.zzMg);
         } else if (!var2.zzTo && var2.errorCode == 2) {
            this.zzsO.zzg(var1);
         }
      }

      return this.zzsO.zzbo();
   }

   public void pause() {
      com.google.android.gms.common.internal.zzbo.zzcz("pause must be called on the main UI thread.");
      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzPg != null && this.zzsP.zzcc()) {
         zzbs.zzbB();
         zzahe.zzk(this.zzsP.zzvY.zzPg);
      }

      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzMH != null) {
         try {
            this.zzsP.zzvY.zzMH.pause();
         } catch (RemoteException var1) {
            zzafr.zzaT("Could not pause mediation adapter.");
         }
      }

      this.zzsR.zzi(this.zzsP.zzvY);
      this.zzsO.pause();
   }

   public void resume() {
      com.google.android.gms.common.internal.zzbo.zzcz("resume must be called on the main UI thread.");
      zzaka var1 = null;
      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzPg != null) {
         var1 = this.zzsP.zzvY.zzPg;
      }

      if (var1 != null && this.zzsP.zzcc()) {
         zzbs.zzbB();
         zzahe.zzl(this.zzsP.zzvY.zzPg);
      }

      if (this.zzsP.zzvY != null && this.zzsP.zzvY.zzMH != null) {
         try {
            this.zzsP.zzvY.zzMH.resume();
         } catch (RemoteException var2) {
            zzafr.zzaT("Could not resume mediation adapter.");
         }
      }

      if (var1 == null || !var1.zziD()) {
         this.zzsO.resume();
      }

      this.zzsR.zzj(this.zzsP.zzvY);
   }

   protected final boolean zzb(zzir var1) {
      return super.zzb(var1) && !this.zzsY;
   }

   protected boolean zzaz() {
      boolean var1 = true;
      zzbs.zzbz();
      if (zzagz.zzc(this.zzsP.zzqD, this.zzsP.zzqD.getPackageName(), "android.permission.INTERNET")) {
         zzbs.zzbz();
         if (zzagz.zzD(this.zzsP.zzqD)) {
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   public void zzaA() {
      this.zzsY = false;
      this.zzap();
      this.zzsP.zzwa.zzhe();
   }

   public void zzaB() {
      this.zzsY = true;
      this.zzar();
   }

   public final void onPause() {
      this.zzsR.zzi(this.zzsP.zzvY);
   }

   public final void onResume() {
      this.zzsR.zzj(this.zzsP.zzvY);
   }

   public void zzaC() {
      this.onAdClicked();
   }

   public final void zzaD() {
      this.zzaA();
   }

   public final void zzaE() {
      this.zzaq();
   }

   public final void zzaF() {
      this.zzaB();
   }

   public final void zzaG() {
      if (this.zzsP.zzvY != null) {
         String var1 = this.zzsP.zzvY.zzMI;
         zzafr.zzaT((new StringBuilder(74 + String.valueOf(var1).length())).append("Mediation adapter ").append(var1).append(" refreshed, but mediation adapters should never refresh.").toString());
      }

      this.zza(this.zzsP.zzvY, true);
      this.zzas();
   }

   public void zzaH() {
      this.recordImpression();
   }

   public final void zze(String var1, String var2) {
      this.onAppEvent(var1, var2);
   }

   public final void zza(zzpj var1, String var2) {
      try {
         zzpt var3 = null;
         String var4 = var1 != null ? var1.getCustomTemplateId() : null;
         if (this.zzsP.zzwh != null && var4 != null) {
            var3 = (zzpt)this.zzsP.zzwh.get(var4);
         }

         if (var3 == null) {
            zzafr.zzaT("Mediation adapter invoked onCustomClick but no listeners were set.");
         } else {
            var3.zzb(var1, var2);
         }
      } catch (RemoteException var5) {
         zzafr.zzc("Unable to call onCustomClick.", var5);
      }
   }

   private final zzaaf zza(zzir var1, Bundle var2, zzafj var3) {
      ApplicationInfo var4 = this.zzsP.zzqD.getApplicationInfo();

      PackageInfo var5;
      try {
         var5 = zzbha.zzaP(this.zzsP.zzqD).getPackageInfo(var4.packageName, 0);
      } catch (NameNotFoundException var29) {
         var5 = null;
      }

      DisplayMetrics var6 = this.zzsP.zzqD.getResources().getDisplayMetrics();
      Bundle var7 = null;
      if (this.zzsP.zzvU != null && this.zzsP.zzvU.getParent() != null) {
         int[] var8 = new int[2];
         this.zzsP.zzvU.getLocationOnScreen(var8);
         int var9 = var8[0];
         int var10 = var8[1];
         int var11 = this.zzsP.zzvU.getWidth();
         int var12 = this.zzsP.zzvU.getHeight();
         byte var13 = 0;
         if (this.zzsP.zzvU.isShown() && var9 + var11 > 0 && var10 + var12 > 0 && var9 <= var6.widthPixels && var10 <= var6.heightPixels) {
            var13 = 1;
         }

         (var7 = new Bundle(5)).putInt("x", var9);
         var7.putInt("y", var10);
         var7.putInt("width", var11);
         var7.putInt("height", var12);
         var7.putInt("visible", var13);
      }

      String var30 = zzbs.zzbD().zzhp();
      this.zzsP.zzwa = new zzafh(var30, this.zzsP.zzvR);
      this.zzsP.zzwa.zzo(var1);
      zzbs.zzbz();
      String var31 = zzagz.zza((Context)this.zzsP.zzqD, (View)this.zzsP.zzvU, (zziv)this.zzsP.zzvX);
      long var32 = 0L;
      if (this.zzsP.zzwe != null) {
         try {
            var32 = this.zzsP.zzwe.getValue();
         } catch (RemoteException var28) {
            zzafr.zzaT("Cannot get correlation id, default to 0.");
         }
      }

      String var33 = UUID.randomUUID().toString();
      Bundle var34 = zzbs.zzbD().zza(this.zzsP.zzqD, this, var30);
      ArrayList var14 = new ArrayList();
      ArrayList var15 = new ArrayList();

      for(int var16 = 0; var16 < this.zzsP.zzwi.size(); ++var16) {
         String var17 = (String)this.zzsP.zzwi.keyAt(var16);
         var14.add(var17);
         if (this.zzsP.zzwh.containsKey(var17) && this.zzsP.zzwh.get(var17) != null) {
            var15.add(var17);
         }
      }

      zzajm var35 = zzagt.zza((Callable)(new zze(this)));
      zzajm var36 = zzagt.zza((Callable)(new zzf(this)));
      String var18 = null;
      if (var3 != null) {
         var18 = var3.zzhk();
      }

      String var19 = null;
      if (this.zzsP.zzwq != null && this.zzsP.zzwq.size() > 0) {
         int var20 = 0;
         if (var5 != null) {
            var20 = var5.versionCode;
         }

         int var21 = zzbs.zzbD().zzhA();
         if (var20 > var21) {
            zzbs.zzbD().zzhF();
            zzbs.zzbD().zzx(var20);
         } else {
            JSONObject var22;
            JSONArray var23;
            if ((var22 = zzbs.zzbD().zzhE()) != null && (var23 = var22.optJSONArray(this.zzsP.zzvR)) != null) {
               var19 = var23.toString();
            }
         }
      }

      zziv var10004 = this.zzsP.zzvX;
      String var10005 = this.zzsP.zzvR;
      String var10009 = zzbs.zzbD().getSessionId();
      zzaje var10010 = this.zzsP.zzvT;
      List var10012 = this.zzsP.zzwq;
      boolean var10015 = zzbs.zzbD().zzhs();
      int var10016 = var6.widthPixels;
      int var10017 = var6.heightPixels;
      float var10018 = var6.density;
      List var10022 = zzmo.zzdJ();
      String var10023 = this.zzsP.zzvQ;
      zzon var10024 = this.zzsP.zzwj;
      String var10025 = this.zzsP.zzce();
      zzbs.zzbz();
      float var10026 = zzagz.zzbf();
      zzbs.zzbz();
      boolean var10027 = zzagz.zzbh();
      zzbs.zzbz();
      int var10028 = zzagz.zzN(this.zzsP.zzqD);
      zzbs.zzbz();
      int var10029 = zzagz.zzp(this.zzsP.zzvU);
      boolean var10030 = this.zzsP.zzqD instanceof Activity;
      boolean var10031 = zzbs.zzbD().zzhx();
      boolean var10034 = zzbs.zzbD().zzhC();
      int var10035 = zzbs.zzbW().zzeE();
      zzbs.zzbz();
      Bundle var10036 = zzagz.zzhS();
      String var10037 = zzbs.zzbH().zzib();
      zzky var10038 = this.zzsP.zzwl;
      boolean var10039 = zzbs.zzbH().zzic();
      Bundle var10040 = zztp.zzeN().asBundle();
      zzbs.zzbD();
      String var24 = this.zzsP.zzvR;
      String var25 = this.zzsP.zzvR;
      SharedPreferences var26 = this.zzsP.zzqD.getSharedPreferences("admob", 0);
      Set var27 = Collections.emptySet();
      return new zzaaf(var7, var1, var10004, var10005, var4, var5, var30, var10009, var10010, var34, var10012, var14, var2, var10015, var10016, var10017, var10018, var31, var32, var33, var10022, var10023, var10024, var10025, var10026, var10027, var10028, var10029, var10030, var10031, var35, var18, var10034, var10035, var10036, var10037, var10038, var10039, var10040, var26.getStringSet("never_pool_slots", var27).contains(var25), var36, this.zzsP.zzwn, var19, var15);
   }

   public final void recordImpression() {
      this.zza(this.zzsP.zzvY, false);
   }

   protected void zza(@Nullable zzaff var1, boolean var2) {
      if (var1 == null) {
         zzafr.zzaT("Ad state was null when trying to ping impression URLs.");
      } else {
         if (var1 == null) {
            zzafr.zzaT("Ad state was null when trying to ping impression URLs.");
         } else {
            zzafr.zzaC("Pinging Impression URLs.");
            if (super.zzsP.zzwa != null) {
               super.zzsP.zzwa.zzhc();
            }

            if (var1.zzMb != null && !var1.zzXU) {
               zzbs.zzbz();
               zzagz.zza(super.zzsP.zzqD, super.zzsP.zzvT.zzaP, this.zzb(var1.zzMb));
               var1.zzXU = true;
            }
         }

         if (var1.zzXN != null && var1.zzXN.zzMb != null) {
            zzbs.zzbS();
            zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, var1, this.zzsP.zzvR, var2, this.zzb((List)var1.zzXN.zzMb));
         }

         if (var1.zzMG != null && var1.zzMG.zzLN != null) {
            zzbs.zzbS();
            zzuj.zza(this.zzsP.zzqD, this.zzsP.zzvT.zzaP, var1, this.zzsP.zzvR, var2, var1.zzMG.zzLN);
         }

      }
   }

   public final String getMediationAdapterClassName() {
      return this.zzsP.zzvY == null ? null : this.zzsP.zzvY.zzMI;
   }

   public final String zzaI() {
      if (this.zzsP.zzvY == null) {
         return null;
      } else {
         String var1 = this.zzsP.zzvY.zzMI;
         if (("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(var1) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(var1)) && this.zzsP.zzvY.zzMG != null) {
            String var2 = this.zzsP.zzvY.zzMG.zzLP;

            try {
               return (new JSONObject(var2)).getString("class_name");
            } catch (NullPointerException | JSONException var3) {
               ;
            }
         }

         return var1;
      }
   }

   public void showInterstitial() {
      throw new IllegalStateException("showInterstitial is not supported for current ad type");
   }

   public final void zzaJ() {
      zzbs.zzbz();
      zzagz.runOnUiThread(new zzg(this));
   }

   public final void zzaK() {
      zzbs.zzbz();
      zzagz.runOnUiThread(new zzh(this));
   }
}
