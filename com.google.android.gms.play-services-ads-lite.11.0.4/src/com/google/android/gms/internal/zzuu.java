package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import java.util.ArrayList;

public abstract class zzuu extends zzee implements zzut {
   public zzuu() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var8;
         Object var9;
         IBinder var12;
         IInterface var13;
         boolean var14;
         IObjectWrapper var16;
         String var17;
         zzir var18;
         zzir var19;
         Bundle var20;
         String var21;
         zziv var22;
         zzir var25;
         String var28;
         switch(var1) {
         case 1:
            var16 = zza.zzM(var2.readStrongBinder());
            var22 = (zziv)zzef.zza(var2, zziv.CREATOR);
            var25 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var8 = var2.readString();
            var9 = (var12 = var2.readStrongBinder()) == null ? null : ((var13 = var12.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener")) instanceof zzuw ? (zzuw)var13 : new zzuy(var12));
            this.zza(var16, var22, var25, var8, (zzuw)var9);
            var3.writeNoException();
            break;
         case 2:
            var16 = this.getView();
            var3.writeNoException();
            zzef.zza(var3, var16);
            break;
         case 3:
            var16 = zza.zzM(var2.readStrongBinder());
            var18 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var21 = var2.readString();
            Object var27 = (var12 = var2.readStrongBinder()) == null ? null : ((var13 = var12.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener")) instanceof zzuw ? (zzuw)var13 : new zzuy(var12));
            this.zza(var16, var18, var21, (zzuw)var27);
            var3.writeNoException();
            break;
         case 4:
            this.showInterstitial();
            var3.writeNoException();
            break;
         case 5:
            this.destroy();
            var3.writeNoException();
            break;
         case 6:
            var16 = zza.zzM(var2.readStrongBinder());
            var22 = (zziv)zzef.zza(var2, zziv.CREATOR);
            var25 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var8 = var2.readString();
            var28 = var2.readString();
            Object var29 = (var12 = var2.readStrongBinder()) == null ? null : ((var13 = var12.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener")) instanceof zzuw ? (zzuw)var13 : new zzuy(var12));
            this.zza(var16, var22, var25, var8, var28, (zzuw)var29);
            var3.writeNoException();
            break;
         case 7:
            var16 = zza.zzM(var2.readStrongBinder());
            var18 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var21 = var2.readString();
            var8 = var2.readString();
            var9 = (var12 = var2.readStrongBinder()) == null ? null : ((var13 = var12.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener")) instanceof zzuw ? (zzuw)var13 : new zzuy(var12));
            this.zza(var16, var18, var21, var8, (zzuw)var9);
            var3.writeNoException();
            break;
         case 8:
            this.pause();
            var3.writeNoException();
            break;
         case 9:
            this.resume();
            var3.writeNoException();
            break;
         case 10:
            var16 = zza.zzM(var2.readStrongBinder());
            var18 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var21 = var2.readString();
            zzaea var23 = zzaeb.zzx(var2.readStrongBinder());
            var28 = var2.readString();
            this.zza(var16, var18, var21, var23, var28);
            var3.writeNoException();
            break;
         case 11:
            var19 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var17 = var2.readString();
            this.zzc(var19, var17);
            var3.writeNoException();
            break;
         case 12:
            this.showVideo();
            var3.writeNoException();
            break;
         case 13:
            var14 = this.isInitialized();
            var3.writeNoException();
            zzef.zza(var3, var14);
            break;
         case 14:
            var16 = zza.zzM(var2.readStrongBinder());
            var18 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var21 = var2.readString();
            var8 = var2.readString();
            var9 = (var12 = var2.readStrongBinder()) == null ? null : ((var13 = var12.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener")) instanceof zzuw ? (zzuw)var13 : new zzuy(var12));
            zzon var10 = (zzon)zzef.zza(var2, zzon.CREATOR);
            ArrayList var11 = var2.createStringArrayList();
            this.zza(var16, var18, var21, var8, (zzuw)var9, var10, var11);
            var3.writeNoException();
            break;
         case 15:
            zzvc var26 = this.zzfq();
            var3.writeNoException();
            zzef.zza(var3, var26);
            break;
         case 16:
            zzvf var24 = this.zzfr();
            var3.writeNoException();
            zzef.zza(var3, var24);
            break;
         case 17:
            var20 = this.zzfs();
            var3.writeNoException();
            zzef.zzb(var3, var20);
            break;
         case 18:
            var20 = this.getInterstitialAdapterInfo();
            var3.writeNoException();
            zzef.zzb(var3, var20);
            break;
         case 19:
            var20 = this.zzft();
            var3.writeNoException();
            zzef.zzb(var3, var20);
            break;
         case 20:
            var19 = (zzir)zzef.zza(var2, zzir.CREATOR);
            var17 = var2.readString();
            var21 = var2.readString();
            this.zza(var19, var17, var21);
            var3.writeNoException();
            break;
         case 21:
            var16 = zza.zzM(var2.readStrongBinder());
            this.zzk(var16);
            var3.writeNoException();
            break;
         case 22:
            var14 = this.zzfu();
            var3.writeNoException();
            zzef.zza(var3, var14);
            break;
         case 23:
            var16 = zza.zzM(var2.readStrongBinder());
            zzaea var6 = zzaeb.zzx(var2.readStrongBinder());
            ArrayList var7 = var2.createStringArrayList();
            this.zza(var16, var6, var7);
            var3.writeNoException();
            break;
         case 24:
            zzpj var15 = this.zzfv();
            var3.writeNoException();
            zzef.zza(var3, var15);
            break;
         case 25:
            var14 = zzef.zza(var2);
            this.setImmersiveMode(var14);
            var3.writeNoException();
            break;
         case 26:
            zzks var5 = this.getVideoController();
            var3.writeNoException();
            zzef.zza(var3, var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
