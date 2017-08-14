package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public final class zzaot extends zzamh {
   private String zzaeI;
   private String zzaeH;
   private int zzagX;
   protected boolean zzaiP;
   protected int zzahZ;
   private boolean zzaiQ;
   private boolean zzadF;

   public zzaot(zzamj var1) {
      super(var1);
   }

   protected final void zzjD() {
      ApplicationInfo var2 = null;
      Context var3 = this.getContext();

      try {
         var2 = var3.getPackageManager().getApplicationInfo(var3.getPackageName(), 129);
      } catch (NameNotFoundException var11) {
         this.zzd("PackageManager doesn't know about the app package", var11);
      }

      if (var2 == null) {
         this.zzbr("Couldn't get ApplicationInfo to load global config");
      } else {
         Bundle var4 = var2.metaData;
         int var5;
         zzanw var6;
         if (var2.metaData != null && (var5 = var4.getInt("com.google.android.gms.analytics.globalConfigResource")) > 0 && (var6 = (zzanw)(new zzanu(this.zzkp())).zzP(var5)) != null) {
            this.zzbo("Loading global XML config values");
            String var9;
            if (var6.zzaeH != null) {
               var9 = var6.zzaeH;
               this.zzaeH = var9;
               this.zzb("XML config - app name", var9);
            }

            if (var6.zzaeI != null) {
               var9 = var6.zzaeI;
               this.zzaeI = var9;
               this.zzb("XML config - app version", var9);
            }

            int var12;
            if (var6.zzahY != null) {
               String var10 = var6.zzahY.toLowerCase();
               if ((var12 = "verbose".equals(var10) ? 0 : ("info".equals(var10) ? 1 : ("warning".equals(var10) ? 2 : ("error".equals(var10) ? 3 : -1)))) >= 0) {
                  this.zzagX = var12;
                  this.zza("XML config - log level", var12);
               }
            }

            if (var6.zzahZ >= 0) {
               var12 = var6.zzahZ;
               this.zzahZ = var12;
               this.zzaiP = true;
               this.zzb("XML config - dispatch period (sec)", var12);
            }

            if (var6.zzaia != -1) {
               boolean var13 = var6.zzaia == 1;
               this.zzadF = var13;
               this.zzaiQ = true;
               this.zzb("XML config - dry run", var13);
            }
         }

      }
   }

   public final String zzjH() {
      this.zzkD();
      return this.zzaeI;
   }

   public final String zzjG() {
      this.zzkD();
      return this.zzaeH;
   }

   public final boolean zzmg() {
      this.zzkD();
      return false;
   }

   public final boolean zzmh() {
      this.zzkD();
      return this.zzaiQ;
   }

   public final boolean zzmi() {
      this.zzkD();
      return this.zzadF;
   }
}
