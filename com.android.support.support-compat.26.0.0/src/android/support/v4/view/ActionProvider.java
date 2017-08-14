package android.support.v4.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public abstract class ActionProvider {
   private static final String TAG = "ActionProvider(support)";
   private final Context mContext;
   private ActionProvider.SubUiVisibilityListener mSubUiVisibilityListener;
   private ActionProvider.VisibilityListener mVisibilityListener;

   public ActionProvider(Context context) {
      this.mContext = context;
   }

   public Context getContext() {
      return this.mContext;
   }

   public abstract View onCreateActionView();

   public View onCreateActionView(MenuItem forItem) {
      return this.onCreateActionView();
   }

   public boolean overridesItemVisibility() {
      return false;
   }

   public boolean isVisible() {
      return true;
   }

   public void refreshVisibility() {
      if (this.mVisibilityListener != null && this.overridesItemVisibility()) {
         this.mVisibilityListener.onActionProviderVisibilityChanged(this.isVisible());
      }

   }

   public boolean onPerformDefaultAction() {
      return false;
   }

   public boolean hasSubMenu() {
      return false;
   }

   public void onPrepareSubMenu(SubMenu subMenu) {
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void subUiVisibilityChanged(boolean isVisible) {
      if (this.mSubUiVisibilityListener != null) {
         this.mSubUiVisibilityListener.onSubUiVisibilityChanged(isVisible);
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setSubUiVisibilityListener(ActionProvider.SubUiVisibilityListener listener) {
      this.mSubUiVisibilityListener = listener;
   }

   public void setVisibilityListener(ActionProvider.VisibilityListener listener) {
      if (this.mVisibilityListener != null && listener != null) {
         Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + this.getClass().getSimpleName() + " instance while it is still in use somewhere else?");
      }

      this.mVisibilityListener = listener;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void reset() {
      this.mVisibilityListener = null;
      this.mSubUiVisibilityListener = null;
   }

   public interface VisibilityListener {
      void onActionProviderVisibilityChanged(boolean var1);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public interface SubUiVisibilityListener {
      void onSubUiVisibilityChanged(boolean var1);
   }
}
