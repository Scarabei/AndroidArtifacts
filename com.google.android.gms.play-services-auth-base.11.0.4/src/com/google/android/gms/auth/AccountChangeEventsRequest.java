package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

public class AccountChangeEventsRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private int mVersion;
   private int zzakj;
   /** @deprecated */
   @Deprecated
   private String zzakh;
   private Account zzajb;

   AccountChangeEventsRequest(int var1, int var2, String var3, Account var4) {
      this.mVersion = var1;
      this.zzakj = var2;
      this.zzakh = var3;
      if (var4 == null && !TextUtils.isEmpty(var3)) {
         this.zzajb = new Account(var3, "com.google");
      } else {
         this.zzajb = var4;
      }
   }

   public AccountChangeEventsRequest() {
      this.mVersion = 1;
   }

   public AccountChangeEventsRequest setEventIndex(int var1) {
      this.zzakj = var1;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public AccountChangeEventsRequest setAccountName(String var1) {
      this.zzakh = var1;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public String getAccountName() {
      return this.zzakh;
   }

   public AccountChangeEventsRequest setAccount(Account var1) {
      this.zzajb = var1;
      return this;
   }

   public Account getAccount() {
      return this.zzajb;
   }

   public int getEventIndex() {
      return this.zzakj;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.mVersion);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzakj);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzakh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzajb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
