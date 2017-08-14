package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.zzh;
import com.google.android.gms.plus.internal.zzn;

final class zzc extends com.google.android.gms.common.api.Api.zza {
   public final int getPriority() {
      return 2;
   }

   // $FF: synthetic method
   public final zze zza(Context var1, Looper var2, zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      Plus.PlusOptions var10 = (Plus.PlusOptions)var4;
      if (var10 == null) {
         var10 = new Plus.PlusOptions((zzc)null);
      }

      return new zzh(var1, var2, var3, new zzn(var3.zzrl().name, zzs.zzc(var3.zzro()), (String[])var10.zzbAs.toArray(new String[0]), new String[0], var1.getPackageName(), var1.getPackageName(), (String)null, new PlusCommonExtras()), var5, var6);
   }
}
