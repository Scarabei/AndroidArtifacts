package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbl;
import com.google.android.gms.ads.internal.zzv;

@zzzn
public final class zzakk {
   public final zzaka zza(Context var1, zziv var2, boolean var3, boolean var4, @Nullable zzcu var5, zzaje var6, zznb var7, zzbl var8, zzv var9, zzig var10) throws zzakm {
      try {
         return (zzaka)zzait.zzc(new zzakl(this, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10));
      } catch (Throwable var12) {
         throw new zzakm(this, "Webview initialization failed.", var12);
      }
   }
}
