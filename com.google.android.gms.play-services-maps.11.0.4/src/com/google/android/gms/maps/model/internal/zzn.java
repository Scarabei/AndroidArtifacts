package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzn extends zzee implements zzm {
   public static zzm zzae(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzm)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate")) instanceof zzm ? (zzm)var1 : new zzo(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var10;
         switch(var1) {
         case 1:
            var10 = this.getName();
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 2:
            var10 = this.getShortName();
            var3.writeNoException();
            var3.writeString(var10);
            break;
         case 3:
            this.activate();
            var3.writeNoException();
            break;
         case 4:
            IBinder var7;
            IInterface var8;
            Object var9 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate")) instanceof zzm ? (zzm)var8 : new zzo(var7));
            boolean var6 = this.zza((zzm)var9);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 5:
            int var5 = this.hashCodeRemote();
            var3.writeNoException();
            var3.writeInt(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
