package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public final class zzcu {
   private String zzqo = "googleads.g.doubleclick.net";
   private String zzqp = "/pagead/ads";
   private String zzqq = "ad.doubleclick.net";
   private String[] zzqr = new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
   private static final String[] zzqs = new String[]{"/aclk", "/pcs/click"};
   private zzcp zzqt;

   public zzcu(zzcp var1) {
      this.zzqt = var1;
   }

   public final void zzb(String var1, String var2) {
      this.zzqo = var1;
      this.zzqp = var2;
   }

   public final boolean zza(Uri var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         try {
            return var1.getHost().equals(this.zzqo) && var1.getPath().equals(this.zzqp);
         } catch (NullPointerException var2) {
            return false;
         }
      }
   }

   private final boolean zzb(Uri var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         try {
            return var1.getHost().equals(this.zzqq);
         } catch (NullPointerException var2) {
            return false;
         }
      }
   }

   public final boolean zzc(Uri param1) {
      // $FF: Couldn't be decompiled
   }

   public final void zzk(String var1) {
      this.zzqr = var1.split(",");
   }

   public final zzcp zzB() {
      return this.zzqt;
   }

   public final Uri zza(Uri var1, Context var2) throws zzcv {
      return this.zza(var1, var2, (String)null, false, (View)null);
   }

   public final void zza(MotionEvent var1) {
      this.zzqt.zza(var1);
   }

   public final Uri zza(Uri var1, Context var2, View var3) throws zzcv {
      try {
         return this.zza(var1, var2, var1.getQueryParameter("ai"), true, var3);
      } catch (UnsupportedOperationException var4) {
         throw new zzcv("Provided Uri is not in a valid state");
      }
   }

   public final boolean zzd(Uri var1) {
      if (this.zzc(var1)) {
         String[] var2 = zzqs;
         int var3 = zzqs.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if (var1.getPath().endsWith(var5)) {
               return true;
            }
         }
      }

      return false;
   }

   private final Uri zza(Uri var1, Context var2, String var3, boolean var4, View var5) throws zzcv {
      try {
         boolean var6;
         if (var6 = this.zzb(var1)) {
            if (var1.toString().contains("dc_ms=")) {
               throw new zzcv("Parameter already exists: dc_ms");
            }
         } else if (var1.getQueryParameter("ms") != null) {
            throw new zzcv("Query parameter already exists: ms");
         }

         String var7;
         if (var4) {
            var7 = this.zzqt.zza(var2, var3, var5);
         } else {
            var7 = this.zzqt.zza(var2);
         }

         String var9;
         String var11;
         int var12;
         if (var6) {
            var9 = "dc_ms";
            if ((var12 = (var11 = var1.toString()).indexOf(";adurl")) != -1) {
               return Uri.parse(var11.substring(0, var12 + 1) + var9 + "=" + var7 + ";" + var11.substring(var12 + 1));
            } else {
               String var13 = var1.getEncodedPath();
               int var14 = var11.indexOf(var13);
               return Uri.parse(var11.substring(0, var14 + var13.length()) + ";" + var9 + "=" + var7 + ";" + var11.substring(var14 + var13.length()));
            }
         } else {
            var9 = "ms";
            if ((var12 = (var11 = var1.toString()).indexOf("&adurl")) == -1) {
               var12 = var11.indexOf("?adurl");
            }

            return var12 != -1 ? Uri.parse(var11.substring(0, var12 + 1) + var9 + "=" + var7 + "&" + var11.substring(var12 + 1)) : var1.buildUpon().appendQueryParameter(var9, var7).build();
         }
      } catch (UnsupportedOperationException var15) {
         throw new zzcv("Provided Uri is not in a valid state");
      }
   }
}
