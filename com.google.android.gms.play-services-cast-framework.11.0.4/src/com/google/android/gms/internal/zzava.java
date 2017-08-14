package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.RouteInfo;
import com.google.android.gms.common.internal.zzbo;

public final class zzava extends Callback {
   private static final zzayo zzarK = new zzayo("MediaRouterCallback");
   private final zzauq zzasN;

   public zzava(zzauq var1) {
      this.zzasN = (zzauq)zzbo.zzu(var1);
   }

   public final void onRouteSelected(MediaRouter var1, RouteInfo var2) {
      try {
         this.zzasN.zzf(var2.getId(), var2.getExtras());
      } catch (RemoteException var4) {
         zzarK.zzb(var4, "Unable to call %s on %s.", new Object[]{"onRouteSelected", zzauq.class.getSimpleName()});
      }
   }

   public final void onRouteUnselected(MediaRouter var1, RouteInfo var2, int var3) {
      try {
         this.zzasN.zza(var2.getId(), var2.getExtras(), var3);
      } catch (RemoteException var5) {
         zzarK.zzb(var5, "Unable to call %s on %s.", new Object[]{"onRouteUnselected", zzauq.class.getSimpleName()});
      }
   }

   public final void onRouteAdded(MediaRouter var1, RouteInfo var2) {
      try {
         this.zzasN.zzc(var2.getId(), var2.getExtras());
      } catch (RemoteException var4) {
         zzarK.zzb(var4, "Unable to call %s on %s.", new Object[]{"onRouteAdded", zzauq.class.getSimpleName()});
      }
   }

   public final void onRouteChanged(MediaRouter var1, RouteInfo var2) {
      try {
         this.zzasN.zzd(var2.getId(), var2.getExtras());
      } catch (RemoteException var4) {
         zzarK.zzb(var4, "Unable to call %s on %s.", new Object[]{"onRouteChanged", zzauq.class.getSimpleName()});
      }
   }

   public final void onRouteRemoved(MediaRouter var1, RouteInfo var2) {
      try {
         this.zzasN.zze(var2.getId(), var2.getExtras());
      } catch (RemoteException var4) {
         zzarK.zzb(var4, "Unable to call %s on %s.", new Object[]{"onRouteRemoved", zzauq.class.getSimpleName()});
      }
   }
}
