package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzad extends zzee implements zzac {
   public zzad() {
      this.attachInterface(this, "com.google.android.gms.cast.framework.ISessionProxy");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Bundle var8;
         switch(var1) {
         case 1:
            IObjectWrapper var9 = this.zznz();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 2:
            var8 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.start(var8);
            var3.writeNoException();
            break;
         case 3:
            var8 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.resume(var8);
            var3.writeNoException();
            break;
         case 4:
            boolean var7 = zzef.zza(var2);
            this.end(var7);
            var3.writeNoException();
            break;
         case 5:
            long var5 = this.getSessionRemainingTimeMs();
            var3.writeNoException();
            var3.writeLong(var5);
            break;
         case 6:
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
