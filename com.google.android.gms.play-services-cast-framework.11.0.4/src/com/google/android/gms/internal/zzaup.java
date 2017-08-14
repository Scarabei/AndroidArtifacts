package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzaup extends zzee implements zzauo {
   public zzaup() {
      this.attachInterface(this, "com.google.android.gms.cast.framework.internal.IMediaRouter");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         Bundle var11;
         int var12;
         switch(var1) {
         case 1:
            var11 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            IBinder var8;
            IInterface var9;
            Object var13 = (var8 = var2.readStrongBinder()) == null ? null : ((var9 = var8.queryLocalInterface("com.google.android.gms.cast.framework.internal.IMediaRouterCallback")) instanceof zzauq ? (zzauq)var9 : new zzaur(var8));
            this.zza(var11, (zzauq)var13);
            var3.writeNoException();
            break;
         case 2:
            var11 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            var12 = var2.readInt();
            this.zza(var11, var12);
            var3.writeNoException();
            break;
         case 3:
            var11 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzk(var11);
            var3.writeNoException();
            break;
         case 4:
            var11 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            var12 = var2.readInt();
            boolean var7 = this.zzb(var11, var12);
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 5:
            var5 = var2.readString();
            this.zzce(var5);
            var3.writeNoException();
            break;
         case 6:
            this.zznI();
            var3.writeNoException();
            break;
         case 7:
            boolean var10 = this.zznJ();
            var3.writeNoException();
            zzef.zza(var3, var10);
            break;
         case 8:
            var5 = var2.readString();
            Bundle var6 = this.zzcf(var5);
            var3.writeNoException();
            zzef.zzb(var3, var6);
            break;
         case 9:
            var5 = this.zznK();
            var3.writeNoException();
            var3.writeString(var5);
            break;
         case 10:
            var3.writeNoException();
            var3.writeInt(11020208);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
