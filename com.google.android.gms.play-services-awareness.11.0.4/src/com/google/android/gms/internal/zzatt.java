package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.PlacesResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

final class zzatt implements PlacesResult {
   private boolean zzaol;
   private List zzaom;
   // $FF: synthetic field
   private zzaud zzaok;

   zzatt(zzats var1, zzaud var2) {
      this.zzaok = var2;
      super();
      this.zzaol = false;
      this.zzaom = null;
   }

   public final List getPlaceLikelihoods() {
      if (this.zzaol) {
         return this.zzaom;
      } else {
         this.zzaol = true;
         if (this.zzaok.zznb() == null) {
            return null;
         } else {
            DataHolder var1;
            if ((var1 = this.zzaok.zznb().zzmY()) == null) {
               return null;
            } else {
               zzatc var2 = new zzatc(var1);

               List var4;
               try {
                  if (var2.getCount() <= 0) {
                     return null;
                  }

                  zzatb var3 = (zzatb)((SafeParcelable)var2.get(0));
                  this.zzaom = var3.getPlaceLikelihoods();
                  var4 = this.zzaom;
               } finally {
                  var2.release();
               }

               return var4;
            }
         }
      }
   }

   public final Status getStatus() {
      return this.zzaok.getStatus();
   }
}
