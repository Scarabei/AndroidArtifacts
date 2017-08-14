package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public final class zzcxj {
   private GoogleAnalytics zzadB;
   private Context mContext;
   private Tracker zzadz;

   public zzcxj(Context var1) {
      this.mContext = var1;
   }

   public final Tracker zzfv(String var1) {
      this.zzfw(var1);
      return this.zzadz;
   }

   private final synchronized void zzfw(String var1) {
      if (this.zzadB == null) {
         this.zzadB = GoogleAnalytics.getInstance(this.mContext);
         this.zzadB.setLogger(new zzcxk());
         this.zzadz = this.zzadB.newTracker(var1);
      }

   }
}
