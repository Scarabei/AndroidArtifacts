package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzba extends zzed implements zzay {
   zzba(IBinder var1) {
      super(var1, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
   }

   public final IObjectWrapper zzrF() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = IObjectWrapper.zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final IObjectWrapper zzrG() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = IObjectWrapper.zza.zzM((var2 = this.zza(2, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final boolean zze(String var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, (IInterface)var2);
      Parcel var4;
      boolean var5 = zzef.zza(var4 = this.zza(3, var3));
      var4.recycle();
      return var5;
   }

   public final boolean zzf(String var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeString(var1);
      zzef.zza(var3, (IInterface)var2);
      Parcel var4;
      boolean var5 = zzef.zza(var4 = this.zza(4, var3));
      var4.recycle();
      return var5;
   }

   public final boolean zza(com.google.android.gms.common.zzm var1, IObjectWrapper var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), (Parcelable)var1);
      zzef.zza(var3, (IInterface)var2);
      Parcel var4;
      boolean var5 = zzef.zza(var4 = this.zza(5, var3));
      var4.recycle();
      return var5;
   }
}
