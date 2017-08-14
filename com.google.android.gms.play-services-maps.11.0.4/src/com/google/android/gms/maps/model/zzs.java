package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.internal.zzaa;

final class zzs extends zzaa {
   // $FF: synthetic field
   private TileProvider zzbod;

   zzs(TileOverlayOptions var1, TileProvider var2) {
      this.zzbod = var2;
      super();
   }

   public final Tile getTile(int var1, int var2, int var3) {
      return this.zzbod.getTile(var1, var2, var3);
   }
}
