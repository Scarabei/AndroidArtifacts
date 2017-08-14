package com.google.android.gms.plus.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzao;
import com.google.android.gms.common.internal.zzap;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import java.util.List;

public final class zzg extends zzed implements zzf {
   zzg(IBinder var1) {
      super(var1, "com.google.android.gms.plus.internal.IPlusService");
   }

   public final String getAccountName() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(5, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void zzAe() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final zzao zza(zzb var1, int var2, int var3, int var4, String var5) throws RemoteException {
      Parcel var6;
      zzef.zza(var6 = this.zzZ(), var1);
      var6.writeInt(var2);
      var6.writeInt(var3);
      var6.writeInt(-1);
      var6.writeString(var5);
      Parcel var7;
      zzao var8 = zzap.zzH((var7 = this.zza(16, var6)).readStrongBinder());
      var7.recycle();
      return var8;
   }

   public final void zza(zzb var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(19, var2);
   }

   public final void zza(zzb var1, List var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeStringList(var2);
      this.zzb(34, var3);
   }
}
