package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzo extends zzed implements zzm {
   zzo(IBinder var1) {
      super(var1, "com.google.android.gms.location.ILocationListener");
   }

   public final void onLocationChanged(Location var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }
}
