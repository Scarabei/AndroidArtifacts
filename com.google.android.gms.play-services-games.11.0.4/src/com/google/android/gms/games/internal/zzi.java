package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzi extends zzee implements zzh {
   public zzi() {
      this.attachInterface(this, "com.google.android.gms.games.internal.IGamesClient");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1001) {
         zzl var5 = this.zzur();
         var3.writeNoException();
         zzef.zzb(var3, var5);
         return true;
      } else {
         return false;
      }
   }
}
