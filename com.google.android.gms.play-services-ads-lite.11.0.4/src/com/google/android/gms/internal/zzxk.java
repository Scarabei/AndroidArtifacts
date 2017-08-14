package com.google.android.gms.internal;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzxk extends zzee implements zzxj {
   public static zzxj zzt(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzxj)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager")) instanceof zzxj ? (zzxj)var1 : new zzxl(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            this.onCreate();
            break;
         case 2:
            this.onDestroy();
            break;
         case 3:
            int var5 = var2.readInt();
            int var6 = var2.readInt();
            Intent var7 = (Intent)zzef.zza(var2, Intent.CREATOR);
            this.onActivityResult(var5, var6, var7);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
