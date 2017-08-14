package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzalk;
import com.google.android.gms.internal.zzall;
import com.google.android.gms.internal.zzalm;
import com.google.android.gms.internal.zzaln;
import com.google.android.gms.internal.zzalo;
import com.google.android.gms.internal.zzalp;
import com.google.android.gms.internal.zzalq;
import com.google.android.gms.internal.zzalr;
import com.google.android.gms.internal.zzals;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.internal.zzalu;
import com.google.android.gms.internal.zzalv;
import com.google.android.gms.internal.zzalw;
import com.google.android.gms.internal.zzamg;
import com.google.android.gms.internal.zzami;
import com.google.android.gms.internal.zzamj;
import com.google.android.gms.internal.zzamm;
import com.google.android.gms.internal.zzanx;
import com.google.android.gms.internal.zzaos;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzb extends zzamg implements zzo {
   private static DecimalFormat zzadn;
   private final zzamj zzadj;
   private final String zzado;
   private final Uri zzadp;
   private final boolean zzadq;
   private final boolean zzadr;

   public zzb(zzamj var1, String var2) {
      this(var1, var2, true, false);
   }

   private zzb(zzamj var1, String var2, boolean var3, boolean var4) {
      super(var1);
      zzbo.zzcF(var2);
      this.zzadj = var1;
      this.zzado = var2;
      this.zzadq = true;
      this.zzadr = false;
      this.zzadp = zzaZ(this.zzado);
   }

   static Uri zzaZ(String var0) {
      zzbo.zzcF(var0);
      Builder var1;
      (var1 = new Builder()).scheme("uri");
      var1.authority("google-analytics.com");
      var1.path(var0);
      return var1.build();
   }

   public final Uri zzjl() {
      return this.zzadp;
   }

   public final void zzb(zzi var1) {
      zzbo.zzu(var1);
      zzbo.zzb(var1.zzju(), "Can't deliver not submitted measurement");
      zzbo.zzcG("deliver should be called on worker thread");
      zzi var2;
      zzalt var3;
      if (TextUtils.isEmpty((var3 = (zzalt)(var2 = var1.zzjp()).zzb(zzalt.class)).zzjW())) {
         this.zzkr().zze(zzc(var2), "Ignoring measurement without type");
      } else if (TextUtils.isEmpty(var3.zzjX())) {
         this.zzkr().zze(zzc(var2), "Ignoring measurement without client id");
      } else if (!this.zzadj.zzkG().getAppOptOut()) {
         double var4;
         if (zzaos.zza(var4 = var3.zzkc(), var3.zzjX())) {
            this.zzb("Sampling enabled. Hit sampled out. sampling rate", var4);
         } else {
            Map var6;
            (var6 = zzc(var2)).put("v", "1");
            var6.put("_v", zzami.zzafL);
            var6.put("tid", this.zzado);
            if (this.zzadj.zzkG().isDryRunEnabled()) {
               StringBuilder var14 = new StringBuilder();
               Iterator var15 = var6.entrySet().iterator();

               while(var15.hasNext()) {
                  Entry var16 = (Entry)var15.next();
                  if (var14.length() != 0) {
                     var14.append(", ");
                  }

                  var14.append((String)var16.getKey());
                  var14.append("=");
                  var14.append((String)var16.getValue());
               }

               String var17 = var14.toString();
               this.zzc("Dry run is enabled. GoogleAnalytics would have sent", var17);
            } else {
               HashMap var7;
               zzaos.zzb(var7 = new HashMap(), "uid", var3.getUserId());
               zzalk var8;
               if ((var8 = (zzalk)var1.zza(zzalk.class)) != null) {
                  zzaos.zzb(var7, "an", var8.zzjG());
                  zzaos.zzb(var7, "aid", var8.zzhl());
                  zzaos.zzb(var7, "av", var8.zzjH());
                  zzaos.zzb(var7, "aiid", var8.zzjI());
               }

               zzamm var9 = new zzamm(0L, var3.zzjX(), this.zzado, !TextUtils.isEmpty(var3.zzjY()), 0L, var7);
               long var10 = this.zzkv().zza(var9);
               var6.put("_s", String.valueOf(var10));
               zzanx var12 = new zzanx(this.zzkr(), var6, var1.zzjs(), true);
               this.zzkv().zza(var12);
            }
         }
      }
   }

   private static Map zzc(zzi var0) {
      HashMap var1 = new HashMap();
      zzalo var2;
      if ((var2 = (zzalo)var0.zza(zzalo.class)) != null) {
         Iterator var3 = var2.zzjR().entrySet().iterator();

         while(var3.hasNext()) {
            Entry var4;
            String var5;
            Object var22;
            String var23;
            Double var40;
            if ((var5 = (var22 = (var4 = (Entry)var3.next()).getValue()) == null ? null : (var22 instanceof String ? (!TextUtils.isEmpty(var23 = (String)var22) ? var23 : null) : (var22 instanceof Double ? ((var40 = (Double)var22).doubleValue() != 0.0D ? zzb(var40.doubleValue()) : null) : (var22 instanceof Boolean ? (var22 != Boolean.FALSE ? "1" : null) : String.valueOf(var22))))) != null) {
               var1.put((String)var4.getKey(), var5);
            }
         }
      }

      zzalt var24;
      if ((var24 = (zzalt)var0.zza(zzalt.class)) != null) {
         zza(var1, "t", var24.zzjW());
         zza(var1, "cid", var24.zzjX());
         zza(var1, "uid", var24.getUserId());
         zza(var1, "sc", var24.zzka());
         zza(var1, "sf", var24.zzkc());
         zza(var1, "ni", var24.zzkb());
         zza(var1, "adid", var24.zzjY());
         zza(var1, "ate", var24.zzjZ());
      }

      zzalu var25;
      if ((var25 = (zzalu)var0.zza(zzalu.class)) != null) {
         zza(var1, "cd", var25.zzkd());
         zza(var1, "a", (double)var25.zzke());
         zza(var1, "dr", var25.zzkf());
      }

      zzalr var26;
      if ((var26 = (zzalr)var0.zza(zzalr.class)) != null) {
         zza(var1, "ec", var26.getCategory());
         zza(var1, "ea", var26.getAction());
         zza(var1, "el", var26.getLabel());
         zza(var1, "ev", (double)var26.getValue());
      }

      zzall var6;
      if ((var6 = (zzall)var0.zza(zzall.class)) != null) {
         zza(var1, "cn", var6.getName());
         zza(var1, "cs", var6.getSource());
         zza(var1, "cm", var6.zzjJ());
         zza(var1, "ck", var6.zzjK());
         zza(var1, "cc", var6.getContent());
         zza(var1, "ci", var6.getId());
         zza(var1, "anid", var6.zzjL());
         zza(var1, "gclid", var6.zzjM());
         zza(var1, "dclid", var6.zzjN());
         zza(var1, "aclid", var6.zzjO());
      }

      zzals var7;
      if ((var7 = (zzals)var0.zza(zzals.class)) != null) {
         zza(var1, "exd", var7.zzafa);
         zza(var1, "exf", var7.zzafb);
      }

      zzalv var8;
      if ((var8 = (zzalv)var0.zza(zzalv.class)) != null) {
         zza(var1, "sn", var8.zzafq);
         zza(var1, "sa", var8.zzaeX);
         zza(var1, "st", var8.zzafr);
      }

      zzalw var9;
      if ((var9 = (zzalw)var0.zza(zzalw.class)) != null) {
         zza(var1, "utv", var9.zzafs);
         zza(var1, "utt", (double)var9.zzaft);
         zza(var1, "utc", var9.mCategory);
         zza(var1, "utl", var9.zzaeY);
      }

      zzalm var10;
      if ((var10 = (zzalm)var0.zza(zzalm.class)) != null) {
         Iterator var12 = var10.zzjP().entrySet().iterator();

         while(var12.hasNext()) {
            Entry var13;
            String var14;
            if (!TextUtils.isEmpty(var14 = zzf.zzD(((Integer)(var13 = (Entry)var12.next()).getKey()).intValue()))) {
               var1.put(var14, (String)var13.getValue());
            }
         }
      }

      zzaln var11;
      if ((var11 = (zzaln)var0.zza(zzaln.class)) != null) {
         Iterator var28 = var11.zzjQ().entrySet().iterator();

         while(var28.hasNext()) {
            String var15;
            Entry var31;
            if (!TextUtils.isEmpty(var15 = zzf.zzF(((Integer)(var31 = (Entry)var28.next()).getKey()).intValue()))) {
               String var16 = zzb(((Double)var31.getValue()).doubleValue());
               var1.put(var15, var16);
            }
         }
      }

      zzalq var27;
      if ((var27 = (zzalq)var0.zza(zzalq.class)) != null) {
         ProductAction var29;
         if ((var29 = var27.zzjS()) != null) {
            Iterator var32 = var29.build().entrySet().iterator();

            while(var32.hasNext()) {
               Entry var34;
               if (((String)(var34 = (Entry)var32.next()).getKey()).startsWith("&")) {
                  var1.put(((String)var34.getKey()).substring(1), (String)var34.getValue());
               } else {
                  var1.put((String)var34.getKey(), (String)var34.getValue());
               }
            }
         }

         int var33 = 1;

         Iterator var35;
         for(var35 = var27.zzjV().iterator(); var35.hasNext(); ++var33) {
            Promotion var36 = (Promotion)var35.next();
            var1.putAll(var36.zzbl(zzf.zzJ(var33)));
         }

         var33 = 1;

         for(var35 = var27.zzjT().iterator(); var35.hasNext(); ++var33) {
            Product var37 = (Product)var35.next();
            var1.putAll(var37.zzbl(zzf.zzH(var33)));
         }

         var33 = 1;

         for(var35 = var27.zzjU().entrySet().iterator(); var35.hasNext(); ++var33) {
            Entry var39;
            List var17 = (List)(var39 = (Entry)var35.next()).getValue();
            String var18 = zzf.zzM(var33);
            int var19 = 1;

            String var10002;
            String var10003;
            for(Iterator var20 = var17.iterator(); var20.hasNext(); ++var19) {
               Product var21 = (Product)var20.next();
               var10002 = String.valueOf(var18);
               var10003 = String.valueOf(zzf.zzK(var19));
               if (var10003.length() != 0) {
                  var10002 = var10002.concat(var10003);
               } else {
                  String var10004 = new String;
                  var10003 = var10002;
                  var10002 = var10004;
                  var10004.<init>(var10003);
               }

               var1.putAll(var21.zzbl(var10002));
            }

            if (!TextUtils.isEmpty((CharSequence)var39.getKey())) {
               String var10001 = String.valueOf(var18);
               var10002 = String.valueOf("nm");
               if (var10002.length() != 0) {
                  var10001 = var10001.concat(var10002);
               } else {
                  var10003 = new String;
                  var10002 = var10001;
                  var10001 = var10003;
                  var10003.<init>(var10002);
               }

               var1.put(var10001, (String)var39.getKey());
            }
         }
      }

      zzalp var30;
      if ((var30 = (zzalp)var0.zza(zzalp.class)) != null) {
         zza(var1, "ul", var30.getLanguage());
         zza(var1, "sd", (double)var30.zzaeU);
         zza(var1, "sr", var30.zzNY, var30.zzNZ);
         zza(var1, "vp", var30.zzaeV, var30.zzaeW);
      }

      zzalk var38;
      if ((var38 = (zzalk)var0.zza(zzalk.class)) != null) {
         zza(var1, "an", var38.zzjG());
         zza(var1, "aid", var38.zzhl());
         zza(var1, "aiid", var38.zzjI());
         zza(var1, "av", var38.zzjH());
      }

      return var1;
   }

   private static void zza(Map var0, String var1, String var2) {
      if (!TextUtils.isEmpty(var2)) {
         var0.put(var1, var2);
      }

   }

   private static String zzb(double var0) {
      if (zzadn == null) {
         zzadn = new DecimalFormat("0.######");
      }

      return zzadn.format(var0);
   }

   private static void zza(Map var0, String var1, double var2) {
      if (var2 != 0.0D) {
         var0.put(var1, zzb(var2));
      }

   }

   private static void zza(Map var0, String var1, boolean var2) {
      if (var2) {
         var0.put(var1, "1");
      }

   }

   private static void zza(Map var0, String var1, int var2, int var3) {
      if (var2 > 0 && var3 > 0) {
         var0.put(var1, (new StringBuilder(23)).append(var2).append("x").append(var3).toString());
      }

   }
}
