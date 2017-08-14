package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.internal.zzajc;

public final class NativeAppInstallAdView extends NativeAdView {
   public NativeAppInstallAdView(Context var1) {
      super(var1);
   }

   public NativeAppInstallAdView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public NativeAppInstallAdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public NativeAppInstallAdView(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public final void setHeadlineView(View var1) {
      super.zza("2001", var1);
   }

   public final void setCallToActionView(View var1) {
      super.zza("2002", var1);
   }

   public final void setIconView(View var1) {
      super.zza("2003", var1);
   }

   public final void setBodyView(View var1) {
      super.zza("2004", var1);
   }

   public final void setStoreView(View var1) {
      super.zza("2005", var1);
   }

   public final void setPriceView(View var1) {
      super.zza("2006", var1);
   }

   public final void setImageView(View var1) {
      super.zza("2007", var1);
   }

   public final void setStarRatingView(View var1) {
      super.zza("2008", var1);
   }

   public final void setMediaView(MediaView var1) {
      super.zza("2011", var1);
   }

   public final View getHeadlineView() {
      return super.zzp("2001");
   }

   public final View getCallToActionView() {
      return super.zzp("2002");
   }

   public final View getIconView() {
      return super.zzp("2003");
   }

   public final View getBodyView() {
      return super.zzp("2004");
   }

   public final View getStoreView() {
      return super.zzp("2005");
   }

   public final View getPriceView() {
      return super.zzp("2006");
   }

   public final View getImageView() {
      return super.zzp("2007");
   }

   public final View getStarRatingView() {
      return super.zzp("2008");
   }

   public final MediaView getMediaView() {
      View var1;
      if ((var1 = super.zzp("2011")) instanceof MediaView) {
         return (MediaView)var1;
      } else {
         if (var1 != null) {
            zzajc.zzaC("View is not an instance of MediaView");
         }

         return null;
      }
   }
}
