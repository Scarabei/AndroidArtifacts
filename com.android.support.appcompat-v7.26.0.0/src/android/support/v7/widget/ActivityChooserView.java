package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.string;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ActivityChooserView extends ViewGroup implements ActivityChooserModel.ActivityChooserModelClient {
   private static final String LOG_TAG = "ActivityChooserView";
   final ActivityChooserView.ActivityChooserViewAdapter mAdapter;
   private final ActivityChooserView.Callbacks mCallbacks;
   private final LinearLayoutCompat mActivityChooserContent;
   private final Drawable mActivityChooserContentBackground;
   final FrameLayout mExpandActivityOverflowButton;
   private final ImageView mExpandActivityOverflowButtonImage;
   final FrameLayout mDefaultActivityButton;
   private final ImageView mDefaultActivityButtonImage;
   private final int mListPopupMaxWidth;
   ActionProvider mProvider;
   final DataSetObserver mModelDataSetObserver;
   private final OnGlobalLayoutListener mOnGlobalLayoutListener;
   private ListPopupWindow mListPopupWindow;
   OnDismissListener mOnDismissListener;
   boolean mIsSelectingDefaultActivity;
   int mInitialActivityCount;
   private boolean mIsAttachedToWindow;
   private int mDefaultActionButtonContentDescription;

   public ActivityChooserView(Context context) {
      this(context, (AttributeSet)null);
   }

   public ActivityChooserView(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public ActivityChooserView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      this.mModelDataSetObserver = new DataSetObserver() {
         public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.mAdapter.notifyDataSetChanged();
         }

         public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.mAdapter.notifyDataSetInvalidated();
         }
      };
      this.mOnGlobalLayoutListener = new OnGlobalLayoutListener() {
         public void onGlobalLayout() {
            if (ActivityChooserView.this.isShowingPopup()) {
               if (!ActivityChooserView.this.isShown()) {
                  ActivityChooserView.this.getListPopupWindow().dismiss();
               } else {
                  ActivityChooserView.this.getListPopupWindow().show();
                  if (ActivityChooserView.this.mProvider != null) {
                     ActivityChooserView.this.mProvider.subUiVisibilityChanged(true);
                  }
               }
            }

         }
      };
      this.mInitialActivityCount = 4;
      TypedArray attributesArray = context.obtainStyledAttributes(attrs, styleable.ActivityChooserView, defStyle, 0);
      this.mInitialActivityCount = attributesArray.getInt(styleable.ActivityChooserView_initialActivityCount, 4);
      Drawable expandActivityOverflowButtonDrawable = attributesArray.getDrawable(styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
      attributesArray.recycle();
      LayoutInflater inflater = LayoutInflater.from(this.getContext());
      inflater.inflate(layout.abc_activity_chooser_view, this, true);
      this.mCallbacks = new ActivityChooserView.Callbacks();
      this.mActivityChooserContent = (LinearLayoutCompat)this.findViewById(id.activity_chooser_view_content);
      this.mActivityChooserContentBackground = this.mActivityChooserContent.getBackground();
      this.mDefaultActivityButton = (FrameLayout)this.findViewById(id.default_activity_button);
      this.mDefaultActivityButton.setOnClickListener(this.mCallbacks);
      this.mDefaultActivityButton.setOnLongClickListener(this.mCallbacks);
      this.mDefaultActivityButtonImage = (ImageView)this.mDefaultActivityButton.findViewById(id.image);
      FrameLayout expandButton = (FrameLayout)this.findViewById(id.expand_activities_button);
      expandButton.setOnClickListener(this.mCallbacks);
      expandButton.setAccessibilityDelegate(new AccessibilityDelegate() {
         public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            AccessibilityNodeInfoCompat.wrap(info).setCanOpenPopup(true);
         }
      });
      expandButton.setOnTouchListener(new ForwardingListener(expandButton) {
         public ShowableListMenu getPopup() {
            return ActivityChooserView.this.getListPopupWindow();
         }

         protected boolean onForwardingStarted() {
            ActivityChooserView.this.showPopup();
            return true;
         }

         protected boolean onForwardingStopped() {
            ActivityChooserView.this.dismissPopup();
            return true;
         }
      });
      this.mExpandActivityOverflowButton = expandButton;
      this.mExpandActivityOverflowButtonImage = (ImageView)expandButton.findViewById(id.image);
      this.mExpandActivityOverflowButtonImage.setImageDrawable(expandActivityOverflowButtonDrawable);
      this.mAdapter = new ActivityChooserView.ActivityChooserViewAdapter();
      this.mAdapter.registerDataSetObserver(new DataSetObserver() {
         public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.updateAppearance();
         }
      });
      Resources resources = context.getResources();
      this.mListPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
   }

   public void setActivityChooserModel(ActivityChooserModel dataModel) {
      this.mAdapter.setDataModel(dataModel);
      if (this.isShowingPopup()) {
         this.dismissPopup();
         this.showPopup();
      }

   }

   public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
      this.mExpandActivityOverflowButtonImage.setImageDrawable(drawable);
   }

   public void setExpandActivityOverflowButtonContentDescription(int resourceId) {
      CharSequence contentDescription = this.getContext().getString(resourceId);
      this.mExpandActivityOverflowButtonImage.setContentDescription(contentDescription);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setProvider(ActionProvider provider) {
      this.mProvider = provider;
   }

   public boolean showPopup() {
      if (!this.isShowingPopup() && this.mIsAttachedToWindow) {
         this.mIsSelectingDefaultActivity = false;
         this.showPopupUnchecked(this.mInitialActivityCount);
         return true;
      } else {
         return false;
      }
   }

   void showPopupUnchecked(int maxActivityCount) {
      if (this.mAdapter.getDataModel() == null) {
         throw new IllegalStateException("No data model. Did you call #setDataModel?");
      } else {
         this.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
         boolean defaultActivityButtonShown = this.mDefaultActivityButton.getVisibility() == 0;
         int activityCount = this.mAdapter.getActivityCount();
         int maxActivityCountOffset = defaultActivityButtonShown ? 1 : 0;
         if (maxActivityCount != Integer.MAX_VALUE && activityCount > maxActivityCount + maxActivityCountOffset) {
            this.mAdapter.setShowFooterView(true);
            this.mAdapter.setMaxActivityCount(maxActivityCount - 1);
         } else {
            this.mAdapter.setShowFooterView(false);
            this.mAdapter.setMaxActivityCount(maxActivityCount);
         }

         ListPopupWindow popupWindow = this.getListPopupWindow();
         if (!popupWindow.isShowing()) {
            if (!this.mIsSelectingDefaultActivity && defaultActivityButtonShown) {
               this.mAdapter.setShowDefaultActivity(false, false);
            } else {
               this.mAdapter.setShowDefaultActivity(true, defaultActivityButtonShown);
            }

            int contentWidth = Math.min(this.mAdapter.measureContentWidth(), this.mListPopupMaxWidth);
            popupWindow.setContentWidth(contentWidth);
            popupWindow.show();
            if (this.mProvider != null) {
               this.mProvider.subUiVisibilityChanged(true);
            }

            popupWindow.getListView().setContentDescription(this.getContext().getString(string.abc_activitychooserview_choose_application));
            popupWindow.getListView().setSelector(new ColorDrawable(0));
         }

      }
   }

   public boolean dismissPopup() {
      if (this.isShowingPopup()) {
         this.getListPopupWindow().dismiss();
         ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
         if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
         }
      }

      return true;
   }

   public boolean isShowingPopup() {
      return this.getListPopupWindow().isShowing();
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      ActivityChooserModel dataModel = this.mAdapter.getDataModel();
      if (dataModel != null) {
         dataModel.registerObserver(this.mModelDataSetObserver);
      }

      this.mIsAttachedToWindow = true;
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      ActivityChooserModel dataModel = this.mAdapter.getDataModel();
      if (dataModel != null) {
         dataModel.unregisterObserver(this.mModelDataSetObserver);
      }

      ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
      if (viewTreeObserver.isAlive()) {
         viewTreeObserver.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
      }

      if (this.isShowingPopup()) {
         this.dismissPopup();
      }

      this.mIsAttachedToWindow = false;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      View child = this.mActivityChooserContent;
      if (this.mDefaultActivityButton.getVisibility() != 0) {
         heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), 1073741824);
      }

      this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
      this.setMeasuredDimension(child.getMeasuredWidth(), child.getMeasuredHeight());
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      this.mActivityChooserContent.layout(0, 0, right - left, bottom - top);
      if (!this.isShowingPopup()) {
         this.dismissPopup();
      }

   }

   public ActivityChooserModel getDataModel() {
      return this.mAdapter.getDataModel();
   }

   public void setOnDismissListener(OnDismissListener listener) {
      this.mOnDismissListener = listener;
   }

   public void setInitialActivityCount(int itemCount) {
      this.mInitialActivityCount = itemCount;
   }

   public void setDefaultActionButtonContentDescription(int resourceId) {
      this.mDefaultActionButtonContentDescription = resourceId;
   }

   ListPopupWindow getListPopupWindow() {
      if (this.mListPopupWindow == null) {
         this.mListPopupWindow = new ListPopupWindow(this.getContext());
         this.mListPopupWindow.setAdapter(this.mAdapter);
         this.mListPopupWindow.setAnchorView(this);
         this.mListPopupWindow.setModal(true);
         this.mListPopupWindow.setOnItemClickListener(this.mCallbacks);
         this.mListPopupWindow.setOnDismissListener(this.mCallbacks);
      }

      return this.mListPopupWindow;
   }

   void updateAppearance() {
      if (this.mAdapter.getCount() > 0) {
         this.mExpandActivityOverflowButton.setEnabled(true);
      } else {
         this.mExpandActivityOverflowButton.setEnabled(false);
      }

      int activityCount = this.mAdapter.getActivityCount();
      int historySize = this.mAdapter.getHistorySize();
      if (activityCount != 1 && (activityCount <= 1 || historySize <= 0)) {
         this.mDefaultActivityButton.setVisibility(8);
      } else {
         this.mDefaultActivityButton.setVisibility(0);
         ResolveInfo activity = this.mAdapter.getDefaultActivity();
         PackageManager packageManager = this.getContext().getPackageManager();
         this.mDefaultActivityButtonImage.setImageDrawable(activity.loadIcon(packageManager));
         if (this.mDefaultActionButtonContentDescription != 0) {
            CharSequence label = activity.loadLabel(packageManager);
            String contentDescription = this.getContext().getString(this.mDefaultActionButtonContentDescription, new Object[]{label});
            this.mDefaultActivityButton.setContentDescription(contentDescription);
         }
      }

      if (this.mDefaultActivityButton.getVisibility() == 0) {
         this.mActivityChooserContent.setBackgroundDrawable(this.mActivityChooserContentBackground);
      } else {
         this.mActivityChooserContent.setBackgroundDrawable((Drawable)null);
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static class InnerLayout extends LinearLayoutCompat {
      private static final int[] TINT_ATTRS = new int[]{16842964};

      public InnerLayout(Context context, AttributeSet attrs) {
         super(context, attrs);
         TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, TINT_ATTRS);
         this.setBackgroundDrawable(a.getDrawable(0));
         a.recycle();
      }
   }

   private class ActivityChooserViewAdapter extends BaseAdapter {
      public static final int MAX_ACTIVITY_COUNT_UNLIMITED = Integer.MAX_VALUE;
      public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
      private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
      private static final int ITEM_VIEW_TYPE_FOOTER = 1;
      private static final int ITEM_VIEW_TYPE_COUNT = 3;
      private ActivityChooserModel mDataModel;
      private int mMaxActivityCount = 4;
      private boolean mShowDefaultActivity;
      private boolean mHighlightDefaultActivity;
      private boolean mShowFooterView;

      public void setDataModel(ActivityChooserModel dataModel) {
         ActivityChooserModel oldDataModel = ActivityChooserView.this.mAdapter.getDataModel();
         if (oldDataModel != null && ActivityChooserView.this.isShown()) {
            oldDataModel.unregisterObserver(ActivityChooserView.this.mModelDataSetObserver);
         }

         this.mDataModel = dataModel;
         if (dataModel != null && ActivityChooserView.this.isShown()) {
            dataModel.registerObserver(ActivityChooserView.this.mModelDataSetObserver);
         }

         this.notifyDataSetChanged();
      }

      public int getItemViewType(int position) {
         return this.mShowFooterView && position == this.getCount() - 1 ? 1 : 0;
      }

      public int getViewTypeCount() {
         return 3;
      }

      public int getCount() {
         int countx = false;
         int activityCount = this.mDataModel.getActivityCount();
         if (!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
            --activityCount;
         }

         int count = Math.min(activityCount, this.mMaxActivityCount);
         if (this.mShowFooterView) {
            ++count;
         }

         return count;
      }

      public Object getItem(int position) {
         int itemViewType = this.getItemViewType(position);
         switch(itemViewType) {
         case 0:
            if (!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
               ++position;
            }

            return this.mDataModel.getActivity(position);
         case 1:
            return null;
         default:
            throw new IllegalArgumentException();
         }
      }

      public long getItemId(int position) {
         return (long)position;
      }

      public View getView(int position, View convertView, ViewGroup parent) {
         int itemViewType = this.getItemViewType(position);
         switch(itemViewType) {
         case 0:
            if (convertView == null || convertView.getId() != id.list_item) {
               convertView = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(layout.abc_activity_chooser_view_list_item, parent, false);
            }

            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView iconView = (ImageView)convertView.findViewById(id.icon);
            ResolveInfo activity = (ResolveInfo)this.getItem(position);
            iconView.setImageDrawable(activity.loadIcon(packageManager));
            TextView titleView = (TextView)convertView.findViewById(id.title);
            titleView.setText(activity.loadLabel(packageManager));
            if (this.mShowDefaultActivity && position == 0 && this.mHighlightDefaultActivity) {
               convertView.setActivated(true);
            } else {
               convertView.setActivated(false);
            }

            return convertView;
         case 1:
            if (convertView == null || convertView.getId() != 1) {
               convertView = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(layout.abc_activity_chooser_view_list_item, parent, false);
               convertView.setId(1);
               TextView titleViewx = (TextView)convertView.findViewById(id.title);
               titleViewx.setText(ActivityChooserView.this.getContext().getString(string.abc_activity_chooser_view_see_all));
            }

            return convertView;
         default:
            throw new IllegalArgumentException();
         }
      }

      public int measureContentWidth() {
         int oldMaxActivityCount = this.mMaxActivityCount;
         this.mMaxActivityCount = Integer.MAX_VALUE;
         int contentWidth = 0;
         View itemView = null;
         int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
         int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
         int count = this.getCount();

         for(int i = 0; i < count; ++i) {
            itemView = this.getView(i, itemView, (ViewGroup)null);
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
            contentWidth = Math.max(contentWidth, itemView.getMeasuredWidth());
         }

         this.mMaxActivityCount = oldMaxActivityCount;
         return contentWidth;
      }

      public void setMaxActivityCount(int maxActivityCount) {
         if (this.mMaxActivityCount != maxActivityCount) {
            this.mMaxActivityCount = maxActivityCount;
            this.notifyDataSetChanged();
         }

      }

      public ResolveInfo getDefaultActivity() {
         return this.mDataModel.getDefaultActivity();
      }

      public void setShowFooterView(boolean showFooterView) {
         if (this.mShowFooterView != showFooterView) {
            this.mShowFooterView = showFooterView;
            this.notifyDataSetChanged();
         }

      }

      public int getActivityCount() {
         return this.mDataModel.getActivityCount();
      }

      public int getHistorySize() {
         return this.mDataModel.getHistorySize();
      }

      public ActivityChooserModel getDataModel() {
         return this.mDataModel;
      }

      public void setShowDefaultActivity(boolean showDefaultActivity, boolean highlightDefaultActivity) {
         if (this.mShowDefaultActivity != showDefaultActivity || this.mHighlightDefaultActivity != highlightDefaultActivity) {
            this.mShowDefaultActivity = showDefaultActivity;
            this.mHighlightDefaultActivity = highlightDefaultActivity;
            this.notifyDataSetChanged();
         }

      }

      public boolean getShowDefaultActivity() {
         return this.mShowDefaultActivity;
      }
   }

   private class Callbacks implements OnItemClickListener, OnClickListener, OnLongClickListener, OnDismissListener {
      public void onItemClick(AdapterView parent, View view, int position, long id) {
         ActivityChooserView.ActivityChooserViewAdapter adapter = (ActivityChooserView.ActivityChooserViewAdapter)parent.getAdapter();
         int itemViewType = adapter.getItemViewType(position);
         switch(itemViewType) {
         case 0:
            ActivityChooserView.this.dismissPopup();
            if (ActivityChooserView.this.mIsSelectingDefaultActivity) {
               if (position > 0) {
                  ActivityChooserView.this.mAdapter.getDataModel().setDefaultActivity(position);
               }
            } else {
               position = ActivityChooserView.this.mAdapter.getShowDefaultActivity() ? position : position + 1;
               Intent launchIntent = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(position);
               if (launchIntent != null) {
                  launchIntent.addFlags(524288);
                  ActivityChooserView.this.getContext().startActivity(launchIntent);
               }
            }
            break;
         case 1:
            ActivityChooserView.this.showPopupUnchecked(Integer.MAX_VALUE);
            break;
         default:
            throw new IllegalArgumentException();
         }

      }

      public void onClick(View view) {
         if (view == ActivityChooserView.this.mDefaultActivityButton) {
            ActivityChooserView.this.dismissPopup();
            ResolveInfo defaultActivity = ActivityChooserView.this.mAdapter.getDefaultActivity();
            int index = ActivityChooserView.this.mAdapter.getDataModel().getActivityIndex(defaultActivity);
            Intent launchIntent = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(index);
            if (launchIntent != null) {
               launchIntent.addFlags(524288);
               ActivityChooserView.this.getContext().startActivity(launchIntent);
            }
         } else {
            if (view != ActivityChooserView.this.mExpandActivityOverflowButton) {
               throw new IllegalArgumentException();
            }

            ActivityChooserView.this.mIsSelectingDefaultActivity = false;
            ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
         }

      }

      public boolean onLongClick(View view) {
         if (view == ActivityChooserView.this.mDefaultActivityButton) {
            if (ActivityChooserView.this.mAdapter.getCount() > 0) {
               ActivityChooserView.this.mIsSelectingDefaultActivity = true;
               ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
            }

            return true;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public void onDismiss() {
         this.notifyOnDismissListener();
         if (ActivityChooserView.this.mProvider != null) {
            ActivityChooserView.this.mProvider.subUiVisibilityChanged(false);
         }

      }

      private void notifyOnDismissListener() {
         if (ActivityChooserView.this.mOnDismissListener != null) {
            ActivityChooserView.this.mOnDismissListener.onDismiss();
         }

      }
   }
}
