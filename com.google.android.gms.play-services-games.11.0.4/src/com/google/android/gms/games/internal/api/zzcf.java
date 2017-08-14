package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.SnapshotMetadata;

final class zzcf extends zzcj {
   // $FF: synthetic field
   private SnapshotMetadata zzbbr;

   zzcf(zzcb var1, GoogleApiClient var2, SnapshotMetadata var3) {
      this.zzbbr = var3;
      super(var2, (zzcc)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zzi(this, this.zzbbr.getSnapshotId());
   }
}
