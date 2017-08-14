package com.google.android.gms.tagmanager;

final class zzgj extends Number implements Comparable {
   private double zzbHb;
   private long zzbHc;
   private boolean zzbHd;

   private zzgj(double var1) {
      this.zzbHb = var1;
      this.zzbHd = false;
   }

   private zzgj(long var1) {
      this.zzbHc = var1;
      this.zzbHd = true;
   }

   public static zzgj zza(Double var0) {
      return new zzgj(var0.doubleValue());
   }

   public static zzgj zzai(long var0) {
      return new zzgj(var0);
   }

   public static zzgj zzfx(String var0) throws NumberFormatException {
      try {
         return new zzgj(Long.parseLong(var0));
      } catch (NumberFormatException var2) {
         try {
            return new zzgj(Double.parseDouble(var0));
         } catch (NumberFormatException var1) {
            throw new NumberFormatException(String.valueOf(var0).concat(" is not a valid TypedNumber"));
         }
      }
   }

   public final String toString() {
      return this.zzbHd ? Long.toString(this.zzbHc) : Double.toString(this.zzbHb);
   }

   public final boolean equals(Object var1) {
      return var1 instanceof zzgj && this.zza((zzgj)var1) == 0;
   }

   public final int hashCode() {
      return (new Long(this.longValue())).hashCode();
   }

   public final int zza(zzgj var1) {
      return this.zzbHd && var1.zzbHd ? (new Long(this.zzbHc)).compareTo(var1.zzbHc) : Double.compare(this.doubleValue(), var1.doubleValue());
   }

   public final boolean zzBZ() {
      return !this.zzbHd;
   }

   public final boolean zzCa() {
      return this.zzbHd;
   }

   public final double doubleValue() {
      return this.zzbHd ? (double)this.zzbHc : this.zzbHb;
   }

   public final float floatValue() {
      return (float)this.doubleValue();
   }

   public final long longValue() {
      return this.zzbHd ? this.zzbHc : (long)this.zzbHb;
   }

   public final int intValue() {
      return (int)this.longValue();
   }

   public final short shortValue() {
      return (short)((int)this.longValue());
   }

   public final byte byteValue() {
      return (byte)((int)this.longValue());
   }

   // $FF: synthetic method
   public final int compareTo(Object var1) {
      return this.zza((zzgj)var1);
   }
}
