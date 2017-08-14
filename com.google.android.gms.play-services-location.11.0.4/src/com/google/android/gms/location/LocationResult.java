package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   static final List zzbic = Collections.emptyList();
   private final List zzbid;
   public static final Creator CREATOR = new zzr();

   public static LocationResult create(List var0) {
      if (var0 == null) {
         var0 = zzbic;
      }

      return new LocationResult(var0);
   }

   LocationResult(List var1) {
      this.zzbid = var1;
   }

   public final Location getLastLocation() {
      int var1;
      return (var1 = this.zzbid.size()) == 0 ? null : (Location)this.zzbid.get(var1 - 1);
   }

   @NonNull
   public final List getLocations() {
      return this.zzbid;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.getLocations(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      int var1 = 17;

      int var5;
      for(Iterator var2 = this.zzbid.iterator(); var2.hasNext(); var1 = var1 * 31 + var5) {
         long var10000 = ((Location)var2.next()).getTime();
         var5 = (int)(var10000 ^ var10000 >>> 32);
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof LocationResult)) {
         return false;
      } else {
         LocationResult var2;
         if ((var2 = (LocationResult)var1).zzbid.size() != this.zzbid.size()) {
            return false;
         } else {
            Iterator var3 = var2.zzbid.iterator();
            Iterator var4 = this.zzbid.iterator();

            Location var5;
            Location var6;
            do {
               if (!var3.hasNext()) {
                  return true;
               }

               var5 = (Location)var4.next();
               var6 = (Location)var3.next();
            } while(var5.getTime() == var6.getTime());

            return false;
         }
      }
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbid);
      return (new StringBuilder(27 + String.valueOf(var1).length())).append("LocationResult[locations: ").append(var1).append("]").toString();
   }

   public static boolean hasResult(Intent var0) {
      return var0 == null ? false : var0.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
   }

   public static LocationResult extractResult(Intent var0) {
      return !hasResult(var0) ? null : (LocationResult)var0.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
   }
}
