package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewGroup.LayoutParams;
import java.lang.ref.WeakReference;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class ViewStubCompat extends View {
   private int mLayoutResource;
   private int mInflatedId;
   private WeakReference mInflatedViewRef;
   private LayoutInflater mInflater;
   private ViewStubCompat.OnInflateListener mInflateListener;

   public ViewStubCompat(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public ViewStubCompat(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      this.mLayoutResource = 0;
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.ViewStubCompat, defStyle, 0);
      this.mInflatedId = a.getResourceId(styleable.ViewStubCompat_android_inflatedId, -1);
      this.mLayoutResource = a.getResourceId(styleable.ViewStubCompat_android_layout, 0);
      this.setId(a.getResourceId(styleable.ViewStubCompat_android_id, -1));
      a.recycle();
      this.setVisibility(8);
      this.setWillNotDraw(true);
   }

   public int getInflatedId() {
      return this.mInflatedId;
   }

   public void setInflatedId(int inflatedId) {
      this.mInflatedId = inflatedId;
   }

   public int getLayoutResource() {
      return this.mLayoutResource;
   }

   public void setLayoutResource(int layoutResource) {
      this.mLayoutResource = layoutResource;
   }

   public void setLayoutInflater(LayoutInflater inflater) {
      this.mInflater = inflater;
   }

   public LayoutInflater getLayoutInflater() {
      return this.mInflater;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      this.setMeasuredDimension(0, 0);
   }

   public void draw(Canvas canvas) {
   }

   protected void dispatchDraw(Canvas canvas) {
   }

   public void setVisibility(int visibility) {
      if (this.mInflatedViewRef != null) {
         View view = (View)this.mInflatedViewRef.get();
         if (view == null) {
            throw new IllegalStateException("setVisibility called on un-referenced view");
         }

         view.setVisibility(visibility);
      } else {
         super.setVisibility(visibility);
         if (visibility == 0 || visibility == 4) {
            this.inflate();
         }
      }

   }

   public View inflate() {
      ViewParent viewParent = this.getParent();
      if (viewParent != null && viewParent instanceof ViewGroup) {
         if (this.mLayoutResource != 0) {
            ViewGroup parent = (ViewGroup)viewParent;
            LayoutInflater factory;
            if (this.mInflater != null) {
               factory = this.mInflater;
            } else {
               factory = LayoutInflater.from(this.getContext());
            }

            View view = factory.inflate(this.mLayoutResource, parent, false);
            if (this.mInflatedId != -1) {
               view.setId(this.mInflatedId);
            }

            int index = parent.indexOfChild(this);
            parent.removeViewInLayout(this);
            LayoutParams layoutParams = this.getLayoutParams();
            if (layoutParams != null) {
               parent.addView(view, index, layoutParams);
            } else {
               parent.addView(view, index);
            }

            this.mInflatedViewRef = new WeakReference(view);
            if (this.mInflateListener != null) {
               this.mInflateListener.onInflate(this, view);
            }

            return view;
         } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
         }
      } else {
         throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
      }
   }

   public void setOnInflateListener(ViewStubCompat.OnInflateListener inflateListener) {
      this.mInflateListener = inflateListener;
   }

   public interface OnInflateListener {
      void onInflate(ViewStubCompat var1, View var2);
   }
}
