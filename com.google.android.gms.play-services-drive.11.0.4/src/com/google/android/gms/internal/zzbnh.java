package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

public final class zzbnh implements DrivePreferencesApi {
   public final PendingResult getFileUploadPreferences(GoogleApiClient var1) {
      return var1.zzd(new zzbni(this, var1));
   }

   public final PendingResult setFileUploadPreferences(GoogleApiClient var1, FileUploadPreferences var2) {
      if (!(var2 instanceof zzbog)) {
         throw new IllegalArgumentException("Invalid preference value");
      } else {
         zzbog var3 = (zzbog)var2;
         return var1.zze(new zzbnj(this, var1, var3));
      }
   }
}
