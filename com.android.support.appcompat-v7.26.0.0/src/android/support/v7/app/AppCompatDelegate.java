package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class AppCompatDelegate {
   static final String TAG = "AppCompatDelegate";
   public static final int MODE_NIGHT_NO = 1;
   public static final int MODE_NIGHT_YES = 2;
   public static final int MODE_NIGHT_AUTO = 0;
   public static final int MODE_NIGHT_FOLLOW_SYSTEM = -1;
   static final int MODE_NIGHT_UNSPECIFIED = -100;
   private static int sDefaultNightMode = -1;
   private static boolean sCompatVectorFromResourcesEnabled = false;
   public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
   public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;
   public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

   public static AppCompatDelegate create(Activity activity, AppCompatCallback callback) {
      return create(activity, activity.getWindow(), callback);
   }

   public static AppCompatDelegate create(Dialog dialog, AppCompatCallback callback) {
      return create(dialog.getContext(), dialog.getWindow(), callback);
   }

   private static AppCompatDelegate create(Context context, Window window, AppCompatCallback callback) {
      if (VERSION.SDK_INT >= 24) {
         return new AppCompatDelegateImplN(context, window, callback);
      } else if (VERSION.SDK_INT >= 23) {
         return new AppCompatDelegateImplV23(context, window, callback);
      } else if (VERSION.SDK_INT >= 14) {
         return new AppCompatDelegateImplV14(context, window, callback);
      } else {
         return (AppCompatDelegate)(VERSION.SDK_INT >= 11 ? new AppCompatDelegateImplV11(context, window, callback) : new AppCompatDelegateImplV9(context, window, callback));
      }
   }

   @Nullable
   public abstract ActionBar getSupportActionBar();

   public abstract void setSupportActionBar(@Nullable Toolbar var1);

   public abstract MenuInflater getMenuInflater();

   public abstract void onCreate(Bundle var1);

   public abstract void onPostCreate(Bundle var1);

   public abstract void onConfigurationChanged(Configuration var1);

   public abstract void onStart();

   public abstract void onStop();

   public abstract void onPostResume();

   @Nullable
   public abstract View findViewById(@IdRes int var1);

   public abstract void setContentView(View var1);

   public abstract void setContentView(@LayoutRes int var1);

   public abstract void setContentView(View var1, LayoutParams var2);

   public abstract void addContentView(View var1, LayoutParams var2);

   public abstract void setTitle(@Nullable CharSequence var1);

   public abstract void invalidateOptionsMenu();

   public abstract void onDestroy();

   @Nullable
   public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();

   public abstract boolean requestWindowFeature(int var1);

   public abstract boolean hasWindowFeature(int var1);

   @Nullable
   public abstract ActionMode startSupportActionMode(@NonNull ActionMode.Callback var1);

   public abstract void installViewFactory();

   public abstract View createView(@Nullable View var1, String var2, @NonNull Context var3, @NonNull AttributeSet var4);

   public abstract void setHandleNativeActionModesEnabled(boolean var1);

   public abstract boolean isHandleNativeActionModesEnabled();

   public abstract void onSaveInstanceState(Bundle var1);

   public abstract boolean applyDayNight();

   public abstract void setLocalNightMode(int var1);

   public static void setDefaultNightMode(int mode) {
      switch(mode) {
      case -1:
      case 0:
      case 1:
      case 2:
         sDefaultNightMode = mode;
         break;
      default:
         Log.d("AppCompatDelegate", "setDefaultNightMode() called with an unknown mode");
      }

   }

   public static int getDefaultNightMode() {
      return sDefaultNightMode;
   }

   public static void setCompatVectorFromResourcesEnabled(boolean enabled) {
      sCompatVectorFromResourcesEnabled = enabled;
   }

   public static boolean isCompatVectorFromResourcesEnabled() {
      return sCompatVectorFromResourcesEnabled;
   }

   @Retention(RetentionPolicy.SOURCE)
   @interface ApplyableNightMode {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface NightMode {
   }
}
