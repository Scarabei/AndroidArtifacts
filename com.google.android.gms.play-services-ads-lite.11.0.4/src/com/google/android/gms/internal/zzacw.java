package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzacw extends zzee implements zzacv {
   public zzacw() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardItem");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            String var6 = this.getType();
            var3.writeNoException();
            var3.writeString(var6);
            break;
         case 2:
            int var5 = this.getAmount();
            var3.writeNoException();
            var3.writeInt(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
