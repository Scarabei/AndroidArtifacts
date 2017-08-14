package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzrr {
   private final zzaka zzJH;

   public zzrr(zzaka var1) {
      this.zzJH = var1;
   }

   private static Intent zzf(Uri var0) {
      if (var0 == null) {
         return null;
      } else {
         Intent var1;
         (var1 = new Intent("android.intent.action.VIEW")).addFlags(268435456);
         var1.setData(var0);
         var1.setAction("android.intent.action.VIEW");
         return var1;
      }
   }

   private static ResolveInfo zza(Context var0, Intent var1) {
      return zza(var0, var1, new ArrayList());
   }

   private static ResolveInfo zza(Context var0, Intent var1, ArrayList var2) {
      PackageManager var3;
      if ((var3 = var0.getPackageManager()) == null) {
         return null;
      } else {
         List var4 = var3.queryIntentActivities(var1, 65536);
         ResolveInfo var5 = var3.resolveActivity(var1, 65536);
         ResolveInfo var6 = null;
         if (var4 != null && var5 != null) {
            for(int var7 = 0; var7 < var4.size(); ++var7) {
               ResolveInfo var8 = (ResolveInfo)var4.get(var7);
               if (var5 != null && var5.activityInfo.name.equals(var8.activityInfo.name)) {
                  var6 = var5;
                  break;
               }
            }
         }

         var2.addAll(var4);
         return var6;
      }
   }

   private static Intent zza(Intent var0, ResolveInfo var1) {
      Intent var2;
      (var2 = new Intent(var0)).setClassName(var1.activityInfo.packageName, var1.activityInfo.name);
      return var2;
   }

   public final Intent zza(Context var1, Map var2) {
      ActivityManager var3 = (ActivityManager)var1.getSystemService("activity");
      String var4;
      if (TextUtils.isEmpty(var4 = (String)var2.get("u"))) {
         return null;
      } else {
         if (this.zzJH != null) {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            var4 = zzagz.zzb(this.zzJH, var4);
         }

         Uri var5;
         boolean var6;
         boolean var7;
         boolean var10000;
         label74: {
            var5 = Uri.parse(var4);
            var6 = Boolean.parseBoolean((String)var2.get("use_first_package"));
            var7 = Boolean.parseBoolean((String)var2.get("use_running_process"));
            if (!Boolean.parseBoolean((String)var2.get("use_custom_tabs"))) {
               zzme var18 = zzmo.zzGp;
               if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var18)).booleanValue()) {
                  var10000 = false;
                  break label74;
               }
            }

            var10000 = true;
         }

         boolean var8 = var10000;
         Uri var9 = null;
         if ("http".equalsIgnoreCase(var5.getScheme())) {
            var9 = var5.buildUpon().scheme("https").build();
         } else if ("https".equalsIgnoreCase(var5.getScheme())) {
            var9 = var5.buildUpon().scheme("http").build();
         }

         ArrayList var10 = new ArrayList();
         Intent var11 = zzf(var5);
         Intent var12 = zzf(var9);
         if (var8) {
            com.google.android.gms.ads.internal.zzbs.zzbz();
            zzagz.zzc(var1, var11);
            com.google.android.gms.ads.internal.zzbs.zzbz();
            zzagz.zzc(var1, var12);
         }

         ResolveInfo var13;
         if ((var13 = zza(var1, var11, var10)) != null) {
            return zza(var11, var13);
         } else {
            ResolveInfo var14;
            if (var12 != null && (var14 = zza(var1, var12)) != null) {
               Intent var15 = zza(var11, var14);
               if (zza(var1, var15) != null) {
                  return var15;
               }
            }

            if (var10.size() == 0) {
               return var11;
            } else {
               List var22;
               if (var7 && var3 != null && (var22 = var3.getRunningAppProcesses()) != null) {
                  ArrayList var19;
                  int var20 = (var19 = (ArrayList)var10).size();
                  int var21 = 0;

                  while(var21 < var20) {
                     Object var23 = var19.get(var21);
                     ++var21;
                     ResolveInfo var16 = (ResolveInfo)var23;
                     Iterator var17 = var22.iterator();

                     while(var17.hasNext()) {
                        if (((RunningAppProcessInfo)var17.next()).processName.equals(var16.activityInfo.packageName)) {
                           return zza(var11, var16);
                        }
                     }
                  }
               }

               return var6 ? zza(var11, (ResolveInfo)var10.get(0)) : var11;
            }
         }
      }
   }
}
