package com.google.android.gms.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

public final class zzarf implements CredentialRequestResult {
   private final Status mStatus;
   private final Credential zzalx;

   public zzarf(Status var1, Credential var2) {
      this.mStatus = var1;
      this.zzalx = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Credential getCredential() {
      return this.zzalx;
   }

   public static zzarf zze(Status var0) {
      return new zzarf(var0, (Credential)null);
   }
}
