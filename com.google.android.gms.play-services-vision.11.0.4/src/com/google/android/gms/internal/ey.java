package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public final class ey extends zzed implements ex {
   ey(IBinder var1) {
      super(var1, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
   }

   public final Barcode[] zza(IObjectWrapper var1, fc var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      Barcode[] var5 = (Barcode[])(var4 = this.zza(1, var3)).createTypedArray(Barcode.CREATOR);
      var4.recycle();
      return var5;
   }

   public final Barcode[] zzb(IObjectWrapper var1, fc var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      Barcode[] var5 = (Barcode[])(var4 = this.zza(2, var3)).createTypedArray(Barcode.CREATOR);
      var4.recycle();
      return var5;
   }

   public final void zzDP() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }
}
