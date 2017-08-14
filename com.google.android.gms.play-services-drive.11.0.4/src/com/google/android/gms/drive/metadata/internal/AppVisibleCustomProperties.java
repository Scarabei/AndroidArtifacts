package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class AppVisibleCustomProperties extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable, Iterable {
   public static final Creator CREATOR = new zza();
   public static final AppVisibleCustomProperties zzaPG = (new AppVisibleCustomProperties.zza()).zztm();
   private List zzaPH;

   AppVisibleCustomProperties(Collection var1) {
      zzbo.zzu(var1);
      this.zzaPH = new ArrayList(var1);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaPH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final Iterator iterator() {
      return this.zzaPH.iterator();
   }

   public final Map zztl() {
      HashMap var1 = new HashMap(this.zzaPH.size());
      Iterator var2 = this.zzaPH.iterator();

      while(var2.hasNext()) {
         zzc var3 = (zzc)var2.next();
         var1.put(var3.zzaPJ, var3.mValue);
      }

      return Collections.unmodifiableMap(var1);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaPH});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return var1 != null && var1.getClass() == this.getClass() ? this.zztl().equals(((AppVisibleCustomProperties)var1).zztl()) : false;
      }
   }

   public static class zza {
      private final Map zzaPI = new HashMap();

      public final AppVisibleCustomProperties.zza zza(CustomPropertyKey var1, String var2) {
         zzbo.zzb(var1, "key");
         this.zzaPI.put(var1, new zzc(var1, var2));
         return this;
      }

      public final AppVisibleCustomProperties.zza zza(zzc var1) {
         zzbo.zzb(var1, "property");
         this.zzaPI.put(var1.zzaPJ, var1);
         return this;
      }

      public final AppVisibleCustomProperties zztm() {
         return new AppVisibleCustomProperties(this.zzaPI.values());
      }
   }
}
