package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

public abstract class zzasa extends zzee implements zzarz {
   public zzasa() {
      this.attachInterface(this, "com.google.android.gms.auth.api.internal.IAuthCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            ProxyResponse var6 = (ProxyResponse)zzef.zza(var2, ProxyResponse.CREATOR);
            this.zza(var6);
            break;
         case 2:
            String var5 = var2.readString();
            this.zzbO(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
