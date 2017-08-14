package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@zzzn
public final class zztl {
   private final Map zzKo = new HashMap();
   private final LinkedList zzKp = new LinkedList();
   @Nullable
   private zzsi zzKq;

   final void zza(zzsi var1) {
      if (this.zzKq == null) {
         this.zzKq = var1.zzeF();
         zztl var2 = this;
         if (this.zzKq != null) {
            SharedPreferences var3 = this.zzKq.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            zztl var12 = this;

            while(var12.zzKp.size() > 0) {
               zztm var13 = (zztm)var12.zzKp.remove();
               zztn var14 = (zztn)var12.zzKo.get(var13);
               zza("Flushing interstitial queue for %s.", var13);

               while(var14.size() > 0) {
                  var14.zzm((zzir)null).zzKv.zzbb();
               }

               var12.zzKo.remove(var13);
            }

            try {
               HashMap var4 = new HashMap();
               Iterator var5 = var3.getAll().entrySet().iterator();

               zztm var10;
               while(var5.hasNext()) {
                  Entry var6;
                  if (!((String)(var6 = (Entry)var5.next()).getKey()).equals("PoolKeys")) {
                     zztr var9 = zztr.zzab((String)var6.getValue());
                     var10 = new zztm(var9.zzuT, var9.zztV, var9.zzKt);
                     if (!var2.zzKo.containsKey(var10)) {
                        zztn var11 = new zztn(var9.zzuT, var9.zztV, var9.zzKt);
                        var2.zzKo.put(var10, var11);
                        var4.put(var10.toString(), var10);
                        zza("Restored interstitial queue for %s.", var10);
                     }
                  }
               }

               String[] var16;
               int var7 = (var16 = zzY(var3.getString("PoolKeys", ""))).length;

               for(int var8 = 0; var8 < var7; ++var8) {
                  String var17 = var16[var8];
                  var10 = (zztm)var4.get(var17);
                  if (var2.zzKo.containsKey(var10)) {
                     var2.zzKp.add(var10);
                  }
               }

               return;
            } catch (IOException | RuntimeException var15) {
               com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var15, (String)"InterstitialAdPool.restore");
               zzafr.zzc("Malformed preferences value for InterstitialAdPool.", var15);
               this.zzKo.clear();
               this.zzKp.clear();
            }
         }
      }

   }

   @Nullable
   final zzto zza(zzir var1, String var2) {
      if (zzZ(var2)) {
         return null;
      } else {
         Context var3 = this.zzKq.getApplicationContext();
         int var4 = (new zzacc(var3)).zzgM().zzVS;
         zzir var5 = zzk(var1);
         String var6 = zzaa(var2);
         zztm var7 = new zztm(var5, var6, var4);
         zztn var8;
         if ((var8 = (zztn)this.zzKo.get(var7)) == null) {
            zza("Interstitial pool created at %s.", var7);
            var8 = new zztn(var5, var6, var4);
            this.zzKo.put(var7, var8);
         }

         this.zzKp.remove(var7);
         this.zzKp.add(var7);
         var8.zzeL();

         while(true) {
            int var10000 = this.zzKp.size();
            zzme var12 = zzmo.zzEc;
            if (var10000 <= ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var12)).intValue()) {
               zzto var13;
               while(true) {
                  if (var8.size() <= 0) {
                     return null;
                  }

                  if (!(var13 = var8.zzm(var5)).zzKz) {
                     break;
                  }

                  long var15 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis() - var13.zzKy;
                  var12 = zzmo.zzEe;
                  if (var15 <= 1000L * (long)((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var12)).intValue()) {
                     break;
                  }

                  zza("Expired interstitial at %s.", var7);
                  zztp.zzeN().zzeO();
               }

               String var14 = var13.zzKw != null ? " (inline) " : " ";
               zza((new StringBuilder(34 + String.valueOf(var14).length())).append("Pooled interstitial").append(var14).append("returned at %s.").toString(), var7);
               return var13;
            }

            zztm var9 = (zztm)this.zzKp.remove();
            zztn var10 = (zztn)this.zzKo.get(var9);
            zza("Evicting interstitial queue for %s.", var9);

            zzto var11;
            for(; var10.size() > 0; var11.zzKv.zzbb()) {
               if ((var11 = var10.zzm((zzir)null)).zzKz) {
                  zztp.zzeN().zzeP();
               }
            }

            this.zzKo.remove(var9);
         }
      }
   }

   final void zzb(zzir var1, String var2) {
      if (this.zzKq != null) {
         Context var3 = this.zzKq.getApplicationContext();
         int var4 = (new zzacc(var3)).zzgM().zzVS;
         zzir var5 = zzk(var1);
         String var6 = zzaa(var2);
         zztm var7 = new zztm(var5, var6, var4);
         zztn var8;
         if ((var8 = (zztn)this.zzKo.get(var7)) == null) {
            zza("Interstitial pool created at %s.", var7);
            var8 = new zztn(var5, var6, var4);
            this.zzKo.put(var7, var8);
         }

         var8.zza(this.zzKq, var1);
         var8.zzeL();
         zza("Inline entry added to the queue at %s.", var7);
      }
   }

   final void zzeG() {
      if (this.zzKq != null) {
         int var5;
         for(Iterator var1 = this.zzKo.entrySet().iterator(); var1.hasNext(); zztp.zzeN().zzn(var5)) {
            Entry var2;
            zztm var3 = (zztm)(var2 = (Entry)var1.next()).getKey();
            zztn var4 = (zztn)var2.getValue();
            if (zzafr.zzz(2)) {
               var5 = var4.size();
               int var6;
               if ((var6 = var4.zzeJ()) < var5) {
                  zzafr.v(String.format("Loading %s/%s pooled interstitials for %s.", var5 - var6, var5, var3));
               }
            }

            var5 = 0 + var4.zzeK();

            while(true) {
               int var10000 = var4.size();
               zzme var7 = zzmo.zzEd;
               if (var10000 >= ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var7)).intValue()) {
                  break;
               }

               zza("Pooling and loading one new interstitial for %s.", var3);
               if (var4.zzb(this.zzKq)) {
                  ++var5;
               }
            }
         }

         if (this.zzKq != null) {
            Editor var8;
            (var8 = this.zzKq.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit()).clear();
            Iterator var9 = this.zzKo.entrySet().iterator();

            while(var9.hasNext()) {
               Entry var10;
               zztm var11 = (zztm)(var10 = (Entry)var9.next()).getKey();
               zztn var12;
               if ((var12 = (zztn)var10.getValue()).zzeM()) {
                  String var13 = (new zztr(var12)).zzeW();
                  String var14 = var11.toString();
                  var8.putString(var14, var13);
                  zza("Saved interstitial queue for %s.", var11);
               }
            }

            var8.putString("PoolKeys", this.zzeH());
            var8.apply();
         }

      }
   }

   private final String zzeH() {
      try {
         StringBuilder var1 = new StringBuilder();
         Iterator var2 = this.zzKp.iterator();

         while(var2.hasNext()) {
            String var3 = ((zztm)var2.next()).toString();
            var1.append(Base64.encodeToString(var3.getBytes("UTF-8"), 0));
            if (var2.hasNext()) {
               var1.append("\u0000");
            }
         }

         return var1.toString();
      } catch (UnsupportedEncodingException var4) {
         return "";
      }
   }

   private static String[] zzY(String var0) {
      try {
         String[] var1 = var0.split("\u0000");

         for(int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = new String(Base64.decode(var1[var2], 0), "UTF-8");
         }

         return var1;
      } catch (UnsupportedEncodingException var3) {
         return new String[0];
      }
   }

   private static boolean zzZ(String var0) {
      try {
         zzme var2 = zzmo.zzEf;
         return Pattern.matches((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2), var0);
      } catch (RuntimeException var3) {
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var3, (String)"InterstitialAdPool.isExcludedAdUnit");
         return false;
      }
   }

   static Set zzi(zzir var0) {
      HashSet var1;
      (var1 = new HashSet()).addAll(var0.extras.keySet());
      Bundle var2;
      if ((var2 = var0.zzzX.getBundle("com.google.ads.mediation.admob.AdMobAdapter")) != null) {
         var1.addAll(var2.keySet());
      }

      return var1;
   }

   static zzir zzj(zzir var0) {
      zzir var1;
      zzir var10000 = var1 = zzl(var0);
      String var3 = "_skipMediation";
      zzir var2 = var10000;
      Bundle var4;
      if ((var4 = var10000.zzzX.getBundle("com.google.ads.mediation.admob.AdMobAdapter")) != null) {
         var4.putBoolean(var3, true);
      }

      var2.extras.putBoolean(var3, true);
      return var1;
   }

   private static zzir zzk(zzir var0) {
      zzir var1 = zzl(var0);
      zzme var8 = zzmo.zzEb;
      String[] var2;
      int var3 = (var2 = ((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8)).split(",")).length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         zzc(var1.zzzX, var5);
         String var6 = "com.google.ads.mediation.admob.AdMobAdapter/";
         if (var5.startsWith(var6)) {
            String var7 = var5.replaceFirst(var6, "");
            zzc(var1.extras, var7);
         }
      }

      return var1;
   }

   private static String zzaa(String var0) {
      try {
         Matcher var1;
         if ((var1 = Pattern.compile("([^/]+/[0-9]+).*").matcher(var0)).matches()) {
            return var1.group(1);
         }
      } catch (RuntimeException var2) {
         ;
      }

      return var0;
   }

   private static zzir zzl(zzir var0) {
      Parcel var1 = Parcel.obtain();
      var0.writeToParcel(var1, 0);
      var1.setDataPosition(0);
      zzir var2 = (zzir)zzir.CREATOR.createFromParcel(var1);
      var1.recycle();
      zzme var3 = zzmo.zzDR;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
         zzir.zzh(var2);
      }

      return var2;
   }

   private static void zzc(Bundle var0, String var1) {
      String[] var2;
      while((var2 = var1.split("/", 2)).length != 0) {
         String var3 = var2[0];
         if (var2.length == 1) {
            var0.remove(var3);
            return;
         }

         Bundle var4;
         if ((var4 = var0.getBundle(var3)) == null) {
            return;
         }

         var1 = var2[1];
         var0 = var4;
      }

   }

   private static void zza(String var0, zztm var1) {
      if (zzafr.zzz(2)) {
         zzafr.v(String.format(var0, var1));
      }

   }
}
