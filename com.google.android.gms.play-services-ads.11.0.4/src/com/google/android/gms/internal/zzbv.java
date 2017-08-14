package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzbv {
   private static boolean zzlP = false;
   private static MessageDigest zzlQ = null;
   private static final Object zzlR = new Object();
   private static final Object zzlS = new Object();
   static CountDownLatch zzlT = new CountDownLatch(1);

   static void zzw() {
      Object var0 = zzlS;
      synchronized(zzlS) {
         if (!zzlP) {
            zzlP = true;
            (new Thread(new zzbx((zzbw)null))).start();
         }

      }
   }

   private static MessageDigest zzx() {
      zzw();
      boolean var0 = false;

      try {
         var0 = zzlT.await(2L, TimeUnit.SECONDS);
      } catch (InterruptedException var1) {
         ;
      }

      if (!var0) {
         return null;
      } else {
         return zzlQ == null ? null : zzlQ;
      }
   }

   static String zza(zzax var0, String var1, boolean var2) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      byte[] var3 = adp.zzc(var0);
      byte[] var10000;
      if (var2) {
         var10000 = zza(var3, var1, true);
      } else {
         String var7 = var1;
         Vector var8;
         if ((var8 = zza(var3, 255)) != null && var8.size() != 0) {
            zzbe var9;
            (var9 = new zzbe()).zzcJ = new byte[var8.size()][];
            int var10 = 0;

            byte[] var12;
            for(Iterator var11 = var8.iterator(); var11.hasNext(); var9.zzcJ[var10++] = var12) {
               var12 = zza((byte[])var11.next(), var7, false);
            }

            var9.zzcE = zzb(var3);
            var10000 = adp.zzc(var9);
         } else {
            var10000 = zza(adp.zzc(zzb(4096L)), var1, true);
         }
      }

      return zzbt.zza(var10000, true);
   }

   private static Vector zza(byte[] var0, int var1) {
      if (var0 != null && var0.length > 0) {
         int var2 = (var0.length + 255 - 1) / 255;
         Vector var3 = new Vector();

         try {
            for(int var6 = 0; var6 < var2; ++var6) {
               int var4 = var6 * 255;
               int var5 = var0.length - var4 > 255 ? var4 + 255 : var0.length;
               var3.add(Arrays.copyOfRange(var0, var4, var5));
            }

            return var3;
         } catch (IndexOutOfBoundsException var7) {
            return null;
         }
      } else {
         return null;
      }
   }

   private static zzax zzb(long var0) {
      zzax var2;
      (var2 = new zzax()).zzbq = 4096L;
      return var2;
   }

   private static byte[] zza(byte[] var0, String var1, boolean var2) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      int var3 = var2 ? 239 : 255;
      if (var0.length > var3) {
         var0 = adp.zzc(zzb(4096L));
      }

      byte[] var4;
      byte[] var5;
      if (var0.length < var3) {
         var5 = new byte[var3 - var0.length];
         (new SecureRandom()).nextBytes(var5);
         var4 = ByteBuffer.allocate(var3 + 1).put((byte)var0.length).put(var0).put(var5).array();
      } else {
         var4 = ByteBuffer.allocate(var3 + 1).put((byte)var0.length).put(var0).array();
      }

      byte[] var6;
      if (var2) {
         var6 = zzb(var4);
         var5 = ByteBuffer.allocate(256).put(var6).put(var4).array();
      } else {
         var5 = var4;
      }

      var6 = new byte[256];
      (new zzby()).zza(var5, var6);
      if (var1 != null && var1.length() > 0) {
         String var7 = var1;
         if (var1.length() > 32) {
            var7 = var1.substring(0, 32);
         }

         byte[] var9 = var7.getBytes("UTF-8");
         (new acg(var9)).zzG(var6);
      }

      return var6;
   }

   public static byte[] zzb(byte[] var0) throws NoSuchAlgorithmException {
      Object var1 = zzlR;
      synchronized(zzlR) {
         MessageDigest var2;
         if ((var2 = zzx()) == null) {
            throw new NoSuchAlgorithmException("Cannot compute hash");
         } else {
            var2.reset();
            var2.update(var0);
            return zzlQ.digest();
         }
      }
   }

   static String zza(String var0, String var1, boolean var2) {
      zzme var3 = zzmo.zzEO;
      boolean var5 = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue();
      byte[] var6;
      return (var6 = zza(var0, var1, true, var5)) != null ? zzbt.zza(var6, true) : Integer.toString(7);
   }

   private static byte[] zza(String var0, String var1, boolean var2, boolean var3) {
      zzbb var4 = new zzbb();

      try {
         byte[] var5 = var0.length() < 3 ? var0.getBytes("ISO-8859-1") : zzbt.zza(var0, true);
         var4.zzcC = var5;
         byte[] var6 = var1.length() < 3 ? var1.getBytes("ISO-8859-1") : zzbt.zza(var1, true);
         var4.zzcD = var6;
         return adp.zzc(var4);
      } catch (NoSuchAlgorithmException | UnsupportedEncodingException var7) {
         return null;
      }
   }

   // $FF: synthetic method
   static MessageDigest zza(MessageDigest var0) {
      zzlQ = var0;
      return var0;
   }
}
