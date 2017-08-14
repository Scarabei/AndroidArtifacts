package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzkt extends zzee implements zzks {
   public zzkt() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.client.IVideoController");
   }

   public static zzks zzg(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzks)((var1 = var0.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController")) instanceof zzks ? (zzks)var1 : new zzku(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         boolean var5;
         float var8;
         switch(var1) {
         case 1:
            this.play();
            var3.writeNoException();
            break;
         case 2:
            this.pause();
            var3.writeNoException();
            break;
         case 3:
            var5 = zzef.zza(var2);
            this.mute(var5);
            var3.writeNoException();
            break;
         case 4:
            var5 = this.isMuted();
            var3.writeNoException();
            zzef.zza(var3, var5);
            break;
         case 5:
            int var10 = this.getPlaybackState();
            var3.writeNoException();
            var3.writeInt(var10);
            break;
         case 6:
            var8 = this.zzdv();
            var3.writeNoException();
            var3.writeFloat(var8);
            break;
         case 7:
            var8 = this.zzdw();
            var3.writeNoException();
            var3.writeFloat(var8);
            break;
         case 8:
            IBinder var6;
            IInterface var7;
            Object var9 = (var6 = var2.readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks")) instanceof zzkv ? (zzkv)var7 : new zzkx(var6));
            this.zza((zzkv)var9);
            var3.writeNoException();
            break;
         case 9:
            var8 = this.getAspectRatio();
            var3.writeNoException();
            var3.writeFloat(var8);
            break;
         case 10:
            var5 = this.isCustomControlsEnabled();
            var3.writeNoException();
            zzef.zza(var3, var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
