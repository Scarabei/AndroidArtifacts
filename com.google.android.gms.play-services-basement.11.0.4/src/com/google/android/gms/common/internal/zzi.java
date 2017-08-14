package com.google.android.gms.common.internal;

import android.util.Log;

public abstract class zzi {
   private Object mListener;
   private boolean zzaHf;
   // $FF: synthetic field
   private zzd zzaHe;

   public zzi(zzd var1, Object var2) {
      this.zzaHe = var1;
      super();
      this.mListener = var2;
      this.zzaHf = false;
   }

   protected abstract void zzs(Object var1);

   public final void zzrk() {
      Object var1;
      synchronized(this) {
         var1 = this.mListener;
         if (this.zzaHf) {
            String var3 = String.valueOf(this);
            Log.w("GmsClient", (new StringBuilder(47 + String.valueOf(var3).length())).append("Callback proxy ").append(var3).append(" being reused. This is not safe.").toString());
         }
      }

      if (var1 != null) {
         try {
            this.zzs(var1);
         } catch (RuntimeException var7) {
            throw var7;
         }
      }

      synchronized(this) {
         this.zzaHf = true;
      }

      this.unregister();
   }

   public final void unregister() {
      this.removeListener();
      synchronized(zzd.zzf(this.zzaHe)) {
         zzd.zzf(this.zzaHe).remove(this);
      }
   }

   public final void removeListener() {
      synchronized(this) {
         this.mListener = null;
      }
   }
}
