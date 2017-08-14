package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zznf extends zzee implements zzne {
   public zznf() {
      this.attachInterface(this, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         String var6;
         switch(var1) {
         case 1:
            var6 = this.zzdX();
            var3.writeNoException();
            var3.writeString(var6);
            break;
         case 2:
            var6 = this.getContent();
            var3.writeNoException();
            var3.writeString(var6);
            break;
         case 3:
            IObjectWrapper var5 = zza.zzM(var2.readStrongBinder());
            this.zzi(var5);
            var3.writeNoException();
            break;
         case 4:
            this.recordClick();
            var3.writeNoException();
            break;
         case 5:
            this.recordImpression();
            var3.writeNoException();
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
