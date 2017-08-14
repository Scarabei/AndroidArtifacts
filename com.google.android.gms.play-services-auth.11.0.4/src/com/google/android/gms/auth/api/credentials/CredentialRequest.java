package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class CredentialRequest extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zze();
   private int zzaku;
   private final boolean zzald;
   private final String[] zzale;
   private final CredentialPickerConfig zzalf;
   private final CredentialPickerConfig zzalg;
   private final boolean zzalh;
   private final String zzali;
   private final String zzalj;

   CredentialRequest(int var1, boolean var2, String[] var3, CredentialPickerConfig var4, CredentialPickerConfig var5, boolean var6, String var7, String var8) {
      this.zzaku = var1;
      this.zzald = var2;
      this.zzale = (String[])zzbo.zzu(var3);
      this.zzalf = var4 == null ? (new CredentialPickerConfig.Builder()).build() : var4;
      this.zzalg = var5 == null ? (new CredentialPickerConfig.Builder()).build() : var5;
      if (var1 < 3) {
         this.zzalh = true;
         this.zzali = null;
         this.zzalj = null;
      } else {
         this.zzalh = var6;
         this.zzali = var7;
         this.zzalj = var8;
      }
   }

   private CredentialRequest(CredentialRequest.Builder var1) {
      this(3, var1.zzald, var1.zzale, var1.zzalf, var1.zzalg, var1.zzalh, var1.zzali, var1.zzalj);
   }

   /** @deprecated */
   @Deprecated
   public final boolean getSupportsPasswordLogin() {
      return this.isPasswordLoginSupported();
   }

   public final boolean isPasswordLoginSupported() {
      return this.zzald;
   }

   @NonNull
   public final String[] getAccountTypes() {
      return this.zzale;
   }

   @NonNull
   public final Set getAccountTypesSet() {
      return new HashSet(Arrays.asList(this.zzale));
   }

   @NonNull
   public final CredentialPickerConfig getCredentialPickerConfig() {
      return this.zzalf;
   }

   @NonNull
   public final CredentialPickerConfig getCredentialHintPickerConfig() {
      return this.zzalg;
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
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.isPasswordLoginSupported());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getAccountTypes(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getCredentialPickerConfig(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getCredentialHintPickerConfig(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.isIdTokenRequested());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getServerClientId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getIdTokenNonce(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   CredentialRequest(CredentialRequest.Builder var1, zzd var2) {
      this(var1);
   }

   public static final class Builder {
      private boolean zzald;
      private String[] zzale;
      private CredentialPickerConfig zzalf;
      private CredentialPickerConfig zzalg;
      private boolean zzalh = false;
      @Nullable
      private String zzali = null;
      @Nullable
      private String zzalj;

      /** @deprecated */
      @Deprecated
      public final CredentialRequest.Builder setSupportsPasswordLogin(boolean var1) {
         return this.setPasswordLoginSupported(var1);
      }

      public final CredentialRequest.Builder setPasswordLoginSupported(boolean var1) {
         this.zzald = var1;
         return this;
      }

      public final CredentialRequest.Builder setAccountTypes(String... var1) {
         if (var1 == null) {
            var1 = new String[0];
         }

         this.zzale = var1;
         return this;
      }

      public final CredentialRequest.Builder setCredentialPickerConfig(CredentialPickerConfig var1) {
         this.zzalf = var1;
         return this;
      }

      public final CredentialRequest.Builder setCredentialHintPickerConfig(CredentialPickerConfig var1) {
         this.zzalg = var1;
         return this;
      }

      public final CredentialRequest.Builder setIdTokenRequested(boolean var1) {
         this.zzalh = var1;
         return this;
      }

      public final CredentialRequest.Builder setServerClientId(@Nullable String var1) {
         this.zzali = var1;
         return this;
      }

      public final CredentialRequest.Builder setIdTokenNonce(@Nullable String var1) {
         this.zzalj = var1;
         return this;
      }

      public final CredentialRequest build() {
         if (this.zzale == null) {
            this.zzale = new String[0];
         }

         if (!this.zzald && this.zzale.length == 0) {
            throw new IllegalStateException("At least one authentication method must be specified");
         } else {
            return new CredentialRequest(this, (zzd)null);
         }
      }
   }
}
