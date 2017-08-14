package com.google.android.gms.auth.api.credentials;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface CredentialsApi {
   int ACTIVITY_RESULT_ADD_ACCOUNT = 1000;
   int ACTIVITY_RESULT_OTHER_ACCOUNT = 1001;
   int CREDENTIAL_PICKER_REQUEST_CODE = 2000;

   PendingResult request(GoogleApiClient var1, CredentialRequest var2);

   PendingIntent getHintPickerIntent(GoogleApiClient var1, HintRequest var2);

   PendingResult save(GoogleApiClient var1, Credential var2);

   PendingResult delete(GoogleApiClient var1, Credential var2);

   PendingResult disableAutoSignIn(GoogleApiClient var1);
}
