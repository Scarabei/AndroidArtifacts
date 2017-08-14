package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;

final class zzblz implements DriveApi.DriveIdResult {
   private final Status mStatus;
   private final DriveId zzaLV;

   public zzblz(Status var1, DriveId var2) {
      this.mStatus = var1;
      this.zzaLV = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final DriveId getDriveId() {
      return this.zzaLV;
   }
}
