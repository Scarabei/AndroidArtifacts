package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Map;

@zzzn
final class zzrx implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      zzme var9 = zzmo.zzEC;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var9)).booleanValue()) {
         zzaks var3 = var1.zziH();

         try {
            if (var3 == null) {
               float var4 = Float.parseFloat((String)var2.get("duration"));
               boolean var5 = "1".equals(var2.get("customControlsAllowed"));
               var3 = new zzaks(var1, var4, var5);
               var1.zza(var3);
            }

            boolean var12 = "1".equals(var2.get("muted"));
            float var13 = Float.parseFloat((String)var2.get("currentTime"));
            int var10;
            int var6 = (var10 = Integer.parseInt((String)var2.get("playbackState"))) >= 0 && 3 >= var10 ? var10 : 0;
            String var7;
            float var8 = TextUtils.isEmpty(var7 = (String)var2.get("aspectRatio")) ? 0.0F : Float.parseFloat(var7);
            if (zzafr.zzz(3)) {
               zzafr.zzaC((new StringBuilder(79 + String.valueOf(var7).length())).append("Video Meta GMSG: isMuted : ").append(var12).append(" , playbackState : ").append(var6).append(" , aspectRatio : ").append(var7).toString());
            }

            var3.zza(var13, var6, var12, var8);
         } catch (NumberFormatException | NullPointerException var11) {
            zzafr.zzb("Unable to parse videoMeta message.", var11);
            com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var11, (String)"VideoMetaGmsgHandler.onGmsg");
         }
      }
   }
}
