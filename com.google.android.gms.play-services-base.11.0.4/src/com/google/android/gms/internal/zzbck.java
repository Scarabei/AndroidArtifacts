package com.google.android.gms.internal;

import android.support.annotation.BinderThread;
import java.lang.ref.WeakReference;

final class zzbck extends zzctp {
   private final WeakReference zzaDq;

   zzbck(zzbcd var1) {
      this.zzaDq = new WeakReference(var1);
   }

   @BinderThread
   public final void zzb(zzctx var1) {
      zzbcd var2;
      if ((var2 = (zzbcd)this.zzaDq.get()) != null) {
         zzbcd.zzd(var2).zza((zzbcy)(new zzbcl(this, var2, var2, var1)));
      }
   }
}
