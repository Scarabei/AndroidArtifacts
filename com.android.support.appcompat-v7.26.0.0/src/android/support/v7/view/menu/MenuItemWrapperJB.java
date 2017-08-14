package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportMenuItem;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import android.view.ActionProvider.VisibilityListener;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(16)
class MenuItemWrapperJB extends MenuItemWrapperICS {
   MenuItemWrapperJB(Context context, SupportMenuItem object) {
      super(context, object);
   }

   MenuItemWrapperICS.ActionProviderWrapper createActionProviderWrapper(ActionProvider provider) {
      return new MenuItemWrapperJB.ActionProviderWrapperJB(this.mContext, provider);
   }

   class ActionProviderWrapperJB extends MenuItemWrapperICS.ActionProviderWrapper implements VisibilityListener {
      android.support.v4.view.ActionProvider.VisibilityListener mListener;

      public ActionProviderWrapperJB(Context context, ActionProvider inner) {
         super(context, inner);
      }

      public View onCreateActionView(MenuItem forItem) {
         return this.mInner.onCreateActionView(forItem);
      }

      public boolean overridesItemVisibility() {
         return this.mInner.overridesItemVisibility();
      }

      public boolean isVisible() {
         return this.mInner.isVisible();
      }

      public void refreshVisibility() {
         this.mInner.refreshVisibility();
      }

      public void setVisibilityListener(android.support.v4.view.ActionProvider.VisibilityListener listener) {
         this.mListener = listener;
         this.mInner.setVisibilityListener(listener != null ? this : null);
      }

      public void onActionProviderVisibilityChanged(boolean isVisible) {
         if (this.mListener != null) {
            this.mListener.onActionProviderVisibilityChanged(isVisible);
         }

      }
   }
}
