package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

final class zzavz implements OnClickListener {
   // $FF: synthetic field
   private zzavy zzavz;

   zzavz(zzavy var1) {
      this.zzavz = var1;
      super();
   }

   public final void onClick(View var1) {
      Activity var2;
      if ((var2 = (Activity)zzavy.zza(this.zzavz).get()) != null) {
         Intent var3;
         (var3 = new Intent()).setComponent(zzavy.zzb(this.zzavz));
         var2.startActivity(var3);
      }

   }
}
