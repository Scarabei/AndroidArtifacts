package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zzbrc;
import com.google.android.gms.internal.zzbrn;
import com.google.android.gms.internal.zzbrp;
import com.google.android.gms.internal.zzbrx;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzf {
   private static final Map zzaPK = new HashMap();
   private static final Map zzaPL = new HashMap();

   private static void zzb(MetadataField var0) {
      if (zzaPK.containsKey(var0.getName())) {
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf(var0.getName());
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Duplicate field name registered: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Duplicate field name registered: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      } else {
         zzaPK.put(var0.getName(), var0);
      }
   }

   public static MetadataField zzcR(String var0) {
      return (MetadataField)zzaPK.get(var0);
   }

   public static Collection zztn() {
      return Collections.unmodifiableCollection(zzaPK.values());
   }

   public static void zzb(DataHolder var0) {
      Iterator var1 = zzaPL.values().iterator();

      while(var1.hasNext()) {
         ((zzg)var1.next()).zzc(var0);
      }

   }

   private static void zza(zzg var0) {
      if (zzaPL.put(var0.zzto(), var0) != null) {
         String var1 = String.valueOf(var0.zzto());
         throw new IllegalStateException((new StringBuilder(46 + String.valueOf(var1).length())).append("A cleaner for key ").append(var1).append(" has already been registered").toString());
      }
   }

   static {
      zzb(zzbrc.zzaPQ);
      zzb((MetadataField)zzbrc.zzaQw);
      zzb((MetadataField)zzbrc.zzaQn);
      zzb((MetadataField)zzbrc.zzaQu);
      zzb((MetadataField)zzbrc.zzaQx);
      zzb(zzbrc.zzaQd);
      zzb(zzbrc.zzaQc);
      zzb(zzbrc.zzaQe);
      zzb((MetadataField)zzbrc.zzaQf);
      zzb(zzbrc.zzaQg);
      zzb(zzbrc.zzaQa);
      zzb(zzbrc.zzaQi);
      zzb(zzbrc.zzaQj);
      zzb(zzbrc.zzaQk);
      zzb((MetadataField)zzbrc.zzaQs);
      zzb(zzbrc.zzaPR);
      zzb((MetadataField)zzbrc.zzaQp);
      zzb(zzbrc.zzaPT);
      zzb(zzbrc.zzaQb);
      zzb(zzbrc.zzaPU);
      zzb(zzbrc.zzaPV);
      zzb(zzbrc.zzaPW);
      zzb(zzbrc.zzaPX);
      zzb(zzbrc.zzaQm);
      zzb(zzbrc.zzaQh);
      zzb(zzbrc.zzaQo);
      zzb((MetadataField)zzbrc.zzaQq);
      zzb((MetadataField)zzbrc.zzaQr);
      zzb((MetadataField)zzbrc.zzaQt);
      zzb(zzbrc.zzaQy);
      zzb(zzbrc.zzaQz);
      zzb(zzbrc.zzaPZ);
      zzb(zzbrc.zzaPY);
      zzb(zzbrc.zzaQv);
      zzb(zzbrc.zzaQl);
      zzb((MetadataField)zzbrc.zzaPS);
      zzb(zzbrc.zzaQA);
      zzb((MetadataField)zzbrc.zzaQB);
      zzb(zzbrc.zzaQC);
      zzb(zzbrc.zzaQD);
      zzb((MetadataField)zzbrc.zzaQE);
      zzb(zzbrc.zzaQF);
      zzb(zzbrc.zzaQG);
      zzb((MetadataField)zzbrp.zzaQI);
      zzb((MetadataField)zzbrp.zzaQK);
      zzb((MetadataField)zzbrp.zzaQL);
      zzb((MetadataField)zzbrp.zzaQM);
      zzb((MetadataField)zzbrp.zzaQJ);
      zzb((MetadataField)zzbrp.zzaQN);
      zzb(zzbrx.zzaQP);
      zzb(zzbrx.zzaQQ);
      zza(zzo.zzaPP);
      zza(zzbrn.zzaQH);
   }
}
