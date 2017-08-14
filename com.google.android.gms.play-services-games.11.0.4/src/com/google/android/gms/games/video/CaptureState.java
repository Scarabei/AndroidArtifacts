package com.google.android.gms.games.video;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;

public final class CaptureState {
   private final boolean zzbfa;
   private final int zzbfb;
   private final int zzbfc;
   private final boolean zzasE;
   private final boolean zzuV;

   private CaptureState(boolean var1, int var2, int var3, boolean var4, boolean var5) {
      zzbo.zzaf(VideoConfiguration.isValidCaptureMode(var2, true));
      zzbo.zzaf(VideoConfiguration.isValidQualityLevel(var3, true));
      this.zzbfa = var1;
      this.zzbfb = var2;
      this.zzbfc = var3;
      this.zzasE = var4;
      this.zzuV = var5;
   }

   public final boolean isCapturing() {
      return this.zzbfa;
   }

   public final int getCaptureMode() {
      return this.zzbfb;
   }

   public final int getCaptureQuality() {
      return this.zzbfc;
   }

   public final boolean isOverlayVisible() {
      return this.zzasE;
   }

   public final boolean isPaused() {
      return this.zzuV;
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("IsCapturing", this.zzbfa).zzg("CaptureMode", this.zzbfb).zzg("CaptureQuality", this.zzbfc).zzg("IsOverlayVisible", this.zzasE).zzg("IsPaused", this.zzuV).toString();
   }

   public static CaptureState zzs(Bundle var0) {
      return var0 != null && var0.get("IsCapturing") != null ? new CaptureState(var0.getBoolean("IsCapturing", false), var0.getInt("CaptureMode", -1), var0.getInt("CaptureQuality", -1), var0.getBoolean("IsOverlayVisible", false), var0.getBoolean("IsPaused", false)) : null;
   }
}
