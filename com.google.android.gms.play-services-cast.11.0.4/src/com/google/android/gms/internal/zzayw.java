package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public final class zzayw implements CastRemoteDisplayApi {
   private static final zzayo zzapq = new zzayo("CastRemoteDisplayApiImpl");
   private Api zzayW;
   private VirtualDisplay zzayX;
   private final zzazl zzayY = new zzayx(this);

   public zzayw(Api var1) {
      this.zzayW = var1;
   }

   public final PendingResult startRemoteDisplay(GoogleApiClient var1, String var2) {
      zzapq.zzb("startRemoteDisplay");
      return var1.zze(new zzayy(this, var1, var2));
   }

   public final PendingResult stopRemoteDisplay(GoogleApiClient var1) {
      zzapq.zzb("stopRemoteDisplay");
      return var1.zze(new zzayz(this, var1));
   }

   @TargetApi(19)
   private final void zzoP() {
      if (this.zzayX != null) {
         if (this.zzayX.getDisplay() != null) {
            zzayo var10000 = zzapq;
            int var1 = this.zzayX.getDisplay().getDisplayId();
            var10000.zzb((new StringBuilder(38)).append("releasing virtual display: ").append(var1).toString());
         }

         this.zzayX.release();
         this.zzayX = null;
      }

   }

   // $FF: synthetic method
   static zzayo zzoQ() {
      return zzapq;
   }

   // $FF: synthetic method
   static void zza(zzayw var0) {
      var0.zzoP();
   }

   // $FF: synthetic method
   static zzazl zzb(zzayw var0) {
      return var0.zzayY;
   }

   // $FF: synthetic method
   static Api zzc(zzayw var0) {
      return var0.zzayW;
   }

   // $FF: synthetic method
   static VirtualDisplay zza(zzayw var0, VirtualDisplay var1) {
      return var0.zzayX = var1;
   }

   // $FF: synthetic method
   static VirtualDisplay zzd(zzayw var0) {
      return var0.zzayX;
   }
}
