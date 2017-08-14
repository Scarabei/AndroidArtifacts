package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public abstract class zzpg extends zzee implements zzpf {
   public zzpg() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         Bundle var5;
         String var8;
         switch(var1) {
         case 2:
            IObjectWrapper var11 = this.zzei();
            var3.writeNoException();
            zzef.zza(var3, var11);
            break;
         case 3:
            var8 = this.getHeadline();
            var3.writeNoException();
            var3.writeString(var8);
            break;
         case 4:
            List var10 = this.getImages();
            var3.writeNoException();
            var3.writeList(var10);
            break;
         case 5:
            var8 = this.getBody();
            var3.writeNoException();
            var3.writeString(var8);
            break;
         case 6:
            zzos var9 = this.zzem();
            var3.writeNoException();
            zzef.zza(var3, var9);
            break;
         case 7:
            var8 = this.getCallToAction();
            var3.writeNoException();
            var3.writeString(var8);
            break;
         case 8:
            var8 = this.getAdvertiser();
            var3.writeNoException();
            var3.writeString(var8);
            break;
         case 9:
            var5 = this.getExtras();
            var3.writeNoException();
            zzef.zzb(var3, var5);
            break;
         case 10:
            this.destroy();
            var3.writeNoException();
            break;
         case 11:
            zzks var7 = this.getVideoController();
            var3.writeNoException();
            zzef.zza(var3, var7);
            break;
         case 12:
            var5 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zzc(var5);
            var3.writeNoException();
            break;
         case 13:
            var5 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            boolean var6 = this.zzd(var5);
            var3.writeNoException();
            zzef.zza(var3, var6);
            break;
         case 14:
            var5 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zze(var5);
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
