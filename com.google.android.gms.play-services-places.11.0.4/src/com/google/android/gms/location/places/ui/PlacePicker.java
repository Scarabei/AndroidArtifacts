package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlacePicker extends zza {
   public static final int RESULT_ERROR = 2;

   /** @deprecated */
   @Deprecated
   public static Place getPlace(Intent var0, Context var1) {
      return zza.getPlace(var1, var0);
   }

   public static Place getPlace(Context var0, Intent var1) {
      return zza.getPlace(var0, var1);
   }

   /** @deprecated */
   @Deprecated
   public static String getAttributions(Intent var0) {
      return var0.getStringExtra("third_party_attributions");
   }

   public static LatLngBounds getLatLngBounds(Intent var0) {
      return (LatLngBounds)com.google.android.gms.common.internal.safeparcel.zze.zza(var0, "final_latlng_bounds", LatLngBounds.CREATOR);
   }

   public static class IntentBuilder extends zzb {
      public IntentBuilder() {
         super("com.google.android.gms.location.places.ui.PICK_PLACE");
         this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
      }

      public PlacePicker.IntentBuilder setLatLngBounds(LatLngBounds var1) {
         zzbo.zzu(var1);
         com.google.android.gms.common.internal.safeparcel.zze.zza(var1, this.mIntent, "latlng_bounds");
         return this;
      }

      public Intent build(Activity var1) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
         return super.build(var1);
      }
   }
}
