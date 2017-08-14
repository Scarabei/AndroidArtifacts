package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import java.util.ArrayList;

public abstract class zzn extends zzee implements zzm {
   public zzn() {
      this.attachInterface(this, "com.google.android.gms.nearby.messages.internal.IMessageListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         zzaf var6;
         switch(var1) {
         case 1:
            var6 = (zzaf)zzef.zza(var2, zzaf.CREATOR);
            this.zza(var6);
            break;
         case 2:
            var6 = (zzaf)zzef.zza(var2, zzaf.CREATOR);
            this.zzb(var6);
            break;
         case 3:
         default:
            return false;
         case 4:
            ArrayList var5 = var2.createTypedArrayList(Update.CREATOR);
            this.zzH(var5);
         }

         return true;
      }
   }
}
