package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public final class zzgg {
   private GoogleAnalytics zzadB;
   private Context mContext;
   private Tracker zzadz;

   public zzgg(Context var1) {
      this.mContext = var1;
   }

   public final Tracker zzfv(String var1) {
      this.zzfw(var1);
      return this.zzadz;
   }

   private final synchronized void zzfw(String var1) {
      if (this.zzadB == null) {
         this.zzadB = GoogleAnalytics.getInstance(this.mContext);
         this.zzadB.setLogger(new zzgh());
         this.zzadz = this.zzadB.newTracker(var1);
      }

   }
}
