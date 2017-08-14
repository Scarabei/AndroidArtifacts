package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcqd extends zzed implements zzcqc {
   zzcqd(IBinder var1) {
      super(var1, "com.google.android.gms.panorama.internal.IPanoramaService");
   }

   public final void zza(zzcqa var1, Uri var2, Bundle var3, boolean var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      this.zzc(1, var5);
   }
}
