package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbh {
   private static final zzbn zzaIi = new zzbi();

   private static Task zza(PendingResult var0, zzbm var1) {
      zzbn var4 = zzaIi;
      TaskCompletionSource var5 = new TaskCompletionSource();
      var0.zza(new zzbj(var0, var5, var1, var4));
      return var5.getTask();
   }

   public static Task zza(PendingResult var0, Response var1) {
      return zza(var0, (zzbm)(new zzbk(var1)));
   }

   public static Task zzb(PendingResult var0) {
      return zza(var0, (zzbm)(new zzbl()));
   }
}
