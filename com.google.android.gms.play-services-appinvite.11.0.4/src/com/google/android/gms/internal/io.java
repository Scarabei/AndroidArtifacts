package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.firebase.appinvite.FirebaseAppInvite;

public final class io extends FirebaseAppInvite {
   private final Bundle zzbVX;

   public io(Bundle var1) {
      this.zzbVX = var1;
   }

   public final String getInvitationId() {
      return this.zzbVX.getString("com.google.firebase.appinvite.fdl.extension.InvitationId", (String)null);
   }
}
