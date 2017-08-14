package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;
import java.util.Locale;

public final class PlacesOptions implements Optional {
   @Nullable
   public final String zzbjS;
   @Nullable
   public final String zzbjT;
   public final int zzbjU;
   @Nullable
   public final String zzakh;
   @Nullable
   public final Locale zzbjV;

   private PlacesOptions(PlacesOptions.Builder var1) {
      this.zzbjS = null;
      this.zzbjT = null;
      this.zzbjU = 0;
      this.zzakh = null;
      this.zzbjV = null;
   }

   public final boolean equals(Object var1) {
      if (var1 instanceof PlacesOptions) {
         return zzbe.equal((Object)null, (Object)null) && zzbe.equal((Object)null, (Object)null) && zzbe.equal(Integer.valueOf(0), Integer.valueOf(0)) && zzbe.equal((Object)null, (Object)null) && zzbe.equal((Object)null, (Object)null);
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{null, null, Integer.valueOf(0), null, null});
   }

   // $FF: synthetic method
   PlacesOptions(PlacesOptions.Builder var1, zzn var2) {
      this(var1);
   }

   public static class Builder {
      private int zzbjU = 0;

      public PlacesOptions build() {
         return new PlacesOptions(this, (zzn)null);
      }
   }
}
