package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzc extends zzed implements zza {
   zzc(IBinder var1) {
      super(var1, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
   }

   public final IObjectWrapper zzbo(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(1, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zzdC(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(2, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zzdD(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(3, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zzwl() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(4, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final IObjectWrapper zze(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(5, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zzd(Bitmap var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(6, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zzdE(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(7, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }
}
