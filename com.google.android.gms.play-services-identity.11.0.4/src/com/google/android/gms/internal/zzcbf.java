package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

public final class zzcbf extends zzcbh {
   private final int zzaBk;
   private Activity mActivity;

   public zzcbf(int var1, Activity var2) {
      this.zzaBk = var1;
      this.mActivity = var2;
   }

   public final void zze(int var1, Bundle var2) {
      if (var1 == 1) {
         Intent var9;
         (var9 = new Intent()).putExtras(var2);
         PendingIntent var10;
         if ((var10 = this.mActivity.createPendingResult(this.zzaBk, var9, 1073741824)) != null) {
            try {
               var10.send(1);
            } catch (CanceledException var6) {
               Log.w("AddressClientImpl", "Exception settng pending result", var6);
            }
         }
      } else {
         PendingIntent var3 = null;
         if (var2 != null) {
            var3 = (PendingIntent)var2.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
         }

         ConnectionResult var4;
         if ((var4 = new ConnectionResult(var1, var3)).hasResolution()) {
            try {
               var4.startResolutionForResult(this.mActivity, this.zzaBk);
            } catch (SendIntentException var7) {
               Log.w("AddressClientImpl", "Exception starting pending intent", var7);
            }
         } else {
            try {
               PendingIntent var5;
               if ((var5 = this.mActivity.createPendingResult(this.zzaBk, new Intent(), 1073741824)) != null) {
                  var5.send(1);
               }

            } catch (CanceledException var8) {
               Log.w("AddressClientImpl", "Exception setting pending result", var8);
            }
         }
      }
   }

   private final void setActivity(Activity var1) {
      this.mActivity = null;
   }

   // $FF: synthetic method
   static void zza(zzcbf var0, Activity var1) {
      var0.setActivity((Activity)null);
   }
}
