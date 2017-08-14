package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.zzbo;

public abstract class ResolvingResultCallbacks extends ResultCallbacks {
   private final Activity mActivity;
   private final int zzaBk;

   protected ResolvingResultCallbacks(@NonNull Activity var1, int var2) {
      this.mActivity = (Activity)zzbo.zzb(var1, "Activity must not be null");
      this.zzaBk = var2;
   }

   @KeepForSdk
   public final void onFailure(@NonNull Status var1) {
      if (var1.hasResolution()) {
         try {
            var1.startResolutionForResult(this.mActivity, this.zzaBk);
         } catch (SendIntentException var3) {
            Log.e("ResolvingResultCallback", "Failed to start resolution", var3);
            this.onUnresolvableFailure(new Status(8));
         }
      } else {
         this.onUnresolvableFailure(var1);
      }
   }

   public abstract void onSuccess(@NonNull Result var1);

   public abstract void onUnresolvableFailure(@NonNull Status var1);
}
