package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzpv extends zzed implements zzpt {
   zzpv(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
   }

   public final void zzb(zzpj var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzb(1, var3);
   }
}
