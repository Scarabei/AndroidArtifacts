package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;

public interface GoogleSignInApi {
   String EXTRA_SIGN_IN_ACCOUNT = "signInAccount";

   Intent getSignInIntent(GoogleApiClient var1);

   OptionalPendingResult silentSignIn(GoogleApiClient var1);

   PendingResult signOut(GoogleApiClient var1);

   PendingResult revokeAccess(GoogleApiClient var1);

   GoogleSignInResult getSignInResultFromIntent(Intent var1);
}
