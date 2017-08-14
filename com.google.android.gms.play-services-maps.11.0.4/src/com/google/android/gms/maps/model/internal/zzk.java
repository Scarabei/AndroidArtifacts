package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import java.util.List;

public abstract class zzk extends zzee implements zzj {
   public static zzj zzad(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzj)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate")) instanceof zzj ? (zzj)var1 : new zzl(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         int var5;
         switch(var1) {
         case 1:
            var5 = this.getActiveLevelIndex();
            var3.writeNoException();
            var3.writeInt(var5);
            break;
         case 2:
            var5 = this.getDefaultLevelIndex();
            var3.writeNoException();
            var3.writeInt(var5);
            break;
         case 3:
            List var11 = this.getLevels();
            var3.writeNoException();
            var3.writeBinderList(var11);
            break;
         case 4:
            boolean var10 = this.isUnderground();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 5:
            IBinder var7;
            IInterface var8;
            Object var9 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate")) instanceof zzj ? (zzj)var8 : new zzl(var7));
            boolean var6 = this.zzb((zzj)var9);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 6:
            var5 = this.hashCodeRemote();
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
