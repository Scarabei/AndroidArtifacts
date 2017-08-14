package android.support.v7.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.TaskStackBuilder.SupportParentable;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;

public class AppCompatActivity extends FragmentActivity implements AppCompatCallback, SupportParentable, ActionBarDrawerToggle.DelegateProvider {
   private AppCompatDelegate mDelegate;
   private int mThemeId = 0;
   private Resources mResources;

   protected void onCreate(@Nullable Bundle savedInstanceState) {
      AppCompatDelegate delegate = this.getDelegate();
      delegate.installViewFactory();
      delegate.onCreate(savedInstanceState);
      if (delegate.applyDayNight() && this.mThemeId != 0) {
         if (VERSION.SDK_INT >= 23) {
            this.onApplyThemeResource(this.getTheme(), this.mThemeId, false);
         } else {
            this.setTheme(this.mThemeId);
         }
      }

      super.onCreate(savedInstanceState);
   }

   public void setTheme(@StyleRes int resid) {
      super.setTheme(resid);
      this.mThemeId = resid;
   }

   protected void onPostCreate(@Nullable Bundle savedInstanceState) {
      super.onPostCreate(savedInstanceState);
      this.getDelegate().onPostCreate(savedInstanceState);
   }

   @Nullable
   public ActionBar getSupportActionBar() {
      return this.getDelegate().getSupportActionBar();
   }

   public void setSupportActionBar(@Nullable Toolbar toolbar) {
      this.getDelegate().setSupportActionBar(toolbar);
   }

   public MenuInflater getMenuInflater() {
      return this.getDelegate().getMenuInflater();
   }

   public void setContentView(@LayoutRes int layoutResID) {
      this.getDelegate().setContentView(layoutResID);
   }

   public void setContentView(View view) {
      this.getDelegate().setContentView(view);
   }

   public void setContentView(View view, LayoutParams params) {
      this.getDelegate().setContentView(view, params);
   }

   public void addContentView(View view, LayoutParams params) {
      this.getDelegate().addContentView(view, params);
   }

   public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      this.getDelegate().onConfigurationChanged(newConfig);
      if (this.mResources != null) {
         DisplayMetrics newMetrics = super.getResources().getDisplayMetrics();
         this.mResources.updateConfiguration(newConfig, newMetrics);
      }

   }

   protected void onPostResume() {
      super.onPostResume();
      this.getDelegate().onPostResume();
   }

   protected void onStart() {
      super.onStart();
      this.getDelegate().onStart();
   }

   protected void onStop() {
      super.onStop();
      this.getDelegate().onStop();
   }

   public View findViewById(@IdRes int id) {
      return this.getDelegate().findViewById(id);
   }

   public final boolean onMenuItemSelected(int featureId, MenuItem item) {
      if (super.onMenuItemSelected(featureId, item)) {
         return true;
      } else {
         ActionBar ab = this.getSupportActionBar();
         return item.getItemId() == 16908332 && ab != null && (ab.getDisplayOptions() & 4) != 0 ? this.onSupportNavigateUp() : false;
      }
   }

   protected void onDestroy() {
      super.onDestroy();
      this.getDelegate().onDestroy();
   }

   protected void onTitleChanged(CharSequence title, int color) {
      super.onTitleChanged(title, color);
      this.getDelegate().setTitle(title);
   }

   public boolean supportRequestWindowFeature(int featureId) {
      return this.getDelegate().requestWindowFeature(featureId);
   }

   public void supportInvalidateOptionsMenu() {
      this.getDelegate().invalidateOptionsMenu();
   }

   public void invalidateOptionsMenu() {
      this.getDelegate().invalidateOptionsMenu();
   }

   @CallSuper
   public void onSupportActionModeStarted(@NonNull ActionMode mode) {
   }

   @CallSuper
   public void onSupportActionModeFinished(@NonNull ActionMode mode) {
   }

   @Nullable
   public ActionMode onWindowStartingSupportActionMode(@NonNull ActionMode.Callback callback) {
      return null;
   }

   @Nullable
   public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
      return this.getDelegate().startSupportActionMode(callback);
   }

   /** @deprecated */
   @Deprecated
   public void setSupportProgressBarVisibility(boolean visible) {
   }

   /** @deprecated */
   @Deprecated
   public void setSupportProgressBarIndeterminateVisibility(boolean visible) {
   }

   /** @deprecated */
   @Deprecated
   public void setSupportProgressBarIndeterminate(boolean indeterminate) {
   }

   /** @deprecated */
   @Deprecated
   public void setSupportProgress(int progress) {
   }

   public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
      builder.addParentStack(this);
   }

   public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
   }

   public boolean onSupportNavigateUp() {
      Intent upIntent = this.getSupportParentActivityIntent();
      if (upIntent != null) {
         if (this.supportShouldUpRecreateTask(upIntent)) {
            TaskStackBuilder b = TaskStackBuilder.create(this);
            this.onCreateSupportNavigateUpTaskStack(b);
            this.onPrepareSupportNavigateUpTaskStack(b);
            b.startActivities();

            try {
               ActivityCompat.finishAffinity(this);
            } catch (IllegalStateException var4) {
               this.finish();
            }
         } else {
            this.supportNavigateUpTo(upIntent);
         }

         return true;
      } else {
         return false;
      }
   }

   @Nullable
   public Intent getSupportParentActivityIntent() {
      return NavUtils.getParentActivityIntent(this);
   }

   public boolean supportShouldUpRecreateTask(@NonNull Intent targetIntent) {
      return NavUtils.shouldUpRecreateTask(this, targetIntent);
   }

   public void supportNavigateUpTo(@NonNull Intent upIntent) {
      NavUtils.navigateUpTo(this, upIntent);
   }

   public void onContentChanged() {
      this.onSupportContentChanged();
   }

   /** @deprecated */
   @Deprecated
   public void onSupportContentChanged() {
   }

   @Nullable
   public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
      return this.getDelegate().getDrawerToggleDelegate();
   }

   public boolean onMenuOpened(int featureId, Menu menu) {
      return super.onMenuOpened(featureId, menu);
   }

   public void onPanelClosed(int featureId, Menu menu) {
      super.onPanelClosed(featureId, menu);
   }

   protected void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      this.getDelegate().onSaveInstanceState(outState);
   }

   @NonNull
   public AppCompatDelegate getDelegate() {
      if (this.mDelegate == null) {
         this.mDelegate = AppCompatDelegate.create((Activity)this, this);
      }

      return this.mDelegate;
   }

   public boolean dispatchKeyEvent(KeyEvent event) {
      int keyCode = event.getKeyCode();
      ActionBar actionBar = this.getSupportActionBar();
      return keyCode == 82 && actionBar != null && actionBar.onMenuKeyEvent(event) ? true : super.dispatchKeyEvent(event);
   }

   public Resources getResources() {
      if (this.mResources == null && VectorEnabledTintResources.shouldBeUsed()) {
         this.mResources = new VectorEnabledTintResources(this, super.getResources());
      }

      return this.mResources == null ? super.getResources() : this.mResources;
   }

   private boolean performMenuItemShortcut(int keycode, KeyEvent event) {
      if (VERSION.SDK_INT < 26 && !event.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(event.getMetaState()) && event.getRepeatCount() == 0 && !KeyEvent.isModifierKey(event.getKeyCode())) {
         Window currentWindow = this.getWindow();
         if (currentWindow != null && currentWindow.getDecorView() != null) {
            View decorView = currentWindow.getDecorView();
            if (decorView.dispatchKeyShortcutEvent(event)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean onKeyDown(int keyCode, KeyEvent event) {
      return this.performMenuItemShortcut(keyCode, event) ? true : super.onKeyDown(keyCode, event);
   }

   public void openOptionsMenu() {
      ActionBar actionBar = this.getSupportActionBar();
      if (this.getWindow().hasFeature(0) && (actionBar == null || !actionBar.openOptionsMenu())) {
         super.openOptionsMenu();
      }

   }

   public void closeOptionsMenu() {
      ActionBar actionBar = this.getSupportActionBar();
      if (this.getWindow().hasFeature(0) && (actionBar == null || !actionBar.closeOptionsMenu())) {
         super.closeOptionsMenu();
      }

   }
}
