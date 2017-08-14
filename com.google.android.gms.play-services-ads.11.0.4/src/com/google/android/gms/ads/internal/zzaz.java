package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzacs;
import com.google.android.gms.internal.zzadu;
import com.google.android.gms.internal.zzadz;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzua;
import com.google.android.gms.internal.zzub;
import com.google.android.gms.internal.zzut;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzaz implements Runnable {
   // $FF: synthetic field
   private zzay zzuN;

   zzaz(zzay var1) {
      this.zzuN = var1;
      super();
   }

   public final void run() {
      Context var10000 = zzax.zza(this.zzuN.zzuM);
      Runnable var2 = this.zzuN.zzuL;
      Context var1 = var10000;
      com.google.android.gms.common.internal.zzbo.zzcz("Adapters must be initialized on the main thread.");
      Map var3;
      if ((var3 = zzbs.zzbD().zzhD().zzhm()) != null && !var3.isEmpty()) {
         try {
            if (var2 != null) {
               var2.run();
            }
         } catch (Throwable var18) {
            zzafr.zzc("Could not initialize rewarded ads.", var18);
            return;
         }

         zzacs var4;
         if ((var4 = zzacs.zzgO()) != null) {
            Collection var10001 = var3.values();
            zzacs var7 = var4;
            Collection var6 = var10001;
            HashMap var8 = new HashMap();
            IObjectWrapper var9 = com.google.android.gms.dynamic.zzn.zzw(var1);
            Iterator var10 = var6.iterator();

            while(var10.hasNext()) {
               Iterator var12 = ((zzub)var10.next()).zzLY.iterator();

               while(var12.hasNext()) {
                  zzua var13;
                  String var14 = (var13 = (zzua)var12.next()).zzLP;
                  Iterator var15 = var13.zzLJ.iterator();

                  while(var15.hasNext()) {
                     String var16 = (String)var15.next();
                     if (!var8.containsKey(var16)) {
                        var8.put(var16, new ArrayList());
                     }

                     if (var14 != null) {
                        ((Collection)var8.get(var16)).add(var14);
                     }
                  }
               }
            }

            var10 = var8.entrySet().iterator();

            while(var10.hasNext()) {
               Entry var11;
               String var19 = (String)(var11 = (Entry)var10.next()).getKey();

               try {
                  zzadz var20;
                  zzut var21;
                  if ((var20 = var7.zzav(var19)) != null && !(var21 = var20.zzgW()).isInitialized() && var21.zzfu()) {
                     zzadu var22 = var20.zzgX();
                     List var23 = (List)var11.getValue();
                     var21.zza(var9, var22, var23);
                     String var25 = String.valueOf(var19);
                     String var24;
                     if (var25.length() != 0) {
                        var24 = "Initialized rewarded video mediation adapter ".concat(var25);
                     } else {
                        String var10002 = new String;
                        var24 = var10002;
                        var10002.<init>("Initialized rewarded video mediation adapter ");
                     }

                     zzafr.zzaC(var24);
                  }
               } catch (Throwable var17) {
                  zzafr.zzc((new StringBuilder(56 + String.valueOf(var19).length())).append("Failed to initialize rewarded video mediation adapter \"").append(var19).append("\"").toString(), var17);
               }
            }
         }

      }
   }
}
