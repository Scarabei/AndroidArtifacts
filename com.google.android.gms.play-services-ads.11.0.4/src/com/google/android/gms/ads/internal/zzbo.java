package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.google.android.gms.internal.zzafr;

final class zzbo implements OnTouchListener {
   // $FF: synthetic field
   private zzbm zzvf;

   zzbo(zzbm var1) {
      this.zzvf = var1;
      super();
   }

   public final boolean onTouch(View var1, MotionEvent var2) {
      if (zzbm.zzb(this.zzvf) != null) {
         try {
            zzbm.zzb(this.zzvf).zza(var2);
         } catch (RemoteException var4) {
            zzafr.zzc("Unable to process ad data", var4);
         }
      }

      return false;
   }
}
