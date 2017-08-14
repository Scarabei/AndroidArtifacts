package com.google.android.gms.internal;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;

public final class zzbdw {
   private final zzbdx zzaEM;
   private volatile Object mListener;
   private final zzbdy zzaEN;

   zzbdw(@NonNull Looper var1, @NonNull Object var2, @NonNull String var3) {
      this.zzaEM = new zzbdx(this, var1);
      this.mListener = zzbo.zzb(var2, "Listener must not be null");
      this.zzaEN = new zzbdy(var2, zzbo.zzcF(var3));
   }

   public final void zza(zzbdz var1) {
      zzbo.zzb(var1, "Notifier must not be null");
      Message var2 = this.zzaEM.obtainMessage(1, var1);
      this.zzaEM.sendMessage(var2);
   }

   public final boolean zzoc() {
      return this.mListener != null;
   }

   public final void clear() {
      this.mListener = null;
   }

   @NonNull
   public final zzbdy zzqG() {
      return this.zzaEN;
   }

   final void zzb(zzbdz var1) {
      Object var2 = this.mListener;
      if (this.mListener == null) {
         var1.zzpT();
      } else {
         try {
            var1.zzq(var2);
         } catch (RuntimeException var4) {
            var1.zzpT();
            throw var4;
         }
      }
   }
}
