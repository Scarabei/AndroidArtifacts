package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zznd implements CustomRenderedAd {
   private final zzne zzHh;

   public zznd(zzne var1) {
      this.zzHh = var1;
   }

   public final String getBaseUrl() {
      try {
         return this.zzHh.zzdX();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not delegate getBaseURL to CustomRenderedAd", var2);
         return null;
      }
   }

   public final String getContent() {
      try {
         return this.zzHh.getContent();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not delegate getContent to CustomRenderedAd", var2);
         return null;
      }
   }

   public final void onAdRendered(View var1) {
      try {
         this.zzHh.zzi(var1 != null ? zzn.zzw(var1) : null);
      } catch (RemoteException var3) {
         zzajc.zzc("Could not delegate onAdRendered to CustomRenderedAd", var3);
      }
   }

   public final void recordClick() {
      try {
         this.zzHh.recordClick();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not delegate recordClick to CustomRenderedAd", var2);
      }
   }

   public final void recordImpression() {
      try {
         this.zzHh.recordImpression();
      } catch (RemoteException var2) {
         zzajc.zzc("Could not delegate recordImpression to CustomRenderedAd", var2);
      }
   }
}
