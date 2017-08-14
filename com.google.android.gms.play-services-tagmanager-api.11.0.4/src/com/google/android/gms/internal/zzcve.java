package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcve extends zzee implements zzcvd {
   public zzcve() {
      this.attachInterface(this, "com.google.android.gms.tagmanager.internal.ITagManagerLoadContainerCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         boolean var5 = zzef.zza(var2);
         String var6 = var2.readString();
         this.zza(var5, var6);
         return true;
      } else {
         return false;
      }
   }
}
