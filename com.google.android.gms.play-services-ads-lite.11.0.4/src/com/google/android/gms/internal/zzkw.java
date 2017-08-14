package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzkw extends zzee implements zzkv {
   public zzkw() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            this.onVideoStart();
            break;
         case 2:
            this.onVideoPlay();
            break;
         case 3:
            this.onVideoPause();
            break;
         case 4:
            this.onVideoEnd();
            break;
         case 5:
            boolean var5 = zzef.zza(var2);
            this.onVideoMute(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
