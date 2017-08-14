package com.google.android.gms.internal;

import java.io.IOException;

public final class adg {
   private final byte[] buffer;
   private int zzcse;
   private int zzcsf;
   private int zzcsg;
   private int zzcsh;
   private int zzcsi;
   private int zzcsj = Integer.MAX_VALUE;
   private int zzcsk;
   private int zzcsl = 64;
   private int zzcsm = 67108864;

   public static adg zzH(byte[] var0) {
      return zzb(var0, 0, var0.length);
   }

   public static adg zzb(byte[] var0, int var1, int var2) {
      return new adg(var0, 0, var2);
   }

   public final int zzLA() throws IOException {
      if (this.zzcsh == this.zzcsf) {
         this.zzcsi = 0;
         return 0;
      } else {
         this.zzcsi = this.zzLF();
         if (this.zzcsi == 0) {
            throw new ado("Protocol message contained an invalid tag (zero).");
         } else {
            return this.zzcsi;
         }
      }
   }

   public final void zzcl(int var1) throws ado {
      if (this.zzcsi != var1) {
         throw new ado("Protocol message end-group tag did not match expected tag.");
      }
   }

   public final boolean zzcm(int var1) throws IOException {
      switch(var1 & 7) {
      case 0:
         this.zzLF();
         return true;
      case 1:
         this.zzLI();
         return true;
      case 2:
         this.zzcq(this.zzLF());
         return true;
      case 3:
         adg var2 = this;

         int var3;
         while((var3 = var2.zzLA()) != 0 && var2.zzcm(var3)) {
            ;
         }

         this.zzcl(var1 >>> 3 << 3 | 4);
         return true;
      case 4:
         return false;
      case 5:
         this.zzLH();
         return true;
      default:
         throw new ado("Protocol message tag had invalid wire type.");
      }
   }

   public final long zzLB() throws IOException {
      return this.zzLG();
   }

   public final int zzLC() throws IOException {
      return this.zzLF();
   }

   public final boolean zzLD() throws IOException {
      return this.zzLF() != 0;
   }

   public final String readString() throws IOException {
      int var1;
      if ((var1 = this.zzLF()) < 0) {
         throw ado.zzLR();
      } else if (var1 > this.zzcsf - this.zzcsh) {
         throw ado.zzLQ();
      } else {
         String var2 = new String(this.buffer, this.zzcsh, var1, adn.UTF_8);
         this.zzcsh += var1;
         return var2;
      }
   }

   public final void zza(adp var1, int var2) throws IOException {
      if (this.zzcsk >= this.zzcsl) {
         throw ado.zzLT();
      } else {
         ++this.zzcsk;
         var1.zza(this);
         this.zzcl(var2 << 3 | 4);
         --this.zzcsk;
      }
   }

   public final void zza(adp var1) throws IOException {
      int var2 = this.zzLF();
      if (this.zzcsk >= this.zzcsl) {
         throw ado.zzLT();
      } else {
         int var3 = this.zzcn(var2);
         ++this.zzcsk;
         var1.zza(this);
         this.zzcl(0);
         --this.zzcsk;
         this.zzco(var3);
      }
   }

   public final byte[] readBytes() throws IOException {
      int var1;
      if ((var1 = this.zzLF()) < 0) {
         throw ado.zzLR();
      } else if (var1 == 0) {
         return ads.zzcsI;
      } else if (var1 > this.zzcsf - this.zzcsh) {
         throw ado.zzLQ();
      } else {
         byte[] var2 = new byte[var1];
         System.arraycopy(this.buffer, this.zzcsh, var2, 0, var1);
         this.zzcsh += var1;
         return var2;
      }
   }

   public final long zzLE() throws IOException {
      long var1;
      return (var1 = this.zzLG()) >>> 1 ^ -(var1 & 1L);
   }

   public final int zzLF() throws IOException {
      byte var1;
      if ((var1 = this.zzLL()) >= 0) {
         return var1;
      } else {
         int var2 = var1 & 127;
         if ((var1 = this.zzLL()) >= 0) {
            var2 |= var1 << 7;
         } else {
            var2 |= (var1 & 127) << 7;
            if ((var1 = this.zzLL()) >= 0) {
               var2 |= var1 << 14;
            } else {
               var2 |= (var1 & 127) << 14;
               if ((var1 = this.zzLL()) >= 0) {
                  var2 |= var1 << 21;
               } else {
                  var2 = var2 | (var1 & 127) << 21 | (var1 = this.zzLL()) << 28;
                  if (var1 < 0) {
                     for(int var3 = 0; var3 < 5; ++var3) {
                        if (this.zzLL() >= 0) {
                           return var2;
                        }
                     }

                     throw ado.zzLS();
                  }
               }
            }
         }

         return var2;
      }
   }

   public final long zzLG() throws IOException {
      int var1 = 0;

      for(long var2 = 0L; var1 < 64; var1 += 7) {
         byte var4 = this.zzLL();
         var2 |= (long)(var4 & 127) << var1;
         if ((var4 & 128) == 0) {
            return var2;
         }
      }

      throw ado.zzLS();
   }

   public final int zzLH() throws IOException {
      byte var1 = this.zzLL();
      byte var2 = this.zzLL();
      byte var3 = this.zzLL();
      byte var4 = this.zzLL();
      return var1 & 255 | (var2 & 255) << 8 | (var3 & 255) << 16 | (var4 & 255) << 24;
   }

   public final long zzLI() throws IOException {
      byte var1 = this.zzLL();
      byte var2 = this.zzLL();
      byte var3 = this.zzLL();
      byte var4 = this.zzLL();
      byte var5 = this.zzLL();
      byte var6 = this.zzLL();
      byte var7 = this.zzLL();
      byte var8 = this.zzLL();
      return (long)var1 & 255L | ((long)var2 & 255L) << 8 | ((long)var3 & 255L) << 16 | ((long)var4 & 255L) << 24 | ((long)var5 & 255L) << 32 | ((long)var6 & 255L) << 40 | ((long)var7 & 255L) << 48 | ((long)var8 & 255L) << 56;
   }

   private adg(byte[] var1, int var2, int var3) {
      this.buffer = var1;
      this.zzcse = var2;
      this.zzcsf = var2 + var3;
      this.zzcsh = var2;
   }

   public final int zzcn(int var1) throws ado {
      if (var1 < 0) {
         throw ado.zzLR();
      } else {
         var1 += this.zzcsh;
         int var2 = this.zzcsj;
         if (var1 > var2) {
            throw ado.zzLQ();
         } else {
            this.zzcsj = var1;
            this.zzLJ();
            return var2;
         }
      }
   }

   private final void zzLJ() {
      this.zzcsf += this.zzcsg;
      int var1 = this.zzcsf;
      if (this.zzcsf > this.zzcsj) {
         this.zzcsg = var1 - this.zzcsj;
         this.zzcsf -= this.zzcsg;
      } else {
         this.zzcsg = 0;
      }
   }

   public final void zzco(int var1) {
      this.zzcsj = var1;
      this.zzLJ();
   }

   public final int zzLK() {
      if (this.zzcsj == Integer.MAX_VALUE) {
         return -1;
      } else {
         int var1 = this.zzcsh;
         return this.zzcsj - var1;
      }
   }

   public final int getPosition() {
      return this.zzcsh - this.zzcse;
   }

   public final byte[] zzp(int var1, int var2) {
      if (var2 == 0) {
         return ads.zzcsI;
      } else {
         byte[] var3 = new byte[var2];
         int var4 = this.zzcse + var1;
         System.arraycopy(this.buffer, var4, var3, 0, var2);
         return var3;
      }
   }

   public final void zzcp(int var1) {
      this.zzq(var1, this.zzcsi);
   }

   final void zzq(int var1, int var2) {
      if (var1 > this.zzcsh - this.zzcse) {
         int var3 = this.zzcsh - this.zzcse;
         throw new IllegalArgumentException((new StringBuilder(50)).append("Position ").append(var1).append(" is beyond current ").append(var3).toString());
      } else if (var1 < 0) {
         throw new IllegalArgumentException((new StringBuilder(24)).append("Bad position ").append(var1).toString());
      } else {
         this.zzcsh = this.zzcse + var1;
         this.zzcsi = var2;
      }
   }

   private final byte zzLL() throws IOException {
      if (this.zzcsh == this.zzcsf) {
         throw ado.zzLQ();
      } else {
         return this.buffer[this.zzcsh++];
      }
   }

   private final void zzcq(int var1) throws IOException {
      if (var1 < 0) {
         throw ado.zzLR();
      } else if (this.zzcsh + var1 > this.zzcsj) {
         this.zzcq(this.zzcsj - this.zzcsh);
         throw ado.zzLQ();
      } else if (var1 <= this.zzcsf - this.zzcsh) {
         this.zzcsh += var1;
      } else {
         throw ado.zzLQ();
      }
   }
}
