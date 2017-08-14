package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.ArrayList;
import java.util.List;

public class zzcr extends zzcs {
   private static final String TAG = zzcr.class.getSimpleName();
   private Info zzqi;

   public static zzcr zzb(Context var0) {
      zza(var0, true);
      return new zzcr(var0);
   }

   private zzcr(Context var1) {
      super(var1, "");
   }

   public static String zza(String var0, String var1) {
      return zzbv.zza(var0, var1, true);
   }

   protected final zzax zza(Context var1, View var2) {
      return null;
   }

   protected final void zza(zzdb var1, zzax var2, zzau var3) {
      if (var1.zzqP) {
         if (this.zzqi != null) {
            String var4;
            if (!TextUtils.isEmpty(var4 = this.zzqi.getId())) {
               var2.zzbU = zzdg.zzn(var4);
               var2.zzbV = Integer.valueOf(5);
               var2.zzbW = this.zzqi.isLimitAdTrackingEnabled();
            }

            this.zzqi = null;
            return;
         }
      } else {
         zza(this.zzb(var1, var2, var3));
      }

   }

   protected final List zzb(zzdb var1, zzax var2, zzau var3) {
      ArrayList var4 = new ArrayList();
      if (var1.zzC() == null) {
         return var4;
      } else {
         int var5 = var1.zzy();
         var4.add(new zzdp(var1, "Ob9vkrYwqwLnJveTtaSWm/WWJCjo/9DRtOCY3btkKa6pJtjMu6sI0iK41HSh10io", "UrT94Dq3ubetC7rQ64nVjqMQ53po9X61geGgrP+ILCU=", var2, var5, 24));
         return var4;
      }
   }

   public final void zza(Info var1) {
      this.zzqi = var1;
   }
}
