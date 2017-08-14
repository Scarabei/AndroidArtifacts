package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.Tile;

public abstract class zzaa extends zzee implements zzz {
   public zzaa() {
      this.attachInterface(this, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
   }

   public static zzz zzaj(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzz)((var1 = var0.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate")) instanceof zzz ? (zzz)var1 : new zzab(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         int var5 = var2.readInt();
         int var6 = var2.readInt();
         int var7 = var2.readInt();
         Tile var8 = this.getTile(var5, var6, var7);
         var3.writeNoException();
         zzef.zzb(var3, var8);
         return true;
      } else {
         return false;
      }
   }
}
