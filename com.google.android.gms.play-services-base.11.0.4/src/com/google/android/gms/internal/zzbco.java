package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Collections;
import java.util.Iterator;

public final class zzbco implements zzbcw {
   private final zzbcx zzaCZ;

   public zzbco(zzbcx var1) {
      this.zzaCZ = var1;
   }

   public final void begin() {
      Iterator var1 = this.zzaCZ.zzaDF.values().iterator();

      while(var1.hasNext()) {
         ((Api.zze)var1.next()).disconnect();
      }

      this.zzaCZ.zzaCl.zzaDG = Collections.emptySet();
   }

   public final zzbay zzd(zzbay var1) {
      this.zzaCZ.zzaCl.zzaCJ.add(var1);
      return var1;
   }

   public final zzbay zze(zzbay var1) {
      throw new IllegalStateException("GoogleApiClient is not connected yet.");
   }

   public final boolean disconnect() {
      return true;
   }

   public final void connect() {
      this.zzaCZ.zzqh();
   }

   public final void onConnected(Bundle var1) {
   }

   public final void zza(ConnectionResult var1, Api var2, boolean var3) {
   }

   public final void onConnectionSuspended(int var1) {
   }
}
