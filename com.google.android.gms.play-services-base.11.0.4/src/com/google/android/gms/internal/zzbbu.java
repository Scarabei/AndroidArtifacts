package com.google.android.gms.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

final class zzbbu implements PendingResult.zza {
   // $FF: synthetic field
   private zzbbe zzaCT;
   // $FF: synthetic field
   private zzbbt zzaCU;

   zzbbu(zzbbt var1, zzbbe var2) {
      this.zzaCU = var1;
      this.zzaCT = var2;
      super();
   }

   public final void zzo(Status var1) {
      zzbbt.zza(this.zzaCU).remove(this.zzaCT);
   }
}
