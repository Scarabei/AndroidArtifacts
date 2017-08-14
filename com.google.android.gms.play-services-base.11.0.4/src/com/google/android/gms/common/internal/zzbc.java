package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzbc extends zzed implements zzbb {
   zzbc(IBinder var1) {
      super(var1, "com.google.android.gms.common.internal.ISignInButtonCreator");
   }

   public final IObjectWrapper zza(IObjectWrapper var1, zzbt var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IObjectWrapper var5 = zza.zzM((var4 = this.zza(2, var3)).readStrongBinder());
      var4.recycle();
      return var5;
   }
}
