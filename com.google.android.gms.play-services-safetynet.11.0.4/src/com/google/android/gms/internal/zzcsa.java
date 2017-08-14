package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.SafeBrowsingThreat;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class zzcsa implements SafetyNetApi {
   private static final String TAG = zzcsa.class.getSimpleName();
   protected static SparseArray zzbBM;
   protected static long zzbBN;

   public PendingResult attest(GoogleApiClient var1, byte[] var2) {
      return zza(var1, var2, (String)null);
   }

   public static PendingResult zza(GoogleApiClient var0, byte[] var1, String var2) {
      return var0.zzd(new zzcsb(var0, var1, var2));
   }

   public PendingResult lookupUri(GoogleApiClient var1, List var2, String var3) {
      return this.zza(var1, var2, var3, (String)null);
   }

   public final PendingResult zza(GoogleApiClient var1, List var2, String var3, String var4) {
      if (var2 == null) {
         throw new IllegalArgumentException("Null threatTypes in lookupUri");
      } else if (TextUtils.isEmpty(var3)) {
         throw new IllegalArgumentException("Null or empty uri in lookupUri");
      } else {
         return var1.zzd(new zzcsc(this, var1, var2, var3, var4));
      }
   }

   public PendingResult lookupUri(GoogleApiClient var1, String var2, int... var3) {
      return zza(var1, var2, 1, (String)null, var3);
   }

   public PendingResult lookupUri(GoogleApiClient var1, String var2, String var3, int... var4) {
      return zza(var1, var2, 1, var3, var4);
   }

   public static PendingResult zza(GoogleApiClient var0, String var1, int var2, String var3, int... var4) {
      if (var4.length == 0) {
         throw new IllegalArgumentException("Null threatTypes in lookupUri");
      } else if (TextUtils.isEmpty(var1)) {
         throw new IllegalArgumentException("Null or empty uri in lookupUri");
      } else {
         return var0.zzd(new zzcsd(var0, var4, var2, var1, var3));
      }
   }

   public boolean lookupUriInLocalBlacklist(String var1, int... var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Null threatTypes in lookupUri");
      } else if (TextUtils.isEmpty(var1)) {
         throw new IllegalArgumentException("Null or empty uri in lookupUri");
      } else if (zzbBM != null && zzbBN != 0L && SystemClock.elapsedRealtime() - zzbBN < 1200000L) {
         if (zzbBM != null && zzbBM.size() != 0) {
            List var3;
            if ((var3 = (new zzcss(var1)).zzAk()) != null && !var3.isEmpty()) {
               Iterator var4 = var3.iterator();

               while(var4.hasNext()) {
                  zzcsp var5 = (zzcsp)var4.next();
                  int[] var6 = var2;
                  int var7 = var2.length;

                  for(int var8 = 0; var8 < var7; ++var8) {
                     int var9 = var6[var8];
                     zzcsr var10;
                     if ((var10 = (zzcsr)zzbBM.get(var9)) == null) {
                        return true;
                     }

                     if (var10.zzr(var5.zzbu(4).getBytes())) {
                        return true;
                     }
                  }
               }

               return false;
            } else {
               return true;
            }
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   public boolean isVerifyAppsEnabled(Context var1) {
      GoogleApiClient var2 = (new Builder(var1)).addApi(SafetyNet.API).build();

      try {
         if (var2.blockingConnect(3L, TimeUnit.SECONDS).isSuccess()) {
            SafetyNetApi.VerifyAppsUserResult var3;
            boolean var4 = (var3 = (SafetyNetApi.VerifyAppsUserResult)this.isVerifyAppsEnabled(var2).await(3L, TimeUnit.SECONDS)) != null && var3.isVerifyAppsEnabled();
            return var4;
         }
      } finally {
         if (var2 != null) {
            var2.disconnect();
         }

      }

      return false;
   }

   public PendingResult isVerifyAppsEnabled(GoogleApiClient var1) {
      return var1.zzd(new zzcse(this, var1));
   }

   public PendingResult enableVerifyApps(GoogleApiClient var1) {
      return var1.zzd(new zzcsf(this, var1));
   }

   public PendingResult listHarmfulApps(GoogleApiClient var1) {
      return var1.zzd(new zzcsg(this, var1));
   }

   public PendingResult verifyWithRecaptcha(GoogleApiClient var1, String var2) {
      if (TextUtils.isEmpty(var2)) {
         throw new IllegalArgumentException("Null or empty site key in verifyWithRecaptcha");
      } else {
         return var1.zzd(new zzcsh(this, var1, var2));
      }
   }

   static class zzh implements SafetyNetApi.RecaptchaTokenResult {
      private final Status mStatus;
      private final com.google.android.gms.safetynet.zzf zzbCd;

      public zzh(Status var1, com.google.android.gms.safetynet.zzf var2) {
         this.mStatus = var1;
         this.zzbCd = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getTokenResult() {
         return this.zzbCd == null ? null : this.zzbCd.getTokenResult();
      }
   }

   abstract static class zze extends zzcrv {
      protected zzcrw zzbBW = new zzcsl(this);

      public zze(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new zzcsa.zzh(var1, (com.google.android.gms.safetynet.zzf)null);
      }
   }

   abstract static class zzd extends zzcrv {
      protected final zzcrw zzbBW = new zzcsk(this);

      public zzd(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new zzcsa.zzg(var1, (com.google.android.gms.safetynet.zzd)null);
      }
   }

   static class zzg implements SafetyNetApi.HarmfulAppsResult {
      private final Status mStatus;
      private final com.google.android.gms.safetynet.zzd zzbCc;

      public zzg(Status var1, com.google.android.gms.safetynet.zzd var2) {
         this.mStatus = var1;
         this.zzbCc = var2;
      }

      public final List getHarmfulAppsList() {
         return this.zzbCc == null ? Collections.emptyList() : Arrays.asList(this.zzbCc.zzbBH);
      }

      public final long getLastScanTimeMs() {
         return this.zzbCc == null ? 0L : this.zzbCc.zzbBG;
      }

      public final Status getStatus() {
         return this.mStatus;
      }
   }

   abstract static class zzc extends zzcrv {
      protected zzcrw zzbBW = new zzcsj(this);

      public zzc(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new zzcsa.zzj(var1, false);
      }
   }

   static class zzj implements SafetyNetApi.VerifyAppsUserResult {
      private Status mStatus;
      private boolean zzzE;

      public zzj() {
      }

      public zzj(Status var1, boolean var2) {
         this.mStatus = var1;
         this.zzzE = var2;
      }

      public final boolean isVerifyAppsEnabled() {
         return this.mStatus != null && this.mStatus.isSuccess() ? this.zzzE : false;
      }

      public final Status getStatus() {
         return this.mStatus;
      }
   }

   static class zzi implements SafetyNetApi.SafeBrowsingResult {
      private Status mStatus;
      private final SafeBrowsingData zzbCe;
      private String zzbBI;

      public zzi(Status var1, SafeBrowsingData var2) {
         this.mStatus = var1;
         this.zzbCe = var2;
         this.zzbBI = null;
         if (this.zzbCe != null) {
            this.zzbBI = this.zzbCe.getMetadata();
         } else {
            if (this.mStatus.isSuccess()) {
               this.mStatus = new Status(8);
            }

         }
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getMetadata() {
         return this.zzbBI;
      }

      public final List getDetectedThreats() {
         ArrayList var1 = new ArrayList();
         if (this.zzbBI == null) {
            return var1;
         } else {
            JSONArray var2;
            try {
               var2 = (new JSONObject(this.zzbBI)).getJSONArray("matches");
            } catch (JSONException var8) {
               return var1;
            }

            for(int var3 = 0; var3 < var2.length(); ++var3) {
               try {
                  int var4 = Integer.parseInt(var2.getJSONObject(var3).getString("threat_type"));
                  SafeBrowsingThreat var5 = new SafeBrowsingThreat(var4);
                  var1.add(var5);
               } catch (JSONException var6) {
                  ;
               } catch (NumberFormatException var7) {
                  ;
               }
            }

            return var1;
         }
      }
   }

   abstract static class zzf extends zzcrv {
      protected zzcrw zzbBW = new zzcsm(this);

      public zzf(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new zzcsa.zzi(var1, (SafeBrowsingData)null);
      }
   }

   static class zza implements SafetyNetApi.AttestationResult {
      private final Status mStatus;
      private final com.google.android.gms.safetynet.zza zzbBV;

      public zza(Status var1, com.google.android.gms.safetynet.zza var2) {
         this.mStatus = var1;
         this.zzbBV = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getJwsResult() {
         return this.zzbBV == null ? null : this.zzbBV.getJwsResult();
      }
   }

   abstract static class zzb extends zzcrv {
      protected zzcrw zzbBW = new zzcsi(this);

      public zzb(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      protected final Result zzb(Status var1) {
         return new zzcsa.zza(var1, (com.google.android.gms.safetynet.zza)null);
      }
   }
}
