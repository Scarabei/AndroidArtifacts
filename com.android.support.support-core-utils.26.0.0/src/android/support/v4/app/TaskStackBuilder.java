package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder implements Iterable {
   private static final String TAG = "TaskStackBuilder";
   private static final TaskStackBuilder.TaskStackBuilderBaseImpl IMPL;
   private final ArrayList mIntents = new ArrayList();
   private final Context mSourceContext;

   private TaskStackBuilder(Context a) {
      this.mSourceContext = a;
   }

   public static TaskStackBuilder create(Context context) {
      return new TaskStackBuilder(context);
   }

   /** @deprecated */
   @Deprecated
   public static TaskStackBuilder from(Context context) {
      return create(context);
   }

   public TaskStackBuilder addNextIntent(Intent nextIntent) {
      this.mIntents.add(nextIntent);
      return this;
   }

   public TaskStackBuilder addNextIntentWithParentStack(Intent nextIntent) {
      ComponentName target = nextIntent.getComponent();
      if (target == null) {
         target = nextIntent.resolveActivity(this.mSourceContext.getPackageManager());
      }

      if (target != null) {
         this.addParentStack(target);
      }

      this.addNextIntent(nextIntent);
      return this;
   }

   public TaskStackBuilder addParentStack(Activity sourceActivity) {
      Intent parent = null;
      if (sourceActivity instanceof TaskStackBuilder.SupportParentable) {
         parent = ((TaskStackBuilder.SupportParentable)sourceActivity).getSupportParentActivityIntent();
      }

      if (parent == null) {
         parent = NavUtils.getParentActivityIntent(sourceActivity);
      }

      if (parent != null) {
         ComponentName target = parent.getComponent();
         if (target == null) {
            target = parent.resolveActivity(this.mSourceContext.getPackageManager());
         }

         this.addParentStack(target);
         this.addNextIntent(parent);
      }

      return this;
   }

   public TaskStackBuilder addParentStack(Class sourceActivityClass) {
      return this.addParentStack(new ComponentName(this.mSourceContext, sourceActivityClass));
   }

   public TaskStackBuilder addParentStack(ComponentName sourceActivityName) {
      int insertAt = this.mIntents.size();

      try {
         for(Intent parent = NavUtils.getParentActivityIntent(this.mSourceContext, sourceActivityName); parent != null; parent = NavUtils.getParentActivityIntent(this.mSourceContext, parent.getComponent())) {
            this.mIntents.add(insertAt, parent);
         }

         return this;
      } catch (NameNotFoundException var4) {
         Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
         throw new IllegalArgumentException(var4);
      }
   }

   public int getIntentCount() {
      return this.mIntents.size();
   }

   /** @deprecated */
   @Deprecated
   public Intent getIntent(int index) {
      return this.editIntentAt(index);
   }

   public Intent editIntentAt(int index) {
      return (Intent)this.mIntents.get(index);
   }

   /** @deprecated */
   @Deprecated
   public Iterator iterator() {
      return this.mIntents.iterator();
   }

   public void startActivities() {
      this.startActivities((Bundle)null);
   }

   public void startActivities(Bundle options) {
      if (this.mIntents.isEmpty()) {
         throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
      } else {
         Intent[] intents = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
         intents[0] = (new Intent(intents[0])).addFlags(268484608);
         if (!ContextCompat.startActivities(this.mSourceContext, intents, options)) {
            Intent topIntent = new Intent(intents[intents.length - 1]);
            topIntent.addFlags(268435456);
            this.mSourceContext.startActivity(topIntent);
         }

      }
   }

   public PendingIntent getPendingIntent(int requestCode, int flags) {
      return this.getPendingIntent(requestCode, flags, (Bundle)null);
   }

   public PendingIntent getPendingIntent(int requestCode, int flags, Bundle options) {
      if (this.mIntents.isEmpty()) {
         throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
      } else {
         Intent[] intents = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
         intents[0] = (new Intent(intents[0])).addFlags(268484608);
         return IMPL.getPendingIntent(this.mSourceContext, intents, requestCode, flags, options);
      }
   }

   public Intent[] getIntents() {
      Intent[] intents = new Intent[this.mIntents.size()];
      if (intents.length == 0) {
         return intents;
      } else {
         intents[0] = (new Intent((Intent)this.mIntents.get(0))).addFlags(268484608);

         for(int i = 1; i < intents.length; ++i) {
            intents[i] = new Intent((Intent)this.mIntents.get(i));
         }

         return intents;
      }
   }

   static {
      if (VERSION.SDK_INT >= 16) {
         IMPL = new TaskStackBuilder.TaskStackBuilderApi16Impl();
      } else {
         IMPL = new TaskStackBuilder.TaskStackBuilderBaseImpl();
      }

   }

   @RequiresApi(16)
   static class TaskStackBuilderApi16Impl extends TaskStackBuilder.TaskStackBuilderBaseImpl {
      public PendingIntent getPendingIntent(Context context, Intent[] intents, int requestCode, int flags, Bundle options) {
         intents[0] = (new Intent(intents[0])).addFlags(268484608);
         return PendingIntent.getActivities(context, requestCode, intents, flags, options);
      }
   }

   static class TaskStackBuilderBaseImpl {
      public PendingIntent getPendingIntent(Context context, Intent[] intents, int requestCode, int flags, Bundle options) {
         intents[0] = (new Intent(intents[0])).addFlags(268484608);
         return PendingIntent.getActivities(context, requestCode, intents, flags);
      }
   }

   public interface SupportParentable {
      Intent getSupportParentActivityIntent();
   }
}
