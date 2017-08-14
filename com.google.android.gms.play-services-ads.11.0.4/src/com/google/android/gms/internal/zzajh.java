package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;

@zzzn
public final class zzajh implements zzajm {
   private final Object mValue;
   private final zzajn zzaaU;

   public zzajh(Object var1) {
      this.mValue = var1;
      this.zzaaU = new zzajn();
      this.zzaaU.zzin();
   }

   public final Object get() {
      return this.mValue;
   }

   public final Object get(long var1, TimeUnit var3) {
      return this.mValue;
   }

   public final boolean cancel(boolean var1) {
      return false;
   }

   public final boolean isCancelled() {
      return false;
   }

   public final boolean isDone() {
      return true;
   }

   public final void zzc(Runnable var1) {
      this.zzaaU.zzc(var1);
   }

   public final void zzd(Runnable var1) {
      this.zzaaU.zzd(var1);
   }
}
