package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzy extends zzee implements zzx {
   public zzy() {
      this.attachInterface(this, "com.google.android.gms.nearby.messages.internal.IStatusCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         boolean var5 = zzef.zza(var2);
         this.onPermissionChanged(var5);
         return true;
      } else {
         return false;
      }
   }
}
