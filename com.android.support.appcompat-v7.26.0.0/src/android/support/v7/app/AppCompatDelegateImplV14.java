package android.support.v7.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v7.view.SupportActionModeWrapper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(14)
class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11 {
   private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
   private int mLocalNightMode = -100;
   private boolean mApplyDayNightCalled;
   private boolean mHandleNativeActionModes = true;
   private AppCompatDelegateImplV14.AutoNightModeManager mAutoNightModeManager;

   AppCompatDelegateImplV14(Context context, Window window, AppCompatCallback callback) {
      super(context, window, callback);
   }

   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (savedInstanceState != null && this.mLocalNightMode == -100) {
         this.mLocalNightMode = savedInstanceState.getInt("appcompat:local_night_mode", -100);
      }

   }

   Callback wrapWindowCallback(Callback callback) {
      return new AppCompatDelegateImplV14.AppCompatWindowCallbackV14(callback);
   }

   public void setHandleNativeActionModesEnabled(boolean enabled) {
      this.mHandleNativeActionModes = enabled;
   }

   public boolean isHandleNativeActionModesEnabled() {
      return this.mHandleNativeActionModes;
   }

   public boolean applyDayNight() {
      boolean applied = false;
      int nightMode = this.getNightMode();
      int modeToApply = this.mapNightMode(nightMode);
      if (modeToApply != -1) {
         applied = this.updateForNightMode(modeToApply);
      }

      if (nightMode == 0) {
         this.ensureAutoNightModeManager();
         this.mAutoNightModeManager.setup();
      }

      this.mApplyDayNightCalled = true;
      return applied;
   }

   public void onStart() {
      super.onStart();
      this.applyDayNight();
   }

   public void onStop() {
      super.onStop();
      if (this.mAutoNightModeManager != null) {
         this.mAutoNightModeManager.cleanup();
      }

   }

   public void setLocalNightMode(int mode) {
      switch(mode) {
      case -1:
      case 0:
      case 1:
      case 2:
         if (this.mLocalNightMode != mode) {
            this.mLocalNightMode = mode;
            if (this.mApplyDayNightCalled) {
               this.applyDayNight();
            }
         }
         break;
      default:
         Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
      }

   }

   int mapNightMode(int mode) {
      switch(mode) {
      case -100:
         return -1;
      case 0:
         this.ensureAutoNightModeManager();
         return this.mAutoNightModeManager.getApplyableNightMode();
      default:
         return mode;
      }
   }

   private int getNightMode() {
      return this.mLocalNightMode != -100 ? this.mLocalNightMode : getDefaultNightMode();
   }

   public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      if (this.mLocalNightMode != -100) {
         outState.putInt("appcompat:local_night_mode", this.mLocalNightMode);
      }

   }

   public void onDestroy() {
      super.onDestroy();
      if (this.mAutoNightModeManager != null) {
         this.mAutoNightModeManager.cleanup();
      }

   }

   private boolean updateForNightMode(int mode) {
      Resources res = this.mContext.getResources();
      Configuration conf = res.getConfiguration();
      int currentNightMode = conf.uiMode & 48;
      int newNightMode = mode == 2 ? 32 : 16;
      if (currentNightMode != newNightMode) {
         if (this.shouldRecreateOnNightModeChange()) {
            Activity activity = (Activity)this.mContext;
            activity.recreate();
         } else {
            Configuration config = new Configuration(conf);
            DisplayMetrics metrics = res.getDisplayMetrics();
            config.uiMode = newNightMode | config.uiMode & -49;
            res.updateConfiguration(config, metrics);
            if (VERSION.SDK_INT < 26) {
               ResourcesFlusher.flush(res);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private void ensureAutoNightModeManager() {
      if (this.mAutoNightModeManager == null) {
         this.mAutoNightModeManager = new AppCompatDelegateImplV14.AutoNightModeManager(TwilightManager.getInstance(this.mContext));
      }

   }

   @VisibleForTesting
   final AppCompatDelegateImplV14.AutoNightModeManager getAutoNightModeManager() {
      this.ensureAutoNightModeManager();
      return this.mAutoNightModeManager;
   }

   private boolean shouldRecreateOnNightModeChange() {
      if (this.mApplyDayNightCalled && this.mContext instanceof Activity) {
         PackageManager pm = this.mContext.getPackageManager();

         try {
            ActivityInfo info = pm.getActivityInfo(new ComponentName(this.mContext, this.mContext.getClass()), 0);
            return (info.configChanges & 512) == 0;
         } catch (NameNotFoundException var3) {
            Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", var3);
            return true;
         }
      } else {
         return false;
      }
   }

   @VisibleForTesting
   final class AutoNightModeManager {
      private TwilightManager mTwilightManager;
      private boolean mIsNight;
      private BroadcastReceiver mAutoTimeChangeReceiver;
      private IntentFilter mAutoTimeChangeReceiverFilter;

      AutoNightModeManager(@NonNull TwilightManager twilightManager) {
         this.mTwilightManager = twilightManager;
         this.mIsNight = twilightManager.isNight();
      }

      final int getApplyableNightMode() {
         this.mIsNight = this.mTwilightManager.isNight();
         return this.mIsNight ? 2 : 1;
      }

      final void dispatchTimeChanged() {
         boolean isNight = this.mTwilightManager.isNight();
         if (isNight != this.mIsNight) {
            this.mIsNight = isNight;
            AppCompatDelegateImplV14.this.applyDayNight();
         }

      }

      final void setup() {
         this.cleanup();
         if (this.mAutoTimeChangeReceiver == null) {
            this.mAutoTimeChangeReceiver = new BroadcastReceiver() {
               public void onReceive(Context context, Intent intent) {
                  AutoNightModeManager.this.dispatchTimeChanged();
               }
            };
         }

         if (this.mAutoTimeChangeReceiverFilter == null) {
            this.mAutoTimeChangeReceiverFilter = new IntentFilter();
            this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
            this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            this.mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
         }

         AppCompatDelegateImplV14.this.mContext.registerReceiver(this.mAutoTimeChangeReceiver, this.mAutoTimeChangeReceiverFilter);
      }

      final void cleanup() {
         if (this.mAutoTimeChangeReceiver != null) {
            AppCompatDelegateImplV14.this.mContext.unregisterReceiver(this.mAutoTimeChangeReceiver);
            this.mAutoTimeChangeReceiver = null;
         }

      }
   }

   class AppCompatWindowCallbackV14 extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase {
      AppCompatWindowCallbackV14(Callback callback) {
         super(callback);
      }

      public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback) {
         return AppCompatDelegateImplV14.this.isHandleNativeActionModesEnabled() ? this.startAsSupportActionMode(callback) : super.onWindowStartingActionMode(callback);
      }

      final ActionMode startAsSupportActionMode(android.view.ActionMode.Callback callback) {
         SupportActionModeWrapper.CallbackWrapper callbackWrapper = new SupportActionModeWrapper.CallbackWrapper(AppCompatDelegateImplV14.this.mContext, callback);
         android.support.v7.view.ActionMode supportActionMode = AppCompatDelegateImplV14.this.startSupportActionMode(callbackWrapper);
         return supportActionMode != null ? callbackWrapper.getActionModeWrapper(supportActionMode) : null;
      }
   }
}
