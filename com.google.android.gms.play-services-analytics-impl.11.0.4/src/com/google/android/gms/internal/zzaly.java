package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzaly extends zzamh {
   private final zzamv zzafB;

   public zzaly(zzamj var1, zzaml var2) {
      super(var1);
      zzbo.zzu(var2);
      this.zzafB = new zzamv(var1, var2);
   }

   protected final void zzjD() {
      this.zzafB.initialize();
   }

   public final void start() {
      this.zzafB.start();
   }

   public final void setLocalDispatchPeriod(int var1) {
      this.zzkD();
      this.zzb("setLocalDispatchPeriod (sec)", var1);
      this.zzkt().zzf(new zzalz(this, var1));
   }

   public final long zza(zzamm var1) {
      this.zzkD();
      zzbo.zzu(var1);
      zzl.zzjC();
      long var2;
      if ((var2 = this.zzafB.zza(var1, true)) == 0L) {
         this.zzafB.zzb(var1);
      }

      return var2;
   }

   public final void zza(zzanx var1) {
      zzbo.zzu(var1);
      this.zzkD();
      this.zzb("Hit delivery requested", var1);
      this.zzkt().zzf(new zzamc(this, var1));
   }

   public final void zzkk() {
      this.zzkD();
      this.zzkt().zzf(new zzamd(this));
   }

   public final void zza(zzanq var1) {
      this.zzkD();
      this.zzkt().zzf(new zzame(this, var1));
   }

   public final void zzkl() {
      this.zzkD();
      Context var1;
      if (zzaoj.zzac(var1 = this.getContext()) && zzaok.zzad(var1)) {
         Intent var2;
         (var2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH")).setComponent(new ComponentName(var1, "com.google.android.gms.analytics.AnalyticsService"));
         var1.startService(var2);
      } else {
         this.zza((zzanq)null);
      }
   }

   public final boolean zzkm() {
      this.zzkD();
      Future var1 = this.zzkt().zzd(new zzamf(this));

      try {
         var1.get(4L, TimeUnit.SECONDS);
         return true;
      } catch (InterruptedException var3) {
         this.zzd("syncDispatchLocalHits interrupted", var3);
         return false;
      } catch (ExecutionException var4) {
         this.zze("syncDispatchLocalHits failed", var4);
         return false;
      } catch (TimeoutException var5) {
         this.zzd("syncDispatchLocalHits timed out", var5);
         return false;
      }
   }

   public final void zzkn() {
      this.zzkD();
      zzl.zzjC();
      zzamv var1 = this.zzafB;
      zzl.zzjC();
      var1.zzkD();
      var1.zzbo("Service disconnected");
   }

   final void onServiceConnected() {
      zzl.zzjC();
      this.zzafB.onServiceConnected();
   }

   final void zzko() {
      zzl.zzjC();
      this.zzafB.zzko();
   }

   public final void zza(String var1, Runnable var2) {
      zzbo.zzh(var1, "campaign param can't be empty");
      this.zzkt().zzf(new zzamb(this, var1, var2));
   }

   // $FF: synthetic method
   static zzamv zza(zzaly var0) {
      return var0.zzafB;
   }
}
