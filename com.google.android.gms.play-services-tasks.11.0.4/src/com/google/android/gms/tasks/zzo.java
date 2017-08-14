package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzo implements Runnable {
   // $FF: synthetic field
   private zzn zzbMk;
   // $FF: synthetic field
   private Callable zzZo;

   zzo(zzn var1, Callable var2) {
      this.zzbMk = var1;
      this.zzZo = var2;
      super();
   }

   public final void run() {
      try {
         this.zzbMk.setResult(this.zzZo.call());
      } catch (Exception var2) {
         this.zzbMk.setException(var2);
      }
   }
}
