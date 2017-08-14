package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzbea {
   private final Set zzauB = Collections.newSetFromMap(new WeakHashMap());

   public final zzbdw zza(@NonNull Object var1, @NonNull Looper var2, @NonNull String var3) {
      zzbdw var4 = zzb(var1, var2, var3);
      this.zzauB.add(var4);
      return var4;
   }

   public final void release() {
      Iterator var1 = this.zzauB.iterator();

      while(var1.hasNext()) {
         ((zzbdw)var1.next()).clear();
      }

      this.zzauB.clear();
   }

   public static zzbdw zzb(@NonNull Object var0, @NonNull Looper var1, @NonNull String var2) {
      zzbo.zzb(var0, "Listener must not be null");
      zzbo.zzb(var1, "Looper must not be null");
      zzbo.zzb(var2, "Listener type must not be null");
      return new zzbdw(var1, var0, var2);
   }

   public static zzbdy zza(@NonNull Object var0, @NonNull String var1) {
      zzbo.zzb(var0, "Listener must not be null");
      zzbo.zzb(var1, "Listener type must not be null");
      zzbo.zzh(var1, "Listener type must not be empty");
      return new zzbdy(var0, var1);
   }
}
