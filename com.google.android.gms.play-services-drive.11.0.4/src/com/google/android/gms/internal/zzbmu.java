package com.google.android.gms.internal;

import com.google.android.gms.drive.DriveFile;

final class zzbmu implements DriveFile.DownloadProgressListener {
   private final zzbdw zzaOo;

   public zzbmu(zzbdw var1) {
      this.zzaOo = var1;
   }

   public final void onProgress(long var1, long var3) {
      this.zzaOo.zza(new zzbmv(this, var1, var3));
   }
}
