package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.zzc;
import com.google.android.gms.drive.metadata.internal.zzg;
import com.google.android.gms.drive.metadata.internal.zzm;
import java.util.Arrays;

public class zzbrn extends zzm {
   public static final zzg zzaQH = new zzbro();

   public zzbrn(int var1) {
      super("customProperties", Arrays.asList("hasCustomProperties", "sqlId"), Arrays.asList("customPropertiesExtra", "customPropertiesExtraHolder"), 5000000);
   }

   private static AppVisibleCustomProperties zzf(DataHolder var0, int var1, int var2) {
      Bundle var3;
      SparseArray var4;
      if ((var4 = (var3 = var0.zzqN()).getSparseParcelableArray("customPropertiesExtra")) == null) {
         if (var3.getParcelable("customPropertiesExtraHolder") != null) {
            DataHolder var5 = var0;
            synchronized(var0) {
               DataHolder var7;
               if ((var7 = (DataHolder)var5.zzqN().getParcelable("customPropertiesExtraHolder")) != null) {
                  try {
                     DataHolder var16 = var7;
                     Bundle var17;
                     String var18 = (var17 = var7.zzqN()).getString("entryIdColumn");
                     String var19 = var17.getString("keyColumn");
                     String var20 = var17.getString("visibilityColumn");
                     String var21 = var17.getString("valueColumn");
                     LongSparseArray var22 = new LongSparseArray();

                     for(int var23 = 0; var23 < var16.getCount(); ++var23) {
                        int var24 = var16.zzat(var23);
                        long var25 = var16.zzb(var18, var23, var24);
                        String var27 = var16.zzd(var19, var23, var24);
                        int var28 = var16.zzc(var20, var23, var24);
                        String var29 = var16.zzd(var21, var23, var24);
                        zzc var30 = new zzc(new CustomPropertyKey(var27, var28), var29);
                        AppVisibleCustomProperties.zza var31;
                        if ((var31 = (AppVisibleCustomProperties.zza)var22.get(var25)) == null) {
                           var31 = new AppVisibleCustomProperties.zza();
                           var22.put(var25, var31);
                        }

                        var31.zza(var30);
                     }

                     LongSparseArray var8 = var22;
                     SparseArray var9 = new SparseArray();

                     for(int var10 = 0; var10 < var5.getCount(); ++var10) {
                        long var11 = var5.zzb("sqlId", var10, var5.zzat(var10));
                        AppVisibleCustomProperties.zza var13;
                        if ((var13 = (AppVisibleCustomProperties.zza)var8.get(var11)) != null) {
                           var9.append(var10, var13.zztm());
                        }
                     }

                     var5.zzqN().putSparseParcelableArray("customPropertiesExtra", var9);
                  } finally {
                     var7.close();
                     var5.zzqN().remove("customPropertiesExtraHolder");
                  }
               }
            }

            var4 = var3.getSparseParcelableArray("customPropertiesExtra");
         }

         if (var4 == null) {
            return AppVisibleCustomProperties.zzaPG;
         }
      }

      return (AppVisibleCustomProperties)var4.get(var1, AppVisibleCustomProperties.zzaPG);
   }

   private static void zzd(DataHolder var0) {
      Bundle var1;
      if ((var1 = var0.zzqN()) != null) {
         synchronized(var0) {
            DataHolder var3;
            if ((var3 = (DataHolder)var1.getParcelable("customPropertiesExtraHolder")) != null) {
               var3.close();
               var1.remove("customPropertiesExtraHolder");
            }

         }
      }
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return zzf(var1, var2, var3);
   }

   // $FF: synthetic method
   static void zze(DataHolder var0) {
      zzd(var0);
   }
}
