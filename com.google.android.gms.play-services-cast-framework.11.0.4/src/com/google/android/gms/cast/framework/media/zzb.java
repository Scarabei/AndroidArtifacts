package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public interface zzb extends IInterface {
   int zznm() throws RemoteException;

   WebImage onPickImage(MediaMetadata var1, int var2) throws RemoteException;

   IObjectWrapper zznT() throws RemoteException;

   WebImage zza(MediaMetadata var1, ImageHints var2) throws RemoteException;

   public abstract static class zza extends zzee implements zzb {
      public zza() {
         this.attachInterface(this, "com.google.android.gms.cast.framework.media.IImagePicker");
      }

      public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
         if (this.zza(var1, var2, var3, var4)) {
            return true;
         } else {
            MediaMetadata var5;
            WebImage var7;
            switch(var1) {
            case 1:
               var5 = (MediaMetadata)zzef.zza(var2, MediaMetadata.CREATOR);
               int var10 = var2.readInt();
               var7 = this.onPickImage(var5, var10);
               var3.writeNoException();
               zzef.zzb(var3, var7);
               break;
            case 2:
               IObjectWrapper var9 = this.zznT();
               var3.writeNoException();
               zzef.zza(var3, var9);
               break;
            case 3:
               int var8 = this.zznm();
               var3.writeNoException();
               var3.writeInt(var8);
               break;
            case 4:
               var5 = (MediaMetadata)zzef.zza(var2, MediaMetadata.CREATOR);
               ImageHints var6 = (ImageHints)zzef.zza(var2, ImageHints.CREATOR);
               var7 = this.zza(var5, var6);
               var3.writeNoException();
               zzef.zzb(var3, var7);
               break;
            default:
               return false;
            }

            return true;
         }
      }
   }
}
