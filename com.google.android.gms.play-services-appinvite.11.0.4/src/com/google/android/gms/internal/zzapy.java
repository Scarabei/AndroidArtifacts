package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.appinvite.AppInviteApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public final class zzapy implements AppInviteApi {
   public final PendingResult updateInvitationOnInstall(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzaqb(this, var1, var2));
   }

   public final PendingResult convertInvitation(GoogleApiClient var1, String var2) {
      return var1.zzd(new zzaqd(this, var1, var2));
   }

   public final PendingResult getInvitation(GoogleApiClient var1, Activity var2, boolean var3) {
      return var1.zzd(new zzaqf(this, var1, var2, var3));
   }
}
