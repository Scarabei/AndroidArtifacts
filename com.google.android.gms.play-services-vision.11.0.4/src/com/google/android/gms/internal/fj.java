package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class fj extends zzed implements fi {
   fj(IBinder var1) {
      super(var1, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
   }

   public final fg zza(IObjectWrapper var1, fr var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IBinder var6;
      IInterface var7;
      Object var5 = (var6 = (var4 = this.zza(1, var3)).readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer")) instanceof fg ? (fg)var7 : new fh(var6));
      var4.recycle();
      return (fg)var5;
   }
}
