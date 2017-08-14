package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Channel;
import java.io.IOException;
import java.io.OutputStream;

final class zzat implements Channel.GetOutputStreamResult {
   private final Status mStatus;
   private final OutputStream zzbSp;

   zzat(Status var1, OutputStream var2) {
      this.mStatus = (Status)com.google.android.gms.common.internal.zzbo.zzu(var1);
      this.zzbSp = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final OutputStream getOutputStream() {
      return this.zzbSp;
   }

   public final void release() {
      if (this.zzbSp != null) {
         try {
            this.zzbSp.close();
            return;
         } catch (IOException var1) {
            ;
         }
      }

   }
}
