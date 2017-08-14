package android.support.v4.internal.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface SupportMenuItem extends MenuItem {
   int SHOW_AS_ACTION_NEVER = 0;
   int SHOW_AS_ACTION_IF_ROOM = 1;
   int SHOW_AS_ACTION_ALWAYS = 2;
   int SHOW_AS_ACTION_WITH_TEXT = 4;
   int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;

   void setShowAsAction(int var1);

   MenuItem setShowAsActionFlags(int var1);

   MenuItem setActionView(View var1);

   MenuItem setActionView(int var1);

   View getActionView();

   SupportMenuItem setSupportActionProvider(ActionProvider var1);

   ActionProvider getSupportActionProvider();

   boolean expandActionView();

   boolean collapseActionView();

   boolean isActionViewExpanded();

   SupportMenuItem setContentDescription(CharSequence var1);

   CharSequence getContentDescription();

   SupportMenuItem setTooltipText(CharSequence var1);

   CharSequence getTooltipText();

   MenuItem setShortcut(char var1, char var2, int var3, int var4);

   MenuItem setNumericShortcut(char var1, int var2);

   int getNumericModifiers();

   MenuItem setAlphabeticShortcut(char var1, int var2);

   int getAlphabeticModifiers();

   MenuItem setIconTintList(ColorStateList var1);

   ColorStateList getIconTintList();

   MenuItem setIconTintMode(Mode var1);

   Mode getIconTintMode();
}
