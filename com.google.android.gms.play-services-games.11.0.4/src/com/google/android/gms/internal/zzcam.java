package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;
import java.util.Set;

public final class zzcam {
   private static final String[] zzbcJ = new String[]{"requestId", "outcome"};
   private final int zzaxu;
   private final HashMap zzbcK;

   private zzcam(int var1, HashMap var2) {
      this.zzaxu = var1;
      this.zzbcK = var2;
   }

   public final int getRequestOutcome(String var1) {
      zzbo.zzb(this.zzbcK.containsKey(var1), (new StringBuilder(46 + String.valueOf(var1).length())).append("Request ").append(var1).append(" was not part of the update operation!").toString());
      return ((Integer)this.zzbcK.get(var1)).intValue();
   }

   public final Set getRequestIds() {
      return this.zzbcK.keySet();
   }

   public static zzcam zzN(DataHolder var0) {
      zzcao var1;
      (var1 = new zzcao()).zzbd(var0.getStatusCode());
      int var2 = var0.getCount();

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = var0.zzat(var3);
         var1.zzs(var0.zzd("requestId", var3, var4), var0.zzc("outcome", var3, var4));
      }

      return var1.zzvm();
   }

   // $FF: synthetic method
   zzcam(int var1, HashMap var2, zzcan var3) {
      this(var1, var2);
   }
}
