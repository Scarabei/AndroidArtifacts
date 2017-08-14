package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.Status;

final class zzaqg extends zzapz {
   // $FF: synthetic field
   private zzaqf zzakd;

   zzaqg(zzaqf var1) {
      this.zzakd = var1;
      super();
   }

   public final void zza(Status var1, Intent var2) {
      this.zzakd.setResult(new zzaqi(var1, var2));
      Activity var3;
      if (AppInviteReferral.hasReferral(var2) && zzaqf.zza(this.zzakd) && zzaqf.zzb(this.zzakd) != null && (var3 = (Activity)zzaqf.zzb(this.zzakd).get()) != null) {
         var3.startActivity(var2);
      }

   }
}
