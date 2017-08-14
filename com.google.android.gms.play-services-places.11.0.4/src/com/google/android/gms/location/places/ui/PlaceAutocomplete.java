package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlaceAutocomplete extends zza {
   public static final int RESULT_ERROR = 2;
   public static final int MODE_FULLSCREEN = 1;
   public static final int MODE_OVERLAY = 2;

   public static Place getPlace(Context var0, Intent var1) {
      return zza.getPlace(var0, var1);
   }

   public static Status getStatus(Context var0, Intent var1) {
      return zza.getStatus(var0, var1);
   }

   public static class IntentBuilder extends zzb {
      public IntentBuilder(int var1) {
         super("com.google.android.gms.location.places.ui.AUTOCOMPLETE");
         this.mIntent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
         this.mIntent.putExtra("mode", var1);
         this.mIntent.putExtra("origin", 2);
      }

      public PlaceAutocomplete.IntentBuilder setBoundsBias(@Nullable LatLngBounds var1) {
         if (var1 != null) {
            this.mIntent.putExtra("bounds", var1);
         } else {
            this.mIntent.removeExtra("bounds");
         }

         return this;
      }

      public PlaceAutocomplete.IntentBuilder setFilter(@Nullable AutocompleteFilter var1) {
         if (var1 != null) {
            this.mIntent.putExtra("filter", var1);
         } else {
            this.mIntent.removeExtra("filter");
         }

         return this;
      }

      public final PlaceAutocomplete.IntentBuilder zzbn(int var1) {
         this.mIntent.putExtra("origin", 1);
         return this;
      }

      public final PlaceAutocomplete.IntentBuilder zzdB(@Nullable String var1) {
         if (var1 != null) {
            this.mIntent.putExtra("initial_query", var1);
         } else {
            this.mIntent.removeExtra("initial_query");
         }

         return this;
      }

      public Intent build(Activity var1) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
         return super.build(var1);
      }
   }
}
