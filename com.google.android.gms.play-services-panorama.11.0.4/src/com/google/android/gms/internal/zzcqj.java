package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;

final class zzcqj extends zzcqb {
   private final zzbaz zzaIz;

   public zzcqj(zzbaz var1) {
      this.zzaIz = var1;
   }

   public final void zza(int var1, Bundle var2, int var3, Intent var4) {
      PendingIntent var5 = null;
      if (var2 != null) {
         var5 = (PendingIntent)var2.getParcelable("pendingIntent");
      }

      Status var6 = new Status(var1, (String)null, var5);
      this.zzaIz.setResult(new zzcqm(var6, var4));
   }
}
