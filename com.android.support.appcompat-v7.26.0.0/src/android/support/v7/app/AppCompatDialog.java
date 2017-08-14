package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.attr;
import android.support.v7.view.ActionMode;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class AppCompatDialog extends Dialog implements AppCompatCallback {
   private AppCompatDelegate mDelegate;

   public AppCompatDialog(Context context) {
      this(context, 0);
   }

   public AppCompatDialog(Context context, int theme) {
      super(context, getThemeResId(context, theme));
      this.getDelegate().onCreate((Bundle)null);
      this.getDelegate().applyDayNight();
   }

   protected AppCompatDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
      super(context, cancelable, cancelListener);
   }

   protected void onCreate(Bundle savedInstanceState) {
      this.getDelegate().installViewFactory();
      super.onCreate(savedInstanceState);
      this.getDelegate().onCreate(savedInstanceState);
   }

   public ActionBar getSupportActionBar() {
      return this.getDelegate().getSupportActionBar();
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

   @Nullable
   public View findViewById(@IdRes int id) {
      return this.getDelegate().findViewById(id);
   }

   public void setTitle(CharSequence title) {
      super.setTitle(title);
      this.getDelegate().setTitle(title);
   }

   public void setTitle(int titleId) {
      super.setTitle(titleId);
      this.getDelegate().setTitle(this.getContext().getString(titleId));
   }

   public void addContentView(View view, LayoutParams params) {
      this.getDelegate().addContentView(view, params);
   }

   protected void onStop() {
      super.onStop();
      this.getDelegate().onStop();
   }

   public boolean supportRequestWindowFeature(int featureId) {
      return this.getDelegate().requestWindowFeature(featureId);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void invalidateOptionsMenu() {
      this.getDelegate().invalidateOptionsMenu();
   }

   public AppCompatDelegate getDelegate() {
      if (this.mDelegate == null) {
         this.mDelegate = AppCompatDelegate.create((Dialog)this, this);
      }

      return this.mDelegate;
   }

   private static int getThemeResId(Context context, int themeId) {
      if (themeId == 0) {
         TypedValue outValue = new TypedValue();
         context.getTheme().resolveAttribute(attr.dialogTheme, outValue, true);
         themeId = outValue.resourceId;
      }

      return themeId;
   }

   public void onSupportActionModeStarted(ActionMode mode) {
   }

   public void onSupportActionModeFinished(ActionMode mode) {
   }

   @Nullable
   public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
      return null;
   }
}
