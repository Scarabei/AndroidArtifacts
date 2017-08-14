package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.R.styleable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

public final class TabItem extends View {
   final CharSequence mText;
   final Drawable mIcon;
   final int mCustomLayout;

   public TabItem(Context context) {
      this(context, (AttributeSet)null);
   }

   public TabItem(Context context, AttributeSet attrs) {
      super(context, attrs);
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.TabItem);
      this.mText = a.getText(styleable.TabItem_android_text);
      this.mIcon = a.getDrawable(styleable.TabItem_android_icon);
      this.mCustomLayout = a.getResourceId(styleable.TabItem_android_layout, 0);
      a.recycle();
   }
}
