package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

final class zzbcv extends zzbdl {
   private WeakReference zzaDR;

   zzbcv(zzbcp var1) {
      this.zzaDR = new WeakReference(var1);
   }

   public final void zzpA() {
      zzbcp var1;
      if ((var1 = (zzbcp)this.zzaDR.get()) != null) {
         zzbcp.zza(var1);
      }
   }
}
