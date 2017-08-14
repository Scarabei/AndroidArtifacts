package com.google.android.gms.cast.framework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.v7.app.MediaRouteButton;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzaus;
import com.google.android.gms.internal.zzauw;

public interface IntroductoryOverlay {
   void show();

   void remove();

   public static class Builder {
      private Activity mActivity;
      private View zzasl;
      private int zzasm;
      private String zzasn;
      private IntroductoryOverlay.OnOverlayDismissedListener zzaso;
      private boolean zzasp;
      private float zzasq;
      private String zzasr;

      @TargetApi(11)
      public Builder(Activity var1, MenuItem var2) {
         this.mActivity = (Activity)zzbo.zzu(var1);
         this.zzasl = ((MenuItem)zzbo.zzu(var2)).getActionView();
      }

      public Builder(Activity var1, MediaRouteButton var2) {
         this.mActivity = (Activity)zzbo.zzu(var1);
         this.zzasl = (View)zzbo.zzu(var2);
      }

      public IntroductoryOverlay.Builder setOverlayColor(@ColorRes int var1) {
         this.zzasm = this.mActivity.getResources().getColor(var1);
         return this;
      }

      public IntroductoryOverlay.Builder setTitleText(String var1) {
         this.zzasn = var1;
         return this;
      }

      public IntroductoryOverlay.Builder setTitleText(@StringRes int var1) {
         this.zzasn = this.mActivity.getResources().getString(var1);
         return this;
      }

      public IntroductoryOverlay.Builder setOnOverlayDismissedListener(IntroductoryOverlay.OnOverlayDismissedListener var1) {
         this.zzaso = var1;
         return this;
      }

      public IntroductoryOverlay.Builder setSingleTime() {
         this.zzasp = true;
         return this;
      }

      public IntroductoryOverlay.Builder setFocusRadiusId(@DimenRes int var1) {
         this.zzasq = this.mActivity.getResources().getDimension(var1);
         return this;
      }

      public IntroductoryOverlay.Builder setFocusRadius(float var1) {
         this.zzasq = var1;
         return this;
      }

      public IntroductoryOverlay.Builder setButtonText(String var1) {
         this.zzasr = var1;
         return this;
      }

      public IntroductoryOverlay.Builder setButtonText(@StringRes int var1) {
         this.zzasr = this.mActivity.getResources().getString(var1);
         return this;
      }

      public final Activity getActivity() {
         return this.mActivity;
      }

      public final View zznA() {
         return this.zzasl;
      }

      public final IntroductoryOverlay.OnOverlayDismissedListener zznB() {
         return this.zzaso;
      }

      public final int zznC() {
         return this.zzasm;
      }

      public final boolean zznD() {
         return this.zzasp;
      }

      public final String zznE() {
         return this.zzasn;
      }

      public final String zznF() {
         return this.zzasr;
      }

      public final float zznG() {
         return this.zzasq;
      }

      public IntroductoryOverlay build() {
         return (IntroductoryOverlay)(com.google.android.gms.common.util.zzq.zzrZ() ? new zzaus(this) : new zzauw(this));
      }
   }

   public static class zza {
      public static void zzal(Context var0) {
         PreferenceManager.getDefaultSharedPreferences(var0).edit().putBoolean("googlecast-introOverlayShown", true).apply();
      }

      public static boolean zzam(Context var0) {
         return PreferenceManager.getDefaultSharedPreferences(var0).getBoolean("googlecast-introOverlayShown", false);
      }
   }

   public interface OnOverlayDismissedListener {
      void onOverlayDismissed();
   }
}
