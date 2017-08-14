package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzz extends zzbzu {
   public zzbzz(int var1, String var2, String var3) {
      super(0, var2, var3, (zzbzv)null);
   }

   private final String zze(zzcac var1) {
      try {
         return var1.getStringFlagValue(this.getKey(), (String)this.zzdI(), this.getSource());
      } catch (RemoteException var2) {
         return (String)this.zzdI();
      }
   }

   // $FF: synthetic method
   public final Object zza(zzcac var1) {
      return this.zze(var1);
   }
}
