package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import java.util.HashMap;
import java.util.Map;

public final class zzcp extends zzed implements zzcn {
   zzcp(IBinder var1) {
      super(var1, "com.google.android.gms.tagmanager.IMeasurementProxy");
   }

   public final void logEventInternalNoInterceptor(String var1, String var2, Bundle var3, long var4) throws RemoteException {
      Parcel var6;
      (var6 = this.zzZ()).writeString(var1);
      var6.writeString(var2);
      zzef.zza(var6, var3);
      var6.writeLong(var4);
      this.zzb(2, var6);
   }

   public final Map zzBh() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      HashMap var3 = zzef.zzc(var2 = this.zza(11, var1));
      var2.recycle();
      return var3;
   }

   public final void zza(zzck var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(21, var2);
   }

   public final void zza(zzch var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(22, var2);
   }
}
