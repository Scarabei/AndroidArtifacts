package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzbo;

final class zzbdx extends Handler {
   // $FF: synthetic field
   private zzbdw zzaEO;

   public zzbdx(zzbdw var1, Looper var2) {
      this.zzaEO = var1;
      super(var2);
   }

   public final void handleMessage(Message var1) {
      zzbo.zzaf(var1.what == 1);
      this.zzaEO.zzb((zzbdz)var1.obj);
   }
}
