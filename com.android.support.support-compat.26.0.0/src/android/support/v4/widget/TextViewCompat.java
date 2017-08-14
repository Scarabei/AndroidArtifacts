package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

public final class TextViewCompat {
   public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
   public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
   static final TextViewCompat.TextViewCompatBaseImpl IMPL;

   public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
      IMPL.setCompoundDrawablesRelative(textView, start, top, end, bottom);
   }

   public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
      IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, start, top, end, bottom);
   }

   public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
      IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, start, top, end, bottom);
   }

   public static int getMaxLines(@NonNull TextView textView) {
      return IMPL.getMaxLines(textView);
   }

   public static int getMinLines(@NonNull TextView textView) {
      return IMPL.getMinLines(textView);
   }

   public static void setTextAppearance(@NonNull TextView textView, @StyleRes int resId) {
      IMPL.setTextAppearance(textView, resId);
   }

   public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
      return IMPL.getCompoundDrawablesRelative(textView);
   }

   public static void setAutoSizeTextTypeWithDefaults(TextView textView, int autoSizeTextType) {
      IMPL.setAutoSizeTextTypeWithDefaults(textView, autoSizeTextType);
   }

   public static void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
      IMPL.setAutoSizeTextTypeUniformWithConfiguration(textView, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
   }

   public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, @NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
      IMPL.setAutoSizeTextTypeUniformWithPresetSizes(textView, presetSizes, unit);
   }

   public static int getAutoSizeTextType(TextView textView) {
      return IMPL.getAutoSizeTextType(textView);
   }

   public static int getAutoSizeStepGranularity(TextView textView) {
      return IMPL.getAutoSizeStepGranularity(textView);
   }

   public static int getAutoSizeMinTextSize(TextView textView) {
      return IMPL.getAutoSizeMinTextSize(textView);
   }

   public static int getAutoSizeMaxTextSize(TextView textView) {
      return IMPL.getAutoSizeMaxTextSize(textView);
   }

   public static int[] getAutoSizeTextAvailableSizes(TextView textView) {
      return IMPL.getAutoSizeTextAvailableSizes(textView);
   }

   static {
      if (VERSION.SDK_INT >= 26) {
         IMPL = new TextViewCompat.TextViewCompatApi26Impl();
      } else if (VERSION.SDK_INT >= 23) {
         IMPL = new TextViewCompat.TextViewCompatApi23Impl();
      } else if (VERSION.SDK_INT >= 18) {
         IMPL = new TextViewCompat.TextViewCompatApi18Impl();
      } else if (VERSION.SDK_INT >= 17) {
         IMPL = new TextViewCompat.TextViewCompatApi17Impl();
      } else if (VERSION.SDK_INT >= 16) {
         IMPL = new TextViewCompat.TextViewCompatApi16Impl();
      } else {
         IMPL = new TextViewCompat.TextViewCompatBaseImpl();
      }

   }

   @RequiresApi(26)
   static class TextViewCompatApi26Impl extends TextViewCompat.TextViewCompatApi23Impl {
      public void setAutoSizeTextTypeWithDefaults(TextView textView, int autoSizeTextType) {
         textView.setAutoSizeTextTypeWithDefaults(autoSizeTextType);
      }

      public void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
         textView.setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
      }

      public void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, @NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
         textView.setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
      }

      public int getAutoSizeTextType(TextView textView) {
         return textView.getAutoSizeTextType();
      }

      public int getAutoSizeStepGranularity(TextView textView) {
         return textView.getAutoSizeStepGranularity();
      }

      public int getAutoSizeMinTextSize(TextView textView) {
         return textView.getAutoSizeMinTextSize();
      }

      public int getAutoSizeMaxTextSize(TextView textView) {
         return textView.getAutoSizeMaxTextSize();
      }

      public int[] getAutoSizeTextAvailableSizes(TextView textView) {
         return textView.getAutoSizeTextAvailableSizes();
      }
   }

   @RequiresApi(23)
   static class TextViewCompatApi23Impl extends TextViewCompat.TextViewCompatApi18Impl {
      public void setTextAppearance(@NonNull TextView textView, @StyleRes int resId) {
         textView.setTextAppearance(resId);
      }
   }

   @RequiresApi(18)
   static class TextViewCompatApi18Impl extends TextViewCompat.TextViewCompatApi17Impl {
      public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
         textView.setCompoundDrawablesRelative(start, top, end, bottom);
      }

      public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
         textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
      }

      public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
         textView.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
      }

      public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
         return textView.getCompoundDrawablesRelative();
      }
   }

   @RequiresApi(17)
   static class TextViewCompatApi17Impl extends TextViewCompat.TextViewCompatApi16Impl {
      public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
         boolean rtl = textView.getLayoutDirection() == 1;
         textView.setCompoundDrawables(rtl ? end : start, top, rtl ? start : end, bottom);
      }

      public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
         boolean rtl = textView.getLayoutDirection() == 1;
         textView.setCompoundDrawablesWithIntrinsicBounds(rtl ? end : start, top, rtl ? start : end, bottom);
      }

      public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
         boolean rtl = textView.getLayoutDirection() == 1;
         textView.setCompoundDrawablesWithIntrinsicBounds(rtl ? end : start, top, rtl ? start : end, bottom);
      }

      public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
         boolean rtl = textView.getLayoutDirection() == 1;
         Drawable[] compounds = textView.getCompoundDrawables();
         if (rtl) {
            Drawable start = compounds[2];
            Drawable end = compounds[0];
            compounds[0] = start;
            compounds[2] = end;
         }

         return compounds;
      }
   }

   @RequiresApi(16)
   static class TextViewCompatApi16Impl extends TextViewCompat.TextViewCompatBaseImpl {
      public int getMaxLines(TextView textView) {
         return textView.getMaxLines();
      }

      public int getMinLines(TextView textView) {
         return textView.getMinLines();
      }
   }

   static class TextViewCompatBaseImpl {
      private static final String LOG_TAG = "TextViewCompatBase";
      private static final int LINES = 1;
      private static Field sMaximumField;
      private static boolean sMaximumFieldFetched;
      private static Field sMaxModeField;
      private static boolean sMaxModeFieldFetched;
      private static Field sMinimumField;
      private static boolean sMinimumFieldFetched;
      private static Field sMinModeField;
      private static boolean sMinModeFieldFetched;

      public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
         textView.setCompoundDrawables(start, top, end, bottom);
      }

      public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end, @Nullable Drawable bottom) {
         textView.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
      }

      public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int start, @DrawableRes int top, @DrawableRes int end, @DrawableRes int bottom) {
         textView.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
      }

      private static Field retrieveField(String fieldName) {
         Field field = null;

         try {
            field = TextView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
         } catch (NoSuchFieldException var3) {
            Log.e("TextViewCompatBase", "Could not retrieve " + fieldName + " field.");
         }

         return field;
      }

      private static int retrieveIntFromField(Field field, TextView textView) {
         try {
            return field.getInt(textView);
         } catch (IllegalAccessException var3) {
            Log.d("TextViewCompatBase", "Could not retrieve value of " + field.getName() + " field.");
            return -1;
         }
      }

      public int getMaxLines(TextView textView) {
         if (!sMaxModeFieldFetched) {
            sMaxModeField = retrieveField("mMaxMode");
            sMaxModeFieldFetched = true;
         }

         if (sMaxModeField != null && retrieveIntFromField(sMaxModeField, textView) == 1) {
            if (!sMaximumFieldFetched) {
               sMaximumField = retrieveField("mMaximum");
               sMaximumFieldFetched = true;
            }

            if (sMaximumField != null) {
               return retrieveIntFromField(sMaximumField, textView);
            }
         }

         return -1;
      }

      public int getMinLines(TextView textView) {
         if (!sMinModeFieldFetched) {
            sMinModeField = retrieveField("mMinMode");
            sMinModeFieldFetched = true;
         }

         if (sMinModeField != null && retrieveIntFromField(sMinModeField, textView) == 1) {
            if (!sMinimumFieldFetched) {
               sMinimumField = retrieveField("mMinimum");
               sMinimumFieldFetched = true;
            }

            if (sMinimumField != null) {
               return retrieveIntFromField(sMinimumField, textView);
            }
         }

         return -1;
      }

      public void setTextAppearance(TextView textView, @StyleRes int resId) {
         textView.setTextAppearance(textView.getContext(), resId);
      }

      public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
         return textView.getCompoundDrawables();
      }

      public void setAutoSizeTextTypeWithDefaults(TextView textView, int autoSizeTextType) {
         if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView)textView).setAutoSizeTextTypeWithDefaults(autoSizeTextType);
         }

      }

      public void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
         if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView)textView).setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
         }

      }

      public void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, @NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
         if (textView instanceof AutoSizeableTextView) {
            ((AutoSizeableTextView)textView).setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
         }

      }

      public int getAutoSizeTextType(TextView textView) {
         return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView)textView).getAutoSizeTextType() : 0;
      }

      public int getAutoSizeStepGranularity(TextView textView) {
         return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView)textView).getAutoSizeStepGranularity() : -1;
      }

      public int getAutoSizeMinTextSize(TextView textView) {
         return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView)textView).getAutoSizeMinTextSize() : -1;
      }

      public int getAutoSizeMaxTextSize(TextView textView) {
         return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView)textView).getAutoSizeMaxTextSize() : -1;
      }

      public int[] getAutoSizeTextAvailableSizes(TextView textView) {
         return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView)textView).getAutoSizeTextAvailableSizes() : new int[0];
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface AutoSizeTextType {
   }
}
