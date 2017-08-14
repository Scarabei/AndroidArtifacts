package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.query.internal.zzj;
import com.google.android.gms.drive.query.internal.zzx;
import java.util.Iterator;
import java.util.List;

public final class zzd implements zzj {
   // $FF: synthetic method
   public final Object zztu() {
      return "ownedByMe()";
   }

   // $FF: synthetic method
   public final Object zzcU(String var1) {
      return String.format("fullTextSearch(%s)", var1);
   }

   // $FF: synthetic method
   public final Object zztv() {
      return "all()";
   }

   // $FF: synthetic method
   public final Object zza(zzx var1, List var2) {
      StringBuilder var5 = new StringBuilder(String.valueOf(var1.getTag()).concat("("));
      String var6 = "";

      for(Iterator var7 = var2.iterator(); var7.hasNext(); var6 = ",") {
         String var8 = (String)var7.next();
         var5.append(var6);
         var5.append(var8);
      }

      return var5.append(")").toString();
   }

   // $FF: synthetic method
   public final Object zzv(Object var1) {
      String var2 = (String)var1;
      return String.format("not(%s)", var2);
   }

   // $FF: synthetic method
   public final Object zza(com.google.android.gms.drive.metadata.zzb var1, Object var2) {
      return String.format("contains(%s,%s)", var1.getName(), var2);
   }

   // $FF: synthetic method
   public final Object zzd(MetadataField var1, Object var2) {
      return String.format("has(%s,%s)", var1.getName(), var2);
   }

   // $FF: synthetic method
   public final Object zza(zzx var1, MetadataField var2, Object var3) {
      return String.format("cmp(%s,%s,%s)", var1.getTag(), var2.getName(), var3);
   }

   // $FF: synthetic method
   public final Object zzd(MetadataField var1) {
      return String.format("fieldOnly(%s)", var1.getName());
   }
}
