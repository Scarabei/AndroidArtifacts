package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzbba extends zzbds implements OnCancelListener {
   protected volatile boolean mStarted;
   protected final AtomicReference zzaBN;
   private final Handler zzaBO;
   protected final GoogleApiAvailability zzaBd;

   protected zzbba(zzbdt var1) {
      this(var1, GoogleApiAvailability.getInstance());
   }

   private zzbba(zzbdt var1, GoogleApiAvailability var2) {
      super(var1);
      this.zzaBN = new AtomicReference((Object)null);
      this.zzaBO = new Handler(Looper.getMainLooper());
      this.zzaBd = var2;
   }

   public void onCancel(DialogInterface var1) {
      this.zza(new ConnectionResult(13, (PendingIntent)null), zza((zzbbb)this.zzaBN.get()));
      this.zzpx();
   }

   public final void onCreate(Bundle var1) {
      super.onCreate(var1);
      if (var1 != null) {
         AtomicReference var10000 = this.zzaBN;
         zzbbb var10001;
         if (var1.getBoolean("resolving_error", false)) {
            ConnectionResult var3 = new ConnectionResult(var1.getInt("failed_status"), (PendingIntent)var1.getParcelable("failed_resolution"));
            int var4 = var1.getInt("failed_client_id", -1);
            var10001 = new zzbbb(var3, var4);
         } else {
            var10001 = null;
         }

         var10000.set(var10001);
      }

   }

   public final void onSaveInstanceState(Bundle var1) {
      super.onSaveInstanceState(var1);
      zzbbb var3;
      if ((var3 = (zzbbb)this.zzaBN.get()) != null) {
         var1.putBoolean("resolving_error", true);
         var1.putInt("failed_client_id", var3.zzpy());
         var1.putInt("failed_status", var3.zzpz().getErrorCode());
         var1.putParcelable("failed_resolution", var3.zzpz().getResolution());
      }

   }

   public void onStart() {
      super.onStart();
      this.mStarted = true;
   }

   public final void onActivityResult(int var1, int var2, Intent var3) {
      boolean var4 = false;
      zzbbb var5 = (zzbbb)this.zzaBN.get();
      switch(var1) {
      case 1:
         if (var2 == -1) {
            var4 = true;
         } else if (var2 == 0) {
            int var7 = 13;
            if (var3 != null) {
               var7 = var3.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
            }

            var5 = new zzbbb(new ConnectionResult(var7, (PendingIntent)null), zza(var5));
            this.zzaBN.set(var5);
         }
         break;
      case 2:
         int var6;
         if ((var6 = this.zzaBd.isGooglePlayServicesAvailable(this.getActivity())) == 0) {
            var4 = true;
         }

         if (var5 == null) {
            return;
         }

         if (var5.zzpz().getErrorCode() == 18 && var6 == 18) {
            return;
         }
      }

      if (var4) {
         this.zzpx();
      } else {
         if (var5 != null) {
            this.zza(var5.zzpz(), var5.zzpy());
         }

      }
   }

   public void onStop() {
      super.onStop();
      this.mStarted = false;
   }

   protected abstract void zza(ConnectionResult var1, int var2);

   protected abstract void zzps();

   protected final void zzpx() {
      this.zzaBN.set((Object)null);
      this.zzps();
   }

   public final void zzb(ConnectionResult var1, int var2) {
      zzbbb var3 = new zzbbb(var1, var2);
      if (this.zzaBN.compareAndSet((Object)null, var3)) {
         this.zzaBO.post(new zzbbc(this, var3));
      }

   }

   private static int zza(@Nullable zzbbb var0) {
      return var0 == null ? -1 : var0.zzpy();
   }
}
