package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayi;
import com.google.android.gms.internal.zzbaz;

final class zzk extends zzayi {
   zzk(Cast.CastApi.zza var1, GoogleApiClient var2) {
      super(var2);
   }

   public final void zza(zzaxx var1) throws RemoteException {
      try {
         var1.zza((String)"", (zzbaz)this);
      } catch (IllegalStateException var2) {
         this.zzad(2001);
      }
   }
}
