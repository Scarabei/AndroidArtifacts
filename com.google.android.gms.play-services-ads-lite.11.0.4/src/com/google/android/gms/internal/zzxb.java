package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzxb extends zzed implements zzxa {
   zzxb(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
   }

   public final IBinder zzp(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IBinder var4 = (var3 = this.zza(1, var2)).readStrongBinder();
      var3.recycle();
      return var4;
   }
}
