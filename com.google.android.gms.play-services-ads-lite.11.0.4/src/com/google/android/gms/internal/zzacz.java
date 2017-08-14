package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzacz extends zzee implements zzacy {
   public zzacz() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
   }

   public static zzacy zzv(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzacy)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd")) instanceof zzacy ? (zzacy)var1 : new zzada(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         boolean var5;
         String var8;
         IObjectWrapper var9;
         switch(var1) {
         case 1:
            zzadj var11 = (zzadj)zzef.zza(var2, zzadj.CREATOR);
            this.zza(var11);
            var3.writeNoException();
            break;
         case 2:
            this.show();
            var3.writeNoException();
            break;
         case 3:
            IBinder var6;
            IInterface var7;
            Object var10 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener")) instanceof zzadd ? (zzadd)var7 : new zzadf(var6));
            this.zza((zzadd)var10);
            var3.writeNoException();
            break;
         case 4:
            var8 = var2.readString();
            this.setUserId(var8);
            var3.writeNoException();
            break;
         case 5:
            var5 = this.isLoaded();
            var3.writeNoException();
            zzef.zza(var3, var5);
            break;
         case 6:
            this.pause();
            var3.writeNoException();
            break;
         case 7:
            this.resume();
            var3.writeNoException();
            break;
         case 8:
            this.destroy();
            var3.writeNoException();
            break;
         case 9:
            var9 = zza.zzM(var2.readStrongBinder());
            this.zzf(var9);
            var3.writeNoException();
            break;
         case 10:
            var9 = zza.zzM(var2.readStrongBinder());
            this.zzg(var9);
            var3.writeNoException();
            break;
         case 11:
            var9 = zza.zzM(var2.readStrongBinder());
            this.zzh(var9);
            var3.writeNoException();
            break;
         case 12:
            var8 = this.getMediationAdapterClassName();
            var3.writeNoException();
            var3.writeString(var8);
            break;
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         default:
            return false;
         case 34:
            var5 = zzef.zza(var2);
            this.setImmersiveMode(var5);
            var3.writeNoException();
         }

         return true;
      }
   }
}
