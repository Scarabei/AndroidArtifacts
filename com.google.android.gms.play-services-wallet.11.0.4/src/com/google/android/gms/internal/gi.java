package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzk;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public final class gi extends zzed implements gh {
   gi(IBinder var1) {
      super(var1, "com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
   }

   public final ga zza(IObjectWrapper var1, zzk var2, WalletFragmentOptions var3, gd var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      zzef.zza(var5, var2);
      zzef.zza(var5, var3);
      zzef.zza(var5, var4);
      Parcel var6;
      ga var7 = gb.zzal((var6 = this.zza(1, var5)).readStrongBinder());
      var6.recycle();
      return var7;
   }
}
