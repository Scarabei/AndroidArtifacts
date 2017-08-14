package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.StatusCallback;

public final class zzcpz {
   private final SimpleArrayMap zzbzJ = new SimpleArrayMap(1);

   @Nullable
   public final synchronized zzcpn zzb(GoogleApiClient var1, @Nullable Object var2) {
      if (var2 == null) {
         return null;
      } else {
         Object var3;
         if (!zza((zzcpn)(var3 = (zzcpn)this.zzbzJ.get(var2)))) {
            zzbdw var5 = var1.zzp(var2);
            Object var10000;
            if (var2 instanceof StatusCallback) {
               var10000 = new zzcpv(var5);
            } else {
               if (!(var2 instanceof MessageListener)) {
                  IllegalArgumentException var6 = new IllegalArgumentException;
                  String var10003 = String.valueOf(var2.getClass().getName());
                  String var10002;
                  if (var10003.length() != 0) {
                     var10002 = "Unknown callback of type ".concat(var10003);
                  } else {
                     String var10004 = new String;
                     var10002 = var10004;
                     var10004.<init>("Unknown callback of type ");
                  }

                  var6.<init>(var10002);
                  throw var6;
               }

               var10000 = new zzcpo(var5);
            }

            var3 = var10000;
            this.zzbzJ.put(var2, var3);
         }

         return (zzcpn)var3;
      }
   }

   @Nullable
   public final synchronized zzcpn zzE(@Nullable Object var1) {
      if (var1 == null) {
         return null;
      } else {
         zzcpn var2;
         if (zza(var2 = (zzcpn)this.zzbzJ.get(var1))) {
            return var2;
         } else {
            this.zzbzJ.remove(var1);
            return null;
         }
      }
   }

   @Nullable
   public final synchronized zzcpn zzh(zzbdw var1) {
      return var1 == null ? null : this.zzE(this.zzj(var1));
   }

   public final synchronized void zzi(zzbdw var1) {
      var1.clear();
      this.zzbzJ.remove(this.zzj(var1));
   }

   @Nullable
   private final synchronized Object zzj(zzbdw var1) {
      for(int var2 = 0; var2 < this.zzbzJ.size(); ++var2) {
         Object var3 = this.zzbzJ.keyAt(var2);
         if (((zzcpn)this.zzbzJ.get(var3)).zzzX().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public final synchronized void clear() {
      this.zzbzJ.clear();
   }

   private static boolean zza(zzcpn var0) {
      return var0 != null && var0.zzzX().zzoc();
   }
}
