package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class zzas extends zzav implements Place {
   private final String zzbjI = this.zzD("place_id", "");
   private final zzae zzbkD;

   public zzas(DataHolder var1, int var2) {
      super(var1, var2);
      this.zzbkD = this.getPlaceTypes().size() > 0 || this.getPhoneNumber() != null && this.getPhoneNumber().length() > 0 || this.getWebsiteUri() != null && !this.getWebsiteUri().equals(Uri.EMPTY) || this.getRating() >= 0.0F || this.getPriceLevel() >= 0 ? new zzae(this.getPlaceTypes(), this.getPhoneNumber() != null ? this.getPhoneNumber().toString() : null, this.getWebsiteUri(), this.getRating(), this.getPriceLevel()) : null;
   }

   public final CharSequence getAddress() {
      return this.zzD("place_address", "");
   }

   public final String getId() {
      return this.zzbjI;
   }

   public final LatLng getLatLng() {
      return (LatLng)this.zza("place_lat_lng", LatLng.CREATOR);
   }

   public final Locale getLocale() {
      String var1;
      String var2;
      if (!TextUtils.isEmpty(var1 = this.zzD("place_locale_language", ""))) {
         var2 = this.zzD("place_locale_country", "");
         return new Locale(var1, var2);
      } else {
         return !TextUtils.isEmpty(var2 = this.zzD("place_locale", "")) ? new Locale(var2) : Locale.getDefault();
      }
   }

   public final CharSequence getName() {
      return this.zzD("place_name", "");
   }

   public final CharSequence getPhoneNumber() {
      return this.zzD("place_phone_number", "");
   }

   public final List getPlaceTypes() {
      return this.zza("place_types", Collections.emptyList());
   }

   public final int getPriceLevel() {
      return this.zzu("place_price_level", -1);
   }

   public final float getRating() {
      return this.zza("place_rating", -1.0F);
   }

   public final LatLngBounds getViewport() {
      return (LatLngBounds)this.zza("place_viewport", LatLngBounds.CREATOR);
   }

   public final Uri getWebsiteUri() {
      String var1;
      return (var1 = this.zzD("place_website_uri", (String)null)) == null ? null : Uri.parse(var1);
   }

   private final List zzwa() {
      return this.zzb("place_attributions", Collections.emptyList());
   }

   public final CharSequence getAttributions() {
      return zzg.zzi(this.zzwa());
   }

   // $FF: synthetic method
   public final Object freeze() {
      PlaceEntity.zza var10000 = (new PlaceEntity.zza()).zzdz(this.getAddress().toString()).zzE(this.zzwa()).zzdx(this.getId());
      String var4 = "place_is_permanently_closed";
      PlaceEntity var2;
      (var2 = var10000.zzaj(this.zzcv(var4) && !this.zzcx(var4) ? this.getBoolean(var4) : false).zza(this.getLatLng()).zzc(this.zza("place_level_number", 0.0F)).zzdy(this.getName().toString()).zzdA(this.getPhoneNumber().toString()).zzbm(this.getPriceLevel()).zzd(this.getRating()).zzD(this.getPlaceTypes()).zza(this.getViewport()).zzp(this.getWebsiteUri()).zza((zzal)this.zza("place_opening_hours", zzal.CREATOR)).zza(this.zzbkD).zzvZ()).setLocale(this.getLocale());
      return var2;
   }
}
