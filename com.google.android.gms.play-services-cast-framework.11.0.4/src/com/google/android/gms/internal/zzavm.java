package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzavm extends zzee implements zzavl {
   public zzavm() {
      this.attachInterface(this, "com.google.android.gms.cast.framework.media.internal.IFetchBitmapTaskProgressPublisher");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            long var5 = var2.readLong();
            long var7 = var2.readLong();
            this.zzb(var5, var7);
            var3.writeNoException();
            break;
         case 2:
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
