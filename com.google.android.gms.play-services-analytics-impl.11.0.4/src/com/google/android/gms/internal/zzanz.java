package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public final class zzanz extends zzed implements zzany {
   zzanz(IBinder var1) {
      super(var1, "com.google.android.gms.analytics.internal.IAnalyticsService");
   }

   public final void zza(Map var1, long var2, String var4, List var5) throws RemoteException {
      Parcel var6;
      (var6 = this.zzZ()).writeMap(var1);
      var6.writeLong(var2);
      var6.writeString(var4);
      var6.writeTypedList(var5);
      this.zzb(1, var6);
   }

   public final void zzkk() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }
}
