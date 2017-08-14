package com.google.android.gms.internal;

public final class zzg implements zzx {
   private int zzn;
   private int zzo;
   private final int zzp;
   private final float zzq;

   public zzg() {
      this(2500, 1, 1.0F);
   }

   private zzg(int var1, int var2, float var3) {
      this.zzn = 2500;
      this.zzp = 1;
      this.zzq = 1.0F;
   }

   public final int zza() {
      return this.zzn;
   }

   public final int zzb() {
      return this.zzo;
   }

   public final void zza(zzaa var1) throws zzaa {
      ++this.zzo;
      this.zzn = (int)((float)this.zzn + (float)this.zzn * this.zzq);
      if (this.zzo > this.zzp) {
         throw var1;
      }
   }
}
