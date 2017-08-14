package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

final class zzajj implements Runnable {
   // $FF: synthetic field
   private zzajg zzaaV;
   // $FF: synthetic field
   private zzajl zzaaW;
   // $FF: synthetic field
   private zzajm zzaaX;

   zzajj(zzajg var1, zzajl var2, zzajm var3) {
      this.zzaaV = var1;
      this.zzaaW = var2;
      this.zzaaX = var3;
      super();
   }

   public final void run() {
      try {
         this.zzaaV.zzg(this.zzaaW.apply(this.zzaaX.get()));
         return;
      } catch (CancellationException var1) {
         ;
      } catch (InterruptedException var2) {
         ;
      } catch (ExecutionException var3) {
         ;
      }

      this.zzaaV.cancel(true);
   }
}
