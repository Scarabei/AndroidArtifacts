package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

public abstract class zzazi extends zzee implements zzazh {
   public zzazi() {
      this.attachInterface(this, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         int var5;
         switch(var1) {
         case 1:
            var5 = var2.readInt();
            int var6 = var2.readInt();
            Surface var7 = (Surface)zzef.zza(var2, Surface.CREATOR);
            this.zza(var5, var6, var7);
            break;
         case 2:
            var5 = var2.readInt();
            this.onError(var5);
            break;
         case 3:
            this.onDisconnected();
            break;
         case 4:
            this.zzoR();
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
