package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayi;
import com.google.android.gms.internal.zzbaz;

final class zzl extends zzayi {
   // $FF: synthetic field
   private String val$sessionId;

   zzl(Cast.CastApi.zza var1, GoogleApiClient var2, String var3) {
      this.val$sessionId = var3;
      super(var2);
   }

   public final void zza(zzaxx var1) throws RemoteException {
      if (TextUtils.isEmpty(this.val$sessionId)) {
         String var3 = "IllegalArgument: sessionId cannot be null or empty";
         this.setResult(this.zzb(new Status(2001, var3, (PendingIntent)null)));
      } else {
         try {
            var1.zza((String)this.val$sessionId, (zzbaz)this);
         } catch (IllegalStateException var4) {
            this.zzad(2001);
         }
      }
   }
}
