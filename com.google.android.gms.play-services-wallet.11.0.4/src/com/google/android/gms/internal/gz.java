package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.zzk;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public final class gz extends zzp {
   private static gz zzbQF;

   public static ga zza(Activity var0, zzk var1, WalletFragmentOptions var2, gd var3) throws GooglePlayServicesNotAvailableException {
      int var4;
      if ((var4 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(var0)) != 0) {
         throw new GooglePlayServicesNotAvailableException(var4);
      } else {
         try {
            if (zzbQF == null) {
               zzbQF = new gz();
            }

            return ((gh)zzbQF.zzaS(var0)).zza(zzn.zzw(var0), var1, var2, var3);
         } catch (RemoteException var6) {
            throw new RuntimeException(var6);
         } catch (zzq var7) {
            throw new RuntimeException(var7);
         }
      }
   }

   protected gz() {
      super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
   }

   // $FF: synthetic method
   protected final Object zzb(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (var3 = var1.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator")) instanceof gh ? (gh)var3 : new gi(var1);
      }
   }
}
