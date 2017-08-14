package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayi;

final class zzf extends zzayi {
   // $FF: synthetic field
   private String zzaoQ;
   // $FF: synthetic field
   private String val$message;

   zzf(Cast.CastApi.zza var1, GoogleApiClient var2, String var3, String var4) {
      this.zzaoQ = var3;
      this.val$message = var4;
      super(var2);
   }

   public final void zza(zzaxx var1) throws RemoteException {
      try {
         var1.zza(this.zzaoQ, (String)this.val$message, this);
      } catch (IllegalStateException | IllegalArgumentException var2) {
         this.zzad(2001);
      }
   }
}
