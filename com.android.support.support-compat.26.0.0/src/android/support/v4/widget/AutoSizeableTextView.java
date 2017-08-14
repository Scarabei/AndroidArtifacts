package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface AutoSizeableTextView {
   void setAutoSizeTextTypeWithDefaults(int var1);

   void setAutoSizeTextTypeUniformWithConfiguration(int var1, int var2, int var3, int var4) throws IllegalArgumentException;

   void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] var1, int var2) throws IllegalArgumentException;

   int getAutoSizeTextType();

   int getAutoSizeStepGranularity();

   int getAutoSizeMinTextSize();

   int getAutoSizeMaxTextSize();

   int[] getAutoSizeTextAvailableSizes();
}
