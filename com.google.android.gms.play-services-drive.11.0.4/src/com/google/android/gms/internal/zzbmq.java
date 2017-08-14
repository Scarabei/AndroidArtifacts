package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbmq implements ResultCallback {
   zzbmq(zzbmn var1) {
   }

   // $FF: synthetic method
   public final void onResult(Result var1) {
      if (!((Status)var1).isSuccess()) {
         zzbng.zzz("DriveContentsImpl", "Error discarding contents");
      } else {
         zzbng.zzx("DriveContentsImpl", "Contents discarded");
      }
   }
}
