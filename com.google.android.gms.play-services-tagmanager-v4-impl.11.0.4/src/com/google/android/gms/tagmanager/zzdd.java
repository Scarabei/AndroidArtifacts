package com.google.android.gms.tagmanager;

import android.util.LruCache;

final class zzdd extends LruCache {
   // $FF: synthetic field
   private zzs zzbFn;

   zzdd(zzdc var1, int var2, zzs var3) {
      this.zzbFn = var3;
      super(var2);
   }

   protected final int sizeOf(Object var1, Object var2) {
      return this.zzbFn.sizeOf(var1, var2);
   }
}
