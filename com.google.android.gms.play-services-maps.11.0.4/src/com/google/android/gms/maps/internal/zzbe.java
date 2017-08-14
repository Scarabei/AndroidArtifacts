package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

public abstract class zzbe extends zzee implements zzbd {
   public zzbe() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnPolylineClickListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         IPolylineDelegate var5 = IPolylineDelegate.zza.zzah(var2.readStrongBinder());
         this.zza(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
