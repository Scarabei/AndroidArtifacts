package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzaj extends zzaq {
   // $FF: synthetic field
   private OnMapReadyCallback zzbmr;

   zzaj(SupportMapFragment.zza var1, OnMapReadyCallback var2) {
      this.zzbmr = var2;
      super();
   }

   public final void zza(IGoogleMapDelegate var1) throws RemoteException {
      this.zzbmr.onMapReady(new GoogleMap(var1));
   }
}
