package com.google.android.gms.tagmanager;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzci extends zzee implements zzch {
   public zzci() {
      this.attachInterface(this, "com.google.android.gms.tagmanager.IMeasurementEventListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         String var5 = var2.readString();
         String var6 = var2.readString();
         Bundle var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
         long var8 = var2.readLong();
         this.onEvent(var5, var6, var7, var8);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
