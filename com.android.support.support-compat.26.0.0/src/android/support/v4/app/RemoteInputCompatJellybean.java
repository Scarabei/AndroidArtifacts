package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

@RequiresApi(16)
class RemoteInputCompatJellybean {
   private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
   private static final String KEY_RESULT_KEY = "resultKey";
   private static final String KEY_LABEL = "label";
   private static final String KEY_CHOICES = "choices";
   private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
   private static final String KEY_EXTRAS = "extras";
   private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";

   static RemoteInputCompatBase.RemoteInput fromBundle(Bundle data, RemoteInputCompatBase.RemoteInput.Factory factory) {
      ArrayList allowedDataTypesAsList = data.getStringArrayList("allowedDataTypes");
      Set allowedDataTypes = new HashSet();
      if (allowedDataTypesAsList != null) {
         Iterator var4 = allowedDataTypesAsList.iterator();

         while(var4.hasNext()) {
            String type = (String)var4.next();
            allowedDataTypes.add(type);
         }
      }

      return factory.build(data.getString("resultKey"), data.getCharSequence("label"), data.getCharSequenceArray("choices"), data.getBoolean("allowFreeFormInput"), data.getBundle("extras"), allowedDataTypes);
   }

   static Bundle toBundle(RemoteInputCompatBase.RemoteInput remoteInput) {
      Bundle data = new Bundle();
      data.putString("resultKey", remoteInput.getResultKey());
      data.putCharSequence("label", remoteInput.getLabel());
      data.putCharSequenceArray("choices", remoteInput.getChoices());
      data.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
      data.putBundle("extras", remoteInput.getExtras());
      Set allowedDataTypes = remoteInput.getAllowedDataTypes();
      if (allowedDataTypes != null && !allowedDataTypes.isEmpty()) {
         ArrayList allowedDataTypesAsList = new ArrayList(allowedDataTypes.size());
         Iterator var4 = allowedDataTypes.iterator();

         while(var4.hasNext()) {
            String type = (String)var4.next();
            allowedDataTypesAsList.add(type);
         }

         data.putStringArrayList("allowedDataTypes", allowedDataTypesAsList);
      }

      return data;
   }

   static RemoteInputCompatBase.RemoteInput[] fromBundleArray(Bundle[] bundles, RemoteInputCompatBase.RemoteInput.Factory factory) {
      if (bundles == null) {
         return null;
      } else {
         RemoteInputCompatBase.RemoteInput[] remoteInputs = factory.newArray(bundles.length);

         for(int i = 0; i < bundles.length; ++i) {
            remoteInputs[i] = fromBundle(bundles[i], factory);
         }

         return remoteInputs;
      }
   }

   static Bundle[] toBundleArray(RemoteInputCompatBase.RemoteInput[] remoteInputs) {
      if (remoteInputs == null) {
         return null;
      } else {
         Bundle[] bundles = new Bundle[remoteInputs.length];

         for(int i = 0; i < remoteInputs.length; ++i) {
            bundles[i] = toBundle(remoteInputs[i]);
         }

         return bundles;
      }
   }

   static Bundle getResultsFromIntent(Intent intent) {
      Intent clipDataIntent = getClipDataIntentFromIntent(intent);
      return clipDataIntent == null ? null : (Bundle)clipDataIntent.getExtras().getParcelable("android.remoteinput.resultsData");
   }

   static Map getDataResultsFromIntent(Intent intent, String remoteInputResultKey) {
      Intent clipDataIntent = getClipDataIntentFromIntent(intent);
      if (clipDataIntent == null) {
         return null;
      } else {
         Map results = new HashMap();
         Bundle extras = clipDataIntent.getExtras();
         Iterator var5 = extras.keySet().iterator();

         while(var5.hasNext()) {
            String key = (String)var5.next();
            if (key.startsWith("android.remoteinput.dataTypeResultsData")) {
               String mimeType = key.substring("android.remoteinput.dataTypeResultsData".length());
               if (mimeType != null && !mimeType.isEmpty()) {
                  Bundle bundle = clipDataIntent.getBundleExtra(key);
                  String uriStr = bundle.getString(remoteInputResultKey);
                  if (uriStr != null && !uriStr.isEmpty()) {
                     results.put(mimeType, Uri.parse(uriStr));
                  }
               }
            }
         }

         return results.isEmpty() ? null : results;
      }
   }

   static void addResultsToIntent(RemoteInputCompatBase.RemoteInput[] remoteInputs, Intent intent, Bundle results) {
      Intent clipDataIntent = getClipDataIntentFromIntent(intent);
      if (clipDataIntent == null) {
         clipDataIntent = new Intent();
      }

      Bundle resultsBundle = clipDataIntent.getBundleExtra("android.remoteinput.resultsData");
      if (resultsBundle == null) {
         resultsBundle = new Bundle();
      }

      RemoteInputCompatBase.RemoteInput[] var5 = remoteInputs;
      int var6 = remoteInputs.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         RemoteInputCompatBase.RemoteInput remoteInput = var5[var7];
         Object result = results.get(remoteInput.getResultKey());
         if (result instanceof CharSequence) {
            resultsBundle.putCharSequence(remoteInput.getResultKey(), (CharSequence)result);
         }
      }

      clipDataIntent.putExtra("android.remoteinput.resultsData", resultsBundle);
      intent.setClipData(ClipData.newIntent("android.remoteinput.results", clipDataIntent));
   }

   public static void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map results) {
      Intent clipDataIntent = getClipDataIntentFromIntent(intent);
      if (clipDataIntent == null) {
         clipDataIntent = new Intent();
      }

      Iterator var4 = results.entrySet().iterator();

      while(var4.hasNext()) {
         Entry entry = (Entry)var4.next();
         String mimeType = (String)entry.getKey();
         Uri uri = (Uri)entry.getValue();
         if (mimeType != null) {
            Bundle resultsBundle = clipDataIntent.getBundleExtra(getExtraResultsKeyForData(mimeType));
            if (resultsBundle == null) {
               resultsBundle = new Bundle();
            }

            resultsBundle.putString(remoteInput.getResultKey(), uri.toString());
            clipDataIntent.putExtra(getExtraResultsKeyForData(mimeType), resultsBundle);
         }
      }

      intent.setClipData(ClipData.newIntent("android.remoteinput.results", clipDataIntent));
   }

   private static String getExtraResultsKeyForData(String mimeType) {
      return "android.remoteinput.dataTypeResultsData" + mimeType;
   }

   private static Intent getClipDataIntentFromIntent(Intent intent) {
      ClipData clipData = intent.getClipData();
      if (clipData == null) {
         return null;
      } else {
         ClipDescription clipDescription = clipData.getDescription();
         if (!clipDescription.hasMimeType("text/vnd.android.intent")) {
            return null;
         } else {
            return !clipDescription.getLabel().equals("android.remoteinput.results") ? null : clipData.getItemAt(0).getIntent();
         }
      }
   }
}
