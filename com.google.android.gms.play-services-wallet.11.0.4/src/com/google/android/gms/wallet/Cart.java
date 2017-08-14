package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.List;

public final class Cart extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzd();
   String zzbOm;
   String zzbOn;
   ArrayList zzbOo;

   public static Cart.Builder newBuilder() {
      return new Cart().new Builder((zzc)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbOm, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbOn, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbOo, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   Cart(String var1, String var2, ArrayList var3) {
      this.zzbOm = var1;
      this.zzbOn = var2;
      this.zzbOo = var3;
   }

   Cart() {
      this.zzbOo = new ArrayList();
   }

   public final String getTotalPrice() {
      return this.zzbOm;
   }

   public final String getCurrencyCode() {
      return this.zzbOn;
   }

   public final ArrayList getLineItems() {
      return this.zzbOo;
   }

   public final class Builder {
      // $FF: synthetic field
      private Cart zzbOp;

      private Builder() {
         this.zzbOp = Cart.this;
         super();
      }

      public final Cart.Builder setTotalPrice(String var1) {
         this.zzbOp.zzbOm = var1;
         return this;
      }

      public final Cart.Builder setCurrencyCode(String var1) {
         this.zzbOp.zzbOn = var1;
         return this;
      }

      public final Cart.Builder setLineItems(List var1) {
         this.zzbOp.zzbOo.clear();
         this.zzbOp.zzbOo.addAll(var1);
         return this;
      }

      public final Cart.Builder addLineItem(LineItem var1) {
         this.zzbOp.zzbOo.add(var1);
         return this;
      }

      public final Cart build() {
         return this.zzbOp;
      }

      // $FF: synthetic method
      Builder(zzc var2) {
         this();
      }
   }
}
