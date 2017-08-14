package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class IsReadyToPayRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzl();
   ArrayList zzbON;
   private String zzbOO;
   private String zzbOP;

   IsReadyToPayRequest() {
   }

   IsReadyToPayRequest(ArrayList var1, String var2, String var3) {
      this.zzbON = var1;
      this.zzbOO = var2;
      this.zzbOP = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbON, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbOO, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOP, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final ArrayList getAllowedCardNetworks() {
      return this.zzbON;
   }

   public static IsReadyToPayRequest.Builder newBuilder() {
      return new IsReadyToPayRequest().new Builder((zzk)null);
   }

   public final class Builder {
      // $FF: synthetic field
      private IsReadyToPayRequest zzbOQ;

      private Builder() {
         this.zzbOQ = IsReadyToPayRequest.this;
         super();
      }

      public final IsReadyToPayRequest.Builder addAllowedCardNetwork(int var1) {
         if (this.zzbOQ.zzbON == null) {
            this.zzbOQ.zzbON = new ArrayList();
         }

         this.zzbOQ.zzbON.add(var1);
         return this;
      }

      public final IsReadyToPayRequest build() {
         return this.zzbOQ;
      }

      // $FF: synthetic method
      Builder(zzk var2) {
         this();
      }
   }
}
