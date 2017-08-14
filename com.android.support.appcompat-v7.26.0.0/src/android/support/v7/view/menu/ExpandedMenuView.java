package android.support.v7.view.menu;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ExpandedMenuView extends ListView implements MenuBuilder.ItemInvoker, MenuView, OnItemClickListener {
   private static final int[] TINT_ATTRS = new int[]{16842964, 16843049};
   private MenuBuilder mMenu;
   private int mAnimations;

   public ExpandedMenuView(Context context, AttributeSet attrs) {
      this(context, attrs, 16842868);
   }

   public ExpandedMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs);
      this.setOnItemClickListener(this);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, TINT_ATTRS, defStyleAttr, 0);
      if (a.hasValue(0)) {
         this.setBackgroundDrawable(a.getDrawable(0));
      }

      if (a.hasValue(1)) {
         this.setDivider(a.getDrawable(1));
      }

      a.recycle();
   }

   public void initialize(MenuBuilder menu) {
      this.mMenu = menu;
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.setChildrenDrawingCacheEnabled(false);
   }

   public boolean invokeItem(MenuItemImpl item) {
      return this.mMenu.performItemAction(item, 0);
   }

   public void onItemClick(AdapterView parent, View v, int position, long id) {
      this.invokeItem((MenuItemImpl)this.getAdapter().getItem(position));
   }

   public int getWindowAnimations() {
      return this.mAnimations;
   }
}
