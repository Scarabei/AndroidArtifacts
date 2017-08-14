package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.zzaye;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

public final class CastMediaControlIntent {
   public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
   public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";
   public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";
   public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";
   public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";
   public static final String EXTRA_CAST_LANGUAGE_CODE = "com.google.android.gms.cast.EXTRA_CAST_LANGUAGE_CODE";
   public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";
   public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";
   public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";
   public static final int ERROR_CODE_REQUEST_FAILED = 1;
   public static final int ERROR_CODE_SESSION_START_FAILED = 2;
   public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;

   public static String categoryForRemotePlayback(String var0) throws IllegalArgumentException {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException("applicationId cannot be null or empty");
      } else {
         return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", var0, (Collection)null);
      }
   }

   public static String categoryForRemotePlayback() {
      return zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", (String)null, (Collection)null);
   }

   public static String categoryForCast(String var0) throws IllegalArgumentException {
      if (var0 == null) {
         throw new IllegalArgumentException("applicationId cannot be null");
      } else {
         return zza("com.google.android.gms.cast.CATEGORY_CAST", var0, (Collection)null);
      }
   }

   public static String categoryForCast(Collection var0) throws IllegalArgumentException {
      if (var0 == null) {
         throw new IllegalArgumentException("namespaces cannot be null");
      } else {
         return zza("com.google.android.gms.cast.CATEGORY_CAST", (String)null, var0);
      }
   }

   public static String categoryForCast(String var0, Collection var1) {
      if (var0 == null) {
         throw new IllegalArgumentException("applicationId cannot be null");
      } else if (var1 == null) {
         throw new IllegalArgumentException("namespaces cannot be null");
      } else {
         return zza("com.google.android.gms.cast.CATEGORY_CAST", var0, var1);
      }
   }

   private static String zza(String var0, String var1, Collection var2) throws IllegalArgumentException {
      StringBuilder var3 = new StringBuilder(var0);
      if (var1 != null) {
         String var4;
         if (!(var4 = var1.toUpperCase()).matches("[A-F0-9]+")) {
            IllegalArgumentException var10000 = new IllegalArgumentException;
            String var10003 = String.valueOf(var1);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Invalid application ID: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Invalid application ID: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         var3.append("/").append(var4);
      }

      if (var2 != null) {
         if (var2.isEmpty()) {
            throw new IllegalArgumentException("Must specify at least one namespace");
         }

         if (var1 == null) {
            var3.append("/");
         }

         var3.append("/");
         boolean var7 = true;

         String var6;
         for(Iterator var5 = var2.iterator(); var5.hasNext(); var3.append(zzaye.zzck(var6))) {
            zzaye.zzci(var6 = (String)var5.next());
            if (var7) {
               var7 = false;
            } else {
               var3.append(",");
            }
         }
      }

      return var3.toString();
   }

   public static String languageTagForLocale(Locale var0) {
      return zzaye.zzb(var0);
   }
}
