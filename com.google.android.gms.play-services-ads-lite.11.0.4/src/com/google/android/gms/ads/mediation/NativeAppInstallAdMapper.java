package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

public class NativeAppInstallAdMapper extends NativeAdMapper {
   private String zzHB;
   private List zzHC;
   private String zzHD;
   private NativeAd.Image zzacM;
   private String zzHF;
   private double zzHG;
   private String zzHH;
   private String zzHI;
   private VideoController zzBd;

   public final void setHeadline(String var1) {
      this.zzHB = var1;
   }

   public final void setImages(List var1) {
      this.zzHC = var1;
   }

   public final void setBody(String var1) {
      this.zzHD = var1;
   }

   public final void setIcon(NativeAd.Image var1) {
      this.zzacM = var1;
   }

   public final void setCallToAction(String var1) {
      this.zzHF = var1;
   }

   public final void setStarRating(double var1) {
      this.zzHG = var1;
   }

   public final void setStore(String var1) {
      this.zzHH = var1;
   }

   public final void setPrice(String var1) {
      this.zzHI = var1;
   }

   public final void zza(VideoController var1) {
      this.zzBd = var1;
   }

   public final String getHeadline() {
      return this.zzHB;
   }

   public final List getImages() {
      return this.zzHC;
   }

   public final String getBody() {
      return this.zzHD;
   }

   public final NativeAd.Image getIcon() {
      return this.zzacM;
   }

   public final String getCallToAction() {
      return this.zzHF;
   }

   public final double getStarRating() {
      return this.zzHG;
   }

   public final String getStore() {
      return this.zzHH;
   }

   public final String getPrice() {
      return this.zzHI;
   }

   public final VideoController getVideoController() {
      return this.zzBd;
   }
}
