package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CredentialPickerConfig extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzc();
   private int zzaku;
   private final boolean zzakZ;
   private final boolean mShowCancelButton;
   /** @deprecated */
   @Deprecated
   private final boolean zzala;
   private final int zzalb;

   CredentialPickerConfig(int var1, boolean var2, boolean var3, boolean var4, int var5) {
      this.zzaku = var1;
      this.zzakZ = var2;
      this.mShowCancelButton = var3;
      if (var1 < 2) {
         this.zzala = var4;
         this.zzalb = var4 ? 3 : 1;
      } else {
         this.zzala = var5 == 3;
         this.zzalb = var5;
      }
   }

   private CredentialPickerConfig(CredentialPickerConfig.Builder var1) {
      this(2, var1.zzakZ, var1.mShowCancelButton, false, var1.zzalc);
   }

   public final boolean shouldShowAddAccountButton() {
      return this.zzakZ;
   }

   public final boolean shouldShowCancelButton() {
      return this.mShowCancelButton;
   }

   /** @deprecated */
   @Deprecated
   public final boolean isForNewAccount() {
      return this.zzalb == 3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.shouldShowAddAccountButton());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.shouldShowCancelButton());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.isForNewAccount());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzalb);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   CredentialPickerConfig(CredentialPickerConfig.Builder var1, zzb var2) {
      this(var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Prompt {
      int CONTINUE = 1;
      int SIGN_IN = 2;
      int SIGN_UP = 3;
   }

   public static class Builder {
      private boolean zzakZ = false;
      private boolean mShowCancelButton = true;
      private int zzalc = 1;

      public CredentialPickerConfig.Builder setShowAddAccountButton(boolean var1) {
         this.zzakZ = var1;
         return this;
      }

      public CredentialPickerConfig.Builder setShowCancelButton(boolean var1) {
         this.mShowCancelButton = var1;
         return this;
      }

      public CredentialPickerConfig.Builder setPrompt(int var1) {
         this.zzalc = var1;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public CredentialPickerConfig.Builder setForNewAccount(boolean var1) {
         this.zzalc = var1 ? 3 : 1;
         return this;
      }

      public CredentialPickerConfig build() {
         return new CredentialPickerConfig(this, (zzb)null);
      }
   }
}
