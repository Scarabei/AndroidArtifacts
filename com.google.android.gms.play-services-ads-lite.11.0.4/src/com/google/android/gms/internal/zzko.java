package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzko extends zzee implements zzkn {
   public zzko() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var5;
         switch(var1) {
         case 1:
            this.initialize();
            break;
         case 2:
            float var9 = var2.readFloat();
            this.setAppVolume(var9);
            break;
         case 3:
            var5 = var2.readString();
            this.zzu(var5);
            break;
         case 4:
            boolean var8 = zzef.zza(var2);
            this.setAppMuted(var8);
            break;
         case 5:
            IObjectWrapper var7 = zza.zzM(var2.readStrongBinder());
            String var10 = var2.readString();
            this.zzb(var7, var10);
            break;
         case 6:
            var5 = var2.readString();
            IObjectWrapper var6 = zza.zzM(var2.readStrongBinder());
            this.zzc(var5, var6);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
