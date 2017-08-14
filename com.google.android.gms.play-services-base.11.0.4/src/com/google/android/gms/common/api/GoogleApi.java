package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.internal.zzbas;
import com.google.android.gms.internal.zzbat;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzbbw;
import com.google.android.gms.internal.zzbdb;
import com.google.android.gms.internal.zzbdd;
import com.google.android.gms.internal.zzbdj;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzbeq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class GoogleApi {
   private final Context mContext;
   private final Api zzayW;
   private final Api.ApiOptions zzaAJ;
   private final zzbat zzaAK;
   private final Looper zzrM;
   private final int mId;
   private final GoogleApiClient zzaAL;
   private final zzbem zzaAM;
   private final Account zzajb;
   protected final zzbdb zzaAN;

   protected GoogleApi(@NonNull Context var1, Api var2, Looper var3) {
      zzbo.zzb(var1, "Null context is not permitted.");
      zzbo.zzb(var2, "Api must not be null.");
      zzbo.zzb(var3, "Looper must not be null.");
      this.mContext = var1.getApplicationContext();
      this.zzayW = var2;
      this.zzaAJ = null;
      this.zzrM = var3;
      this.zzaAK = zzbat.zzb(var2);
      this.zzaAL = new zzbdj(this);
      this.zzaAN = zzbdb.zzay(this.mContext);
      this.mId = this.zzaAN.zzqm();
      this.zzaAM = new zzbas();
      this.zzajb = null;
   }

   /** @deprecated */
   @Deprecated
   public GoogleApi(@NonNull Context var1, Api var2, Api.ApiOptions var3, Looper var4, zzbem var5) {
      this((Context)var1, var2, (Api.ApiOptions)null, (GoogleApi.zza)(new zzd()).zza(var4).zza(var5).zzpj());
   }

   @MainThread
   private GoogleApi(@NonNull Activity var1, Api var2, Api.ApiOptions var3, GoogleApi.zza var4) {
      zzbo.zzb(var1, "Null activity is not permitted.");
      zzbo.zzb(var2, "Api must not be null.");
      zzbo.zzb(var4, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
      this.mContext = var1.getApplicationContext();
      this.zzayW = var2;
      this.zzaAJ = null;
      this.zzrM = var4.zzaAQ;
      this.zzaAK = zzbat.zza(this.zzayW, this.zzaAJ);
      this.zzaAL = new zzbdj(this);
      this.zzaAN = zzbdb.zzay(this.mContext);
      this.mId = this.zzaAN.zzqm();
      this.zzaAM = var4.zzaAP;
      this.zzajb = var4.account;
      zzbbw.zza(var1, this.zzaAN, this.zzaAK);
      this.zzaAN.zzb(this);
   }

   public GoogleApi(@NonNull Context var1, Api var2, Api.ApiOptions var3, GoogleApi.zza var4) {
      zzbo.zzb(var1, "Null context is not permitted.");
      zzbo.zzb(var2, "Api must not be null.");
      zzbo.zzb(var4, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
      this.mContext = var1.getApplicationContext();
      this.zzayW = var2;
      this.zzaAJ = var3;
      this.zzrM = var4.zzaAQ;
      this.zzaAK = zzbat.zza(this.zzayW, this.zzaAJ);
      this.zzaAL = new zzbdj(this);
      this.zzaAN = zzbdb.zzay(this.mContext);
      this.mId = this.zzaAN.zzqm();
      this.zzaAM = var4.zzaAP;
      this.zzajb = var4.account;
      this.zzaAN.zzb(this);
   }

   /** @deprecated */
   @Deprecated
   public GoogleApi(@NonNull Activity var1, Api var2, Api.ApiOptions var3, zzbem var4) {
      this((Activity)var1, var2, (Api.ApiOptions)null, (GoogleApi.zza)(new zzd()).zza(var4).zza(var1.getMainLooper()).zzpj());
   }

   /** @deprecated */
   @Deprecated
   public GoogleApi(@NonNull Context var1, Api var2, Api.ApiOptions var3, zzbem var4) {
      this(var1, var2, var3, (new zzd()).zza(var4).zzpj());
   }

   private final zzbay zza(int var1, @NonNull zzbay var2) {
      var2.zzpC();
      this.zzaAN.zza(this, var1, var2);
      return var2;
   }

   private final Task zza(int var1, @NonNull zzbeq var2) {
      TaskCompletionSource var3 = new TaskCompletionSource();
      this.zzaAN.zza(this, var1, var2, var3, this.zzaAM);
      return var3.getTask();
   }

   public final zzbay zza(@NonNull zzbay var1) {
      return this.zza(0, (zzbay)var1);
   }

   public final Task zza(zzbeq var1) {
      return this.zza(0, (zzbeq)var1);
   }

   public final zzbay zzb(@NonNull zzbay var1) {
      return this.zza(1, (zzbay)var1);
   }

   public final Task zzb(zzbeq var1) {
      return this.zza(1, (zzbeq)var1);
   }

   public final zzbay zzc(@NonNull zzbay var1) {
      return this.zza(2, (zzbay)var1);
   }

   @WorkerThread
   public Api.zze zza(Looper var1, zzbdd var2) {
      zzq var3 = (new GoogleApiClient.Builder(this.mContext)).zze(this.zzajb).zzpn();
      return this.zzayW.zzpc().zza(this.mContext, var1, var3, this.zzaAJ, var2, var2);
   }

   public final Api zzpg() {
      return this.zzayW;
   }

   public final zzbat zzph() {
      return this.zzaAK;
   }

   public final int getInstanceId() {
      return this.mId;
   }

   public final GoogleApiClient zzpi() {
      return this.zzaAL;
   }

   public final Looper getLooper() {
      return this.zzrM;
   }

   public final Context getApplicationContext() {
      return this.mContext;
   }

   public zzbej zza(Context var1, Handler var2) {
      return new zzbej(var1, var2);
   }

   public static class zza {
      public static final GoogleApi.zza zzaAO = (new zzd()).zzpj();
      public final zzbem zzaAP;
      public final Account account;
      public final Looper zzaAQ;

      private zza(zzbem var1, Account var2, Looper var3) {
         this.zzaAP = var1;
         this.account = var2;
         this.zzaAQ = var3;
      }

      // $FF: synthetic method
      zza(zzbem var1, Account var2, Looper var3, zzc var4) {
         this(var1, (Account)null, var3);
      }
   }
}
