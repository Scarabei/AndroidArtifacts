package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.WeatherResult;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.api.Status;

final class zzatv implements WeatherResult {
   // $FF: synthetic field
   private zzaud zzaok;

   zzatv(zzatu var1, zzaud var2) {
      this.zzaok = var2;
      super();
   }

   public final Weather getWeather() {
      return this.zzaok.zznb() == null ? null : this.zzaok.zznb().zzmZ();
   }

   public final Status getStatus() {
      return this.zzaok.getStatus();
   }
}
