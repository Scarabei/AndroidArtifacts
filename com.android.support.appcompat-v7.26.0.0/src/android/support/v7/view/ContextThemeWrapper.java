package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.style;
import android.view.LayoutInflater;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ContextThemeWrapper extends ContextWrapper {
   private int mThemeResource;
   private Theme mTheme;
   private LayoutInflater mInflater;
   private Configuration mOverrideConfiguration;
   private Resources mResources;

   public ContextThemeWrapper() {
      super((Context)null);
   }

   public ContextThemeWrapper(Context base, @StyleRes int themeResId) {
      super(base);
      this.mThemeResource = themeResId;
   }

   public ContextThemeWrapper(Context base, Theme theme) {
      super(base);
      this.mTheme = theme;
   }

   protected void attachBaseContext(Context newBase) {
      super.attachBaseContext(newBase);
   }

   public void applyOverrideConfiguration(Configuration overrideConfiguration) {
      if (this.mResources != null) {
         throw new IllegalStateException("getResources() or getAssets() has already been called");
      } else if (this.mOverrideConfiguration != null) {
         throw new IllegalStateException("Override configuration has already been set");
      } else {
         this.mOverrideConfiguration = new Configuration(overrideConfiguration);
      }
   }

   public Configuration getOverrideConfiguration() {
      return this.mOverrideConfiguration;
   }

   public Resources getResources() {
      return this.getResourcesInternal();
   }

   private Resources getResourcesInternal() {
      if (this.mResources == null) {
         if (this.mOverrideConfiguration == null) {
            this.mResources = super.getResources();
         } else if (VERSION.SDK_INT >= 17) {
            Context resContext = this.createConfigurationContext(this.mOverrideConfiguration);
            this.mResources = resContext.getResources();
         }
      }

      return this.mResources;
   }

   public void setTheme(int resid) {
      if (this.mThemeResource != resid) {
         this.mThemeResource = resid;
         this.initializeTheme();
      }

   }

   public int getThemeResId() {
      return this.mThemeResource;
   }

   public Theme getTheme() {
      if (this.mTheme != null) {
         return this.mTheme;
      } else {
         if (this.mThemeResource == 0) {
            this.mThemeResource = style.Theme_AppCompat_Light;
         }

         this.initializeTheme();
         return this.mTheme;
      }
   }

   public Object getSystemService(String name) {
      if ("layout_inflater".equals(name)) {
         if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(this.getBaseContext()).cloneInContext(this);
         }

         return this.mInflater;
      } else {
         return this.getBaseContext().getSystemService(name);
      }
   }

   protected void onApplyThemeResource(Theme theme, int resid, boolean first) {
      theme.applyStyle(resid, true);
   }

   private void initializeTheme() {
      boolean first = this.mTheme == null;
      if (first) {
         this.mTheme = this.getResources().newTheme();
         Theme theme = this.getBaseContext().getTheme();
         if (theme != null) {
            this.mTheme.setTo(theme);
         }
      }

      this.onApplyThemeResource(this.mTheme, this.mThemeResource, first);
   }

   public AssetManager getAssets() {
      return this.getResources().getAssets();
   }
}
