package com.google.android.gms.internal;

import android.app.Activity;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzbeb extends zzbba {
   private TaskCompletionSource zzalE = new TaskCompletionSource();

   public static zzbeb zzp(Activity var0) {
      zzbdt var1;
      zzbeb var2;
      if ((var2 = (zzbeb)(var1 = zzn(var0)).zza("GmsAvailabilityHelper", zzbeb.class)) != null) {
         if (var2.zzalE.getTask().isComplete()) {
            var2.zzalE = new TaskCompletionSource();
         }

         return var2;
      } else {
         return new zzbeb(var1);
      }
   }

   private zzbeb(zzbdt var1) {
      super(var1);
      this.zzaEG.zza("GmsAvailabilityHelper", this);
   }

   protected final void zza(ConnectionResult var1, int var2) {
      this.zzalE.setException(zzb.zzx(new Status(var1.getErrorCode(), var1.getErrorMessage(), var1.getResolution())));
   }

   protected final void zzps() {
      int var1;
      if ((var1 = this.zzaBd.isGooglePlayServicesAvailable(this.zzaEG.zzqF())) == 0) {
         this.zzalE.setResult((Object)null);
      } else {
         if (!this.zzalE.getTask().isComplete()) {
            this.zzb(new ConnectionResult(var1, (PendingIntent)null), 0);
         }

      }
   }

   public final void onDestroy() {
      super.onDestroy();
      this.zzalE.setException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
   }

   public final Task getTask() {
      return this.zzalE.getTask();
   }
}
