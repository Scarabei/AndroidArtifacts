package com.google.android.gms.drive.metadata.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zzbng;
import com.google.android.gms.internal.zzbrc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class MetadataBundle extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzj();
   private Bundle zzaPM;

   MetadataBundle(Bundle var1) {
      this.zzaPM = (Bundle)zzbo.zzu(var1);
      this.zzaPM.setClassLoader(this.getClass().getClassLoader());
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.zzaPM.keySet().iterator();

      String var4;
      while(var3.hasNext()) {
         if (zzf.zzcR(var4 = (String)var3.next()) == null) {
            var2.add(var4);
            String var10002 = String.valueOf(var4);
            String var10001;
            if (var10002.length() != 0) {
               var10001 = "Ignored unknown metadata field in bundle: ".concat(var10002);
            } else {
               String var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Ignored unknown metadata field in bundle: ");
            }

            zzbng.zzy("MetadataBundle", var10001);
         }
      }

      ArrayList var5;
      int var6 = (var5 = (ArrayList)var2).size();
      int var7 = 0;

      while(var7 < var6) {
         Object var10000 = var5.get(var7);
         ++var7;
         var4 = (String)var10000;
         this.zzaPM.remove(var4);
      }

   }

   public static MetadataBundle zztp() {
      return new MetadataBundle(new Bundle());
   }

   public static MetadataBundle zzb(MetadataField var0, Object var1) {
      MetadataBundle var2;
      (var2 = zztp()).zzc(var0, var1);
      return var2;
   }

   public final MetadataBundle zztq() {
      return new MetadataBundle(new Bundle(this.zzaPM));
   }

   public final void zzc(MetadataField var1, Object var2) {
      if (zzf.zzcR(var1.getName()) == null) {
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf(var1.getName());
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Unregistered field: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Unregistered field: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      } else {
         var1.zza(var2, this.zzaPM);
      }
   }

   public final Object zza(MetadataField var1) {
      return var1.zzp(this.zzaPM);
   }

   public final boolean zzc(MetadataField var1) {
      return this.zzaPM.containsKey(var1.getName());
   }

   public final Set zztr() {
      HashSet var1 = new HashSet();
      Iterator var2 = this.zzaPM.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.add(zzf.zzcR(var3));
      }

      return var1;
   }

   public final void setContext(Context var1) {
      BitmapTeleporter var2;
      if ((var2 = (BitmapTeleporter)this.zza(zzbrc.zzaQv)) != null) {
         var2.zzc(var1.getCacheDir());
      }

   }

   public final int hashCode() {
      int var1 = 1;

      String var3;
      for(Iterator var2 = this.zzaPM.keySet().iterator(); var2.hasNext(); var1 = var1 * 31 + this.zzaPM.get(var3).hashCode()) {
         var3 = (String)var2.next();
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MetadataBundle)) {
         return false;
      } else {
         MetadataBundle var2 = (MetadataBundle)var1;
         Set var3;
         if (!(var3 = this.zzaPM.keySet()).equals(var2.zzaPM.keySet())) {
            return false;
         } else {
            Iterator var4 = var3.iterator();

            String var5;
            do {
               if (!var4.hasNext()) {
                  return true;
               }

               var5 = (String)var4.next();
            } while(zzbe.equal(this.zzaPM.get(var5), var2.zzaPM.get(var5)));

            return false;
         }
      }
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzaPM);
      return (new StringBuilder(24 + String.valueOf(var1).length())).append("MetadataBundle [values=").append(var1).append("]").toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaPM, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
