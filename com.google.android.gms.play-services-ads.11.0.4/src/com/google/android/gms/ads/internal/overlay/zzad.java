package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zznb;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzad extends zzz {
   @Nullable
   public final zzy zza(Context var1, zzaka var2, int var3, boolean var4, zznb var5, zzaq var6) {
      ApplicationInfo var7;
      return (var7 = var1.getApplicationInfo()) != null && var7.targetSdkVersion < 11 ? null : new zzd(var1, var4, var2.zzam().zzAt, var6, new zzar(var1, var2.zziz(), var2.getRequestId(), var5, var2.zziF()));
   }
}
