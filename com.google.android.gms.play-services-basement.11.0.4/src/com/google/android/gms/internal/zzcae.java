package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzcae extends zzed implements zzcac {
   zzcae(IBinder var1) {
      super(var1, "com.google.android.gms.flags.IFlagProvider");
   }

   public final void init(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), (IInterface)var1);
      this.zzb(1, var2);
   }

   public final boolean getBooleanFlagValue(String var1, boolean var2, int var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      zzef.zza(var4, var2);
      var4.writeInt(var3);
      Parcel var5;
      boolean var6 = zzef.zza(var5 = this.zza(2, var4));
      var5.recycle();
      return var6;
   }

   public final int getIntFlagValue(String var1, int var2, int var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeInt(var2);
      var4.writeInt(var3);
      Parcel var5;
      int var6 = (var5 = this.zza(3, var4)).readInt();
      var5.recycle();
      return var6;
   }

   public final long getLongFlagValue(String var1, long var2, int var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeString(var1);
      var5.writeLong(var2);
      var5.writeInt(var4);
      Parcel var6;
      long var7 = (var6 = this.zza(4, var5)).readLong();
      var6.recycle();
      return var7;
   }

   public final String getStringFlagValue(String var1, String var2, int var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      var4.writeString(var2);
      var4.writeInt(var3);
      Parcel var5;
      String var6 = (var5 = this.zza(5, var4)).readString();
      var5.recycle();
      return var6;
   }
}
