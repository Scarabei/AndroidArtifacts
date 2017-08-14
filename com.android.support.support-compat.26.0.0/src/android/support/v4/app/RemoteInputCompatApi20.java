package android.support.v4.app;

import android.app.RemoteInput.Builder;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

@RequiresApi(20)
class RemoteInputCompatApi20 {
   private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";

   static RemoteInputCompatBase.RemoteInput[] toCompat(android.app.RemoteInput[] srcArray, RemoteInputCompatBase.RemoteInput.Factory factory) {
      if (srcArray == null) {
         return null;
      } else {
         RemoteInputCompatBase.RemoteInput[] result = factory.newArray(srcArray.length);

         for(int i = 0; i < srcArray.length; ++i) {
            android.app.RemoteInput src = srcArray[i];
            result[i] = factory.build(src.getResultKey(), src.getLabel(), src.getChoices(), src.getAllowFreeFormInput(), src.getExtras(), (Set)null);
         }

         return result;
      }
   }

   static android.app.RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput[] srcArray) {
      if (srcArray == null) {
         return null;
      } else {
         android.app.RemoteInput[] result = new android.app.RemoteInput[srcArray.length];

         for(int i = 0; i < srcArray.length; ++i) {
            RemoteInputCompatBase.RemoteInput src = srcArray[i];
            result[i] = (new Builder(src.getResultKey())).setLabel(src.getLabel()).setChoices(src.getChoices()).setAllowFreeFormInput(src.getAllowFreeFormInput()).addExtras(src.getExtras()).build();
         }

         return result;
      }
   }

   static Bundle getResultsFromIntent(Intent intent) {
      return android.app.RemoteInput.getResultsFromIntent(intent);
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
      Bundle existingTextResults = getResultsFromIntent(intent);
      if (existingTextResults == null) {
         existingTextResults = results;
      } else {
         existingTextResults.putAll(results);
      }

      RemoteInputCompatBase.RemoteInput[] var4 = remoteInputs;
      int var5 = remoteInputs.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         RemoteInputCompatBase.RemoteInput input = var4[var6];
         Map existingDataResults = getDataResultsFromIntent(intent, input.getResultKey());
         RemoteInputCompatBase.RemoteInput[] arr = new RemoteInputCompatBase.RemoteInput[]{input};
         android.app.RemoteInput.addResultsToIntent(fromCompat(arr), intent, existingTextResults);
         if (existingDataResults != null) {
            addDataResultToIntent(input, intent, existingDataResults);
         }
      }

   }

   public static void addDataResultToIntent(RemoteInputCompatBase.RemoteInput remoteInput, Intent intent, Map results) {
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
