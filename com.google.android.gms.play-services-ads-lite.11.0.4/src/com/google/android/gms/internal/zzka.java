package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzka extends zzee implements zzjz {
   public zzka() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IAdManager");
   }

   public static zzjz zze(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzjz)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager")) instanceof zzjz ? (zzjz)var1 : new zzkb(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         IBinder var7;
         IInterface var8;
         boolean var9;
         Object var17;
         zziv var21;
         switch(var1) {
         case 1:
            IObjectWrapper var23 = this.zzal();
            var3.writeNoException();
            zzef.zza(var3, var23);
            break;
         case 2:
            this.destroy();
            var3.writeNoException();
            break;
         case 3:
            var9 = this.isReady();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 4:
            zzir var22 = (zzir)zzef.zza(var2, zzir.CREATOR);
            boolean var12 = this.zza(var22);
            var3.writeNoException();
            zzef.zza(var3, var12);
            break;
         case 5:
            this.pause();
            var3.writeNoException();
            break;
         case 6:
            this.resume();
            var3.writeNoException();
            break;
         case 7:
            var17 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener")) instanceof zzjo ? (zzjo)var8 : new zzjq(var7));
            this.zza((zzjo)var17);
            var3.writeNoException();
            break;
         case 8:
            var17 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener")) instanceof zzke ? (zzke)var8 : new zzkg(var7));
            this.zza((zzke)var17);
            var3.writeNoException();
            break;
         case 9:
            this.showInterstitial();
            var3.writeNoException();
            break;
         case 10:
            this.stopLoading();
            var3.writeNoException();
            break;
         case 11:
            this.zzao();
            var3.writeNoException();
            break;
         case 12:
            var21 = this.zzam();
            var3.writeNoException();
            zzef.zzb(var3, var21);
            break;
         case 13:
            var21 = (zziv)zzef.zza(var2, zziv.CREATOR);
            this.zza(var21);
            var3.writeNoException();
            break;
         case 14:
            zzxg var20 = zzxh.zzs(var2.readStrongBinder());
            this.zza(var20);
            var3.writeNoException();
            break;
         case 15:
            zzxo var19 = zzxp.zzu(var2.readStrongBinder());
            String var6 = var2.readString();
            this.zza(var19, var6);
            var3.writeNoException();
            break;
         case 16:
         case 17:
         case 27:
         case 28:
         default:
            return false;
         case 18:
            var5 = this.getMediationAdapterClassName();
            var3.writeNoException();
            var3.writeString(var5);
            break;
         case 19:
            zznh var18 = zzni.zzh(var2.readStrongBinder());
            this.zza(var18);
            var3.writeNoException();
            break;
         case 20:
            var17 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener")) instanceof zzjl ? (zzjl)var8 : new zzjn(var7));
            this.zza((zzjl)var17);
            var3.writeNoException();
            break;
         case 21:
            var17 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider")) instanceof zzkk ? (zzkk)var8 : new zzkm(var7));
            this.zza((zzkk)var17);
            var3.writeNoException();
            break;
         case 22:
            var9 = zzef.zza(var2);
            this.setManualImpressionsEnabled(var9);
            var3.writeNoException();
            break;
         case 23:
            var9 = this.isLoading();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 24:
            zzadd var16 = zzade.zzw(var2.readStrongBinder());
            this.zza(var16);
            var3.writeNoException();
            break;
         case 25:
            var5 = var2.readString();
            this.setUserId(var5);
            var3.writeNoException();
            break;
         case 26:
            zzks var15 = this.getVideoController();
            var3.writeNoException();
            zzef.zza(var3, var15);
            break;
         case 29:
            zzlx var14 = (zzlx)zzef.zza(var2, zzlx.CREATOR);
            this.zza(var14);
            var3.writeNoException();
            break;
         case 30:
            zzky var13 = (zzky)zzef.zza(var2, zzky.CREATOR);
            this.zza(var13);
            var3.writeNoException();
            break;
         case 31:
            var5 = this.getAdUnitId();
            var3.writeNoException();
            var3.writeString(var5);
            break;
         case 32:
            zzke var11 = this.zzax();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 33:
            zzjo var10 = this.zzay();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 34:
            var9 = zzef.zza(var2);
            this.setImmersiveMode(var9);
            var3.writeNoException();
            break;
         case 35:
            var5 = this.zzaI();
            var3.writeNoException();
            var3.writeString(var5);
         }

         return true;
      }
   }
}
