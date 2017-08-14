package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzy extends zzbzu {
   public zzbzy(int var1, String var2, Long var3) {
      super(0, var2, var3, (zzbzv)null);
   }

   private final Long zzd(zzcac var1) {
      try {
         return var1.getLongFlagValue(this.getKey(), ((Long)this.zzdI()).longValue(), this.getSource());
      } catch (RemoteException var2) {
         return (Long)this.zzdI();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzcac var1) {
      return this.zzd(var1);
   }
}
