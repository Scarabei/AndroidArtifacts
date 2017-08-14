package com.google.android.gms.tagmanager;

final class zzaw implements Runnable {
   // $FF: synthetic field
   private String zzbEv;
   // $FF: synthetic field
   private zzat zzbEt;

   zzaw(zzat var1, String var2) {
      this.zzbEt = var1;
      this.zzbEv = var2;
      super();
   }

   public final void run() {
      zzat.zza(this.zzbEt, this.zzbEv);
   }
}
