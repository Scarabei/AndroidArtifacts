package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzwy extends zzee implements zzwx {
   public zzwy() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
   }

   public static zzwx zzr(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzwx)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay")) instanceof zzwx ? (zzwx)var1 : new zzwz(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Bundle var10;
         switch(var1) {
         case 1:
            var10 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.onCreate(var10);
            var3.writeNoException();
            break;
         case 2:
            this.onRestart();
            var3.writeNoException();
            break;
         case 3:
            this.onStart();
            var3.writeNoException();
            break;
         case 4:
            this.onResume();
            var3.writeNoException();
            break;
         case 5:
            this.onPause();
            var3.writeNoException();
            break;
         case 6:
            var10 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.onSaveInstanceState(var10);
            var3.writeNoException();
            zzef.zzb(var3, var10);
            break;
         case 7:
            this.onStop();
            var3.writeNoException();
            break;
         case 8:
            this.onDestroy();
            var3.writeNoException();
            break;
         case 9:
            this.zzaa();
            var3.writeNoException();
            break;
         case 10:
            this.onBackPressed();
            var3.writeNoException();
            break;
         case 11:
            boolean var9 = this.zzfK();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 12:
            int var8 = var2.readInt();
            int var6 = var2.readInt();
            Intent var7 = (Intent)zzef.zza(var2, Intent.CREATOR);
            this.onActivityResult(var8, var6, var7);
            var3.writeNoException();
            break;
         case 13:
            IObjectWrapper var5 = zza.zzM(var2.readStrongBinder());
            this.zzo(var5);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
