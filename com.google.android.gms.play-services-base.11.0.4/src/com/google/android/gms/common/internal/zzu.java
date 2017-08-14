package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

final class zzu extends zzt {
   // $FF: synthetic field
   private Intent val$intent;
   // $FF: synthetic field
   private Activity val$activity;
   // $FF: synthetic field
   private int val$requestCode;

   zzu(Intent var1, Activity var2, int var3) {
      this.val$intent = var1;
      this.val$activity = var2;
      this.val$requestCode = var3;
      super();
   }

   public final void zzrv() {
      if (this.val$intent != null) {
         this.val$activity.startActivityForResult(this.val$intent, this.val$requestCode);
      }

   }
}
