package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.internal.zzzn;

@zzzn
public class NativeAdMapper {
   protected boolean mOverrideImpressionRecording;
   protected boolean mOverrideClickHandling;
   protected Bundle mExtras = new Bundle();
   protected View mAdChoicesContent;

   public final void setOverrideImpressionRecording(boolean var1) {
      this.mOverrideImpressionRecording = var1;
   }

   public final void setOverrideClickHandling(boolean var1) {
      this.mOverrideClickHandling = var1;
   }

   public final void setExtras(Bundle var1) {
      this.mExtras = var1;
   }

   public void setAdChoicesContent(View var1) {
      this.mAdChoicesContent = var1;
   }

   public final boolean getOverrideImpressionRecording() {
      return this.mOverrideImpressionRecording;
   }

   public final boolean getOverrideClickHandling() {
      return this.mOverrideClickHandling;
   }

   public final Bundle getExtras() {
      return this.mExtras;
   }

   public View getAdChoicesContent() {
      return this.mAdChoicesContent;
   }

   public void trackView(View var1) {
   }

   public void untrackView(View var1) {
   }

   public void recordImpression() {
   }

   public void handleClick(View var1) {
   }
}
