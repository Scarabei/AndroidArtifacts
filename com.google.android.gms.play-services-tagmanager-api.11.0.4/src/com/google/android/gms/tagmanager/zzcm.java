package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzcm extends zzed implements zzck {
   zzcm(IBinder var1) {
      super(var1, "com.google.android.gms.tagmanager.IMeasurementInterceptor");
   }

   public final void interceptEvent(String var1, String var2, Bundle var3, long var4) throws RemoteException {
      Parcel var6;
      (var6 = this.zzZ()).writeString(var1);
      var6.writeString(var2);
      zzef.zza(var6, var3);
      var6.writeLong(var4);
      this.zzb(2, var6);
   }
}
