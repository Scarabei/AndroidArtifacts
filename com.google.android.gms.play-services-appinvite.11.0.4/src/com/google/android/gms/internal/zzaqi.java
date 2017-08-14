package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.api.Status;

public final class zzaqi implements AppInviteInvitationResult {
   private final Status mStatus;
   private final Intent zzakf;

   public zzaqi(Status var1, Intent var2) {
      this.mStatus = var1;
      this.zzakf = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Intent getInvitationIntent() {
      return this.zzakf;
   }
}
