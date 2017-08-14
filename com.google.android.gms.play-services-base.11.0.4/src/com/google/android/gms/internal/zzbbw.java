package com.google.android.gms.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zza;

public class zzbbw extends zzbba {
   private final zza zzaCW = new zza();
   private zzbdb zzaAN;

   public static void zza(Activity var0, zzbdb var1, zzbat var2) {
      zzn(var0);
      zzbdt var3;
      zzbbw var6;
      if ((var6 = (zzbbw)(var3 = zzn(var0)).zza("ConnectionlessLifecycleHelper", zzbbw.class)) == null) {
         var6 = new zzbbw(var3);
      }

      var6.zzaAN = var1;
      zzbo.zzb(var2, "ApiKey cannot be null");
      var6.zzaCW.add(var2);
      var1.zza(var6);
   }

   private zzbbw(zzbdt var1) {
      super(var1);
      this.zzaEG.zza("ConnectionlessLifecycleHelper", this);
   }

   public final void onStart() {
      super.onStart();
      this.zzpS();
   }

   public final void onResume() {
      super.onResume();
      this.zzpS();
   }

   public final void onStop() {
      super.onStop();
      this.zzaAN.zzb(this);
   }

   protected final void zza(ConnectionResult var1, int var2) {
      this.zzaAN.zza(var1, var2);
   }

   protected final void zzps() {
      this.zzaAN.zzps();
   }

   final zza zzpR() {
      return this.zzaCW;
   }

   private final void zzpS() {
      if (!this.zzaCW.isEmpty()) {
         this.zzaAN.zza(this);
      }

   }
}
