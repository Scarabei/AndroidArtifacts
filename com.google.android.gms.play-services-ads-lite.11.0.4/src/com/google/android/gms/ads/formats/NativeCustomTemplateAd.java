package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.VideoController;
import java.util.List;

public interface NativeCustomTemplateAd {
   String ASSET_NAME_VIDEO = "_videoMediaView";

   CharSequence getText(String var1);

   NativeAd.Image getImage(String var1);

   VideoController getVideoController();

   MediaView getVideoMediaView();

   List getAvailableAssetNames();

   String getCustomTemplateId();

   void performClick(String var1);

   void recordImpression();

   void destroy();

   public interface OnCustomClickListener {
      void onCustomClick(NativeCustomTemplateAd var1, String var2);
   }

   public interface OnCustomTemplateAdLoadedListener {
      void onCustomTemplateAdLoaded(NativeCustomTemplateAd var1);
   }
}
