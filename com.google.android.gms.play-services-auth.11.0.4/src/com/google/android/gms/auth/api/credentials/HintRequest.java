package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;

public final class HintRequest extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzg();
   private int zzaku;
   private final CredentialPickerConfig zzalk;
   private final boolean zzall;
   private final boolean zzalm;
   private final String[] zzale;
   private final boolean zzalh;
   private final String zzali;
   private final String zzalj;

   HintRequest(int var1, CredentialPickerConfig var2, boolean var3, boolean var4, String[] var5, boolean var6, String var7, String var8) {
      this.zzaku = var1;
      this.zzalk = (CredentialPickerConfig)zzbo.zzu(var2);
      this.zzall = var3;
      this.zzalm = var4;
      this.zzale = (String[])zzbo.zzu(var5);
      if (this.zzaku < 2) {
         this.zzalh = true;
         this.zzali = null;
         this.zzalj = null;
      } else {
         this.zzalh = var6;
         this.zzali = var7;
         this.zzalj = var8;
      }
   }

   private HintRequest(HintRequest.Builder var1) {
      this(2, var1.zzalk, var1.zzall, var1.zzalm, var1.zzale, var1.zzalh, var1.zzali, var1.zzalj);
   }

   @NonNull
   public final CredentialPickerConfig getHintPickerConfig() {
      return this.zzalk;
   }

   public final boolean isEmailAddressIdentifierSupported() {
      return this.zzall;
   }

   @NonNull
   public final String[] getAccountTypes() {
      return this.zzale;
   }

   public final boolean isIdTokenRequested() {
      return this.zzalh;
   }

   @Nullable
   public final String getServerClientId() {
      return this.zzali;
   }

   @Nullable
   public final String getIdTokenNonce() {
      return this.zzalj;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getHintPickerConfig(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.isEmailAddressIdentifierSupported());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzalm);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getAccountTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.isIdTokenRequested());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getServerClientId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getIdTokenNonce(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   HintRequest(HintRequest.Builder var1, zzf var2) {
      this(var1);
   }

   public static final class Builder {
      private boolean zzall;
      private boolean zzalm;
      private String[] zzale;
      private CredentialPickerConfig zzalk = (new CredentialPickerConfig.Builder()).build();
      private boolean zzalh = false;
      private String zzali;
      private String zzalj;

      public final HintRequest.Builder setEmailAddressIdentifierSupported(boolean var1) {
         this.zzall = var1;
         return this;
      }

      public final HintRequest.Builder setPhoneNumberIdentifierSupported(boolean var1) {
         this.zzalm = var1;
         return this;
      }

      public final HintRequest.Builder setAccountTypes(String... var1) {
         if (var1 == null) {
            var1 = new String[0];
         }

         this.zzale = var1;
         return this;
      }

      public final HintRequest.Builder setHintPickerConfig(@NonNull CredentialPickerConfig var1) {
         this.zzalk = (CredentialPickerConfig)zzbo.zzu(var1);
         return this;
      }

      public final HintRequest.Builder setIdTokenRequested(boolean var1) {
         this.zzalh = var1;
         return this;
      }

      public final HintRequest.Builder setServerClientId(@Nullable String var1) {
         this.zzali = var1;
         return this;
      }

      public final HintRequest.Builder setIdTokenNonce(@Nullable String var1) {
         this.zzalj = var1;
         return this;
      }

      public final HintRequest build() {
         if (this.zzale == null) {
            this.zzale = new String[0];
         }

         if (!this.zzall && !this.zzalm && this.zzale.length == 0) {
            throw new IllegalStateException("At least one authentication method must be specified");
         } else {
            return new HintRequest(this, (zzf)null);
         }
      }
   }
}
