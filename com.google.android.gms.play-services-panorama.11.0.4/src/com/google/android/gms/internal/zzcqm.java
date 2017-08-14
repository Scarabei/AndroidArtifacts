package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.panorama.PanoramaApi;

final class zzcqm implements PanoramaApi.PanoramaResult {
   private final Status mStatus;
   private final Intent zzbzT;

   public zzcqm(Status var1, Intent var2) {
      this.mStatus = (Status)zzbo.zzu(var1);
      this.zzbzT = var2;
   }

   public final Status getStatus() {
      return this.mStatus;
   }

   public final Intent getViewerIntent() {
      return this.zzbzT;
   }
}
