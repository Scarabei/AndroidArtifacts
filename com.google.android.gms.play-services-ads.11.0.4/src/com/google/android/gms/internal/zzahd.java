package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzahd extends BroadcastReceiver {
   // $FF: synthetic field
   private zzagz zzZy;

   private zzahd(zzagz var1) {
      this.zzZy = var1;
      super();
   }

   public final void onReceive(Context var1, Intent var2) {
      if ("android.intent.action.USER_PRESENT".equals(var2.getAction())) {
         zzagz.zza(this.zzZy, true);
      } else {
         if ("android.intent.action.SCREEN_OFF".equals(var2.getAction())) {
            zzagz.zza(this.zzZy, false);
         }

      }
   }

   // $FF: synthetic method
   zzahd(zzagz var1, zzaha var2) {
      this(var1);
   }
}
