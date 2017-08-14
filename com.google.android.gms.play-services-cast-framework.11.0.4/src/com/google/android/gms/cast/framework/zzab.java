package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzab extends zzee implements zzaa {
   public zzab() {
      this.attachInterface(this, "com.google.android.gms.cast.framework.ISessionProvider");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         switch(var1) {
         case 1:
            var5 = var2.readString();
            IObjectWrapper var6 = this.zzcd(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 2:
            boolean var7 = this.isSessionRecoverable();
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 3:
            var5 = this.getCategory();
            var3.writeNoException();
            var3.writeString(var5);
            break;
         case 4:
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
