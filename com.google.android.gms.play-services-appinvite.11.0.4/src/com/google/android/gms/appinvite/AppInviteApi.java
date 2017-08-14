package com.google.android.gms.appinvite;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

/** @deprecated */
@Deprecated
public interface AppInviteApi {
   /** @deprecated */
   @Deprecated
   PendingResult updateInvitationOnInstall(@NonNull GoogleApiClient var1, String var2);

   PendingResult convertInvitation(@NonNull GoogleApiClient var1, String var2);

   /** @deprecated */
   @Deprecated
   PendingResult getInvitation(@NonNull GoogleApiClient var1, Activity var2, boolean var3);
}
