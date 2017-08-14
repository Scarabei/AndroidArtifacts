package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public abstract class gb extends zzee implements ga {
   public static ga zzal(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (ga)((var1 = var0.queryLocalInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate")) instanceof ga ? (ga)var1 : new gc(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         int var9;
         Bundle var15;
         IObjectWrapper var16;
         Bundle var17;
         switch(var1) {
         case 1:
            var16 = zza.zzM(var2.readStrongBinder());
            WalletFragmentOptions var14 = (WalletFragmentOptions)zzef.zza(var2, WalletFragmentOptions.CREATOR);
            var17 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zza(var16, var14, var17);
            var3.writeNoException();
            break;
         case 2:
            var15 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.onCreate(var15);
            var3.writeNoException();
            break;
         case 3:
            var16 = zza.zzM(var2.readStrongBinder());
            IObjectWrapper var12 = zza.zzM(var2.readStrongBinder());
            var17 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            IObjectWrapper var8 = this.onCreateView(var16, var12, var17);
            var3.writeNoException();
            zzef.zza(var3, var8);
            break;
         case 4:
            this.onStart();
            var3.writeNoException();
            break;
         case 5:
            this.onResume();
            var3.writeNoException();
            break;
         case 6:
            this.onPause();
            var3.writeNoException();
            break;
         case 7:
            this.onStop();
            var3.writeNoException();
            break;
         case 8:
            var15 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.onSaveInstanceState(var15);
            var3.writeNoException();
            zzef.zzb(var3, var15);
            break;
         case 9:
            var9 = var2.readInt();
            int var6 = var2.readInt();
            Intent var7 = (Intent)zzef.zza(var2, Intent.CREATOR);
            this.onActivityResult(var9, var6, var7);
            var3.writeNoException();
            break;
         case 10:
            WalletFragmentInitParams var13 = (WalletFragmentInitParams)zzef.zza(var2, WalletFragmentInitParams.CREATOR);
            this.initialize(var13);
            var3.writeNoException();
            break;
         case 11:
            MaskedWalletRequest var11 = (MaskedWalletRequest)zzef.zza(var2, MaskedWalletRequest.CREATOR);
            this.updateMaskedWalletRequest(var11);
            var3.writeNoException();
            break;
         case 12:
            boolean var10 = zzef.zza(var2);
            this.setEnabled(var10);
            var3.writeNoException();
            break;
         case 13:
            var9 = this.getState();
            var3.writeNoException();
            var3.writeInt(var9);
            break;
         case 14:
            MaskedWallet var5 = (MaskedWallet)zzef.zza(var2, MaskedWallet.CREATOR);
            this.updateMaskedWallet(var5);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
