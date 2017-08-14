package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbaz;

final class zzfi extends zzfc {
   private final zzbd zzbTf;

   public zzfi(zzbaz var1, zzbd var2) {
      super(var1);
      this.zzbTf = (zzbd)com.google.android.gms.common.internal.zzbo.zzu(var2);
   }

   public final void zza(zzck var1) {
      zzav var2 = null;
      if (var1.zzbSI != null) {
         var2 = new zzav(new AutoCloseInputStream(var1.zzbSI));
         this.zzbTf.zza(new zzaw(var2));
      }

      this.zzR(new zzas(new Status(var1.statusCode), var2));
   }
}
