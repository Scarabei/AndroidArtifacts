package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.internal.zzbaz;

final class zzce extends zzch {
   // $FF: synthetic field
   private Snapshot zzbbp;
   // $FF: synthetic field
   private SnapshotMetadataChange zzbbq;

   zzce(zzcb var1, GoogleApiClient var2, Snapshot var3, SnapshotMetadataChange var4) {
      this.zzbbp = var3;
      this.zzbbq = var4;
      super(var2, (zzcc)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza((zzbaz)this, (Snapshot)this.zzbbp, (SnapshotMetadataChange)this.zzbbq);
   }
}
