package com.google.android.gms.common.images;

public final class Size {
   private final int zzrW;
   private final int zzrX;

   public Size(int var1, int var2) {
      this.zzrW = var1;
      this.zzrX = var2;
   }

   public final int getWidth() {
      return this.zzrW;
   }

   public final int getHeight() {
      return this.zzrX;
   }

   public final boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (this == var1) {
         return true;
      } else if (var1 instanceof Size) {
         Size var2 = (Size)var1;
         return this.zzrW == var2.zzrW && this.zzrX == var2.zzrX;
      } else {
         return false;
      }
   }

   public final String toString() {
      int var1 = this.zzrW;
      int var2 = this.zzrX;
      return (new StringBuilder(23)).append(var1).append("x").append(var2).toString();
   }

   private static NumberFormatException zzcy(String var0) {
      throw new NumberFormatException((new StringBuilder(16 + String.valueOf(var0).length())).append("Invalid Size: \"").append(var0).append("\"").toString());
   }

   public static Size parseSize(String var0) throws NumberFormatException {
      if (var0 == null) {
         throw new IllegalArgumentException("string must not be null");
      } else {
         int var1;
         if ((var1 = var0.indexOf(42)) < 0) {
            var1 = var0.indexOf(120);
         }

         if (var1 < 0) {
            throw zzcy(var0);
         } else {
            try {
               return new Size(Integer.parseInt(var0.substring(0, var1)), Integer.parseInt(var0.substring(var1 + 1)));
            } catch (NumberFormatException var2) {
               throw zzcy(var0);
            }
         }
      }
   }

   public final int hashCode() {
      return this.zzrX ^ (this.zzrW << 16 | this.zzrW >>> 16);
   }
}
