package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzzn
public final class zzvl extends zzvd {
   private final NativeAppInstallAdMapper zzNf;

   public zzvl(NativeAppInstallAdMapper var1) {
      this.zzNf = var1;
   }

   public final String getHeadline() {
      return this.zzNf.getHeadline();
   }

   public final List getImages() {
      List var1;
      if ((var1 = this.zzNf.getImages()) == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            NativeAd.Image var4 = (NativeAd.Image)var3.next();
            var2.add(new zznp(var4.getDrawable(), var4.getUri(), var4.getScale()));
         }

         return var2;
      }
   }

   public final String getBody() {
      return this.zzNf.getBody();
   }

   public final zzos zzeh() {
      NativeAd.Image var1;
      return (var1 = this.zzNf.getIcon()) != null ? new zznp(var1.getDrawable(), var1.getUri(), var1.getScale()) : null;
   }

   public final String getCallToAction() {
      return this.zzNf.getCallToAction();
   }

   public final double getStarRating() {
      return this.zzNf.getStarRating();
   }

   public final String getStore() {
      return this.zzNf.getStore();
   }

   public final String getPrice() {
      return this.zzNf.getPrice();
   }

   public final void recordImpression() {
      this.zzNf.recordImpression();
   }

   public final void zzl(IObjectWrapper var1) {
      this.zzNf.handleClick((View)zzn.zzE(var1));
   }

   public final void zzm(IObjectWrapper var1) {
      this.zzNf.trackView((View)zzn.zzE(var1));
   }

   public final void zzn(IObjectWrapper var1) {
      this.zzNf.untrackView((View)zzn.zzE(var1));
   }

   public final boolean getOverrideImpressionRecording() {
      return this.zzNf.getOverrideImpressionRecording();
   }

   public final boolean getOverrideClickHandling() {
      return this.zzNf.getOverrideClickHandling();
   }

   public final Bundle getExtras() {
      return this.zzNf.getExtras();
   }

   public final zzks getVideoController() {
      return this.zzNf.getVideoController() != null ? this.zzNf.getVideoController().zzae() : null;
   }

   public final IObjectWrapper zzfw() {
      View var1;
      return (var1 = this.zzNf.getAdChoicesContent()) == null ? null : zzn.zzw(var1);
   }
}
