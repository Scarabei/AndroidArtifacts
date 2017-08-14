package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzaeb extends zzee implements zzaea {
   public zzaeb() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
   }

   public static zzaea zzx(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzaea)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener")) instanceof zzaea ? (zzaea)var1 : new zzaec(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IObjectWrapper var5;
         int var6;
         switch(var1) {
         case 1:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzq(var5);
            break;
         case 2:
            var5 = zza.zzM(var2.readStrongBinder());
            var6 = var2.readInt();
            this.zzc(var5, var6);
            break;
         case 3:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzr(var5);
            break;
         case 4:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzs(var5);
            break;
         case 5:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzt(var5);
            break;
         case 6:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzu(var5);
            break;
         case 7:
            var5 = zza.zzM(var2.readStrongBinder());
            zzaee var7 = (zzaee)zzef.zza(var2, zzaee.CREATOR);
            this.zza(var5, var7);
            break;
         case 8:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzv(var5);
            break;
         case 9:
            var5 = zza.zzM(var2.readStrongBinder());
            var6 = var2.readInt();
            this.zzd(var5, var6);
            break;
         case 10:
            var5 = zza.zzM(var2.readStrongBinder());
            this.zzw(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
