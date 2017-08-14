package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class ActivityChooserModel extends DataSetObservable {
   static final boolean DEBUG = false;
   static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
   static final String TAG_HISTORICAL_RECORDS = "historical-records";
   static final String TAG_HISTORICAL_RECORD = "historical-record";
   static final String ATTRIBUTE_ACTIVITY = "activity";
   static final String ATTRIBUTE_TIME = "time";
   static final String ATTRIBUTE_WEIGHT = "weight";
   public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
   public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
   private static final int DEFAULT_ACTIVITY_INFLATION = 5;
   private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
   private static final String HISTORY_FILE_EXTENSION = ".xml";
   private static final int INVALID_INDEX = -1;
   private static final Object sRegistryLock = new Object();
   private static final Map sDataModelRegistry = new HashMap();
   private final Object mInstanceLock = new Object();
   private final List mActivities = new ArrayList();
   private final List mHistoricalRecords = new ArrayList();
   final Context mContext;
   final String mHistoryFileName;
   private Intent mIntent;
   private ActivityChooserModel.ActivitySorter mActivitySorter = new ActivityChooserModel.DefaultSorter();
   private int mHistoryMaxSize = 50;
   boolean mCanReadHistoricalData = true;
   private boolean mReadShareHistoryCalled = false;
   private boolean mHistoricalRecordsChanged = true;
   private boolean mReloadActivities = false;
   private ActivityChooserModel.OnChooseActivityListener mActivityChoserModelPolicy;

   public static ActivityChooserModel get(Context context, String historyFileName) {
      Object var2 = sRegistryLock;
      synchronized(sRegistryLock) {
         ActivityChooserModel dataModel = (ActivityChooserModel)sDataModelRegistry.get(historyFileName);
         if (dataModel == null) {
            dataModel = new ActivityChooserModel(context, historyFileName);
            sDataModelRegistry.put(historyFileName, dataModel);
         }

         return dataModel;
      }
   }

   private ActivityChooserModel(Context context, String historyFileName) {
      this.mContext = context.getApplicationContext();
      if (!TextUtils.isEmpty(historyFileName) && !historyFileName.endsWith(".xml")) {
         this.mHistoryFileName = historyFileName + ".xml";
      } else {
         this.mHistoryFileName = historyFileName;
      }

   }

   public void setIntent(Intent intent) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         if (this.mIntent != intent) {
            this.mIntent = intent;
            this.mReloadActivities = true;
            this.ensureConsistentState();
         }
      }
   }

   public Intent getIntent() {
      Object var1 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         return this.mIntent;
      }
   }

   public int getActivityCount() {
      Object var1 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         this.ensureConsistentState();
         return this.mActivities.size();
      }
   }

   public ResolveInfo getActivity(int index) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         this.ensureConsistentState();
         return ((ActivityChooserModel.ActivityResolveInfo)this.mActivities.get(index)).resolveInfo;
      }
   }

   public int getActivityIndex(ResolveInfo activity) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         this.ensureConsistentState();
         List activities = this.mActivities;
         int activityCount = activities.size();

         for(int i = 0; i < activityCount; ++i) {
            ActivityChooserModel.ActivityResolveInfo currentActivity = (ActivityChooserModel.ActivityResolveInfo)activities.get(i);
            if (currentActivity.resolveInfo == activity) {
               return i;
            }
         }

         return -1;
      }
   }

   public Intent chooseActivity(int index) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         if (this.mIntent == null) {
            return null;
         } else {
            this.ensureConsistentState();
            ActivityChooserModel.ActivityResolveInfo chosenActivity = (ActivityChooserModel.ActivityResolveInfo)this.mActivities.get(index);
            ComponentName chosenName = new ComponentName(chosenActivity.resolveInfo.activityInfo.packageName, chosenActivity.resolveInfo.activityInfo.name);
            Intent choiceIntent = new Intent(this.mIntent);
            choiceIntent.setComponent(chosenName);
            if (this.mActivityChoserModelPolicy != null) {
               Intent choiceIntentCopy = new Intent(choiceIntent);
               boolean handled = this.mActivityChoserModelPolicy.onChooseActivity(this, choiceIntentCopy);
               if (handled) {
                  return null;
               }
            }

            ActivityChooserModel.HistoricalRecord historicalRecord = new ActivityChooserModel.HistoricalRecord(chosenName, System.currentTimeMillis(), 1.0F);
            this.addHistoricalRecord(historicalRecord);
            return choiceIntent;
         }
      }
   }

   public void setOnChooseActivityListener(ActivityChooserModel.OnChooseActivityListener listener) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         this.mActivityChoserModelPolicy = listener;
      }
   }

   public ResolveInfo getDefaultActivity() {
      Object var1 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         this.ensureConsistentState();
         return !this.mActivities.isEmpty() ? ((ActivityChooserModel.ActivityResolveInfo)this.mActivities.get(0)).resolveInfo : null;
      }
   }

   public void setDefaultActivity(int index) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         this.ensureConsistentState();
         ActivityChooserModel.ActivityResolveInfo newDefaultActivity = (ActivityChooserModel.ActivityResolveInfo)this.mActivities.get(index);
         ActivityChooserModel.ActivityResolveInfo oldDefaultActivity = (ActivityChooserModel.ActivityResolveInfo)this.mActivities.get(0);
         float weight;
         if (oldDefaultActivity != null) {
            weight = oldDefaultActivity.weight - newDefaultActivity.weight + 5.0F;
         } else {
            weight = 1.0F;
         }

         ComponentName defaultName = new ComponentName(newDefaultActivity.resolveInfo.activityInfo.packageName, newDefaultActivity.resolveInfo.activityInfo.name);
         ActivityChooserModel.HistoricalRecord historicalRecord = new ActivityChooserModel.HistoricalRecord(defaultName, System.currentTimeMillis(), weight);
         this.addHistoricalRecord(historicalRecord);
      }
   }

   private void persistHistoricalDataIfNeeded() {
      if (!this.mReadShareHistoryCalled) {
         throw new IllegalStateException("No preceding call to #readHistoricalData");
      } else if (this.mHistoricalRecordsChanged) {
         this.mHistoricalRecordsChanged = false;
         if (!TextUtils.isEmpty(this.mHistoryFileName)) {
            (new ActivityChooserModel.PersistHistoryAsyncTask()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.mHistoricalRecords), this.mHistoryFileName});
         }

      }
   }

   public void setActivitySorter(ActivityChooserModel.ActivitySorter activitySorter) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         if (this.mActivitySorter != activitySorter) {
            this.mActivitySorter = activitySorter;
            if (this.sortActivitiesIfNeeded()) {
               this.notifyChanged();
            }

         }
      }
   }

   public void setHistoryMaxSize(int historyMaxSize) {
      Object var2 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         if (this.mHistoryMaxSize != historyMaxSize) {
            this.mHistoryMaxSize = historyMaxSize;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            if (this.sortActivitiesIfNeeded()) {
               this.notifyChanged();
            }

         }
      }
   }

   public int getHistoryMaxSize() {
      Object var1 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         return this.mHistoryMaxSize;
      }
   }

   public int getHistorySize() {
      Object var1 = this.mInstanceLock;
      synchronized(this.mInstanceLock) {
         this.ensureConsistentState();
         return this.mHistoricalRecords.size();
      }
   }

   private void ensureConsistentState() {
      boolean stateChanged = this.loadActivitiesIfNeeded();
      stateChanged |= this.readHistoricalDataIfNeeded();
      this.pruneExcessiveHistoricalRecordsIfNeeded();
      if (stateChanged) {
         this.sortActivitiesIfNeeded();
         this.notifyChanged();
      }

   }

   private boolean sortActivitiesIfNeeded() {
      if (this.mActivitySorter != null && this.mIntent != null && !this.mActivities.isEmpty() && !this.mHistoricalRecords.isEmpty()) {
         this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
         return true;
      } else {
         return false;
      }
   }

   private boolean loadActivitiesIfNeeded() {
      if (this.mReloadActivities && this.mIntent != null) {
         this.mReloadActivities = false;
         this.mActivities.clear();
         List resolveInfos = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
         int resolveInfoCount = resolveInfos.size();

         for(int i = 0; i < resolveInfoCount; ++i) {
            ResolveInfo resolveInfo = (ResolveInfo)resolveInfos.get(i);
            this.mActivities.add(new ActivityChooserModel.ActivityResolveInfo(resolveInfo));
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean readHistoricalDataIfNeeded() {
      if (this.mCanReadHistoricalData && this.mHistoricalRecordsChanged && !TextUtils.isEmpty(this.mHistoryFileName)) {
         this.mCanReadHistoricalData = false;
         this.mReadShareHistoryCalled = true;
         this.readHistoricalDataImpl();
         return true;
      } else {
         return false;
      }
   }

   private boolean addHistoricalRecord(ActivityChooserModel.HistoricalRecord historicalRecord) {
      boolean added = this.mHistoricalRecords.add(historicalRecord);
      if (added) {
         this.mHistoricalRecordsChanged = true;
         this.pruneExcessiveHistoricalRecordsIfNeeded();
         this.persistHistoricalDataIfNeeded();
         this.sortActivitiesIfNeeded();
         this.notifyChanged();
      }

      return added;
   }

   private void pruneExcessiveHistoricalRecordsIfNeeded() {
      int pruneCount = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
      if (pruneCount > 0) {
         this.mHistoricalRecordsChanged = true;

         for(int i = 0; i < pruneCount; ++i) {
            ActivityChooserModel.HistoricalRecord var3 = (ActivityChooserModel.HistoricalRecord)this.mHistoricalRecords.remove(0);
         }

      }
   }

   private void readHistoricalDataImpl() {
      FileInputStream fis = null;

      try {
         fis = this.mContext.openFileInput(this.mHistoryFileName);
      } catch (FileNotFoundException var22) {
         return;
      }

      try {
         XmlPullParser parser = Xml.newPullParser();
         parser.setInput(fis, "UTF-8");

         int type;
         for(type = 0; type != 1 && type != 2; type = parser.next()) {
            ;
         }

         if (!"historical-records".equals(parser.getName())) {
            throw new XmlPullParserException("Share records file does not start with historical-records tag.");
         }

         List historicalRecords = this.mHistoricalRecords;
         historicalRecords.clear();

         while(true) {
            type = parser.next();
            if (type == 1) {
               break;
            }

            if (type != 3 && type != 4) {
               String nodeName = parser.getName();
               if (!"historical-record".equals(nodeName)) {
                  throw new XmlPullParserException("Share records file not well-formed.");
               }

               String activity = parser.getAttributeValue((String)null, "activity");
               long time = Long.parseLong(parser.getAttributeValue((String)null, "time"));
               float weight = Float.parseFloat(parser.getAttributeValue((String)null, "weight"));
               ActivityChooserModel.HistoricalRecord readRecord = new ActivityChooserModel.HistoricalRecord(activity, time, weight);
               historicalRecords.add(readRecord);
            }
         }
      } catch (XmlPullParserException var23) {
         Log.e(LOG_TAG, "Error reading historical recrod file: " + this.mHistoryFileName, var23);
      } catch (IOException var24) {
         Log.e(LOG_TAG, "Error reading historical recrod file: " + this.mHistoryFileName, var24);
      } finally {
         if (fis != null) {
            try {
               fis.close();
            } catch (IOException var21) {
               ;
            }
         }

      }

   }

   private final class PersistHistoryAsyncTask extends AsyncTask {
      public Void doInBackground(Object... args) {
         List historicalRecords = (List)args[0];
         String historyFileName = (String)args[1];
         FileOutputStream fos = null;

         try {
            fos = ActivityChooserModel.this.mContext.openFileOutput(historyFileName, 0);
         } catch (FileNotFoundException var22) {
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical record file: " + historyFileName, var22);
            return null;
         }

         XmlSerializer serializer = Xml.newSerializer();

         try {
            serializer.setOutput(fos, (String)null);
            serializer.startDocument("UTF-8", true);
            serializer.startTag((String)null, "historical-records");
            int recordCount = historicalRecords.size();

            for(int i = 0; i < recordCount; ++i) {
               ActivityChooserModel.HistoricalRecord record = (ActivityChooserModel.HistoricalRecord)historicalRecords.remove(0);
               serializer.startTag((String)null, "historical-record");
               serializer.attribute((String)null, "activity", record.activity.flattenToString());
               serializer.attribute((String)null, "time", String.valueOf(record.time));
               serializer.attribute((String)null, "weight", String.valueOf(record.weight));
               serializer.endTag((String)null, "historical-record");
            }

            serializer.endTag((String)null, "historical-records");
            serializer.endDocument();
         } catch (IllegalArgumentException var23) {
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, var23);
         } catch (IllegalStateException var24) {
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, var24);
         } catch (IOException var25) {
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical record file: " + ActivityChooserModel.this.mHistoryFileName, var25);
         } finally {
            ActivityChooserModel.this.mCanReadHistoricalData = true;
            if (fos != null) {
               try {
                  fos.close();
               } catch (IOException var21) {
                  ;
               }
            }

         }

         return null;
      }
   }

   private static final class DefaultSorter implements ActivityChooserModel.ActivitySorter {
      private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
      private final Map mPackageNameToActivityMap = new HashMap();

      public void sort(Intent intent, List activities, List historicalRecords) {
         Map componentNameToActivityMap = this.mPackageNameToActivityMap;
         componentNameToActivityMap.clear();
         int activityCount = activities.size();

         int lastShareIndex;
         for(lastShareIndex = 0; lastShareIndex < activityCount; ++lastShareIndex) {
            ActivityChooserModel.ActivityResolveInfo activity = (ActivityChooserModel.ActivityResolveInfo)activities.get(lastShareIndex);
            activity.weight = 0.0F;
            ComponentName componentName = new ComponentName(activity.resolveInfo.activityInfo.packageName, activity.resolveInfo.activityInfo.name);
            componentNameToActivityMap.put(componentName, activity);
         }

         lastShareIndex = historicalRecords.size() - 1;
         float nextRecordWeight = 1.0F;

         for(int i = lastShareIndex; i >= 0; --i) {
            ActivityChooserModel.HistoricalRecord historicalRecord = (ActivityChooserModel.HistoricalRecord)historicalRecords.get(i);
            ComponentName componentName = historicalRecord.activity;
            ActivityChooserModel.ActivityResolveInfo activity = (ActivityChooserModel.ActivityResolveInfo)componentNameToActivityMap.get(componentName);
            if (activity != null) {
               activity.weight += historicalRecord.weight * nextRecordWeight;
               nextRecordWeight *= 0.95F;
            }
         }

         Collections.sort(activities);
      }
   }

   public static final class ActivityResolveInfo implements Comparable {
      public final ResolveInfo resolveInfo;
      public float weight;

      public ActivityResolveInfo(ResolveInfo resolveInfo) {
         this.resolveInfo = resolveInfo;
      }

      public int hashCode() {
         return 31 + Float.floatToIntBits(this.weight);
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            ActivityChooserModel.ActivityResolveInfo other = (ActivityChooserModel.ActivityResolveInfo)obj;
            return Float.floatToIntBits(this.weight) == Float.floatToIntBits(other.weight);
         }
      }

      public int compareTo(ActivityChooserModel.ActivityResolveInfo another) {
         return Float.floatToIntBits(another.weight) - Float.floatToIntBits(this.weight);
      }

      public String toString() {
         StringBuilder builder = new StringBuilder();
         builder.append("[");
         builder.append("resolveInfo:").append(this.resolveInfo.toString());
         builder.append("; weight:").append(new BigDecimal((double)this.weight));
         builder.append("]");
         return builder.toString();
      }
   }

   public static final class HistoricalRecord {
      public final ComponentName activity;
      public final long time;
      public final float weight;

      public HistoricalRecord(String activityName, long time, float weight) {
         this(ComponentName.unflattenFromString(activityName), time, weight);
      }

      public HistoricalRecord(ComponentName activityName, long time, float weight) {
         this.activity = activityName;
         this.time = time;
         this.weight = weight;
      }

      public int hashCode() {
         int prime = true;
         int result = 1;
         int result = 31 * result + (this.activity == null ? 0 : this.activity.hashCode());
         result = 31 * result + (int)(this.time ^ this.time >>> 32);
         result = 31 * result + Float.floatToIntBits(this.weight);
         return result;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            ActivityChooserModel.HistoricalRecord other = (ActivityChooserModel.HistoricalRecord)obj;
            if (this.activity == null) {
               if (other.activity != null) {
                  return false;
               }
            } else if (!this.activity.equals(other.activity)) {
               return false;
            }

            if (this.time != other.time) {
               return false;
            } else {
               return Float.floatToIntBits(this.weight) == Float.floatToIntBits(other.weight);
            }
         }
      }

      public String toString() {
         StringBuilder builder = new StringBuilder();
         builder.append("[");
         builder.append("; activity:").append(this.activity);
         builder.append("; time:").append(this.time);
         builder.append("; weight:").append(new BigDecimal((double)this.weight));
         builder.append("]");
         return builder.toString();
      }
   }

   public interface OnChooseActivityListener {
      boolean onChooseActivity(ActivityChooserModel var1, Intent var2);
   }

   public interface ActivitySorter {
      void sort(Intent var1, List var2, List var3);
   }

   public interface ActivityChooserModelClient {
      void setActivityChooserModel(ActivityChooserModel var1);
   }
}
