package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzw extends zzbzu {
   public zzbzw(int var1, String var2, Boolean var3) {
      super(0, var2, var3, (zzbzv)null);
   }

   private final Boolean zzb(zzcac var1) {
      try {
         return var1.getBooleanFlagValue(this.getKey(), ((Boolean)this.zzdI()).booleanValue(), this.getSource());
      } catch (RemoteException var2) {
         return (Boolean)this.zzdI();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzcac var1) {
      return this.zzb(var1);
   }
}
