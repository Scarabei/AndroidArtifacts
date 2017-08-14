package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzz extends zzed implements zzx {
   zzz(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.messages.internal.IStatusCallback");
   }

   public final void onPermissionChanged(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
