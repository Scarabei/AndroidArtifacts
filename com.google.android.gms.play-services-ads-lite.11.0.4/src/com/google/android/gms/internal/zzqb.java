package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzqb extends zzed implements zzpz {
   zzqb(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener");
   }

   public final void zza(zzjz var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(1, var3);
   }
}
