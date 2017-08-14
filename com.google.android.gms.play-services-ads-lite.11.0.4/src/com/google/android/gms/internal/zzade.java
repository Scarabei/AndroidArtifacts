package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzade extends zzee implements zzadd {
   public zzade() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
   }

   public static zzadd zzw(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzadd)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener")) instanceof zzadd ? (zzadd)var1 : new zzadf(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            this.onRewardedVideoAdLoaded();
            break;
         case 2:
            this.onRewardedVideoAdOpened();
            break;
         case 3:
            this.onRewardedVideoStarted();
            break;
         case 4:
            this.onRewardedVideoAdClosed();
            break;
         case 5:
            IBinder var6;
            IInterface var7;
            Object var8 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem")) instanceof zzacv ? (zzacv)var7 : new zzacx(var6));
            this.zza((zzacv)var8);
            break;
         case 6:
            this.onRewardedVideoAdLeftApplication();
            break;
         case 7:
            int var5 = var2.readInt();
            this.onRewardedVideoAdFailedToLoad(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
