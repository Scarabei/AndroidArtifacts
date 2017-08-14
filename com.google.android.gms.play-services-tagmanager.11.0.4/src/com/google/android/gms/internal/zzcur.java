package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzcur implements zzcux {
   private static zzcur zzbHY;
   private static final Object zzbDk = new Object();
   private zzcvt zzbHZ;
   private zzcuy zzbIa;
   private static final Set zzbIb = new HashSet(Arrays.asList("GET", "HEAD", "POST", "PUT"));

   private zzcur(Context var1) {
      this(zzcuz.zzbw(var1), new zzcwb());
   }

   private zzcur(zzcuy var1, zzcvt var2) {
      this.zzbIa = var1;
      this.zzbHZ = var2;
   }

   public static zzcux zzbv(Context var0) {
      Object var1 = zzbDk;
      synchronized(zzbDk) {
         if (zzbHY == null) {
            zzbHY = new zzcur(var0);
         }

         return zzbHY;
      }
   }

   public final boolean zzfh(String var1) {
      return this.zza(var1, (String)null, (String)null, (Map)null, (String)null);
   }

   public final boolean zzW(String var1, String var2) {
      return this.zza(var1, (String)null, var2, (Map)null, (String)null);
   }

   public final boolean zza(String var1, @Nullable String var2, @Nullable String var3, @Nullable Map var4, @Nullable String var5) {
      if (var2 != null && !zzbIb.contains(var2)) {
         zzcvk.zzaT(String.format("Unsupport http method %s. Drop the hit.", var2));
         return false;
      } else if (!zzcvs.zzCw().isPreview() && !this.zzbHZ.zzlL()) {
         zzcvk.zzaT("Too many hits sent too quickly (rate throttled).");
         return false;
      } else {
         this.zzbIa.zzb(var1, var2, var3, var4, var5);
         return true;
      }
   }

   public final void dispatch() {
      zzcwd.zzCA().dispatch();
   }
}
