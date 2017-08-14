package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbo;
import java.lang.ref.WeakReference;

public final class zzbes extends TransformedResult implements ResultCallback {
   private ResultTransform zzaFa = null;
   private zzbes zzaFb = null;
   private volatile ResultCallbacks zzaFc = null;
   private PendingResult zzaFd = null;
   private final Object zzaBW = new Object();
   private Status zzaFe = null;
   private final WeakReference zzaBY;
   private final zzbeu zzaFf;
   private boolean zzaFg = false;

   public zzbes(WeakReference var1) {
      zzbo.zzb(var1, "GoogleApiClient reference must not be null");
      this.zzaBY = var1;
      GoogleApiClient var2 = (GoogleApiClient)this.zzaBY.get();
      this.zzaFf = new zzbeu(this, var2 != null ? var2.getLooper() : Looper.getMainLooper());
   }

   @NonNull
   public final TransformedResult then(@NonNull ResultTransform var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         zzbo.zza(this.zzaFa == null, "Cannot call then() twice.");
         zzbo.zza(this.zzaFc == null, "Cannot call then() and andFinally() on the same TransformedResult.");
         this.zzaFa = var1;
         zzbes var3 = new zzbes(this.zzaBY);
         this.zzaFb = var3;
         this.zzqJ();
         return var3;
      }
   }

   public final void andFinally(@NonNull ResultCallbacks var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         zzbo.zza(this.zzaFc == null, "Cannot call andFinally() twice.");
         zzbo.zza(this.zzaFa == null, "Cannot call then() and andFinally() on the same TransformedResult.");
         this.zzaFc = var1;
         this.zzqJ();
      }
   }

   public final void onResult(Result var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (var1.getStatus().isSuccess()) {
            if (this.zzaFa != null) {
               zzbeg.zzqj().submit(new zzbet(this, var1));
            } else if (this.zzqL()) {
               this.zzaFc.onSuccess(var1);
            }
         } else {
            this.zzv(var1.getStatus());
            zzc(var1);
         }

      }
   }

   public final void zza(PendingResult var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         this.zzaFd = var1;
         this.zzqJ();
      }
   }

   private final void zzqJ() {
      if (this.zzaFa != null || this.zzaFc != null) {
         GoogleApiClient var1 = (GoogleApiClient)this.zzaBY.get();
         if (!this.zzaFg && this.zzaFa != null && var1 != null) {
            var1.zza(this);
            this.zzaFg = true;
         }

         if (this.zzaFe != null) {
            this.zzw(this.zzaFe);
         } else {
            if (this.zzaFd != null) {
               this.zzaFd.setResultCallback(this);
            }

         }
      }
   }

   private final void zzv(Status var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         this.zzaFe = var1;
         this.zzw(this.zzaFe);
      }
   }

   private final void zzw(Status var1) {
      Object var2 = this.zzaBW;
      synchronized(this.zzaBW) {
         if (this.zzaFa != null) {
            Status var3;
            zzbo.zzb(var3 = this.zzaFa.onFailure(var1), "onFailure must not return null");
            this.zzaFb.zzv(var3);
         } else if (this.zzqL()) {
            this.zzaFc.onFailure(var1);
         }

      }
   }

   final void zzqK() {
      this.zzaFc = null;
   }

   private final boolean zzqL() {
      GoogleApiClient var1 = (GoogleApiClient)this.zzaBY.get();
      return this.zzaFc != null && var1 != null;
   }

   private static void zzc(Result var0) {
      if (var0 instanceof Releasable) {
         try {
            ((Releasable)var0).release();
            return;
         } catch (RuntimeException var3) {
            String var2 = String.valueOf(var0);
            Log.w("TransformedResultImpl", (new StringBuilder(18 + String.valueOf(var2).length())).append("Unable to release ").append(var2).toString(), var3);
         }
      }

   }

   // $FF: synthetic method
   static ResultTransform zzc(zzbes var0) {
      return var0.zzaFa;
   }

   // $FF: synthetic method
   static zzbeu zzd(zzbes var0) {
      return var0.zzaFf;
   }

   // $FF: synthetic method
   static void zza(zzbes var0, Result var1) {
      zzc(var1);
   }

   // $FF: synthetic method
   static WeakReference zze(zzbes var0) {
      return var0.zzaBY;
   }

   // $FF: synthetic method
   static Object zzf(zzbes var0) {
      return var0.zzaBW;
   }

   // $FF: synthetic method
   static zzbes zzg(zzbes var0) {
      return var0.zzaFb;
   }

   // $FF: synthetic method
   static void zza(zzbes var0, Status var1) {
      var0.zzv(var1);
   }
}
