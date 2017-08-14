package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;

final class zzblw implements Releasable, DriveApi.DriveContentsResult {
   private final Status mStatus;
   private final DriveContents zzaLY;

   public zzblw(Status var1, DriveContents var2) {
      this.mStatus = var1;
      this.zzaLY = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final DriveContents getDriveContents() {
      return this.zzaLY;
   }

   public final void release() {
      if (this.zzaLY != null) {
         this.zzaLY.zzsN();
      }

   }
}
