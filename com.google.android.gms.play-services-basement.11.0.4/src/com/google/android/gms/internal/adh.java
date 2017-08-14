package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class adh {
   private final ByteBuffer zzcsn;

   private adh(byte[] var1, int var2, int var3) {
      this(ByteBuffer.wrap(var1, var2, var3));
   }

   private adh(ByteBuffer var1) {
      this.zzcsn = var1;
      this.zzcsn.order(ByteOrder.LITTLE_ENDIAN);
   }

   public static adh zzI(byte[] var0) {
      return zzc(var0, 0, var0.length);
   }

   public static adh zzc(byte[] var0, int var1, int var2) {
      return new adh(var0, 0, var2);
   }

   public final void zza(int var1, double var2) throws IOException {
      this.zzt(var1, 1);
      this.zzaQ(Double.doubleToLongBits(var2));
   }

   public final void zzc(int var1, float var2) throws IOException {
      this.zzt(var1, 5);
      int var5 = Float.floatToIntBits(var2);
      if (this.zzcsn.remaining() < 4) {
         throw new adi(this.zzcsn.position(), this.zzcsn.limit());
      } else {
         this.zzcsn.putInt(var5);
      }
   }

   public final void zza(int var1, long var2) throws IOException {
      this.zzt(var1, 0);
      this.zzaO(var2);
   }

   public final void zzb(int var1, long var2) throws IOException {
      this.zzt(var1, 0);
      this.zzaO(var2);
   }

   public final void zzr(int var1, int var2) throws IOException {
      this.zzt(var1, 0);
      if (var2 >= 0) {
         this.zzcu(var2);
      } else {
         this.zzaO((long)var2);
      }
   }

   public final void zzc(int var1, long var2) throws IOException {
      this.zzt(var1, 1);
      this.zzaQ(var2);
   }

   public final void zzk(int var1, boolean var2) throws IOException {
      this.zzt(var1, 0);
      int var4;
      byte var6 = (byte)(var4 = var2 ? 1 : 0);
      if (!this.zzcsn.hasRemaining()) {
         throw new adi(this.zzcsn.position(), this.zzcsn.limit());
      } else {
         this.zzcsn.put(var6);
      }
   }

   public final void zzl(int var1, String var2) throws IOException {
      this.zzt(var1, 2);
      String var4 = var2;
      adh var3 = this;

      try {
         int var5 = zzcv(var4.length());
         int var10 = zzcv(var4.length() * 3);
         if (var5 == var10) {
            int var7 = var3.zzcsn.position();
            if (var3.zzcsn.remaining() < var5) {
               throw new adi(var7 + var5, var3.zzcsn.limit());
            } else {
               var3.zzcsn.position(var7 + var5);
               zza(var4, var3.zzcsn);
               int var8 = var3.zzcsn.position();
               var3.zzcsn.position(var7);
               var3.zzcu(var8 - var7 - var5);
               var3.zzcsn.position(var8);
            }
         } else {
            var3.zzcu(zzb((CharSequence)var4));
            zza(var4, var3.zzcsn);
         }
      } catch (BufferOverflowException var9) {
         adi var6;
         (var6 = new adi(this.zzcsn.position(), this.zzcsn.limit())).initCause(var9);
         throw var6;
      }
   }

   public final void zza(int var1, adp var2) throws IOException {
      this.zzt(var1, 2);
      this.zzb(var2);
   }

   public final void zzb(int var1, byte[] var2) throws IOException {
      this.zzt(var1, 2);
      this.zzcu(var2.length);
      this.zzK(var2);
   }

   public final void zzd(int var1, long var2) throws IOException {
      this.zzt(var1, 0);
      this.zzaO(zzaR(var2));
   }

   private static int zzb(CharSequence var0) {
      int var1;
      int var2 = var1 = var0.length();

      int var3;
      for(var3 = 0; var3 < var1 && var0.charAt(var3) < 128; ++var3) {
         ;
      }

      while(var3 < var1) {
         char var4;
         if ((var4 = var0.charAt(var3)) >= 2048) {
            CharSequence var6 = var0;
            int var8 = var0.length();
            int var9 = 0;

            for(int var10 = var3; var10 < var8; ++var10) {
               char var11;
               if ((var11 = var6.charAt(var10)) < 2048) {
                  var9 += 127 - var11 >>> 31;
               } else {
                  var9 += 2;
                  if ('\ud800' <= var11 && var11 <= '\udfff') {
                     if (Character.codePointAt(var6, var10) < 65536) {
                        throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(var10).toString());
                     }

                     ++var10;
                  }
               }
            }

            var2 += var9;
            break;
         }

         var2 += 127 - var4 >>> 31;
         ++var3;
      }

      if (var2 < var1) {
         long var13 = (long)var2 + 4294967296L;
         throw new IllegalArgumentException((new StringBuilder(54)).append("UTF-8 length does not fit in int: ").append(var13).toString());
      } else {
         return var2;
      }
   }

   private static void zza(CharSequence var0, ByteBuffer var1) {
      if (var1.isReadOnly()) {
         throw new ReadOnlyBufferException();
      } else if (var1.hasArray()) {
         try {
            int var2 = zza(var0, var1.array(), var1.arrayOffset() + var1.position(), var1.remaining());
            var1.position(var2 - var1.arrayOffset());
         } catch (ArrayIndexOutOfBoundsException var4) {
            BufferOverflowException var3;
            (var3 = new BufferOverflowException()).initCause(var4);
            throw var3;
         }
      } else {
         zzb(var0, var1);
      }
   }

   private static void zzb(CharSequence var0, ByteBuffer var1) {
      int var2 = var0.length();
      int var3 = 0;

      int var6;
      while(true) {
         if (var3 >= var2) {
            return;
         }

         char var4;
         if ((var4 = var0.charAt(var3)) < 128) {
            var1.put((byte)var4);
         } else if (var4 < 2048) {
            var1.put((byte)(960 | var4 >>> 6));
            var1.put((byte)(128 | 63 & var4));
         } else if (var4 >= '\ud800' && '\udfff' >= var4) {
            if (var3 + 1 == var0.length()) {
               break;
            }

            ++var3;
            char var5;
            if (!Character.isSurrogatePair(var4, var5 = var0.charAt(var3))) {
               break;
            }

            var6 = Character.toCodePoint(var4, var5);
            var1.put((byte)(240 | var6 >>> 18));
            var1.put((byte)(128 | 63 & var6 >>> 12));
            var1.put((byte)(128 | 63 & var6 >>> 6));
            var1.put((byte)(128 | 63 & var6));
         } else {
            var1.put((byte)(480 | var4 >>> 12));
            var1.put((byte)(128 | 63 & var4 >>> 6));
            var1.put((byte)(128 | 63 & var4));
         }

         ++var3;
      }

      var6 = var3 - 1;
      throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(var6).toString());
   }

   private static int zza(CharSequence var0, byte[] var1, int var2, int var3) {
      int var4 = var0.length();
      int var5 = var2;
      int var6 = 0;

      int var7;
      char var8;
      for(var7 = var2 + var3; var6 < var4 && var6 + var5 < var7 && (var8 = var0.charAt(var6)) < 128; ++var6) {
         var1[var5 + var6] = (byte)var8;
      }

      if (var6 == var4) {
         return var5 + var4;
      } else {
         var5 += var6;

         int var10;
         while(true) {
            if (var6 >= var4) {
               return var5;
            }

            if ((var8 = var0.charAt(var6)) < 128 && var5 < var7) {
               var1[var5++] = (byte)var8;
            } else if (var8 < 2048 && var5 <= var7 - 2) {
               var1[var5++] = (byte)(960 | var8 >>> 6);
               var1[var5++] = (byte)(128 | 63 & var8);
            } else if ((var8 < '\ud800' || '\udfff' < var8) && var5 <= var7 - 3) {
               var1[var5++] = (byte)(480 | var8 >>> 12);
               var1[var5++] = (byte)(128 | 63 & var8 >>> 6);
               var1[var5++] = (byte)(128 | 63 & var8);
            } else {
               if (var5 > var7 - 4) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(37)).append("Failed writing ").append(var8).append(" at index ").append(var5).toString());
               }

               if (var6 + 1 == var0.length()) {
                  break;
               }

               ++var6;
               char var9;
               if (!Character.isSurrogatePair(var8, var9 = var0.charAt(var6))) {
                  break;
               }

               var10 = Character.toCodePoint(var8, var9);
               var1[var5++] = (byte)(240 | var10 >>> 18);
               var1[var5++] = (byte)(128 | 63 & var10 >>> 12);
               var1[var5++] = (byte)(128 | 63 & var10 >>> 6);
               var1[var5++] = (byte)(128 | 63 & var10);
            }

            ++var6;
         }

         var10 = var6 - 1;
         throw new IllegalArgumentException((new StringBuilder(39)).append("Unpaired surrogate at index ").append(var10).toString());
      }
   }

   public final void zzb(adp var1) throws IOException {
      this.zzcu(var1.zzLU());
      var1.zza(this);
   }

   public static int zze(int var0, long var1) {
      return zzct(var0) + zzaP(var1);
   }

   public static int zzs(int var0, int var1) {
      return zzct(var0) + zzcr(var1);
   }

   public static int zzm(int var0, String var1) {
      return zzct(var0) + zzhQ(var1);
   }

   public static int zzb(int var0, adp var1) {
      int var2;
      return zzct(var0) + zzcv(var2 = var1.zzLV()) + var2;
   }

   public static int zzc(int var0, byte[] var1) {
      return zzct(var0) + zzJ(var1);
   }

   public static int zzf(int var0, long var1) {
      return zzct(var0) + zzaP(zzaR(var1));
   }

   public static int zzcr(int var0) {
      return var0 >= 0 ? zzcv(var0) : 10;
   }

   public static int zzhQ(String var0) {
      int var1;
      return zzcv(var1 = zzb((CharSequence)var0)) + var1;
   }

   public static int zzJ(byte[] var0) {
      return zzcv(var0.length) + var0.length;
   }

   public final void zzLM() {
      if (this.zzcsn.remaining() != 0) {
         throw new IllegalStateException("Did not write as much data as expected.");
      }
   }

   private final void zzcs(int var1) throws IOException {
      byte var3 = (byte)var1;
      if (!this.zzcsn.hasRemaining()) {
         throw new adi(this.zzcsn.position(), this.zzcsn.limit());
      } else {
         this.zzcsn.put(var3);
      }
   }

   public final void zzK(byte[] var1) throws IOException {
      int var4 = var1.length;
      if (this.zzcsn.remaining() >= var4) {
         this.zzcsn.put(var1, 0, var4);
      } else {
         throw new adi(this.zzcsn.position(), this.zzcsn.limit());
      }
   }

   public final void zzt(int var1, int var2) throws IOException {
      this.zzcu(var1 << 3 | var2);
   }

   public static int zzct(int var0) {
      return zzcv(var0 << 3);
   }

   public final void zzcu(int var1) throws IOException {
      while((var1 & -128) != 0) {
         this.zzcs(var1 & 127 | 128);
         var1 >>>= 7;
      }

      this.zzcs(var1);
   }

   public static int zzcv(int var0) {
      if ((var0 & -128) == 0) {
         return 1;
      } else if ((var0 & -16384) == 0) {
         return 2;
      } else if ((var0 & -2097152) == 0) {
         return 3;
      } else {
         return (var0 & -268435456) == 0 ? 4 : 5;
      }
   }

   private final void zzaO(long var1) throws IOException {
      while((var1 & -128L) != 0L) {
         this.zzcs((int)var1 & 127 | 128);
         var1 >>>= 7;
      }

      this.zzcs((int)var1);
   }

   public static int zzaP(long var0) {
      if ((var0 & -128L) == 0L) {
         return 1;
      } else if ((var0 & -16384L) == 0L) {
         return 2;
      } else if ((var0 & -2097152L) == 0L) {
         return 3;
      } else if ((var0 & -268435456L) == 0L) {
         return 4;
      } else if ((var0 & -34359738368L) == 0L) {
         return 5;
      } else if ((var0 & -4398046511104L) == 0L) {
         return 6;
      } else if ((var0 & -562949953421312L) == 0L) {
         return 7;
      } else if ((var0 & -72057594037927936L) == 0L) {
         return 8;
      } else {
         return (var0 & Long.MIN_VALUE) == 0L ? 9 : 10;
      }
   }

   private final void zzaQ(long var1) throws IOException {
      if (this.zzcsn.remaining() < 8) {
         throw new adi(this.zzcsn.position(), this.zzcsn.limit());
      } else {
         this.zzcsn.putLong(var1);
      }
   }

   public static int zzcw(int var0) {
      return var0 << 1 ^ var0 >> 31;
   }

   private static long zzaR(long var0) {
      return var0 << 1 ^ var0 >> 63;
   }
}
