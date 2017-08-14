package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Channel;
import java.io.IOException;
import java.io.InputStream;

final class zzas implements Channel.GetInputStreamResult {
   private final Status mStatus;
   private final InputStream zzbSo;

   zzas(Status var1, InputStream var2) {
      this.mStatus = (Status)com.google.android.gms.common.internal.zzbo.zzu(var1);
      this.zzbSo = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final InputStream getInputStream() {
      return this.zzbSo;
   }

   public final void release() {
      if (this.zzbSo != null) {
         try {
            this.zzbSo.close();
            return;
         } catch (IOException var1) {
            ;
         }
      }

   }
}
