package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzki extends zzee implements zzkh {
   public zzki() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IClientApi");
   }

   public static zzkh asInterface(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzkh)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi")) instanceof zzkh ? (zzkh)var1 : new zzkj(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         zziv var6;
         String var7;
         int var8;
         zzjz var10;
         zzuq var23;
         int var25;
         switch(var1) {
         case 1:
            var5 = zza.zzM(var2.readStrongBinder());
            var6 = (zziv)zzef.zza(var2, zziv.CREATOR);
            var7 = var2.readString();
            var23 = zzur.zzq(var2.readStrongBinder());
            var25 = var2.readInt();
            var10 = this.createBannerAdManager(var5, var6, var7, var23, var25);
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 2:
            var5 = zza.zzM(var2.readStrongBinder());
            var6 = (zziv)zzef.zza(var2, zziv.CREATOR);
            var7 = var2.readString();
            var23 = zzur.zzq(var2.readStrongBinder());
            var25 = var2.readInt();
            var10 = this.createInterstitialAdManager(var5, var6, var7, var23, var25);
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 3:
            var5 = zza.zzM(var2.readStrongBinder());
            String var20 = var2.readString();
            zzuq var21 = zzur.zzq(var2.readStrongBinder());
            var8 = var2.readInt();
            zzju var24 = this.createAdLoaderBuilder(var5, var20, var21, var8);
            var3.writeNoException();
            zzef.zza(var3, var24);
            break;
         case 4:
            var5 = zza.zzM(var2.readStrongBinder());
            zzkn var18 = this.getMobileAdsSettingsManager(var5);
            var3.writeNoException();
            zzef.zza(var3, var18);
            break;
         case 5:
            var5 = zza.zzM(var2.readStrongBinder());
            IObjectWrapper var16 = zza.zzM(var2.readStrongBinder());
            zzow var19 = this.createNativeAdViewDelegate(var5, var16);
            var3.writeNoException();
            zzef.zza(var3, var19);
            break;
         case 6:
            var5 = zza.zzM(var2.readStrongBinder());
            zzuq var14 = zzur.zzq(var2.readStrongBinder());
            int var17 = var2.readInt();
            zzacy var22 = this.createRewardedVideoAd(var5, var14, var17);
            var3.writeNoException();
            zzef.zza(var3, var22);
            break;
         case 7:
            var5 = zza.zzM(var2.readStrongBinder());
            zzxj var13 = this.createInAppPurchaseManager(var5);
            var3.writeNoException();
            zzef.zza(var3, var13);
            break;
         case 8:
            var5 = zza.zzM(var2.readStrongBinder());
            zzwx var12 = this.createAdOverlay(var5);
            var3.writeNoException();
            zzef.zza(var3, var12);
            break;
         case 9:
            var5 = zza.zzM(var2.readStrongBinder());
            int var11 = var2.readInt();
            zzkn var15 = this.getMobileAdsSettingsManagerWithClientJarVersion(var5, var11);
            var3.writeNoException();
            zzef.zza(var3, var15);
            break;
         case 10:
            var5 = zza.zzM(var2.readStrongBinder());
            var6 = (zziv)zzef.zza(var2, zziv.CREATOR);
            var7 = var2.readString();
            var8 = var2.readInt();
            zzjz var9 = this.createSearchAdManager(var5, var6, var7, var8);
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
