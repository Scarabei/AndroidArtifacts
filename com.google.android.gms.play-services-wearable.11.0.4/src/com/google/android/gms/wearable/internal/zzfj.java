package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbaz;

final class zzfj extends zzfc {
   private final zzbd zzbTf;

   public zzfj(zzbaz var1, zzbd var2) {
      super(var1);
      this.zzbTf = (zzbd)com.google.android.gms.common.internal.zzbo.zzu(var2);
   }

   public final void zza(zzcm var1) {
      zzax var2 = null;
      if (var1.zzbSI != null) {
         var2 = new zzax(new AutoCloseOutputStream(var1.zzbSI));
         this.zzbTf.zza(new zzay(var2));
      }

      this.zzR(new zzat(new Status(var1.statusCode), var2));
   }
}
