package com.google.android.gms.ads.formats;

import android.os.Bundle;
import com.google.android.gms.ads.VideoController;
import java.util.List;

public abstract class NativeContentAd extends NativeAd {
   public abstract CharSequence getHeadline();

   public abstract List getImages();

   public abstract CharSequence getBody();

   public abstract NativeAd.Image getLogo();

   public abstract CharSequence getCallToAction();

   public abstract CharSequence getAdvertiser();

   public abstract VideoController getVideoController();

   public abstract Bundle getExtras();

   public abstract void destroy();

   public interface OnContentAdLoadedListener {
      void onContentAdLoaded(NativeContentAd var1);
   }
}
