package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class hi {
   private static Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
   private static Uri zzbUa = Uri.parse("content://com.google.android.gsf.gservices/prefix");
   private static Pattern zzbUb = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
   private static Pattern zzbUc = Pattern.compile("^(0|false|f|off|no|n)$", 2);
   private static final AtomicBoolean zzbUd = new AtomicBoolean();
   private static HashMap zzbUe;
   private static HashMap zzbUf = new HashMap();
   private static HashMap zzbUg = new HashMap();
   private static HashMap zzbUh = new HashMap();
   private static HashMap zzbUi = new HashMap();
   private static Object zzbUj;
   private static boolean zzbUk;
   private static String[] zzbUl = new String[0];

   private static void zza(ContentResolver var0) {
      if (zzbUe == null) {
         zzbUd.set(false);
         zzbUe = new HashMap();
         zzbUj = new Object();
         zzbUk = false;
         var0.registerContentObserver(CONTENT_URI, true, new hj((Handler)null));
      } else {
         if (zzbUd.getAndSet(false)) {
            zzbUe.clear();
            zzbUf.clear();
            zzbUg.clear();
            zzbUh.clear();
            zzbUi.clear();
            zzbUj = new Object();
            zzbUk = false;
         }

      }
   }

   public static String zza(ContentResolver var0, String var1, String var2) {
      Class var4 = hi.class;
      String var17;
      Object var3;
      synchronized(hi.class) {
         zza(var0);
         var3 = zzbUj;
         if (zzbUe.containsKey(var1)) {
            return (var17 = (String)zzbUe.get(var1)) != null ? var17 : null;
         }

         String[] var5 = zzbUl;
         int var6 = zzbUl.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            String var8 = var5[var7];
            if (var1.startsWith(var8)) {
               if (!zzbUk || zzbUe.isEmpty()) {
                  zzc(var0, zzbUl);
                  if (zzbUe.containsKey(var1)) {
                     String var9;
                     return (var9 = (String)zzbUe.get(var1)) != null ? var9 : null;
                  }
               }

               return null;
            }
         }
      }

      Cursor var16 = var0.query(CONTENT_URI, (String[])null, (String)null, new String[]{var1}, (String)null);

      try {
         if (var16 != null && var16.moveToFirst()) {
            if ((var17 = var16.getString(1)) != null && var17.equals((Object)null)) {
               var17 = null;
            }

            zza(var3, var1, var17);
            String var18 = var17 != null ? var17 : null;
            return var18;
         }

         zza((Object)var3, var1, (String)null);
      } finally {
         if (var16 != null) {
            var16.close();
         }

      }

      return null;
   }

   private static void zza(Object var0, String var1, String var2) {
      Class var3 = hi.class;
      synchronized(hi.class) {
         if (var0 == zzbUj) {
            zzbUe.put(var1, var2);
         }

      }
   }

   public static long getLong(ContentResolver var0, String var1, long var2) {
      Object var4 = zzb(var0);
      Long var5;
      if ((var5 = (Long)zza((HashMap)zzbUh, var1, (Object)0L)) != null) {
         return var5.longValue();
      } else {
         String var6 = zza((ContentResolver)var0, var1, (String)null);

         long var7;
         try {
            if (var6 == null) {
               var7 = 0L;
            } else {
               var5 = var7 = Long.parseLong(var6);
            }
         } catch (NumberFormatException var16) {
            var7 = 0L;
         }

         Long var12 = var5;
         String var11 = var1;
         HashMap var10 = zzbUh;
         Object var9 = var4;
         Class var13 = hi.class;
         synchronized(hi.class) {
            if (var9 == zzbUj) {
               var10.put(var11, var12);
               zzbUe.remove(var11);
            }

            return var7;
         }
      }
   }

   private static Map zza(ContentResolver var0, String... var1) {
      Cursor var2 = var0.query(zzbUa, (String[])null, (String)null, var1, (String)null);
      TreeMap var3 = new TreeMap();
      if (var2 == null) {
         return var3;
      } else {
         try {
            while(var2.moveToNext()) {
               var3.put(var2.getString(0), var2.getString(1));
            }
         } finally {
            var2.close();
         }

         return var3;
      }
   }

   public static void zzb(ContentResolver var0, String... var1) {
      if (var1.length != 0) {
         Class var2 = hi.class;
         synchronized(hi.class) {
            zza(var0);
            int var6 = (zzbUl.length + var1.length << 2) / 3 + 1;
            HashSet var7;
            (var7 = new HashSet(var6)).addAll(Arrays.asList(zzbUl));
            ArrayList var8 = new ArrayList();
            String[] var9;
            int var10 = (var9 = var1).length;

            for(int var11 = 0; var11 < var10; ++var11) {
               String var12 = var9[var11];
               if (var7.add(var12)) {
                  var8.add(var12);
               }
            }

            String[] var10000;
            if (var8.isEmpty()) {
               var10000 = new String[0];
            } else {
               zzbUl = (String[])var7.toArray(new String[var7.size()]);
               var10000 = (String[])var8.toArray(new String[var8.size()]);
            }

            String[] var3 = var10000;
            if (zzbUk && !zzbUe.isEmpty()) {
               if (var3.length != 0) {
                  zzc(var0, var3);
               }
            } else {
               zzc(var0, zzbUl);
            }

         }
      }
   }

   private static void zzc(ContentResolver var0, String[] var1) {
      zzbUe.putAll(zza(var0, var1));
      zzbUk = true;
   }

   private static Object zzb(ContentResolver var0) {
      Class var1 = hi.class;
      synchronized(hi.class) {
         zza(var0);
         return zzbUj;
      }
   }

   private static Object zza(HashMap var0, String var1, Object var2) {
      Class var3 = hi.class;
      synchronized(hi.class) {
         if (var0.containsKey(var1)) {
            Object var4;
            return (var4 = var0.get(var1)) != null ? var4 : var2;
         } else {
            return null;
         }
      }
   }

   // $FF: synthetic method
   static AtomicBoolean zzEc() {
      return zzbUd;
   }
}
