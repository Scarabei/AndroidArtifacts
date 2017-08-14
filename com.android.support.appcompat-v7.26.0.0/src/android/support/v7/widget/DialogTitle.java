package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.styleable;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class DialogTitle extends TextView {
   public DialogTitle(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   public DialogTitle(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public DialogTitle(Context context) {
      super(context);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      Layout layout = this.getLayout();
      if (layout != null) {
         int lineCount = layout.getLineCount();
         if (lineCount > 0) {
            int ellipsisCount = layout.getEllipsisCount(lineCount - 1);
            if (ellipsisCount > 0) {
               this.setSingleLine(false);
               this.setMaxLines(2);
               TypedArray a = this.getContext().obtainStyledAttributes((AttributeSet)null, styleable.TextAppearance, 16842817, 16973892);
               int textSize = a.getDimensionPixelSize(styleable.TextAppearance_android_textSize, 0);
               if (textSize != 0) {
                  this.setTextSize(0, (float)textSize);
               }

               a.recycle();
               super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
         }
      }

   }
}
