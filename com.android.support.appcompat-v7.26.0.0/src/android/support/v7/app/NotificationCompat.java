package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Build.VERSION;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;

/** @deprecated */
@Deprecated
public class NotificationCompat extends android.support.v4.app.NotificationCompat {
   /** @deprecated */
   @Deprecated
   public static Token getMediaSession(Notification notification) {
      Bundle extras = getExtras(notification);
      if (extras != null) {
         if (VERSION.SDK_INT >= 21) {
            Object tokenInner = extras.getParcelable("android.mediaSession");
            if (tokenInner != null) {
               return Token.fromToken(tokenInner);
            }
         } else {
            IBinder tokenInner = BundleCompat.getBinder(extras, "android.mediaSession");
            if (tokenInner != null) {
               Parcel p = Parcel.obtain();
               p.writeStrongBinder(tokenInner);
               p.setDataPosition(0);
               Token token = (Token)Token.CREATOR.createFromParcel(p);
               p.recycle();
               return token;
            }
         }
      }

      return null;
   }

   /** @deprecated */
   @Deprecated
   public static class DecoratedMediaCustomViewStyle extends android.support.v4.media.app.NotificationCompat.DecoratedMediaCustomViewStyle {
   }

   /** @deprecated */
   @Deprecated
   public static class DecoratedCustomViewStyle extends android.support.v4.app.NotificationCompat.DecoratedCustomViewStyle {
   }

   /** @deprecated */
   @Deprecated
   public static class MediaStyle extends android.support.v4.media.app.NotificationCompat.MediaStyle {
      /** @deprecated */
      @Deprecated
      public MediaStyle() {
      }

      /** @deprecated */
      @Deprecated
      public MediaStyle(android.support.v4.app.NotificationCompat.Builder builder) {
         super(builder);
      }

      /** @deprecated */
      @Deprecated
      public NotificationCompat.MediaStyle setShowActionsInCompactView(int... actions) {
         return (NotificationCompat.MediaStyle)super.setShowActionsInCompactView(actions);
      }

      /** @deprecated */
      @Deprecated
      public NotificationCompat.MediaStyle setMediaSession(Token token) {
         return (NotificationCompat.MediaStyle)super.setMediaSession(token);
      }

      /** @deprecated */
      @Deprecated
      public NotificationCompat.MediaStyle setShowCancelButton(boolean show) {
         return (NotificationCompat.MediaStyle)super.setShowCancelButton(show);
      }

      /** @deprecated */
      @Deprecated
      public NotificationCompat.MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
         return (NotificationCompat.MediaStyle)super.setCancelButtonIntent(pendingIntent);
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Builder extends android.support.v4.app.NotificationCompat.Builder {
      /** @deprecated */
      @Deprecated
      public Builder(Context context) {
         super(context);
      }
   }
}
