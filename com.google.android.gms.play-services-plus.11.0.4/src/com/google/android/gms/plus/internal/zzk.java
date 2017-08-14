package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbaz;

final class zzk extends zza {
   private final zzbaz zzbiT;

   public zzk(zzbaz var1) {
      this.zzbiT = var1;
   }

   public final void zzf(int var1, Bundle var2) {
      PendingIntent var3 = null;
      if (var2 != null) {
         var3 = (PendingIntent)var2.getParcelable("pendingIntent");
      }

      Status var4 = new Status(var1, (String)null, var3);
      this.zzbiT.setResult(var4);
   }
}
