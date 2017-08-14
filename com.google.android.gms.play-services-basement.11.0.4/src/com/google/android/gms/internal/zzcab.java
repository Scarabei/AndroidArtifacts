package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;

public final class zzcab {
   private boolean zzuH = false;
   private zzcac zzaXG = null;

   public final void initialize(Context var1) {
      synchronized(this) {
         if (!this.zzuH) {
            try {
               DynamiteModule var3 = DynamiteModule.zza(var1, DynamiteModule.zzaSP, "com.google.android.gms.flags");
               this.zzaXG = zzcad.asInterface(var3.zzcV("com.google.android.gms.flags.impl.FlagProviderImpl"));
               this.zzaXG.init(com.google.android.gms.dynamic.zzn.zzw(var1));
               this.zzuH = true;
            } catch (RemoteException | DynamiteModule.zzc var5) {
               Log.w("FlagValueProvider", "Failed to initialize flags module.", var5);
            }

         }
      }
   }

   public final Object zzb(zzbzu var1) {
      synchronized(this) {
         if (!this.zzuH) {
            return var1.zzdI();
         }
      }

      return var1.zza(this.zzaXG);
   }
}
