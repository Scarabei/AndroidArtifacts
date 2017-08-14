package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

public abstract class zzjv extends zzee implements zzju {
   public zzjv() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         IBinder var8;
         IInterface var9;
         Object var11;
         switch(var1) {
         case 1:
            zzjr var17 = this.zzaZ();
            var3.writeNoException();
            zzef.zza(var3, var17);
            break;
         case 2:
            var11 = (var8 = var2.readStrongBinder()) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener")) instanceof zzjo ? (zzjo)var9 : new zzjq(var8));
            this.zzb((zzjo)var11);
            var3.writeNoException();
            break;
         case 3:
            zzpn var16 = zzpo.zzl(var2.readStrongBinder());
            this.zza(var16);
            var3.writeNoException();
            break;
         case 4:
            zzpq var15 = zzpr.zzm(var2.readStrongBinder());
            this.zza(var15);
            var3.writeNoException();
            break;
         case 5:
            String var13 = var2.readString();
            zzpw var14 = zzpx.zzo(var2.readStrongBinder());
            zzpt var7 = zzpu.zzn(var2.readStrongBinder());
            this.zza(var13, var14, var7);
            var3.writeNoException();
            break;
         case 6:
            zzon var12 = (zzon)zzef.zza(var2, zzon.CREATOR);
            this.zza(var12);
            var3.writeNoException();
            break;
         case 7:
            var11 = (var8 = var2.readStrongBinder()) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider")) instanceof zzkk ? (zzkk)var9 : new zzkm(var8));
            this.zzb((zzkk)var11);
            var3.writeNoException();
            break;
         case 8:
            zzpz var10 = zzqa.zzp(var2.readStrongBinder());
            zziv var6 = (zziv)zzef.zza(var2, zziv.CREATOR);
            this.zza(var10, var6);
            var3.writeNoException();
            break;
         case 9:
            PublisherAdViewOptions var5 = (PublisherAdViewOptions)zzef.zza(var2, PublisherAdViewOptions.CREATOR);
            this.zza(var5);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
