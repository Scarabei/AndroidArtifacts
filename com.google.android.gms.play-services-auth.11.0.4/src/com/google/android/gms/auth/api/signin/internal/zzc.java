package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;

public final class zzc implements GoogleSignInApi {
   public final Intent getSignInIntent(GoogleApiClient var1) {
      return zze.zza(var1.getContext(), zza(var1));
   }

   public final OptionalPendingResult silentSignIn(GoogleApiClient var1) {
      return zze.zza(var1, var1.getContext(), zza(var1));
   }

   public final PendingResult signOut(GoogleApiClient var1) {
      return zze.zza(var1, var1.getContext());
   }

   public final PendingResult revokeAccess(GoogleApiClient var1) {
      return zze.zzb(var1, var1.getContext());
   }

   public final GoogleSignInResult getSignInResultFromIntent(Intent var1) {
      return zze.getSignInResultFromIntent(var1);
   }

   private static GoogleSignInOptions zza(GoogleApiClient var0) {
      return ((zzd)var0.zza(Auth.zzakG)).zzmI();
   }
}
