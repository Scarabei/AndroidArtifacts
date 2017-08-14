package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzvc;
import com.google.android.gms.internal.zzvf;
import java.util.Map;

final class zzaw implements zzrd {
   // $FF: synthetic field
   private zzvc zzuC;
   // $FF: synthetic field
   private zzab zzuD;
   // $FF: synthetic field
   private zzvf zzuE;

   zzaw(zzvc var1, zzab var2, zzvf var3) {
      this.zzuC = var1;
      this.zzuD = var2;
      this.zzuE = var3;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      View var3;
      if ((var3 = var1.getView()) != null) {
         try {
            if (this.zzuC != null) {
               if (!this.zzuC.getOverrideClickHandling()) {
                  this.zzuC.zzl(com.google.android.gms.dynamic.zzn.zzw(var3));
                  this.zzuD.zztt.onAdClicked();
               } else {
                  zzar.zzc(var1);
               }
            } else {
               if (this.zzuE != null) {
                  if (!this.zzuE.getOverrideClickHandling()) {
                     this.zzuE.zzl(com.google.android.gms.dynamic.zzn.zzw(var3));
                     this.zzuD.zztt.onAdClicked();
                     return;
                  }

                  zzar.zzc(var1);
               }

            }
         } catch (RemoteException var5) {
            zzafr.zzc("Unable to call handleClick on mapper", var5);
         }
      }
   }
}
