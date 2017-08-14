package com.google.android.gms.ads.search;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzlb;

public final class SearchAdRequest {
   public static final int ERROR_CODE_INTERNAL_ERROR = 0;
   public static final int ERROR_CODE_INVALID_REQUEST = 1;
   public static final int ERROR_CODE_NETWORK_ERROR = 2;
   public static final int ERROR_CODE_NO_FILL = 3;
   public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
   public static final int BORDER_TYPE_NONE = 0;
   public static final int BORDER_TYPE_DASHED = 1;
   public static final int BORDER_TYPE_DOTTED = 2;
   public static final int BORDER_TYPE_SOLID = 3;
   public static final int CALL_BUTTON_COLOR_LIGHT = 0;
   public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
   public static final int CALL_BUTTON_COLOR_DARK = 2;
   private final zzla zzrT;
   private final int zzacX;
   private final int zzHs;
   private final int zzacY;
   private final int zzacZ;
   private final int zzada;
   private final int zzadb;
   private final int zzadc;
   private final int zzadd;
   private final String zzade;
   private final int zzadf;
   private final String zzadg;
   private final int zzadh;
   private final int zzadi;
   private final String zzvi;

   private SearchAdRequest(SearchAdRequest.Builder var1) {
      this.zzacX = var1.zzacX;
      this.zzHs = var1.zzHs;
      this.zzacY = var1.zzacY;
      this.zzacZ = var1.zzacZ;
      this.zzada = var1.zzada;
      this.zzadb = var1.zzadb;
      this.zzadc = var1.zzadc;
      this.zzadd = var1.zzadd;
      this.zzade = var1.zzade;
      this.zzadf = var1.zzadf;
      this.zzadg = var1.zzadg;
      this.zzadh = var1.zzadh;
      this.zzadi = var1.zzadi;
      this.zzvi = var1.zzvi;
      this.zzrT = new zzla(var1.zzrU, this);
   }

   public final int getAnchorTextColor() {
      return this.zzacX;
   }

   public final int getBackgroundColor() {
      return this.zzHs;
   }

   public final int getBackgroundGradientBottom() {
      return this.zzacY;
   }

   public final int getBackgroundGradientTop() {
      return this.zzacZ;
   }

   public final int getBorderColor() {
      return this.zzada;
   }

   public final int getBorderThickness() {
      return this.zzadb;
   }

   public final int getBorderType() {
      return this.zzadc;
   }

   public final int getCallButtonColor() {
      return this.zzadd;
   }

   public final String getCustomChannels() {
      return this.zzade;
   }

   public final int getDescriptionTextColor() {
      return this.zzadf;
   }

   public final String getFontFace() {
      return this.zzadg;
   }

   public final int getHeaderTextColor() {
      return this.zzadh;
   }

   public final int getHeaderTextSize() {
      return this.zzadi;
   }

   public final Location getLocation() {
      return this.zzrT.getLocation();
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

   public final String getQuery() {
      return this.zzvi;
   }

   public final boolean isTestDevice(Context var1) {
      return this.zzrT.isTestDevice(var1);
   }

   final zzla zzab() {
      return this.zzrT;
   }

   // $FF: synthetic method
   SearchAdRequest(SearchAdRequest.Builder var1, zzb var2) {
      this(var1);
   }

   public static final class Builder {
      private final zzlb zzrU = new zzlb();
      private int zzacX;
      private int zzHs;
      private int zzacY;
      private int zzacZ;
      private int zzada;
      private int zzadb;
      private int zzadc = 0;
      private int zzadd;
      private String zzade;
      private int zzadf;
      private String zzadg;
      private int zzadh;
      private int zzadi;
      private String zzvi;

      public final SearchAdRequest.Builder addNetworkExtras(NetworkExtras var1) {
         this.zzrU.zza(var1);
         return this;
      }

      public final SearchAdRequest.Builder addNetworkExtrasBundle(Class var1, Bundle var2) {
         this.zzrU.zza(var1, var2);
         return this;
      }

      public final SearchAdRequest.Builder addCustomEventExtrasBundle(Class var1, Bundle var2) {
         this.zzrU.zzb(var1, var2);
         return this;
      }

      public final SearchAdRequest.Builder addTestDevice(String var1) {
         this.zzrU.zzE(var1);
         return this;
      }

      public final SearchAdRequest build() {
         return new SearchAdRequest(this, (zzb)null);
      }

      public final SearchAdRequest.Builder setAnchorTextColor(int var1) {
         this.zzacX = var1;
         return this;
      }

      public final SearchAdRequest.Builder setBackgroundColor(int var1) {
         this.zzHs = var1;
         this.zzacY = Color.argb(0, 0, 0, 0);
         this.zzacZ = Color.argb(0, 0, 0, 0);
         return this;
      }

      public final SearchAdRequest.Builder setBackgroundGradient(int var1, int var2) {
         this.zzHs = Color.argb(0, 0, 0, 0);
         this.zzacY = var2;
         this.zzacZ = var1;
         return this;
      }

      public final SearchAdRequest.Builder setBorderColor(int var1) {
         this.zzada = var1;
         return this;
      }

      public final SearchAdRequest.Builder setBorderThickness(int var1) {
         this.zzadb = var1;
         return this;
      }

      public final SearchAdRequest.Builder setBorderType(int var1) {
         this.zzadc = var1;
         return this;
      }

      public final SearchAdRequest.Builder setCallButtonColor(int var1) {
         this.zzadd = var1;
         return this;
      }

      public final SearchAdRequest.Builder setCustomChannels(String var1) {
         this.zzade = var1;
         return this;
      }

      public final SearchAdRequest.Builder setDescriptionTextColor(int var1) {
         this.zzadf = var1;
         return this;
      }

      public final SearchAdRequest.Builder setFontFace(String var1) {
         this.zzadg = var1;
         return this;
      }

      public final SearchAdRequest.Builder setHeaderTextColor(int var1) {
         this.zzadh = var1;
         return this;
      }

      public final SearchAdRequest.Builder setHeaderTextSize(int var1) {
         this.zzadi = var1;
         return this;
      }

      public final SearchAdRequest.Builder setLocation(Location var1) {
         this.zzrU.zzb(var1);
         return this;
      }

      public final SearchAdRequest.Builder setQuery(String var1) {
         this.zzvi = var1;
         return this;
      }

      public final SearchAdRequest.Builder setRequestAgent(String var1) {
         this.zzrU.zzI(var1);
         return this;
      }

      public final SearchAdRequest.Builder tagForChildDirectedTreatment(boolean var1) {
         this.zzrU.zzh(var1);
         return this;
      }
   }
}
