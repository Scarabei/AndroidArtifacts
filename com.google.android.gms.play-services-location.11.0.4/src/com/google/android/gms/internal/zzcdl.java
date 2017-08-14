package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationStatusCodes;

final class zzcdl extends zzccy {
   private zzbaz zzbiT;

   public zzcdl(zzbaz var1) {
      this.zzbiT = var1;
   }

   public final void zza(int var1, String[] var2) {
      Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
   }

   public final void zzb(int var1, String[] var2) {
      this.zzbl(var1);
   }

   public final void zza(int var1, PendingIntent var2) {
      this.zzbl(var1);
   }

   private final void zzbl(int var1) {
      if (this.zzbiT == null) {
         Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
      } else {
         Status var2 = LocationStatusCodes.zzbk(LocationStatusCodes.zzbj(var1));
         this.zzbiT.setResult(var2);
         this.zzbiT = null;
      }
   }
}
