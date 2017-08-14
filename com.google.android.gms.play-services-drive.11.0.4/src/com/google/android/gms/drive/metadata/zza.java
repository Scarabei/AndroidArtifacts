package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class zza implements MetadataField {
   private final String zzaPB;
   private final Set zzaPC;
   private final Set zzaPD;
   private final int zzaPE;

   protected zza(String var1, int var2) {
      this.zzaPB = (String)zzbo.zzb(var1, "fieldName");
      this.zzaPC = Collections.singleton(var1);
      this.zzaPD = Collections.emptySet();
      this.zzaPE = var2;
   }

   protected zza(String var1, Collection var2, Collection var3, int var4) {
      this.zzaPB = (String)zzbo.zzb(var1, "fieldName");
      this.zzaPC = Collections.unmodifiableSet(new HashSet(var2));
      this.zzaPD = Collections.unmodifiableSet(new HashSet(var3));
      this.zzaPE = var4;
   }

   public final String getName() {
      return this.zzaPB;
   }

   public final Collection zztk() {
      return this.zzaPC;
   }

   public final Object zzp(Bundle var1) {
      zzbo.zzb(var1, "bundle");
      return var1.get(this.zzaPB) != null ? this.zzq(var1) : null;
   }

   public final void zza(Object var1, Bundle var2) {
      zzbo.zzb(var2, "bundle");
      if (var1 == null) {
         var2.putString(this.zzaPB, (String)null);
      } else {
         this.zza(var2, var1);
      }
   }

   public final void zza(DataHolder var1, MetadataBundle var2, int var3, int var4) {
      zzbo.zzb(var1, "dataHolder");
      zzbo.zzb(var2, "bundle");
      if (this.zzb(var1, var3, var4)) {
         var2.zzc(this, this.zzc(var1, var3, var4));
      }

   }

   protected abstract Object zzq(Bundle var1);

   protected abstract void zza(Bundle var1, Object var2);

   public String toString() {
      return this.zzaPB;
   }

   public final Object zza(DataHolder var1, int var2, int var3) {
      return this.zzb(var1, var2, var3) ? this.zzc(var1, var2, var3) : null;
   }

   protected boolean zzb(DataHolder var1, int var2, int var3) {
      Iterator var4 = this.zzaPC.iterator();

      String var5;
      do {
         if (!var4.hasNext()) {
            return true;
         }

         var5 = (String)var4.next();
      } while(var1.zzcv(var5) && !var1.zzh(var5, var2, var3));

      return false;
   }

   protected abstract Object zzc(DataHolder var1, int var2, int var3);
}
