package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzzn
public abstract class zzafp implements zzahp {
   private final Runnable zzv = new zzafq(this);
   private volatile Thread zzYV;
   private boolean zzYW;

   public zzafp() {
      this.zzYW = false;
   }

   public zzafp(boolean var1) {
      this.zzYW = true;
   }

   public final Future zzhL() {
      return this.zzYW ? zzagt.zza(1, this.zzv) : zzagt.zza(this.zzv);
   }

   public final void cancel() {
      this.onStop();
      if (this.zzYV != null) {
         this.zzYV.interrupt();
      }

   }

   public abstract void onStop();

   public abstract void zzbd();

   // $FF: synthetic method
   public final Object zzgp() {
      return this.zzYW ? zzagt.zza(1, this.zzv) : zzagt.zza(this.zzv);
   }

   // $FF: synthetic method
   static Thread zza(zzafp var0, Thread var1) {
      return var0.zzYV = var1;
   }
}
