package com.google.android.gms.location.places.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

@TargetApi(12)
public class PlaceAutocompleteFragment extends Fragment {
   private View zzblm;
   private View zzbln;
   private EditText zzblo;
   private boolean zzblp;
   @Nullable
   private LatLngBounds zzblq;
   @Nullable
   private AutocompleteFilter zzblr;
   @Nullable
   private PlaceSelectionListener zzbls;

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      View var4 = var1.inflate(layout.place_autocomplete_fragment, var2, false);
      this.zzblm = var4.findViewById(id.place_autocomplete_search_button);
      this.zzbln = var4.findViewById(id.place_autocomplete_clear_button);
      this.zzblo = (EditText)var4.findViewById(id.place_autocomplete_search_input);
      zzc var5 = new zzc(this);
      this.zzblm.setOnClickListener(var5);
      this.zzblo.setOnClickListener(var5);
      this.zzbln.setOnClickListener(new zzd(this));
      this.zzwb();
      return var4;
   }

   public void onDestroyView() {
      this.zzblm = null;
      this.zzbln = null;
      this.zzblo = null;
      super.onDestroyView();
   }

   public void setBoundsBias(@Nullable LatLngBounds var1) {
      this.zzblq = var1;
   }

   public void setFilter(@Nullable AutocompleteFilter var1) {
      this.zzblr = var1;
   }

   public void setText(CharSequence var1) {
      this.zzblo.setText(var1);
      this.zzwb();
   }

   public void setHint(CharSequence var1) {
      this.zzblo.setHint(var1);
      this.zzblm.setContentDescription(var1);
   }

   public void setOnPlaceSelectedListener(PlaceSelectionListener var1) {
      this.zzbls = var1;
   }

   private final void zzwb() {
      boolean var1 = !this.zzblo.getText().toString().isEmpty();
      this.zzbln.setVisibility(var1 ? 0 : 8);
   }

   private final void zzwc() {
      int var1 = -1;

      try {
         Intent var2 = (new PlaceAutocomplete.IntentBuilder(2)).setBoundsBias(this.zzblq).setFilter(this.zzblr).zzdB(this.zzblo.getText().toString()).zzbn(1).build(this.getActivity());
         this.zzblp = true;
         this.startActivityForResult(var2, 30421);
      } catch (GooglePlayServicesRepairableException var3) {
         var1 = var3.getConnectionStatusCode();
         Log.e("Places", "Could not open autocomplete activity", var3);
      } catch (GooglePlayServicesNotAvailableException var4) {
         var1 = var4.errorCode;
         Log.e("Places", "Could not open autocomplete activity", var4);
      }

      if (var1 != -1) {
         GoogleApiAvailability.getInstance().showErrorDialogFragment(this.getActivity(), var1, 30422);
      }

   }

   public void onActivityResult(int var1, int var2, Intent var3) {
      this.zzblp = false;
      if (var1 == 30421) {
         if (var2 == -1) {
            Place var4 = PlaceAutocomplete.getPlace(this.getActivity(), var3);
            if (this.zzbls != null) {
               this.zzbls.onPlaceSelected(var4);
            }

            this.setText(var4.getName().toString());
         } else if (var2 == 2) {
            Status var5 = PlaceAutocomplete.getStatus(this.getActivity(), var3);
            if (this.zzbls != null) {
               this.zzbls.onError(var5);
            }
         }
      }

      super.onActivityResult(var1, var2, var3);
   }

   // $FF: synthetic method
   static boolean zza(PlaceAutocompleteFragment var0) {
      return var0.zzblp;
   }

   // $FF: synthetic method
   static void zzb(PlaceAutocompleteFragment var0) {
      var0.zzwc();
   }
}
