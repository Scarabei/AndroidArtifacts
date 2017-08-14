package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzr extends zzed implements zzp {
   zzr(IBinder var1) {
      super(var1, "com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
   }

   public final void zzG(Status var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
