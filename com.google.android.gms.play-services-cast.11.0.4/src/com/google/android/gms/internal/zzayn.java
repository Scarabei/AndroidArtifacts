package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api.zzf;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public final class zzayn {
   public static final zzf zzayp = new zzf();
   public static final zzf zzayq = new zzf();
   private static zzf zzayr = new zzf();
   private static zzf zzays = new zzf();
   private static Charset zzayt;
   private static String zzayu;

   static {
      Charset var0 = null;

      try {
         var0 = Charset.forName("UTF-8");
      } catch (IllegalCharsetNameException var1) {
         ;
      } catch (UnsupportedCharsetException var2) {
         ;
      }

      zzayt = var0;
      zzayu = zzaye.zzcj("com.google.cast.multizone");
   }
}
