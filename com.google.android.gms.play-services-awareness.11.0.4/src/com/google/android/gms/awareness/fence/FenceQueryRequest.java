package com.google.android.gms.awareness.fence;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbja;
import java.util.Collection;
import java.util.Iterator;

public abstract class FenceQueryRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static FenceQueryRequest forFences(Collection var0) {
      zzbo.zzu(var0);
      Iterator var1 = var0.iterator();

      while(var1.hasNext()) {
         zzbo.zzcF((String)var1.next());
      }

      return new zzbja(var0);
   }

   public static FenceQueryRequest forFences(String... var0) {
      zzbo.zzu(var0);
      String[] var1 = var0;
      int var2 = var0.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         zzbo.zzcF(var1[var3]);
      }

      return new zzbja(var0);
   }

   public static FenceQueryRequest all() {
      return new zzbja();
   }
}
