package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzbr extends zzee implements zzbq {
   public zzbr() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.ISnapshotReadyCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            Bitmap var6 = (Bitmap)zzef.zza(var2, Bitmap.CREATOR);
            this.onSnapshotReady(var6);
            break;
         case 2:
            IObjectWrapper var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2.readStrongBinder());
            this.zzG(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
