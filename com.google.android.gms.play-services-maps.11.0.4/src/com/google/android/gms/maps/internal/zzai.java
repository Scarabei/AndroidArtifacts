package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzai extends zzed implements zzah {
   zzai(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
   }

   public final void zzd(Location var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }
}
