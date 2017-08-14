package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;

final class zzbnc implements DriveFolder.DriveFileResult {
   private final Status mStatus;
   private final DriveFile zzaOw;

   public zzbnc(Status var1, DriveFile var2) {
      this.mStatus = var1;
      this.zzaOw = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final DriveFile getDriveFile() {
      return this.zzaOw;
   }
}
