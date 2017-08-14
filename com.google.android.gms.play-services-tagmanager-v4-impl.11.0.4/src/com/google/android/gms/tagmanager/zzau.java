package com.google.android.gms.tagmanager;

import java.util.List;

final class zzau implements Runnable {
   // $FF: synthetic field
   private List zzbEr;
   // $FF: synthetic field
   private long zzbEs;
   // $FF: synthetic field
   private zzat zzbEt;

   zzau(zzat var1, List var2, long var3) {
      this.zzbEt = var1;
      this.zzbEr = var2;
      this.zzbEs = var3;
      super();
   }

   public final void run() {
      zzat.zza(this.zzbEt, this.zzbEr, this.zzbEs);
   }
}
