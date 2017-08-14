package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import java.lang.ref.WeakReference;

class AlertController {
   private final Context mContext;
   final AppCompatDialog mDialog;
   private final Window mWindow;
   private CharSequence mTitle;
   private CharSequence mMessage;
   ListView mListView;
   private View mView;
   private int mViewLayoutResId;
   private int mViewSpacingLeft;
   private int mViewSpacingTop;
   private int mViewSpacingRight;
   private int mViewSpacingBottom;
   private boolean mViewSpacingSpecified = false;
   Button mButtonPositive;
   private CharSequence mButtonPositiveText;
   Message mButtonPositiveMessage;
   Button mButtonNegative;
   private CharSequence mButtonNegativeText;
   Message mButtonNegativeMessage;
   Button mButtonNeutral;
   private CharSequence mButtonNeutralText;
   Message mButtonNeutralMessage;
   NestedScrollView mScrollView;
   private int mIconId = 0;
   private Drawable mIcon;
   private ImageView mIconView;
   private TextView mTitleView;
   private TextView mMessageView;
   private View mCustomTitleView;
   ListAdapter mAdapter;
   int mCheckedItem = -1;
   private int mAlertDialogLayout;
   private int mButtonPanelSideLayout;
   int mListLayout;
   int mMultiChoiceItemLayout;
   int mSingleChoiceItemLayout;
   int mListItemLayout;
   private boolean mShowTitle;
   private int mButtonPanelLayoutHint = 0;
   Handler mHandler;
   private final OnClickListener mButtonHandler = new OnClickListener() {
      public void onClick(View v) {
         Message m;
         if (v == AlertController.this.mButtonPositive && AlertController.this.mButtonPositiveMessage != null) {
            m = Message.obtain(AlertController.this.mButtonPositiveMessage);
         } else if (v == AlertController.this.mButtonNegative && AlertController.this.mButtonNegativeMessage != null) {
            m = Message.obtain(AlertController.this.mButtonNegativeMessage);
         } else if (v == AlertController.this.mButtonNeutral && AlertController.this.mButtonNeutralMessage != null) {
            m = Message.obtain(AlertController.this.mButtonNeutralMessage);
         } else {
            m = null;
         }

         if (m != null) {
            m.sendToTarget();
         }

         AlertController.this.mHandler.obtainMessage(1, AlertController.this.mDialog).sendToTarget();
      }
   };

   private static boolean shouldCenterSingleButton(Context context) {
      TypedValue outValue = new TypedValue();
      context.getTheme().resolveAttribute(attr.alertDialogCenterButtons, outValue, true);
      return outValue.data != 0;
   }

   public AlertController(Context context, AppCompatDialog di, Window window) {
      this.mContext = context;
      this.mDialog = di;
      this.mWindow = window;
      this.mHandler = new AlertController.ButtonHandler(di);
      TypedArray a = context.obtainStyledAttributes((AttributeSet)null, styleable.AlertDialog, attr.alertDialogStyle, 0);
      this.mAlertDialogLayout = a.getResourceId(styleable.AlertDialog_android_layout, 0);
      this.mButtonPanelSideLayout = a.getResourceId(styleable.AlertDialog_buttonPanelSideLayout, 0);
      this.mListLayout = a.getResourceId(styleable.AlertDialog_listLayout, 0);
      this.mMultiChoiceItemLayout = a.getResourceId(styleable.AlertDialog_multiChoiceItemLayout, 0);
      this.mSingleChoiceItemLayout = a.getResourceId(styleable.AlertDialog_singleChoiceItemLayout, 0);
      this.mListItemLayout = a.getResourceId(styleable.AlertDialog_listItemLayout, 0);
      this.mShowTitle = a.getBoolean(styleable.AlertDialog_showTitle, true);
      a.recycle();
      di.supportRequestWindowFeature(1);
   }

   static boolean canTextInput(View v) {
      if (v.onCheckIsTextEditor()) {
         return true;
      } else if (!(v instanceof ViewGroup)) {
         return false;
      } else {
         ViewGroup vg = (ViewGroup)v;
         int i = vg.getChildCount();

         do {
            if (i <= 0) {
               return false;
            }

            --i;
            v = vg.getChildAt(i);
         } while(!canTextInput(v));

         return true;
      }
   }

   public void installContent() {
      int contentView = this.selectContentView();
      this.mDialog.setContentView(contentView);
      this.setupView();
   }

   private int selectContentView() {
      if (this.mButtonPanelSideLayout == 0) {
         return this.mAlertDialogLayout;
      } else {
         return this.mButtonPanelLayoutHint == 1 ? this.mButtonPanelSideLayout : this.mAlertDialogLayout;
      }
   }

   public void setTitle(CharSequence title) {
      this.mTitle = title;
      if (this.mTitleView != null) {
         this.mTitleView.setText(title);
      }

   }

   public void setCustomTitle(View customTitleView) {
      this.mCustomTitleView = customTitleView;
   }

   public void setMessage(CharSequence message) {
      this.mMessage = message;
      if (this.mMessageView != null) {
         this.mMessageView.setText(message);
      }

   }

   public void setView(int layoutResId) {
      this.mView = null;
      this.mViewLayoutResId = layoutResId;
      this.mViewSpacingSpecified = false;
   }

   public void setView(View view) {
      this.mView = view;
      this.mViewLayoutResId = 0;
      this.mViewSpacingSpecified = false;
   }

   public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
      this.mView = view;
      this.mViewLayoutResId = 0;
      this.mViewSpacingSpecified = true;
      this.mViewSpacingLeft = viewSpacingLeft;
      this.mViewSpacingTop = viewSpacingTop;
      this.mViewSpacingRight = viewSpacingRight;
      this.mViewSpacingBottom = viewSpacingBottom;
   }

   public void setButtonPanelLayoutHint(int layoutHint) {
      this.mButtonPanelLayoutHint = layoutHint;
   }

   public void setButton(int whichButton, CharSequence text, android.content.DialogInterface.OnClickListener listener, Message msg) {
      if (msg == null && listener != null) {
         msg = this.mHandler.obtainMessage(whichButton, listener);
      }

      switch(whichButton) {
      case -3:
         this.mButtonNeutralText = text;
         this.mButtonNeutralMessage = msg;
         break;
      case -2:
         this.mButtonNegativeText = text;
         this.mButtonNegativeMessage = msg;
         break;
      case -1:
         this.mButtonPositiveText = text;
         this.mButtonPositiveMessage = msg;
         break;
      default:
         throw new IllegalArgumentException("Button does not exist");
      }

   }

   public void setIcon(int resId) {
      this.mIcon = null;
      this.mIconId = resId;
      if (this.mIconView != null) {
         if (resId != 0) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
         } else {
            this.mIconView.setVisibility(8);
         }
      }

   }

   public void setIcon(Drawable icon) {
      this.mIcon = icon;
      this.mIconId = 0;
      if (this.mIconView != null) {
         if (icon != null) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageDrawable(icon);
         } else {
            this.mIconView.setVisibility(8);
         }
      }

   }

   public int getIconAttributeResId(int attrId) {
      TypedValue out = new TypedValue();
      this.mContext.getTheme().resolveAttribute(attrId, out, true);
      return out.resourceId;
   }

   public ListView getListView() {
      return this.mListView;
   }

   public Button getButton(int whichButton) {
      switch(whichButton) {
      case -3:
         return this.mButtonNeutral;
      case -2:
         return this.mButtonNegative;
      case -1:
         return this.mButtonPositive;
      default:
         return null;
      }
   }

   public boolean onKeyDown(int keyCode, KeyEvent event) {
      return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
   }

   public boolean onKeyUp(int keyCode, KeyEvent event) {
      return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
   }

   @Nullable
   private ViewGroup resolvePanel(@Nullable View customPanel, @Nullable View defaultPanel) {
      if (customPanel == null) {
         if (defaultPanel instanceof ViewStub) {
            defaultPanel = ((ViewStub)defaultPanel).inflate();
         }

         return (ViewGroup)defaultPanel;
      } else {
         if (defaultPanel != null) {
            ViewParent parent = defaultPanel.getParent();
            if (parent instanceof ViewGroup) {
               ((ViewGroup)parent).removeView(defaultPanel);
            }
         }

         if (customPanel instanceof ViewStub) {
            customPanel = ((ViewStub)customPanel).inflate();
         }

         return (ViewGroup)customPanel;
      }
   }

   private void setupView() {
      View parentPanel = this.mWindow.findViewById(id.parentPanel);
      View defaultTopPanel = parentPanel.findViewById(id.topPanel);
      View defaultContentPanel = parentPanel.findViewById(id.contentPanel);
      View defaultButtonPanel = parentPanel.findViewById(id.buttonPanel);
      ViewGroup customPanel = (ViewGroup)parentPanel.findViewById(id.customPanel);
      this.setupCustomContent(customPanel);
      View customTopPanel = customPanel.findViewById(id.topPanel);
      View customContentPanel = customPanel.findViewById(id.contentPanel);
      View customButtonPanel = customPanel.findViewById(id.buttonPanel);
      ViewGroup topPanel = this.resolvePanel(customTopPanel, defaultTopPanel);
      ViewGroup contentPanel = this.resolvePanel(customContentPanel, defaultContentPanel);
      ViewGroup buttonPanel = this.resolvePanel(customButtonPanel, defaultButtonPanel);
      this.setupContent(contentPanel);
      this.setupButtons(buttonPanel);
      this.setupTitle(topPanel);
      boolean hasCustomPanel = customPanel != null && customPanel.getVisibility() != 8;
      boolean hasTopPanel = topPanel != null && topPanel.getVisibility() != 8;
      boolean hasButtonPanel = buttonPanel != null && buttonPanel.getVisibility() != 8;
      View divider;
      if (!hasButtonPanel && contentPanel != null) {
         divider = contentPanel.findViewById(id.textSpacerNoButtons);
         if (divider != null) {
            divider.setVisibility(0);
         }
      }

      if (hasTopPanel) {
         if (this.mScrollView != null) {
            this.mScrollView.setClipToPadding(true);
         }

         divider = null;
         if ((this.mMessage != null || this.mListView != null || hasCustomPanel) && !hasCustomPanel) {
            divider = topPanel.findViewById(id.titleDividerNoCustom);
         }

         if (divider != null) {
            divider.setVisibility(0);
         }
      } else if (contentPanel != null) {
         divider = contentPanel.findViewById(id.textSpacerNoTitle);
         if (divider != null) {
            divider.setVisibility(0);
         }
      }

      if (this.mListView instanceof AlertController.RecycleListView) {
         ((AlertController.RecycleListView)this.mListView).setHasDecor(hasTopPanel, hasButtonPanel);
      }

      int checkedItem;
      if (!hasCustomPanel) {
         View content = this.mListView != null ? this.mListView : this.mScrollView;
         if (content != null) {
            checkedItem = (hasTopPanel ? 1 : 0) | (hasButtonPanel ? 2 : 0);
            this.setScrollIndicators(contentPanel, (View)content, checkedItem, 3);
         }
      }

      ListView listView = this.mListView;
      if (listView != null && this.mAdapter != null) {
         listView.setAdapter(this.mAdapter);
         checkedItem = this.mCheckedItem;
         if (checkedItem > -1) {
            listView.setItemChecked(checkedItem, true);
            listView.setSelection(checkedItem);
         }
      }

   }

   private void setScrollIndicators(ViewGroup contentPanel, View content, int indicators, int mask) {
      final View indicatorUp = this.mWindow.findViewById(id.scrollIndicatorUp);
      final View indicatorDown = this.mWindow.findViewById(id.scrollIndicatorDown);
      if (VERSION.SDK_INT >= 23) {
         ViewCompat.setScrollIndicators(content, indicators, mask);
         if (indicatorUp != null) {
            contentPanel.removeView(indicatorUp);
         }

         if (indicatorDown != null) {
            contentPanel.removeView(indicatorDown);
         }
      } else {
         if (indicatorUp != null && (indicators & 1) == 0) {
            contentPanel.removeView(indicatorUp);
            indicatorUp = null;
         }

         if (indicatorDown != null && (indicators & 2) == 0) {
            contentPanel.removeView(indicatorDown);
            indicatorDown = null;
         }

         if (indicatorUp != null || indicatorDown != null) {
            if (this.mMessage != null) {
               this.mScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
                  public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                     AlertController.manageScrollIndicators(v, indicatorUp, indicatorDown);
                  }
               });
               this.mScrollView.post(new Runnable() {
                  public void run() {
                     AlertController.manageScrollIndicators(AlertController.this.mScrollView, indicatorUp, indicatorDown);
                  }
               });
            } else if (this.mListView != null) {
               this.mListView.setOnScrollListener(new OnScrollListener() {
                  public void onScrollStateChanged(AbsListView view, int scrollState) {
                  }

                  public void onScroll(AbsListView v, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                     AlertController.manageScrollIndicators(v, indicatorUp, indicatorDown);
                  }
               });
               this.mListView.post(new Runnable() {
                  public void run() {
                     AlertController.manageScrollIndicators(AlertController.this.mListView, indicatorUp, indicatorDown);
                  }
               });
            } else {
               if (indicatorUp != null) {
                  contentPanel.removeView(indicatorUp);
               }

               if (indicatorDown != null) {
                  contentPanel.removeView(indicatorDown);
               }
            }
         }
      }

   }

   private void setupCustomContent(ViewGroup customPanel) {
      View customView;
      if (this.mView != null) {
         customView = this.mView;
      } else if (this.mViewLayoutResId != 0) {
         LayoutInflater inflater = LayoutInflater.from(this.mContext);
         customView = inflater.inflate(this.mViewLayoutResId, customPanel, false);
      } else {
         customView = null;
      }

      boolean hasCustomView = customView != null;
      if (!hasCustomView || !canTextInput(customView)) {
         this.mWindow.setFlags(131072, 131072);
      }

      if (hasCustomView) {
         FrameLayout custom = (FrameLayout)this.mWindow.findViewById(id.custom);
         custom.addView(customView, new LayoutParams(-1, -1));
         if (this.mViewSpacingSpecified) {
            custom.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
         }

         if (this.mListView != null) {
            ((LinearLayoutCompat.LayoutParams)customPanel.getLayoutParams()).weight = 0.0F;
         }
      } else {
         customPanel.setVisibility(8);
      }

   }

   private void setupTitle(ViewGroup topPanel) {
      View titleTemplate;
      if (this.mCustomTitleView != null) {
         LayoutParams lp = new LayoutParams(-1, -2);
         topPanel.addView(this.mCustomTitleView, 0, lp);
         titleTemplate = this.mWindow.findViewById(id.title_template);
         titleTemplate.setVisibility(8);
      } else {
         this.mIconView = (ImageView)this.mWindow.findViewById(16908294);
         boolean hasTextTitle = !TextUtils.isEmpty(this.mTitle);
         if (hasTextTitle && this.mShowTitle) {
            this.mTitleView = (TextView)this.mWindow.findViewById(id.alertTitle);
            this.mTitleView.setText(this.mTitle);
            if (this.mIconId != 0) {
               this.mIconView.setImageResource(this.mIconId);
            } else if (this.mIcon != null) {
               this.mIconView.setImageDrawable(this.mIcon);
            } else {
               this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
               this.mIconView.setVisibility(8);
            }
         } else {
            titleTemplate = this.mWindow.findViewById(id.title_template);
            titleTemplate.setVisibility(8);
            this.mIconView.setVisibility(8);
            topPanel.setVisibility(8);
         }
      }

   }

   private void setupContent(ViewGroup contentPanel) {
      this.mScrollView = (NestedScrollView)this.mWindow.findViewById(id.scrollView);
      this.mScrollView.setFocusable(false);
      this.mScrollView.setNestedScrollingEnabled(false);
      this.mMessageView = (TextView)contentPanel.findViewById(16908299);
      if (this.mMessageView != null) {
         if (this.mMessage != null) {
            this.mMessageView.setText(this.mMessage);
         } else {
            this.mMessageView.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            if (this.mListView != null) {
               ViewGroup scrollParent = (ViewGroup)this.mScrollView.getParent();
               int childIndex = scrollParent.indexOfChild(this.mScrollView);
               scrollParent.removeViewAt(childIndex);
               scrollParent.addView(this.mListView, childIndex, new LayoutParams(-1, -1));
            } else {
               contentPanel.setVisibility(8);
            }
         }

      }
   }

   static void manageScrollIndicators(View v, View upIndicator, View downIndicator) {
      if (upIndicator != null) {
         upIndicator.setVisibility(v.canScrollVertically(-1) ? 0 : 4);
      }

      if (downIndicator != null) {
         downIndicator.setVisibility(v.canScrollVertically(1) ? 0 : 4);
      }

   }

   private void setupButtons(ViewGroup buttonPanel) {
      int BIT_BUTTON_POSITIVE = true;
      int BIT_BUTTON_NEGATIVE = true;
      int BIT_BUTTON_NEUTRAL = true;
      int whichButtons = false;
      this.mButtonPositive = (Button)buttonPanel.findViewById(16908313);
      this.mButtonPositive.setOnClickListener(this.mButtonHandler);
      if (TextUtils.isEmpty(this.mButtonPositiveText)) {
         this.mButtonPositive.setVisibility(8);
      } else {
         this.mButtonPositive.setText(this.mButtonPositiveText);
         this.mButtonPositive.setVisibility(0);
         whichButtons |= BIT_BUTTON_POSITIVE;
      }

      this.mButtonNegative = (Button)buttonPanel.findViewById(16908314);
      this.mButtonNegative.setOnClickListener(this.mButtonHandler);
      if (TextUtils.isEmpty(this.mButtonNegativeText)) {
         this.mButtonNegative.setVisibility(8);
      } else {
         this.mButtonNegative.setText(this.mButtonNegativeText);
         this.mButtonNegative.setVisibility(0);
         whichButtons |= BIT_BUTTON_NEGATIVE;
      }

      this.mButtonNeutral = (Button)buttonPanel.findViewById(16908315);
      this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
      if (TextUtils.isEmpty(this.mButtonNeutralText)) {
         this.mButtonNeutral.setVisibility(8);
      } else {
         this.mButtonNeutral.setText(this.mButtonNeutralText);
         this.mButtonNeutral.setVisibility(0);
         whichButtons |= BIT_BUTTON_NEUTRAL;
      }

      if (shouldCenterSingleButton(this.mContext)) {
         if (whichButtons == BIT_BUTTON_POSITIVE) {
            this.centerButton(this.mButtonPositive);
         } else if (whichButtons == BIT_BUTTON_NEGATIVE) {
            this.centerButton(this.mButtonNegative);
         } else if (whichButtons == BIT_BUTTON_NEUTRAL) {
            this.centerButton(this.mButtonNeutral);
         }
      }

      boolean hasButtons = whichButtons;
      if (!hasButtons) {
         buttonPanel.setVisibility(8);
      }

   }

   private void centerButton(Button button) {
      android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams)button.getLayoutParams();
      params.gravity = 1;
      params.weight = 0.5F;
      button.setLayoutParams(params);
   }

   private static class CheckedItemAdapter extends ArrayAdapter {
      public CheckedItemAdapter(Context context, int resource, int textViewResourceId, CharSequence[] objects) {
         super(context, resource, textViewResourceId, objects);
      }

      public boolean hasStableIds() {
         return true;
      }

      public long getItemId(int position) {
         return (long)position;
      }
   }

   public static class AlertParams {
      public final Context mContext;
      public final LayoutInflater mInflater;
      public int mIconId = 0;
      public Drawable mIcon;
      public int mIconAttrId = 0;
      public CharSequence mTitle;
      public View mCustomTitleView;
      public CharSequence mMessage;
      public CharSequence mPositiveButtonText;
      public android.content.DialogInterface.OnClickListener mPositiveButtonListener;
      public CharSequence mNegativeButtonText;
      public android.content.DialogInterface.OnClickListener mNegativeButtonListener;
      public CharSequence mNeutralButtonText;
      public android.content.DialogInterface.OnClickListener mNeutralButtonListener;
      public boolean mCancelable;
      public OnCancelListener mOnCancelListener;
      public OnDismissListener mOnDismissListener;
      public OnKeyListener mOnKeyListener;
      public CharSequence[] mItems;
      public ListAdapter mAdapter;
      public android.content.DialogInterface.OnClickListener mOnClickListener;
      public int mViewLayoutResId;
      public View mView;
      public int mViewSpacingLeft;
      public int mViewSpacingTop;
      public int mViewSpacingRight;
      public int mViewSpacingBottom;
      public boolean mViewSpacingSpecified = false;
      public boolean[] mCheckedItems;
      public boolean mIsMultiChoice;
      public boolean mIsSingleChoice;
      public int mCheckedItem = -1;
      public OnMultiChoiceClickListener mOnCheckboxClickListener;
      public Cursor mCursor;
      public String mLabelColumn;
      public String mIsCheckedColumn;
      public boolean mForceInverseBackground;
      public OnItemSelectedListener mOnItemSelectedListener;
      public AlertController.AlertParams.OnPrepareListViewListener mOnPrepareListViewListener;
      public boolean mRecycleOnMeasure = true;

      public AlertParams(Context context) {
         this.mContext = context;
         this.mCancelable = true;
         this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
      }

      public void apply(AlertController dialog) {
         if (this.mCustomTitleView != null) {
            dialog.setCustomTitle(this.mCustomTitleView);
         } else {
            if (this.mTitle != null) {
               dialog.setTitle(this.mTitle);
            }

            if (this.mIcon != null) {
               dialog.setIcon(this.mIcon);
            }

            if (this.mIconId != 0) {
               dialog.setIcon(this.mIconId);
            }

            if (this.mIconAttrId != 0) {
               dialog.setIcon(dialog.getIconAttributeResId(this.mIconAttrId));
            }
         }

         if (this.mMessage != null) {
            dialog.setMessage(this.mMessage);
         }

         if (this.mPositiveButtonText != null) {
            dialog.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, (Message)null);
         }

         if (this.mNegativeButtonText != null) {
            dialog.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, (Message)null);
         }

         if (this.mNeutralButtonText != null) {
            dialog.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, (Message)null);
         }

         if (this.mItems != null || this.mCursor != null || this.mAdapter != null) {
            this.createListView(dialog);
         }

         if (this.mView != null) {
            if (this.mViewSpacingSpecified) {
               dialog.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            } else {
               dialog.setView(this.mView);
            }
         } else if (this.mViewLayoutResId != 0) {
            dialog.setView(this.mViewLayoutResId);
         }

      }

      private void createListView(final AlertController dialog) {
         final AlertController.RecycleListView listView = (AlertController.RecycleListView)this.mInflater.inflate(dialog.mListLayout, (ViewGroup)null);
         Object adapter;
         if (this.mIsMultiChoice) {
            if (this.mCursor == null) {
               adapter = new ArrayAdapter(this.mContext, dialog.mMultiChoiceItemLayout, 16908308, this.mItems) {
                  public View getView(int position, View convertView, ViewGroup parent) {
                     View view = super.getView(position, convertView, parent);
                     if (AlertParams.this.mCheckedItems != null) {
                        boolean isItemChecked = AlertParams.this.mCheckedItems[position];
                        if (isItemChecked) {
                           listView.setItemChecked(position, true);
                        }
                     }

                     return view;
                  }
               };
            } else {
               adapter = new CursorAdapter(this.mContext, this.mCursor, false) {
                  private final int mLabelIndex;
                  private final int mIsCheckedIndex;

                  {
                     Cursor cursor = this.getCursor();
                     this.mLabelIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
                     this.mIsCheckedIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mIsCheckedColumn);
                  }

                  public void bindView(View view, Context context, Cursor cursor) {
                     CheckedTextView text = (CheckedTextView)view.findViewById(16908308);
                     text.setText(cursor.getString(this.mLabelIndex));
                     listView.setItemChecked(cursor.getPosition(), cursor.getInt(this.mIsCheckedIndex) == 1);
                  }

                  public View newView(Context context, Cursor cursor, ViewGroup parent) {
                     return AlertParams.this.mInflater.inflate(dialog.mMultiChoiceItemLayout, parent, false);
                  }
               };
            }
         } else {
            int layout;
            if (this.mIsSingleChoice) {
               layout = dialog.mSingleChoiceItemLayout;
            } else {
               layout = dialog.mListItemLayout;
            }

            if (this.mCursor != null) {
               adapter = new SimpleCursorAdapter(this.mContext, layout, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
            } else if (this.mAdapter != null) {
               adapter = this.mAdapter;
            } else {
               adapter = new AlertController.CheckedItemAdapter(this.mContext, layout, 16908308, this.mItems);
            }
         }

         if (this.mOnPrepareListViewListener != null) {
            this.mOnPrepareListViewListener.onPrepareListView(listView);
         }

         dialog.mAdapter = (ListAdapter)adapter;
         dialog.mCheckedItem = this.mCheckedItem;
         if (this.mOnClickListener != null) {
            listView.setOnItemClickListener(new OnItemClickListener() {
               public void onItemClick(AdapterView parent, View v, int position, long id) {
                  AlertParams.this.mOnClickListener.onClick(dialog.mDialog, position);
                  if (!AlertParams.this.mIsSingleChoice) {
                     dialog.mDialog.dismiss();
                  }

               }
            });
         } else if (this.mOnCheckboxClickListener != null) {
            listView.setOnItemClickListener(new OnItemClickListener() {
               public void onItemClick(AdapterView parent, View v, int position, long id) {
                  if (AlertParams.this.mCheckedItems != null) {
                     AlertParams.this.mCheckedItems[position] = listView.isItemChecked(position);
                  }

                  AlertParams.this.mOnCheckboxClickListener.onClick(dialog.mDialog, position, listView.isItemChecked(position));
               }
            });
         }

         if (this.mOnItemSelectedListener != null) {
            listView.setOnItemSelectedListener(this.mOnItemSelectedListener);
         }

         if (this.mIsSingleChoice) {
            listView.setChoiceMode(1);
         } else if (this.mIsMultiChoice) {
            listView.setChoiceMode(2);
         }

         dialog.mListView = listView;
      }

      public interface OnPrepareListViewListener {
         void onPrepareListView(ListView var1);
      }
   }

   public static class RecycleListView extends ListView {
      private final int mPaddingTopNoTitle;
      private final int mPaddingBottomNoButtons;

      public RecycleListView(Context context) {
         this(context, (AttributeSet)null);
      }

      public RecycleListView(Context context, AttributeSet attrs) {
         super(context, attrs);
         TypedArray ta = context.obtainStyledAttributes(attrs, styleable.RecycleListView);
         this.mPaddingBottomNoButtons = ta.getDimensionPixelOffset(styleable.RecycleListView_paddingBottomNoButtons, -1);
         this.mPaddingTopNoTitle = ta.getDimensionPixelOffset(styleable.RecycleListView_paddingTopNoTitle, -1);
      }

      public void setHasDecor(boolean hasTitle, boolean hasButtons) {
         if (!hasButtons || !hasTitle) {
            int paddingLeft = this.getPaddingLeft();
            int paddingTop = hasTitle ? this.getPaddingTop() : this.mPaddingTopNoTitle;
            int paddingRight = this.getPaddingRight();
            int paddingBottom = hasButtons ? this.getPaddingBottom() : this.mPaddingBottomNoButtons;
            this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
         }

      }
   }

   private static final class ButtonHandler extends Handler {
      private static final int MSG_DISMISS_DIALOG = 1;
      private WeakReference mDialog;

      public ButtonHandler(DialogInterface dialog) {
         this.mDialog = new WeakReference(dialog);
      }

      public void handleMessage(Message msg) {
         switch(msg.what) {
         case -3:
         case -2:
         case -1:
            ((android.content.DialogInterface.OnClickListener)msg.obj).onClick((DialogInterface)this.mDialog.get(), msg.what);
         case 0:
         default:
            break;
         case 1:
            ((DialogInterface)msg.obj).dismiss();
         }

      }
   }
}
