package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzxp extends zzee implements zzxo {
   public static zzxo zzu(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzxo)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener")) instanceof zzxo ? (zzxo)var1 : new zzxq(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            String var9 = var2.readString();
            boolean var6 = this.zzas(var9);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 2:
            IBinder var7;
            IInterface var8;
            Object var5 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult")) instanceof zzxm ? (zzxm)var8 : new zzxn(var7));
            this.zza((zzxm)var5);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
