package com.google.android.gms.cast.framework;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.MediaRouteActionProvider;
import android.support.v7.app.MediaRouteButton;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.common.internal.zzbo;
import java.util.Locale;

public final class CastButtonFactory {
   public static MenuItem setUpMediaRouteButton(Context var0, Menu var1, int var2) {
      zzbo.zzcz("Must be called from the main thread.");
      zzbo.zzu(var1);
      CastContext var3 = CastContext.getSharedInstance(var0);
      MenuItem var4;
      if ((var4 = var1.findItem(var2)) == null) {
         throw new IllegalArgumentException(String.format(Locale.ROOT, "menu doesn't contain a menu item whose ID is %d.", var2));
      } else {
         MediaRouteActionProvider var5;
         if ((var5 = (MediaRouteActionProvider)MenuItemCompat.getActionProvider(var4)) == null) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "menu item with ID %d doesn't have a MediaRouteActionProvider.", var2));
         } else {
            var5.setRouteSelector(var3.getMergedSelector());
            return var4;
         }
      }
   }

   public static void setUpMediaRouteButton(Context var0, MediaRouteButton var1) {
      zzbo.zzcz("Must be called from the main thread.");
      CastContext var2 = CastContext.getSharedInstance(var0);
      var1.setRouteSelector(var2.getMergedSelector());
   }
}
