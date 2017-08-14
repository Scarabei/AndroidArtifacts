package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback.OnSharedElementsReadyListener;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.ContextCompat;
import android.view.View;
import java.util.List;
import java.util.Map;

public class ActivityCompat extends ContextCompat {
   public static boolean invalidateOptionsMenu(Activity activity) {
      activity.invalidateOptionsMenu();
      return true;
   }

   public static void startActivityForResult(Activity activity, Intent intent, int requestCode, @Nullable Bundle options) {
      if (VERSION.SDK_INT >= 16) {
         activity.startActivityForResult(intent, requestCode, options);
      } else {
         activity.startActivityForResult(intent, requestCode);
      }

   }

   public static void startIntentSenderForResult(Activity activity, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws SendIntentException {
      if (VERSION.SDK_INT >= 16) {
         activity.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
      } else {
         activity.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
      }

   }

   public static void finishAffinity(Activity activity) {
      if (VERSION.SDK_INT >= 16) {
         activity.finishAffinity();
      } else {
         activity.finish();
      }

   }

   public static void finishAfterTransition(Activity activity) {
      if (VERSION.SDK_INT >= 21) {
         activity.finishAfterTransition();
      } else {
         activity.finish();
      }

   }

   @Nullable
   public static Uri getReferrer(Activity activity) {
      if (VERSION.SDK_INT >= 22) {
         return activity.getReferrer();
      } else {
         Intent intent = activity.getIntent();
         Uri referrer = (Uri)intent.getParcelableExtra("android.intent.extra.REFERRER");
         if (referrer != null) {
            return referrer;
         } else {
            String referrerName = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
            return referrerName != null ? Uri.parse(referrerName) : null;
         }
      }
   }

   public static void setEnterSharedElementCallback(Activity activity, SharedElementCallback callback) {
      if (VERSION.SDK_INT >= 23) {
         android.app.SharedElementCallback frameworkCallback = callback != null ? new ActivityCompat.SharedElementCallback23Impl(callback) : null;
         activity.setEnterSharedElementCallback(frameworkCallback);
      } else if (VERSION.SDK_INT >= 21) {
         android.app.SharedElementCallback frameworkCallback = callback != null ? new ActivityCompat.SharedElementCallback21Impl(callback) : null;
         activity.setEnterSharedElementCallback(frameworkCallback);
      }

   }

   public static void setExitSharedElementCallback(Activity activity, SharedElementCallback callback) {
      if (VERSION.SDK_INT >= 23) {
         android.app.SharedElementCallback frameworkCallback = callback != null ? new ActivityCompat.SharedElementCallback23Impl(callback) : null;
         activity.setExitSharedElementCallback(frameworkCallback);
      } else if (VERSION.SDK_INT >= 21) {
         android.app.SharedElementCallback frameworkCallback = callback != null ? new ActivityCompat.SharedElementCallback21Impl(callback) : null;
         activity.setExitSharedElementCallback(frameworkCallback);
      }

   }

   public static void postponeEnterTransition(Activity activity) {
      if (VERSION.SDK_INT >= 21) {
         activity.postponeEnterTransition();
      }

   }

   public static void startPostponedEnterTransition(Activity activity) {
      if (VERSION.SDK_INT >= 21) {
         activity.startPostponedEnterTransition();
      }

   }

   public static void requestPermissions(@NonNull final Activity activity, @NonNull final String[] permissions, @IntRange(from = 0L) final int requestCode) {
      if (VERSION.SDK_INT >= 23) {
         if (activity instanceof ActivityCompat.RequestPermissionsRequestCodeValidator) {
            ((ActivityCompat.RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(requestCode);
         }

         activity.requestPermissions(permissions, requestCode);
      } else if (activity instanceof ActivityCompat.OnRequestPermissionsResultCallback) {
         Handler handler = new Handler(Looper.getMainLooper());
         handler.post(new Runnable() {
            public void run() {
               int[] grantResults = new int[permissions.length];
               PackageManager packageManager = activity.getPackageManager();
               String packageName = activity.getPackageName();
               int permissionCount = permissions.length;

               for(int i = 0; i < permissionCount; ++i) {
                  grantResults[i] = packageManager.checkPermission(permissions[i], packageName);
               }

               ((ActivityCompat.OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
         });
      }

   }

   public static boolean shouldShowRequestPermissionRationale(@NonNull Activity activity, @NonNull String permission) {
      return VERSION.SDK_INT >= 23 ? activity.shouldShowRequestPermissionRationale(permission) : false;
   }

   @RequiresApi(23)
   private static class SharedElementCallback23Impl extends ActivityCompat.SharedElementCallback21Impl {
      public SharedElementCallback23Impl(SharedElementCallback callback) {
         super(callback);
      }

      public void onSharedElementsArrived(List sharedElementNames, List sharedElements, final OnSharedElementsReadyListener listener) {
         this.mCallback.onSharedElementsArrived(sharedElementNames, sharedElements, new SharedElementCallback.OnSharedElementsReadyListener() {
            public void onSharedElementsReady() {
               listener.onSharedElementsReady();
            }
         });
      }
   }

   @RequiresApi(21)
   private static class SharedElementCallback21Impl extends android.app.SharedElementCallback {
      protected SharedElementCallback mCallback;

      public SharedElementCallback21Impl(SharedElementCallback callback) {
         this.mCallback = callback;
      }

      public void onSharedElementStart(List sharedElementNames, List sharedElements, List sharedElementSnapshots) {
         this.mCallback.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
      }

      public void onSharedElementEnd(List sharedElementNames, List sharedElements, List sharedElementSnapshots) {
         this.mCallback.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
      }

      public void onRejectSharedElements(List rejectedSharedElements) {
         this.mCallback.onRejectSharedElements(rejectedSharedElements);
      }

      public void onMapSharedElements(List names, Map sharedElements) {
         this.mCallback.onMapSharedElements(names, sharedElements);
      }

      public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
         return this.mCallback.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
      }

      public View onCreateSnapshotView(Context context, Parcelable snapshot) {
         return this.mCallback.onCreateSnapshotView(context, snapshot);
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public interface RequestPermissionsRequestCodeValidator {
      void validateRequestPermissionsRequestCode(int var1);
   }

   public interface OnRequestPermissionsResultCallback {
      void onRequestPermissionsResult(int var1, @NonNull String[] var2, @NonNull int[] var3);
   }
}
