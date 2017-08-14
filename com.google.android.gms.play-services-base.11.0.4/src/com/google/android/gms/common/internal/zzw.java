package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.internal.zzbdt;

final class zzw extends zzt {
   // $FF: synthetic field
   private Intent val$intent;
   // $FF: synthetic field
   private zzbdt zzaHp;
   // $FF: synthetic field
   private int val$requestCode;

   zzw(Intent var1, zzbdt var2, int var3) {
      this.val$intent = var1;
      this.zzaHp = var2;
      this.val$requestCode = var3;
      super();
   }

   public final void zzrv() {
      if (this.val$intent != null) {
         this.zzaHp.startActivityForResult(this.val$intent, this.val$requestCode);
      }

   }
}
