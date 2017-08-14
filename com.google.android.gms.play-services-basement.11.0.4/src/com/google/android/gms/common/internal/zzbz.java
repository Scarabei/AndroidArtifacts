package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

public final class zzbz {
   public static String zza(String var0, String var1, Context var2, AttributeSet var3, boolean var4, boolean var5, String var6) {
      String var10000 = var3 == null ? null : var3.getAttributeValue(var0, var1);
      String var7 = var10000;
      if (var10000 != null && var7.startsWith("@string/")) {
         String var8 = var7.substring(8);
         String var9 = var2.getPackageName();
         TypedValue var10 = new TypedValue();

         try {
            var2.getResources().getValue((new StringBuilder(8 + String.valueOf(var9).length() + String.valueOf(var8).length())).append(var9).append(":string/").append(var8).toString(), var10, true);
         } catch (NotFoundException var13) {
            Log.w(var6, (new StringBuilder(30 + String.valueOf(var1).length() + String.valueOf(var7).length())).append("Could not find resource for ").append(var1).append(": ").append(var7).toString());
         }

         if (var10.string != null) {
            var7 = var10.string.toString();
         } else {
            String var11 = String.valueOf(var10);
            Log.w(var6, (new StringBuilder(28 + String.valueOf(var1).length() + String.valueOf(var11).length())).append("Resource ").append(var1).append(" was not a string: ").append(var11).toString());
         }
      }

      return var7;
   }
}
