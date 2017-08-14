package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.KeyEvent.DispatcherState;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
   private static final String TAG = "ListPopupWindow";
   private static final boolean DEBUG = false;
   static final int EXPAND_LIST_TIMEOUT = 250;
   private static Method sClipToWindowEnabledMethod;
   private static Method sGetMaxAvailableHeightMethod;
   private static Method sSetEpicenterBoundsMethod;
   private Context mContext;
   private ListAdapter mAdapter;
   DropDownListView mDropDownList;
   private int mDropDownHeight;
   private int mDropDownWidth;
   private int mDropDownHorizontalOffset;
   private int mDropDownVerticalOffset;
   private int mDropDownWindowLayoutType;
   private boolean mDropDownVerticalOffsetSet;
   private boolean mIsAnimatedFromAnchor;
   private boolean mOverlapAnchor;
   private boolean mOverlapAnchorSet;
   private int mDropDownGravity;
   private boolean mDropDownAlwaysVisible;
   private boolean mForceIgnoreOutsideTouch;
   int mListItemExpandMaximum;
   private View mPromptView;
   private int mPromptPosition;
   private DataSetObserver mObserver;
   private View mDropDownAnchorView;
   private Drawable mDropDownListHighlight;
   private OnItemClickListener mItemClickListener;
   private OnItemSelectedListener mItemSelectedListener;
   final ListPopupWindow.ResizePopupRunnable mResizePopupRunnable;
   private final ListPopupWindow.PopupTouchInterceptor mTouchInterceptor;
   private final ListPopupWindow.PopupScrollListener mScrollListener;
   private final ListPopupWindow.ListSelectorHider mHideSelector;
   private Runnable mShowDropDownRunnable;
   final Handler mHandler;
   private final Rect mTempRect;
   private Rect mEpicenterBounds;
   private boolean mModal;
   PopupWindow mPopup;
   public static final int POSITION_PROMPT_ABOVE = 0;
   public static final int POSITION_PROMPT_BELOW = 1;
   public static final int MATCH_PARENT = -1;
   public static final int WRAP_CONTENT = -2;
   public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
   public static final int INPUT_METHOD_NEEDED = 1;
   public static final int INPUT_METHOD_NOT_NEEDED = 2;

   public ListPopupWindow(@NonNull Context context) {
      this(context, (AttributeSet)null, attr.listPopupWindowStyle);
   }

   public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs) {
      this(context, attrs, attr.listPopupWindowStyle);
   }

   public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
      this(context, attrs, defStyleAttr, 0);
   }

   public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
      this.mDropDownHeight = -2;
      this.mDropDownWidth = -2;
      this.mDropDownWindowLayoutType = 1002;
      this.mIsAnimatedFromAnchor = true;
      this.mDropDownGravity = 0;
      this.mDropDownAlwaysVisible = false;
      this.mForceIgnoreOutsideTouch = false;
      this.mListItemExpandMaximum = Integer.MAX_VALUE;
      this.mPromptPosition = 0;
      this.mResizePopupRunnable = new ListPopupWindow.ResizePopupRunnable();
      this.mTouchInterceptor = new ListPopupWindow.PopupTouchInterceptor();
      this.mScrollListener = new ListPopupWindow.PopupScrollListener();
      this.mHideSelector = new ListPopupWindow.ListSelectorHider();
      this.mTempRect = new Rect();
      this.mContext = context;
      this.mHandler = new Handler(context.getMainLooper());
      TypedArray a = context.obtainStyledAttributes(attrs, styleable.ListPopupWindow, defStyleAttr, defStyleRes);
      this.mDropDownHorizontalOffset = a.getDimensionPixelOffset(styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
      this.mDropDownVerticalOffset = a.getDimensionPixelOffset(styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
      if (this.mDropDownVerticalOffset != 0) {
         this.mDropDownVerticalOffsetSet = true;
      }

      a.recycle();
      this.mPopup = new AppCompatPopupWindow(context, attrs, defStyleAttr, defStyleRes);
      this.mPopup.setInputMethodMode(1);
   }

   public void setAdapter(@Nullable ListAdapter adapter) {
      if (this.mObserver == null) {
         this.mObserver = new ListPopupWindow.PopupDataSetObserver();
      } else if (this.mAdapter != null) {
         this.mAdapter.unregisterDataSetObserver(this.mObserver);
      }

      this.mAdapter = adapter;
      if (this.mAdapter != null) {
         adapter.registerDataSetObserver(this.mObserver);
      }

      if (this.mDropDownList != null) {
         this.mDropDownList.setAdapter(this.mAdapter);
      }

   }

   public void setPromptPosition(int position) {
      this.mPromptPosition = position;
   }

   public int getPromptPosition() {
      return this.mPromptPosition;
   }

   public void setModal(boolean modal) {
      this.mModal = modal;
      this.mPopup.setFocusable(modal);
   }

   public boolean isModal() {
      return this.mModal;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setForceIgnoreOutsideTouch(boolean forceIgnoreOutsideTouch) {
      this.mForceIgnoreOutsideTouch = forceIgnoreOutsideTouch;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setDropDownAlwaysVisible(boolean dropDownAlwaysVisible) {
      this.mDropDownAlwaysVisible = dropDownAlwaysVisible;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public boolean isDropDownAlwaysVisible() {
      return this.mDropDownAlwaysVisible;
   }

   public void setSoftInputMode(int mode) {
      this.mPopup.setSoftInputMode(mode);
   }

   public int getSoftInputMode() {
      return this.mPopup.getSoftInputMode();
   }

   public void setListSelector(Drawable selector) {
      this.mDropDownListHighlight = selector;
   }

   @Nullable
   public Drawable getBackground() {
      return this.mPopup.getBackground();
   }

   public void setBackgroundDrawable(@Nullable Drawable d) {
      this.mPopup.setBackgroundDrawable(d);
   }

   public void setAnimationStyle(@StyleRes int animationStyle) {
      this.mPopup.setAnimationStyle(animationStyle);
   }

   @StyleRes
   public int getAnimationStyle() {
      return this.mPopup.getAnimationStyle();
   }

   @Nullable
   public View getAnchorView() {
      return this.mDropDownAnchorView;
   }

   public void setAnchorView(@Nullable View anchor) {
      this.mDropDownAnchorView = anchor;
   }

   public int getHorizontalOffset() {
      return this.mDropDownHorizontalOffset;
   }

   public void setHorizontalOffset(int offset) {
      this.mDropDownHorizontalOffset = offset;
   }

   public int getVerticalOffset() {
      return !this.mDropDownVerticalOffsetSet ? 0 : this.mDropDownVerticalOffset;
   }

   public void setVerticalOffset(int offset) {
      this.mDropDownVerticalOffset = offset;
      this.mDropDownVerticalOffsetSet = true;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setEpicenterBounds(Rect bounds) {
      this.mEpicenterBounds = bounds;
   }

   public void setDropDownGravity(int gravity) {
      this.mDropDownGravity = gravity;
   }

   public int getWidth() {
      return this.mDropDownWidth;
   }

   public void setWidth(int width) {
      this.mDropDownWidth = width;
   }

   public void setContentWidth(int width) {
      Drawable popupBackground = this.mPopup.getBackground();
      if (popupBackground != null) {
         popupBackground.getPadding(this.mTempRect);
         this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + width;
      } else {
         this.setWidth(width);
      }

   }

   public int getHeight() {
      return this.mDropDownHeight;
   }

   public void setHeight(int height) {
      if (height < 0 && -2 != height && -1 != height) {
         throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
      } else {
         this.mDropDownHeight = height;
      }
   }

   public void setWindowLayoutType(int layoutType) {
      this.mDropDownWindowLayoutType = layoutType;
   }

   public void setOnItemClickListener(@Nullable OnItemClickListener clickListener) {
      this.mItemClickListener = clickListener;
   }

   public void setOnItemSelectedListener(@Nullable OnItemSelectedListener selectedListener) {
      this.mItemSelectedListener = selectedListener;
   }

   public void setPromptView(@Nullable View prompt) {
      boolean showing = this.isShowing();
      if (showing) {
         this.removePromptView();
      }

      this.mPromptView = prompt;
      if (showing) {
         this.show();
      }

   }

   public void postShow() {
      this.mHandler.post(this.mShowDropDownRunnable);
   }

   public void show() {
      int height = this.buildDropDown();
      boolean noInputMethod = this.isInputMethodNotNeeded();
      PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
      int widthSpec;
      int heightSpec;
      if (this.mPopup.isShowing()) {
         if (!ViewCompat.isAttachedToWindow(this.getAnchorView())) {
            return;
         }

         if (this.mDropDownWidth == -1) {
            widthSpec = -1;
         } else if (this.mDropDownWidth == -2) {
            widthSpec = this.getAnchorView().getWidth();
         } else {
            widthSpec = this.mDropDownWidth;
         }

         if (this.mDropDownHeight == -1) {
            heightSpec = noInputMethod ? height : -1;
            if (noInputMethod) {
               this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
               this.mPopup.setHeight(0);
            } else {
               this.mPopup.setWidth(this.mDropDownWidth == -1 ? -1 : 0);
               this.mPopup.setHeight(-1);
            }
         } else if (this.mDropDownHeight == -2) {
            heightSpec = height;
         } else {
            heightSpec = this.mDropDownHeight;
         }

         this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible);
         this.mPopup.update(this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, widthSpec < 0 ? -1 : widthSpec, heightSpec < 0 ? -1 : heightSpec);
      } else {
         if (this.mDropDownWidth == -1) {
            widthSpec = -1;
         } else if (this.mDropDownWidth == -2) {
            widthSpec = this.getAnchorView().getWidth();
         } else {
            widthSpec = this.mDropDownWidth;
         }

         if (this.mDropDownHeight == -1) {
            heightSpec = -1;
         } else if (this.mDropDownHeight == -2) {
            heightSpec = height;
         } else {
            heightSpec = this.mDropDownHeight;
         }

         this.mPopup.setWidth(widthSpec);
         this.mPopup.setHeight(heightSpec);
         this.setPopupClipToScreenEnabled(true);
         this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible);
         this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
         if (this.mOverlapAnchorSet) {
            PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
         }

         if (sSetEpicenterBoundsMethod != null) {
            try {
               sSetEpicenterBoundsMethod.invoke(this.mPopup, this.mEpicenterBounds);
            } catch (Exception var6) {
               Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", var6);
            }
         }

         PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
         this.mDropDownList.setSelection(-1);
         if (!this.mModal || this.mDropDownList.isInTouchMode()) {
            this.clearListSelection();
         }

         if (!this.mModal) {
            this.mHandler.post(this.mHideSelector);
         }
      }

   }

   public void dismiss() {
      this.mPopup.dismiss();
      this.removePromptView();
      this.mPopup.setContentView((View)null);
      this.mDropDownList = null;
      this.mHandler.removeCallbacks(this.mResizePopupRunnable);
   }

   public void setOnDismissListener(@Nullable OnDismissListener listener) {
      this.mPopup.setOnDismissListener(listener);
   }

   private void removePromptView() {
      if (this.mPromptView != null) {
         ViewParent parent = this.mPromptView.getParent();
         if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)parent;
            group.removeView(this.mPromptView);
         }
      }

   }

   public void setInputMethodMode(int mode) {
      this.mPopup.setInputMethodMode(mode);
   }

   public int getInputMethodMode() {
      return this.mPopup.getInputMethodMode();
   }

   public void setSelection(int position) {
      DropDownListView list = this.mDropDownList;
      if (this.isShowing() && list != null) {
         list.setListSelectionHidden(false);
         list.setSelection(position);
         if (list.getChoiceMode() != 0) {
            list.setItemChecked(position, true);
         }
      }

   }

   public void clearListSelection() {
      DropDownListView list = this.mDropDownList;
      if (list != null) {
         list.setListSelectionHidden(true);
         list.requestLayout();
      }

   }

   public boolean isShowing() {
      return this.mPopup.isShowing();
   }

   public boolean isInputMethodNotNeeded() {
      return this.mPopup.getInputMethodMode() == 2;
   }

   public boolean performItemClick(int position) {
      if (this.isShowing()) {
         if (this.mItemClickListener != null) {
            DropDownListView list = this.mDropDownList;
            View child = list.getChildAt(position - list.getFirstVisiblePosition());
            ListAdapter adapter = list.getAdapter();
            this.mItemClickListener.onItemClick(list, child, position, adapter.getItemId(position));
         }

         return true;
      } else {
         return false;
      }
   }

   @Nullable
   public Object getSelectedItem() {
      return !this.isShowing() ? null : this.mDropDownList.getSelectedItem();
   }

   public int getSelectedItemPosition() {
      return !this.isShowing() ? -1 : this.mDropDownList.getSelectedItemPosition();
   }

   public long getSelectedItemId() {
      return !this.isShowing() ? Long.MIN_VALUE : this.mDropDownList.getSelectedItemId();
   }

   @Nullable
   public View getSelectedView() {
      return !this.isShowing() ? null : this.mDropDownList.getSelectedView();
   }

   @Nullable
   public ListView getListView() {
      return this.mDropDownList;
   }

   @NonNull
   DropDownListView createDropDownListView(Context context, boolean hijackFocus) {
      return new DropDownListView(context, hijackFocus);
   }

   void setListItemExpandMax(int max) {
      this.mListItemExpandMaximum = max;
   }

   public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
      if (this.isShowing() && keyCode != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(keyCode))) {
         int curIndex = this.mDropDownList.getSelectedItemPosition();
         boolean below = !this.mPopup.isAboveAnchor();
         ListAdapter adapter = this.mAdapter;
         int firstItem = Integer.MAX_VALUE;
         int lastItem = Integer.MIN_VALUE;
         if (adapter != null) {
            boolean allEnabled = adapter.areAllItemsEnabled();
            firstItem = allEnabled ? 0 : this.mDropDownList.lookForSelectablePosition(0, true);
            lastItem = allEnabled ? adapter.getCount() - 1 : this.mDropDownList.lookForSelectablePosition(adapter.getCount() - 1, false);
         }

         if (below && keyCode == 19 && curIndex <= firstItem || !below && keyCode == 20 && curIndex >= lastItem) {
            this.clearListSelection();
            this.mPopup.setInputMethodMode(1);
            this.show();
            return true;
         }

         this.mDropDownList.setListSelectionHidden(false);
         boolean consumed = this.mDropDownList.onKeyDown(keyCode, event);
         if (consumed) {
            this.mPopup.setInputMethodMode(2);
            this.mDropDownList.requestFocusFromTouch();
            this.show();
            switch(keyCode) {
            case 19:
            case 20:
            case 23:
            case 66:
               return true;
            }
         } else if (below && keyCode == 20) {
            if (curIndex == lastItem) {
               return true;
            }
         } else if (!below && keyCode == 19 && curIndex == firstItem) {
            return true;
         }
      }

      return false;
   }

   public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
      if (this.isShowing() && this.mDropDownList.getSelectedItemPosition() >= 0) {
         boolean consumed = this.mDropDownList.onKeyUp(keyCode, event);
         if (consumed && isConfirmKey(keyCode)) {
            this.dismiss();
         }

         return consumed;
      } else {
         return false;
      }
   }

   public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent event) {
      if (keyCode == 4 && this.isShowing()) {
         View anchorView = this.mDropDownAnchorView;
         DispatcherState state;
         if (event.getAction() == 0 && event.getRepeatCount() == 0) {
            state = anchorView.getKeyDispatcherState();
            if (state != null) {
               state.startTracking(event, this);
            }

            return true;
         }

         if (event.getAction() == 1) {
            state = anchorView.getKeyDispatcherState();
            if (state != null) {
               state.handleUpEvent(event);
            }

            if (event.isTracking() && !event.isCanceled()) {
               this.dismiss();
               return true;
            }
         }
      }

      return false;
   }

   public OnTouchListener createDragToOpenListener(View src) {
      return new ForwardingListener(src) {
         public ListPopupWindow getPopup() {
            return ListPopupWindow.this;
         }
      };
   }

   private int buildDropDown() {
      int otherHeights = 0;
      int widthSize;
      int widthMode;
      int widthSpec;
      if (this.mDropDownList == null) {
         Context context = this.mContext;
         this.mShowDropDownRunnable = new Runnable() {
            public void run() {
               View view = ListPopupWindow.this.getAnchorView();
               if (view != null && view.getWindowToken() != null) {
                  ListPopupWindow.this.show();
               }

            }
         };
         this.mDropDownList = this.createDropDownListView(context, !this.mModal);
         if (this.mDropDownListHighlight != null) {
            this.mDropDownList.setSelector(this.mDropDownListHighlight);
         }

         this.mDropDownList.setAdapter(this.mAdapter);
         this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
         this.mDropDownList.setFocusable(true);
         this.mDropDownList.setFocusableInTouchMode(true);
         this.mDropDownList.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
               if (position != -1) {
                  DropDownListView dropDownList = ListPopupWindow.this.mDropDownList;
                  if (dropDownList != null) {
                     dropDownList.setListSelectionHidden(false);
                  }
               }

            }

            public void onNothingSelected(AdapterView parent) {
            }
         });
         this.mDropDownList.setOnScrollListener(this.mScrollListener);
         if (this.mItemSelectedListener != null) {
            this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
         }

         ViewGroup dropDownView = this.mDropDownList;
         View hintView = this.mPromptView;
         if (hintView != null) {
            LinearLayout hintContainer = new LinearLayout(context);
            hintContainer.setOrientation(1);
            LayoutParams hintParams = new LayoutParams(-1, 0, 1.0F);
            switch(this.mPromptPosition) {
            case 0:
               hintContainer.addView(hintView);
               hintContainer.addView((View)dropDownView, hintParams);
               break;
            case 1:
               hintContainer.addView((View)dropDownView, hintParams);
               hintContainer.addView(hintView);
               break;
            default:
               Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
            }

            if (this.mDropDownWidth >= 0) {
               widthMode = Integer.MIN_VALUE;
               widthSize = this.mDropDownWidth;
            } else {
               widthMode = 0;
               widthSize = 0;
            }

            widthSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode);
            int heightSpec = false;
            hintView.measure(widthSpec, 0);
            hintParams = (LayoutParams)hintView.getLayoutParams();
            otherHeights = hintView.getMeasuredHeight() + hintParams.topMargin + hintParams.bottomMargin;
            dropDownView = hintContainer;
         }

         this.mPopup.setContentView((View)dropDownView);
      } else {
         ViewGroup dropDownView = (ViewGroup)this.mPopup.getContentView();
         View view = this.mPromptView;
         if (view != null) {
            LayoutParams hintParams = (LayoutParams)view.getLayoutParams();
            otherHeights = view.getMeasuredHeight() + hintParams.topMargin + hintParams.bottomMargin;
         }
      }

      Drawable background = this.mPopup.getBackground();
      int padding;
      if (background != null) {
         background.getPadding(this.mTempRect);
         padding = this.mTempRect.top + this.mTempRect.bottom;
         if (!this.mDropDownVerticalOffsetSet) {
            this.mDropDownVerticalOffset = -this.mTempRect.top;
         }
      } else {
         this.mTempRect.setEmpty();
         padding = 0;
      }

      boolean ignoreBottomDecorations = this.mPopup.getInputMethodMode() == 2;
      int maxHeight = this.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset, ignoreBottomDecorations);
      if (!this.mDropDownAlwaysVisible && this.mDropDownHeight != -1) {
         int var10001;
         switch(this.mDropDownWidth) {
         case -2:
            var10001 = this.mTempRect.left + this.mTempRect.right;
            widthSize = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - var10001, Integer.MIN_VALUE);
            break;
         case -1:
            var10001 = this.mTempRect.left + this.mTempRect.right;
            widthSize = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - var10001, 1073741824);
            break;
         default:
            widthSize = MeasureSpec.makeMeasureSpec(this.mDropDownWidth, 1073741824);
         }

         widthMode = this.mDropDownList.measureHeightOfChildrenCompat(widthSize, 0, -1, maxHeight - otherHeights, -1);
         if (widthMode > 0) {
            widthSpec = this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom();
            otherHeights += padding + widthSpec;
         }

         return widthMode + otherHeights;
      } else {
         return maxHeight + padding;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setOverlapAnchor(boolean overlapAnchor) {
      this.mOverlapAnchorSet = true;
      this.mOverlapAnchor = overlapAnchor;
   }

   private static boolean isConfirmKey(int keyCode) {
      return keyCode == 66 || keyCode == 23;
   }

   private void setPopupClipToScreenEnabled(boolean clip) {
      if (sClipToWindowEnabledMethod != null) {
         try {
            sClipToWindowEnabledMethod.invoke(this.mPopup, clip);
         } catch (Exception var3) {
            Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
         }
      }

   }

   private int getMaxAvailableHeight(View anchor, int yOffset, boolean ignoreBottomDecorations) {
      if (sGetMaxAvailableHeightMethod != null) {
         try {
            return ((Integer)sGetMaxAvailableHeightMethod.invoke(this.mPopup, anchor, yOffset, ignoreBottomDecorations)).intValue();
         } catch (Exception var5) {
            Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
         }
      }

      return this.mPopup.getMaxAvailableHeight(anchor, yOffset);
   }

   static {
      try {
         sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
      } catch (NoSuchMethodException var3) {
         Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
      }

      try {
         sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
      } catch (NoSuchMethodException var2) {
         Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
      }

      try {
         sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
      } catch (NoSuchMethodException var1) {
         Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
      }

   }

   private class PopupScrollListener implements OnScrollListener {
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
      }

      public void onScrollStateChanged(AbsListView view, int scrollState) {
         if (scrollState == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
            ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
            ListPopupWindow.this.mResizePopupRunnable.run();
         }

      }
   }

   private class PopupTouchInterceptor implements OnTouchListener {
      public boolean onTouch(View v, MotionEvent event) {
         int action = event.getAction();
         int x = (int)event.getX();
         int y = (int)event.getY();
         if (action == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && x >= 0 && x < ListPopupWindow.this.mPopup.getWidth() && y >= 0 && y < ListPopupWindow.this.mPopup.getHeight()) {
            ListPopupWindow.this.mHandler.postDelayed(ListPopupWindow.this.mResizePopupRunnable, 250L);
         } else if (action == 1) {
            ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
         }

         return false;
      }
   }

   private class ResizePopupRunnable implements Runnable {
      public void run() {
         if (ListPopupWindow.this.mDropDownList != null && ViewCompat.isAttachedToWindow(ListPopupWindow.this.mDropDownList) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
            ListPopupWindow.this.mPopup.setInputMethodMode(2);
            ListPopupWindow.this.show();
         }

      }
   }

   private class ListSelectorHider implements Runnable {
      public void run() {
         ListPopupWindow.this.clearListSelection();
      }
   }

   private class PopupDataSetObserver extends DataSetObserver {
      public void onChanged() {
         if (ListPopupWindow.this.isShowing()) {
            ListPopupWindow.this.show();
         }

      }

      public void onInvalidated() {
         ListPopupWindow.this.dismiss();
      }
   }
}
