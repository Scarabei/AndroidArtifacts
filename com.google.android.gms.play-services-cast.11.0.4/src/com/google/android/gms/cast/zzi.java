package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzaxx;

final class zzi extends Cast.zza {
   // $FF: synthetic field
   private String zzaoR;
   // $FF: synthetic field
   private String val$sessionId;
   // $FF: synthetic field
   private zzz zzaoT;

   zzi(Cast.CastApi.zza var1, GoogleApiClient var2, String var3, String var4, zzz var5) {
      this.zzaoR = var3;
      this.val$sessionId = var4;
      this.zzaoT = null;
      super(var2);
   }

   public final void zza(zzaxx var1) throws RemoteException {
      try {
         var1.zza(this.zzaoR, this.val$sessionId, this.zzaoT, this);
      } catch (IllegalStateException var2) {
         this.zzad(2001);
      }
   }
}
