package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.zzab;

public abstract class gk extends zzee implements gj {
   public gk() {
      this.attachInterface(this, "com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         boolean var6;
         Bundle var7;
         int var8;
         switch(var1) {
         case 1:
            var8 = var2.readInt();
            MaskedWallet var11 = (MaskedWallet)zzef.zza(var2, MaskedWallet.CREATOR);
            var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zza(var8, var11, var7);
            break;
         case 2:
            var8 = var2.readInt();
            FullWallet var10 = (FullWallet)zzef.zza(var2, FullWallet.CREATOR);
            var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zza(var8, var10, var7);
            break;
         case 3:
            var8 = var2.readInt();
            var6 = zzef.zza(var2);
            var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zza(var8, var6, var7);
            break;
         case 4:
            var8 = var2.readInt();
            Bundle var9 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzg(var8, var9);
            break;
         case 5:
         default:
            return false;
         case 6:
            var8 = var2.readInt();
            var6 = zzef.zza(var2);
            var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzb(var8, var6, var7);
            break;
         case 7:
            zzef.zza(var2, Status.CREATOR);
            zzef.zza(var2, fw.CREATOR);
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 8:
            zzef.zza(var2, Status.CREATOR);
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 9:
            Status var5 = (Status)zzef.zza(var2, Status.CREATOR);
            var6 = zzef.zza(var2);
            var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zza(var5, var6, var7);
            break;
         case 10:
            zzef.zza(var2, Status.CREATOR);
            zzef.zza(var2, fy.CREATOR);
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 11:
            zzef.zza(var2, Status.CREATOR);
            zzef.zza(var2, Bundle.CREATOR);
            break;
         case 12:
            zzef.zza(var2, Status.CREATOR);
            zzef.zza(var2, zzab.CREATOR);
            zzef.zza(var2, Bundle.CREATOR);
         }

         return true;
      }
   }
}
