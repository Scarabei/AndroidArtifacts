package com.google.android.gms.internal;

import android.os.Looper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public final class zzdg {
   private static final char[] zzri = "0123456789abcdef".toCharArray();

   public static String zzn(String var0) {
      if (var0 != null && var0.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
         UUID var1 = UUID.fromString(var0);
         byte[] var2;
         ByteBuffer var3;
         (var3 = ByteBuffer.wrap(var2 = new byte[16])).putLong(var1.getMostSignificantBits());
         var3.putLong(var1.getLeastSignificantBits());
         var0 = zzbt.zza(var2, true);
      }

      return var0;
   }

   public static String zza(Throwable var0) {
      StringWriter var1 = new StringWriter();
      var0.printStackTrace(new PrintWriter(var1));
      return var1.toString();
   }

   public static Long zzR() {
      return Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")).getTime().getTime();
   }

   public static boolean zzo(String var0) {
      return var0 == null || var0.isEmpty();
   }

   public static boolean zzS() {
      return Looper.myLooper() == Looper.getMainLooper();
   }
}
