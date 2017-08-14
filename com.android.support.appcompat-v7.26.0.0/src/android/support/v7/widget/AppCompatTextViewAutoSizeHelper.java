package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R.styleable;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

class AppCompatTextViewAutoSizeHelper {
   private static final String TAG = "ACTVAutoSizeHelper";
   private static final RectF TEMP_RECTF = new RectF();
   private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
   private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
   private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
   private static Hashtable sTextViewMethodByNameCache = new Hashtable();
   static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0F;
   private static final int VERY_WIDE = 1048576;
   private int mAutoSizeTextType = 0;
   private boolean mNeedsAutoSizeText = false;
   private float mAutoSizeStepGranularityInPx = -1.0F;
   private float mAutoSizeMinTextSizeInPx = -1.0F;
   private float mAutoSizeMaxTextSizeInPx = -1.0F;
   private int[] mAutoSizeTextSizesInPx = new int[0];
   private boolean mHasPresetAutoSizeValues = false;
   private TextPaint mTempTextPaint;
   private final TextView mTextView;
   private final Context mContext;

   AppCompatTextViewAutoSizeHelper(TextView textView) {
      this.mTextView = textView;
      this.mContext = this.mTextView.getContext();
   }

   void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
      float autoSizeMinTextSizeInPx = -1.0F;
      float autoSizeMaxTextSizeInPx = -1.0F;
      float autoSizeStepGranularityInPx = -1.0F;
      TypedArray a = this.mContext.obtainStyledAttributes(attrs, styleable.AppCompatTextView, defStyleAttr, 0);
      if (a.hasValue(styleable.AppCompatTextView_autoSizeTextType)) {
         this.mAutoSizeTextType = a.getInt(styleable.AppCompatTextView_autoSizeTextType, 0);
      }

      if (a.hasValue(styleable.AppCompatTextView_autoSizeStepGranularity)) {
         autoSizeStepGranularityInPx = a.getDimension(styleable.AppCompatTextView_autoSizeStepGranularity, -1.0F);
      }

      if (a.hasValue(styleable.AppCompatTextView_autoSizeMinTextSize)) {
         autoSizeMinTextSizeInPx = a.getDimension(styleable.AppCompatTextView_autoSizeMinTextSize, -1.0F);
      }

      if (a.hasValue(styleable.AppCompatTextView_autoSizeMaxTextSize)) {
         autoSizeMaxTextSizeInPx = a.getDimension(styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0F);
      }

      if (a.hasValue(styleable.AppCompatTextView_autoSizePresetSizes)) {
         int autoSizeStepSizeArrayResId = a.getResourceId(styleable.AppCompatTextView_autoSizePresetSizes, 0);
         if (autoSizeStepSizeArrayResId > 0) {
            TypedArray autoSizePreDefTextSizes = a.getResources().obtainTypedArray(autoSizeStepSizeArrayResId);
            this.setupAutoSizeUniformPresetSizes(autoSizePreDefTextSizes);
            autoSizePreDefTextSizes.recycle();
         }
      }

      a.recycle();
      if (this.supportsAutoSizeText()) {
         if (this.mAutoSizeTextType == 1) {
            if (!this.mHasPresetAutoSizeValues) {
               DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
               if (autoSizeMinTextSizeInPx == -1.0F) {
                  autoSizeMinTextSizeInPx = TypedValue.applyDimension(2, 12.0F, displayMetrics);
               }

               if (autoSizeMaxTextSizeInPx == -1.0F) {
                  autoSizeMaxTextSizeInPx = TypedValue.applyDimension(2, 112.0F, displayMetrics);
               }

               if (autoSizeStepGranularityInPx == -1.0F) {
                  autoSizeStepGranularityInPx = 1.0F;
               }

               this.validateAndSetAutoSizeTextTypeUniformConfiguration(autoSizeMinTextSizeInPx, autoSizeMaxTextSizeInPx, autoSizeStepGranularityInPx);
            }

            this.setupAutoSizeText();
         }
      } else {
         this.mAutoSizeTextType = 0;
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void setAutoSizeTextTypeWithDefaults(int autoSizeTextType) {
      if (this.supportsAutoSizeText()) {
         switch(autoSizeTextType) {
         case 0:
            this.clearAutoSizeConfiguration();
            break;
         case 1:
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            float autoSizeMinTextSizeInPx = TypedValue.applyDimension(2, 12.0F, displayMetrics);
            float autoSizeMaxTextSizeInPx = TypedValue.applyDimension(2, 112.0F, displayMetrics);
            this.validateAndSetAutoSizeTextTypeUniformConfiguration(autoSizeMinTextSizeInPx, autoSizeMaxTextSizeInPx, 1.0F);
            if (this.setupAutoSizeText()) {
               this.autoSizeText();
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown auto-size text type: " + autoSizeTextType);
         }
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
      if (this.supportsAutoSizeText()) {
         DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
         float autoSizeMinTextSizeInPx = TypedValue.applyDimension(unit, (float)autoSizeMinTextSize, displayMetrics);
         float autoSizeMaxTextSizeInPx = TypedValue.applyDimension(unit, (float)autoSizeMaxTextSize, displayMetrics);
         float autoSizeStepGranularityInPx = TypedValue.applyDimension(unit, (float)autoSizeStepGranularity, displayMetrics);
         this.validateAndSetAutoSizeTextTypeUniformConfiguration(autoSizeMinTextSizeInPx, autoSizeMaxTextSizeInPx, autoSizeStepGranularityInPx);
         if (this.setupAutoSizeText()) {
            this.autoSizeText();
         }
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
      if (this.supportsAutoSizeText()) {
         int presetSizesLength = presetSizes.length;
         if (presetSizesLength <= 0) {
            this.mHasPresetAutoSizeValues = false;
         } else {
            int[] presetSizesInPx = new int[presetSizesLength];
            if (unit == 0) {
               presetSizesInPx = Arrays.copyOf(presetSizes, presetSizesLength);
            } else {
               DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();

               for(int i = 0; i < presetSizesLength; ++i) {
                  presetSizesInPx[i] = Math.round(TypedValue.applyDimension(unit, (float)presetSizes[i], displayMetrics));
               }
            }

            this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(presetSizesInPx);
            if (!this.setupAutoSizeUniformPresetSizesConfiguration()) {
               throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(presetSizes));
            }
         }

         if (this.setupAutoSizeText()) {
            this.autoSizeText();
         }
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int getAutoSizeTextType() {
      return this.mAutoSizeTextType;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int getAutoSizeStepGranularity() {
      return Math.round(this.mAutoSizeStepGranularityInPx);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int getAutoSizeMinTextSize() {
      return Math.round(this.mAutoSizeMinTextSizeInPx);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int getAutoSizeMaxTextSize() {
      return Math.round(this.mAutoSizeMaxTextSizeInPx);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int[] getAutoSizeTextAvailableSizes() {
      return this.mAutoSizeTextSizesInPx;
   }

   private void setupAutoSizeUniformPresetSizes(TypedArray textSizes) {
      int textSizesLength = textSizes.length();
      int[] parsedSizes = new int[textSizesLength];
      if (textSizesLength > 0) {
         for(int i = 0; i < textSizesLength; ++i) {
            parsedSizes[i] = textSizes.getDimensionPixelSize(i, -1);
         }

         this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(parsedSizes);
         this.setupAutoSizeUniformPresetSizesConfiguration();
      }

   }

   private boolean setupAutoSizeUniformPresetSizesConfiguration() {
      int sizesLength = this.mAutoSizeTextSizesInPx.length;
      this.mHasPresetAutoSizeValues = sizesLength > 0;
      if (this.mHasPresetAutoSizeValues) {
         this.mAutoSizeTextType = 1;
         this.mAutoSizeMinTextSizeInPx = (float)this.mAutoSizeTextSizesInPx[0];
         this.mAutoSizeMaxTextSizeInPx = (float)this.mAutoSizeTextSizesInPx[sizesLength - 1];
         this.mAutoSizeStepGranularityInPx = -1.0F;
      }

      return this.mHasPresetAutoSizeValues;
   }

   private int[] cleanupAutoSizePresetSizes(int[] presetValues) {
      int presetValuesLength = presetValues.length;
      if (presetValuesLength == 0) {
         return presetValues;
      } else {
         Arrays.sort(presetValues);
         List uniqueValidSizes = new ArrayList();

         int uniqueValidSizesLength;
         for(uniqueValidSizesLength = 0; uniqueValidSizesLength < presetValuesLength; ++uniqueValidSizesLength) {
            int currentPresetValue = presetValues[uniqueValidSizesLength];
            if (currentPresetValue > 0 && Collections.binarySearch(uniqueValidSizes, currentPresetValue) < 0) {
               uniqueValidSizes.add(currentPresetValue);
            }
         }

         if (presetValuesLength == uniqueValidSizes.size()) {
            return presetValues;
         } else {
            uniqueValidSizesLength = uniqueValidSizes.size();
            int[] cleanedUpSizes = new int[uniqueValidSizesLength];

            for(int i = 0; i < uniqueValidSizesLength; ++i) {
               cleanedUpSizes[i] = ((Integer)uniqueValidSizes.get(i)).intValue();
            }

            return cleanedUpSizes;
         }
      }
   }

   private void validateAndSetAutoSizeTextTypeUniformConfiguration(float autoSizeMinTextSizeInPx, float autoSizeMaxTextSizeInPx, float autoSizeStepGranularityInPx) throws IllegalArgumentException {
      if (autoSizeMinTextSizeInPx <= 0.0F) {
         throw new IllegalArgumentException("Minimum auto-size text size (" + autoSizeMinTextSizeInPx + "px) is less or equal to (0px)");
      } else if (autoSizeMaxTextSizeInPx <= autoSizeMinTextSizeInPx) {
         throw new IllegalArgumentException("Maximum auto-size text size (" + autoSizeMaxTextSizeInPx + "px) is less or equal to minimum auto-size " + "text size (" + autoSizeMinTextSizeInPx + "px)");
      } else if (autoSizeStepGranularityInPx <= 0.0F) {
         throw new IllegalArgumentException("The auto-size step granularity (" + autoSizeStepGranularityInPx + "px) is less or equal to (0px)");
      } else {
         this.mAutoSizeTextType = 1;
         this.mAutoSizeMinTextSizeInPx = autoSizeMinTextSizeInPx;
         this.mAutoSizeMaxTextSizeInPx = autoSizeMaxTextSizeInPx;
         this.mAutoSizeStepGranularityInPx = autoSizeStepGranularityInPx;
         this.mHasPresetAutoSizeValues = false;
      }
   }

   private boolean setupAutoSizeText() {
      if (this.supportsAutoSizeText() && this.mAutoSizeTextType == 1) {
         if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
            int autoSizeValuesLength = 1;

            for(float currentSize = (float)Math.round(this.mAutoSizeMinTextSizeInPx); Math.round(currentSize + this.mAutoSizeStepGranularityInPx) <= Math.round(this.mAutoSizeMaxTextSizeInPx); currentSize += this.mAutoSizeStepGranularityInPx) {
               ++autoSizeValuesLength;
            }

            int[] autoSizeTextSizesInPx = new int[autoSizeValuesLength];
            float sizeToAdd = this.mAutoSizeMinTextSizeInPx;

            for(int i = 0; i < autoSizeValuesLength; ++i) {
               autoSizeTextSizesInPx[i] = Math.round(sizeToAdd);
               sizeToAdd += this.mAutoSizeStepGranularityInPx;
            }

            this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(autoSizeTextSizesInPx);
         }

         this.mNeedsAutoSizeText = true;
      } else {
         this.mNeedsAutoSizeText = false;
      }

      return this.mNeedsAutoSizeText;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void autoSizeText() {
      if (this.isAutoSizeEnabled()) {
         if (this.mNeedsAutoSizeText) {
            label50: {
               if (this.mTextView.getMeasuredHeight() > 0 && this.mTextView.getMeasuredWidth() > 0) {
                  boolean horizontallyScrolling = ((Boolean)this.invokeAndReturnWithDefault(this.mTextView, "getHorizontallyScrolling", false)).booleanValue();
                  int availableWidth = horizontallyScrolling ? 1048576 : this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft() - this.mTextView.getTotalPaddingRight();
                  int availableHeight = this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom() - this.mTextView.getCompoundPaddingTop();
                  if (availableWidth > 0 && availableHeight > 0) {
                     RectF var4 = TEMP_RECTF;
                     synchronized(TEMP_RECTF) {
                        TEMP_RECTF.setEmpty();
                        TEMP_RECTF.right = (float)availableWidth;
                        TEMP_RECTF.bottom = (float)availableHeight;
                        float optimalTextSize = (float)this.findLargestTextSizeWhichFits(TEMP_RECTF);
                        if (optimalTextSize != this.mTextView.getTextSize()) {
                           this.setTextSizeInternal(0, optimalTextSize);
                        }
                        break label50;
                     }
                  }

                  return;
               }

               return;
            }
         }

         this.mNeedsAutoSizeText = true;
      }
   }

   private void clearAutoSizeConfiguration() {
      this.mAutoSizeTextType = 0;
      this.mAutoSizeMinTextSizeInPx = -1.0F;
      this.mAutoSizeMaxTextSizeInPx = -1.0F;
      this.mAutoSizeStepGranularityInPx = -1.0F;
      this.mAutoSizeTextSizesInPx = new int[0];
      this.mNeedsAutoSizeText = false;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   void setTextSizeInternal(int unit, float size) {
      Resources res = this.mContext == null ? Resources.getSystem() : this.mContext.getResources();
      this.setRawTextSize(TypedValue.applyDimension(unit, size, res.getDisplayMetrics()));
   }

   private void setRawTextSize(float size) {
      if (size != this.mTextView.getPaint().getTextSize()) {
         this.mTextView.getPaint().setTextSize(size);
         boolean isInLayout = false;
         if (VERSION.SDK_INT >= 18) {
            isInLayout = this.mTextView.isInLayout();
         }

         if (this.mTextView.getLayout() != null) {
            this.mNeedsAutoSizeText = false;
            String var3 = "nullLayouts";

            try {
               Method method = this.getTextViewMethod("nullLayouts");
               if (method != null) {
                  method.invoke(this.mTextView);
               }
            } catch (Exception var5) {
               Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", var5);
            }

            if (!isInLayout) {
               this.mTextView.requestLayout();
            } else {
               this.mTextView.forceLayout();
            }

            this.mTextView.invalidate();
         }
      }

   }

   private int findLargestTextSizeWhichFits(RectF availableSpace) {
      int sizesCount = this.mAutoSizeTextSizesInPx.length;
      if (sizesCount == 0) {
         throw new IllegalStateException("No available text sizes to choose from.");
      } else {
         int bestSizeIndex = 0;
         int lowIndex = bestSizeIndex + 1;
         int highIndex = sizesCount - 1;

         while(lowIndex <= highIndex) {
            int sizeToTryIndex = (lowIndex + highIndex) / 2;
            if (this.suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[sizeToTryIndex], availableSpace)) {
               bestSizeIndex = lowIndex;
               lowIndex = sizeToTryIndex + 1;
            } else {
               highIndex = sizeToTryIndex - 1;
               bestSizeIndex = highIndex;
            }
         }

         return this.mAutoSizeTextSizesInPx[bestSizeIndex];
      }
   }

   private boolean suggestedSizeFitsInSpace(int suggestedSizeInPx, RectF availableSpace) {
      CharSequence text = this.mTextView.getText();
      int maxLines = VERSION.SDK_INT >= 16 ? this.mTextView.getMaxLines() : -1;
      if (this.mTempTextPaint == null) {
         this.mTempTextPaint = new TextPaint();
      } else {
         this.mTempTextPaint.reset();
      }

      this.mTempTextPaint.set(this.mTextView.getPaint());
      this.mTempTextPaint.setTextSize((float)suggestedSizeInPx);
      Alignment alignment = (Alignment)this.invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Alignment.ALIGN_NORMAL);
      StaticLayout layout = VERSION.SDK_INT >= 23 ? this.createStaticLayoutForMeasuring(text, alignment, Math.round(availableSpace.right), maxLines) : this.createStaticLayoutForMeasuringPre23(text, alignment, Math.round(availableSpace.right));
      if (maxLines != -1 && (layout.getLineCount() > maxLines || layout.getLineEnd(layout.getLineCount() - 1) != text.length())) {
         return false;
      } else {
         return (float)layout.getHeight() <= availableSpace.bottom;
      }
   }

   @TargetApi(23)
   private StaticLayout createStaticLayoutForMeasuring(CharSequence text, Alignment alignment, int availableWidth, int maxLines) {
      TextDirectionHeuristic textDirectionHeuristic = (TextDirectionHeuristic)this.invokeAndReturnWithDefault(this.mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
      Builder layoutBuilder = Builder.obtain(text, 0, text.length(), this.mTempTextPaint, availableWidth);
      return layoutBuilder.setAlignment(alignment).setLineSpacing(this.mTextView.getLineSpacingExtra(), this.mTextView.getLineSpacingMultiplier()).setIncludePad(this.mTextView.getIncludeFontPadding()).setBreakStrategy(this.mTextView.getBreakStrategy()).setHyphenationFrequency(this.mTextView.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Integer.MAX_VALUE : maxLines).setTextDirection(textDirectionHeuristic).build();
   }

   @TargetApi(14)
   private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence text, Alignment alignment, int availableWidth) {
      float lineSpacingMultiplier = 1.0F;
      float lineSpacingAdd = 0.0F;
      boolean includePad = true;
      if (VERSION.SDK_INT >= 16) {
         lineSpacingMultiplier = this.mTextView.getLineSpacingMultiplier();
         lineSpacingAdd = this.mTextView.getLineSpacingExtra();
         includePad = this.mTextView.getIncludeFontPadding();
      } else {
         lineSpacingMultiplier = ((Float)this.invokeAndReturnWithDefault(this.mTextView, "getLineSpacingMultiplier", lineSpacingMultiplier)).floatValue();
         lineSpacingAdd = ((Float)this.invokeAndReturnWithDefault(this.mTextView, "getLineSpacingExtra", lineSpacingAdd)).floatValue();
         includePad = ((Boolean)this.invokeAndReturnWithDefault(this.mTextView, "getIncludeFontPadding", includePad)).booleanValue();
      }

      return new StaticLayout(text, this.mTempTextPaint, availableWidth, alignment, lineSpacingMultiplier, lineSpacingAdd, includePad);
   }

   private Object invokeAndReturnWithDefault(@NonNull Object object, @NonNull String methodName, @NonNull Object defaultValue) {
      Object result = null;
      boolean exceptionThrown = false;

      try {
         Method method = this.getTextViewMethod(methodName);
         result = method.invoke(object);
      } catch (Exception var10) {
         exceptionThrown = true;
         Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + methodName + "() method", var10);
      } finally {
         if (result == null && exceptionThrown) {
            result = defaultValue;
         }

      }

      return result;
   }

   @Nullable
   private Method getTextViewMethod(@NonNull String methodName) {
      try {
         Method method = (Method)sTextViewMethodByNameCache.get(methodName);
         if (method == null) {
            method = TextView.class.getDeclaredMethod(methodName);
            if (method != null) {
               method.setAccessible(true);
               sTextViewMethodByNameCache.put(methodName, method);
            }
         }

         return method;
      } catch (Exception var3) {
         Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + methodName + "() method", var3);
         return null;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   boolean isAutoSizeEnabled() {
      return this.supportsAutoSizeText() && this.mAutoSizeTextType != 0;
   }

   private boolean supportsAutoSizeText() {
      return !(this.mTextView instanceof AppCompatEditText);
   }
}
