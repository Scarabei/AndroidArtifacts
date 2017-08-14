package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationStatusCodes;

final class zzcdk extends zzccy {
   private zzbaz zzbiT;

   public zzcdk(zzbaz var1) {
      this.zzbiT = var1;
   }

   public final void zza(int var1, String[] var2) {
      if (this.zzbiT == null) {
         Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
      } else {
         Status var3 = LocationStatusCodes.zzbk(LocationStatusCodes.zzbj(var1));
         this.zzbiT.setResult(var3);
         this.zzbiT = null;
      }
   }

   public final void zzb(int var1, String[] var2) {
      Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
   }

   public final void zza(int var1, PendingIntent var2) {
      Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
   }
}
