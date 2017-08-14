package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class LocationSettingsStates extends com.google.android.gms.common.internal.safeparcel.zza {
   private final boolean zzbin;
   private final boolean zzbio;
   private final boolean zzbip;
   private final boolean zzbiq;
   private final boolean zzbir;
   private final boolean zzbis;
   public static final Creator CREATOR = new zzx();

   public LocationSettingsStates(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      this.zzbin = var1;
      this.zzbio = var2;
      this.zzbip = var3;
      this.zzbiq = var4;
      this.zzbir = var5;
      this.zzbis = var6;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.isGpsUsable());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.isNetworkLocationUsable());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.isBleUsable());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.isGpsPresent());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.isNetworkLocationPresent());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.isBlePresent());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean isGpsUsable() {
      return this.zzbin;
   }

   public final boolean isGpsPresent() {
      return this.zzbiq;
   }

   public final boolean isNetworkLocationUsable() {
      return this.zzbio;
   }

   public final boolean isNetworkLocationPresent() {
      return this.zzbir;
   }

   public final boolean isLocationUsable() {
      return this.zzbin || this.zzbio;
   }

   public final boolean isLocationPresent() {
      return this.zzbiq || this.zzbir;
   }

   public final boolean isBleUsable() {
      return this.zzbip;
   }

   public final boolean isBlePresent() {
      return this.zzbis;
   }

   public static LocationSettingsStates fromIntent(Intent var0) {
      return (LocationSettingsStates)com.google.android.gms.common.internal.safeparcel.zze.zza(var0, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
   }
}
