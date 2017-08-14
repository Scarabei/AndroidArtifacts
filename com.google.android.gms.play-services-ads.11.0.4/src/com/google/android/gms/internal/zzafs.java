package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

@zzzn
public final class zzafs extends Handler {
   public zzafs(Looper var1) {
      super(var1);
   }

   public final void handleMessage(Message var1) {
      try {
         super.handleMessage(var1);
      } catch (Exception var3) {
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var3, (String)"AdMobHandler.handleMessage");
      }
   }
}
