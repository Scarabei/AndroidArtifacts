package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.zzz;

final class zzr implements TileProvider {
   private final zzz zzbob;
   // $FF: synthetic field
   private TileOverlayOptions zzboc;

   zzr(TileOverlayOptions var1) {
      this.zzboc = var1;
      super();
      this.zzbob = TileOverlayOptions.zza(this.zzboc);
   }

   public final Tile getTile(int var1, int var2, int var3) {
      try {
         return this.zzbob.getTile(var1, var2, var3);
      } catch (RemoteException var4) {
         return null;
      }
   }
}
