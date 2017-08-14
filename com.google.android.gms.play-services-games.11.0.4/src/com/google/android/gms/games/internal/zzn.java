package com.google.android.gms.games.internal;

import android.os.Binder;
import android.view.View;

public class zzn {
   protected GamesClientImpl zzbaK;
   protected zzp zzbaL;

   private zzn(GamesClientImpl var1, int var2) {
      this.zzbaK = var1;
      this.zzbb(var2);
   }

   protected void zzbb(int var1) {
      this.zzbaL = new zzp(var1, new Binder(), (zzo)null);
   }

   public void zzt(View var1) {
   }

   public void zzuV() {
      this.zzbaK.zza(this.zzbaL.zzbaM, this.zzbaL.zzuW());
   }

   // $FF: synthetic method
   zzn(GamesClientImpl var1, int var2, zzo var3) {
      this(var1, var2);
   }
}
