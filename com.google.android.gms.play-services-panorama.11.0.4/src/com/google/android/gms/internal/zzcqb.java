package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcqb extends zzee implements zzcqa {
   public zzcqb() {
      this.attachInterface(this, "com.google.android.gms.panorama.internal.IPanoramaCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         int var5 = var2.readInt();
         Bundle var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
         int var7 = var2.readInt();
         Intent var8 = (Intent)zzef.zza(var2, Intent.CREATOR);
         this.zza(var5, var6, var7, var8);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
