package com.google.android.gms.common.util;

import android.util.Base64;

public final class zzc {
   public static String encode(byte[] var0) {
      return var0 == null ? null : Base64.encodeToString(var0, 0);
   }

   public static String zzg(byte[] var0) {
      return var0 == null ? null : Base64.encodeToString(var0, 10);
   }

   public static String zzh(byte[] var0) {
      return var0 == null ? null : Base64.encodeToString(var0, 11);
   }
}
