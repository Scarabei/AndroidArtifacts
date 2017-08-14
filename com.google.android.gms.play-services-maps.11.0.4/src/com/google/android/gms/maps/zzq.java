package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.internal.zzbr;

final class zzq extends zzbr {
   // $FF: synthetic field
   private GoogleMap.SnapshotReadyCallback zzblP;

   zzq(GoogleMap var1, GoogleMap.SnapshotReadyCallback var2) {
      this.zzblP = var2;
      super();
   }

   public final void onSnapshotReady(Bitmap var1) throws RemoteException {
      this.zzblP.onSnapshotReady(var1);
   }

   public final void zzG(IObjectWrapper var1) throws RemoteException {
      this.zzblP.onSnapshotReady((Bitmap)com.google.android.gms.dynamic.zzn.zzE(var1));
   }
}
