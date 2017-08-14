package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationSettingsRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   private final List zzaWY;
   private final boolean zzbii;
   private final boolean zzbij;
   private zzt zzbik;
   public static final Creator CREATOR = new zzv();

   LocationSettingsRequest(List var1, boolean var2, boolean var3, zzt var4) {
      this.zzaWY = var1;
      this.zzbii = var2;
      this.zzbij = var3;
      this.zzbik = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, Collections.unmodifiableList(this.zzaWY), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbii);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbij);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbik, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static final class Builder {
      private final ArrayList zzbil = new ArrayList();
      private boolean zzbii = false;
      private boolean zzbij = false;
      private zzt zzbik = null;

      public final LocationSettingsRequest.Builder addLocationRequest(@NonNull LocationRequest var1) {
         if (var1 != null) {
            this.zzbil.add(var1);
         }

         return this;
      }

      public final LocationSettingsRequest.Builder addAllLocationRequests(Collection var1) {
         Iterator var2 = var1.iterator();

         while(var2.hasNext()) {
            LocationRequest var3;
            if ((var3 = (LocationRequest)var2.next()) != null) {
               this.zzbil.add(var3);
            }
         }

         return this;
      }

      public final LocationSettingsRequest.Builder setAlwaysShow(boolean var1) {
         this.zzbii = var1;
         return this;
      }

      public final LocationSettingsRequest.Builder setNeedBle(boolean var1) {
         this.zzbij = var1;
         return this;
      }

      public final LocationSettingsRequest build() {
         return new LocationSettingsRequest(this.zzbil, this.zzbii, this.zzbij, (zzt)null);
      }
   }
}
