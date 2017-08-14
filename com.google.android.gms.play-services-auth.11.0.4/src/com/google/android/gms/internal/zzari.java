package com.google.android.gms.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

final class zzari extends zzard {
   // $FF: synthetic field
   private zzarh zzalz;

   zzari(zzarh var1) {
      this.zzalz = var1;
      super();
   }

   public final void zza(Status var1, Credential var2) {
      this.zzalz.setResult(new zzarf(var1, var2));
   }

   public final void zzd(Status var1) {
      this.zzalz.setResult(zzarf.zze(var1));
   }
}
