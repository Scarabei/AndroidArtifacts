package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzare;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Credential extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final String EXTRA_KEY = "com.google.android.gms.credentials.Credential";
   public static final Creator CREATOR = new zza();
   private int zzaku;
   private final String zzIi;
   @Nullable
   private final String mName;
   @Nullable
   private final Uri zzakR;
   private final List zzakS;
   @Nullable
   private final String zzakT;
   @Nullable
   private final String zzakU;
   @Nullable
   private final String zzakV;
   @Nullable
   private final String zzakW;
   @Nullable
   private final String zzakX;
   @Nullable
   private final String zzakY;

   Credential(int var1, String var2, String var3, Uri var4, List var5, String var6, String var7, String var8, String var9, String var10, String var11) {
      this.zzaku = var1;
      String var12;
      zzbo.zzh(var12 = ((String)zzbo.zzb(var2, "credential identifier cannot be null")).trim(), "credential identifier cannot be empty");
      this.zzIi = var12;
      if (var3 != null && TextUtils.isEmpty(var3.trim())) {
         var3 = null;
      }

      this.mName = var3;
      this.zzakR = var4;
      this.zzakS = var5 == null ? Collections.emptyList() : Collections.unmodifiableList(var5);
      this.zzakT = var6;
      if (var6 != null && var6.isEmpty()) {
         throw new IllegalArgumentException("password cannot be empty");
      } else {
         if (!TextUtils.isEmpty(var7)) {
            zzare.zzbN(var7);
         }

         this.zzakU = var7;
         this.zzakV = var8;
         this.zzakW = var9;
         this.zzakX = var10;
         this.zzakY = var11;
         if (!TextUtils.isEmpty(this.zzakT) && !TextUtils.isEmpty(this.zzakU)) {
            throw new IllegalStateException("password and accountType cannot both be set");
         }
      }
   }

   public String getId() {
      return this.zzIi;
   }

   @Nullable
   public String getName() {
      return this.mName;
   }

   @Nullable
   public Uri getProfilePictureUri() {
      return this.zzakR;
   }

   public List getIdTokens() {
      return this.zzakS;
   }

   @Nullable
   public String getPassword() {
      return this.zzakT;
   }

   @Nullable
   public String getGeneratedPassword() {
      return this.zzakV;
   }

   @Nullable
   public String getAccountType() {
      return this.zzakU;
   }

   @Nullable
   public String getGivenName() {
      return this.zzakX;
   }

   @Nullable
   public String getFamilyName() {
      return this.zzakY;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getProfilePictureUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getIdTokens(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getPassword(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getAccountType(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getGeneratedPassword(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzakW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.getGivenName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.getFamilyName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Credential)) {
         return false;
      } else {
         Credential var2 = (Credential)var1;
         return TextUtils.equals(this.zzIi, var2.zzIi) && TextUtils.equals(this.mName, var2.mName) && zzbe.equal(this.zzakR, var2.zzakR) && TextUtils.equals(this.zzakT, var2.zzakT) && TextUtils.equals(this.zzakU, var2.zzakU) && TextUtils.equals(this.zzakV, var2.zzakV);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzIi, this.mName, this.zzakR, this.zzakT, this.zzakU, this.zzakV});
   }

   public static class Builder {
      private final String zzIi;
      private String mName;
      private Uri zzakR;
      private List zzakS;
      private String zzakT;
      private String zzakU;
      private String zzakV;
      private String zzakW;
      private String zzakX;
      private String zzakY;

      public Builder(String var1) {
         this.zzIi = var1;
      }

      public Builder(Credential var1) {
         this.zzIi = var1.zzIi;
         this.mName = var1.mName;
         this.zzakR = var1.zzakR;
         this.zzakS = var1.zzakS;
         this.zzakT = var1.zzakT;
         this.zzakU = var1.zzakU;
         this.zzakV = var1.zzakV;
         this.zzakW = var1.zzakW;
         this.zzakX = var1.zzakX;
         this.zzakY = var1.zzakY;
      }

      public Credential.Builder setName(String var1) {
         this.mName = var1;
         return this;
      }

      public Credential.Builder setProfilePictureUri(Uri var1) {
         this.zzakR = var1;
         return this;
      }

      public Credential.Builder setPassword(String var1) {
         this.zzakT = var1;
         return this;
      }

      public Credential.Builder setAccountType(String var1) {
         this.zzakU = var1;
         return this;
      }

      public Credential build() {
         return new Credential(4, this.zzIi, this.mName, this.zzakR, this.zzakS, this.zzakT, this.zzakU, this.zzakV, this.zzakW, this.zzakX, this.zzakY);
      }
   }
}
