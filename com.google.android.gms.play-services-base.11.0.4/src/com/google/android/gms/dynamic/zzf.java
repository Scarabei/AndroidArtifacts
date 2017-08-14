package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

final class zzf implements OnClickListener {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private Intent zzaSA;

   zzf(Context var1, Intent var2) {
      this.zztF = var1;
      this.zzaSA = var2;
      super();
   }

   public final void onClick(View var1) {
      try {
         this.zztF.startActivity(this.zzaSA);
      } catch (ActivityNotFoundException var3) {
         Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", var3);
      }
   }
}
