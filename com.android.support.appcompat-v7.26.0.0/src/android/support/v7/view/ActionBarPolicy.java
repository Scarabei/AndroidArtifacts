package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.bool;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.ViewConfiguration;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActionBarPolicy {
   private Context mContext;

   public static ActionBarPolicy get(Context context) {
      return new ActionBarPolicy(context);
   }

   private ActionBarPolicy(Context context) {
      this.mContext = context;
   }

   public int getMaxActionButtons() {
      Configuration configuration = this.mContext.getResources().getConfiguration();
      int widthDp = configuration.screenWidthDp;
      int heightDp = configuration.screenHeightDp;
      int smallest = configuration.smallestScreenWidthDp;
      if (smallest > 600 || widthDp > 600 || widthDp > 960 && heightDp > 720 || widthDp > 720 && heightDp > 960) {
         return 5;
      } else if (widthDp >= 500 || widthDp > 640 && heightDp > 480 || widthDp > 480 && heightDp > 640) {
         return 4;
      } else {
         return widthDp >= 360 ? 3 : 2;
      }
   }

   public boolean showsOverflowMenuButton() {
      if (VERSION.SDK_INT >= 19) {
         return true;
      } else {
         return !ViewConfiguration.get(this.mContext).hasPermanentMenuKey();
      }
   }

   public int getEmbeddedMenuWidthLimit() {
      return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
   }

   public boolean hasEmbeddedTabs() {
      return this.mContext.getResources().getBoolean(bool.abc_action_bar_embed_tabs);
   }

   public int getTabContainerHeight() {
      TypedArray a = this.mContext.obtainStyledAttributes((AttributeSet)null, styleable.ActionBar, attr.actionBarStyle, 0);
      int height = a.getLayoutDimension(styleable.ActionBar_height, 0);
      Resources r = this.mContext.getResources();
      if (!this.hasEmbeddedTabs()) {
         height = Math.min(height, r.getDimensionPixelSize(dimen.abc_action_bar_stacked_max_height));
      }

      a.recycle();
      return height;
   }

   public boolean enableHomeButtonByDefault() {
      return this.mContext.getApplicationInfo().targetSdkVersion < 14;
   }

   public int getStackedTabMaxWidth() {
      return this.mContext.getResources().getDimensionPixelSize(dimen.abc_action_bar_stacked_tab_max_width);
   }
}
