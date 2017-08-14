package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

public abstract class zzau extends zzee implements zzat {
   public zzau() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerDragListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         com.google.android.gms.maps.model.internal.zzp var5;
         switch(var1) {
         case 1:
            var5 = com.google.android.gms.maps.model.internal.zzq.zzaf(var2.readStrongBinder());
            this.zzb(var5);
            break;
         case 2:
            var5 = com.google.android.gms.maps.model.internal.zzq.zzaf(var2.readStrongBinder());
            this.zzd(var5);
            break;
         case 3:
            var5 = com.google.android.gms.maps.model.internal.zzq.zzaf(var2.readStrongBinder());
            this.zzc(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
