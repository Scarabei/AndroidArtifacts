package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddPlaceRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final String mName;
   private final LatLng zzbji;
   private final String zzaTl;
   private final List zzbjj;
   private final String zzbjk;
   private final Uri zzbjl;
   public static final Creator CREATOR = new zzb();

   public AddPlaceRequest(String var1, LatLng var2, String var3, List var4, String var5, Uri var6) {
      this.mName = zzbo.zzcF(var1);
      this.zzbji = (LatLng)zzbo.zzu(var2);
      this.zzaTl = zzbo.zzcF(var3);
      this.zzbjj = new ArrayList((Collection)zzbo.zzu(var4));
      zzbo.zzb(!this.zzbjj.isEmpty(), "At least one place type should be provided.");
      zzbo.zzb(!TextUtils.isEmpty(var5) || var6 != null, "One of phone number or URI should be provided.");
      this.zzbjk = var5;
      this.zzbjl = var6;
   }

   public AddPlaceRequest(String var1, LatLng var2, String var3, List var4, String var5) {
      this(var1, var2, var3, var4, zzbo.zzcF(var5), (Uri)null);
   }

   public AddPlaceRequest(String var1, LatLng var2, String var3, List var4, Uri var5) {
      this(var1, var2, var3, var4, (String)null, (Uri)zzbo.zzu(var5));
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getLatLng(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getAddress(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getPlaceTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getPhoneNumber(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getWebsiteUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String getName() {
      return this.mName;
   }

   public LatLng getLatLng() {
      return this.zzbji;
   }

   public String getAddress() {
      return this.zzaTl;
   }

   public List getPlaceTypes() {
      return this.zzbjj;
   }

   @Nullable
   public String getPhoneNumber() {
      return this.zzbjk;
   }

   @Nullable
   public Uri getWebsiteUri() {
      return this.zzbjl;
   }

   public String toString() {
      return zzbe.zzt(this).zzg("name", this.mName).zzg("latLng", this.zzbji).zzg("address", this.zzaTl).zzg("placeTypes", this.zzbjj).zzg("phoneNumer", this.zzbjk).zzg("websiteUri", this.zzbjl).toString();
   }
}
