package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzarh extends zzarn {
   // $FF: synthetic field
   private CredentialRequest zzaly;

   zzarh(zzarg var1, GoogleApiClient var2, CredentialRequest var3) {
      this.zzaly = var3;
      super(var2);
   }

   protected final void zza(Context var1, zzart var2) throws RemoteException {
      zzari var3 = new zzari(this);
      var2.zza(var3, (CredentialRequest)this.zzaly);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return zzarf.zze(var1);
   }
}
