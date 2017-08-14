package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

public class NativeContentAdMapper extends NativeAdMapper {
   private String zzHB;
   private List zzHC;
   private String zzHD;
   private NativeAd.Image zzacN;
   private String zzHF;
   private String zzHP;
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

   public final void setLogo(NativeAd.Image var1) {
      this.zzacN = var1;
   }

   public final void setCallToAction(String var1) {
      this.zzHF = var1;
   }

   public final void setAdvertiser(String var1) {
      this.zzHP = var1;
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

   public final NativeAd.Image getLogo() {
      return this.zzacN;
   }

   public final String getCallToAction() {
      return this.zzHF;
   }

   public final String getAdvertiser() {
      return this.zzHP;
   }

   public final VideoController getVideoController() {
      return this.zzBd;
   }
}
