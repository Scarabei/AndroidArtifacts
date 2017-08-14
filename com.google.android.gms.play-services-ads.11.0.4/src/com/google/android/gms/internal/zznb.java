package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzzn
public final class zznb {
   private boolean zzGI;
   private final List zzGZ = new LinkedList();
   private final Map zzHa = new LinkedHashMap();
   private final Object mLock = new Object();
   private String zzHb;
   private zzmz zzHc;
   @Nullable
   private zznb zzHd;

   public zznb(boolean var1, String var2, String var3) {
      this.zzGI = var1;
      this.zzHa.put("action", var2);
      this.zzHa.put("ad_format", var3);
   }

   public final void zzc(@Nullable zznb var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzHd = var1;
      }
   }

   public final zzmz zzdS() {
      return this.zzc(com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime());
   }

   @Nullable
   public final zzmz zzc(long var1) {
      return !this.zzGI ? null : new zzmz(var1, (String)null, (zzmz)null);
   }

   public final boolean zza(@Nullable zzmz var1, String... var2) {
      return this.zzGI && var1 != null ? this.zza(var1, com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime(), var2) : false;
   }

   public final boolean zza(zzmz var1, long var2, String... var4) {
      Object var5 = this.mLock;
      synchronized(this.mLock) {
         String[] var6 = var4;
         int var7 = var4.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            String var9 = var6[var8];
            zzmz var10 = new zzmz(var2, var9, var1);
            this.zzGZ.add(var10);
         }

         return true;
      }
   }

   public final void zzdT() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzHc = this.zzdS();
      }
   }

   public final String zzdU() {
      StringBuilder var1 = new StringBuilder();
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         Iterator var3 = this.zzGZ.iterator();

         while(var3.hasNext()) {
            zzmz var4;
            long var5 = (var4 = (zzmz)var3.next()).getTime();
            String var7 = var4.zzdP();
            zzmz var8;
            if ((var8 = var4.zzdQ()) != null && var5 > 0L) {
               long var9 = var5 - var8.getTime();
               var1.append(var7).append('.').append(var9).append(',');
            }
         }

         this.zzGZ.clear();
         if (!TextUtils.isEmpty(this.zzHb)) {
            var1.append(this.zzHb);
         } else if (var1.length() > 0) {
            var1.setLength(var1.length() - 1);
         }

         return var1.toString();
      }
   }

   public final void zzO(String var1) {
      if (this.zzGI) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            this.zzHb = var1;
         }
      }
   }

   public final void zzh(String var1, String var2) {
      if (this.zzGI && !TextUtils.isEmpty(var2)) {
         zzmr var3;
         if ((var3 = com.google.android.gms.ads.internal.zzbs.zzbD().zzhr()) != null) {
            Object var4 = this.mLock;
            synchronized(this.mLock) {
               zzmv var10000 = var3.zzM(var1);
               Map var7 = this.zzHa;
               zzmv var6 = var10000;
               var7.put(var1, var6.zzg((String)var7.get(var1), var2));
            }
         }
      }
   }

   final Map zzdV() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         zzmr var2;
         return (var2 = com.google.android.gms.ads.internal.zzbs.zzbD().zzhr()) != null && this.zzHd != null ? var2.zza(this.zzHa, this.zzHd.zzdV()) : this.zzHa;
      }
   }

   public final zzmz zzdW() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzHc;
      }
   }
}
