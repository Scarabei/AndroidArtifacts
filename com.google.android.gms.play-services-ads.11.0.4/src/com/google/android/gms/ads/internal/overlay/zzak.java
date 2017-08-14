package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;
import java.util.concurrent.TimeUnit;

@zzzn
@TargetApi(14)
public final class zzak {
   private final long zzPI;
   private long zzPJ;
   private boolean zzPK;

   zzak() {
      zzme var1 = zzmo.zzCx;
      this.zzPI = TimeUnit.MILLISECONDS.toNanos(((Long)zzbs.zzbL().zzd(var1)).longValue());
      this.zzPK = true;
   }

   public final void zzfU() {
      this.zzPK = true;
   }

   public final void zza(SurfaceTexture var1, zzx var2) {
      if (var2 != null) {
         long var3 = var1.getTimestamp();
         if (this.zzPK || Math.abs(var3 - this.zzPJ) >= this.zzPI) {
            this.zzPK = false;
            this.zzPJ = var3;
            zzagz.zzZr.post(new zzal(this, var2));
         }

      }
   }
}
