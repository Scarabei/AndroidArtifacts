package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

final class zzbeu extends Handler {
   // $FF: synthetic field
   private zzbes zzaFi;

   public zzbeu(zzbes var1, Looper var2) {
      this.zzaFi = var1;
      super(var2);
   }

   public final void handleMessage(Message var1) {
      switch(var1.what) {
      case 0:
         PendingResult var2 = (PendingResult)var1.obj;
         synchronized(zzbes.zzf(this.zzaFi)) {
            if (var2 == null) {
               zzbes.zza(zzbes.zzg(this.zzaFi), new Status(13, "Transform returned null"));
            } else if (var2 instanceof zzbeh) {
               zzbes.zza(zzbes.zzg(this.zzaFi), ((zzbeh)var2).getStatus());
            } else {
               zzbes.zzg(this.zzaFi).zza(var2);
            }

            return;
         }
      case 1:
         RuntimeException var3 = (RuntimeException)var1.obj;
         String var10002 = String.valueOf(var3.getMessage());
         String var10001;
         if (var10002.length() != 0) {
            var10001 = "Runtime exception on the transformation worker thread: ".concat(var10002);
         } else {
            String var10003 = new String;
            var10001 = var10003;
            var10003.<init>("Runtime exception on the transformation worker thread: ");
         }

         Log.e("TransformedResultImpl", var10001);
         throw var3;
      default:
         int var4 = var1.what;
         Log.e("TransformedResultImpl", (new StringBuilder(70)).append("TransformationResultHandler received unknown message type: ").append(var4).toString());
      }
   }
}
