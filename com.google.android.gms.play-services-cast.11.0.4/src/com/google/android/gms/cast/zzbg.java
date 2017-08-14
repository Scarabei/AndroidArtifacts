package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzayt;
import org.json.JSONObject;

final class zzbg implements zzayt {
   // $FF: synthetic field
   private RemoteMediaPlayer.zzb zzarx;

   zzbg(RemoteMediaPlayer.zzb var1) {
      this.zzarx = var1;
      super();
   }

   public final void zzx(long var1) {
      this.zzarx.setResult((RemoteMediaPlayer.MediaChannelResult)this.zzarx.zzb(new Status(2103)));
   }

   public final void zza(long var1, int var3, Object var4) {
      JSONObject var5 = null;
      if (var4 instanceof JSONObject) {
         var5 = (JSONObject)var4;
      }

      this.zzarx.setResult(new RemoteMediaPlayer.zzc(new Status(var3), var5));
   }
}
