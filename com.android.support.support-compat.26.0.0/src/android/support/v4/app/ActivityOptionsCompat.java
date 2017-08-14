package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;
import android.view.View;

public class ActivityOptionsCompat {
   public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
   public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

   public static ActivityOptionsCompat makeCustomAnimation(Context context, int enterResId, int exitResId) {
      return VERSION.SDK_INT >= 16 ? createImpl(ActivityOptions.makeCustomAnimation(context, enterResId, exitResId)) : new ActivityOptionsCompat();
   }

   public static ActivityOptionsCompat makeScaleUpAnimation(View source, int startX, int startY, int startWidth, int startHeight) {
      return VERSION.SDK_INT >= 16 ? createImpl(ActivityOptions.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight)) : new ActivityOptionsCompat();
   }

   public static ActivityOptionsCompat makeClipRevealAnimation(View source, int startX, int startY, int width, int height) {
      return VERSION.SDK_INT >= 23 ? createImpl(ActivityOptions.makeClipRevealAnimation(source, startX, startY, width, height)) : new ActivityOptionsCompat();
   }

   public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View source, Bitmap thumbnail, int startX, int startY) {
      return VERSION.SDK_INT >= 16 ? createImpl(ActivityOptions.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY)) : new ActivityOptionsCompat();
   }

   public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, View sharedElement, String sharedElementName) {
      return VERSION.SDK_INT >= 21 ? createImpl(ActivityOptions.makeSceneTransitionAnimation(activity, sharedElement, sharedElementName)) : new ActivityOptionsCompat();
   }

   public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair... sharedElements) {
      if (VERSION.SDK_INT < 21) {
         return new ActivityOptionsCompat();
      } else {
         android.util.Pair[] pairs = null;
         if (sharedElements != null) {
            pairs = new android.util.Pair[sharedElements.length];

            for(int i = 0; i < sharedElements.length; ++i) {
               pairs[i] = android.util.Pair.create(sharedElements[i].first, sharedElements[i].second);
            }
         }

         return createImpl(ActivityOptions.makeSceneTransitionAnimation(activity, pairs));
      }
   }

   public static ActivityOptionsCompat makeTaskLaunchBehind() {
      return VERSION.SDK_INT >= 21 ? createImpl(ActivityOptions.makeTaskLaunchBehind()) : new ActivityOptionsCompat();
   }

   public static ActivityOptionsCompat makeBasic() {
      return VERSION.SDK_INT >= 23 ? createImpl(ActivityOptions.makeBasic()) : new ActivityOptionsCompat();
   }

   @RequiresApi(16)
   private static ActivityOptionsCompat createImpl(ActivityOptions options) {
      if (VERSION.SDK_INT >= 24) {
         return new ActivityOptionsCompat.ActivityOptionsCompatApi24Impl(options);
      } else {
         return (ActivityOptionsCompat)(VERSION.SDK_INT >= 23 ? new ActivityOptionsCompat.ActivityOptionsCompatApi23Impl(options) : new ActivityOptionsCompat.ActivityOptionsCompatApi16Impl(options));
      }
   }

   public ActivityOptionsCompat setLaunchBounds(@Nullable Rect screenSpacePixelRect) {
      return null;
   }

   @Nullable
   public Rect getLaunchBounds() {
      return null;
   }

   public Bundle toBundle() {
      return null;
   }

   public void update(ActivityOptionsCompat otherOptions) {
   }

   public void requestUsageTimeReport(PendingIntent receiver) {
   }

   @RequiresApi(24)
   private static class ActivityOptionsCompatApi24Impl extends ActivityOptionsCompat.ActivityOptionsCompatApi23Impl {
      ActivityOptionsCompatApi24Impl(ActivityOptions activityOptions) {
         super(activityOptions);
      }

      public ActivityOptionsCompat setLaunchBounds(@Nullable Rect screenSpacePixelRect) {
         return new ActivityOptionsCompat.ActivityOptionsCompatApi24Impl(this.mActivityOptions.setLaunchBounds(screenSpacePixelRect));
      }

      public Rect getLaunchBounds() {
         return this.mActivityOptions.getLaunchBounds();
      }
   }

   @RequiresApi(23)
   private static class ActivityOptionsCompatApi23Impl extends ActivityOptionsCompat.ActivityOptionsCompatApi16Impl {
      ActivityOptionsCompatApi23Impl(ActivityOptions activityOptions) {
         super(activityOptions);
      }

      public void requestUsageTimeReport(PendingIntent receiver) {
         this.mActivityOptions.requestUsageTimeReport(receiver);
      }
   }

   @RequiresApi(16)
   private static class ActivityOptionsCompatApi16Impl extends ActivityOptionsCompat {
      protected final ActivityOptions mActivityOptions;

      ActivityOptionsCompatApi16Impl(ActivityOptions activityOptions) {
         this.mActivityOptions = activityOptions;
      }

      public Bundle toBundle() {
         return this.mActivityOptions.toBundle();
      }

      public void update(ActivityOptionsCompat otherOptions) {
         if (otherOptions instanceof ActivityOptionsCompat.ActivityOptionsCompatApi16Impl) {
            ActivityOptionsCompat.ActivityOptionsCompatApi16Impl otherImpl = (ActivityOptionsCompat.ActivityOptionsCompatApi16Impl)otherOptions;
            this.mActivityOptions.update(otherImpl.mActivityOptions);
         }

      }
   }
}
