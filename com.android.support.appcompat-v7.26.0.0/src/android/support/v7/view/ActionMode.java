package android.support.v7.view;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public abstract class ActionMode {
   private Object mTag;
   private boolean mTitleOptionalHint;

   public void setTag(Object tag) {
      this.mTag = tag;
   }

   public Object getTag() {
      return this.mTag;
   }

   public abstract void setTitle(CharSequence var1);

   public abstract void setTitle(int var1);

   public abstract void setSubtitle(CharSequence var1);

   public abstract void setSubtitle(int var1);

   public void setTitleOptionalHint(boolean titleOptional) {
      this.mTitleOptionalHint = titleOptional;
   }

   public boolean getTitleOptionalHint() {
      return this.mTitleOptionalHint;
   }

   public boolean isTitleOptional() {
      return false;
   }

   public abstract void setCustomView(View var1);

   public abstract void invalidate();

   public abstract void finish();

   public abstract Menu getMenu();

   public abstract CharSequence getTitle();

   public abstract CharSequence getSubtitle();

   public abstract View getCustomView();

   public abstract MenuInflater getMenuInflater();

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isUiFocusable() {
      return true;
   }

   public interface Callback {
      boolean onCreateActionMode(ActionMode var1, Menu var2);

      boolean onPrepareActionMode(ActionMode var1, Menu var2);

      boolean onActionItemClicked(ActionMode var1, MenuItem var2);

      void onDestroyActionMode(ActionMode var1);
   }
}
