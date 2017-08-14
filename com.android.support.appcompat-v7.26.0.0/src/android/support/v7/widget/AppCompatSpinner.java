package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
   private static final int[] ATTRS_ANDROID_SPINNERMODE = new int[]{16843505};
   private static final int MAX_ITEMS_MEASURED = 15;
   private static final String TAG = "AppCompatSpinner";
   private static final int MODE_DIALOG = 0;
   private static final int MODE_DROPDOWN = 1;
   private static final int MODE_THEME = -1;
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   private final Context mPopupContext;
   private ForwardingListener mForwardingListener;
   private SpinnerAdapter mTempAdapter;
   private final boolean mPopupSet;
   private AppCompatSpinner.DropdownPopup mPopup;
   private int mDropDownWidth;
   private final Rect mTempRect;

   public AppCompatSpinner(Context context) {
      this(context, (AttributeSet)null);
   }

   public AppCompatSpinner(Context context, int mode) {
      this(context, (AttributeSet)null, attr.spinnerStyle, mode);
   }

   public AppCompatSpinner(Context context, AttributeSet attrs) {
      this(context, attrs, attr.spinnerStyle);
   }

   public AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
      this(context, attrs, defStyleAttr, -1);
   }

   public AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
      this(context, attrs, defStyleAttr, mode, (Theme)null);
   }

   public AppCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Theme popupTheme) {
      super(context, attrs, defStyleAttr);
      this.mTempRect = new Rect();
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.Spinner, defStyleAttr, 0);
      this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
      if (popupTheme != null) {
         this.mPopupContext = new ContextThemeWrapper(context, popupTheme);
      } else {
         int popupThemeResId = a.getResourceId(styleable.Spinner_popupTheme, 0);
         if (popupThemeResId != 0) {
            this.mPopupContext = new ContextThemeWrapper(context, popupThemeResId);
         } else {
            this.mPopupContext = VERSION.SDK_INT < 23 ? context : null;
         }
      }

      if (this.mPopupContext != null) {
         if (mode == -1) {
            if (VERSION.SDK_INT >= 11) {
               TypedArray aa = null;

               try {
                  aa = context.obtainStyledAttributes(attrs, ATTRS_ANDROID_SPINNERMODE, defStyleAttr, 0);
                  if (aa.hasValue(0)) {
                     mode = aa.getInt(0, 0);
                  }
               } catch (Exception var12) {
                  Log.i("AppCompatSpinner", "Could not read android:spinnerMode", var12);
               } finally {
                  if (aa != null) {
                     aa.recycle();
                  }

               }
            } else {
               mode = 1;
            }
         }

         if (mode == 1) {
            final AppCompatSpinner.DropdownPopup popup = new AppCompatSpinner.DropdownPopup(this.mPopupContext, attrs, defStyleAttr);
            TintTypedArray pa = TintTypedArray.obtainStyledAttributes(this.mPopupContext, attrs, styleable.Spinner, defStyleAttr, 0);
            this.mDropDownWidth = pa.getLayoutDimension(styleable.Spinner_android_dropDownWidth, -2);
            popup.setBackgroundDrawable(pa.getDrawable(styleable.Spinner_android_popupBackground));
            popup.setPromptText(a.getString(styleable.Spinner_android_prompt));
            pa.recycle();
            this.mPopup = popup;
            this.mForwardingListener = new ForwardingListener(this) {
               public ShowableListMenu getPopup() {
                  return popup;
               }

               public boolean onForwardingStarted() {
                  if (!AppCompatSpinner.this.mPopup.isShowing()) {
                     AppCompatSpinner.this.mPopup.show();
                  }

                  return true;
               }
            };
         }
      }

      CharSequence[] entries = a.getTextArray(styleable.Spinner_android_entries);
      if (entries != null) {
         ArrayAdapter adapter = new ArrayAdapter(context, 17367048, entries);
         adapter.setDropDownViewResource(layout.support_simple_spinner_dropdown_item);
         this.setAdapter((SpinnerAdapter)adapter);
      }

      a.recycle();
      this.mPopupSet = true;
      if (this.mTempAdapter != null) {
         this.setAdapter(this.mTempAdapter);
         this.mTempAdapter = null;
      }

      this.mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
   }

   public Context getPopupContext() {
      if (this.mPopup != null) {
         return this.mPopupContext;
      } else {
         return VERSION.SDK_INT >= 23 ? super.getPopupContext() : null;
      }
   }

   public void setPopupBackgroundDrawable(Drawable background) {
      if (this.mPopup != null) {
         this.mPopup.setBackgroundDrawable(background);
      } else if (VERSION.SDK_INT >= 16) {
         super.setPopupBackgroundDrawable(background);
      }

   }

   public void setPopupBackgroundResource(@DrawableRes int resId) {
      this.setPopupBackgroundDrawable(AppCompatResources.getDrawable(this.getPopupContext(), resId));
   }

   public Drawable getPopupBackground() {
      if (this.mPopup != null) {
         return this.mPopup.getBackground();
      } else {
         return VERSION.SDK_INT >= 16 ? super.getPopupBackground() : null;
      }
   }

   public void setDropDownVerticalOffset(int pixels) {
      if (this.mPopup != null) {
         this.mPopup.setVerticalOffset(pixels);
      } else if (VERSION.SDK_INT >= 16) {
         super.setDropDownVerticalOffset(pixels);
      }

   }

   public int getDropDownVerticalOffset() {
      if (this.mPopup != null) {
         return this.mPopup.getVerticalOffset();
      } else {
         return VERSION.SDK_INT >= 16 ? super.getDropDownVerticalOffset() : 0;
      }
   }

   public void setDropDownHorizontalOffset(int pixels) {
      if (this.mPopup != null) {
         this.mPopup.setHorizontalOffset(pixels);
      } else if (VERSION.SDK_INT >= 16) {
         super.setDropDownHorizontalOffset(pixels);
      }

   }

   public int getDropDownHorizontalOffset() {
      if (this.mPopup != null) {
         return this.mPopup.getHorizontalOffset();
      } else {
         return VERSION.SDK_INT >= 16 ? super.getDropDownHorizontalOffset() : 0;
      }
   }

   public void setDropDownWidth(int pixels) {
      if (this.mPopup != null) {
         this.mDropDownWidth = pixels;
      } else if (VERSION.SDK_INT >= 16) {
         super.setDropDownWidth(pixels);
      }

   }

   public int getDropDownWidth() {
      if (this.mPopup != null) {
         return this.mDropDownWidth;
      } else {
         return VERSION.SDK_INT >= 16 ? super.getDropDownWidth() : 0;
      }
   }

   public void setAdapter(SpinnerAdapter adapter) {
      if (!this.mPopupSet) {
         this.mTempAdapter = adapter;
      } else {
         super.setAdapter(adapter);
         if (this.mPopup != null) {
            Context popupContext = this.mPopupContext == null ? this.getContext() : this.mPopupContext;
            this.mPopup.setAdapter(new AppCompatSpinner.DropDownAdapter(adapter, popupContext.getTheme()));
         }

      }
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.mPopup != null && this.mPopup.isShowing()) {
         this.mPopup.dismiss();
      }

   }

   public boolean onTouchEvent(MotionEvent event) {
      return this.mForwardingListener != null && this.mForwardingListener.onTouch(this, event) ? true : super.onTouchEvent(event);
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      if (this.mPopup != null && MeasureSpec.getMode(widthMeasureSpec) == Integer.MIN_VALUE) {
         int measuredWidth = this.getMeasuredWidth();
         this.setMeasuredDimension(Math.min(Math.max(measuredWidth, this.compatMeasureContentWidth(this.getAdapter(), this.getBackground())), MeasureSpec.getSize(widthMeasureSpec)), this.getMeasuredHeight());
      }

   }

   public boolean performClick() {
      if (this.mPopup != null) {
         if (!this.mPopup.isShowing()) {
            this.mPopup.show();
         }

         return true;
      } else {
         return super.performClick();
      }
   }

   public void setPrompt(CharSequence prompt) {
      if (this.mPopup != null) {
         this.mPopup.setPromptText(prompt);
      } else {
         super.setPrompt(prompt);
      }

   }

   public CharSequence getPrompt() {
      return this.mPopup != null ? this.mPopup.getHintText() : super.getPrompt();
   }

   public void setBackgroundResource(@DrawableRes int resId) {
      super.setBackgroundResource(resId);
      if (this.mBackgroundTintHelper != null) {
         this.mBackgroundTintHelper.onSetBackgroundResource(resId);
      }

   }

   public void setBackgroundDrawable(Drawable background) {
      super.setBackgroundDrawable(background);
      if (this.mBackgroundTintHelper != null) {
         this.mBackgroundTintHelper.onSetBackgroundDrawable(background);
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setSupportBackgroundTintList(@Nullable ColorStateList tint) {
      if (this.mBackgroundTintHelper != null) {
         this.mBackgroundTintHelper.setSupportBackgroundTintList(tint);
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @Nullable
   public ColorStateList getSupportBackgroundTintList() {
      return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setSupportBackgroundTintMode(@Nullable Mode tintMode) {
      if (this.mBackgroundTintHelper != null) {
         this.mBackgroundTintHelper.setSupportBackgroundTintMode(tintMode);
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @Nullable
   public Mode getSupportBackgroundTintMode() {
      return this.mBackgroundTintHelper != null ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      if (this.mBackgroundTintHelper != null) {
         this.mBackgroundTintHelper.applySupportBackgroundTint();
      }

   }

   int compatMeasureContentWidth(SpinnerAdapter adapter, Drawable background) {
      if (adapter == null) {
         return 0;
      } else {
         int width = 0;
         View itemView = null;
         int itemType = 0;
         int widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
         int heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
         int start = Math.max(0, this.getSelectedItemPosition());
         int end = Math.min(adapter.getCount(), start + 15);
         int count = end - start;
         start = Math.max(0, start - (15 - count));

         for(int i = start; i < end; ++i) {
            int positionType = adapter.getItemViewType(i);
            if (positionType != itemType) {
               itemType = positionType;
               itemView = null;
            }

            itemView = adapter.getView(i, itemView, this);
            if (itemView.getLayoutParams() == null) {
               itemView.setLayoutParams(new LayoutParams(-2, -2));
            }

            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            width = Math.max(width, itemView.getMeasuredWidth());
         }

         if (background != null) {
            background.getPadding(this.mTempRect);
            width += this.mTempRect.left + this.mTempRect.right;
         }

         return width;
      }
   }

   private class DropdownPopup extends ListPopupWindow {
      private CharSequence mHintText;
      ListAdapter mAdapter;
      private final Rect mVisibleRect = new Rect();

      public DropdownPopup(Context context, AttributeSet attrs, int defStyleAttr) {
         super(context, attrs, defStyleAttr);
         this.setAnchorView(AppCompatSpinner.this);
         this.setModal(true);
         this.setPromptPosition(0);
         this.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
               AppCompatSpinner.this.setSelection(position);
               if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                  AppCompatSpinner.this.performItemClick(v, position, DropdownPopup.this.mAdapter.getItemId(position));
               }

               DropdownPopup.this.dismiss();
            }
         });
      }

      public void setAdapter(ListAdapter adapter) {
         super.setAdapter(adapter);
         this.mAdapter = adapter;
      }

      public CharSequence getHintText() {
         return this.mHintText;
      }

      public void setPromptText(CharSequence hintText) {
         this.mHintText = hintText;
      }

      void computeContentWidth() {
         Drawable background = this.getBackground();
         int hOffset = 0;
         if (background != null) {
            background.getPadding(AppCompatSpinner.this.mTempRect);
            hOffset = ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? AppCompatSpinner.this.mTempRect.right : -AppCompatSpinner.this.mTempRect.left;
         } else {
            AppCompatSpinner.this.mTempRect.left = AppCompatSpinner.this.mTempRect.right = 0;
         }

         int spinnerPaddingLeft = AppCompatSpinner.this.getPaddingLeft();
         int spinnerPaddingRight = AppCompatSpinner.this.getPaddingRight();
         int spinnerWidth = AppCompatSpinner.this.getWidth();
         if (AppCompatSpinner.this.mDropDownWidth == -2) {
            int contentWidth = AppCompatSpinner.this.compatMeasureContentWidth((SpinnerAdapter)this.mAdapter, this.getBackground());
            int contentWidthLimit = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.mTempRect.left - AppCompatSpinner.this.mTempRect.right;
            if (contentWidth > contentWidthLimit) {
               contentWidth = contentWidthLimit;
            }

            this.setContentWidth(Math.max(contentWidth, spinnerWidth - spinnerPaddingLeft - spinnerPaddingRight));
         } else if (AppCompatSpinner.this.mDropDownWidth == -1) {
            this.setContentWidth(spinnerWidth - spinnerPaddingLeft - spinnerPaddingRight);
         } else {
            this.setContentWidth(AppCompatSpinner.this.mDropDownWidth);
         }

         if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
            hOffset += spinnerWidth - spinnerPaddingRight - this.getWidth();
         } else {
            hOffset += spinnerPaddingLeft;
         }

         this.setHorizontalOffset(hOffset);
      }

      public void show() {
         boolean wasShowing = this.isShowing();
         this.computeContentWidth();
         this.setInputMethodMode(2);
         super.show();
         ListView listView = this.getListView();
         listView.setChoiceMode(1);
         this.setSelection(AppCompatSpinner.this.getSelectedItemPosition());
         if (!wasShowing) {
            ViewTreeObserver vto = AppCompatSpinner.this.getViewTreeObserver();
            if (vto != null) {
               final OnGlobalLayoutListener layoutListener = new OnGlobalLayoutListener() {
                  public void onGlobalLayout() {
                     if (!DropdownPopup.this.isVisibleToUser(AppCompatSpinner.this)) {
                        DropdownPopup.this.dismiss();
                     } else {
                        DropdownPopup.this.computeContentWidth();
                        AppCompatSpinner.DropdownPopup.super.show();
                     }

                  }
               };
               vto.addOnGlobalLayoutListener(layoutListener);
               this.setOnDismissListener(new OnDismissListener() {
                  public void onDismiss() {
                     ViewTreeObserver vto = AppCompatSpinner.this.getViewTreeObserver();
                     if (vto != null) {
                        vto.removeGlobalOnLayoutListener(layoutListener);
                     }

                  }
               });
            }

         }
      }

      boolean isVisibleToUser(View view) {
         return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.mVisibleRect);
      }
   }

   private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
      private SpinnerAdapter mAdapter;
      private ListAdapter mListAdapter;

      public DropDownAdapter(@Nullable SpinnerAdapter adapter, @Nullable Theme dropDownTheme) {
         this.mAdapter = adapter;
         if (adapter instanceof ListAdapter) {
            this.mListAdapter = (ListAdapter)adapter;
         }

         if (dropDownTheme != null) {
            if (VERSION.SDK_INT >= 23 && adapter instanceof android.widget.ThemedSpinnerAdapter) {
               android.widget.ThemedSpinnerAdapter themedAdapter = (android.widget.ThemedSpinnerAdapter)adapter;
               if (themedAdapter.getDropDownViewTheme() != dropDownTheme) {
                  themedAdapter.setDropDownViewTheme(dropDownTheme);
               }
            } else if (adapter instanceof ThemedSpinnerAdapter) {
               ThemedSpinnerAdapter themedAdapter = (ThemedSpinnerAdapter)adapter;
               if (themedAdapter.getDropDownViewTheme() == null) {
                  themedAdapter.setDropDownViewTheme(dropDownTheme);
               }
            }
         }

      }

      public int getCount() {
         return this.mAdapter == null ? 0 : this.mAdapter.getCount();
      }

      public Object getItem(int position) {
         return this.mAdapter == null ? null : this.mAdapter.getItem(position);
      }

      public long getItemId(int position) {
         return this.mAdapter == null ? -1L : this.mAdapter.getItemId(position);
      }

      public View getView(int position, View convertView, ViewGroup parent) {
         return this.getDropDownView(position, convertView, parent);
      }

      public View getDropDownView(int position, View convertView, ViewGroup parent) {
         return this.mAdapter == null ? null : this.mAdapter.getDropDownView(position, convertView, parent);
      }

      public boolean hasStableIds() {
         return this.mAdapter != null && this.mAdapter.hasStableIds();
      }

      public void registerDataSetObserver(DataSetObserver observer) {
         if (this.mAdapter != null) {
            this.mAdapter.registerDataSetObserver(observer);
         }

      }

      public void unregisterDataSetObserver(DataSetObserver observer) {
         if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(observer);
         }

      }

      public boolean areAllItemsEnabled() {
         ListAdapter adapter = this.mListAdapter;
         return adapter != null ? adapter.areAllItemsEnabled() : true;
      }

      public boolean isEnabled(int position) {
         ListAdapter adapter = this.mListAdapter;
         return adapter != null ? adapter.isEnabled(position) : true;
      }

      public int getItemViewType(int position) {
         return 0;
      }

      public int getViewTypeCount() {
         return 1;
      }

      public boolean isEmpty() {
         return this.getCount() == 0;
      }
   }
}
