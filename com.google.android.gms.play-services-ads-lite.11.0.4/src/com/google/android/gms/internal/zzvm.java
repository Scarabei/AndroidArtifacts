package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzzn
public final class zzvm extends zzvg {
   private final NativeContentAdMapper zzNg;

   public zzvm(NativeContentAdMapper var1) {
      this.zzNg = var1;
   }

   public final String getHeadline() {
      return this.zzNg.getHeadline();
   }

   public final List getImages() {
      List var1;
      if ((var1 = this.zzNg.getImages()) == null) {
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
      return this.zzNg.getBody();
   }

   public final zzos zzem() {
      NativeAd.Image var1;
      return (var1 = this.zzNg.getLogo()) != null ? new zznp(var1.getDrawable(), var1.getUri(), var1.getScale()) : null;
   }

   public final String getCallToAction() {
      return this.zzNg.getCallToAction();
   }

   public final String getAdvertiser() {
      return this.zzNg.getAdvertiser();
   }

   public final void recordImpression() {
      this.zzNg.recordImpression();
   }

   public final void zzl(IObjectWrapper var1) {
      this.zzNg.handleClick((View)zzn.zzE(var1));
   }

   public final void zzm(IObjectWrapper var1) {
      this.zzNg.trackView((View)zzn.zzE(var1));
   }

   public final void zzn(IObjectWrapper var1) {
      this.zzNg.untrackView((View)zzn.zzE(var1));
   }

   public final boolean getOverrideImpressionRecording() {
      return this.zzNg.getOverrideImpressionRecording();
   }

   public final boolean getOverrideClickHandling() {
      return this.zzNg.getOverrideClickHandling();
   }

   public final Bundle getExtras() {
      return this.zzNg.getExtras();
   }

   public final IObjectWrapper zzfw() {
      View var1;
      return (var1 = this.zzNg.getAdChoicesContent()) == null ? null : zzn.zzw(var1);
   }

   public final zzks getVideoController() {
      return this.zzNg.getVideoController() != null ? this.zzNg.getVideoController().zzae() : null;
   }
}
