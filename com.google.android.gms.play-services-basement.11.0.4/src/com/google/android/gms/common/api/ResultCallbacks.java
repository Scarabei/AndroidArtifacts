package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

public abstract class ResultCallbacks implements ResultCallback {
   @KeepForSdk
   public final void onResult(@NonNull Result var1) {
      Status var2;
      if ((var2 = var1.getStatus()).isSuccess()) {
         this.onSuccess(var1);
      } else {
         this.onFailure(var2);
         if (var1 instanceof Releasable) {
            try {
               ((Releasable)var1).release();
               return;
            } catch (RuntimeException var5) {
               String var4 = String.valueOf(var1);
               Log.w("ResultCallbacks", (new StringBuilder(18 + String.valueOf(var4).length())).append("Unable to release ").append(var4).toString(), var5);
            }
         }

      }
   }

   public abstract void onSuccess(@NonNull Result var1);

   public abstract void onFailure(@NonNull Status var1);
}
