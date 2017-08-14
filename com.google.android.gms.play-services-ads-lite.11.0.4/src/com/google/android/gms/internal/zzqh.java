package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;

public final class zzqh extends zzqa {
   private final OnPublisherAdViewLoadedListener zzIP;

   public zzqh(OnPublisherAdViewLoadedListener var1) {
      this.zzIP = var1;
   }

   public final void zza(zzjz var1, IObjectWrapper var2) {
      if (var1 != null && var2 != null) {
         Context var3 = (Context)zzn.zzE(var2);
         PublisherAdView var4 = new PublisherAdView(var3);

         try {
            if (var1.zzay() instanceof zzio) {
               zzio var5 = (zzio)var1.zzay();
               var4.setAdListener(var5 != null ? var5.getAdListener() : null);
            }
         } catch (RemoteException var7) {
            zzajc.zzc("Failed to get ad listener.", var7);
         }

         try {
            if (var1.zzax() instanceof zzix) {
               zzix var8 = (zzix)var1.zzax();
               var4.setAppEventListener(var8 != null ? var8.getAppEventListener() : null);
            }
         } catch (RemoteException var6) {
            zzajc.zzc("Failed to get app event listener.", var6);
         }

         zzaiy.zzaaH.post(new zzqi(this, var4, var1));
      }
   }

   // $FF: synthetic method
   static OnPublisherAdViewLoadedListener zza(zzqh var0) {
      return var0.zzIP;
   }
}
