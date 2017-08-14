package android.support.v4.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.mediacompat.R.color;
import android.support.mediacompat.R.id;
import android.support.mediacompat.R.integer;
import android.support.mediacompat.R.layout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;

public class NotificationCompat {
   public static class DecoratedMediaCustomViewStyle extends NotificationCompat.MediaStyle {
      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            builder.getBuilder().setStyle(this.fillInMediaStyle(new android.app.Notification.DecoratedMediaCustomViewStyle()));
         } else {
            super.apply(builder);
         }

      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            boolean hasContentView = this.mBuilder.getContentView() != null;
            if (VERSION.SDK_INT >= 21) {
               boolean createCustomContent = hasContentView || this.mBuilder.getBigContentView() != null;
               if (createCustomContent) {
                  RemoteViews contentView = this.generateContentView();
                  if (hasContentView) {
                     this.buildIntoRemoteViews(contentView, this.mBuilder.getContentView());
                  }

                  this.setBackgroundColor(contentView);
                  return contentView;
               }
            } else {
               RemoteViews contentView = this.generateContentView();
               if (hasContentView) {
                  this.buildIntoRemoteViews(contentView, this.mBuilder.getContentView());
                  return contentView;
               }
            }

            return null;
         }
      }

      int getContentViewLayoutResource() {
         return this.mBuilder.getContentView() != null ? layout.notification_template_media_custom : super.getContentViewLayoutResource();
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            RemoteViews innerView = this.mBuilder.getBigContentView() != null ? this.mBuilder.getBigContentView() : this.mBuilder.getContentView();
            if (innerView == null) {
               return null;
            } else {
               RemoteViews bigContentView = this.generateBigContentView();
               this.buildIntoRemoteViews(bigContentView, innerView);
               if (VERSION.SDK_INT >= 21) {
                  this.setBackgroundColor(bigContentView);
               }

               return bigContentView;
            }
         }
      }

      int getBigContentViewLayoutResource(int actionCount) {
         return actionCount <= 3 ? layout.notification_template_big_media_narrow_custom : layout.notification_template_big_media_custom;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            RemoteViews innerView = this.mBuilder.getHeadsUpContentView() != null ? this.mBuilder.getHeadsUpContentView() : this.mBuilder.getContentView();
            if (innerView == null) {
               return null;
            } else {
               RemoteViews headsUpContentView = this.generateBigContentView();
               this.buildIntoRemoteViews(headsUpContentView, innerView);
               if (VERSION.SDK_INT >= 21) {
                  this.setBackgroundColor(headsUpContentView);
               }

               return headsUpContentView;
            }
         }
      }

      private void setBackgroundColor(RemoteViews views) {
         int color = this.mBuilder.getColor() != 0 ? this.mBuilder.getColor() : this.mBuilder.mContext.getResources().getColor(color.notification_material_background_media_default_color);
         views.setInt(id.status_bar_latest_event_content, "setBackgroundColor", color);
      }
   }

   public static class MediaStyle extends Style {
      private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
      private static final int MAX_MEDIA_BUTTONS = 5;
      int[] mActionsToShowInCompact = null;
      MediaSessionCompat.Token mToken;
      boolean mShowCancelButton;
      PendingIntent mCancelButtonIntent;

      public static MediaSessionCompat.Token getMediaSession(Notification notification) {
         Bundle extras = android.support.v4.app.NotificationCompat.getExtras(notification);
         if (extras != null) {
            if (VERSION.SDK_INT >= 21) {
               Object tokenInner = extras.getParcelable("android.mediaSession");
               if (tokenInner != null) {
                  return MediaSessionCompat.Token.fromToken(tokenInner);
               }
            } else {
               IBinder tokenInner = BundleCompat.getBinder(extras, "android.mediaSession");
               if (tokenInner != null) {
                  Parcel p = Parcel.obtain();
                  p.writeStrongBinder(tokenInner);
                  p.setDataPosition(0);
                  MediaSessionCompat.Token token = (MediaSessionCompat.Token)MediaSessionCompat.Token.CREATOR.createFromParcel(p);
                  p.recycle();
                  return token;
               }
            }
         }

         return null;
      }

      public MediaStyle() {
      }

      public MediaStyle(Builder builder) {
         this.setBuilder(builder);
      }

      public NotificationCompat.MediaStyle setShowActionsInCompactView(int... actions) {
         this.mActionsToShowInCompact = actions;
         return this;
      }

      public NotificationCompat.MediaStyle setMediaSession(MediaSessionCompat.Token token) {
         this.mToken = token;
         return this;
      }

      public NotificationCompat.MediaStyle setShowCancelButton(boolean show) {
         if (VERSION.SDK_INT < 21) {
            this.mShowCancelButton = show;
         }

         return this;
      }

      public NotificationCompat.MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
         this.mCancelButtonIntent = pendingIntent;
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 21) {
            builder.getBuilder().setStyle(this.fillInMediaStyle(new android.app.Notification.MediaStyle()));
         } else if (this.mShowCancelButton) {
            builder.getBuilder().setOngoing(true);
         }

      }

      @RequiresApi(21)
      android.app.Notification.MediaStyle fillInMediaStyle(android.app.Notification.MediaStyle style) {
         if (this.mActionsToShowInCompact != null) {
            style.setShowActionsInCompactView(this.mActionsToShowInCompact);
         }

         if (this.mToken != null) {
            style.setMediaSession((Token)this.mToken.getToken());
         }

         return style;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
         return VERSION.SDK_INT >= 21 ? null : this.generateContentView();
      }

      RemoteViews generateContentView() {
         RemoteViews view = this.applyStandardTemplate(false, this.getContentViewLayoutResource(), true);
         int numActions = this.mBuilder.mActions.size();
         int numActionsInCompact = this.mActionsToShowInCompact == null ? 0 : Math.min(this.mActionsToShowInCompact.length, 3);
         view.removeAllViews(id.media_actions);
         if (numActionsInCompact > 0) {
            for(int i = 0; i < numActionsInCompact; ++i) {
               if (i >= numActions) {
                  throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", i, numActions - 1));
               }

               Action action = (Action)this.mBuilder.mActions.get(this.mActionsToShowInCompact[i]);
               RemoteViews button = this.generateMediaActionButton(action);
               view.addView(id.media_actions, button);
            }
         }

         if (this.mShowCancelButton) {
            view.setViewVisibility(id.end_padder, 8);
            view.setViewVisibility(id.cancel_action, 0);
            view.setOnClickPendingIntent(id.cancel_action, this.mCancelButtonIntent);
            view.setInt(id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(integer.cancel_button_image_alpha));
         } else {
            view.setViewVisibility(id.end_padder, 0);
            view.setViewVisibility(id.cancel_action, 8);
         }

         return view;
      }

      private RemoteViews generateMediaActionButton(Action action) {
         boolean tombstone = action.getActionIntent() == null;
         RemoteViews button = new RemoteViews(this.mBuilder.mContext.getPackageName(), layout.notification_media_action);
         button.setImageViewResource(id.action0, action.getIcon());
         if (!tombstone) {
            button.setOnClickPendingIntent(id.action0, action.getActionIntent());
         }

         if (VERSION.SDK_INT >= 15) {
            button.setContentDescription(id.action0, action.getTitle());
         }

         return button;
      }

      int getContentViewLayoutResource() {
         return layout.notification_template_media;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
         return VERSION.SDK_INT >= 21 ? null : this.generateBigContentView();
      }

      RemoteViews generateBigContentView() {
         int actionCount = Math.min(this.mBuilder.mActions.size(), 5);
         RemoteViews big = this.applyStandardTemplate(false, this.getBigContentViewLayoutResource(actionCount), false);
         big.removeAllViews(id.media_actions);
         if (actionCount > 0) {
            for(int i = 0; i < actionCount; ++i) {
               RemoteViews button = this.generateMediaActionButton((Action)this.mBuilder.mActions.get(i));
               big.addView(id.media_actions, button);
            }
         }

         if (this.mShowCancelButton) {
            big.setViewVisibility(id.cancel_action, 0);
            big.setInt(id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(integer.cancel_button_image_alpha));
            big.setOnClickPendingIntent(id.cancel_action, this.mCancelButtonIntent);
         } else {
            big.setViewVisibility(id.cancel_action, 8);
         }

         return big;
      }

      int getBigContentViewLayoutResource(int actionCount) {
         return actionCount <= 3 ? layout.notification_template_big_media_narrow : layout.notification_template_big_media;
      }
   }
}
