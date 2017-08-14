package com.google.android.gms.ads.formats;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzow;

public class NativeAdView extends FrameLayout {
   private final FrameLayout zzss;
   private final zzow zzst;

   public NativeAdView(Context var1) {
      super(var1);
      this.zzss = this.zzc(var1);
      this.zzst = this.zzah();
   }

   public NativeAdView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.zzss = this.zzc(var1);
      this.zzst = this.zzah();
   }

   public NativeAdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.zzss = this.zzc(var1);
      this.zzst = this.zzah();
   }

   @TargetApi(21)
   public NativeAdView(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      this.zzss = this.zzc(var1);
      this.zzst = this.zzah();
   }

   public void setAdChoicesView(AdChoicesView var1) {
      this.zza("1098", var1);
   }

   public AdChoicesView getAdChoicesView() {
      View var1;
      return (var1 = this.zzp("1098")) instanceof AdChoicesView ? (AdChoicesView)var1 : null;
   }

   protected final void zza(String var1, View var2) {
      try {
         this.zzst.zzd(var1, zzn.zzw(var2));
      } catch (RemoteException var4) {
         zzajc.zzb("Unable to call setAssetView on delegate", var4);
      }
   }

   protected final View zzp(String var1) {
      try {
         IObjectWrapper var2;
         if ((var2 = this.zzst.zzL(var1)) != null) {
            return (View)zzn.zzE(var2);
         }
      } catch (RemoteException var3) {
         zzajc.zzb("Unable to call getAssetView on delegate", var3);
      }

      return null;
   }

   public void setNativeAd(NativeAd var1) {
      try {
         this.zzst.zze((IObjectWrapper)var1.zzag());
      } catch (RemoteException var3) {
         zzajc.zzb("Unable to call setNativeAd on delegate", var3);
      }
   }

   public void destroy() {
      try {
         this.zzst.destroy();
      } catch (RemoteException var2) {
         zzajc.zzb("Unable to destroy native ad view", var2);
      }
   }

   private final FrameLayout zzc(Context var1) {
      FrameLayout var2;
      (var2 = new FrameLayout(var1)).setLayoutParams(new LayoutParams(-1, -1));
      this.addView(var2);
      return var2;
   }

   private final zzow zzah() {
      zzbo.zzb(this.zzss, "createDelegate must be called after mOverlayFrame has been created");
      return zzji.zzdt().zza((Context)this.zzss.getContext(), (FrameLayout)this, (FrameLayout)this.zzss);
   }

   public void addView(View var1, int var2, android.view.ViewGroup.LayoutParams var3) {
      super.addView(var1, var2, var3);
      super.bringChildToFront(this.zzss);
   }

   public void removeView(View var1) {
      if (this.zzss != var1) {
         super.removeView(var1);
      }
   }

   public void removeAllViews() {
      super.removeAllViews();
      super.addView(this.zzss);
   }

   public void bringChildToFront(View var1) {
      super.bringChildToFront(var1);
      if (this.zzss != var1) {
         super.bringChildToFront(this.zzss);
      }

   }

   public void onVisibilityChanged(View var1, int var2) {
      super.onVisibilityChanged(var1, var2);
      if (this.zzst != null) {
         try {
            this.zzst.zzb(zzn.zzw(var1), var2);
            return;
         } catch (RemoteException var4) {
            zzajc.zzb("Unable to call onVisibilityChanged on delegate", var4);
         }
      }

   }
}
