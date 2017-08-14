package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.zzbdw;

public final class zzdr implements Videos {
   public final PendingResult getCaptureCapabilities(GoogleApiClient var1) {
      return var1.zzd(new zzds(this, var1));
   }

   public final Intent getCaptureOverlayIntent(GoogleApiClient var1) {
      return Games.zzf(var1).zzuM();
   }

   public final PendingResult getCaptureState(GoogleApiClient var1) {
      return var1.zzd(new zzdt(this, var1));
   }

   public final PendingResult isCaptureAvailable(GoogleApiClient var1, int var2) {
      return var1.zzd(new zzdu(this, var1, var2));
   }

   public final boolean isCaptureSupported(GoogleApiClient var1) {
      return Games.zzf(var1).zzuN();
   }

   public final void registerCaptureOverlayStateChangedListener(GoogleApiClient var1, Videos.CaptureOverlayStateListener var2) {
      GamesClientImpl var3;
      if ((var3 = Games.zza(var1, false)) != null) {
         zzbdw var4 = var1.zzp(var2);
         var3.zze(var4);
      }

   }

   public final void unregisterCaptureOverlayStateChangedListener(GoogleApiClient var1) {
      GamesClientImpl var2;
      if ((var2 = Games.zza(var1, false)) != null) {
         var2.zzuO();
      }

   }
}
