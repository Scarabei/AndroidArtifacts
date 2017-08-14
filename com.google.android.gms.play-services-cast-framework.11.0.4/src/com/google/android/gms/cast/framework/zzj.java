package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzj extends zzee implements zzi {
   public zzj() {
      this.attachInterface(this, "com.google.android.gms.cast.framework.ICastConnectionController");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var7;
         switch(var1) {
         case 1:
            var7 = var2.readString();
            String var8 = var2.readString();
            this.zzt(var7, var8);
            var3.writeNoException();
            break;
         case 2:
            var7 = var2.readString();
            LaunchOptions var6 = (LaunchOptions)zzef.zza(var2, LaunchOptions.CREATOR);
            this.zza(var7, var6);
            var3.writeNoException();
            break;
         case 3:
            var7 = var2.readString();
            this.zzcc(var7);
            var3.writeNoException();
            break;
         case 4:
            int var5 = var2.readInt();
            this.zzY(var5);
            var3.writeNoException();
            break;
         case 5:
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
