package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcvf extends zzed implements zzcvd {
   zzcvf(IBinder var1) {
      super(var1, "com.google.android.gms.tagmanager.internal.ITagManagerLoadContainerCallback");
   }

   public final void zza(boolean var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      this.zzc(1, var3);
   }
}
