package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;

final class zzbnx implements DriveResource.MetadataResult {
   private final Status mStatus;
   private final Metadata zzaOF;

   public zzbnx(Status var1, Metadata var2) {
      this.mStatus = var1;
      this.zzaOF = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Metadata getMetadata() {
      return this.zzaOF;
   }
}
