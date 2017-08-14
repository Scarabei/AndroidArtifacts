package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzh extends zzed implements zzg {
   zzh(IBinder var1) {
      super(var1, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
   }

   public final zze zza(IObjectWrapper var1, zzc var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IBinder var6;
      IInterface var7;
      Object var5 = (var6 = (var4 = this.zza(1, var3)).readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector")) instanceof zze ? (zze)var7 : new zzf(var6));
      var4.recycle();
      return (zze)var5;
   }
}
