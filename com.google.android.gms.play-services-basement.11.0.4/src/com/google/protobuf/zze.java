package com.google.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zze {
   private static final Logger logger = Logger.getLogger(zze.class.getName());
   private static final Unsafe zzcrT = zzLv();
   private static final Class zzcrU = zzhP("libcore.io.Memory");
   private static final boolean zzcrV = zzhP("org.robolectric.Robolectric") != null;
   private static final boolean zzcrW;
   private static final boolean zzcrX;
   private static final zze.zzd zzcrY;
   private static final boolean zzcrZ;
   private static final boolean zzcrM;
   private static final boolean zzcsa;
   private static final long zzcrN;
   private static final long zzcsb;
   private static final boolean zzcsc;

   static boolean zzLt() {
      return zzcrM;
   }

   static long zzLu() {
      return zzcrN;
   }

   private static Unsafe zzLv() {
      Unsafe var0 = null;

      try {
         var0 = (Unsafe)AccessController.doPrivileged(new zzf());
      } catch (Throwable var1) {
         ;
      }

      return var0;
   }

   private static boolean zzLw() {
      if (zzcrT == null) {
         return false;
      } else {
         try {
            Class var0;
            (var0 = zzcrT.getClass()).getMethod("objectFieldOffset", Field.class);
            var0.getMethod("arrayBaseOffset", Class.class);
            var0.getMethod("getInt", Object.class, Long.TYPE);
            var0.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            var0.getMethod("getLong", Object.class, Long.TYPE);
            var0.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            var0.getMethod("getObject", Object.class, Long.TYPE);
            var0.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzLz()) {
               return true;
            } else {
               var0.getMethod("getByte", Object.class, Long.TYPE);
               var0.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
               var0.getMethod("getBoolean", Object.class, Long.TYPE);
               var0.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
               var0.getMethod("getFloat", Object.class, Long.TYPE);
               var0.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
               var0.getMethod("getDouble", Object.class, Long.TYPE);
               var0.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
               return true;
            }
         } catch (Throwable var2) {
            Logger var10000 = logger;
            Level var10001 = Level.WARNING;
            String var1 = String.valueOf(var2);
            var10000.logp(var10001, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", (new StringBuilder(71 + String.valueOf(var1).length())).append("platform method missing - proto runtime falling back to safer methods: ").append(var1).toString());
            return false;
         }
      }
   }

   private static boolean zzLx() {
      if (zzcrT == null) {
         return false;
      } else {
         try {
            zzcrT.getClass().getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
         } catch (Throwable var0) {
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeCopyMemory", "copyMemory is missing from platform - proto runtime falling back to safer methods.");
            return false;
         }
      }
   }

   private static boolean zzLy() {
      if (zzcrT == null) {
         return false;
      } else {
         try {
            Class var0;
            (var0 = zzcrT.getClass()).getMethod("objectFieldOffset", Field.class);
            var0.getMethod("getLong", Object.class, Long.TYPE);
            if (zzLz()) {
               return true;
            } else {
               var0.getMethod("getByte", Long.TYPE);
               var0.getMethod("putByte", Long.TYPE, Byte.TYPE);
               var0.getMethod("getInt", Long.TYPE);
               var0.getMethod("putInt", Long.TYPE, Integer.TYPE);
               var0.getMethod("getLong", Long.TYPE);
               var0.getMethod("putLong", Long.TYPE, Long.TYPE);
               var0.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
               return true;
            }
         } catch (Throwable var2) {
            Logger var10000 = logger;
            Level var10001 = Level.WARNING;
            String var1 = String.valueOf(var2);
            var10000.logp(var10001, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", (new StringBuilder(71 + String.valueOf(var1).length())).append("platform method missing - proto runtime falling back to safer methods: ").append(var1).toString());
            return false;
         }
      }
   }

   private static boolean zzg(Class var0) {
      if (!zzLz()) {
         return false;
      } else {
         try {
            Class var1;
            (var1 = zzcrU).getMethod("peekLong", var0, Boolean.TYPE);
            var1.getMethod("pokeLong", var0, Long.TYPE, Boolean.TYPE);
            var1.getMethod("pokeInt", var0, Integer.TYPE, Boolean.TYPE);
            var1.getMethod("peekInt", var0, Boolean.TYPE);
            var1.getMethod("pokeByte", var0, Byte.TYPE);
            var1.getMethod("peekByte", var0);
            var1.getMethod("pokeByteArray", var0, byte[].class, Integer.TYPE, Integer.TYPE);
            var1.getMethod("peekByteArray", var0, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
         } catch (Throwable var2) {
            return false;
         }
      }
   }

   private static boolean zzLz() {
      return zzcrU != null && !zzcrV;
   }

   private static Class zzhP(String var0) {
      try {
         return Class.forName(var0);
      } catch (Throwable var1) {
         return null;
      }
   }

   private static Field zza(Class var0, String var1) {
      Field var2;
      try {
         (var2 = var0.getDeclaredField(var1)).setAccessible(true);
      } catch (Throwable var3) {
         var2 = null;
      }

      return var2;
   }

   static {
      zzcrW = zzg(Long.TYPE);
      zzcrX = zzg(Integer.TYPE);
      zzcrY = (zze.zzd)(zzcrT == null ? null : (zzLz() ? (zzcrW ? new zze.zzb(zzcrT) : (zzcrX ? new zze.zza(zzcrT) : null)) : new zze.zzc(zzcrT)));
      zzcrZ = zzLy();
      zzcrM = zzLw();
      zzcsa = zzLx();
      int var10000;
      if (zzcrM) {
         Class var1 = byte[].class;
         var10000 = zzcrY.zzcsd.arrayBaseOffset(var1);
      } else {
         var10000 = -1;
      }

      zzcrN = (long)var10000;
      Field var0;
      Field var3 = zzLz() && (var0 = zza(Buffer.class, "effectiveDirectAddress")) != null ? var0 : zza(Buffer.class, "address");
      var0 = var3;
      zzcsb = var3 != null && zzcrY != null ? zzcrY.zzcsd.objectFieldOffset(var0) : -1L;
      zzcsc = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   }

   static final class zza extends zze.zzd {
      zza(Unsafe var1) {
         super(var1);
      }
   }

   static final class zzb extends zze.zzd {
      zzb(Unsafe var1) {
         super(var1);
      }
   }

   static final class zzc extends zze.zzd {
      zzc(Unsafe var1) {
         super(var1);
      }
   }

   abstract static class zzd {
      Unsafe zzcsd;

      zzd(Unsafe var1) {
         this.zzcsd = var1;
      }
   }
}
