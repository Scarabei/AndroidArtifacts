package android.support.transition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@RequiresApi(14)
class ViewOverlayApi14 implements ViewOverlayImpl {
   protected ViewOverlayApi14.OverlayViewGroup mOverlayViewGroup;

   ViewOverlayApi14(Context context, ViewGroup hostView, View requestingView) {
      this.mOverlayViewGroup = new ViewOverlayApi14.OverlayViewGroup(context, hostView, requestingView, this);
   }

   static ViewGroup getContentView(View view) {
      Object parent = view;

      while(parent != null) {
         if (((View)parent).getId() == 16908290 && parent instanceof ViewGroup) {
            return (ViewGroup)parent;
         }

         if (((View)parent).getParent() instanceof ViewGroup) {
            parent = (ViewGroup)((View)parent).getParent();
         }
      }

      return null;
   }

   static ViewOverlayApi14 createFrom(View view) {
      ViewGroup contentView = getContentView(view);
      if (contentView != null) {
         int numChildren = contentView.getChildCount();

         for(int i = 0; i < numChildren; ++i) {
            View child = contentView.getChildAt(i);
            if (child instanceof ViewOverlayApi14.OverlayViewGroup) {
               return ((ViewOverlayApi14.OverlayViewGroup)child).mViewOverlay;
            }
         }

         return new ViewGroupOverlayApi14(contentView.getContext(), contentView, view);
      } else {
         return null;
      }
   }

   ViewGroup getOverlayView() {
      return this.mOverlayViewGroup;
   }

   public void add(@NonNull Drawable drawable) {
      this.mOverlayViewGroup.add(drawable);
   }

   public void clear() {
      this.mOverlayViewGroup.clear();
   }

   public void remove(@NonNull Drawable drawable) {
      this.mOverlayViewGroup.remove(drawable);
   }

   boolean isEmpty() {
      return this.mOverlayViewGroup.isEmpty();
   }

   static class OverlayViewGroup extends ViewGroup {
      static Method sInvalidateChildInParentFastMethod;
      ViewGroup mHostView;
      View mRequestingView;
      ArrayList mDrawables = null;
      ViewOverlayApi14 mViewOverlay;

      OverlayViewGroup(Context context, ViewGroup hostView, View requestingView, ViewOverlayApi14 viewOverlay) {
         super(context);
         this.mHostView = hostView;
         this.mRequestingView = requestingView;
         this.setRight(hostView.getWidth());
         this.setBottom(hostView.getHeight());
         hostView.addView(this);
         this.mViewOverlay = viewOverlay;
      }

      public boolean dispatchTouchEvent(MotionEvent ev) {
         return false;
      }

      public void add(Drawable drawable) {
         if (this.mDrawables == null) {
            this.mDrawables = new ArrayList();
         }

         if (!this.mDrawables.contains(drawable)) {
            this.mDrawables.add(drawable);
            this.invalidate(drawable.getBounds());
            drawable.setCallback(this);
         }

      }

      public void remove(Drawable drawable) {
         if (this.mDrawables != null) {
            this.mDrawables.remove(drawable);
            this.invalidate(drawable.getBounds());
            drawable.setCallback((Callback)null);
         }

      }

      protected boolean verifyDrawable(@NonNull Drawable who) {
         return super.verifyDrawable(who) || this.mDrawables != null && this.mDrawables.contains(who);
      }

      public void add(View child) {
         if (child.getParent() instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup)child.getParent();
            if (parent != this.mHostView && parent.getParent() != null && ViewCompat.isAttachedToWindow(parent)) {
               int[] parentLocation = new int[2];
               int[] hostViewLocation = new int[2];
               parent.getLocationOnScreen(parentLocation);
               this.mHostView.getLocationOnScreen(hostViewLocation);
               ViewCompat.offsetLeftAndRight(child, parentLocation[0] - hostViewLocation[0]);
               ViewCompat.offsetTopAndBottom(child, parentLocation[1] - hostViewLocation[1]);
            }

            parent.removeView(child);
            if (child.getParent() != null) {
               parent.removeView(child);
            }
         }

         super.addView(child, this.getChildCount() - 1);
      }

      public void remove(View view) {
         super.removeView(view);
         if (this.isEmpty()) {
            this.mHostView.removeView(this);
         }

      }

      public void clear() {
         this.removeAllViews();
         if (this.mDrawables != null) {
            this.mDrawables.clear();
         }

      }

      boolean isEmpty() {
         return this.getChildCount() == 0 && (this.mDrawables == null || this.mDrawables.size() == 0);
      }

      public void invalidateDrawable(@NonNull Drawable drawable) {
         this.invalidate(drawable.getBounds());
      }

      protected void dispatchDraw(Canvas canvas) {
         int[] contentViewLocation = new int[2];
         int[] hostViewLocation = new int[2];
         this.mHostView.getLocationOnScreen(contentViewLocation);
         this.mRequestingView.getLocationOnScreen(hostViewLocation);
         canvas.translate((float)(hostViewLocation[0] - contentViewLocation[0]), (float)(hostViewLocation[1] - contentViewLocation[1]));
         canvas.clipRect(new Rect(0, 0, this.mRequestingView.getWidth(), this.mRequestingView.getHeight()));
         super.dispatchDraw(canvas);
         int numDrawables = this.mDrawables == null ? 0 : this.mDrawables.size();

         for(int i = 0; i < numDrawables; ++i) {
            ((Drawable)this.mDrawables.get(i)).draw(canvas);
         }

      }

      protected void onLayout(boolean changed, int l, int t, int r, int b) {
      }

      private void getOffset(int[] offset) {
         int[] contentViewLocation = new int[2];
         int[] hostViewLocation = new int[2];
         this.mHostView.getLocationOnScreen(contentViewLocation);
         this.mRequestingView.getLocationOnScreen(hostViewLocation);
         offset[0] = hostViewLocation[0] - contentViewLocation[0];
         offset[1] = hostViewLocation[1] - contentViewLocation[1];
      }

      public void invalidateChildFast(View child, Rect dirty) {
         if (this.mHostView != null) {
            int left = child.getLeft();
            int top = child.getTop();
            int[] offset = new int[2];
            this.getOffset(offset);
            dirty.offset(left + offset[0], top + offset[1]);
            this.mHostView.invalidate(dirty);
         }

      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      protected ViewParent invalidateChildInParentFast(int left, int top, Rect dirty) {
         if (this.mHostView instanceof ViewGroup && sInvalidateChildInParentFastMethod != null) {
            try {
               int[] offset = new int[2];
               this.getOffset(offset);
               sInvalidateChildInParentFastMethod.invoke(this.mHostView, left, top, dirty);
            } catch (IllegalAccessException var5) {
               var5.printStackTrace();
            } catch (InvocationTargetException var6) {
               var6.printStackTrace();
            }
         }

         return null;
      }

      public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
         if (this.mHostView != null) {
            dirty.offset(location[0], location[1]);
            if (this.mHostView instanceof ViewGroup) {
               location[0] = 0;
               location[1] = 0;
               int[] offset = new int[2];
               this.getOffset(offset);
               dirty.offset(offset[0], offset[1]);
               return super.invalidateChildInParent(location, dirty);
            }

            this.invalidate(dirty);
         }

         return null;
      }

      static {
         try {
            sInvalidateChildInParentFastMethod = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", Integer.TYPE, Integer.TYPE, Rect.class);
         } catch (NoSuchMethodException var1) {
            ;
         }

      }

      static class TouchInterceptor extends View {
         TouchInterceptor(Context context) {
            super(context);
         }
      }
   }
}
