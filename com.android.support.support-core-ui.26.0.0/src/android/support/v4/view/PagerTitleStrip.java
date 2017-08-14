package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils.TruncateAt;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;

@ViewPager.DecorView
public class PagerTitleStrip extends ViewGroup {
   ViewPager mPager;
   TextView mPrevText;
   TextView mCurrText;
   TextView mNextText;
   private int mLastKnownCurrentPage;
   float mLastKnownPositionOffset;
   private int mScaledTextSpacing;
   private int mGravity;
   private boolean mUpdatingText;
   private boolean mUpdatingPositions;
   private final PagerTitleStrip.PageListener mPageListener;
   private WeakReference mWatchingAdapter;
   private static final int[] ATTRS = new int[]{16842804, 16842901, 16842904, 16842927};
   private static final int[] TEXT_ATTRS = new int[]{16843660};
   private static final float SIDE_ALPHA = 0.6F;
   private static final int TEXT_SPACING = 16;
   private int mNonPrimaryAlpha;
   int mTextColor;

   private static void setSingleLineAllCaps(TextView text) {
      text.setTransformationMethod(new PagerTitleStrip.SingleLineAllCapsTransform(text.getContext()));
   }

   public PagerTitleStrip(Context context) {
      this(context, (AttributeSet)null);
   }

   public PagerTitleStrip(Context context, AttributeSet attrs) {
      super(context, attrs);
      this.mLastKnownCurrentPage = -1;
      this.mLastKnownPositionOffset = -1.0F;
      this.mPageListener = new PagerTitleStrip.PageListener();
      this.addView(this.mPrevText = new TextView(context));
      this.addView(this.mCurrText = new TextView(context));
      this.addView(this.mNextText = new TextView(context));
      TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);
      int textAppearance = a.getResourceId(0, 0);
      if (textAppearance != 0) {
         TextViewCompat.setTextAppearance(this.mPrevText, textAppearance);
         TextViewCompat.setTextAppearance(this.mCurrText, textAppearance);
         TextViewCompat.setTextAppearance(this.mNextText, textAppearance);
      }

      int textSize = a.getDimensionPixelSize(1, 0);
      if (textSize != 0) {
         this.setTextSize(0, (float)textSize);
      }

      if (a.hasValue(2)) {
         int textColor = a.getColor(2, 0);
         this.mPrevText.setTextColor(textColor);
         this.mCurrText.setTextColor(textColor);
         this.mNextText.setTextColor(textColor);
      }

      this.mGravity = a.getInteger(3, 80);
      a.recycle();
      this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
      this.setNonPrimaryAlpha(0.6F);
      this.mPrevText.setEllipsize(TruncateAt.END);
      this.mCurrText.setEllipsize(TruncateAt.END);
      this.mNextText.setEllipsize(TruncateAt.END);
      boolean allCaps = false;
      if (textAppearance != 0) {
         TypedArray ta = context.obtainStyledAttributes(textAppearance, TEXT_ATTRS);
         allCaps = ta.getBoolean(0, false);
         ta.recycle();
      }

      if (allCaps) {
         setSingleLineAllCaps(this.mPrevText);
         setSingleLineAllCaps(this.mCurrText);
         setSingleLineAllCaps(this.mNextText);
      } else {
         this.mPrevText.setSingleLine();
         this.mCurrText.setSingleLine();
         this.mNextText.setSingleLine();
      }

      float density = context.getResources().getDisplayMetrics().density;
      this.mScaledTextSpacing = (int)(16.0F * density);
   }

   public void setTextSpacing(int spacingPixels) {
      this.mScaledTextSpacing = spacingPixels;
      this.requestLayout();
   }

   public int getTextSpacing() {
      return this.mScaledTextSpacing;
   }

   public void setNonPrimaryAlpha(@FloatRange(from = 0.0D,to = 1.0D) float alpha) {
      this.mNonPrimaryAlpha = (int)(alpha * 255.0F) & 255;
      int transparentColor = this.mNonPrimaryAlpha << 24 | this.mTextColor & 16777215;
      this.mPrevText.setTextColor(transparentColor);
      this.mNextText.setTextColor(transparentColor);
   }

   public void setTextColor(@ColorInt int color) {
      this.mTextColor = color;
      this.mCurrText.setTextColor(color);
      int transparentColor = this.mNonPrimaryAlpha << 24 | this.mTextColor & 16777215;
      this.mPrevText.setTextColor(transparentColor);
      this.mNextText.setTextColor(transparentColor);
   }

   public void setTextSize(int unit, float size) {
      this.mPrevText.setTextSize(unit, size);
      this.mCurrText.setTextSize(unit, size);
      this.mNextText.setTextSize(unit, size);
   }

   public void setGravity(int gravity) {
      this.mGravity = gravity;
      this.requestLayout();
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      ViewParent parent = this.getParent();
      if (!(parent instanceof ViewPager)) {
         throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
      } else {
         ViewPager pager = (ViewPager)parent;
         PagerAdapter adapter = pager.getAdapter();
         pager.setInternalPageChangeListener(this.mPageListener);
         pager.addOnAdapterChangeListener(this.mPageListener);
         this.mPager = pager;
         this.updateAdapter(this.mWatchingAdapter != null ? (PagerAdapter)this.mWatchingAdapter.get() : null, adapter);
      }
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.mPager != null) {
         this.updateAdapter(this.mPager.getAdapter(), (PagerAdapter)null);
         this.mPager.setInternalPageChangeListener((ViewPager.OnPageChangeListener)null);
         this.mPager.removeOnAdapterChangeListener(this.mPageListener);
         this.mPager = null;
      }

   }

   void updateText(int currentItem, PagerAdapter adapter) {
      int itemCount = adapter != null ? adapter.getCount() : 0;
      this.mUpdatingText = true;
      CharSequence text = null;
      if (currentItem >= 1 && adapter != null) {
         text = adapter.getPageTitle(currentItem - 1);
      }

      this.mPrevText.setText(text);
      this.mCurrText.setText(adapter != null && currentItem < itemCount ? adapter.getPageTitle(currentItem) : null);
      text = null;
      if (currentItem + 1 < itemCount && adapter != null) {
         text = adapter.getPageTitle(currentItem + 1);
      }

      this.mNextText.setText(text);
      int width = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
      int maxWidth = Math.max(0, (int)((float)width * 0.8F));
      int childWidthSpec = MeasureSpec.makeMeasureSpec(maxWidth, Integer.MIN_VALUE);
      int childHeight = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
      int maxHeight = Math.max(0, childHeight);
      int childHeightSpec = MeasureSpec.makeMeasureSpec(maxHeight, Integer.MIN_VALUE);
      this.mPrevText.measure(childWidthSpec, childHeightSpec);
      this.mCurrText.measure(childWidthSpec, childHeightSpec);
      this.mNextText.measure(childWidthSpec, childHeightSpec);
      this.mLastKnownCurrentPage = currentItem;
      if (!this.mUpdatingPositions) {
         this.updateTextPositions(currentItem, this.mLastKnownPositionOffset, false);
      }

      this.mUpdatingText = false;
   }

   public void requestLayout() {
      if (!this.mUpdatingText) {
         super.requestLayout();
      }

   }

   void updateAdapter(PagerAdapter oldAdapter, PagerAdapter newAdapter) {
      if (oldAdapter != null) {
         oldAdapter.unregisterDataSetObserver(this.mPageListener);
         this.mWatchingAdapter = null;
      }

      if (newAdapter != null) {
         newAdapter.registerDataSetObserver(this.mPageListener);
         this.mWatchingAdapter = new WeakReference(newAdapter);
      }

      if (this.mPager != null) {
         this.mLastKnownCurrentPage = -1;
         this.mLastKnownPositionOffset = -1.0F;
         this.updateText(this.mPager.getCurrentItem(), newAdapter);
         this.requestLayout();
      }

   }

   void updateTextPositions(int position, float positionOffset, boolean force) {
      if (position != this.mLastKnownCurrentPage) {
         this.updateText(position, this.mPager.getAdapter());
      } else if (!force && positionOffset == this.mLastKnownPositionOffset) {
         return;
      }

      this.mUpdatingPositions = true;
      int prevWidth = this.mPrevText.getMeasuredWidth();
      int currWidth = this.mCurrText.getMeasuredWidth();
      int nextWidth = this.mNextText.getMeasuredWidth();
      int halfCurrWidth = currWidth / 2;
      int stripWidth = this.getWidth();
      int stripHeight = this.getHeight();
      int paddingLeft = this.getPaddingLeft();
      int paddingRight = this.getPaddingRight();
      int paddingTop = this.getPaddingTop();
      int paddingBottom = this.getPaddingBottom();
      int textPaddedLeft = paddingLeft + halfCurrWidth;
      int textPaddedRight = paddingRight + halfCurrWidth;
      int contentWidth = stripWidth - textPaddedLeft - textPaddedRight;
      float currOffset = positionOffset + 0.5F;
      if (currOffset > 1.0F) {
         --currOffset;
      }

      int currCenter = stripWidth - textPaddedRight - (int)((float)contentWidth * currOffset);
      int currLeft = currCenter - currWidth / 2;
      int currRight = currLeft + currWidth;
      int prevBaseline = this.mPrevText.getBaseline();
      int currBaseline = this.mCurrText.getBaseline();
      int nextBaseline = this.mNextText.getBaseline();
      int maxBaseline = Math.max(Math.max(prevBaseline, currBaseline), nextBaseline);
      int prevTopOffset = maxBaseline - prevBaseline;
      int currTopOffset = maxBaseline - currBaseline;
      int nextTopOffset = maxBaseline - nextBaseline;
      int alignedPrevHeight = prevTopOffset + this.mPrevText.getMeasuredHeight();
      int alignedCurrHeight = currTopOffset + this.mCurrText.getMeasuredHeight();
      int alignedNextHeight = nextTopOffset + this.mNextText.getMeasuredHeight();
      int maxTextHeight = Math.max(Math.max(alignedPrevHeight, alignedCurrHeight), alignedNextHeight);
      int vgrav = this.mGravity & 112;
      int prevTop;
      int currTop;
      int nextTop;
      int prevLeft;
      int nextLeft;
      switch(vgrav) {
      case 16:
         prevLeft = stripHeight - paddingTop - paddingBottom;
         nextLeft = (prevLeft - maxTextHeight) / 2;
         prevTop = nextLeft + prevTopOffset;
         currTop = nextLeft + currTopOffset;
         nextTop = nextLeft + nextTopOffset;
         break;
      case 48:
      default:
         prevTop = paddingTop + prevTopOffset;
         currTop = paddingTop + currTopOffset;
         nextTop = paddingTop + nextTopOffset;
         break;
      case 80:
         int bottomGravTop = stripHeight - paddingBottom - maxTextHeight;
         prevTop = bottomGravTop + prevTopOffset;
         currTop = bottomGravTop + currTopOffset;
         nextTop = bottomGravTop + nextTopOffset;
      }

      this.mCurrText.layout(currLeft, currTop, currRight, currTop + this.mCurrText.getMeasuredHeight());
      prevLeft = Math.min(paddingLeft, currLeft - this.mScaledTextSpacing - prevWidth);
      this.mPrevText.layout(prevLeft, prevTop, prevLeft + prevWidth, prevTop + this.mPrevText.getMeasuredHeight());
      nextLeft = Math.max(stripWidth - paddingRight - nextWidth, currRight + this.mScaledTextSpacing);
      this.mNextText.layout(nextLeft, nextTop, nextLeft + nextWidth, nextTop + this.mNextText.getMeasuredHeight());
      this.mLastKnownPositionOffset = positionOffset;
      this.mUpdatingPositions = false;
   }

   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int widthMode = MeasureSpec.getMode(widthMeasureSpec);
      if (widthMode != 1073741824) {
         throw new IllegalStateException("Must measure with an exact width");
      } else {
         int heightPadding = this.getPaddingTop() + this.getPaddingBottom();
         int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, heightPadding, -2);
         int widthSize = MeasureSpec.getSize(widthMeasureSpec);
         int widthPadding = (int)((float)widthSize * 0.2F);
         int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, widthPadding, -2);
         this.mPrevText.measure(childWidthSpec, childHeightSpec);
         this.mCurrText.measure(childWidthSpec, childHeightSpec);
         this.mNextText.measure(childWidthSpec, childHeightSpec);
         int heightMode = MeasureSpec.getMode(heightMeasureSpec);
         int height;
         int childState;
         int measuredHeight;
         if (heightMode == 1073741824) {
            height = MeasureSpec.getSize(heightMeasureSpec);
         } else {
            childState = this.mCurrText.getMeasuredHeight();
            measuredHeight = this.getMinHeight();
            height = Math.max(measuredHeight, childState + heightPadding);
         }

         childState = this.mCurrText.getMeasuredState();
         measuredHeight = View.resolveSizeAndState(height, heightMeasureSpec, childState << 16);
         this.setMeasuredDimension(widthSize, measuredHeight);
      }
   }

   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      if (this.mPager != null) {
         float offset = this.mLastKnownPositionOffset >= 0.0F ? this.mLastKnownPositionOffset : 0.0F;
         this.updateTextPositions(this.mLastKnownCurrentPage, offset, true);
      }

   }

   int getMinHeight() {
      int minHeight = 0;
      Drawable bg = this.getBackground();
      if (bg != null) {
         minHeight = bg.getIntrinsicHeight();
      }

      return minHeight;
   }

   private class PageListener extends DataSetObserver implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
      private int mScrollState;

      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
         if (positionOffset > 0.5F) {
            ++position;
         }

         PagerTitleStrip.this.updateTextPositions(position, positionOffset, false);
      }

      public void onPageSelected(int position) {
         if (this.mScrollState == 0) {
            PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
            float offset = PagerTitleStrip.this.mLastKnownPositionOffset >= 0.0F ? PagerTitleStrip.this.mLastKnownPositionOffset : 0.0F;
            PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), offset, true);
         }

      }

      public void onPageScrollStateChanged(int state) {
         this.mScrollState = state;
      }

      public void onAdapterChanged(ViewPager viewPager, PagerAdapter oldAdapter, PagerAdapter newAdapter) {
         PagerTitleStrip.this.updateAdapter(oldAdapter, newAdapter);
      }

      public void onChanged() {
         PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
         float offset = PagerTitleStrip.this.mLastKnownPositionOffset >= 0.0F ? PagerTitleStrip.this.mLastKnownPositionOffset : 0.0F;
         PagerTitleStrip.this.updateTextPositions(PagerTitleStrip.this.mPager.getCurrentItem(), offset, true);
      }
   }

   private static class SingleLineAllCapsTransform extends SingleLineTransformationMethod {
      private Locale mLocale;

      SingleLineAllCapsTransform(Context context) {
         this.mLocale = context.getResources().getConfiguration().locale;
      }

      public CharSequence getTransformation(CharSequence source, View view) {
         source = super.getTransformation(source, view);
         return source != null ? source.toString().toUpperCase(this.mLocale) : null;
      }
   }
}
