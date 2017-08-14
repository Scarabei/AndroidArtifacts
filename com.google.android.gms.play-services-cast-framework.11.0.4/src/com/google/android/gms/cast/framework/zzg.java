package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzg extends zzee implements zzf {
   public zzg() {
      this.attachInterface(this, "com.google.android.gms.cast.framework.IAppVisibilityListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            IObjectWrapper var6 = this.zznn();
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 2:
            this.onAppEnteredForeground();
            var3.writeNoException();
            break;
         case 3:
            this.onAppEnteredBackground();
            var3.writeNoException();
            break;
         case 4:
            int var5 = this.zznm();
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
