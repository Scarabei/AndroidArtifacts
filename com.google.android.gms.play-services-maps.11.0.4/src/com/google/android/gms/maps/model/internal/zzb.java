package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzb extends zzee implements zza {
   public static zza zzaa(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zza)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate")) instanceof zza ? (zza)var1 : new zzc(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         IObjectWrapper var6;
         switch(var1) {
         case 1:
            int var10 = var2.readInt();
            var6 = this.zzbo(var10);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 2:
            var5 = var2.readString();
            var6 = this.zzdC(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 3:
            var5 = var2.readString();
            var6 = this.zzdD(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 4:
            IObjectWrapper var9 = this.zzwl();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 5:
            float var8 = var2.readFloat();
            var6 = this.zze(var8);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 6:
            Bitmap var7 = (Bitmap)zzef.zza(var2, Bitmap.CREATOR);
            var6 = this.zzd(var7);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 7:
            var5 = var2.readString();
            var6 = this.zzdE(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
