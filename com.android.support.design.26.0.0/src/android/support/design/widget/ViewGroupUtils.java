package android.support.design.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

class ViewGroupUtils {
   private static final ThreadLocal sMatrix = new ThreadLocal();
   private static final ThreadLocal sRectF = new ThreadLocal();

   static void offsetDescendantRect(ViewGroup parent, View descendant, Rect rect) {
      Matrix m = (Matrix)sMatrix.get();
      if (m == null) {
         m = new Matrix();
         sMatrix.set(m);
      } else {
         m.reset();
      }

      offsetDescendantMatrix(parent, descendant, m);
      RectF rectF = (RectF)sRectF.get();
      if (rectF == null) {
         rectF = new RectF();
         sRectF.set(rectF);
      }

      rectF.set(rect);
      m.mapRect(rectF);
      rect.set((int)(rectF.left + 0.5F), (int)(rectF.top + 0.5F), (int)(rectF.right + 0.5F), (int)(rectF.bottom + 0.5F));
   }

   static void getDescendantRect(ViewGroup parent, View descendant, Rect out) {
      out.set(0, 0, descendant.getWidth(), descendant.getHeight());
      offsetDescendantRect(parent, descendant, out);
   }

   private static void offsetDescendantMatrix(ViewParent target, View view, Matrix m) {
      ViewParent parent = view.getParent();
      if (parent instanceof View && parent != target) {
         View vp = (View)parent;
         offsetDescendantMatrix(target, vp, m);
         m.preTranslate((float)(-vp.getScrollX()), (float)(-vp.getScrollY()));
      }

      m.preTranslate((float)view.getLeft(), (float)view.getTop());
      if (!view.getMatrix().isIdentity()) {
         m.preConcat(view.getMatrix());
      }

   }
}
