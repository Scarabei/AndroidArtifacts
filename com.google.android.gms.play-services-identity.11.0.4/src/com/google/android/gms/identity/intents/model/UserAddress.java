package com.google.android.gms.identity.intents.model;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class UserAddress extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzb();
   private String name;
   private String zzbgE;
   private String zzbgF;
   private String zzbgG;
   private String zzbgH;
   private String zzbgI;
   private String zzbgJ;
   private String zzbgK;
   private String zzVJ;
   private String zzbgL;
   private String zzbgM;
   private String phoneNumber;
   private boolean zzbgN;
   private String zzbgO;
   private String zzbgP;

   public static UserAddress fromIntent(Intent var0) {
      return var0 != null && var0.hasExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS") ? (UserAddress)var0.getParcelableExtra("com.google.android.gms.identity.intents.EXTRA_ADDRESS") : null;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.name, false);
      zzd.zza(var1, 3, this.zzbgE, false);
      zzd.zza(var1, 4, this.zzbgF, false);
      zzd.zza(var1, 5, this.zzbgG, false);
      zzd.zza(var1, 6, this.zzbgH, false);
      zzd.zza(var1, 7, this.zzbgI, false);
      zzd.zza(var1, 8, this.zzbgJ, false);
      zzd.zza(var1, 9, this.zzbgK, false);
      zzd.zza(var1, 10, this.zzVJ, false);
      zzd.zza(var1, 11, this.zzbgL, false);
      zzd.zza(var1, 12, this.zzbgM, false);
      zzd.zza(var1, 13, this.phoneNumber, false);
      zzd.zza(var1, 14, this.zzbgN);
      zzd.zza(var1, 15, this.zzbgO, false);
      zzd.zza(var1, 16, this.zzbgP, false);
      zzd.zzI(var1, var5);
   }

   UserAddress(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, boolean var13, String var14, String var15) {
      this.name = var1;
      this.zzbgE = var2;
      this.zzbgF = var3;
      this.zzbgG = var4;
      this.zzbgH = var5;
      this.zzbgI = var6;
      this.zzbgJ = var7;
      this.zzbgK = var8;
      this.zzVJ = var9;
      this.zzbgL = var10;
      this.zzbgM = var11;
      this.phoneNumber = var12;
      this.zzbgN = var13;
      this.zzbgO = var14;
      this.zzbgP = var15;
   }

   UserAddress() {
   }

   public final String getName() {
      return this.name;
   }

   public final String getAddress1() {
      return this.zzbgE;
   }

   public final String getAddress2() {
      return this.zzbgF;
   }

   public final String getAddress3() {
      return this.zzbgG;
   }

   public final String getAddress4() {
      return this.zzbgH;
   }

   public final String getAddress5() {
      return this.zzbgI;
   }

   public final String getAdministrativeArea() {
      return this.zzbgJ;
   }

   public final String getLocality() {
      return this.zzbgK;
   }

   public final String getCountryCode() {
      return this.zzVJ;
   }

   public final String getPostalCode() {
      return this.zzbgL;
   }

   public final String getSortingCode() {
      return this.zzbgM;
   }

   public final String getPhoneNumber() {
      return this.phoneNumber;
   }

   public final boolean isPostBox() {
      return this.zzbgN;
   }

   public final String getCompanyName() {
      return this.zzbgO;
   }

   public final String getEmailAddress() {
      return this.zzbgP;
   }
}
