package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzzn
public final class zziu {
   public static final zziu zzAr = new zziu();

   public static zzir zza(Context var0, zzla var1) {
      Date var2;
      long var3 = (var2 = var1.getBirthday()) != null ? var2.getTime() : -1L;
      String var5 = var1.getContentUrl();
      int var6 = var1.getGender();
      Set var7;
      List var8 = !(var7 = var1.getKeywords()).isEmpty() ? Collections.unmodifiableList(new ArrayList(var7)) : null;
      boolean var9 = var1.isTestDevice(var0);
      int var10 = var1.zzdB();
      Location var11 = var1.getLocation();
      Bundle var12 = var1.getNetworkExtrasBundle(AdMobAdapter.class);
      boolean var13 = var1.getManualImpressionsEnabled();
      String var14 = var1.getPublisherProvidedId();
      SearchAdRequest var15;
      zzlt var16 = (var15 = var1.zzdy()) != null ? new zzlt(var15) : null;
      String var17 = null;
      Context var18;
      if ((var18 = var0.getApplicationContext()) != null) {
         String var19 = var18.getPackageName();
         zzji.zzds();
         var17 = zzaiy.zza(Thread.currentThread().getStackTrace(), var19);
      }

      boolean var20 = var1.isDesignedForFamilies();
      return new zzir(7, var3, var12, var6, var8, var9, var10, var13, var14, var16, var11, var5, var1.zzdA(), var1.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(var1.zzdC())), var1.zzdx(), var17, var20);
   }
}
