package com.google.android.gms.internal;

public final class adl implements Cloneable {
   private static final adm zzcsq = new adm();
   private boolean zzcsr;
   private int[] zzcss;
   private adm[] zzcst;
   private int mSize;

   adl() {
      this(10);
   }

   private adl(int var1) {
      this.zzcsr = false;
      var1 = idealIntArraySize(var1);
      this.zzcss = new int[var1];
      this.zzcst = new adm[var1];
      this.mSize = 0;
   }

   final adm zzcx(int var1) {
      int var2;
      return (var2 = this.zzcz(var1)) >= 0 && this.zzcst[var2] != zzcsq ? this.zzcst[var2] : null;
   }

   final void zza(int var1, adm var2) {
      int var3;
      if ((var3 = this.zzcz(var1)) >= 0) {
         this.zzcst[var3] = var2;
      } else if ((var3 = ~var3) < this.mSize && this.zzcst[var3] == zzcsq) {
         this.zzcss[var3] = var1;
         this.zzcst[var3] = var2;
      } else {
         if (this.mSize >= this.zzcss.length) {
            int var4;
            int[] var5 = new int[var4 = idealIntArraySize(this.mSize + 1)];
            adm[] var6 = new adm[var4];
            System.arraycopy(this.zzcss, 0, var5, 0, this.zzcss.length);
            System.arraycopy(this.zzcst, 0, var6, 0, this.zzcst.length);
            this.zzcss = var5;
            this.zzcst = var6;
         }

         if (this.mSize - var3 != 0) {
            System.arraycopy(this.zzcss, var3, this.zzcss, var3 + 1, this.mSize - var3);
            System.arraycopy(this.zzcst, var3, this.zzcst, var3 + 1, this.mSize - var3);
         }

         this.zzcss[var3] = var1;
         this.zzcst[var3] = var2;
         ++this.mSize;
      }
   }

   final int size() {
      return this.mSize;
   }

   public final boolean isEmpty() {
      return this.mSize == 0;
   }

   final adm zzcy(int var1) {
      return this.zzcst[var1];
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof adl)) {
         return false;
      } else {
         adl var2 = (adl)var1;
         if (this.mSize != var2.mSize) {
            return false;
         } else {
            int var5 = this.mSize;
            int[] var4 = var2.zzcss;
            int[] var3 = this.zzcss;
            int var6 = 0;

            boolean var10000;
            while(true) {
               if (var6 >= var5) {
                  var10000 = true;
                  break;
               }

               if (var3[var6] != var4[var6]) {
                  var10000 = false;
                  break;
               }

               ++var6;
            }

            if (var10000) {
               var5 = this.mSize;
               adm[] var8 = var2.zzcst;
               adm[] var7 = this.zzcst;
               var6 = 0;

               while(true) {
                  if (var6 >= var5) {
                     var10000 = true;
                     break;
                  }

                  if (!var7[var6].equals(var8[var6])) {
                     var10000 = false;
                     break;
                  }

                  ++var6;
               }

               if (var10000) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public final int hashCode() {
      int var1 = 17;

      for(int var2 = 0; var2 < this.mSize; ++var2) {
         var1 = (var1 * 31 + this.zzcss[var2]) * 31 + this.zzcst[var2].hashCode();
      }

      return var1;
   }

   private static int idealIntArraySize(int var0) {
      int var1 = var0 << 2;
      int var2 = 4;

      int var10000;
      while(true) {
         if (var2 >= 32) {
            var10000 = var1;
            break;
         }

         if (var1 <= (1 << var2) - 12) {
            var10000 = (1 << var2) - 12;
            break;
         }

         ++var2;
      }

      return var10000 / 4;
   }

   private final int zzcz(int var1) {
      int var2 = 0;
      int var3 = this.mSize - 1;

      while(var2 <= var3) {
         int var4 = var2 + var3 >>> 1;
         int var5;
         if ((var5 = this.zzcss[var4]) < var1) {
            var2 = var4 + 1;
         } else {
            if (var5 <= var1) {
               return var4;
            }

            var3 = var4 - 1;
         }
      }

      return ~var2;
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      adl var1 = this;
      int var2 = this.mSize;
      adl var3 = new adl(var2);
      System.arraycopy(this.zzcss, 0, var3.zzcss, 0, var2);

      for(int var4 = 0; var4 < var2; ++var4) {
         if (var1.zzcst[var4] != null) {
            var3.zzcst[var4] = (adm)var1.zzcst[var4].clone();
         }
      }

      var3.mSize = var2;
      return var3;
   }
}
