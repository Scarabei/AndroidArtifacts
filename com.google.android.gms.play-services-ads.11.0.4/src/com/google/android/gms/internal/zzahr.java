package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzahr implements OnClickListener {
   // $FF: synthetic field
   private int zzZF;
   // $FF: synthetic field
   private int zzZG;
   // $FF: synthetic field
   private int zzZH;
   // $FF: synthetic field
   private zzahq zzZI;

   zzahr(zzahq var1, int var2, int var3, int var4) {
      this.zzZI = var1;
      this.zzZF = var2;
      this.zzZG = var3;
      this.zzZH = var4;
      super();
   }

   public final void onClick(DialogInterface var1, int var2) {
      if (var2 == this.zzZF) {
         zzahq.zza(this.zzZI);
      } else {
         zzme var3;
         if (var2 == this.zzZG) {
            var3 = zzmo.zzGr;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
               zzahq.zzb(this.zzZI);
               return;
            }
         }

         if (var2 == this.zzZH) {
            var3 = zzmo.zzGs;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
               zzahq.zzc(this.zzZI);
            }
         }

      }
   }
}
