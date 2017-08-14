package com.google.android.gms.internal;

public abstract class zzamh extends zzamg {
   private boolean zzafK;

   protected zzamh(zzamj var1) {
      super(var1);
   }

   public final boolean isInitialized() {
      return this.zzafK;
   }

   protected final void zzkD() {
      if (!this.isInitialized()) {
         throw new IllegalStateException("Not initialized");
      }
   }

   public final void initialize() {
      this.zzjD();
      this.zzafK = true;
   }

   protected abstract void zzjD();
}
