package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@zzzn
public final class zzvn implements NativeMediationAdRequest {
   private final Date zzda;
   private final int zzAe;
   private final Set zzdc;
   private final boolean zzdd;
   private final Location zzde;
   private final int zzMZ;
   private final zzon zztS;
   private final List zztT;
   private final boolean zzAq;
   private final Map zzNh;

   public zzvn(@Nullable Date var1, int var2, @Nullable Set var3, @Nullable Location var4, boolean var5, int var6, zzon var7, List var8, boolean var9) {
      this.zzda = var1;
      this.zzAe = var2;
      this.zzdc = var3;
      this.zzde = var4;
      this.zzdd = var5;
      this.zzMZ = var6;
      this.zztS = var7;
      this.zzAq = var9;
      String var10 = "custom:";
      this.zztT = new ArrayList();
      this.zzNh = new HashMap();
      if (var8 != null) {
         Iterator var11 = var8.iterator();

         while(var11.hasNext()) {
            String var12;
            if ((var12 = (String)var11.next()).startsWith(var10)) {
               String[] var13;
               if ((var13 = var12.split(":", 3)).length == 3) {
                  if ("true".equals(var13[2])) {
                     this.zzNh.put(var13[1], true);
                  } else if ("false".equals(var13[2])) {
                     this.zzNh.put(var13[1], false);
                  }
               }
            } else {
               this.zztT.add(var12);
            }
         }
      }

   }

   public final Date getBirthday() {
      return this.zzda;
   }

   public final int getGender() {
      return this.zzAe;
   }

   public final Set getKeywords() {
      return this.zzdc;
   }

   public final Location getLocation() {
      return this.zzde;
   }

   public final boolean isTesting() {
      return this.zzdd;
   }

   public final int taggedForChildDirectedTreatment() {
      return this.zzMZ;
   }

   public final NativeAdOptions getNativeAdOptions() {
      if (this.zztS == null) {
         return null;
      } else {
         NativeAdOptions.Builder var1 = (new NativeAdOptions.Builder()).setReturnUrlsForImageAssets(this.zztS.zzIn).setImageOrientation(this.zztS.zzIo).setRequestMultipleImages(this.zztS.zzIp);
         if (this.zztS.versionCode >= 2) {
            var1.setAdChoicesPlacement(this.zztS.zzIq);
         }

         if (this.zztS.versionCode >= 3 && this.zztS.zzIr != null) {
            VideoOptions var2 = new VideoOptions(this.zztS.zzIr);
            var1.setVideoOptions(var2);
         }

         return var1.build();
      }
   }

   public final boolean isAppInstallAdRequested() {
      return this.zztT != null && this.zztT.contains("2");
   }

   public final boolean isContentAdRequested() {
      return this.zztT != null && this.zztT.contains("1");
   }

   public final boolean zzfz() {
      return this.zztT != null && this.zztT.contains("3");
   }

   public final Map zzfA() {
      return this.zzNh;
   }

   public final boolean isDesignedForFamilies() {
      return this.zzAq;
   }
}
