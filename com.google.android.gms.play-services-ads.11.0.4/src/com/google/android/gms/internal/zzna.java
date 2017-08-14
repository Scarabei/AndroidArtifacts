package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@zzzn
public final class zzna {
   private final Map zzGY;
   @Nullable
   private final zznb zzsK;

   public zzna(@Nullable zznb var1) {
      this.zzsK = var1;
      this.zzGY = new HashMap();
   }

   public final void zza(String var1, zzmz var2) {
      this.zzGY.put(var1, var2);
   }

   public final void zza(String var1, String var2, long var3) {
      zznb var10000 = this.zzsK;
      zzmz var10001 = (zzmz)this.zzGY.get(var2);
      String[] var9 = new String[]{var1};
      zzmz var6 = var10001;
      zznb var5 = var10000;
      if (var10000 != null && var6 != null) {
         var5.zza(var6, var3, var9);
      }

      var5 = this.zzsK;
      this.zzGY.put(var1, this.zzsK == null ? null : var5.zzc(var3));
   }

   @Nullable
   public final zznb zzdR() {
      return this.zzsK;
   }
}
