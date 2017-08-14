package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.ParticipantResult;

final class zzcz extends zzdp {
   // $FF: synthetic field
   private String zzbbw;
   // $FF: synthetic field
   private byte[] zzbby;
   // $FF: synthetic field
   private String zzbbz;
   // $FF: synthetic field
   private ParticipantResult[] zzbbA;

   zzcz(zzcu var1, GoogleApiClient var2, String var3, byte[] var4, String var5, ParticipantResult[] var6) {
      this.zzbbw = var3;
      this.zzbby = var4;
      this.zzbbz = var5;
      this.zzbbA = var6;
      super(var2, (zzcv)null);
   }

   // $FF: synthetic method
   protected final void zza(com.google.android.gms.common.api.Api.zzb var1) throws RemoteException {
      GamesClientImpl var3 = (GamesClientImpl)var1;
      var3.zza(this, this.zzbbw, (byte[])this.zzbby, (String)this.zzbbz, (ParticipantResult[])this.zzbbA);
   }
}
