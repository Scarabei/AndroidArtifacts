package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzi extends zzee implements zzh {
   public zzi() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         com.google.android.gms.maps.model.internal.zzp var5;
         IObjectWrapper var6;
         switch(var1) {
         case 1:
            var5 = com.google.android.gms.maps.model.internal.zzq.zzaf(var2.readStrongBinder());
            var6 = this.zzh(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 2:
            var5 = com.google.android.gms.maps.model.internal.zzq.zzaf(var2.readStrongBinder());
            var6 = this.zzi(var5);
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
