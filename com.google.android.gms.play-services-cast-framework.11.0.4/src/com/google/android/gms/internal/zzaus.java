package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.R.layout;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.internal.featurehighlight.zza;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzi;

public final class zzaus extends RelativeLayout implements IntroductoryOverlay {
   private final boolean zzasC;
   private Activity mActivity;
   private IntroductoryOverlay.OnOverlayDismissedListener zzaso;
   private View zzasl;
   private zza zzasD;
   private String zzasn;
   private boolean zzasE;
   private int mColor;

   @TargetApi(15)
   public zzaus(IntroductoryOverlay.Builder var1) {
      super(var1.getActivity());
      this.mActivity = var1.getActivity();
      this.zzasC = var1.zznD();
      this.zzaso = var1.zznB();
      this.zzasl = var1.zznA();
      this.zzasn = var1.zznE();
      this.mColor = var1.zznC();
   }

   private final void reset() {
      this.removeAllViews();
      this.mActivity = null;
      this.zzaso = null;
      this.zzasl = null;
      this.zzasD = null;
      this.zzasn = null;
      this.mColor = 0;
      this.zzasE = false;
   }

   public final void show() {
      if (this.mActivity != null && this.zzasl != null && !this.zzasE) {
         if (!zzao(this.mActivity)) {
            if (this.zzasC && IntroductoryOverlay.zza.zzam(this.mActivity)) {
               this.reset();
            } else {
               this.zzasD = new zza(this.mActivity);
               if (this.mColor != 0) {
                  this.zzasD.zzaa(this.mColor);
               }

               this.addView(this.zzasD);
               zzi var1;
               (var1 = (zzi)this.mActivity.getLayoutInflater().inflate(layout.cast_help_text, this.zzasD, false)).setText(this.zzasn, (CharSequence)null);
               this.zzasD.zza(var1);
               this.zzasD.zza(this.zzasl, (View)null, true, new zzaut(this));
               this.zzasE = true;
               ((ViewGroup)this.mActivity.getWindow().getDecorView()).addView(this);
               this.zzasD.zzg((Runnable)null);
            }
         }
      }
   }

   public final void remove() {
      if (this.zzasE) {
         ((ViewGroup)this.mActivity.getWindow().getDecorView()).removeView(this);
         this.reset();
      }

   }

   protected final void onDetachedFromWindow() {
      this.reset();
      super.onDetachedFromWindow();
   }

   static boolean zzao(Context var0) {
      AccessibilityManager var1;
      return (var1 = (AccessibilityManager)var0.getSystemService("accessibility")) != null && var1.isEnabled() && var1.isTouchExplorationEnabled();
   }

   // $FF: synthetic method
   static boolean zza(zzaus var0) {
      return var0.zzasE;
   }

   // $FF: synthetic method
   static Activity zzb(zzaus var0) {
      return var0.mActivity;
   }

   // $FF: synthetic method
   static IntroductoryOverlay.OnOverlayDismissedListener zzc(zzaus var0) {
      return var0.zzaso;
   }

   // $FF: synthetic method
   static void zzd(zzaus var0) {
      var0.reset();
   }

   // $FF: synthetic method
   static zza zze(zzaus var0) {
      return var0.zzasD;
   }
}
