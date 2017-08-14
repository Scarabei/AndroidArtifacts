package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class fh extends zzed implements fg {
   fh(IBinder var1) {
      super(var1, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
   }

   public final fk[] zza(IObjectWrapper var1, fc var2, fm var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      Parcel var5;
      fk[] var6 = (fk[])(var5 = this.zza(3, var4)).createTypedArray(fk.CREATOR);
      var5.recycle();
      return var6;
   }

   public final void zzDS() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }
}
