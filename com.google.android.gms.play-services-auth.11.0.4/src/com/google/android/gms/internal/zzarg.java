package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zze;

public final class zzarg implements CredentialsApi {
   public final PendingResult request(GoogleApiClient var1, CredentialRequest var2) {
      return var1.zzd(new zzarh(this, var1, var2));
   }

   public final PendingIntent getHintPickerIntent(GoogleApiClient var1, HintRequest var2) {
      zzbo.zzb(var1.zza(Auth.CREDENTIALS_API), "Auth.CREDENTIALS_API must be added to GoogleApiClient to use this API");
      Auth.AuthCredentialsOptions var3 = ((zzaro)var1.zza(Auth.zzakE)).zzmu();
      Context var4;
      zzbo.zzb(var4 = var1.getContext(), "context must not be null");
      zzbo.zzb(var2, "request must not be null");
      PasswordSpecification var10 = var3 != null && var3.zzmr() != null ? var3.zzmr() : PasswordSpecification.zzalo;
      Intent var11 = (new Intent("com.google.android.gms.auth.api.credentials.PICKER")).putExtra("com.google.android.gms.credentials.hintRequestVersion", 2).putExtra("com.google.android.gms.credentials.RequestType", "Hints");
      zze.zza(var10, var11, "com.google.android.gms.credentials.PasswordSpecification");
      zze.zza(var2, var11, "com.google.android.gms.credentials.HintRequest");
      return PendingIntent.getActivity(var4, 2000, var11, 268435456);
   }

   public final PendingResult save(GoogleApiClient var1, Credential var2) {
      return var1.zze(new zzarj(this, var1, var2));
   }

   public final PendingResult delete(GoogleApiClient var1, Credential var2) {
      return var1.zze(new zzark(this, var1, var2));
   }

   public final PendingResult disableAutoSignIn(GoogleApiClient var1) {
      return var1.zze(new zzarl(this, var1));
   }
}
