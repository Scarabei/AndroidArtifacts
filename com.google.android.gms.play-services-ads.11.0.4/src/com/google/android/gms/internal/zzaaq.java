package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzaaq extends zzee implements zzaap {
   public zzaaq() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.request.IAdResponseListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         zzaai var5 = (zzaai)zzef.zza(var2, zzaai.CREATOR);
         this.zza(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
