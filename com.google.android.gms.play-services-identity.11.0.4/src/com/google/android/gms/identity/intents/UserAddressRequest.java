package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class UserAddressRequest extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzd();
   List zzbgB;

   public static UserAddressRequest.Builder newBuilder() {
      return new UserAddressRequest().new Builder((zzc)null);
   }

   UserAddressRequest(List var1) {
      this.zzbgB = var1;
   }

   UserAddressRequest() {
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbgB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final class Builder {
      // $FF: synthetic field
      private UserAddressRequest zzbgC;

      private Builder() {
         this.zzbgC = UserAddressRequest.this;
         super();
      }

      public final UserAddressRequest.Builder addAllowedCountrySpecification(CountrySpecification var1) {
         if (this.zzbgC.zzbgB == null) {
            this.zzbgC.zzbgB = new ArrayList();
         }

         this.zzbgC.zzbgB.add(var1);
         return this;
      }

      public final UserAddressRequest.Builder addAllowedCountrySpecifications(Collection var1) {
         if (this.zzbgC.zzbgB == null) {
            this.zzbgC.zzbgB = new ArrayList();
         }

         this.zzbgC.zzbgB.addAll(var1);
         return this;
      }

      public final UserAddressRequest build() {
         if (this.zzbgC.zzbgB != null) {
            this.zzbgC.zzbgB = Collections.unmodifiableList(this.zzbgC.zzbgB);
         }

         return this.zzbgC;
      }

      // $FF: synthetic method
      Builder(zzc var2) {
         this();
      }
   }
}
