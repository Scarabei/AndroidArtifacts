package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public final class zzfa extends zzed implements zzey {
   zzfa(IBinder var1) {
      super(var1, "com.google.android.gms.ads.adshield.internal.IAdShieldClient");
   }

   public final String zzaf() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(1, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void zzb(String var1, String var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      var3.writeString(var2);
      this.zzb(2, var3);
   }

   public final boolean zza(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(3, var2));
      var3.recycle();
      return var4;
   }

   public final boolean zzb(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(4, var2));
      var3.recycle();
      return var4;
   }

   public final void zzk(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(5, var2);
   }

   public final IObjectWrapper zza(IObjectWrapper var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IObjectWrapper var5 = zza.zzM((var4 = this.zza(6, var3)).readStrongBinder());
      var4.recycle();
      return var5;
   }

   public final String zzc(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      String var4 = (var3 = this.zza(7, var2)).readString();
      var3.recycle();
      return var4;
   }

   public final String zza(IObjectWrapper var1, String var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeString(var2);
      Parcel var4;
      String var5 = (var4 = this.zza(8, var3)).readString();
      var4.recycle();
      return var5;
   }

   public final void zzd(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(9, var2);
   }

   public final IObjectWrapper zzb(IObjectWrapper var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IObjectWrapper var5 = zza.zzM((var4 = this.zza(10, var3)).readStrongBinder());
      var4.recycle();
      return var5;
   }

   public final boolean zzb(String var1, boolean var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, var2);
      Parcel var4;
      boolean var5 = zzef.zza(var4 = this.zza(11, var3));
      var4.recycle();
      return var5;
   }

   public final String zza(IObjectWrapper var1, byte[] var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeByteArray(var2);
      Parcel var4;
      String var5 = (var4 = this.zza(12, var3)).readString();
      var4.recycle();
      return var5;
   }
}
