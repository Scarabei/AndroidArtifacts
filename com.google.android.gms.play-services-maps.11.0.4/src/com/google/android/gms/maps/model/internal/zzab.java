package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.Tile;

public final class zzab extends zzed implements zzz {
   zzab(IBinder var1) {
      super(var1, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
   }

   public final Tile getTile(int var1, int var2, int var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeInt(var1);
      var4.writeInt(var2);
      var4.writeInt(var3);
      Parcel var5;
      Tile var6 = (Tile)zzef.zza(var5 = this.zza(1, var4), Tile.CREATOR);
      var5.recycle();
      return var6;
   }
}
