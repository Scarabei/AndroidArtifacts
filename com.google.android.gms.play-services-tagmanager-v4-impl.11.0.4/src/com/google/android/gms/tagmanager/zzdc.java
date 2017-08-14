package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;

@TargetApi(12)
final class zzdc implements zzp {
   private LruCache zzbFm;

   zzdc(int var1, zzs var2) {
      this.zzbFm = new zzdd(this, 1048576, var2);
   }

   public final void zzf(Object var1, Object var2) {
      this.zzbFm.put(var1, var2);
   }

   public final Object get(Object var1) {
      return this.zzbFm.get(var1);
   }
}
