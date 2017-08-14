package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public final class MenuItemCompat {
   private static final String TAG = "MenuItemCompat";
   /** @deprecated */
   @Deprecated
   public static final int SHOW_AS_ACTION_NEVER = 0;
   /** @deprecated */
   @Deprecated
   public static final int SHOW_AS_ACTION_IF_ROOM = 1;
   /** @deprecated */
   @Deprecated
   public static final int SHOW_AS_ACTION_ALWAYS = 2;
   /** @deprecated */
   @Deprecated
   public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
   /** @deprecated */
   @Deprecated
   public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
   static final MenuItemCompat.MenuVersionImpl IMPL;

   /** @deprecated */
   @Deprecated
   public static void setShowAsAction(MenuItem item, int actionEnum) {
      item.setShowAsAction(actionEnum);
   }

   /** @deprecated */
   @Deprecated
   public static MenuItem setActionView(MenuItem item, View view) {
      return item.setActionView(view);
   }

   /** @deprecated */
   @Deprecated
   public static MenuItem setActionView(MenuItem item, int resId) {
      return item.setActionView(resId);
   }

   /** @deprecated */
   @Deprecated
   public static View getActionView(MenuItem item) {
      return item.getActionView();
   }

   public static MenuItem setActionProvider(MenuItem item, ActionProvider provider) {
      if (item instanceof SupportMenuItem) {
         return ((SupportMenuItem)item).setSupportActionProvider(provider);
      } else {
         Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
         return item;
      }
   }

   public static ActionProvider getActionProvider(MenuItem item) {
      if (item instanceof SupportMenuItem) {
         return ((SupportMenuItem)item).getSupportActionProvider();
      } else {
         Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean expandActionView(MenuItem item) {
      return item.expandActionView();
   }

   /** @deprecated */
   @Deprecated
   public static boolean collapseActionView(MenuItem item) {
      return item.collapseActionView();
   }

   /** @deprecated */
   @Deprecated
   public static boolean isActionViewExpanded(MenuItem item) {
      return item.isActionViewExpanded();
   }

   /** @deprecated */
   @Deprecated
   public static MenuItem setOnActionExpandListener(MenuItem item, final MenuItemCompat.OnActionExpandListener listener) {
      return item.setOnActionExpandListener(new android.view.MenuItem.OnActionExpandListener() {
         public boolean onMenuItemActionExpand(MenuItem item) {
            return listener.onMenuItemActionExpand(item);
         }

         public boolean onMenuItemActionCollapse(MenuItem item) {
            return listener.onMenuItemActionCollapse(item);
         }
      });
   }

   public static void setContentDescription(MenuItem item, CharSequence contentDescription) {
      if (item instanceof SupportMenuItem) {
         ((SupportMenuItem)item).setContentDescription(contentDescription);
      } else {
         IMPL.setContentDescription(item, contentDescription);
      }

   }

   public static CharSequence getContentDescription(MenuItem item) {
      return item instanceof SupportMenuItem ? ((SupportMenuItem)item).getContentDescription() : IMPL.getContentDescription(item);
   }

   public static void setTooltipText(MenuItem item, CharSequence tooltipText) {
      if (item instanceof SupportMenuItem) {
         ((SupportMenuItem)item).setTooltipText(tooltipText);
      } else {
         IMPL.setTooltipText(item, tooltipText);
      }

   }

   public static CharSequence getTooltipText(MenuItem item) {
      return item instanceof SupportMenuItem ? ((SupportMenuItem)item).getTooltipText() : IMPL.getTooltipText(item);
   }

   public static void setShortcut(MenuItem item, char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
      if (item instanceof SupportMenuItem) {
         ((SupportMenuItem)item).setShortcut(numericChar, alphaChar, numericModifiers, alphaModifiers);
      } else {
         IMPL.setShortcut(item, numericChar, alphaChar, numericModifiers, alphaModifiers);
      }

   }

   public static void setNumericShortcut(MenuItem item, char numericChar, int numericModifiers) {
      if (item instanceof SupportMenuItem) {
         ((SupportMenuItem)item).setNumericShortcut(numericChar, numericModifiers);
      } else {
         IMPL.setNumericShortcut(item, numericChar, numericModifiers);
      }

   }

   public static int getNumericModifiers(MenuItem item) {
      return item instanceof SupportMenuItem ? ((SupportMenuItem)item).getNumericModifiers() : IMPL.getNumericModifiers(item);
   }

   public static void setAlphabeticShortcut(MenuItem item, char alphaChar, int alphaModifiers) {
      if (item instanceof SupportMenuItem) {
         ((SupportMenuItem)item).setAlphabeticShortcut(alphaChar, alphaModifiers);
      } else {
         IMPL.setAlphabeticShortcut(item, alphaChar, alphaModifiers);
      }

   }

   public static int getAlphabeticModifiers(MenuItem item) {
      return item instanceof SupportMenuItem ? ((SupportMenuItem)item).getAlphabeticModifiers() : IMPL.getAlphabeticModifiers(item);
   }

   public static void setIconTintList(MenuItem item, ColorStateList tint) {
      if (item instanceof SupportMenuItem) {
         ((SupportMenuItem)item).setIconTintList(tint);
      } else {
         IMPL.setIconTintList(item, tint);
      }

   }

   public static ColorStateList getIconTintList(MenuItem item) {
      return item instanceof SupportMenuItem ? ((SupportMenuItem)item).getIconTintList() : IMPL.getIconTintList(item);
   }

   public static void setIconTintMode(MenuItem item, Mode tintMode) {
      if (item instanceof SupportMenuItem) {
         ((SupportMenuItem)item).setIconTintMode(tintMode);
      } else {
         IMPL.setIconTintMode(item, tintMode);
      }

   }

   public static Mode getIconTintMode(MenuItem item) {
      return item instanceof SupportMenuItem ? ((SupportMenuItem)item).getIconTintMode() : IMPL.getIconTintMode(item);
   }

   static {
      if (VERSION.SDK_INT >= 26) {
         IMPL = new MenuItemCompat.MenuItemCompatApi26Impl();
      } else {
         IMPL = new MenuItemCompat.MenuItemCompatBaseImpl();
      }

   }

   @RequiresApi(26)
   static class MenuItemCompatApi26Impl extends MenuItemCompat.MenuItemCompatBaseImpl {
      public void setContentDescription(MenuItem item, CharSequence contentDescription) {
         item.setContentDescription(contentDescription);
      }

      public CharSequence getContentDescription(MenuItem item) {
         return item.getContentDescription();
      }

      public void setTooltipText(MenuItem item, CharSequence tooltipText) {
         item.setTooltipText(tooltipText);
      }

      public CharSequence getTooltipText(MenuItem item) {
         return item.getTooltipText();
      }

      public void setShortcut(MenuItem item, char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
         item.setShortcut(numericChar, alphaChar, numericModifiers, alphaModifiers);
      }

      public void setAlphabeticShortcut(MenuItem item, char alphaChar, int alphaModifiers) {
         item.setAlphabeticShortcut(alphaChar, alphaModifiers);
      }

      public int getAlphabeticModifiers(MenuItem item) {
         return item.getAlphabeticModifiers();
      }

      public void setNumericShortcut(MenuItem item, char numericChar, int numericModifiers) {
         item.setNumericShortcut(numericChar, numericModifiers);
      }

      public int getNumericModifiers(MenuItem item) {
         return item.getNumericModifiers();
      }

      public void setIconTintList(MenuItem item, ColorStateList tint) {
         item.setIconTintList(tint);
      }

      public ColorStateList getIconTintList(MenuItem item) {
         return item.getIconTintList();
      }

      public void setIconTintMode(MenuItem item, Mode tintMode) {
         item.setIconTintMode(tintMode);
      }

      public Mode getIconTintMode(MenuItem item) {
         return item.getIconTintMode();
      }
   }

   static class MenuItemCompatBaseImpl implements MenuItemCompat.MenuVersionImpl {
      public void setContentDescription(MenuItem item, CharSequence contentDescription) {
      }

      public CharSequence getContentDescription(MenuItem item) {
         return null;
      }

      public void setTooltipText(MenuItem item, CharSequence tooltipText) {
      }

      public CharSequence getTooltipText(MenuItem item) {
         return null;
      }

      public void setShortcut(MenuItem item, char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
      }

      public void setAlphabeticShortcut(MenuItem item, char alphaChar, int alphaModifiers) {
      }

      public int getAlphabeticModifiers(MenuItem item) {
         return 0;
      }

      public void setNumericShortcut(MenuItem item, char numericChar, int numericModifiers) {
      }

      public int getNumericModifiers(MenuItem item) {
         return 0;
      }

      public void setIconTintList(MenuItem item, ColorStateList tint) {
      }

      public ColorStateList getIconTintList(MenuItem item) {
         return null;
      }

      public void setIconTintMode(MenuItem item, Mode tintMode) {
      }

      public Mode getIconTintMode(MenuItem item) {
         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public interface OnActionExpandListener {
      boolean onMenuItemActionExpand(MenuItem var1);

      boolean onMenuItemActionCollapse(MenuItem var1);
   }

   interface MenuVersionImpl {
      void setContentDescription(MenuItem var1, CharSequence var2);

      CharSequence getContentDescription(MenuItem var1);

      void setTooltipText(MenuItem var1, CharSequence var2);

      CharSequence getTooltipText(MenuItem var1);

      void setShortcut(MenuItem var1, char var2, char var3, int var4, int var5);

      void setAlphabeticShortcut(MenuItem var1, char var2, int var3);

      int getAlphabeticModifiers(MenuItem var1);

      void setNumericShortcut(MenuItem var1, char var2, int var3);

      int getNumericModifiers(MenuItem var1);

      void setIconTintList(MenuItem var1, ColorStateList var2);

      ColorStateList getIconTintList(MenuItem var1);

      void setIconTintMode(MenuItem var1, Mode var2);

      Mode getIconTintMode(MenuItem var1);
   }
}
