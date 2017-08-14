package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzux extends zzee implements zzuw {
   public zzux() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var6;
         switch(var1) {
         case 1:
            this.onAdClicked();
            break;
         case 2:
            this.onAdClosed();
            break;
         case 3:
            int var11 = var2.readInt();
            this.onAdFailedToLoad(var11);
            break;
         case 4:
            this.onAdLeftApplication();
            break;
         case 5:
            this.onAdOpened();
            break;
         case 6:
            this.onAdLoaded();
            break;
         case 7:
            IBinder var7;
            IInterface var8;
            Object var10 = (var7 = var2.readStrongBinder()) == null ? null : ((var8 = var7.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata")) instanceof zzuz ? (zzuz)var8 : new zzvb(var7));
            this.zza((zzuz)var10);
            break;
         case 8:
            this.onAdImpression();
            break;
         case 9:
            String var9 = var2.readString();
            var6 = var2.readString();
            this.onAppEvent(var9, var6);
            break;
         case 10:
            zzpj var5 = zzpk.zzk(var2.readStrongBinder());
            var6 = var2.readString();
            this.zzb(var5, var6);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
