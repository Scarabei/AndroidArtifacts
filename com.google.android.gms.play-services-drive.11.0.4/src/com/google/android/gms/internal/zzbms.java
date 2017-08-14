package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;

public final class zzbms extends zzbnn implements DriveFile {
   public zzbms(DriveId var1) {
      super(var1);
   }

   public final PendingResult open(GoogleApiClient var1, int var2, DriveFile.DownloadProgressListener var3) {
      if (var2 != 268435456 && var2 != 536870912 && var2 != 805306368) {
         throw new IllegalArgumentException("Invalid mode provided.");
      } else {
         zzbmu var4 = var3 == null ? null : new zzbmu(var1.zzp(var3));
         return var1.zzd(new zzbmt(this, var1, var2, var4));
      }
   }
}
