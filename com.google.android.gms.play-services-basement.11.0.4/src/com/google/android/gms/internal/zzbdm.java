package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.R.string;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbd;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzby;

/** @deprecated */
@Deprecated
public final class zzbdm {
   private static final Object zzuF = new Object();
   private static zzbdm zzaEB;
   private final String mAppId;
   private final Status zzaEC;
   private final boolean zzaED;
   private final boolean zzaEE;

   private zzbdm(Context var1) {
      Resources var2;
      String var3 = (var2 = var1.getResources()).getResourcePackageName(string.common_google_play_services_unknown_issue);
      boolean var4 = true;
      int var5;
      if ((var5 = var2.getIdentifier("google_app_measurement_enable", "integer", var3)) != 0) {
         var4 = var2.getInteger(var5) != 0;
         this.zzaEE = !var4;
      } else {
         this.zzaEE = false;
      }

      this.zzaED = var4;
      String var6;
      if ((var6 = zzbd.zzaD(var1)) == null) {
         var6 = (new zzby(var1)).getString("google_app_id");
      }

      if (TextUtils.isEmpty(var6)) {
         this.zzaEC = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
         this.mAppId = null;
      } else {
         this.mAppId = var6;
         this.zzaEC = Status.zzaBm;
      }
   }

   public static Status zzaz(Context var0) {
      zzbo.zzb(var0, "Context must not be null.");
      Object var1 = zzuF;
      synchronized(zzuF) {
         if (zzaEB == null) {
            zzaEB = new zzbdm(var0);
         }

         return zzaEB.zzaEC;
      }
   }

   public static String zzqA() {
      return zzcu("getGoogleAppId").mAppId;
   }

   public static boolean zzqB() {
      return zzcu("isMeasurementExplicitlyDisabled").zzaEE;
   }

   private static zzbdm zzcu(String var0) {
      Object var1 = zzuF;
      synchronized(zzuF) {
         if (zzaEB == null) {
            throw new IllegalStateException((new StringBuilder(34 + String.valueOf(var0).length())).append("Initialize must be called before ").append(var0).append(".").toString());
         } else {
            return zzaEB;
         }
      }
   }
}
