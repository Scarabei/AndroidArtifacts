package com.google.android.gms.common.internal;

import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class zzai {
   private static final Uri zzaHX;
   private static final Uri zzaHY;

   public static Intent zzcD(String var0) {
      Uri var1 = Uri.fromParts("package", var0, (String)null);
      Intent var2;
      (var2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS")).setData(var1);
      return var2;
   }

   public static Intent zzw(String var0, @Nullable String var1) {
      Intent var2;
      Intent var10000 = var2 = new Intent("android.intent.action.VIEW");
      Builder var5 = Uri.parse("market://details").buildUpon().appendQueryParameter("id", var0);
      if (!TextUtils.isEmpty(var1)) {
         var5.appendQueryParameter("pcampaignid", var1);
      }

      var10000.setData(var5.build());
      var2.setPackage("com.android.vending");
      var2.addFlags(524288);
      return var2;
   }

   public static Intent zzrD() {
      Intent var0;
      (var0 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION")).setPackage("com.google.android.wearable.app");
      return var0;
   }

   static {
      zzaHY = (zzaHX = Uri.parse("https://plus.google.com/")).buildUpon().appendPath("circles").appendPath("find").build();
   }
}
