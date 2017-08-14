package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

final class zzbnl implements DrivePreferencesApi.FileUploadPreferencesResult {
   private final Status mStatus;
   private final FileUploadPreferences zzaOB;

   private zzbnl(zzbnh var1, Status var2, FileUploadPreferences var3) {
      this.mStatus = var2;
      this.zzaOB = var3;
   }

   public final FileUploadPreferences getFileUploadPreferences() {
      return this.zzaOB;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   // $FF: synthetic method
   zzbnl(zzbnh var1, Status var2, FileUploadPreferences var3, zzbni var4) {
      this(var1, var2, var3);
   }
}
