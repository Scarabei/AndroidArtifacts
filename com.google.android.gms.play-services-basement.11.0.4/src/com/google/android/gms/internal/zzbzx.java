package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzx extends zzbzu {
   public zzbzx(int var1, String var2, Integer var3) {
      super(0, var2, var3, (zzbzv)null);
   }

   private final Integer zzc(zzcac var1) {
      try {
         return var1.getIntFlagValue(this.getKey(), ((Integer)this.zzdI()).intValue(), this.getSource());
      } catch (RemoteException var2) {
         return (Integer)this.zzdI();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzcac var1) {
      return this.zzc(var1);
   }
}
