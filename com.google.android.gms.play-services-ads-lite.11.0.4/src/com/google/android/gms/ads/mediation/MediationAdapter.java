package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public interface MediationAdapter {
   void onDestroy();

   void onPause();

   void onResume();

   public static class zza {
      private int zzacL;

      public final MediationAdapter.zza zzB(int var1) {
         this.zzacL = 1;
         return this;
      }

      public final Bundle zzjh() {
         Bundle var1;
         (var1 = new Bundle()).putInt("capabilities", this.zzacL);
         return var1;
      }
   }
}
