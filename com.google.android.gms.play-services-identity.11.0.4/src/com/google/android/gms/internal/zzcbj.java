package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.identity.intents.UserAddressRequest;

public final class zzcbj extends zzed implements zzcbi {
   zzcbj(IBinder var1) {
      super(var1, "com.google.android.gms.identity.intents.internal.IAddressService");
   }

   public final void zza(zzcbg var1, UserAddressRequest var2, Bundle var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(2, var4);
   }
}
