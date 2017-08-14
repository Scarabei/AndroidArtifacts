package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

@zzzn
public final class zzix extends zzkf {
   private final AppEventListener zzsw;

   public zzix(AppEventListener var1) {
      this.zzsw = var1;
   }

   public final void onAppEvent(String var1, String var2) {
      this.zzsw.onAppEvent(var1, var2);
   }

   public final AppEventListener getAppEventListener() {
      return this.zzsw;
   }
}
