package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.location.places.internal.zzu;

public final class zzd extends zzu {
   private final zzf zzbjq;
   private final zze zzbjr;

   public zzd(zzf var1) {
      this.zzbjq = var1;
      this.zzbjr = null;
   }

   public zzd(zze var1) {
      this.zzbjq = null;
      this.zzbjr = var1;
   }

   public final void zza(PlacePhotoMetadataResult var1) throws RemoteException {
      this.zzbjq.setResult(var1);
   }

   public final void zza(PlacePhotoResult var1) throws RemoteException {
      this.zzbjr.setResult(var1);
   }
}
