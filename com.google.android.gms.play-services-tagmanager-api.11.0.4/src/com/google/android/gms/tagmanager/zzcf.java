package com.google.android.gms.tagmanager;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import java.util.HashMap;

public abstract class zzcf extends zzee implements zzce {
   public zzcf() {
      this.attachInterface(this, "com.google.android.gms.tagmanager.ICustomEvaluatorProxy");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         HashMap var6;
         switch(var1) {
         case 1:
            var5 = var2.readString();
            var6 = zzef.zzc(var2);
            this.zze(var5, var6);
            var3.writeNoException();
            break;
         case 2:
            var5 = var2.readString();
            var6 = zzef.zzc(var2);
            String var7 = this.zzf(var5, var6);
            var3.writeNoException();
            var3.writeString(var7);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
