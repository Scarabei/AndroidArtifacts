package com.google.firebase.appinvite;

import android.os.Bundle;
import com.google.android.gms.internal.io;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

public abstract class FirebaseAppInvite {
   public static FirebaseAppInvite getInvitation(PendingDynamicLinkData var0) {
      Bundle var1;
      return (var1 = var0.zzJJ()) != null && var1.getString("com.google.firebase.appinvite.fdl.extension.InvitationId", (String)null) != null ? new io(var1) : null;
   }

   public abstract String getInvitationId();
}
