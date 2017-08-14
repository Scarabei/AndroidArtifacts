package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.MetadataBuffer;

final class zzbmb implements DriveApi.MetadataBufferResult {
   private final Status mStatus;
   private final MetadataBuffer zzaNO;
   private final boolean zzaNP;

   public zzbmb(Status var1, MetadataBuffer var2, boolean var3) {
      this.mStatus = var1;
      this.zzaNO = var2;
      this.zzaNP = var3;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final MetadataBuffer getMetadataBuffer() {
      return this.zzaNO;
   }

   public final void release() {
      if (this.zzaNO != null) {
         this.zzaNO.release();
      }

   }
}
