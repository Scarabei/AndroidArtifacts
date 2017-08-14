package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

final class zzajk implements Runnable {
   // $FF: synthetic field
   private AtomicInteger zzaaY;
   // $FF: synthetic field
   private int zzaaZ;
   // $FF: synthetic field
   private zzajg zzaba;
   // $FF: synthetic field
   private List zzabb;

   zzajk(AtomicInteger var1, int var2, zzajg var3, List var4) {
      this.zzaaY = var1;
      this.zzaaZ = var2;
      this.zzaba = var3;
      this.zzabb = var4;
      super();
   }

   public final void run() {
      if (this.zzaaY.incrementAndGet() >= this.zzaaZ) {
         try {
            this.zzaba.zzg(zzaji.zzr(this.zzabb));
            return;
         } catch (InterruptedException | ExecutionException var2) {
            zzafr.zzc("Unable to convert list of futures to a future of list", var2);
         }
      }

   }
}
