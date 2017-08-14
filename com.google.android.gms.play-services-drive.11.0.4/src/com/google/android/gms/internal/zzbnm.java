package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.FileUploadPreferences;

abstract class zzbnm extends zzbmf {
   // $FF: synthetic field
   private zzbnh zzaOz;

   public zzbnm(zzbnh var1, GoogleApiClient var2) {
      this.zzaOz = var1;
      super(var2);
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return new zzbnl(this.zzaOz, var1, (FileUploadPreferences)null, (zzbni)null);
   }
}
