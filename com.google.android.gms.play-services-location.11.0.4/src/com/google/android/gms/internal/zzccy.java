package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzccy extends zzee implements zzccx {
   public zzccy() {
      this.attachInterface(this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         int var5;
         String[] var7;
         switch(var1) {
         case 1:
            var5 = var2.readInt();
            var7 = var2.createStringArray();
            this.zza(var5, var7);
            break;
         case 2:
            var5 = var2.readInt();
            var7 = var2.createStringArray();
            this.zzb(var5, var7);
            break;
         case 3:
            var5 = var2.readInt();
            PendingIntent var6 = (PendingIntent)zzef.zza(var2, PendingIntent.CREATOR);
            this.zza(var5, var6);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
