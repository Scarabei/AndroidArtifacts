package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Looper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

@zzzn
public final class zzzi implements zzzl {
   private static final Object zzuF = new Object();
   private static zzzl zzSg = null;
   private final Object zzSh = new Object();
   private final String mPackageName;
   private final zzaje zzuK;
   private final WeakHashMap zzSi = new WeakHashMap();

   public static zzzl zzc(Context var0, zzaje var1) {
      Object var2 = zzuF;
      synchronized(zzuF) {
         if (zzSg == null) {
            zzme var5 = zzmo.zzCc;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue()) {
               String var3 = "unknown";

               try {
                  var3 = var0.getApplicationContext().getPackageName();
               } catch (Throwable var6) {
                  zzafr.zzaT("Cannot obtain package name, proceeding.");
               }

               zzSg = new zzzi(var3, var1);
            } else {
               zzSg = new zzzm();
            }
         }
      }

      return zzSg;
   }

   private zzzi(String var1, zzaje var2) {
      this.mPackageName = var1;
      this.zzuK = var2;
      Thread var6 = Looper.getMainLooper().getThread();
      zzzi var5 = this;
      if (var6 != null) {
         Object var7 = this.zzSh;
         synchronized(this.zzSh) {
            var5.zzSi.put(var6, true);
         }

         UncaughtExceptionHandler var10 = var6.getUncaughtExceptionHandler();
         var6.setUncaughtExceptionHandler(new zzzk(this, var10));
      }

      UncaughtExceptionHandler var4 = Thread.getDefaultUncaughtExceptionHandler();
      Thread.setDefaultUncaughtExceptionHandler(new zzzj(this, var4));
   }

   protected final void zza(Thread var1, Throwable var2) {
      boolean var10000;
      label44: {
         Throwable var4 = var2;
         zzzi var3 = this;
         if (var2 != null) {
            boolean var5 = false;

            boolean var6;
            for(var6 = false; var4 != null; var4 = var4.getCause()) {
               StackTraceElement[] var7;
               int var8 = (var7 = var4.getStackTrace()).length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  StackTraceElement var10;
                  if (zzat((var10 = var7[var9]).getClassName())) {
                     var5 = true;
                  }

                  if (var3.getClass().getName().equals(var10.getClassName())) {
                     var6 = true;
                  }
               }
            }

            if (var5 && !var6) {
               var10000 = true;
               break label44;
            }
         }

         var10000 = false;
      }

      if (var10000) {
         this.zza(var2, "");
      }

   }

   private static boolean zzat(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return false;
      } else {
         zzme var2 = zzmo.zzCe;
         if (var0.startsWith((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2))) {
            return true;
         } else {
            try {
               return Class.forName(var0).isAnnotationPresent(zzzn.class);
            } catch (Exception var3) {
               String var10001 = String.valueOf(var0);
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Fail to check class type for class ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Fail to check class type for class ");
               }

               zzafr.zza(var10000, var3);
               return false;
            }
         }
      }
   }

   public final void zza(Throwable var1, String var2) {
      zzme var19 = zzmo.zzCd;
      Throwable var10000;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var19)).booleanValue()) {
         var10000 = var1;
      } else {
         LinkedList var8 = new LinkedList();

         Throwable var9;
         for(var9 = var1; var9 != null; var9 = var9.getCause()) {
            var8.push(var9);
         }

         Throwable var10 = null;

         while(!var8.isEmpty()) {
            StackTraceElement[] var11 = (var9 = (Throwable)var8.pop()).getStackTrace();
            ArrayList var12;
            (var12 = new ArrayList()).add(new StackTraceElement(var9.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean var13 = false;
            StackTraceElement[] var14 = var11;
            int var15 = var11.length;

            for(int var16 = 0; var16 < var15; ++var16) {
               StackTraceElement var17;
               if (zzat((var17 = var14[var16]).getClassName())) {
                  var13 = true;
                  var12.add(var17);
               } else {
                  String var22;
                  if (!TextUtils.isEmpty(var22 = var17.getClassName()) && (var22.startsWith("android.") || var22.startsWith("java."))) {
                     var12.add(var17);
                  } else {
                     StackTraceElement var18 = new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1);
                     var12.add(var18);
                  }
               }
            }

            if (var13) {
               if (var10 == null) {
                  var10 = new Throwable(var9.getMessage());
               } else {
                  var10 = new Throwable(var9.getMessage(), var10);
               }

               var10.setStackTrace((StackTraceElement[])var12.toArray(new StackTraceElement[0]));
            }
         }

         var10000 = var10;
      }

      Throwable var3 = var10000;
      if (var10000 != null) {
         Class var4 = var1.getClass();
         ArrayList var5;
         ArrayList var21 = var5 = new ArrayList();
         StringWriter var20 = new StringWriter();
         var3.printStackTrace(new PrintWriter(var20));
         Builder var10001 = (new Builder()).scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", VERSION.RELEASE).appendQueryParameter("api", String.valueOf(VERSION.SDK_INT));
         com.google.android.gms.ads.internal.zzbs.zzbz();
         var21.add(var10001.appendQueryParameter("device", zzagz.zzhQ()).appendQueryParameter("js", this.zzuK.zzaP).appendQueryParameter("appid", this.mPackageName).appendQueryParameter("exceptiontype", var4.getName()).appendQueryParameter("stacktrace", var20.toString()).appendQueryParameter("eids", TextUtils.join(",", zzmo.zzdJ())).appendQueryParameter("exceptionkey", var2).appendQueryParameter("cl", "162978962").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", com.google.android.gms.ads.internal.zzbs.zzbD().getSessionId()).toString());
         com.google.android.gms.ads.internal.zzbs.zzbz();
         zzagz.zza((List)var5, (String)com.google.android.gms.ads.internal.zzbs.zzbD().zzht());
      }
   }
}
