package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;

public final class zzbdj extends zzbbz {
   private final GoogleApi zzaEz;

   public zzbdj(GoogleApi var1) {
      super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
      this.zzaEz = var1;
   }

   public final zzbay zzd(@NonNull zzbay var1) {
      return this.zzaEz.zza(var1);
   }

   public final zzbay zze(@NonNull zzbay var1) {
      return this.zzaEz.zzb(var1);
   }

   public final Looper getLooper() {
      return this.zzaEz.getLooper();
   }

   public final void zza(zzbes var1) {
   }

   public final void zzb(zzbes var1) {
   }

   public final Context getContext() {
      return this.zzaEz.getApplicationContext();
   }
}
