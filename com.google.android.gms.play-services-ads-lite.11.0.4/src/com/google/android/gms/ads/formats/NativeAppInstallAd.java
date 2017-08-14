package com.google.android.gms.ads.formats;

import android.os.Bundle;
import com.google.android.gms.ads.VideoController;
import java.util.List;

public abstract class NativeAppInstallAd extends NativeAd {
   public abstract CharSequence getHeadline();

   public abstract List getImages();

   public abstract CharSequence getBody();

   public abstract NativeAd.Image getIcon();

   public abstract CharSequence getCallToAction();

   public abstract Double getStarRating();

   public abstract CharSequence getStore();

   public abstract CharSequence getPrice();

   public abstract VideoController getVideoController();

   public abstract Bundle getExtras();

   public abstract void destroy();

   public interface OnAppInstallAdLoadedListener {
      void onAppInstallAdLoaded(NativeAppInstallAd var1);
   }
}
