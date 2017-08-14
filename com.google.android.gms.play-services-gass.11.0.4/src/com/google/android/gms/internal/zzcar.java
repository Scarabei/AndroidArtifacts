package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class zzcar implements zzf, zzg {
   private zzcas zzbfo;
   private final String packageName;
   private final String zzbfp;
   private final LinkedBlockingQueue zzbfq;
   private final HandlerThread zzbfr;

   public zzcar(Context var1, String var2, String var3) {
      this.packageName = var2;
      this.zzbfp = var3;
      this.zzbfr = new HandlerThread("GassClient");
      this.zzbfr.start();
      this.zzbfo = new zzcas(var1, this.zzbfr.getLooper(), this, this);
      this.zzbfq = new LinkedBlockingQueue();
      this.zzbfo.zzrb();
   }

   public final zzax zzbe(int var1) {
      zzax var2 = null;

      try {
         var2 = (zzax)this.zzbfq.poll(5000L, TimeUnit.MILLISECONDS);
      } catch (InterruptedException var3) {
         ;
      }

      return var2 == null ? zzvy() : var2;
   }

   private final zzcax zzvx() {
      try {
         return this.zzbfo.zzvz();
      } catch (DeadObjectException | IllegalStateException var1) {
         return null;
      }
   }

   public final void onConnectionSuspended(int var1) {
      try {
         this.zzbfq.put(zzvy());
      } catch (InterruptedException var2) {
         ;
      }
   }

   public final void onConnected(Bundle var1) {
      zzcax var2;
      if ((var2 = this.zzvx()) != null) {
         try {
            zzax var3 = var2.zza(new zzcat(this.packageName, this.zzbfp)).zzvA();
            this.zzbfq.put(var3);
            return;
         } catch (Throwable var9) {
            try {
               this.zzbfq.put(zzvy());
            } catch (InterruptedException var8) {
               ;
            }
         } finally {
            this.zzgA();
            this.zzbfr.quit();
         }

      }
   }

   public final void onConnectionFailed(ConnectionResult var1) {
      try {
         this.zzbfq.put(zzvy());
      } catch (InterruptedException var2) {
         ;
      }
   }

   private final void zzgA() {
      if (this.zzbfo != null && (this.zzbfo.isConnected() || this.zzbfo.isConnecting())) {
         this.zzbfo.disconnect();
      }

   }

   private static zzax zzvy() {
      zzax var0;
      (var0 = new zzax()).zzbq = 32768L;
      return var0;
   }
}
