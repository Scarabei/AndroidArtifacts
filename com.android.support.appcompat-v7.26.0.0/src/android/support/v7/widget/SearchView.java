package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.string;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.CollapsibleActionView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.KeyEvent.DispatcherState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLayoutChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
   static final boolean DBG = false;
   static final String LOG_TAG = "SearchView";
   private static final String IME_OPTION_NO_MICROPHONE = "nm";
   final SearchView.SearchAutoComplete mSearchSrcTextView;
   private final View mSearchEditFrame;
   private final View mSearchPlate;
   private final View mSubmitArea;
   final ImageView mSearchButton;
   final ImageView mGoButton;
   final ImageView mCloseButton;
   final ImageView mVoiceButton;
   private final View mDropDownAnchor;
   private SearchView.UpdatableTouchDelegate mTouchDelegate;
   private Rect mSearchSrcTextViewBounds;
   private Rect mSearchSrtTextViewBoundsExpanded;
   private int[] mTemp;
   private int[] mTemp2;
   private final ImageView mCollapsedIcon;
   private final Drawable mSearchHintIcon;
   private final int mSuggestionRowLayout;
   private final int mSuggestionCommitIconResId;
   private final Intent mVoiceWebSearchIntent;
   private final Intent mVoiceAppSearchIntent;
   private final CharSequence mDefaultQueryHint;
   private SearchView.OnQueryTextListener mOnQueryChangeListener;
   private SearchView.OnCloseListener mOnCloseListener;
   OnFocusChangeListener mOnQueryTextFocusChangeListener;
   private SearchView.OnSuggestionListener mOnSuggestionListener;
   private OnClickListener mOnSearchClickListener;
   private boolean mIconifiedByDefault;
   private boolean mIconified;
   CursorAdapter mSuggestionsAdapter;
   private boolean mSubmitButtonEnabled;
   private CharSequence mQueryHint;
   private boolean mQueryRefinement;
   private boolean mClearingFocus;
   private int mMaxWidth;
   private boolean mVoiceButtonEnabled;
   private CharSequence mOldQueryText;
   private CharSequence mUserQuery;
   private boolean mExpandedInActionView;
   private int mCollapsedImeOptions;
   SearchableInfo mSearchable;
   private Bundle mAppSearchData;
   static final SearchView.AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER = new SearchView.AutoCompleteTextViewReflector();
   private final Runnable mUpdateDrawableStateRunnable;
   private Runnable mReleaseCursorRunnable;
   private final WeakHashMap mOutsideDrawablesCache;
   private final OnClickListener mOnClickListener;
   OnKeyListener mTextKeyListener;
   private final OnEditorActionListener mOnEditorActionListener;
   private final OnItemClickListener mOnItemClickListener;
   private final OnItemSelectedListener mOnItemSelectedListener;
   private TextWatcher mTextWatcher;

   public SearchView(Context context) {
      this(context, (AttributeSet)null);
   }

   public SearchView(Context context, AttributeSet attrs) {
      this(context, attrs, attr.searchViewStyle);
   }

   public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      this.mSearchSrcTextViewBounds = new Rect();
      this.mSearchSrtTextViewBoundsExpanded = new Rect();
      this.mTemp = new int[2];
      this.mTemp2 = new int[2];
      this.mUpdateDrawableStateRunnable = new Runnable() {
         public void run() {
            SearchView.this.updateFocusedState();
         }
      };
      this.mReleaseCursorRunnable = new Runnable() {
         public void run() {
            if (SearchView.this.mSuggestionsAdapter != null && SearchView.this.mSuggestionsAdapter instanceof SuggestionsAdapter) {
               SearchView.this.mSuggestionsAdapter.changeCursor((Cursor)null);
            }

         }
      };
      this.mOutsideDrawablesCache = new WeakHashMap();
      this.mOnClickListener = new OnClickListener() {
         public void onClick(View v) {
            if (v == SearchView.this.mSearchButton) {
               SearchView.this.onSearchClicked();
            } else if (v == SearchView.this.mCloseButton) {
               SearchView.this.onCloseClicked();
            } else if (v == SearchView.this.mGoButton) {
               SearchView.this.onSubmitQuery();
            } else if (v == SearchView.this.mVoiceButton) {
               SearchView.this.onVoiceClicked();
            } else if (v == SearchView.this.mSearchSrcTextView) {
               SearchView.this.forceSuggestionQuery();
            }

         }
      };
      this.mTextKeyListener = new OnKeyListener() {
         public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (SearchView.this.mSearchable == null) {
               return false;
            } else if (SearchView.this.mSearchSrcTextView.isPopupShowing() && SearchView.this.mSearchSrcTextView.getListSelection() != -1) {
               return SearchView.this.onSuggestionsKey(v, keyCode, event);
            } else if (!SearchView.this.mSearchSrcTextView.isEmpty() && event.hasNoModifiers() && event.getAction() == 1 && keyCode == 66) {
               v.cancelLongPress();
               SearchView.this.launchQuerySearch(0, (String)null, SearchView.this.mSearchSrcTextView.getText().toString());
               return true;
            } else {
               return false;
            }
         }
      };
      this.mOnEditorActionListener = new OnEditorActionListener() {
         public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            SearchView.this.onSubmitQuery();
            return true;
         }
      };
      this.mOnItemClickListener = new OnItemClickListener() {
         public void onItemClick(AdapterView parent, View view, int position, long id) {
            SearchView.this.onItemClicked(position, 0, (String)null);
         }
      };
      this.mOnItemSelectedListener = new OnItemSelectedListener() {
         public void onItemSelected(AdapterView parent, View view, int position, long id) {
            SearchView.this.onItemSelected(position);
         }

         public void onNothingSelected(AdapterView parent) {
         }
      };
      this.mTextWatcher = new TextWatcher() {
         public void beforeTextChanged(CharSequence s, int start, int before, int after) {
         }

         public void onTextChanged(CharSequence s, int start, int before, int after) {
            SearchView.this.onTextChanged(s);
         }

         public void afterTextChanged(Editable s) {
         }
      };
      TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, styleable.SearchView, defStyleAttr, 0);
      LayoutInflater inflater = LayoutInflater.from(context);
      int layoutResId = a.getResourceId(styleable.SearchView_layout, layout.abc_search_view);
      inflater.inflate(layoutResId, this, true);
      this.mSearchSrcTextView = (SearchView.SearchAutoComplete)this.findViewById(id.search_src_text);
      this.mSearchSrcTextView.setSearchView(this);
      this.mSearchEditFrame = this.findViewById(id.search_edit_frame);
      this.mSearchPlate = this.findViewById(id.search_plate);
      this.mSubmitArea = this.findViewById(id.submit_area);
      this.mSearchButton = (ImageView)this.findViewById(id.search_button);
      this.mGoButton = (ImageView)this.findViewById(id.search_go_btn);
      this.mCloseButton = (ImageView)this.findViewById(id.search_close_btn);
      this.mVoiceButton = (ImageView)this.findViewById(id.search_voice_btn);
      this.mCollapsedIcon = (ImageView)this.findViewById(id.search_mag_icon);
      ViewCompat.setBackground(this.mSearchPlate, a.getDrawable(styleable.SearchView_queryBackground));
      ViewCompat.setBackground(this.mSubmitArea, a.getDrawable(styleable.SearchView_submitBackground));
      this.mSearchButton.setImageDrawable(a.getDrawable(styleable.SearchView_searchIcon));
      this.mGoButton.setImageDrawable(a.getDrawable(styleable.SearchView_goIcon));
      this.mCloseButton.setImageDrawable(a.getDrawable(styleable.SearchView_closeIcon));
      this.mVoiceButton.setImageDrawable(a.getDrawable(styleable.SearchView_voiceIcon));
      this.mCollapsedIcon.setImageDrawable(a.getDrawable(styleable.SearchView_searchIcon));
      this.mSearchHintIcon = a.getDrawable(styleable.SearchView_searchHintIcon);
      TooltipCompat.setTooltipText(this.mSearchButton, this.getResources().getString(string.abc_searchview_description_search));
      this.mSuggestionRowLayout = a.getResourceId(styleable.SearchView_suggestionRowLayout, layout.abc_search_dropdown_item_icons_2line);
      this.mSuggestionCommitIconResId = a.getResourceId(styleable.SearchView_commitIcon, 0);
      this.mSearchButton.setOnClickListener(this.mOnClickListener);
      this.mCloseButton.setOnClickListener(this.mOnClickListener);
      this.mGoButton.setOnClickListener(this.mOnClickListener);
      this.mVoiceButton.setOnClickListener(this.mOnClickListener);
      this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
      this.mSearchSrcTextView.addTextChangedListener(this.mTextWatcher);
      this.mSearchSrcTextView.setOnEditorActionListener(this.mOnEditorActionListener);
      this.mSearchSrcTextView.setOnItemClickListener(this.mOnItemClickListener);
      this.mSearchSrcTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
      this.mSearchSrcTextView.setOnKeyListener(this.mTextKeyListener);
      this.mSearchSrcTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
         public void onFocusChange(View v, boolean hasFocus) {
            if (SearchView.this.mOnQueryTextFocusChangeListener != null) {
               SearchView.this.mOnQueryTextFocusChangeListener.onFocusChange(SearchView.this, hasFocus);
            }

         }
      });
      this.setIconifiedByDefault(a.getBoolean(styleable.SearchView_iconifiedByDefault, true));
      int maxWidth = a.getDimensionPixelSize(styleable.SearchView_android_maxWidth, -1);
      if (maxWidth != -1) {
         this.setMaxWidth(maxWidth);
      }

      this.mDefaultQueryHint = a.getText(styleable.SearchView_defaultQueryHint);
      this.mQueryHint = a.getText(styleable.SearchView_queryHint);
      int imeOptions = a.getInt(styleable.SearchView_android_imeOptions, -1);
      if (imeOptions != -1) {
         this.setImeOptions(imeOptions);
      }

      int inputType = a.getInt(styleable.SearchView_android_inputType, -1);
      if (inputType != -1) {
         this.setInputType(inputType);
      }

      boolean focusable = true;
      focusable = a.getBoolean(styleable.SearchView_android_focusable, focusable);
      this.setFocusable(focusable);
      a.recycle();
      this.mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
      this.mVoiceWebSearchIntent.addFlags(268435456);
      this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
      this.mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
      this.mVoiceAppSearchIntent.addFlags(268435456);
      this.mDropDownAnchor = this.findViewById(this.mSearchSrcTextView.getDropDownAnchor());
      if (this.mDropDownAnchor != null) {
         this.mDropDownAnchor.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
               SearchView.this.adjustDropDownSizeAndPosition();
            }
         });
      }

      this.updateViewsVisibility(this.mIconifiedByDefault);
      this.updateQueryHint();
   }

   int getSuggestionRowLayout() {
      return this.mSuggestionRowLayout;
   }

   int getSuggestionCommitIconResId() {
      return this.mSuggestionCommitIconResId;
   }

   public void setSearchableInfo(SearchableInfo searchable) {
      this.mSearchable = searchable;
      if (this.mSearchable != null) {
         this.updateSearchAutoComplete();
         this.updateQueryHint();
      }

      this.mVoiceButtonEnabled = this.hasVoiceSearch();
      if (this.mVoiceButtonEnabled) {
         this.mSearchSrcTextView.setPrivateImeOptions("nm");
      }

      this.updateViewsVisibility(this.isIconified());
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void setAppSearchData(Bundle appSearchData) {
      this.mAppSearchData = appSearchData;
   }

   public void setImeOptions(int imeOptions) {
      this.mSearchSrcTextView.setImeOptions(imeOptions);
   }

   public int getImeOptions() {
      return this.mSearchSrcTextView.getImeOptions();
   }

   public void setInputType(int inputType) {
      this.mSearchSrcTextView.setInputType(inputType);
   }

   public int getInputType() {
      return this.mSearchSrcTextView.getInputType();
   }

   public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
      if (this.mClearingFocus) {
         return false;
      } else if (!this.isFocusable()) {
         return false;
      } else if (!this.isIconified()) {
         boolean result = this.mSearchSrcTextView.requestFocus(direction, previouslyFocusedRect);
         if (result) {
            this.updateViewsVisibility(false);
         }

         return result;
      } else {
         return super.requestFocus(direction, previouslyFocusedRect);
      }
   }

   public void clearFocus() {
      this.mClearingFocus = true;
      super.clearFocus();
      this.mSearchSrcTextView.clearFocus();
      this.mSearchSrcTextView.setImeVisibility(false);
      this.mClearingFocus = false;
   }

   public void setOnQueryTextListener(SearchView.OnQueryTextListener listener) {
      this.mOnQueryChangeListener = listener;
   }

   public void setOnCloseListener(SearchView.OnCloseListener listener) {
      this.mOnCloseListener = listener;
   }

   public void setOnQueryTextFocusChangeListener(OnFocusChangeListener listener) {
      this.mOnQueryTextFocusChangeListener = listener;
   }

   public void setOnSuggestionListener(SearchView.OnSuggestionListener listener) {
      this.mOnSuggestionListener = listener;
   }

   public void setOnSearchClickListener(OnClickListener listener) {
      this.mOnSearchClickListener = listener;
   }

   public CharSequence getQuery() {
      return this.mSearchSrcTextView.getText();
   }

   public void setQuery(CharSequence query, boolean submit) {
      this.mSearchSrcTextView.setText(query);
      if (query != null) {
         this.mSearchSrcTextView.setSelection(this.mSearchSrcTextView.length());
         this.mUserQuery = query;
      }

      if (submit && !TextUtils.isEmpty(query)) {
         this.onSubmitQuery();
      }

   }

   public void setQueryHint(@Nullable CharSequence hint) {
      this.mQueryHint = hint;
      this.updateQueryHint();
   }

   @Nullable
   public CharSequence getQueryHint() {
      CharSequence hint;
      if (this.mQueryHint != null) {
         hint = this.mQueryHint;
      } else if (this.mSearchable != null && this.mSearchable.getHintId() != 0) {
         hint = this.getContext().getText(this.mSearchable.getHintId());
      } else {
         hint = this.mDefaultQueryHint;
      }

      return hint;
   }

   public void setIconifiedByDefault(boolean iconified) {
      if (this.mIconifiedByDefault != iconified) {
         this.mIconifiedByDefault = iconified;
         this.updateViewsVisibility(iconified);
         this.updateQueryHint();
      }
   }

   public boolean isIconfiedByDefault() {
      return this.mIconifiedByDefault;
   }

   public void setIconified(boolean iconify) {
      if (iconify) {
         this.onCloseClicked();
      } else {
         this.onSearchClicked();
      }

   }

   public boolean isIconified() {
      return this.mIconified;
   }

   public void setSubmitButtonEnabled(boolean enabled) {
      this.mSubmitButtonEnabled = enabled;
      this.updateViewsVisibility(this.isIconified());
   }

   public boolean isSubmitButtonEnabled() {
      return this.mSubmitButtonEnabled;
   }

   public void setQueryRefinementEnabled(boolean enable) {
      this.mQueryRefinement = enable;
      if (this.mSuggestionsAdapter instanceof SuggestionsAdapter) {
         ((SuggestionsAdapter)this.mSuggestionsAdapter).setQueryRefinement(enable ? 2 : 1);
      }

   }

   public boolean isQueryRefinementEnabled() {
      return this.mQueryRefinement;
   }

   public void setSuggestionsAdapter(CursorAdapter adapter) {
      this.mSuggestionsAdapter = adapter;
      this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
   }

   public CursorAdapter getSuggestionsAdapter() {
      return this.mSuggestionsAdapter;
   }

   public void setMaxWidth(int maxpixels) {
      this.mMaxWidth = maxpixels;
      this.requestLayout();
   }

   public int getMaxWidth() {
      return this.mMaxWidth;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      if (this.isIconified()) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      } else {
         int widthMode = MeasureSpec.getMode(widthMeasureSpec);
         int width = MeasureSpec.getSize(widthMeasureSpec);
         switch(widthMode) {
         case Integer.MIN_VALUE:
            if (this.mMaxWidth > 0) {
               width = Math.min(this.mMaxWidth, width);
            } else {
               width = Math.min(this.getPreferredWidth(), width);
            }
            break;
         case 0:
            width = this.mMaxWidth > 0 ? this.mMaxWidth : this.getPreferredWidth();
            break;
         case 1073741824:
            if (this.mMaxWidth > 0) {
               width = Math.min(this.mMaxWidth, width);
            }
         }

         widthMode = 1073741824;
         int heightMode = MeasureSpec.getMode(heightMeasureSpec);
         int height = MeasureSpec.getSize(heightMeasureSpec);
         switch(heightMode) {
         case Integer.MIN_VALUE:
            height = Math.min(this.getPreferredHeight(), height);
            break;
         case 0:
            height = this.getPreferredHeight();
         }

         heightMode = 1073741824;
         super.onMeasure(MeasureSpec.makeMeasureSpec(width, widthMode), MeasureSpec.makeMeasureSpec(height, heightMode));
      }
   }

   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      if (changed) {
         this.getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
         this.mSearchSrtTextViewBoundsExpanded.set(this.mSearchSrcTextViewBounds.left, 0, this.mSearchSrcTextViewBounds.right, bottom - top);
         if (this.mTouchDelegate == null) {
            this.mTouchDelegate = new SearchView.UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, this.mSearchSrcTextView);
            this.setTouchDelegate(this.mTouchDelegate);
         } else {
            this.mTouchDelegate.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
         }
      }

   }

   private void getChildBoundsWithinSearchView(View view, Rect rect) {
      view.getLocationInWindow(this.mTemp);
      this.getLocationInWindow(this.mTemp2);
      int top = this.mTemp[1] - this.mTemp2[1];
      int left = this.mTemp[0] - this.mTemp2[0];
      rect.set(left, top, left + view.getWidth(), top + view.getHeight());
   }

   private int getPreferredWidth() {
      return this.getContext().getResources().getDimensionPixelSize(dimen.abc_search_view_preferred_width);
   }

   private int getPreferredHeight() {
      return this.getContext().getResources().getDimensionPixelSize(dimen.abc_search_view_preferred_height);
   }

   private void updateViewsVisibility(boolean collapsed) {
      this.mIconified = collapsed;
      int visCollapsed = collapsed ? 0 : 8;
      boolean hasText = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
      this.mSearchButton.setVisibility(visCollapsed);
      this.updateSubmitButton(hasText);
      this.mSearchEditFrame.setVisibility(collapsed ? 8 : 0);
      byte iconVisibility;
      if (this.mCollapsedIcon.getDrawable() != null && !this.mIconifiedByDefault) {
         iconVisibility = 0;
      } else {
         iconVisibility = 8;
      }

      this.mCollapsedIcon.setVisibility(iconVisibility);
      this.updateCloseButton();
      this.updateVoiceButton(!hasText);
      this.updateSubmitArea();
   }

   private boolean hasVoiceSearch() {
      if (this.mSearchable != null && this.mSearchable.getVoiceSearchEnabled()) {
         Intent testIntent = null;
         if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
            testIntent = this.mVoiceWebSearchIntent;
         } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
            testIntent = this.mVoiceAppSearchIntent;
         }

         if (testIntent != null) {
            ResolveInfo ri = this.getContext().getPackageManager().resolveActivity(testIntent, 65536);
            return ri != null;
         }
      }

      return false;
   }

   private boolean isSubmitAreaEnabled() {
      return (this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !this.isIconified();
   }

   private void updateSubmitButton(boolean hasText) {
      int visibility = 8;
      if (this.mSubmitButtonEnabled && this.isSubmitAreaEnabled() && this.hasFocus() && (hasText || !this.mVoiceButtonEnabled)) {
         visibility = 0;
      }

      this.mGoButton.setVisibility(visibility);
   }

   private void updateSubmitArea() {
      int visibility = 8;
      if (this.isSubmitAreaEnabled() && (this.mGoButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) {
         visibility = 0;
      }

      this.mSubmitArea.setVisibility(visibility);
   }

   private void updateCloseButton() {
      boolean hasText = !TextUtils.isEmpty(this.mSearchSrcTextView.getText());
      boolean showClose = hasText || this.mIconifiedByDefault && !this.mExpandedInActionView;
      this.mCloseButton.setVisibility(showClose ? 0 : 8);
      Drawable closeButtonImg = this.mCloseButton.getDrawable();
      if (closeButtonImg != null) {
         closeButtonImg.setState(hasText ? ENABLED_STATE_SET : EMPTY_STATE_SET);
      }

   }

   private void postUpdateFocusedState() {
      this.post(this.mUpdateDrawableStateRunnable);
   }

   void updateFocusedState() {
      boolean focused = this.mSearchSrcTextView.hasFocus();
      int[] stateSet = focused ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
      Drawable searchPlateBg = this.mSearchPlate.getBackground();
      if (searchPlateBg != null) {
         searchPlateBg.setState(stateSet);
      }

      Drawable submitAreaBg = this.mSubmitArea.getBackground();
      if (submitAreaBg != null) {
         submitAreaBg.setState(stateSet);
      }

      this.invalidate();
   }

   protected void onDetachedFromWindow() {
      this.removeCallbacks(this.mUpdateDrawableStateRunnable);
      this.post(this.mReleaseCursorRunnable);
      super.onDetachedFromWindow();
   }

   void onQueryRefine(CharSequence queryText) {
      this.setQuery(queryText);
   }

   boolean onSuggestionsKey(View v, int keyCode, KeyEvent event) {
      if (this.mSearchable == null) {
         return false;
      } else if (this.mSuggestionsAdapter == null) {
         return false;
      } else if (event.getAction() == 0 && event.hasNoModifiers()) {
         int selPoint;
         if (keyCode != 66 && keyCode != 84 && keyCode != 61) {
            if (keyCode != 21 && keyCode != 22) {
               if (keyCode == 19 && 0 == this.mSearchSrcTextView.getListSelection()) {
                  return false;
               } else {
                  return false;
               }
            } else {
               selPoint = keyCode == 21 ? 0 : this.mSearchSrcTextView.length();
               this.mSearchSrcTextView.setSelection(selPoint);
               this.mSearchSrcTextView.setListSelection(0);
               this.mSearchSrcTextView.clearListSelection();
               HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mSearchSrcTextView, true);
               return true;
            }
         } else {
            selPoint = this.mSearchSrcTextView.getListSelection();
            return this.onItemClicked(selPoint, 0, (String)null);
         }
      } else {
         return false;
      }
   }

   private CharSequence getDecoratedHint(CharSequence hintText) {
      if (this.mIconifiedByDefault && this.mSearchHintIcon != null) {
         int textSize = (int)((double)this.mSearchSrcTextView.getTextSize() * 1.25D);
         this.mSearchHintIcon.setBounds(0, 0, textSize, textSize);
         SpannableStringBuilder ssb = new SpannableStringBuilder("   ");
         ssb.setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
         ssb.append(hintText);
         return ssb;
      } else {
         return hintText;
      }
   }

   private void updateQueryHint() {
      CharSequence hint = this.getQueryHint();
      this.mSearchSrcTextView.setHint(this.getDecoratedHint((CharSequence)(hint == null ? "" : hint)));
   }

   private void updateSearchAutoComplete() {
      this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
      this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
      int inputType = this.mSearchable.getInputType();
      if ((inputType & 15) == 1) {
         inputType &= -65537;
         if (this.mSearchable.getSuggestAuthority() != null) {
            inputType |= 65536;
            inputType |= 524288;
         }
      }

      this.mSearchSrcTextView.setInputType(inputType);
      if (this.mSuggestionsAdapter != null) {
         this.mSuggestionsAdapter.changeCursor((Cursor)null);
      }

      if (this.mSearchable.getSuggestAuthority() != null) {
         this.mSuggestionsAdapter = new SuggestionsAdapter(this.getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
         this.mSearchSrcTextView.setAdapter(this.mSuggestionsAdapter);
         ((SuggestionsAdapter)this.mSuggestionsAdapter).setQueryRefinement(this.mQueryRefinement ? 2 : 1);
      }

   }

   private void updateVoiceButton(boolean empty) {
      int visibility = 8;
      if (this.mVoiceButtonEnabled && !this.isIconified() && empty) {
         visibility = 0;
         this.mGoButton.setVisibility(8);
      }

      this.mVoiceButton.setVisibility(visibility);
   }

   void onTextChanged(CharSequence newText) {
      CharSequence text = this.mSearchSrcTextView.getText();
      this.mUserQuery = text;
      boolean hasText = !TextUtils.isEmpty(text);
      this.updateSubmitButton(hasText);
      this.updateVoiceButton(!hasText);
      this.updateCloseButton();
      this.updateSubmitArea();
      if (this.mOnQueryChangeListener != null && !TextUtils.equals(newText, this.mOldQueryText)) {
         this.mOnQueryChangeListener.onQueryTextChange(newText.toString());
      }

      this.mOldQueryText = newText.toString();
   }

   void onSubmitQuery() {
      CharSequence query = this.mSearchSrcTextView.getText();
      if (query != null && TextUtils.getTrimmedLength(query) > 0 && (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(query.toString()))) {
         if (this.mSearchable != null) {
            this.launchQuerySearch(0, (String)null, query.toString());
         }

         this.mSearchSrcTextView.setImeVisibility(false);
         this.dismissSuggestions();
      }

   }

   private void dismissSuggestions() {
      this.mSearchSrcTextView.dismissDropDown();
   }

   void onCloseClicked() {
      CharSequence text = this.mSearchSrcTextView.getText();
      if (TextUtils.isEmpty(text)) {
         if (this.mIconifiedByDefault && (this.mOnCloseListener == null || !this.mOnCloseListener.onClose())) {
            this.clearFocus();
            this.updateViewsVisibility(true);
         }
      } else {
         this.mSearchSrcTextView.setText("");
         this.mSearchSrcTextView.requestFocus();
         this.mSearchSrcTextView.setImeVisibility(true);
      }

   }

   void onSearchClicked() {
      this.updateViewsVisibility(false);
      this.mSearchSrcTextView.requestFocus();
      this.mSearchSrcTextView.setImeVisibility(true);
      if (this.mOnSearchClickListener != null) {
         this.mOnSearchClickListener.onClick(this);
      }

   }

   void onVoiceClicked() {
      if (this.mSearchable != null) {
         SearchableInfo searchable = this.mSearchable;

         try {
            Intent appSearchIntent;
            if (searchable.getVoiceSearchLaunchWebSearch()) {
               appSearchIntent = this.createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchable);
               this.getContext().startActivity(appSearchIntent);
            } else if (searchable.getVoiceSearchLaunchRecognizer()) {
               appSearchIntent = this.createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchable);
               this.getContext().startActivity(appSearchIntent);
            }
         } catch (ActivityNotFoundException var3) {
            Log.w("SearchView", "Could not find voice search activity");
         }

      }
   }

   void onTextFocusChanged() {
      this.updateViewsVisibility(this.isIconified());
      this.postUpdateFocusedState();
      if (this.mSearchSrcTextView.hasFocus()) {
         this.forceSuggestionQuery();
      }

   }

   public void onWindowFocusChanged(boolean hasWindowFocus) {
      super.onWindowFocusChanged(hasWindowFocus);
      this.postUpdateFocusedState();
   }

   public void onActionViewCollapsed() {
      this.setQuery("", false);
      this.clearFocus();
      this.updateViewsVisibility(true);
      this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
      this.mExpandedInActionView = false;
   }

   public void onActionViewExpanded() {
      if (!this.mExpandedInActionView) {
         this.mExpandedInActionView = true;
         this.mCollapsedImeOptions = this.mSearchSrcTextView.getImeOptions();
         this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions | 33554432);
         this.mSearchSrcTextView.setText("");
         this.setIconified(false);
      }
   }

   protected Parcelable onSaveInstanceState() {
      Parcelable superState = super.onSaveInstanceState();
      SearchView.SavedState ss = new SearchView.SavedState(superState);
      ss.isIconified = this.isIconified();
      return ss;
   }

   protected void onRestoreInstanceState(Parcelable state) {
      if (!(state instanceof SearchView.SavedState)) {
         super.onRestoreInstanceState(state);
      } else {
         SearchView.SavedState ss = (SearchView.SavedState)state;
         super.onRestoreInstanceState(ss.getSuperState());
         this.updateViewsVisibility(ss.isIconified);
         this.requestLayout();
      }
   }

   void adjustDropDownSizeAndPosition() {
      if (this.mDropDownAnchor.getWidth() > 1) {
         Resources res = this.getContext().getResources();
         int anchorPadding = this.mSearchPlate.getPaddingLeft();
         Rect dropDownPadding = new Rect();
         boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
         int iconOffset = this.mIconifiedByDefault ? res.getDimensionPixelSize(dimen.abc_dropdownitem_icon_width) + res.getDimensionPixelSize(dimen.abc_dropdownitem_text_padding_left) : 0;
         this.mSearchSrcTextView.getDropDownBackground().getPadding(dropDownPadding);
         int offset;
         if (isLayoutRtl) {
            offset = -dropDownPadding.left;
         } else {
            offset = anchorPadding - (dropDownPadding.left + iconOffset);
         }

         this.mSearchSrcTextView.setDropDownHorizontalOffset(offset);
         int width = this.mDropDownAnchor.getWidth() + dropDownPadding.left + dropDownPadding.right + iconOffset - anchorPadding;
         this.mSearchSrcTextView.setDropDownWidth(width);
      }

   }

   boolean onItemClicked(int position, int actionKey, String actionMsg) {
      if (this.mOnSuggestionListener != null && this.mOnSuggestionListener.onSuggestionClick(position)) {
         return false;
      } else {
         this.launchSuggestion(position, 0, (String)null);
         this.mSearchSrcTextView.setImeVisibility(false);
         this.dismissSuggestions();
         return true;
      }
   }

   boolean onItemSelected(int position) {
      if (this.mOnSuggestionListener != null && this.mOnSuggestionListener.onSuggestionSelect(position)) {
         return false;
      } else {
         this.rewriteQueryFromSuggestion(position);
         return true;
      }
   }

   private void rewriteQueryFromSuggestion(int position) {
      CharSequence oldQuery = this.mSearchSrcTextView.getText();
      Cursor c = this.mSuggestionsAdapter.getCursor();
      if (c != null) {
         if (c.moveToPosition(position)) {
            CharSequence newQuery = this.mSuggestionsAdapter.convertToString(c);
            if (newQuery != null) {
               this.setQuery(newQuery);
            } else {
               this.setQuery(oldQuery);
            }
         } else {
            this.setQuery(oldQuery);
         }

      }
   }

   private boolean launchSuggestion(int position, int actionKey, String actionMsg) {
      Cursor c = this.mSuggestionsAdapter.getCursor();
      if (c != null && c.moveToPosition(position)) {
         Intent intent = this.createIntentFromSuggestion(c, actionKey, actionMsg);
         this.launchIntent(intent);
         return true;
      } else {
         return false;
      }
   }

   private void launchIntent(Intent intent) {
      if (intent != null) {
         try {
            this.getContext().startActivity(intent);
         } catch (RuntimeException var3) {
            Log.e("SearchView", "Failed launch activity: " + intent, var3);
         }

      }
   }

   private void setQuery(CharSequence query) {
      this.mSearchSrcTextView.setText(query);
      this.mSearchSrcTextView.setSelection(TextUtils.isEmpty(query) ? 0 : query.length());
   }

   void launchQuerySearch(int actionKey, String actionMsg, String query) {
      String action = "android.intent.action.SEARCH";
      Intent intent = this.createIntent(action, (Uri)null, (String)null, query, actionKey, actionMsg);
      this.getContext().startActivity(intent);
   }

   private Intent createIntent(String action, Uri data, String extraData, String query, int actionKey, String actionMsg) {
      Intent intent = new Intent(action);
      intent.addFlags(268435456);
      if (data != null) {
         intent.setData(data);
      }

      intent.putExtra("user_query", this.mUserQuery);
      if (query != null) {
         intent.putExtra("query", query);
      }

      if (extraData != null) {
         intent.putExtra("intent_extra_data_key", extraData);
      }

      if (this.mAppSearchData != null) {
         intent.putExtra("app_data", this.mAppSearchData);
      }

      if (actionKey != 0) {
         intent.putExtra("action_key", actionKey);
         intent.putExtra("action_msg", actionMsg);
      }

      intent.setComponent(this.mSearchable.getSearchActivity());
      return intent;
   }

   private Intent createVoiceWebSearchIntent(Intent baseIntent, SearchableInfo searchable) {
      Intent voiceIntent = new Intent(baseIntent);
      ComponentName searchActivity = searchable.getSearchActivity();
      voiceIntent.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
      return voiceIntent;
   }

   private Intent createVoiceAppSearchIntent(Intent baseIntent, SearchableInfo searchable) {
      ComponentName searchActivity = searchable.getSearchActivity();
      Intent queryIntent = new Intent("android.intent.action.SEARCH");
      queryIntent.setComponent(searchActivity);
      PendingIntent pending = PendingIntent.getActivity(this.getContext(), 0, queryIntent, 1073741824);
      Bundle queryExtras = new Bundle();
      if (this.mAppSearchData != null) {
         queryExtras.putParcelable("app_data", this.mAppSearchData);
      }

      Intent voiceIntent = new Intent(baseIntent);
      String languageModel = "free_form";
      String prompt = null;
      String language = null;
      int maxResults = 1;
      Resources resources = this.getResources();
      if (searchable.getVoiceLanguageModeId() != 0) {
         languageModel = resources.getString(searchable.getVoiceLanguageModeId());
      }

      if (searchable.getVoicePromptTextId() != 0) {
         prompt = resources.getString(searchable.getVoicePromptTextId());
      }

      if (searchable.getVoiceLanguageId() != 0) {
         language = resources.getString(searchable.getVoiceLanguageId());
      }

      if (searchable.getVoiceMaxResults() != 0) {
         maxResults = searchable.getVoiceMaxResults();
      }

      voiceIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", languageModel);
      voiceIntent.putExtra("android.speech.extra.PROMPT", prompt);
      voiceIntent.putExtra("android.speech.extra.LANGUAGE", language);
      voiceIntent.putExtra("android.speech.extra.MAX_RESULTS", maxResults);
      voiceIntent.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
      voiceIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", pending);
      voiceIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", queryExtras);
      return voiceIntent;
   }

   private Intent createIntentFromSuggestion(Cursor c, int actionKey, String actionMsg) {
      try {
         String action = SuggestionsAdapter.getColumnString(c, "suggest_intent_action");
         if (action == null) {
            action = this.mSearchable.getSuggestIntentAction();
         }

         if (action == null) {
            action = "android.intent.action.SEARCH";
         }

         String data = SuggestionsAdapter.getColumnString(c, "suggest_intent_data");
         if (data == null) {
            data = this.mSearchable.getSuggestIntentData();
         }

         if (data != null) {
            String id = SuggestionsAdapter.getColumnString(c, "suggest_intent_data_id");
            if (id != null) {
               data = data + "/" + Uri.encode(id);
            }
         }

         Uri dataUri = data == null ? null : Uri.parse(data);
         String query = SuggestionsAdapter.getColumnString(c, "suggest_intent_query");
         String extraData = SuggestionsAdapter.getColumnString(c, "suggest_intent_extra_data");
         return this.createIntent(action, dataUri, extraData, query, actionKey, actionMsg);
      } catch (RuntimeException var10) {
         int rowNum;
         try {
            rowNum = c.getPosition();
         } catch (RuntimeException var9) {
            rowNum = -1;
         }

         Log.w("SearchView", "Search suggestions cursor at row " + rowNum + " returned exception.", var10);
         return null;
      }
   }

   void forceSuggestionQuery() {
      HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mSearchSrcTextView);
      HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mSearchSrcTextView);
   }

   static boolean isLandscapeMode(Context context) {
      return context.getResources().getConfiguration().orientation == 2;
   }

   private static class AutoCompleteTextViewReflector {
      private Method doBeforeTextChanged;
      private Method doAfterTextChanged;
      private Method ensureImeVisible;
      private Method showSoftInputUnchecked;

      AutoCompleteTextViewReflector() {
         try {
            this.doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged");
            this.doBeforeTextChanged.setAccessible(true);
         } catch (NoSuchMethodException var4) {
            ;
         }

         try {
            this.doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged");
            this.doAfterTextChanged.setAccessible(true);
         } catch (NoSuchMethodException var3) {
            ;
         }

         try {
            this.ensureImeVisible = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
            this.ensureImeVisible.setAccessible(true);
         } catch (NoSuchMethodException var2) {
            ;
         }

      }

      void doBeforeTextChanged(AutoCompleteTextView view) {
         if (this.doBeforeTextChanged != null) {
            try {
               this.doBeforeTextChanged.invoke(view);
            } catch (Exception var3) {
               ;
            }
         }

      }

      void doAfterTextChanged(AutoCompleteTextView view) {
         if (this.doAfterTextChanged != null) {
            try {
               this.doAfterTextChanged.invoke(view);
            } catch (Exception var3) {
               ;
            }
         }

      }

      void ensureImeVisible(AutoCompleteTextView view, boolean visible) {
         if (this.ensureImeVisible != null) {
            try {
               this.ensureImeVisible.invoke(view, visible);
            } catch (Exception var4) {
               ;
            }
         }

      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
      private int mThreshold;
      private SearchView mSearchView;
      private boolean mHasPendingShowSoftInputRequest;
      final Runnable mRunShowSoftInputIfNecessary;

      public SearchAutoComplete(Context context) {
         this(context, (AttributeSet)null);
      }

      public SearchAutoComplete(Context context, AttributeSet attrs) {
         this(context, attrs, attr.autoCompleteTextViewStyle);
      }

      public SearchAutoComplete(Context context, AttributeSet attrs, int defStyle) {
         super(context, attrs, defStyle);
         this.mRunShowSoftInputIfNecessary = new Runnable() {
            public void run() {
               SearchAutoComplete.this.showSoftInputIfNecessary();
            }
         };
         this.mThreshold = this.getThreshold();
      }

      protected void onFinishInflate() {
         super.onFinishInflate();
         DisplayMetrics metrics = this.getResources().getDisplayMetrics();
         this.setMinWidth((int)TypedValue.applyDimension(1, (float)this.getSearchViewTextMinWidthDp(), metrics));
      }

      void setSearchView(SearchView searchView) {
         this.mSearchView = searchView;
      }

      public void setThreshold(int threshold) {
         super.setThreshold(threshold);
         this.mThreshold = threshold;
      }

      private boolean isEmpty() {
         return TextUtils.getTrimmedLength(this.getText()) == 0;
      }

      protected void replaceText(CharSequence text) {
      }

      public void performCompletion() {
      }

      public void onWindowFocusChanged(boolean hasWindowFocus) {
         super.onWindowFocusChanged(hasWindowFocus);
         if (hasWindowFocus && this.mSearchView.hasFocus() && this.getVisibility() == 0) {
            this.mHasPendingShowSoftInputRequest = true;
            if (SearchView.isLandscapeMode(this.getContext())) {
               SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
            }
         }

      }

      protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
         super.onFocusChanged(focused, direction, previouslyFocusedRect);
         this.mSearchView.onTextFocusChanged();
      }

      public boolean enoughToFilter() {
         return this.mThreshold <= 0 || super.enoughToFilter();
      }

      public boolean onKeyPreIme(int keyCode, KeyEvent event) {
         if (keyCode == 4) {
            DispatcherState state;
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
               state = this.getKeyDispatcherState();
               if (state != null) {
                  state.startTracking(event, this);
               }

               return true;
            }

            if (event.getAction() == 1) {
               state = this.getKeyDispatcherState();
               if (state != null) {
                  state.handleUpEvent(event);
               }

               if (event.isTracking() && !event.isCanceled()) {
                  this.mSearchView.clearFocus();
                  this.setImeVisibility(false);
                  return true;
               }
            }
         }

         return super.onKeyPreIme(keyCode, event);
      }

      private int getSearchViewTextMinWidthDp() {
         Configuration config = this.getResources().getConfiguration();
         int widthDp = config.screenWidthDp;
         int heightDp = config.screenHeightDp;
         if (widthDp >= 960 && heightDp >= 720 && config.orientation == 2) {
            return 256;
         } else {
            return widthDp < 600 && (widthDp < 640 || heightDp < 480) ? 160 : 192;
         }
      }

      public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
         InputConnection ic = super.onCreateInputConnection(editorInfo);
         if (this.mHasPendingShowSoftInputRequest) {
            this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
            this.post(this.mRunShowSoftInputIfNecessary);
         }

         return ic;
      }

      private void showSoftInputIfNecessary() {
         if (this.mHasPendingShowSoftInputRequest) {
            InputMethodManager imm = (InputMethodManager)this.getContext().getSystemService("input_method");
            imm.showSoftInput(this, 0);
            this.mHasPendingShowSoftInputRequest = false;
         }

      }

      private void setImeVisibility(boolean visible) {
         InputMethodManager imm = (InputMethodManager)this.getContext().getSystemService("input_method");
         if (!visible) {
            this.mHasPendingShowSoftInputRequest = false;
            this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
            imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
         } else if (imm.isActive(this)) {
            this.mHasPendingShowSoftInputRequest = false;
            this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
            imm.showSoftInput(this, 0);
         } else {
            this.mHasPendingShowSoftInputRequest = true;
         }
      }
   }

   private static class UpdatableTouchDelegate extends TouchDelegate {
      private final View mDelegateView;
      private final Rect mTargetBounds;
      private final Rect mActualBounds;
      private final Rect mSlopBounds;
      private final int mSlop;
      private boolean mDelegateTargeted;

      public UpdatableTouchDelegate(Rect targetBounds, Rect actualBounds, View delegateView) {
         super(targetBounds, delegateView);
         this.mSlop = ViewConfiguration.get(delegateView.getContext()).getScaledTouchSlop();
         this.mTargetBounds = new Rect();
         this.mSlopBounds = new Rect();
         this.mActualBounds = new Rect();
         this.setBounds(targetBounds, actualBounds);
         this.mDelegateView = delegateView;
      }

      public void setBounds(Rect desiredBounds, Rect actualBounds) {
         this.mTargetBounds.set(desiredBounds);
         this.mSlopBounds.set(desiredBounds);
         this.mSlopBounds.inset(-this.mSlop, -this.mSlop);
         this.mActualBounds.set(actualBounds);
      }

      public boolean onTouchEvent(MotionEvent event) {
         int x = (int)event.getX();
         int y = (int)event.getY();
         boolean sendToDelegate = false;
         boolean hit = true;
         boolean handled = false;
         switch(event.getAction()) {
         case 0:
            if (this.mTargetBounds.contains(x, y)) {
               this.mDelegateTargeted = true;
               sendToDelegate = true;
            }
            break;
         case 1:
         case 2:
            sendToDelegate = this.mDelegateTargeted;
            if (sendToDelegate && !this.mSlopBounds.contains(x, y)) {
               hit = false;
            }
            break;
         case 3:
            sendToDelegate = this.mDelegateTargeted;
            this.mDelegateTargeted = false;
         }

         if (sendToDelegate) {
            if (hit && !this.mActualBounds.contains(x, y)) {
               event.setLocation((float)(this.mDelegateView.getWidth() / 2), (float)(this.mDelegateView.getHeight() / 2));
            } else {
               event.setLocation((float)(x - this.mActualBounds.left), (float)(y - this.mActualBounds.top));
            }

            handled = this.mDelegateView.dispatchTouchEvent(event);
         }

         return handled;
      }
   }

   static class SavedState extends AbsSavedState {
      boolean isIconified;
      public static final Creator CREATOR = new ClassLoaderCreator() {
         public SearchView.SavedState createFromParcel(Parcel in, ClassLoader loader) {
            return new SearchView.SavedState(in, loader);
         }

         public SearchView.SavedState createFromParcel(Parcel in) {
            return new SearchView.SavedState(in, (ClassLoader)null);
         }

         public SearchView.SavedState[] newArray(int size) {
            return new SearchView.SavedState[size];
         }
      };

      SavedState(Parcelable superState) {
         super(superState);
      }

      public SavedState(Parcel source, ClassLoader loader) {
         super(source, loader);
         this.isIconified = ((Boolean)source.readValue((ClassLoader)null)).booleanValue();
      }

      public void writeToParcel(Parcel dest, int flags) {
         super.writeToParcel(dest, flags);
         dest.writeValue(this.isIconified);
      }

      public String toString() {
         return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.isIconified + "}";
      }
   }

   public interface OnSuggestionListener {
      boolean onSuggestionSelect(int var1);

      boolean onSuggestionClick(int var1);
   }

   public interface OnCloseListener {
      boolean onClose();
   }

   public interface OnQueryTextListener {
      boolean onQueryTextSubmit(String var1);

      boolean onQueryTextChange(String var1);
   }
}
