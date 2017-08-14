package android.support.v7.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ActionMode.Callback;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SupportActionModeWrapper extends android.view.ActionMode {
   final Context mContext;
   final ActionMode mWrappedObject;

   public SupportActionModeWrapper(Context context, ActionMode supportActionMode) {
      this.mContext = context;
      this.mWrappedObject = supportActionMode;
   }

   public Object getTag() {
      return this.mWrappedObject.getTag();
   }

   public void setTag(Object tag) {
      this.mWrappedObject.setTag(tag);
   }

   public void setTitle(CharSequence title) {
      this.mWrappedObject.setTitle(title);
   }

   public void setSubtitle(CharSequence subtitle) {
      this.mWrappedObject.setSubtitle(subtitle);
   }

   public void invalidate() {
      this.mWrappedObject.invalidate();
   }

   public void finish() {
      this.mWrappedObject.finish();
   }

   public Menu getMenu() {
      return MenuWrapperFactory.wrapSupportMenu(this.mContext, (SupportMenu)this.mWrappedObject.getMenu());
   }

   public CharSequence getTitle() {
      return this.mWrappedObject.getTitle();
   }

   public void setTitle(int resId) {
      this.mWrappedObject.setTitle(resId);
   }

   public CharSequence getSubtitle() {
      return this.mWrappedObject.getSubtitle();
   }

   public void setSubtitle(int resId) {
      this.mWrappedObject.setSubtitle(resId);
   }

   public View getCustomView() {
      return this.mWrappedObject.getCustomView();
   }

   public void setCustomView(View view) {
      this.mWrappedObject.setCustomView(view);
   }

   public MenuInflater getMenuInflater() {
      return this.mWrappedObject.getMenuInflater();
   }

   public boolean getTitleOptionalHint() {
      return this.mWrappedObject.getTitleOptionalHint();
   }

   public void setTitleOptionalHint(boolean titleOptional) {
      this.mWrappedObject.setTitleOptionalHint(titleOptional);
   }

   public boolean isTitleOptional() {
      return this.mWrappedObject.isTitleOptional();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static class CallbackWrapper implements ActionMode.Callback {
      final Callback mWrappedCallback;
      final Context mContext;
      final ArrayList mActionModes;
      final SimpleArrayMap mMenus;

      public CallbackWrapper(Context context, Callback supportCallback) {
         this.mContext = context;
         this.mWrappedCallback = supportCallback;
         this.mActionModes = new ArrayList();
         this.mMenus = new SimpleArrayMap();
      }

      public boolean onCreateActionMode(ActionMode mode, Menu menu) {
         return this.mWrappedCallback.onCreateActionMode(this.getActionModeWrapper(mode), this.getMenuWrapper(menu));
      }

      public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
         return this.mWrappedCallback.onPrepareActionMode(this.getActionModeWrapper(mode), this.getMenuWrapper(menu));
      }

      public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
         return this.mWrappedCallback.onActionItemClicked(this.getActionModeWrapper(mode), MenuWrapperFactory.wrapSupportMenuItem(this.mContext, (SupportMenuItem)item));
      }

      public void onDestroyActionMode(ActionMode mode) {
         this.mWrappedCallback.onDestroyActionMode(this.getActionModeWrapper(mode));
      }

      private Menu getMenuWrapper(Menu menu) {
         Menu wrapper = (Menu)this.mMenus.get(menu);
         if (wrapper == null) {
            wrapper = MenuWrapperFactory.wrapSupportMenu(this.mContext, (SupportMenu)menu);
            this.mMenus.put(menu, wrapper);
         }

         return wrapper;
      }

      public android.view.ActionMode getActionModeWrapper(ActionMode mode) {
         int i = 0;

         for(int count = this.mActionModes.size(); i < count; ++i) {
            SupportActionModeWrapper wrapper = (SupportActionModeWrapper)this.mActionModes.get(i);
            if (wrapper != null && wrapper.mWrappedObject == mode) {
               return wrapper;
            }
         }

         SupportActionModeWrapper wrapper = new SupportActionModeWrapper(this.mContext, mode);
         this.mActionModes.add(wrapper);
         return wrapper;
      }
   }
}
