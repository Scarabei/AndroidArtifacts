package com.google.android.gms.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.internal.zzbo;

public final class zzanp extends zzamh {
   private boolean zzahb;
   private boolean zzahc;
   private AlarmManager zzahd = (AlarmManager)this.getContext().getSystemService("alarm");

   protected zzanp(zzamj var1) {
      super(var1);
   }

   protected final void zzjD() {
      try {
         this.zzahd.cancel(this.zzlD());
         ActivityInfo var1;
         if (zzank.zzlr() > 0L && (var1 = this.getContext().getPackageManager().getReceiverInfo(new ComponentName(this.getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2)) != null && var1.enabled) {
            this.zzbo("Receiver registered. Using alarm for local dispatch.");
            this.zzahb = true;
         }

      } catch (NameNotFoundException var2) {
         ;
      }
   }

   public final boolean zzlC() {
      return this.zzahb;
   }

   public final boolean zzbo() {
      return this.zzahc;
   }

   public final void schedule() {
      this.zzkD();
      zzbo.zza(this.zzahb, "Receiver not registered");
      long var1;
      if ((var1 = zzank.zzlr()) > 0L) {
         this.cancel();
         long var3 = this.zzkq().elapsedRealtime() + var1;
         this.zzahc = true;
         this.zzahd.setInexactRepeating(2, var3, 0L, this.zzlD());
      }

   }

   public final void cancel() {
      this.zzkD();
      this.zzahc = false;
      this.zzahd.cancel(this.zzlD());
   }

   private final PendingIntent zzlD() {
      Intent var1;
      (var1 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH")).setComponent(new ComponentName(this.getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
      return PendingIntent.getBroadcast(this.getContext(), 0, var1, 0);
   }
}
