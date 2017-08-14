package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import java.util.Locale;

final class zzaxi implements zzayt {
   // $FF: synthetic field
   private zzawy zzaxj;
   // $FF: synthetic field
   private zzaxh zzaxm;

   zzaxi(zzaxh var1, zzawy var2) {
      this.zzaxm = var1;
      this.zzaxj = var2;
      super();
   }

   public final void zzx(long var1) {
      this.zzaxm.setResult(zzaxh.zzk(new Status(2103)));
   }

   public final void zza(long var1, int var3, Object var4) {
      try {
         if (var4 == null) {
            this.zzaxm.setResult(new zzaxj(new Status(var3, (String)null, (PendingIntent)null), zzaxh.zza(this.zzaxm)));
         } else {
            zzaxm var5;
            zzaxl var6;
            if ((var6 = (var5 = (zzaxm)var4).zzawU) != null && !zzaye.zza("1.0.0", var6.getVersion())) {
               zzawy.zza(this.zzaxm.zzaxd, (zzaxl)null);
               this.zzaxm.setResult(zzaxh.zzk(new Status(2150, String.format(Locale.ROOT, "Incorrect Game Manager SDK version. Receiver: %s Sender: %s", var6.getVersion(), "1.0.0"))));
            } else {
               this.zzaxm.setResult(new zzaxj(new Status(var3, var5.zzaxv, (PendingIntent)null), zzaxh.zza(this.zzaxm)));
            }
         }
      } catch (ClassCastException var7) {
         this.zzaxm.setResult(zzaxh.zzk(new Status(13)));
      }
   }
}
