package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;

public final class zzac extends zzed implements zzaa {
   zzac(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.messages.internal.ISubscribeCallback");
   }

   public final void onExpired() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzc(1, var1);
   }
}
