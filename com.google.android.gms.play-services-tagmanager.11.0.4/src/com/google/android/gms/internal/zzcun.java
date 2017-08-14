package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.tagmanager.zzce;
import com.google.android.gms.tagmanager.zzcn;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public final class zzcun {
   private final Context mContext;
   private final cd zzbHK;
   private final ExecutorService zzGM;
   private final ScheduledExecutorService zzbHV;
   private final zzcn zzbHN;
   private final zzce zzbHW;

   public zzcun(Context var1, zzcn var2, zzce var3) {
      this(var1, var2, var3, new cd(var1), zzcxe.zza.zzbx(var1), zzcxg.zzCD());
   }

   private zzcun(Context var1, zzcn var2, zzce var3, cd var4, ExecutorService var5, ScheduledExecutorService var6) {
      this.mContext = ((Context)zzbo.zzu(var1)).getApplicationContext();
      this.zzbHN = (zzcn)zzbo.zzu(var2);
      this.zzbHW = (zzce)zzbo.zzu(var3);
      this.zzbHK = (cd)zzbo.zzu(var4);
      this.zzGM = (ExecutorService)zzbo.zzu(var5);
      this.zzbHV = (ScheduledExecutorService)zzbo.zzu(var6);
   }

   public final zzcuf zzm(String var1, @Nullable String var2, @Nullable String var3) {
      zzcvz var4 = new zzcvz(this.mContext, this.zzbHN, this.zzbHW, var1);
      zzcuo var5 = new zzcuo(this.mContext, var1);
      return new zzcuf(this.mContext, var1, var2, var3, var4, this.zzbHK, this.zzGM, this.zzbHV, this.zzbHN, zzi.zzrY(), var5);
   }
}
