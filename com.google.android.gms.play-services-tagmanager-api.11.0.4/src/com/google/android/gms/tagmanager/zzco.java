package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import java.util.Map;

public abstract class zzco extends zzee implements zzcn {
   public zzco() {
      this.attachInterface(this, "com.google.android.gms.tagmanager.IMeasurementProxy");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Object var5;
         IBinder var10;
         IInterface var11;
         switch(var1) {
         case 2:
            String var13 = var2.readString();
            String var6 = var2.readString();
            Bundle var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            long var8 = var2.readLong();
            this.logEventInternalNoInterceptor(var13, var6, var7, var8);
            var3.writeNoException();
            break;
         case 11:
            Map var12 = this.zzBh();
            var3.writeNoException();
            var3.writeMap(var12);
            break;
         case 21:
            var5 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.tagmanager.IMeasurementInterceptor")) instanceof zzck ? (zzck)var11 : new zzcm(var10));
            this.zza((zzck)var5);
            var3.writeNoException();
            break;
         case 22:
            var5 = (var10 = var2.readStrongBinder()) == null ? null : ((var11 = var10.queryLocalInterface("com.google.android.gms.tagmanager.IMeasurementEventListener")) instanceof zzch ? (zzch)var11 : new zzcj(var10));
            this.zza((zzch)var5);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
