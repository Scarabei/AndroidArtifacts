package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class FullWallet extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzf();
   private String zzbOq;
   private String zzbOr;
   private ProxyCard zzbOs;
   private String zzbOt;
   private zza zzbOu;
   private zza zzbOv;
   private String[] zzbOw;
   private UserAddress zzbOx;
   private UserAddress zzbOy;
   private InstrumentInfo[] zzbOz;
   private PaymentMethodToken zzbOA;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbOq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbOr, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbOs, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOt, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbOu, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOv, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbOw, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbOx, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbOy, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbOz, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbOA, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   FullWallet(String var1, String var2, ProxyCard var3, String var4, zza var5, zza var6, String[] var7, UserAddress var8, UserAddress var9, InstrumentInfo[] var10, PaymentMethodToken var11) {
      this.zzbOq = var1;
      this.zzbOr = var2;
      this.zzbOs = var3;
      this.zzbOt = var4;
      this.zzbOu = var5;
      this.zzbOv = var6;
      this.zzbOw = var7;
      this.zzbOx = var8;
      this.zzbOy = var9;
      this.zzbOz = var10;
      this.zzbOA = var11;
   }

   private FullWallet() {
   }

   public final String getGoogleTransactionId() {
      return this.zzbOq;
   }

   public final String getMerchantTransactionId() {
      return this.zzbOr;
   }

   public final ProxyCard getProxyCard() {
      return this.zzbOs;
   }

   public final String getEmail() {
      return this.zzbOt;
   }

   public final String[] getPaymentDescriptions() {
      return this.zzbOw;
   }

   public final UserAddress getBuyerBillingAddress() {
      return this.zzbOx;
   }

   public final UserAddress getBuyerShippingAddress() {
      return this.zzbOy;
   }

   public final InstrumentInfo[] getInstrumentInfos() {
      return this.zzbOz;
   }

   public final PaymentMethodToken getPaymentMethodToken() {
      return this.zzbOA;
   }
}
