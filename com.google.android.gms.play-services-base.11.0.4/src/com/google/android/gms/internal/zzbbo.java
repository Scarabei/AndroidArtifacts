package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzq;

public final class zzbbo extends GoogleApi {
   private final Api.zze zzaCy;
   private final zzbbi zzaCz;
   private final zzq zzaCA;
   private final Api.zza zzaBe;

   public zzbbo(@NonNull Context var1, Api var2, Looper var3, @NonNull Api.zze var4, @NonNull zzbbi var5, zzq var6, Api.zza var7) {
      super(var1, var2, var3);
      this.zzaCy = var4;
      this.zzaCz = var5;
      this.zzaCA = var6;
      this.zzaBe = var7;
      this.zzaAN.zzb((GoogleApi)this);
   }

   public final Api.zze zzpJ() {
      return this.zzaCy;
   }

   public final Api.zze zza(Looper var1, zzbdd var2) {
      this.zzaCz.zza(var2);
      return this.zzaCy;
   }

   public final zzbej zza(Context var1, Handler var2) {
      return new zzbej(var1, var2, this.zzaCA, this.zzaBe);
   }
}
