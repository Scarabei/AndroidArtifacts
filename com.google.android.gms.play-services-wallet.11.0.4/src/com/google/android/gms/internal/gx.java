package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.Status;

final class gx extends gw {
   private final zzbaz zzaIz;

   public gx(zzbaz var1) {
      super((gv)null);
      this.zzaIz = var1;
   }

   public final void zza(Status var1, boolean var2, Bundle var3) {
      this.zzaIz.setResult(new BooleanResult(var1, var2));
   }
}
