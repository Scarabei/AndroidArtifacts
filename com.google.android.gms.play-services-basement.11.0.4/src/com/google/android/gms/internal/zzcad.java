package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzcad extends zzee implements zzcac {
   public zzcad() {
      this.attachInterface(this, "com.google.android.gms.flags.IFlagProvider");
   }

   public static zzcac asInterface(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzcac)((var1 = var0.queryLocalInterface("com.google.android.gms.flags.IFlagProvider")) instanceof zzcac ? (zzcac)var1 : new zzcae(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         int var7;
         int var15;
         switch(var1) {
         case 1:
            IObjectWrapper var11 = IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.init(var11);
            var3.writeNoException();
            break;
         case 2:
            var5 = var2.readString();
            boolean var14 = zzef.zza(var2);
            var7 = var2.readInt();
            boolean var16 = this.getBooleanFlagValue(var5, var14, var7);
            var3.writeNoException();
            zzef.zza(var3, var16);
            break;
         case 3:
            var5 = var2.readString();
            int var13 = var2.readInt();
            var7 = var2.readInt();
            var15 = this.getIntFlagValue(var5, var13, var7);
            var3.writeNoException();
            var3.writeInt(var15);
            break;
         case 4:
            var5 = var2.readString();
            long var12 = var2.readLong();
            var15 = var2.readInt();
            long var9 = this.getLongFlagValue(var5, var12, var15);
            var3.writeNoException();
            var3.writeLong(var9);
            break;
         case 5:
            var5 = var2.readString();
            String var6 = var2.readString();
            var7 = var2.readInt();
            String var8 = this.getStringFlagValue(var5, var6, var7);
            var3.writeNoException();
            var3.writeString(var8);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
