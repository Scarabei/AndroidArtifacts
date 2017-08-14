package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzbev {
   public static final Status zzaFj = new Status(8, "The connection to Google Play services was lost");
   private static final zzbbe[] zzaFk = new zzbbe[0];
   final Set zzaFl = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
   private final zzbex zzaFm = new zzbew(this);
   private final Map zzaDF;

   public zzbev(Map var1) {
      this.zzaDF = var1;
   }

   final void zzb(zzbbe var1) {
      this.zzaFl.add(var1);
      var1.zza(this.zzaFm);
   }

   public final void release() {
      zzbbe[] var1;
      int var2 = (var1 = (zzbbe[])this.zzaFl.toArray(zzaFk)).length;

      for(int var3 = 0; var3 < var2; ++var3) {
         zzbbe var4;
         (var4 = var1[var3]).zza((zzbex)null);
         var4.zzpo();
         if (var4.zzpB()) {
            this.zzaFl.remove(var4);
         }
      }

   }

   public final void zzqM() {
      zzbbe[] var1;
      int var2 = (var1 = (zzbbe[])this.zzaFl.toArray(zzaFk)).length;

      for(int var3 = 0; var3 < var2; ++var3) {
         var1[var3].zzs(zzaFj);
      }

   }
}
