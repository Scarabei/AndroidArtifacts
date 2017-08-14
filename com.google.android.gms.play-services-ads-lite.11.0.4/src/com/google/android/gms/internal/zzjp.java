package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzjp extends zzee implements zzjo {
   public zzjp() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IAdListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            this.onAdClosed();
            break;
         case 2:
            int var5 = var2.readInt();
            this.onAdFailedToLoad(var5);
            break;
         case 3:
            this.onAdLeftApplication();
            break;
         case 4:
            this.onAdLoaded();
            break;
         case 5:
            this.onAdOpened();
            break;
         case 6:
            this.onAdClicked();
            break;
         case 7:
            this.onAdImpression();
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
