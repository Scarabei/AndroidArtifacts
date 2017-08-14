package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.internal.zzbha;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class zzx {
   private static final Method zzaJZ = zzsg();
   private static final Method zzaKa = zzsh();
   private static final Method zzaKb = zzsi();
   private static final Method zzaKc = zzsj();
   private static final Method zzaKd = zzsk();

   private static WorkSource zze(int var0, String var1) {
      WorkSource var2;
      WorkSource var10000 = var2 = new WorkSource();
      String var5 = var1;
      int var4 = var0;
      WorkSource var3 = var10000;
      if (zzaKa != null) {
         if (var1 == null) {
            var5 = "";
         }

         try {
            zzaKa.invoke(var3, var4, var5);
         } catch (Exception var8) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", var8);
         }
      } else if (zzaJZ != null) {
         try {
            zzaJZ.invoke(var3, var4);
         } catch (Exception var7) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", var7);
         }
      }

      return var2;
   }

   @Nullable
   public static WorkSource zzD(Context var0, String var1) {
      if (var0 != null && var0.getPackageManager() != null) {
         String var10001;
         ApplicationInfo var2;
         String var10002;
         String var10003;
         try {
            var2 = zzbha.zzaP(var0).getApplicationInfo(var1, 0);
         } catch (NameNotFoundException var3) {
            var10002 = String.valueOf(var1);
            if (var10002.length() != 0) {
               var10001 = "Could not find package: ".concat(var10002);
            } else {
               var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Could not find package: ");
            }

            Log.e("WorkSourceUtil", var10001);
            return null;
         }

         if (var2 == null) {
            var10002 = String.valueOf(var1);
            if (var10002.length() != 0) {
               var10001 = "Could not get applicationInfo from package: ".concat(var10002);
            } else {
               var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Could not get applicationInfo from package: ");
            }

            Log.e("WorkSourceUtil", var10001);
            return null;
         } else {
            return zze(var2.uid, var1);
         }
      } else {
         return null;
      }
   }

   private static int zza(WorkSource var0) {
      if (zzaKb != null) {
         try {
            return ((Integer)zzaKb.invoke(var0)).intValue();
         } catch (Exception var2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", var2);
         }
      }

      return 0;
   }

   @Nullable
   private static String zza(WorkSource var0, int var1) {
      if (zzaKd != null) {
         try {
            return (String)zzaKd.invoke(var0, var1);
         } catch (Exception var3) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", var3);
         }
      }

      return null;
   }

   public static List zzb(@Nullable WorkSource var0) {
      int var10000 = var0 == null ? 0 : zza(var0);
      int var1 = var10000;
      if (var10000 == 0) {
         return Collections.emptyList();
      } else {
         ArrayList var3 = new ArrayList();

         for(int var4 = 0; var4 < var1; ++var4) {
            String var2;
            if (!zzt.zzcL(var2 = zza(var0, var4))) {
               var3.add(var2);
            }
         }

         return var3;
      }
   }

   public static boolean zzaM(Context var0) {
      if (var0 == null) {
         return false;
      } else if (var0.getPackageManager() == null) {
         return false;
      } else {
         return zzbha.zzaP(var0).checkPermission("android.permission.UPDATE_DEVICE_STATS", var0.getPackageName()) == 0;
      }
   }

   private static Method zzsg() {
      Method var0 = null;

      try {
         var0 = WorkSource.class.getMethod("add", Integer.TYPE);
      } catch (Exception var1) {
         ;
      }

      return var0;
   }

   private static Method zzsh() {
      Method var0 = null;
      if (zzq.zzsb()) {
         try {
            var0 = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
         } catch (Exception var1) {
            ;
         }
      }

      return var0;
   }

   private static Method zzsi() {
      Method var0 = null;

      try {
         var0 = WorkSource.class.getMethod("size");
      } catch (Exception var1) {
         ;
      }

      return var0;
   }

   private static Method zzsj() {
      Method var0 = null;

      try {
         var0 = WorkSource.class.getMethod("get", Integer.TYPE);
      } catch (Exception var1) {
         ;
      }

      return var0;
   }

   private static Method zzsk() {
      Method var0 = null;
      if (zzq.zzsb()) {
         try {
            var0 = WorkSource.class.getMethod("getName", Integer.TYPE);
         } catch (Exception var1) {
            ;
         }
      }

      return var0;
   }
}
