package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

final class zzcg extends zzcn {
   // $FF: synthetic field
   private String zzbbs;
   // $FF: synthetic field
   private String zzbbt;
   // $FF: synthetic field
   private SnapshotMetadataChange zzbbq;
   // $FF: synthetic field
   private SnapshotContents zzbbu;

   zzcg(zzcb var1, GoogleApiClient var2, String var3, String var4, SnapshotMetadataChange var5, SnapshotContents var6) {
      this.zzbbs = var3;
      this.zzbbt = var4;
      this.zzbbq = var5;
      this.zzbbu = var6;
      super(var2, (zzcc)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbbs, (String)this.zzbbt, (SnapshotMetadataChange)this.zzbbq, (SnapshotContents)this.zzbbu);
   }
}
