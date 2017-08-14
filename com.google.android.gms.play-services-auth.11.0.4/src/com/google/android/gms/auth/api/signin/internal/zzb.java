package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbei;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zzb extends AsyncTaskLoader implements zzbei {
   private Semaphore zzami = new Semaphore(0);
   private Set zzamj;

   public zzb(Context var1, Set var2) {
      super(var1);
      this.zzamj = var2;
   }

   private final Void zzmE() {
      int var1 = 0;
      Iterator var2 = this.zzamj.iterator();

      while(var2.hasNext()) {
         if (((GoogleApiClient)var2.next()).zza(this)) {
            ++var1;
         }
      }

      try {
         this.zzami.tryAcquire(var1, 5L, TimeUnit.SECONDS);
      } catch (InterruptedException var3) {
         Log.i("GACSignInLoader", "Unexpected InterruptedException", var3);
         Thread.currentThread().interrupt();
      }

      return null;
   }

   protected final void onStartLoading() {
      this.zzami.drainPermits();
      this.forceLoad();
   }

   public final void zzmF() {
      this.zzami.release();
   }

   // $FF: synthetic method
   public final Object loadInBackground() {
      return this.zzmE();
   }
}
