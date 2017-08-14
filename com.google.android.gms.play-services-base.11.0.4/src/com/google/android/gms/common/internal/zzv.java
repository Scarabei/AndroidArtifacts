package com.google.android.gms.common.internal;

import android.content.Intent;
import android.support.v4.app.Fragment;

final class zzv extends zzt {
   // $FF: synthetic field
   private Intent val$intent;
   // $FF: synthetic field
   private Fragment val$fragment;
   // $FF: synthetic field
   private int val$requestCode;

   zzv(Intent var1, Fragment var2, int var3) {
      this.val$intent = var1;
      this.val$fragment = var2;
      this.val$requestCode = var3;
      super();
   }

   public final void zzrv() {
      if (this.val$intent != null) {
         this.val$fragment.startActivityForResult(this.val$intent, this.val$requestCode);
      }

   }
}
