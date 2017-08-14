package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

class zzm extends zzgi {
   private static final String ID;
   private static final String URL;
   private static final String zzbDq;
   private static final String zzbDr;
   private static String zzbDs;
   private static final Set zzbDt;
   private final zzm.zza zzbDu;
   private final Context mContext;

   public zzm(Context var1) {
      this(var1, new zzn(var1));
   }

   private zzm(Context var1, zzm.zza var2) {
      super(ID, URL);
      this.zzbDu = var2;
      this.mContext = var1;
   }

   public final void zzq(Map var1) {
      String var10000 = var1.get(zzbDr) != null ? zzgk.zzb((com.google.android.gms.internal.zzbr)var1.get(zzbDr)) : null;
      String var2 = var10000;
      if (var10000 == null || !this.zzeW(var2)) {
         Builder var3 = Uri.parse(zzgk.zzb((com.google.android.gms.internal.zzbr)var1.get(URL))).buildUpon();
         com.google.android.gms.internal.zzbr var4;
         String var10001;
         String var10002;
         if ((var4 = (com.google.android.gms.internal.zzbr)var1.get(zzbDq)) != null) {
            Object var5;
            if (!((var5 = zzgk.zzg(var4)) instanceof List)) {
               var10001 = String.valueOf(var3.build().toString());
               if (var10001.length() != 0) {
                  var10000 = "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("ArbitraryPixel: additional params not a list: not sending partial hit: ");
               }

               zzdj.e(var10000);
               return;
            }

            Iterator var7 = ((List)var5).iterator();

            while(var7.hasNext()) {
               Object var8;
               if (!((var8 = var7.next()) instanceof Map)) {
                  var10001 = String.valueOf(var3.build().toString());
                  if (var10001.length() != 0) {
                     var10000 = "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("ArbitraryPixel: additional params contains non-map: not sending partial hit: ");
                  }

                  zzdj.e(var10000);
                  return;
               }

               Iterator var9 = ((Map)var8).entrySet().iterator();

               while(var9.hasNext()) {
                  Entry var10 = (Entry)var9.next();
                  var3.appendQueryParameter(var10.getKey().toString(), var10.getValue().toString());
               }
            }
         }

         String var13 = var3.build().toString();
         this.zzbDu.zzAF().zzfh(var13);
         var10001 = String.valueOf(var13);
         if (var10001.length() != 0) {
            var10000 = "ArbitraryPixel: url = ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("ArbitraryPixel: url = ");
         }

         zzdj.v(var10000);
         if (var2 != null) {
            Class var6 = zzm.class;
            synchronized(zzm.class) {
               zzbDt.add(var2);
               zzfu.zzd(this.mContext, zzbDs, var2, "true");
            }
         }
      }
   }

   private final synchronized boolean zzeW(String var1) {
      if (zzbDt.contains(var1)) {
         return true;
      } else if (this.mContext.getSharedPreferences(zzbDs, 0).contains(var1)) {
         zzbDt.add(var1);
         return true;
      } else {
         return false;
      }
   }

   static {
      ID = zzbf.zzeG.toString();
      URL = zzbg.zzkg.toString();
      zzbDq = zzbg.zzfE.toString();
      zzbDr = zzbg.zzkf.toString();
      String var0 = ID;
      zzbDs = (new StringBuilder(17 + String.valueOf(var0).length())).append("gtm_").append(var0).append("_unrepeatable").toString();
      zzbDt = new HashSet();
   }

   public interface zza {
      zzby zzAF();
   }
}
