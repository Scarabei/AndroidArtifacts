package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class RemoteInput extends RemoteInputCompatBase.RemoteInput {
   private static final String TAG = "RemoteInput";
   public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
   public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
   private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
   private final String mResultKey;
   private final CharSequence mLabel;
   private final CharSequence[] mChoices;
   private final boolean mAllowFreeFormTextInput;
   private final Bundle mExtras;
   private final Set mAllowedDataTypes;
   private static final RemoteInput.Impl IMPL;
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final RemoteInputCompatBase.RemoteInput.Factory FACTORY;

   RemoteInput(String resultKey, CharSequence label, CharSequence[] choices, boolean allowFreeFormTextInput, Bundle extras, Set allowedDataTypes) {
      this.mResultKey = resultKey;
      this.mLabel = label;
      this.mChoices = choices;
      this.mAllowFreeFormTextInput = allowFreeFormTextInput;
      this.mExtras = extras;
      this.mAllowedDataTypes = allowedDataTypes;
   }

   public String getResultKey() {
      return this.mResultKey;
   }

   public CharSequence getLabel() {
      return this.mLabel;
   }

   public CharSequence[] getChoices() {
      return this.mChoices;
   }

   public Set getAllowedDataTypes() {
      return this.mAllowedDataTypes;
   }

   public boolean isDataOnly() {
      return !this.getAllowFreeFormInput() && (this.getChoices() == null || this.getChoices().length == 0) && this.getAllowedDataTypes() != null && !this.getAllowedDataTypes().isEmpty();
   }

   public boolean getAllowFreeFormInput() {
      return this.mAllowFreeFormTextInput;
   }

   public Bundle getExtras() {
      return this.mExtras;
   }

   public static Map getDataResultsFromIntent(Intent intent, String remoteInputResultKey) {
      return IMPL.getDataResultsFromIntent(intent, remoteInputResultKey);
   }

   public static Bundle getResultsFromIntent(Intent intent) {
      return IMPL.getResultsFromIntent(intent);
   }

   public static void addResultsToIntent(RemoteInput[] remoteInputs, Intent intent, Bundle results) {
      IMPL.addResultsToIntent(remoteInputs, intent, results);
   }

   public static void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map results) {
      IMPL.addDataResultToIntent(remoteInput, intent, results);
   }

   static {
      if (VERSION.SDK_INT >= 20) {
         IMPL = new RemoteInput.ImplApi20();
      } else if (VERSION.SDK_INT >= 16) {
         IMPL = new RemoteInput.ImplJellybean();
      } else {
         IMPL = new RemoteInput.ImplBase();
      }

      FACTORY = new RemoteInputCompatBase.RemoteInput.Factory() {
         public RemoteInput build(String resultKey, CharSequence label, CharSequence[] choices, boolean allowFreeFormInput, Bundle extras, Set allowedDataTypes) {
            return new RemoteInput(resultKey, label, choices, allowFreeFormInput, extras, allowedDataTypes);
         }

         public RemoteInput[] newArray(int size) {
            return new RemoteInput[size];
         }
      };
   }

   @RequiresApi(20)
   static class ImplApi20 implements RemoteInput.Impl {
      public Bundle getResultsFromIntent(Intent intent) {
         return RemoteInputCompatApi20.getResultsFromIntent(intent);
      }

      public Map getDataResultsFromIntent(Intent intent, String remoteInputResultKey) {
         return RemoteInputCompatApi20.getDataResultsFromIntent(intent, remoteInputResultKey);
      }

      public void addResultsToIntent(RemoteInput[] remoteInputs, Intent intent, Bundle results) {
         RemoteInputCompatApi20.addResultsToIntent(remoteInputs, intent, results);
      }

      public void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map results) {
         RemoteInputCompatApi20.addDataResultToIntent(remoteInput, intent, results);
      }
   }

   @RequiresApi(16)
   static class ImplJellybean implements RemoteInput.Impl {
      public Bundle getResultsFromIntent(Intent intent) {
         return RemoteInputCompatJellybean.getResultsFromIntent(intent);
      }

      public Map getDataResultsFromIntent(Intent intent, String remoteInputResultKey) {
         return RemoteInputCompatJellybean.getDataResultsFromIntent(intent, remoteInputResultKey);
      }

      public void addResultsToIntent(RemoteInput[] remoteInputs, Intent intent, Bundle results) {
         RemoteInputCompatJellybean.addResultsToIntent(remoteInputs, intent, results);
      }

      public void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map results) {
         RemoteInputCompatJellybean.addDataResultToIntent(remoteInput, intent, results);
      }
   }

   static class ImplBase implements RemoteInput.Impl {
      public Bundle getResultsFromIntent(Intent intent) {
         Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
         return null;
      }

      public Map getDataResultsFromIntent(Intent intent, String remoteInputResultKey) {
         Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
         return null;
      }

      public void addResultsToIntent(RemoteInput[] remoteInputs, Intent intent, Bundle results) {
         Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
      }

      public void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map results) {
         Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
      }
   }

   interface Impl {
      Bundle getResultsFromIntent(Intent var1);

      Map getDataResultsFromIntent(Intent var1, String var2);

      void addResultsToIntent(RemoteInput[] var1, Intent var2, Bundle var3);

      void addDataResultToIntent(RemoteInput var1, Intent var2, Map var3);
   }

   public static final class Builder {
      private final String mResultKey;
      private CharSequence mLabel;
      private CharSequence[] mChoices;
      private boolean mAllowFreeFormTextInput = true;
      private Bundle mExtras = new Bundle();
      private final Set mAllowedDataTypes = new HashSet();

      public Builder(String resultKey) {
         if (resultKey == null) {
            throw new IllegalArgumentException("Result key can't be null");
         } else {
            this.mResultKey = resultKey;
         }
      }

      public RemoteInput.Builder setLabel(CharSequence label) {
         this.mLabel = label;
         return this;
      }

      public RemoteInput.Builder setChoices(CharSequence[] choices) {
         this.mChoices = choices;
         return this;
      }

      public RemoteInput.Builder setAllowDataType(String mimeType, boolean doAllow) {
         if (doAllow) {
            this.mAllowedDataTypes.add(mimeType);
         } else {
            this.mAllowedDataTypes.remove(mimeType);
         }

         return this;
      }

      public RemoteInput.Builder setAllowFreeFormInput(boolean allowFreeFormTextInput) {
         this.mAllowFreeFormTextInput = allowFreeFormTextInput;
         return this;
      }

      public RemoteInput.Builder addExtras(Bundle extras) {
         if (extras != null) {
            this.mExtras.putAll(extras);
         }

         return this;
      }

      public Bundle getExtras() {
         return this.mExtras;
      }

      public RemoteInput build() {
         return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
      }
   }
}
