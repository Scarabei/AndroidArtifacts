package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzbgz;
import com.google.android.gms.internal.zzbha;

public final class zzbd {
   private static Object zzuF = new Object();
   private static boolean zzRk;
   private static String zzaIf;
   private static int zzaIg;

   public static String zzaD(Context var0) {
      zzaF(var0);
      return zzaIf;
   }

   public static int zzaE(Context var0) {
      zzaF(var0);
      return zzaIg;
   }

   private static void zzaF(Context var0) {
      Object var1 = zzuF;
      synchronized(zzuF) {
         if (!zzRk) {
            zzRk = true;
            String var2 = var0.getPackageName();
            zzbgz var3 = zzbha.zzaP(var0);

            try {
               Bundle var5;
               if ((var5 = var3.getApplicationInfo(var2, 128).metaData) == null) {
                  return;
               }

               zzaIf = var5.getString("com.google.app.id");
               zzaIg = var5.getInt("com.google.android.gms.version");
            } catch (NameNotFoundException var7) {
               Log.wtf("MetadataValueReader", "This should never happen.", var7);
            }

         }
      }
   }
}
