package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

public abstract class zzu extends zzee implements zzt {
   public zzu() {
      this.attachInterface(this, "com.google.android.gms.location.places.internal.IPhotosCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 2:
            PlacePhotoResult var6 = (PlacePhotoResult)zzef.zza(var2, PlacePhotoResult.CREATOR);
            this.zza(var6);
            break;
         case 3:
            PlacePhotoMetadataResult var5 = (PlacePhotoMetadataResult)zzef.zza(var2, PlacePhotoMetadataResult.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
