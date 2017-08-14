package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzazg extends zzazm {
   // $FF: synthetic field
   private zzazl zzaze;
   // $FF: synthetic field
   private zzazf zzazf;

   zzazg(zzazf var1, zzazl var2) {
      this.zzazf = var1;
      this.zzaze = var2;
      super();
   }

   public final void zzah(int var1) throws RemoteException {
      com.google.android.gms.internal.zzazf.zzoQ().zzb("onRemoteDisplayEnded");
      if (this.zzaze != null) {
         this.zzaze.zzah(var1);
      }

      if (com.google.android.gms.internal.zzazf.zzb(this.zzazf) != null) {
         com.google.android.gms.internal.zzazf.zzb(this.zzazf).onRemoteDisplayEnded(new Status(var1));
      }

   }
}
