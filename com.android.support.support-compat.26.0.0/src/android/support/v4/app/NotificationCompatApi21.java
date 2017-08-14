package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@RequiresApi(21)
class NotificationCompatApi21 {
   private static final String KEY_AUTHOR = "author";
   private static final String KEY_TEXT = "text";
   private static final String KEY_MESSAGES = "messages";
   private static final String KEY_REMOTE_INPUT = "remote_input";
   private static final String KEY_ON_REPLY = "on_reply";
   private static final String KEY_ON_READ = "on_read";
   private static final String KEY_PARTICIPANTS = "participants";
   private static final String KEY_TIMESTAMP = "timestamp";

   static Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation uc) {
      if (uc == null) {
         return null;
      } else {
         Bundle b = new Bundle();
         String author = null;
         if (uc.getParticipants() != null && uc.getParticipants().length > 1) {
            author = uc.getParticipants()[0];
         }

         Parcelable[] messages = new Parcelable[uc.getMessages().length];

         for(int i = 0; i < messages.length; ++i) {
            Bundle m = new Bundle();
            m.putString("text", uc.getMessages()[i]);
            m.putString("author", author);
            messages[i] = m;
         }

         b.putParcelableArray("messages", messages);
         RemoteInputCompatBase.RemoteInput remoteInput = uc.getRemoteInput();
         if (remoteInput != null) {
            b.putParcelable("remote_input", fromCompatRemoteInput(remoteInput));
         }

         b.putParcelable("on_reply", uc.getReplyPendingIntent());
         b.putParcelable("on_read", uc.getReadPendingIntent());
         b.putStringArray("participants", uc.getParticipants());
         b.putLong("timestamp", uc.getLatestTimestamp());
         return b;
      }
   }

   static NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle b, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
      if (b == null) {
         return null;
      } else {
         Parcelable[] parcelableMessages = b.getParcelableArray("messages");
         String[] messages = null;
         if (parcelableMessages != null) {
            String[] tmp = new String[parcelableMessages.length];
            boolean success = true;

            for(int i = 0; i < tmp.length; ++i) {
               if (!(parcelableMessages[i] instanceof Bundle)) {
                  success = false;
                  break;
               }

               tmp[i] = ((Bundle)parcelableMessages[i]).getString("text");
               if (tmp[i] == null) {
                  success = false;
                  break;
               }
            }

            if (!success) {
               return null;
            }

            messages = tmp;
         }

         PendingIntent onRead = (PendingIntent)b.getParcelable("on_read");
         PendingIntent onReply = (PendingIntent)b.getParcelable("on_reply");
         android.app.RemoteInput remoteInput = (android.app.RemoteInput)b.getParcelable("remote_input");
         String[] participants = b.getStringArray("participants");
         return participants != null && participants.length == 1 ? factory.build(messages, remoteInput != null ? toCompatRemoteInput(remoteInput, remoteInputFactory) : null, onReply, onRead, participants, b.getLong("timestamp")) : null;
      }
   }

   private static android.app.RemoteInput fromCompatRemoteInput(RemoteInputCompatBase.RemoteInput src) {
      return (new android.app.RemoteInput.Builder(src.getResultKey())).setLabel(src.getLabel()).setChoices(src.getChoices()).setAllowFreeFormInput(src.getAllowFreeFormInput()).addExtras(src.getExtras()).build();
   }

   private static RemoteInputCompatBase.RemoteInput toCompatRemoteInput(android.app.RemoteInput remoteInput, RemoteInputCompatBase.RemoteInput.Factory factory) {
      return factory.build(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), (Set)null);
   }

   public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions {
      private android.app.Notification.Builder b;
      private Bundle mExtras;
      private RemoteViews mContentView;
      private RemoteViews mBigContentView;
      private RemoteViews mHeadsUpContentView;
      private int mGroupAlertBehavior;

      public Builder(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate, boolean showWhen, boolean useChronometer, int priority, CharSequence subText, boolean localOnly, String category, ArrayList people, Bundle extras, int color, int visibility, Notification publicVersion, String groupKey, boolean groupSummary, String sortKey, RemoteViews contentView, RemoteViews bigContentView, RemoteViews headsUpContentView, int groupAlertBehavior) {
         this.b = (new android.app.Notification.Builder(context)).setWhen(n.when).setShowWhen(showWhen).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(useChronometer).setPriority(priority).setProgress(progressMax, progress, progressIndeterminate).setLocalOnly(localOnly).setGroup(groupKey).setGroupSummary(groupSummary).setSortKey(sortKey).setCategory(category).setColor(color).setVisibility(visibility).setPublicVersion(publicVersion);
         this.mExtras = new Bundle();
         if (extras != null) {
            this.mExtras.putAll(extras);
         }

         Iterator var32 = people.iterator();

         while(var32.hasNext()) {
            String person = (String)var32.next();
            this.b.addPerson(person);
         }

         this.mContentView = contentView;
         this.mBigContentView = bigContentView;
         this.mHeadsUpContentView = headsUpContentView;
         this.mGroupAlertBehavior = groupAlertBehavior;
      }

      public void addAction(NotificationCompatBase.Action action) {
         NotificationCompatApi20.addAction(this.b, action);
      }

      public android.app.Notification.Builder getBuilder() {
         return this.b;
      }

      public Notification build() {
         this.b.setExtras(this.mExtras);
         Notification notification = this.b.build();
         if (this.mContentView != null) {
            notification.contentView = this.mContentView;
         }

         if (this.mBigContentView != null) {
            notification.bigContentView = this.mBigContentView;
         }

         if (this.mHeadsUpContentView != null) {
            notification.headsUpContentView = this.mHeadsUpContentView;
         }

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
