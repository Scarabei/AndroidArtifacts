package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzlb;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class PublisherAdRequest {
   public static final int ERROR_CODE_INTERNAL_ERROR = 0;
   public static final int ERROR_CODE_INVALID_REQUEST = 1;
   public static final int ERROR_CODE_NETWORK_ERROR = 2;
   public static final int ERROR_CODE_NO_FILL = 3;
   public static final int GENDER_UNKNOWN = 0;
   public static final int GENDER_MALE = 1;
   public static final int GENDER_FEMALE = 2;
   public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
   private final zzla zzrT;

   private PublisherAdRequest(PublisherAdRequest.Builder var1) {
      this.zzrT = new zzla(var1.zzrU);
   }

   public final Date getBirthday() {
      return this.zzrT.getBirthday();
   }

   public final String getContentUrl() {
      return this.zzrT.getContentUrl();
   }

   public final int getGender() {
      return this.zzrT.getGender();
   }

   public final Set getKeywords() {
      return this.zzrT.getKeywords();
   }

   public final Location getLocation() {
      return this.zzrT.getLocation();
   }

   public final boolean getManualImpressionsEnabled() {
      return this.zzrT.getManualImpressionsEnabled();
   }

   /** @deprecated */
   @Deprecated
   public final NetworkExtras getNetworkExtras(Class var1) {
      return this.zzrT.getNetworkExtras(var1);
   }

   public final Bundle getNetworkExtrasBundle(Class var1) {
      return this.zzrT.getNetworkExtrasBundle(var1);
   }

   public final Bundle getCustomEventExtrasBundle(Class var1) {
      return this.zzrT.getCustomEventExtrasBundle(var1);
   }

   public final String getPublisherProvidedId() {
      return this.zzrT.getPublisherProvidedId();
   }

   public final boolean isTestDevice(Context var1) {
      return this.zzrT.isTestDevice(var1);
   }

   public final Bundle getCustomTargeting() {
      return this.zzrT.getCustomTargeting();
   }

   public static void updateCorrelator() {
   }

   public final zzla zzab() {
      return this.zzrT;
   }

   // $FF: synthetic method
   PublisherAdRequest(PublisherAdRequest.Builder var1, zza var2) {
      this(var1);
   }

   public static final class Builder {
      private final zzlb zzrU = new zzlb();

      public final PublisherAdRequest.Builder addKeyword(String var1) {
         this.zzrU.zzD(var1);
         return this;
      }

      public final PublisherAdRequest.Builder addNetworkExtras(NetworkExtras var1) {
         this.zzrU.zza(var1);
         return this;
      }

      public final PublisherAdRequest.Builder addNetworkExtrasBundle(Class var1, Bundle var2) {
         this.zzrU.zza(var1, var2);
         return this;
      }

      public final PublisherAdRequest.Builder addCustomEventExtrasBundle(Class var1, Bundle var2) {
         this.zzrU.zzb(var1, var2);
         return this;
      }

      public final PublisherAdRequest.Builder addTestDevice(String var1) {
         this.zzrU.zzE(var1);
         return this;
      }

      public final PublisherAdRequest build() {
         return new PublisherAdRequest(this, (zza)null);
      }

      public final PublisherAdRequest.Builder setBirthday(Date var1) {
         this.zzrU.zza(var1);
         return this;
      }

      public final PublisherAdRequest.Builder setContentUrl(String var1) {
         zzbo.zzb(var1, "Content URL must be non-null.");
         zzbo.zzh(var1, "Content URL must be non-empty.");
         zzbo.zzb(var1.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[]{Integer.valueOf(512), var1.length()});
         this.zzrU.zzG(var1);
         return this;
      }

      public final PublisherAdRequest.Builder setGender(int var1) {
         this.zzrU.zzk(var1);
         return this;
      }

      public final PublisherAdRequest.Builder setLocation(Location var1) {
         this.zzrU.zzb(var1);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public final PublisherAdRequest.Builder setManualImpressionsEnabled(boolean var1) {
         this.zzrU.setManualImpressionsEnabled(var1);
         return this;
      }

      public final PublisherAdRequest.Builder setPublisherProvidedId(String var1) {
         this.zzrU.zzH(var1);
         return this;
      }

      public final PublisherAdRequest.Builder setRequestAgent(String var1) {
         this.zzrU.zzI(var1);
         return this;
      }

      public final PublisherAdRequest.Builder tagForChildDirectedTreatment(boolean var1) {
         this.zzrU.zzh(var1);
         return this;
      }

      public final PublisherAdRequest.Builder addCustomTargeting(String var1, String var2) {
         this.zzrU.zzf(var1, var2);
         return this;
      }

      public final PublisherAdRequest.Builder addCustomTargeting(String var1, List var2) {
         if (var2 != null) {
            this.zzrU.zzf(var1, TextUtils.join(",", var2));
         }

         return this;
      }

      public final PublisherAdRequest.Builder addCategoryExclusion(String var1) {
         this.zzrU.zzJ(var1);
         return this;
      }

      public final PublisherAdRequest.Builder setIsDesignedForFamilies(boolean var1) {
         this.zzrU.zzi(var1);
         return this;
      }
   }
}
