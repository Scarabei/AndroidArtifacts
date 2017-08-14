package com.google.android.gms.tagmanager;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import java.util.Map;

public final class zzcg extends zzed implements zzce {
   zzcg(IBinder var1) {
      super(var1, "com.google.android.gms.tagmanager.ICustomEvaluatorProxy");
   }

   public final void zze(String var1, Map var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeMap(var2);
      this.zzb(1, var3);
   }

   public final String zzf(String var1, Map var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeMap(var2);
      Parcel var4;
      String var5 = (var4 = this.zza(2, var3)).readString();
      var4.recycle();
      return var5;
   }
}
