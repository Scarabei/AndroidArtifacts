package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Iterator;
import java.util.List;

@zzzn
public final class zzia extends zza {
   public static final Creator CREATOR = new zzib();
   @Nullable
   public final String url;
   private long zzzu;
   private String zzzv;
   private String zzzw;
   private String zzzx;
   private Bundle zzzy;
   private boolean zzzz;

   @Nullable
   public static zzia zzB(String var0) {
      return zze(Uri.parse(var0));
   }

   @Nullable
   public static zzia zze(Uri var0) {
      try {
         if (!"gcache".equals(var0.getScheme())) {
            return null;
         } else {
            List var1;
            if ((var1 = var0.getPathSegments()).size() != 2) {
               int var16 = var1.size();
               zzafr.zzaT((new StringBuilder(62)).append("Expected 2 path parts for namespace and id, found :").append(var16).toString());
               return null;
            } else {
               String var2 = (String)var1.get(0);
               String var3 = (String)var1.get(1);
               String var4 = var0.getHost();
               String var5 = var0.getQueryParameter("url");
               boolean var6 = "1".equals(var0.getQueryParameter("read_only"));
               String var7;
               long var8 = (var7 = var0.getQueryParameter("expiration")) == null ? 0L : Long.parseLong(var7);
               Bundle var10 = new Bundle();
               Iterator var11 = com.google.android.gms.ads.internal.zzbs.zzbB().zzh(var0).iterator();

               while(var11.hasNext()) {
                  String var12;
                  if ((var12 = (String)var11.next()).startsWith("tag.")) {
                     String var13 = var12.substring(4);
                     String var14 = var0.getQueryParameter(var12);
                     var10.putString(var13, var14);
                  }
               }

               return new zzia(var5, var8, var4, var2, var3, var10, var6);
            }
         }
      } catch (NumberFormatException | NullPointerException var15) {
         zzafr.zzc("Unable to parse Uri into cache offering.", var15);
         return null;
      }
   }

   zzia(@Nullable String var1, long var2, String var4, String var5, String var6, Bundle var7, boolean var8) {
      this.url = var1;
      this.zzzu = var2;
      this.zzzv = var4 != null ? var4 : "";
      this.zzzw = var5 != null ? var5 : "";
      this.zzzx = var6 != null ? var6 : "";
      this.zzzy = var7 != null ? var7 : new Bundle();
      this.zzzz = var8;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.url, false);
      zzd.zza(var1, 3, this.zzzu);
      zzd.zza(var1, 4, this.zzzv, false);
      zzd.zza(var1, 5, this.zzzw, false);
      zzd.zza(var1, 6, this.zzzx, false);
      zzd.zza(var1, 7, this.zzzy, false);
      zzd.zza(var1, 8, this.zzzz);
      zzd.zzI(var1, var5);
   }
}
