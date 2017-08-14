package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.internal.zzajc;

public final class NativeContentAdView extends NativeAdView {
   public NativeContentAdView(Context var1) {
      super(var1);
   }

   public NativeContentAdView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public NativeContentAdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public NativeContentAdView(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public final void setHeadlineView(View var1) {
      super.zza("1001", var1);
   }

   public final void setBodyView(View var1) {
      super.zza("1002", var1);
   }

   public final void setCallToActionView(View var1) {
      super.zza("1003", var1);
   }

   public final void setAdvertiserView(View var1) {
      super.zza("1004", var1);
   }

   public final void setImageView(View var1) {
      super.zza("1005", var1);
   }

   public final void setLogoView(View var1) {
      super.zza("1006", var1);
   }

   public final void setMediaView(MediaView var1) {
      super.zza("1009", var1);
   }

   public final View getHeadlineView() {
      return super.zzp("1001");
   }

   public final View getBodyView() {
      return super.zzp("1002");
   }

   public final View getCallToActionView() {
      return super.zzp("1003");
   }

   public final View getAdvertiserView() {
      return super.zzp("1004");
   }

   public final View getImageView() {
      return super.zzp("1005");
   }

   public final View getLogoView() {
      return super.zzp("1006");
   }

   public final MediaView getMediaView() {
      View var1;
      if ((var1 = super.zzp("1009")) instanceof MediaView) {
         return (MediaView)var1;
      } else {
         if (var1 != null) {
            zzajc.zzaC("View is not an instance of MediaView");
         }

         return null;
      }
   }
}
