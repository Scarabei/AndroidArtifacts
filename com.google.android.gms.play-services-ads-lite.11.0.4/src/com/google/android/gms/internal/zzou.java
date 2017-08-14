package com.google.android.gms.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public final class zzou extends zzed implements zzos {
   zzou(IBinder var1) {
      super(var1, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
   }

   public final IObjectWrapper zzeg() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final Uri getUri() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Uri var3 = (Uri)zzef.zza(var2 = this.zza(2, var1), Uri.CREATOR);
      var2.recycle();
      return var3;
   }

   public final double getScale() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      double var3 = (var2 = this.zza(3, var1)).readDouble();
      var2.recycle();
      return var3;
   }
}
