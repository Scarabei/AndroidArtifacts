package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.fc;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzf extends zzed implements zze {
   zzf(IBinder var1) {
      super(var1, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
   }

   public final FaceParcel[] zzc(IObjectWrapper var1, fc var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      FaceParcel[] var5 = (FaceParcel[])(var4 = this.zza(1, var3)).createTypedArray(FaceParcel.CREATOR);
      var4.recycle();
      return var5;
   }

   public final boolean zzbN(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(2, var2));
      var3.recycle();
      return var4;
   }

   public final void zzDP() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }
}
