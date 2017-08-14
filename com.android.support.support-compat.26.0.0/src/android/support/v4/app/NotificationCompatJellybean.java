package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.InboxStyle;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(16)
class NotificationCompatJellybean {
   public static final String TAG = "NotificationCompat";
   static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
   static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
   private static final String KEY_ICON = "icon";
   private static final String KEY_TITLE = "title";
   private static final String KEY_ACTION_INTENT = "actionIntent";
   private static final String KEY_EXTRAS = "extras";
   private static final String KEY_REMOTE_INPUTS = "remoteInputs";
   private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
   private static final Object sExtrasLock = new Object();
   private static Field sExtrasField;
   private static boolean sExtrasFieldAccessFailed;
   private static final Object sActionsLock = new Object();
   private static Class sActionClass;
   private static Field sActionsField;
   private static Field sActionIconField;
   private static Field sActionTitleField;
   private static Field sActionIntentField;
   private static boolean sActionsAccessFailed;

   public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor b, CharSequence bigContentTitle, boolean useSummary, CharSequence summaryText, CharSequence bigText) {
      BigTextStyle style = (new BigTextStyle(b.getBuilder())).setBigContentTitle(bigContentTitle).bigText(bigText);
      if (useSummary) {
         style.setSummaryText(summaryText);
      }

   }

   public static void addBigPictureStyle(NotificationBuilderWithBuilderAccessor b, CharSequence bigContentTitle, boolean useSummary, CharSequence summaryText, Bitmap bigPicture, Bitmap bigLargeIcon, boolean bigLargeIconSet) {
      BigPictureStyle style = (new BigPictureStyle(b.getBuilder())).setBigContentTitle(bigContentTitle).bigPicture(bigPicture);
      if (bigLargeIconSet) {
         style.bigLargeIcon(bigLargeIcon);
      }

      if (useSummary) {
         style.setSummaryText(summaryText);
      }

   }

   public static void addInboxStyle(NotificationBuilderWithBuilderAccessor b, CharSequence bigContentTitle, boolean useSummary, CharSequence summaryText, ArrayList texts) {
      InboxStyle style = (new InboxStyle(b.getBuilder())).setBigContentTitle(bigContentTitle);
      if (useSummary) {
         style.setSummaryText(summaryText);
      }

      Iterator var6 = texts.iterator();

      while(var6.hasNext()) {
         CharSequence text = (CharSequence)var6.next();
         style.addLine(text);
      }

   }

   public static SparseArray buildActionExtrasMap(List actionExtrasList) {
      SparseArray actionExtrasMap = null;
      int i = 0;

      for(int count = actionExtrasList.size(); i < count; ++i) {
         Bundle actionExtras = (Bundle)actionExtrasList.get(i);
         if (actionExtras != null) {
            if (actionExtrasMap == null) {
               actionExtrasMap = new SparseArray();
            }

            actionExtrasMap.put(i, actionExtras);
         }
      }

      return actionExtrasMap;
   }

   public static Bundle getExtras(Notification notif) {
      Object var1 = sExtrasLock;
      synchronized(sExtrasLock) {
         if (sExtrasFieldAccessFailed) {
            return null;
         } else {
            label64: {
               Bundle var10000;
               try {
                  if (sExtrasField == null) {
                     Field extrasField = Notification.class.getDeclaredField("extras");
                     if (!Bundle.class.isAssignableFrom(extrasField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        sExtrasFieldAccessFailed = true;
                        var10000 = null;
                        return var10000;
                     }

                     extrasField.setAccessible(true);
                     sExtrasField = extrasField;
                  }

                  Bundle extras = (Bundle)sExtrasField.get(notif);
                  if (extras == null) {
                     extras = new Bundle();
                     sExtrasField.set(notif, extras);
                  }

                  var10000 = extras;
               } catch (IllegalAccessException var4) {
                  Log.e("NotificationCompat", "Unable to access notification extras", var4);
                  break label64;
               } catch (NoSuchFieldException var5) {
                  Log.e("NotificationCompat", "Unable to access notification extras", var5);
                  break label64;
               }

               return var10000;
            }

            sExtrasFieldAccessFailed = true;
            return null;
         }
      }
   }

   public static NotificationCompatBase.Action readAction(NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory, int icon, CharSequence title, PendingIntent actionIntent, Bundle extras) {
      RemoteInputCompatBase.RemoteInput[] remoteInputs = null;
      RemoteInputCompatBase.RemoteInput[] dataOnlyRemoteInputs = null;
      boolean allowGeneratedReplies = false;
      if (extras != null) {
         remoteInputs = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(extras, "android.support.remoteInputs"), remoteInputFactory);
         dataOnlyRemoteInputs = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(extras, "android.support.dataRemoteInputs"), remoteInputFactory);
         allowGeneratedReplies = extras.getBoolean("android.support.allowGeneratedReplies");
      }

      return factory.build(icon, title, actionIntent, extras, remoteInputs, dataOnlyRemoteInputs, allowGeneratedReplies);
   }

   public static Bundle writeActionAndGetExtras(android.app.Notification.Builder builder, NotificationCompatBase.Action action) {
      builder.addAction(action.getIcon(), action.getTitle(), action.getActionIntent());
      Bundle actionExtras = new Bundle(action.getExtras());
      if (action.getRemoteInputs() != null) {
         actionExtras.putParcelableArray("android.support.remoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
      }

      if (action.getDataOnlyRemoteInputs() != null) {
         actionExtras.putParcelableArray("android.support.dataRemoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getDataOnlyRemoteInputs()));
      }

      actionExtras.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
      return actionExtras;
   }

   public static int getActionCount(Notification notif) {
      Object var1 = sActionsLock;
      synchronized(sActionsLock) {
         Object[] actionObjects = getActionObjectsLocked(notif);
         return actionObjects != null ? actionObjects.length : 0;
      }
   }

   public static NotificationCompatBase.Action getAction(Notification notif, int actionIndex, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
      Object var4 = sActionsLock;
      synchronized(sActionsLock) {
         try {
            Object[] actionObjects = getActionObjectsLocked(notif);
            if (actionObjects != null) {
               Object actionObject = actionObjects[actionIndex];
               Bundle actionExtras = null;
               Bundle extras = getExtras(notif);
               if (extras != null) {
                  SparseArray actionExtrasMap = extras.getSparseParcelableArray("android.support.actionExtras");
                  if (actionExtrasMap != null) {
                     actionExtras = (Bundle)actionExtrasMap.get(actionIndex);
                  }
               }

               NotificationCompatBase.Action var10000 = readAction(factory, remoteInputFactory, sActionIconField.getInt(actionObject), (CharSequence)sActionTitleField.get(actionObject), (PendingIntent)sActionIntentField.get(actionObject), actionExtras);
               return var10000;
            }
         } catch (IllegalAccessException var11) {
            Log.e("NotificationCompat", "Unable to access notification actions", var11);
            sActionsAccessFailed = true;
         }

         return null;
      }
   }

   private static Object[] getActionObjectsLocked(Notification notif) {
      Object var1 = sActionsLock;
      synchronized(sActionsLock) {
         if (!ensureActionReflectionReadyLocked()) {
            return null;
         } else {
            Object[] var10000;
            try {
               var10000 = (Object[])((Object[])sActionsField.get(notif));
            } catch (IllegalAccessException var4) {
               Log.e("NotificationCompat", "Unable to access notification actions", var4);
               sActionsAccessFailed = true;
               return null;
            }

            return var10000;
         }
      }
   }

   private static boolean ensureActionReflectionReadyLocked() {
      if (sActionsAccessFailed) {
         return false;
      } else {
         try {
            if (sActionsField == null) {
               sActionClass = Class.forName("android.app.Notification$Action");
               sActionIconField = sActionClass.getDeclaredField("icon");
               sActionTitleField = sActionClass.getDeclaredField("title");
               sActionIntentField = sActionClass.getDeclaredField("actionIntent");
               sActionsField = Notification.class.getDeclaredField("actions");
               sActionsField.setAccessible(true);
            }
         } catch (ClassNotFoundException var1) {
            Log.e("NotificationCompat", "Unable to access notification actions", var1);
            sActionsAccessFailed = true;
         } catch (NoSuchFieldException var2) {
            Log.e("NotificationCompat", "Unable to access notification actions", var2);
            sActionsAccessFailed = true;
         }

         return !sActionsAccessFailed;
      }
   }

   public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList parcelables, NotificationCompatBase.Action.Factory actionFactory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
      if (parcelables == null) {
         return null;
      } else {
         NotificationCompatBase.Action[] actions = actionFactory.newArray(parcelables.size());

         for(int i = 0; i < actions.length; ++i) {
            actions[i] = getActionFromBundle((Bundle)parcelables.get(i), actionFactory, remoteInputFactory);
         }

         return actions;
      }
   }

   private static NotificationCompatBase.Action getActionFromBundle(Bundle bundle, NotificationCompatBase.Action.Factory actionFactory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
      Bundle extras = bundle.getBundle("extras");
      boolean allowGeneratedReplies = false;
      if (extras != null) {
         allowGeneratedReplies = extras.getBoolean("android.support.allowGeneratedReplies", false);
      }

      return actionFactory.build(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent)bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "remoteInputs"), remoteInputFactory), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "dataOnlyRemoteInputs"), remoteInputFactory), allowGeneratedReplies);
   }

   public static ArrayList getParcelableArrayListForActions(NotificationCompatBase.Action[] actions) {
      if (actions == null) {
         return null;
      } else {
         ArrayList parcelables = new ArrayList(actions.length);
         NotificationCompatBase.Action[] var2 = actions;
         int var3 = actions.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            NotificationCompatBase.Action action = var2[var4];
            parcelables.add(getBundleForAction(action));
         }

         return parcelables;
      }
   }

   private static Bundle getBundleForAction(NotificationCompatBase.Action action) {
      Bundle bundle = new Bundle();
      bundle.putInt("icon", action.getIcon());
      bundle.putCharSequence("title", action.getTitle());
      bundle.putParcelable("actionIntent", action.getActionIntent());
      Bundle actionExtras;
      if (action.getExtras() != null) {
         actionExtras = new Bundle(action.getExtras());
      } else {
         actionExtras = new Bundle();
      }

      actionExtras.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
      bundle.putBundle("extras", actionExtras);
      bundle.putParcelableArray("remoteInputs", RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
      return bundle;
   }

   public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions {
      private android.app.Notification.Builder b;
      private final Bundle mExtras;
      private List mActionExtrasList = new ArrayList();
      private RemoteViews mContentView;
      private RemoteViews mBigContentView;

      public Builder(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, Bundle extras, String groupKey, boolean groupSummary, String sortKey, RemoteViews contentView, RemoteViews bigContentView) {
         this.b = (new android.app.Notification.Builder(context)).setWhen(n.when).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate);
         this.mExtras = new Bundle();
         if (extras != null) {
            this.mExtras.putAll(extras);
         }

         if (localOnly) {
            this.mExtras.putBoolean("android.support.localOnly", true);
         }

         if (groupKey != null) {
            this.mExtras.putString("android.support.groupKey", groupKey);
            if (groupSummary) {
               this.mExtras.putBoolean("android.support.isGroupSummary", true);
            } else {
               this.mExtras.putBoolean("android.support.useSideChannel", true);
            }
         }

         if (sortKey != null) {
            this.mExtras.putString("android.support.sortKey", sortKey);
         }

         this.mContentView = contentView;
         this.mBigContentView = bigContentView;
      }

      public void addAction(NotificationCompatBase.Action action) {
         this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, action));
      }

      public android.app.Notification.Builder getBuilder() {
         return this.b;
      }

      public Notification build() {
         Notification notif = this.b.build();
         Bundle extras = NotificationCompatJellybean.getExtras(notif);
         Bundle mergeBundle = new Bundle(this.mExtras);
         Iterator var4 = this.mExtras.keySet().iterator();

         while(var4.hasNext()) {
            String key = (String)var4.next();
            if (extras.containsKey(key)) {
               mergeBundle.remove(key);
            }
         }

         extras.putAll(mergeBundle);
         SparseArray actionExtrasMap = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
         if (actionExtrasMap != null) {
            NotificationCompatJellybean.getExtras(notif).putSparseParcelableArray("android.support.actionExtras", actionExtrasMap);
         }

         if (this.mContentView != null) {
            notif.contentView = this.mContentView;
         }

         if (this.mBigContentView != null) {
            notif.bigContentView = this.mBigContentView;
         }

         return notif;
      }
   }
}
