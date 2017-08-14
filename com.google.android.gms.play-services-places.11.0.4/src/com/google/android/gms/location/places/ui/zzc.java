package com.google.android.gms.location.places.ui;

import android.view.View;
import android.view.View.OnClickListener;

final class zzc implements OnClickListener {
   // $FF: synthetic field
   private PlaceAutocompleteFragment zzblt;

   zzc(PlaceAutocompleteFragment var1) {
      this.zzblt = var1;
      super();
   }

   public final void onClick(View var1) {
      if (!PlaceAutocompleteFragment.zza(this.zzblt)) {
         PlaceAutocompleteFragment.zzb(this.zzblt);
      }

   }
}
