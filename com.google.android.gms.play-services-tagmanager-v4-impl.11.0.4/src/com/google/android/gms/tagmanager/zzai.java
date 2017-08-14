package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.VisibleForTesting;
import java.util.Random;

public final class zzai {
   private final Context mContext;
   private final Random zzAO;
   private final String zzbDw;

   public zzai(Context var1, String var2) {
      this(var1, var2, new Random());
   }

   @VisibleForTesting
   private zzai(Context var1, String var2, Random var3) {
      this.mContext = (Context)com.google.android.gms.common.internal.zzbo.zzu(var1);
      this.zzbDw = (String)com.google.android.gms.common.internal.zzbo.zzu(var2);
      this.zzAO = var3;
   }

   public final long zzAS() {
      return 43200000L + this.zzg(7200000L, 259200000L);
   }

   public final long zzAT() {
      return 3600000L + this.zzg(600000L, 86400000L);
   }

   private final long zzg(long var1, long var3) {
      SharedPreferences var5 = this.zzAW();
      long var6 = Math.max(0L, var5.getLong("FORBIDDEN_COUNT", 0L));
      long var8 = Math.max(0L, var5.getLong("SUCCESSFUL_COUNT", 0L));
      float var10 = (float)var6 / (float)(var6 + var8 + 1L);
      long var11 = var1 + (long)(var10 * (float)(var3 - var1));
      return (long)(this.zzAO.nextFloat() * (float)var11);
   }

   @SuppressLint({"CommitPrefEdits"})
   public final void zzAU() {
      SharedPreferences var1;
      long var2 = (var1 = this.zzAW()).getLong("FORBIDDEN_COUNT", 0L);
      long var4 = var1.getLong("SUCCESSFUL_COUNT", 0L);
      Editor var6 = var1.edit();
      if (var2 == 0L) {
         var2 = 3L;
      } else {
         var2 = Math.min(10L, var2 + 1L);
      }

      var4 = Math.max(0L, Math.min(var4, 10L - var2));
      var6.putLong("FORBIDDEN_COUNT", var2);
      var6.putLong("SUCCESSFUL_COUNT", var4);
      var6.apply();
   }

   @SuppressLint({"CommitPrefEdits"})
   public final void zzAV() {
      SharedPreferences var1;
      long var2 = (var1 = this.zzAW()).getLong("SUCCESSFUL_COUNT", 0L);
      long var4 = var1.getLong("FORBIDDEN_COUNT", 0L);
      var2 = Math.min(10L, var2 + 1L);
      var4 = Math.max(0L, Math.min(var4, 10L - var2));
      Editor var6;
      (var6 = var1.edit()).putLong("SUCCESSFUL_COUNT", var2);
      var6.putLong("FORBIDDEN_COUNT", var4);
      var6.apply();
   }

   private final SharedPreferences zzAW() {
      Context var10000 = this.mContext;
      String var10001 = String.valueOf("_gtmContainerRefreshPolicy_");
      String var10002 = String.valueOf(this.zzbDw);
      if (var10002.length() != 0) {
         var10001 = var10001.concat(var10002);
      } else {
         String var10003 = new String;
         var10002 = var10001;
         var10001 = var10003;
         var10003.<init>(var10002);
      }

      return var10000.getSharedPreferences(var10001, 0);
   }
}
