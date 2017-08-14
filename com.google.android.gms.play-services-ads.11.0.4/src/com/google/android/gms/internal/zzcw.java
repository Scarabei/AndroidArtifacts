package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzcw {
   private final SecureRandom zzqu = null;
   private static Cipher zzqv = null;
   private static final Object zzqw = new Object();
   private static final Object zzqx = new Object();

   public zzcw(SecureRandom var1) {
   }

   public final byte[] zzl(String var1) throws zzcx {
      try {
         byte[] var2;
         if ((var2 = zzbt.zza(var1, false)).length != 32) {
            throw new zzcx(this);
         } else {
            ByteBuffer var3 = ByteBuffer.wrap(var2, 4, 16);
            byte[] var4 = new byte[16];
            var3.get(var4);
            byte[] var5 = var4;

            for(int var6 = 0; var6 < 16; ++var6) {
               var5[var6] = (byte)(var5[var6] ^ 68);
            }

            return var4;
         }
      } catch (IllegalArgumentException var7) {
         throw new zzcx(this, var7);
      }
   }

   public final String zzc(byte[] var1, byte[] var2) throws zzcx {
      if (var1.length != 16) {
         throw new zzcx(this);
      } else {
         try {
            SecretKeySpec var3 = new SecretKeySpec(var1, "AES");
            Object var6 = zzqw;
            byte[] var4;
            byte[] var5;
            synchronized(zzqw) {
               getCipher().init(1, var3, (SecureRandom)null);
               var4 = getCipher().doFinal(var2);
               var5 = getCipher().getIV();
            }

            ByteBuffer var7;
            int var15;
            (var7 = ByteBuffer.allocate(var15 = var4.length + var5.length)).put(var5).put(var4);
            var7.flip();
            byte[] var8 = new byte[var15];
            var7.get(var8);
            return zzbt.zza(var8, false);
         } catch (NoSuchAlgorithmException var10) {
            throw new zzcx(this, var10);
         } catch (InvalidKeyException var11) {
            throw new zzcx(this, var11);
         } catch (IllegalBlockSizeException var12) {
            throw new zzcx(this, var12);
         } catch (NoSuchPaddingException var13) {
            throw new zzcx(this, var13);
         } catch (BadPaddingException var14) {
            throw new zzcx(this, var14);
         }
      }
   }

   public final byte[] zza(byte[] var1, String var2) throws zzcx {
      if (var1.length != 16) {
         throw new zzcx(this);
      } else {
         try {
            byte[] var3;
            if ((var3 = zzbt.zza(var2, false)).length <= 16) {
               throw new zzcx(this);
            } else {
               ByteBuffer var4;
               (var4 = ByteBuffer.allocate(var3.length)).put(var3);
               var4.flip();
               byte[] var5 = new byte[16];
               byte[] var6 = new byte[var3.length - 16];
               var4.get(var5);
               var4.get(var6);
               SecretKeySpec var7 = new SecretKeySpec(var1, "AES");
               Object var8 = zzqw;
               synchronized(zzqw) {
                  getCipher().init(2, var7, new IvParameterSpec(var5));
                  return getCipher().doFinal(var6);
               }
            }
         } catch (NoSuchAlgorithmException var11) {
            throw new zzcx(this, var11);
         } catch (InvalidKeyException var12) {
            throw new zzcx(this, var12);
         } catch (IllegalBlockSizeException var13) {
            throw new zzcx(this, var13);
         } catch (NoSuchPaddingException var14) {
            throw new zzcx(this, var14);
         } catch (BadPaddingException var15) {
            throw new zzcx(this, var15);
         } catch (InvalidAlgorithmParameterException var16) {
            throw new zzcx(this, var16);
         } catch (IllegalArgumentException var17) {
            throw new zzcx(this, var17);
         }
      }
   }

   private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
      Object var0 = zzqx;
      synchronized(zzqx) {
         if (zzqv == null) {
            zzqv = Cipher.getInstance("AES/CBC/PKCS5Padding");
         }

         return zzqv;
      }
   }
}
