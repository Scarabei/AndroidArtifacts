package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public final class zzct extends zzcs {
   public static zzct zza(String var0, Context var1, boolean var2) {
      zza(var1, var2);
      return new zzct(var1, var0, var2);
   }

   private zzct(Context var1, String var2, boolean var3) {
      super(var1, var2, var3);
   }

   protected final List zzb(zzdb var1, zzax var2, zzau var3) {
      if (var1.zzC() != null && this.zzqk) {
         int var4 = var1.zzy();
         ArrayList var5;
         (var5 = new ArrayList()).addAll(super.zzb(var1, var2, var3));
         var5.add(new zzdp(var1, "Ob9vkrYwqwLnJveTtaSWm/WWJCjo/9DRtOCY3btkKa6pJtjMu6sI0iK41HSh10io", "UrT94Dq3ubetC7rQ64nVjqMQ53po9X61geGgrP+ILCU=", var2, var4, 24));
         return var5;
      } else {
         return super.zzb(var1, var2, var3);
      }
   }
}
