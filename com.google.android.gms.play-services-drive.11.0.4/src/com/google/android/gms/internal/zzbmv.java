package com.google.android.gms.internal;

import com.google.android.gms.drive.DriveFile;

final class zzbmv implements zzbdz {
   // $FF: synthetic field
   private long zzaOp;
   // $FF: synthetic field
   private long zzaOq;

   zzbmv(zzbmu var1, long var2, long var4) {
      this.zzaOp = var2;
      this.zzaOq = var4;
      super();
   }

   public final void zzpT() {
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      DriveFile.DownloadProgressListener var3 = (DriveFile.DownloadProgressListener)var1;
      var3.onProgress(this.zzaOp, this.zzaOq);
   }
}
