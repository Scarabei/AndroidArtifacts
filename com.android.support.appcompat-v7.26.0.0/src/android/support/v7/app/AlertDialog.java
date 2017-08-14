package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.attr;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
   final AlertController mAlert;
   static final int LAYOUT_HINT_NONE = 0;
   static final int LAYOUT_HINT_SIDE = 1;

   protected AlertDialog(@NonNull Context context) {
      this(context, 0);
   }

   protected AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
      super(context, resolveDialogTheme(context, themeResId));
      this.mAlert = new AlertController(this.getContext(), this, this.getWindow());
   }

   protected AlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
      this(context, 0);
      this.setCancelable(cancelable);
      this.setOnCancelListener(cancelListener);
   }

   static int resolveDialogTheme(@NonNull Context context, @StyleRes int resid) {
      if ((resid >>> 24 & 255) >= 1) {
         return resid;
      } else {
         TypedValue outValue = new TypedValue();
         context.getTheme().resolveAttribute(attr.alertDialogTheme, outValue, true);
         return outValue.resourceId;
      }
   }

   public Button getButton(int whichButton) {
      return this.mAlert.getButton(whichButton);
   }

   public ListView getListView() {
      return this.mAlert.getListView();
   }

   public void setTitle(CharSequence title) {
      super.setTitle(title);
      this.mAlert.setTitle(title);
   }

   public void setCustomTitle(View customTitleView) {
      this.mAlert.setCustomTitle(customTitleView);
   }

   public void setMessage(CharSequence message) {
      this.mAlert.setMessage(message);
   }

   public void setView(View view) {
      this.mAlert.setView(view);
   }

   public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
      this.mAlert.setView(view, viewSpacingLeft, viewSpacingTop, viewSpacingRight, viewSpacingBottom);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void setButtonPanelLayoutHint(int layoutHint) {
      this.mAlert.setButtonPanelLayoutHint(layoutHint);
   }

   public void setButton(int whichButton, CharSequence text, Message msg) {
      this.mAlert.setButton(whichButton, text, (OnClickListener)null, msg);
   }

   public void setButton(int whichButton, CharSequence text, OnClickListener listener) {
      this.mAlert.setButton(whichButton, text, listener, (Message)null);
   }

   public void setIcon(int resId) {
      this.mAlert.setIcon(resId);
   }

   public void setIcon(Drawable icon) {
      this.mAlert.setIcon(icon);
   }

   public void setIconAttribute(int attrId) {
      TypedValue out = new TypedValue();
      this.getContext().getTheme().resolveAttribute(attrId, out, true);
      this.mAlert.setIcon(out.resourceId);
   }

   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.mAlert.installContent();
   }

   public boolean onKeyDown(int keyCode, KeyEvent event) {
      return this.mAlert.onKeyDown(keyCode, event) ? true : super.onKeyDown(keyCode, event);
   }

   public boolean onKeyUp(int keyCode, KeyEvent event) {
      return this.mAlert.onKeyUp(keyCode, event) ? true : super.onKeyUp(keyCode, event);
   }

   public static class Builder {
      private final AlertController.AlertParams P;
      private final int mTheme;

      public Builder(@NonNull Context context) {
         this(context, AlertDialog.resolveDialogTheme(context, 0));
      }

      public Builder(@NonNull Context context, @StyleRes int themeResId) {
         this.P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, themeResId)));
         this.mTheme = themeResId;
      }

      @NonNull
      public Context getContext() {
         return this.P.mContext;
      }

      public AlertDialog.Builder setTitle(@StringRes int titleId) {
         this.P.mTitle = this.P.mContext.getText(titleId);
         return this;
      }

      public AlertDialog.Builder setTitle(@Nullable CharSequence title) {
         this.P.mTitle = title;
         return this;
      }

      public AlertDialog.Builder setCustomTitle(@Nullable View customTitleView) {
         this.P.mCustomTitleView = customTitleView;
         return this;
      }

      public AlertDialog.Builder setMessage(@StringRes int messageId) {
         this.P.mMessage = this.P.mContext.getText(messageId);
         return this;
      }

      public AlertDialog.Builder setMessage(@Nullable CharSequence message) {
         this.P.mMessage = message;
         return this;
      }

      public AlertDialog.Builder setIcon(@DrawableRes int iconId) {
         this.P.mIconId = iconId;
         return this;
      }

      public AlertDialog.Builder setIcon(@Nullable Drawable icon) {
         this.P.mIcon = icon;
         return this;
      }

      public AlertDialog.Builder setIconAttribute(@AttrRes int attrId) {
         TypedValue out = new TypedValue();
         this.P.mContext.getTheme().resolveAttribute(attrId, out, true);
         this.P.mIconId = out.resourceId;
         return this;
      }

      public AlertDialog.Builder setPositiveButton(@StringRes int textId, OnClickListener listener) {
         this.P.mPositiveButtonText = this.P.mContext.getText(textId);
         this.P.mPositiveButtonListener = listener;
         return this;
      }

      public AlertDialog.Builder setPositiveButton(CharSequence text, OnClickListener listener) {
         this.P.mPositiveButtonText = text;
         this.P.mPositiveButtonListener = listener;
         return this;
      }

      public AlertDialog.Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
         this.P.mNegativeButtonText = this.P.mContext.getText(textId);
         this.P.mNegativeButtonListener = listener;
         return this;
      }

      public AlertDialog.Builder setNegativeButton(CharSequence text, OnClickListener listener) {
         this.P.mNegativeButtonText = text;
         this.P.mNegativeButtonListener = listener;
         return this;
      }

      public AlertDialog.Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
         this.P.mNeutralButtonText = this.P.mContext.getText(textId);
         this.P.mNeutralButtonListener = listener;
         return this;
      }

      public AlertDialog.Builder setNeutralButton(CharSequence text, OnClickListener listener) {
         this.P.mNeutralButtonText = text;
         this.P.mNeutralButtonListener = listener;
         return this;
      }

      public AlertDialog.Builder setCancelable(boolean cancelable) {
         this.P.mCancelable = cancelable;
         return this;
      }

      public AlertDialog.Builder setOnCancelListener(OnCancelListener onCancelListener) {
         this.P.mOnCancelListener = onCancelListener;
         return this;
      }

      public AlertDialog.Builder setOnDismissListener(OnDismissListener onDismissListener) {
         this.P.mOnDismissListener = onDismissListener;
         return this;
      }

      public AlertDialog.Builder setOnKeyListener(OnKeyListener onKeyListener) {
         this.P.mOnKeyListener = onKeyListener;
         return this;
      }

      public AlertDialog.Builder setItems(@ArrayRes int itemsId, OnClickListener listener) {
         this.P.mItems = this.P.mContext.getResources().getTextArray(itemsId);
         this.P.mOnClickListener = listener;
         return this;
      }

      public AlertDialog.Builder setItems(CharSequence[] items, OnClickListener listener) {
         this.P.mItems = items;
         this.P.mOnClickListener = listener;
         return this;
      }

      public AlertDialog.Builder setAdapter(ListAdapter adapter, OnClickListener listener) {
         this.P.mAdapter = adapter;
         this.P.mOnClickListener = listener;
         return this;
      }

      public AlertDialog.Builder setCursor(Cursor cursor, OnClickListener listener, String labelColumn) {
         this.P.mCursor = cursor;
         this.P.mLabelColumn = labelColumn;
         this.P.mOnClickListener = listener;
         return this;
      }

      public AlertDialog.Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
         this.P.mItems = this.P.mContext.getResources().getTextArray(itemsId);
         this.P.mOnCheckboxClickListener = listener;
         this.P.mCheckedItems = checkedItems;
         this.P.mIsMultiChoice = true;
         return this;
      }

      public AlertDialog.Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, OnMultiChoiceClickListener listener) {
         this.P.mItems = items;
         this.P.mOnCheckboxClickListener = listener;
         this.P.mCheckedItems = checkedItems;
         this.P.mIsMultiChoice = true;
         return this;
      }

      public AlertDialog.Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, OnMultiChoiceClickListener listener) {
         this.P.mCursor = cursor;
         this.P.mOnCheckboxClickListener = listener;
         this.P.mIsCheckedColumn = isCheckedColumn;
         this.P.mLabelColumn = labelColumn;
         this.P.mIsMultiChoice = true;
         return this;
      }

      public AlertDialog.Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, OnClickListener listener) {
         this.P.mItems = this.P.mContext.getResources().getTextArray(itemsId);
         this.P.mOnClickListener = listener;
         this.P.mCheckedItem = checkedItem;
         this.P.mIsSingleChoice = true;
         return this;
      }

      public AlertDialog.Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, OnClickListener listener) {
         this.P.mCursor = cursor;
         this.P.mOnClickListener = listener;
         this.P.mCheckedItem = checkedItem;
         this.P.mLabelColumn = labelColumn;
         this.P.mIsSingleChoice = true;
         return this;
      }

      public AlertDialog.Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, OnClickListener listener) {
         this.P.mItems = items;
         this.P.mOnClickListener = listener;
         this.P.mCheckedItem = checkedItem;
         this.P.mIsSingleChoice = true;
         return this;
      }

      public AlertDialog.Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, OnClickListener listener) {
         this.P.mAdapter = adapter;
         this.P.mOnClickListener = listener;
         this.P.mCheckedItem = checkedItem;
         this.P.mIsSingleChoice = true;
         return this;
      }

      public AlertDialog.Builder setOnItemSelectedListener(OnItemSelectedListener listener) {
         this.P.mOnItemSelectedListener = listener;
         return this;
      }

      public AlertDialog.Builder setView(int layoutResId) {
         this.P.mView = null;
         this.P.mViewLayoutResId = layoutResId;
         this.P.mViewSpacingSpecified = false;
         return this;
      }

      public AlertDialog.Builder setView(View view) {
         this.P.mView = view;
         this.P.mViewLayoutResId = 0;
         this.P.mViewSpacingSpecified = false;
         return this;
      }

      /** @deprecated */
      @Deprecated
      @RestrictTo({Scope.LIBRARY_GROUP})
      public AlertDialog.Builder setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
         this.P.mView = view;
         this.P.mViewLayoutResId = 0;
         this.P.mViewSpacingSpecified = true;
         this.P.mViewSpacingLeft = viewSpacingLeft;
         this.P.mViewSpacingTop = viewSpacingTop;
         this.P.mViewSpacingRight = viewSpacingRight;
         this.P.mViewSpacingBottom = viewSpacingBottom;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public AlertDialog.Builder setInverseBackgroundForced(boolean useInverseBackground) {
         this.P.mForceInverseBackground = useInverseBackground;
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public AlertDialog.Builder setRecycleOnMeasureEnabled(boolean enabled) {
         this.P.mRecycleOnMeasure = enabled;
         return this;
      }

      public AlertDialog create() {
         AlertDialog dialog = new AlertDialog(this.P.mContext, this.mTheme);
         this.P.apply(dialog.mAlert);
         dialog.setCancelable(this.P.mCancelable);
         if (this.P.mCancelable) {
            dialog.setCanceledOnTouchOutside(true);
         }

         dialog.setOnCancelListener(this.P.mOnCancelListener);
         dialog.setOnDismissListener(this.P.mOnDismissListener);
         if (this.P.mOnKeyListener != null) {
            dialog.setOnKeyListener(this.P.mOnKeyListener);
         }

         return dialog;
      }

      public AlertDialog show() {
         AlertDialog dialog = this.create();
         dialog.show();
         return dialog;
      }
   }
}
