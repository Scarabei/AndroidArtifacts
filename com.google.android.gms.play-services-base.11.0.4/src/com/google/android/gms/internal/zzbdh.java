package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

final class zzbdh implements zzj, zzbel {
   private final Api.zze zzaCy;
   private final zzbat zzaAK;
   private zzal zzaDl;
   private Set zzame;
   private boolean zzaEx;
   // $FF: synthetic field
   final zzbdb zzaEm;

   public zzbdh(zzbdb var1, Api.zze var2, zzbat var3) {
      this.zzaEm = var1;
      this.zzaDl = null;
      this.zzame = null;
      this.zzaEx = false;
      this.zzaCy = var2;
      this.zzaAK = var3;
   }

   public final void zzf(@NonNull ConnectionResult var1) {
      zzbdb.zza(this.zzaEm).post(new zzbdi(this, var1));
   }

   @WorkerThread
   public final void zzh(ConnectionResult var1) {
      ((zzbdd)zzbdb.zzj(this.zzaEm).get(this.zzaAK)).zzh(var1);
   }

   @WorkerThread
   public final void zzb(zzal var1, Set var2) {
      if (var1 != null && var2 != null) {
         this.zzaDl = var1;
         this.zzame = var2;
         this.zzqz();
      } else {
         Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
         this.zzh(new ConnectionResult(4));
      }
   }

   @WorkerThread
   private final void zzqz() {
      if (this.zzaEx && this.zzaDl != null) {
         this.zzaCy.zza(this.zzaDl, this.zzame);
      }

   }

   // $FF: synthetic method
   static boolean zza(zzbdh var0, boolean var1) {
      return var0.zzaEx = true;
   }

   // $FF: synthetic method
   static Api.zze zza(zzbdh var0) {
      return var0.zzaCy;
   }

   // $FF: synthetic method
   static void zzb(zzbdh var0) {
      var0.zzqz();
   }

   // $FF: synthetic method
   static zzbat zzc(zzbdh var0) {
      return var0.zzaAK;
   }
}
