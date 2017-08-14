package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;

@zzzn
public final class zzaac extends zzzy implements zzf, zzg {
   private Context mContext;
   private zzaje zztW;
   private zzajp zzSs;
   private final zzzw zzSt;
   private final Object mLock = new Object();
   private zzaad zzSw;

   public zzaac(Context var1, zzaje var2, zzajp var3, zzzw var4) {
      super(var3, var4);
      this.mContext = var1;
      this.zztW = var2;
      this.zzSs = var3;
      this.zzSt = var4;
      zzme var6 = zzmo.zzCK;
      Looper var5;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
         var5 = com.google.android.gms.ads.internal.zzbs.zzbP().zzie();
      } else {
         var5 = var1.getMainLooper();
      }

      this.zzSw = new zzaad(var1, var5, this, this, this.zztW.zzaaP);
      this.zzSw.zzrb();
   }

   public final zzaam zzgB() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzaam var10000;
         try {
            var10000 = this.zzSw.zzgC();
         } catch (DeadObjectException | IllegalStateException var3) {
            return null;
         }

         return var10000;
      }
   }

   public final void zzgA() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzSw.isConnected() || this.zzSw.isConnecting()) {
            this.zzSw.disconnect();
         }

         Binder.flushPendingCommands();
      }
   }

   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      zzafr.zzaC("Cannot connect to remote service, fallback to local instance.");
      (new zzaab(this.mContext, this.zzSs, this.zzSt)).zzgp();
      Bundle var2;
      (var2 = new Bundle()).putString("action", "gms_connection_failed_fallback_to_local");
      com.google.android.gms.ads.internal.zzbs.zzbz().zzb(this.mContext, this.zztW.zzaP, "gmob-apps", var2, true);
   }

   public final void onConnected(Bundle var1) {
      this.zzgp();
   }

   public final void onConnectionSuspended(int var1) {
      zzafr.zzaC("Disconnected from remote ad request service.");
   }
}
