package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzayt;
import org.json.JSONObject;

final class zzag implements zzayt {
   // $FF: synthetic field
   private RemoteMediaClient.zzb zzauA;

   zzag(RemoteMediaClient.zzb var1) {
      this.zzauA = var1;
      super();
   }

   public final void zzx(long var1) {
      this.zzauA.setResult((RemoteMediaClient.MediaChannelResult)this.zzauA.zzb(new Status(2103)));
   }

   public final void zza(long var1, int var3, Object var4) {
      JSONObject var5 = null;
      if (var4 instanceof JSONObject) {
         var5 = (JSONObject)var4;
      }

      this.zzauA.setResult(new RemoteMediaClient.zzc(new Status(var3), var5));
   }
}
