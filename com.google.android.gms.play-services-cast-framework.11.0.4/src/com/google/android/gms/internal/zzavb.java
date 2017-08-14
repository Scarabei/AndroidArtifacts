package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.RouteInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzavb extends zzaup {
   private final MediaRouter zzapE;
   private final Map zzasO = new HashMap();

   public zzavb(MediaRouter var1) {
      this.zzapE = var1;
   }

   public final void zza(Bundle var1, zzauq var2) {
      MediaRouteSelector var3 = MediaRouteSelector.fromBundle(var1);
      if (!this.zzasO.containsKey(var3)) {
         this.zzasO.put(var3, new HashSet());
      }

      ((Set)this.zzasO.get(var3)).add(new zzava(var2));
   }

   public final void zza(Bundle var1, int var2) {
      MediaRouteSelector var3 = MediaRouteSelector.fromBundle(var1);
      Iterator var4 = ((Set)this.zzasO.get(var3)).iterator();

      while(var4.hasNext()) {
         Callback var5 = (Callback)var4.next();
         this.zzapE.addCallback(var3, var5, var2);
      }

   }

   public final void zzk(Bundle var1) {
      MediaRouteSelector var2 = MediaRouteSelector.fromBundle(var1);
      Iterator var3 = ((Set)this.zzasO.get(var2)).iterator();

      while(var3.hasNext()) {
         Callback var4 = (Callback)var3.next();
         this.zzapE.removeCallback(var4);
      }

   }

   public final boolean zzb(Bundle var1, int var2) {
      return this.zzapE.isRouteAvailable(MediaRouteSelector.fromBundle(var1), var2);
   }

   public final void zzce(String var1) {
      Iterator var2 = this.zzapE.getRoutes().iterator();

      RouteInfo var3;
      do {
         if (!var2.hasNext()) {
            return;
         }
      } while(!(var3 = (RouteInfo)var2.next()).getId().equals(var1));

      this.zzapE.selectRoute(var3);
   }

   public final void zznI() {
      this.zzapE.selectRoute(this.zzapE.getDefaultRoute());
   }

   public final boolean zznJ() {
      return this.zzapE.getSelectedRoute().getId().equals(this.zzapE.getDefaultRoute().getId());
   }

   public final Bundle zzcf(String var1) {
      Iterator var2 = this.zzapE.getRoutes().iterator();

      RouteInfo var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }
      } while(!(var3 = (RouteInfo)var2.next()).getId().equals(var1));

      return var3.getExtras();
   }

   public final String zznK() {
      return this.zzapE.getSelectedRoute().getId();
   }

   public final void setMediaSessionCompat(MediaSessionCompat var1) {
      this.zzapE.setMediaSessionCompat(var1);
   }
}
