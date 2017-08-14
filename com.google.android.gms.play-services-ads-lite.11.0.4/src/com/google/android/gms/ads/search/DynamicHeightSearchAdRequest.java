package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.zzla;

public final class DynamicHeightSearchAdRequest {
   private final SearchAdRequest zzacU;

   private DynamicHeightSearchAdRequest(DynamicHeightSearchAdRequest.Builder var1) {
      this.zzacU = var1.zzacV.build();
   }

   /** @deprecated */
   @Deprecated
   public final NetworkExtras getNetworkExtras(Class var1) {
      return this.zzacU.getNetworkExtras(var1);
   }

   public final Bundle getNetworkExtrasBundle(Class var1) {
      return this.zzacU.getNetworkExtrasBundle(var1);
   }

   public final Bundle getCustomEventExtrasBundle(Class var1) {
      return this.zzacU.getCustomEventExtrasBundle(var1);
   }

   public final String getQuery() {
      return this.zzacU.getQuery();
   }

   public final boolean isTestDevice(Context var1) {
      return this.zzacU.isTestDevice(var1);
   }

   final zzla zzab() {
      return this.zzacU.zzab();
   }

   // $FF: synthetic method
   DynamicHeightSearchAdRequest(DynamicHeightSearchAdRequest.Builder var1, zza var2) {
      this(var1);
   }

   public static final class Builder {
      private final SearchAdRequest.Builder zzacV = new SearchAdRequest.Builder();
      private final Bundle zzacW = new Bundle();

      public final DynamicHeightSearchAdRequest.Builder addNetworkExtras(NetworkExtras var1) {
         this.zzacV.addNetworkExtras(var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder addNetworkExtrasBundle(Class var1, Bundle var2) {
         this.zzacV.addNetworkExtrasBundle(var1, var2);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder addCustomEventExtrasBundle(Class var1, Bundle var2) {
         this.zzacV.addCustomEventExtrasBundle(var1, var2);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setPage(int var1) {
         this.zzacW.putString("csa_adPage", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setNumber(int var1) {
         this.zzacW.putString("csa_number", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setAdTest(boolean var1) {
         this.zzacW.putString("csa_adtest", var1 ? "on" : "off");
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setChannel(String var1) {
         this.zzacW.putString("csa_channel", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setHostLanguage(String var1) {
         this.zzacW.putString("csa_hl", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setLocationColor(String var1) {
         this.zzacW.putString("csa_colorLocation", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setLocationFontSize(int var1) {
         this.zzacW.putString("csa_fontSizeLocation", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setIsClickToCallEnabled(boolean var1) {
         this.zzacW.putString("csa_clickToCall", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setIsLocationEnabled(boolean var1) {
         this.zzacW.putString("csa_location", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setIsPlusOnesEnabled(boolean var1) {
         this.zzacW.putString("csa_plusOnes", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setIsSellerRatingsEnabled(boolean var1) {
         this.zzacW.putString("csa_sellerRatings", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setIsSiteLinksEnabled(boolean var1) {
         this.zzacW.putString("csa_siteLinks", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setCssWidth(int var1) {
         this.zzacW.putString("csa_width", Integer.toString(var1));
         return this;
      }

      /** @deprecated */
      @Deprecated
      public final DynamicHeightSearchAdRequest.Builder setFontFamily(int var1) {
         return this.setFontFamily(Integer.toString(var1));
      }

      public final DynamicHeightSearchAdRequest.Builder setFontFamily(String var1) {
         this.zzacW.putString("csa_fontFamily", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setFontFamilyAttribution(String var1) {
         this.zzacW.putString("csa_fontFamilyAttribution", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setFontSizeAnnotation(int var1) {
         this.zzacW.putString("csa_fontSizeAnnotation", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setFontSizeAttribution(int var1) {
         this.zzacW.putString("csa_fontSizeAttribution", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setFontSizeDescription(int var1) {
         this.zzacW.putString("csa_fontSizeDescription", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setFontSizeDomainLink(int var1) {
         this.zzacW.putString("csa_fontSizeDomainLink", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setFontSizeTitle(int var1) {
         this.zzacW.putString("csa_fontSizeTitle", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorAdBorder(String var1) {
         this.zzacW.putString("csa_colorAdBorder", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorAdSeparator(String var1) {
         this.zzacW.putString("csa_colorAdSeparator", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorAnnotation(String var1) {
         this.zzacW.putString("csa_colorAnnotation", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorAttribution(String var1) {
         this.zzacW.putString("csa_colorAttribution", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorBackground(String var1) {
         this.zzacW.putString("csa_colorBackground", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorBorder(String var1) {
         this.zzacW.putString("csa_colorBorder", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorDomainLink(String var1) {
         this.zzacW.putString("csa_colorDomainLink", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorText(String var1) {
         this.zzacW.putString("csa_colorText", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setColorTitleLink(String var1) {
         this.zzacW.putString("csa_colorTitleLink", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setAdBorderSelectors(String var1) {
         this.zzacW.putString("csa_adBorderSelectors", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setAdjustableLineHeight(int var1) {
         this.zzacW.putString("csa_adjustableLineHeight", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setAttributionSpacingBelow(int var1) {
         this.zzacW.putString("csa_attributionSpacingBelow", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setBorderSelections(String var1) {
         this.zzacW.putString("csa_borderSelections", var1);
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setIsTitleUnderlined(boolean var1) {
         this.zzacW.putString("csa_noTitleUnderline", Boolean.toString(!var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setIsTitleBold(boolean var1) {
         this.zzacW.putString("csa_titleBold", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setVerticalSpacing(int var1) {
         this.zzacW.putString("csa_verticalSpacing", Integer.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setDetailedAttribution(boolean var1) {
         this.zzacW.putString("csa_detailedAttribution", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setLongerHeadlines(boolean var1) {
         this.zzacW.putString("csa_longerHeadlines", Boolean.toString(var1));
         return this;
      }

      public final DynamicHeightSearchAdRequest.Builder setAdvancedOptionValue(String var1, String var2) {
         this.zzacW.putString(var1, var2);
         return this;
      }

      public final DynamicHeightSearchAdRequest build() {
         this.zzacV.addNetworkExtrasBundle(AdMobAdapter.class, this.zzacW);
         return new DynamicHeightSearchAdRequest(this, (zza)null);
      }

      public final DynamicHeightSearchAdRequest.Builder setQuery(String var1) {
         this.zzacV.setQuery(var1);
         return this;
      }
   }
}
