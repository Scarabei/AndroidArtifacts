package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzas extends zzee implements zzar {
   public zzas() {
      this.attachInterface(this, "com.google.android.gms.common.internal.ICertData");
   }

   public static zzar zzI(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzar)((var1 = var0.queryLocalInterface("com.google.android.gms.common.internal.ICertData")) instanceof zzar ? (zzar)var1 : new zzat(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            IObjectWrapper var6 = this.zzoY();
            var3.writeNoException();
            zzef.zza(var3, (IInterface)var6);
            break;
         case 2:
            int var5 = this.zzoZ();
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
