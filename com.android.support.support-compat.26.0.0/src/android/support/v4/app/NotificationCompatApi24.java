package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Notification.Action;
import android.app.Notification.MessagingStyle;
import android.app.Notification.MessagingStyle.Message;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(24)
class NotificationCompatApi24 {
   public static void addMessagingStyle(NotificationBuilderWithBuilderAccessor b, CharSequence userDisplayName, CharSequence conversationTitle, List texts, List timestamps, List senders, List dataMimeTypes, List dataUris) {
      MessagingStyle style = (new MessagingStyle(userDisplayName)).setConversationTitle(conversationTitle);

      for(int i = 0; i < texts.size(); ++i) {
         Message message = new Message((CharSequence)texts.get(i), ((Long)timestamps.get(i)).longValue(), (CharSequence)senders.get(i));
         if (dataMimeTypes.get(i) != null) {
            message.setData((String)dataMimeTypes.get(i), (Uri)dataUris.get(i));
         }

         style.addMessage(message);
      }

      style.setBuilder(b.getBuilder());
   }

   public static void addAction(android.app.Notification.Builder b, NotificationCompatBase.Action action) {
      android.app.Notification.Action.Builder actionBuilder = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
      if (action.getRemoteInputs() != null) {
         android.app.RemoteInput[] var3 = RemoteInputCompatApi20.fromCompat(action.getRemoteInputs());
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            android.app.RemoteInput remoteInput = var3[var5];
            actionBuilder.addRemoteInput(remoteInput);
         }
      }

      Bundle actionExtras;
      if (action.getExtras() != null) {
         actionExtras = new Bundle(action.getExtras());
      } else {
         actionExtras = new Bundle();
      }

      actionExtras.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
      actionBuilder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
      actionBuilder.addExtras(actionExtras);
      b.addAction(actionBuilder.build());
   }

   public static NotificationCompatBase.Action getAction(Notification notif, int actionIndex, NotificationCompatBase.Action.Factory actionFactory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
      return getActionCompatFromAction(notif.actions[actionIndex], actionFactory, remoteInputFactory);
   }

   private static NotificationCompatBase.Action getActionCompatFromAction(Action action, NotificationCompatBase.Action.Factory actionFactory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
      RemoteInputCompatBase.RemoteInput[] remoteInputs = RemoteInputCompatApi20.toCompat(action.getRemoteInputs(), remoteInputFactory);
      boolean allowGeneratedReplies = action.getExtras().getBoolean("android.support.allowGeneratedReplies") || action.getAllowGeneratedReplies();
      return actionFactory.build(action.icon, action.title, action.actionIntent, action.getExtras(), remoteInputs, (RemoteInputCompatBase.RemoteInput[])null, allowGeneratedReplies);
   }

   private static Action getActionFromActionCompat(NotificationCompatBase.Action actionCompat) {
      android.app.Notification.Action.Builder actionBuilder = new android.app.Notification.Action.Builder(actionCompat.getIcon(), actionCompat.getTitle(), actionCompat.getActionIntent());
      Bundle actionExtras;
      if (actionCompat.getExtras() != null) {
         actionExtras = new Bundle(actionCompat.getExtras());
      } else {
         actionExtras = new Bundle();
      }

      actionExtras.putBoolean("android.support.allowGeneratedReplies", actionCompat.getAllowGeneratedReplies());
      actionBuilder.setAllowGeneratedReplies(actionCompat.getAllowGeneratedReplies());
      actionBuilder.addExtras(actionExtras);
      RemoteInputCompatBase.RemoteInput[] remoteInputCompats = actionCompat.getRemoteInputs();
      if (remoteInputCompats != null) {
         android.app.RemoteInput[] remoteInputs = RemoteInputCompatApi20.fromCompat(remoteInputCompats);
         android.app.RemoteInput[] var5 = remoteInputs;
         int var6 = remoteInputs.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            android.app.RemoteInput remoteInput = var5[var7];
            actionBuilder.addRemoteInput(remoteInput);
         }
      }

      return actionBuilder.build();
   }

   public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(ArrayList parcelables, NotificationCompatBase.Action.Factory actionFactory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
      if (parcelables == null) {
         return null;
      } else {
         NotificationCompatBase.Action[] actions = actionFactory.newArray(parcelables.size());

         for(int i = 0; i < actions.length; ++i) {
            Action action = (Action)parcelables.get(i);
            actions[i] = getActionCompatFromAction(action, actionFactory, remoteInputFactory);
         }

         return actions;
      }
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
            parcelables.add(getActionFromActionCompat(action));
         }

         return parcelables;
      }
   }

   public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions {
      private android.app.Notification.Builder b;
      private int mGroupAlertBehavior;

      public Builder(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, String category, ArrayList people, Bundle extras, int color, int visibility, Notification publicVersion, String groupKey, boolean groupSummary, String sortKey, CharSequence[] remoteInputHistory, RemoteViews contentView, RemoteViews bigContentView, RemoteViews headsUpContentView, int groupAlertBehavior) {
         this.b = (new android.app.Notification.Builder(context)).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate).setLocalOnly(localOnly).setExtras(extras).setGroup(groupKey).setGroupSummary(groupSummary).setSortKey(sortKey).setCategory(category).setColor(color).setVisibility(visibility).setPublicVersion(publicVersion).setRemoteInputHistory(remoteInputHistory);
         if (contentView != null) {
            this.b.setCustomContentView(contentView);
         }

         if (bigContentView != null) {
            this.b.setCustomBigContentView(bigContentView);
         }

         if (headsUpContentView != null) {
            this.b.setCustomHeadsUpContentView(headsUpContentView);
         }

         Iterator var33 = people.iterator();

         while(var33.hasNext()) {
            String person = (String)var33.next();
            this.b.addPerson(person);
         }

         this.mGroupAlertBehavior = groupAlertBehavior;
      }

      public void addAction(NotificationCompatBase.Action action) {
         NotificationCompatApi24.addAction(this.b, action);
      }

      public android.app.Notification.Builder getBuilder() {
         return this.b;
      }

      public Notification build() {
         Notification notification = this.b.build();
         if (this.mGroupAlertBehavior != 0) {
            if (notification.getGroup() != null && (notification.flags & 512) != 0 && this.mGroupAlertBehavior == 2) {
               this.removeSoundAndVibration(notification);
            }

            if (notification.getGroup() != null && (notification.flags & 512) == 0 && this.mGroupAlertBehavior == 1) {
               this.removeSoundAndVibration(notification);
            }
         }

         return notification;
      }

      private void removeSoundAndVibration(Notification notification) {
         notification.sound = null;
         notification.vibrate = null;
         notification.defaults &= -2;
         notification.defaults &= -3;
      }
   }
}
