package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public final class zzo extends zzl implements SearchableCollectionMetadataField {
   public static final zzg zzaPP = new zzp();

   public zzo(int var1) {
      super("parents", Collections.emptySet(), Arrays.asList("parentsExtra", "dbInstanceId", "parentsExtraHolder"), 4100000);
   }

   protected final Collection zzr(Bundle var1) {
      Collection var2;
      return (var2 = super.zzr(var1)) == null ? null : new HashSet(var2);
   }

   protected final Collection zzd(DataHolder var1, int var2, int var3) {
      Bundle var4;
      ArrayList var5;
      if ((var5 = (var4 = var1.zzqN()).getParcelableArrayList("parentsExtra")) == null) {
         if (var4.getParcelable("parentsExtraHolder") != null) {
            DataHolder var8 = var1;
            synchronized(var1) {
               DataHolder var10;
               if ((var10 = (DataHolder)var8.zzqN().getParcelable("parentsExtraHolder")) != null) {
                  try {
                     int var11 = var8.getCount();
                     ArrayList var12 = new ArrayList(var11);
                     HashMap var13 = new HashMap(var11);

                     for(int var14 = 0; var14 < var11; ++var14) {
                        int var15 = var8.zzat(var14);
                        ParentDriveIdSet var16 = new ParentDriveIdSet();
                        var12.add(var16);
                        var13.put(var8.zzb("sqlId", var14, var15), var16);
                     }

                     Bundle var28;
                     String var29 = (var28 = var10.zzqN()).getString("childSqlIdColumn");
                     String var30 = var28.getString("parentSqlIdColumn");
                     String var17 = var28.getString("parentResIdColumn");
                     int var18 = var10.getCount();
                     int var19 = 0;

                     while(true) {
                        if (var19 >= var18) {
                           var8.zzqN().putParcelableArrayList("parentsExtra", var12);
                           break;
                        }

                        int var20 = var10.zzat(var19);
                        ParentDriveIdSet var10000 = (ParentDriveIdSet)var13.get(var10.zzb(var29, var19, var20));
                        zzq var23 = new zzq(var10.zzd(var17, var19, var20), var10.zzb(var30, var19, var20), 1);
                        var10000.zzaPO.add(var23);
                        ++var19;
                     }
                  } finally {
                     var10.close();
                     var8.zzqN().remove("parentsExtraHolder");
                  }
               }
            }

            var5 = var4.getParcelableArrayList("parentsExtra");
         }

         if (var5 == null) {
            return null;
         }
      }

      long var6 = var4.getLong("dbInstanceId");
      return ((ParentDriveIdSet)var5.get(var2)).zzB(var6);
   }

   private static void zzd(DataHolder var0) {
      Bundle var1;
      if ((var1 = var0.zzqN()) != null) {
         synchronized(var0) {
            DataHolder var3;
            if ((var3 = (DataHolder)var1.getParcelable("parentsExtraHolder")) != null) {
               var3.close();
               var1.remove("parentsExtraHolder");
            }

         }
      }
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return this.zzd(var1, var2, var3);
   }

   // $FF: synthetic method
   protected final Object zzq(Bundle var1) {
      return this.zzr(var1);
   }

   // $FF: synthetic method
   static void zze(DataHolder var0) {
      zzd(var0);
   }
}
