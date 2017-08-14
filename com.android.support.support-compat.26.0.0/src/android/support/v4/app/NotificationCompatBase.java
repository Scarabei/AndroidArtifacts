package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class NotificationCompatBase {
   public abstract static class UnreadConversation {
      abstract String[] getParticipants();

      abstract String getParticipant();

      abstract String[] getMessages();

      abstract RemoteInputCompatBase.RemoteInput getRemoteInput();

      abstract PendingIntent getReplyPendingIntent();

      abstract PendingIntent getReadPendingIntent();

      abstract long getLatestTimestamp();

      public interface Factory {
         NotificationCompatBase.UnreadConversation build(String[] var1, RemoteInputCompatBase.RemoteInput var2, PendingIntent var3, PendingIntent var4, String[] var5, long var6);
      }
   }

   public abstract static class Action {
      public abstract int getIcon();

      public abstract CharSequence getTitle();

      public abstract PendingIntent getActionIntent();

      public abstract Bundle getExtras();

      public abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();

      public abstract RemoteInputCompatBase.RemoteInput[] getDataOnlyRemoteInputs();

      public abstract boolean getAllowGeneratedReplies();

      public interface Factory {
         NotificationCompatBase.Action build(int var1, CharSequence var2, PendingIntent var3, Bundle var4, RemoteInputCompatBase.RemoteInput[] var5, RemoteInputCompatBase.RemoteInput[] var6, boolean var7);

         NotificationCompatBase.Action[] newArray(int var1);
      }
   }
}
