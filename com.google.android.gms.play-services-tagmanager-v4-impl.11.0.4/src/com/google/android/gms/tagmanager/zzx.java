package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzx extends Handler {
   private final ContainerHolder.ContainerAvailableListener zzbDJ;
   // $FF: synthetic field
   private zzv zzbDK;

   public zzx(zzv var1, ContainerHolder.ContainerAvailableListener var2, Looper var3) {
      this.zzbDK = var1;
      super(var3);
      this.zzbDJ = var2;
   }

   public final void handleMessage(Message var1) {
      switch(var1.what) {
      case 1:
         String var3 = (String)var1.obj;
         this.zzbDJ.onContainerAvailable(this.zzbDK, var3);
         return;
      default:
         zzdj.e("Don't know how to handle this message.");
      }
   }
}
