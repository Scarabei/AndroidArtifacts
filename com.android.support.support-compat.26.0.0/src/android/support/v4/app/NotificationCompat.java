package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.compat.R.color;
import android.support.compat.R.dimen;
import android.support.compat.R.drawable;
import android.support.compat.R.id;
import android.support.compat.R.integer;
import android.support.compat.R.layout;
import android.support.compat.R.string;
import android.support.v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NotificationCompat {
   public static final int DEFAULT_ALL = -1;
   public static final int DEFAULT_SOUND = 1;
   public static final int DEFAULT_VIBRATE = 2;
   public static final int DEFAULT_LIGHTS = 4;
   public static final int STREAM_DEFAULT = -1;
   public static final int FLAG_SHOW_LIGHTS = 1;
   public static final int FLAG_ONGOING_EVENT = 2;
   public static final int FLAG_INSISTENT = 4;
   public static final int FLAG_ONLY_ALERT_ONCE = 8;
   public static final int FLAG_AUTO_CANCEL = 16;
   public static final int FLAG_NO_CLEAR = 32;
   public static final int FLAG_FOREGROUND_SERVICE = 64;
   /** @deprecated */
   @Deprecated
   public static final int FLAG_HIGH_PRIORITY = 128;
   public static final int FLAG_LOCAL_ONLY = 256;
   public static final int FLAG_GROUP_SUMMARY = 512;
   public static final int PRIORITY_DEFAULT = 0;
   public static final int PRIORITY_LOW = -1;
   public static final int PRIORITY_MIN = -2;
   public static final int PRIORITY_HIGH = 1;
   public static final int PRIORITY_MAX = 2;
   public static final String EXTRA_TITLE = "android.title";
   public static final String EXTRA_TITLE_BIG = "android.title.big";
   public static final String EXTRA_TEXT = "android.text";
   public static final String EXTRA_SUB_TEXT = "android.subText";
   public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
   public static final String EXTRA_INFO_TEXT = "android.infoText";
   public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
   public static final String EXTRA_BIG_TEXT = "android.bigText";
   public static final String EXTRA_SMALL_ICON = "android.icon";
   public static final String EXTRA_LARGE_ICON = "android.largeIcon";
   public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
   public static final String EXTRA_PROGRESS = "android.progress";
   public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
   public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
   public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
   public static final String EXTRA_SHOW_WHEN = "android.showWhen";
   public static final String EXTRA_PICTURE = "android.picture";
   public static final String EXTRA_TEXT_LINES = "android.textLines";
   public static final String EXTRA_TEMPLATE = "android.template";
   public static final String EXTRA_PEOPLE = "android.people";
   public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
   public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
   public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
   public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
   public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
   public static final String EXTRA_MESSAGES = "android.messages";
   public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
   @ColorInt
   public static final int COLOR_DEFAULT = 0;
   public static final int VISIBILITY_PUBLIC = 1;
   public static final int VISIBILITY_PRIVATE = 0;
   public static final int VISIBILITY_SECRET = -1;
   public static final String CATEGORY_CALL = "call";
   public static final String CATEGORY_MESSAGE = "msg";
   public static final String CATEGORY_EMAIL = "email";
   public static final String CATEGORY_EVENT = "event";
   public static final String CATEGORY_PROMO = "promo";
   public static final String CATEGORY_ALARM = "alarm";
   public static final String CATEGORY_PROGRESS = "progress";
   public static final String CATEGORY_SOCIAL = "social";
   public static final String CATEGORY_ERROR = "err";
   public static final String CATEGORY_TRANSPORT = "transport";
   public static final String CATEGORY_SYSTEM = "sys";
   public static final String CATEGORY_SERVICE = "service";
   public static final String CATEGORY_REMINDER = "reminder";
   public static final String CATEGORY_RECOMMENDATION = "recommendation";
   public static final String CATEGORY_STATUS = "status";
   public static final int BADGE_ICON_NONE = 0;
   public static final int BADGE_ICON_SMALL = 1;
   public static final int BADGE_ICON_LARGE = 2;
   public static final int GROUP_ALERT_ALL = 0;
   public static final int GROUP_ALERT_SUMMARY = 1;
   public static final int GROUP_ALERT_CHILDREN = 2;
   static final NotificationCompat.NotificationCompatImpl IMPL;

   static void addActionsToBuilder(NotificationBuilderWithActions builder, ArrayList actions) {
      Iterator var2 = actions.iterator();

      while(var2.hasNext()) {
         NotificationCompat.Action action = (NotificationCompat.Action)var2.next();
         builder.addAction(action);
      }

   }

   static Notification[] getNotificationArrayFromBundle(Bundle bundle, String key) {
      Parcelable[] array = bundle.getParcelableArray(key);
      if (!(array instanceof Notification[]) && array != null) {
         Notification[] typedArray = new Notification[array.length];

         for(int i = 0; i < array.length; ++i) {
            typedArray[i] = (Notification)array[i];
         }

         bundle.putParcelableArray(key, typedArray);
         return typedArray;
      } else {
         return (Notification[])((Notification[])array);
      }
   }

   public static Bundle getExtras(Notification notification) {
      if (VERSION.SDK_INT >= 19) {
         return notification.extras;
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification) : null;
      }
   }

   public static int getActionCount(Notification notification) {
      if (VERSION.SDK_INT >= 19) {
         return notification.actions != null ? notification.actions.length : 0;
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getActionCount(notification) : 0;
      }
   }

   public static NotificationCompat.Action getAction(Notification notification, int actionIndex) {
      return IMPL.getAction(notification, actionIndex);
   }

   public static String getCategory(Notification notification) {
      return VERSION.SDK_INT >= 21 ? notification.category : null;
   }

   public static boolean getLocalOnly(Notification notification) {
      if (VERSION.SDK_INT >= 20) {
         return (notification.flags & 256) != 0;
      } else if (VERSION.SDK_INT >= 19) {
         return notification.extras.getBoolean("android.support.localOnly");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification).getBoolean("android.support.localOnly") : false;
      }
   }

   public static String getGroup(Notification notification) {
      if (VERSION.SDK_INT >= 20) {
         return notification.getGroup();
      } else if (VERSION.SDK_INT >= 19) {
         return notification.extras.getString("android.support.groupKey");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification).getString("android.support.groupKey") : null;
      }
   }

   public static boolean isGroupSummary(Notification notification) {
      if (VERSION.SDK_INT >= 20) {
         return (notification.flags & 512) != 0;
      } else if (VERSION.SDK_INT >= 19) {
         return notification.extras.getBoolean("android.support.isGroupSummary");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification).getBoolean("android.support.isGroupSummary") : false;
      }
   }

   public static String getSortKey(Notification notification) {
      if (VERSION.SDK_INT >= 20) {
         return notification.getSortKey();
      } else if (VERSION.SDK_INT >= 19) {
         return notification.extras.getString("android.support.sortKey");
      } else {
         return VERSION.SDK_INT >= 16 ? NotificationCompatJellybean.getExtras(notification).getString("android.support.sortKey") : null;
      }
   }

   public static String getChannelId(Notification notification) {
      return VERSION.SDK_INT >= 26 ? notification.getChannelId() : null;
   }

   public static long getTimeoutAfter(Notification notification) {
      return VERSION.SDK_INT >= 26 ? notification.getTimeoutAfter() : 0L;
   }

   public static int getBadgeIconType(Notification notification) {
      return VERSION.SDK_INT >= 26 ? notification.getBadgeIconType() : 0;
   }

   public static String getShortcutId(Notification notification) {
      return VERSION.SDK_INT >= 26 ? notification.getShortcutId() : null;
   }

   public static int getGroupAlertBehavior(Notification notification) {
      return VERSION.SDK_INT >= 26 ? notification.getGroupAlertBehavior() : 0;
   }

   static {
      if (VERSION.SDK_INT >= 26) {
         IMPL = new NotificationCompat.NotificationCompatApi26Impl();
      } else if (VERSION.SDK_INT >= 24) {
         IMPL = new NotificationCompat.NotificationCompatApi24Impl();
      } else if (VERSION.SDK_INT >= 21) {
         IMPL = new NotificationCompat.NotificationCompatApi21Impl();
      } else if (VERSION.SDK_INT >= 20) {
         IMPL = new NotificationCompat.NotificationCompatApi20Impl();
      } else if (VERSION.SDK_INT >= 19) {
         IMPL = new NotificationCompat.NotificationCompatApi19Impl();
      } else if (VERSION.SDK_INT >= 16) {
         IMPL = new NotificationCompat.NotificationCompatApi16Impl();
      } else {
         IMPL = new NotificationCompat.NotificationCompatBaseImpl();
      }

   }

   public static final class CarExtender implements NotificationCompat.Extender {
      private static final String TAG = "CarExtender";
      private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
      private static final String EXTRA_LARGE_ICON = "large_icon";
      private static final String EXTRA_CONVERSATION = "car_conversation";
      private static final String EXTRA_COLOR = "app_color";
      private Bitmap mLargeIcon;
      private NotificationCompat.CarExtender.UnreadConversation mUnreadConversation;
      private int mColor = 0;

      public CarExtender() {
      }

      public CarExtender(Notification notification) {
         if (VERSION.SDK_INT >= 21) {
            Bundle carBundle = NotificationCompat.getExtras(notification) == null ? null : NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
            if (carBundle != null) {
               this.mLargeIcon = (Bitmap)carBundle.getParcelable("large_icon");
               this.mColor = carBundle.getInt("app_color", 0);
               Bundle b = carBundle.getBundle("car_conversation");
               this.mUnreadConversation = (NotificationCompat.CarExtender.UnreadConversation)NotificationCompat.IMPL.getUnreadConversationFromBundle(b, NotificationCompat.CarExtender.UnreadConversation.FACTORY, RemoteInput.FACTORY);
            }

         }
      }

      public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
         if (VERSION.SDK_INT < 21) {
            return builder;
         } else {
            Bundle carExtensions = new Bundle();
            if (this.mLargeIcon != null) {
               carExtensions.putParcelable("large_icon", this.mLargeIcon);
            }

            if (this.mColor != 0) {
               carExtensions.putInt("app_color", this.mColor);
            }

            if (this.mUnreadConversation != null) {
               Bundle b = NotificationCompat.IMPL.getBundleForUnreadConversation(this.mUnreadConversation);
               carExtensions.putBundle("car_conversation", b);
            }

            builder.getExtras().putBundle("android.car.EXTENSIONS", carExtensions);
            return builder;
         }
      }

      public NotificationCompat.CarExtender setColor(@ColorInt int color) {
         this.mColor = color;
         return this;
      }

      @ColorInt
      public int getColor() {
         return this.mColor;
      }

      public NotificationCompat.CarExtender setLargeIcon(Bitmap largeIcon) {
         this.mLargeIcon = largeIcon;
         return this;
      }

      public Bitmap getLargeIcon() {
         return this.mLargeIcon;
      }

      public NotificationCompat.CarExtender setUnreadConversation(NotificationCompat.CarExtender.UnreadConversation unreadConversation) {
         this.mUnreadConversation = unreadConversation;
         return this;
      }

      public NotificationCompat.CarExtender.UnreadConversation getUnreadConversation() {
         return this.mUnreadConversation;
      }

      public static class UnreadConversation extends NotificationCompatBase.UnreadConversation {
         private final String[] mMessages;
         private final RemoteInput mRemoteInput;
         private final PendingIntent mReplyPendingIntent;
         private final PendingIntent mReadPendingIntent;
         private final String[] mParticipants;
         private final long mLatestTimestamp;
         static final NotificationCompatBase.UnreadConversation.Factory FACTORY = new NotificationCompatBase.UnreadConversation.Factory() {
            public NotificationCompat.CarExtender.UnreadConversation build(String[] messages, RemoteInputCompatBase.RemoteInput remoteInput, PendingIntent replyPendingIntent, PendingIntent readPendingIntent, String[] participants, long latestTimestamp) {
               return new NotificationCompat.CarExtender.UnreadConversation(messages, (RemoteInput)remoteInput, replyPendingIntent, readPendingIntent, participants, latestTimestamp);
            }
         };

         UnreadConversation(String[] messages, RemoteInput remoteInput, PendingIntent replyPendingIntent, PendingIntent readPendingIntent, String[] participants, long latestTimestamp) {
            this.mMessages = messages;
            this.mRemoteInput = remoteInput;
            this.mReadPendingIntent = readPendingIntent;
            this.mReplyPendingIntent = replyPendingIntent;
            this.mParticipants = participants;
            this.mLatestTimestamp = latestTimestamp;
         }

         public String[] getMessages() {
            return this.mMessages;
         }

         public RemoteInput getRemoteInput() {
            return this.mRemoteInput;
         }

         public PendingIntent getReplyPendingIntent() {
            return this.mReplyPendingIntent;
         }

         public PendingIntent getReadPendingIntent() {
            return this.mReadPendingIntent;
         }

         public String[] getParticipants() {
            return this.mParticipants;
         }

         public String getParticipant() {
            return this.mParticipants.length > 0 ? this.mParticipants[0] : null;
         }

         public long getLatestTimestamp() {
            return this.mLatestTimestamp;
         }

         public static class Builder {
            private final List mMessages = new ArrayList();
            private final String mParticipant;
            private RemoteInput mRemoteInput;
            private PendingIntent mReadPendingIntent;
            private PendingIntent mReplyPendingIntent;
            private long mLatestTimestamp;

            public Builder(String name) {
               this.mParticipant = name;
            }

            public NotificationCompat.CarExtender.UnreadConversation.Builder addMessage(String message) {
               this.mMessages.add(message);
               return this;
            }

            public NotificationCompat.CarExtender.UnreadConversation.Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
               this.mRemoteInput = remoteInput;
               this.mReplyPendingIntent = pendingIntent;
               return this;
            }

            public NotificationCompat.CarExtender.UnreadConversation.Builder setReadPendingIntent(PendingIntent pendingIntent) {
               this.mReadPendingIntent = pendingIntent;
               return this;
            }

            public NotificationCompat.CarExtender.UnreadConversation.Builder setLatestTimestamp(long timestamp) {
               this.mLatestTimestamp = timestamp;
               return this;
            }

            public NotificationCompat.CarExtender.UnreadConversation build() {
               String[] messages = (String[])this.mMessages.toArray(new String[this.mMessages.size()]);
               String[] participants = new String[]{this.mParticipant};
               return new NotificationCompat.CarExtender.UnreadConversation(messages, this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, participants, this.mLatestTimestamp);
            }
         }
      }
   }

   public static final class WearableExtender implements NotificationCompat.Extender {
      public static final int UNSET_ACTION_INDEX = -1;
      public static final int SIZE_DEFAULT = 0;
      public static final int SIZE_XSMALL = 1;
      public static final int SIZE_SMALL = 2;
      public static final int SIZE_MEDIUM = 3;
      public static final int SIZE_LARGE = 4;
      public static final int SIZE_FULL_SCREEN = 5;
      public static final int SCREEN_TIMEOUT_SHORT = 0;
      public static final int SCREEN_TIMEOUT_LONG = -1;
      private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
      private static final String KEY_ACTIONS = "actions";
      private static final String KEY_FLAGS = "flags";
      private static final String KEY_DISPLAY_INTENT = "displayIntent";
      private static final String KEY_PAGES = "pages";
      private static final String KEY_BACKGROUND = "background";
      private static final String KEY_CONTENT_ICON = "contentIcon";
      private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
      private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
      private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
      private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
      private static final String KEY_GRAVITY = "gravity";
      private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
      private static final String KEY_DISMISSAL_ID = "dismissalId";
      private static final String KEY_BRIDGE_TAG = "bridgeTag";
      private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
      private static final int FLAG_HINT_HIDE_ICON = 2;
      private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
      private static final int FLAG_START_SCROLL_BOTTOM = 8;
      private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
      private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
      private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
      private static final int DEFAULT_FLAGS = 1;
      private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
      private static final int DEFAULT_GRAVITY = 80;
      private ArrayList mActions = new ArrayList();
      private int mFlags = 1;
      private PendingIntent mDisplayIntent;
      private ArrayList mPages = new ArrayList();
      private Bitmap mBackground;
      private int mContentIcon;
      private int mContentIconGravity = 8388613;
      private int mContentActionIndex = -1;
      private int mCustomSizePreset = 0;
      private int mCustomContentHeight;
      private int mGravity = 80;
      private int mHintScreenTimeout;
      private String mDismissalId;
      private String mBridgeTag;

      public WearableExtender() {
      }

      public WearableExtender(Notification notification) {
         Bundle extras = NotificationCompat.getExtras(notification);
         Bundle wearableBundle = extras != null ? extras.getBundle("android.wearable.EXTENSIONS") : null;
         if (wearableBundle != null) {
            NotificationCompat.Action[] actions = NotificationCompat.IMPL.getActionsFromParcelableArrayList(wearableBundle.getParcelableArrayList("actions"));
            if (actions != null) {
               Collections.addAll(this.mActions, actions);
            }

            this.mFlags = wearableBundle.getInt("flags", 1);
            this.mDisplayIntent = (PendingIntent)wearableBundle.getParcelable("displayIntent");
            Notification[] pages = NotificationCompat.getNotificationArrayFromBundle(wearableBundle, "pages");
            if (pages != null) {
               Collections.addAll(this.mPages, pages);
            }

            this.mBackground = (Bitmap)wearableBundle.getParcelable("background");
            this.mContentIcon = wearableBundle.getInt("contentIcon");
            this.mContentIconGravity = wearableBundle.getInt("contentIconGravity", 8388613);
            this.mContentActionIndex = wearableBundle.getInt("contentActionIndex", -1);
            this.mCustomSizePreset = wearableBundle.getInt("customSizePreset", 0);
            this.mCustomContentHeight = wearableBundle.getInt("customContentHeight");
            this.mGravity = wearableBundle.getInt("gravity", 80);
            this.mHintScreenTimeout = wearableBundle.getInt("hintScreenTimeout");
            this.mDismissalId = wearableBundle.getString("dismissalId");
            this.mBridgeTag = wearableBundle.getString("bridgeTag");
         }

      }

      public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
         Bundle wearableBundle = new Bundle();
         if (!this.mActions.isEmpty()) {
            wearableBundle.putParcelableArrayList("actions", NotificationCompat.IMPL.getParcelableArrayListForActions((NotificationCompat.Action[])this.mActions.toArray(new NotificationCompat.Action[this.mActions.size()])));
         }

         if (this.mFlags != 1) {
            wearableBundle.putInt("flags", this.mFlags);
         }

         if (this.mDisplayIntent != null) {
            wearableBundle.putParcelable("displayIntent", this.mDisplayIntent);
         }

         if (!this.mPages.isEmpty()) {
            wearableBundle.putParcelableArray("pages", (Parcelable[])this.mPages.toArray(new Notification[this.mPages.size()]));
         }

         if (this.mBackground != null) {
            wearableBundle.putParcelable("background", this.mBackground);
         }

         if (this.mContentIcon != 0) {
            wearableBundle.putInt("contentIcon", this.mContentIcon);
         }

         if (this.mContentIconGravity != 8388613) {
            wearableBundle.putInt("contentIconGravity", this.mContentIconGravity);
         }

         if (this.mContentActionIndex != -1) {
            wearableBundle.putInt("contentActionIndex", this.mContentActionIndex);
         }

         if (this.mCustomSizePreset != 0) {
            wearableBundle.putInt("customSizePreset", this.mCustomSizePreset);
         }

         if (this.mCustomContentHeight != 0) {
            wearableBundle.putInt("customContentHeight", this.mCustomContentHeight);
         }

         if (this.mGravity != 80) {
            wearableBundle.putInt("gravity", this.mGravity);
         }

         if (this.mHintScreenTimeout != 0) {
            wearableBundle.putInt("hintScreenTimeout", this.mHintScreenTimeout);
         }

         if (this.mDismissalId != null) {
            wearableBundle.putString("dismissalId", this.mDismissalId);
         }

         if (this.mBridgeTag != null) {
            wearableBundle.putString("bridgeTag", this.mBridgeTag);
         }

         builder.getExtras().putBundle("android.wearable.EXTENSIONS", wearableBundle);
         return builder;
      }

      public NotificationCompat.WearableExtender clone() {
         NotificationCompat.WearableExtender that = new NotificationCompat.WearableExtender();
         that.mActions = new ArrayList(this.mActions);
         that.mFlags = this.mFlags;
         that.mDisplayIntent = this.mDisplayIntent;
         that.mPages = new ArrayList(this.mPages);
         that.mBackground = this.mBackground;
         that.mContentIcon = this.mContentIcon;
         that.mContentIconGravity = this.mContentIconGravity;
         that.mContentActionIndex = this.mContentActionIndex;
         that.mCustomSizePreset = this.mCustomSizePreset;
         that.mCustomContentHeight = this.mCustomContentHeight;
         that.mGravity = this.mGravity;
         that.mHintScreenTimeout = this.mHintScreenTimeout;
         that.mDismissalId = this.mDismissalId;
         that.mBridgeTag = this.mBridgeTag;
         return that;
      }

      public NotificationCompat.WearableExtender addAction(NotificationCompat.Action action) {
         this.mActions.add(action);
         return this;
      }

      public NotificationCompat.WearableExtender addActions(List actions) {
         this.mActions.addAll(actions);
         return this;
      }

      public NotificationCompat.WearableExtender clearActions() {
         this.mActions.clear();
         return this;
      }

      public List getActions() {
         return this.mActions;
      }

      public NotificationCompat.WearableExtender setDisplayIntent(PendingIntent intent) {
         this.mDisplayIntent = intent;
         return this;
      }

      public PendingIntent getDisplayIntent() {
         return this.mDisplayIntent;
      }

      public NotificationCompat.WearableExtender addPage(Notification page) {
         this.mPages.add(page);
         return this;
      }

      public NotificationCompat.WearableExtender addPages(List pages) {
         this.mPages.addAll(pages);
         return this;
      }

      public NotificationCompat.WearableExtender clearPages() {
         this.mPages.clear();
         return this;
      }

      public List getPages() {
         return this.mPages;
      }

      public NotificationCompat.WearableExtender setBackground(Bitmap background) {
         this.mBackground = background;
         return this;
      }

      public Bitmap getBackground() {
         return this.mBackground;
      }

      public NotificationCompat.WearableExtender setContentIcon(int icon) {
         this.mContentIcon = icon;
         return this;
      }

      public int getContentIcon() {
         return this.mContentIcon;
      }

      public NotificationCompat.WearableExtender setContentIconGravity(int contentIconGravity) {
         this.mContentIconGravity = contentIconGravity;
         return this;
      }

      public int getContentIconGravity() {
         return this.mContentIconGravity;
      }

      public NotificationCompat.WearableExtender setContentAction(int actionIndex) {
         this.mContentActionIndex = actionIndex;
         return this;
      }

      public int getContentAction() {
         return this.mContentActionIndex;
      }

      public NotificationCompat.WearableExtender setGravity(int gravity) {
         this.mGravity = gravity;
         return this;
      }

      public int getGravity() {
         return this.mGravity;
      }

      public NotificationCompat.WearableExtender setCustomSizePreset(int sizePreset) {
         this.mCustomSizePreset = sizePreset;
         return this;
      }

      public int getCustomSizePreset() {
         return this.mCustomSizePreset;
      }

      public NotificationCompat.WearableExtender setCustomContentHeight(int height) {
         this.mCustomContentHeight = height;
         return this;
      }

      public int getCustomContentHeight() {
         return this.mCustomContentHeight;
      }

      public NotificationCompat.WearableExtender setStartScrollBottom(boolean startScrollBottom) {
         this.setFlag(8, startScrollBottom);
         return this;
      }

      public boolean getStartScrollBottom() {
         return (this.mFlags & 8) != 0;
      }

      public NotificationCompat.WearableExtender setContentIntentAvailableOffline(boolean contentIntentAvailableOffline) {
         this.setFlag(1, contentIntentAvailableOffline);
         return this;
      }

      public boolean getContentIntentAvailableOffline() {
         return (this.mFlags & 1) != 0;
      }

      public NotificationCompat.WearableExtender setHintHideIcon(boolean hintHideIcon) {
         this.setFlag(2, hintHideIcon);
         return this;
      }

      public boolean getHintHideIcon() {
         return (this.mFlags & 2) != 0;
      }

      public NotificationCompat.WearableExtender setHintShowBackgroundOnly(boolean hintShowBackgroundOnly) {
         this.setFlag(4, hintShowBackgroundOnly);
         return this;
      }

      public boolean getHintShowBackgroundOnly() {
         return (this.mFlags & 4) != 0;
      }

      public NotificationCompat.WearableExtender setHintAvoidBackgroundClipping(boolean hintAvoidBackgroundClipping) {
         this.setFlag(16, hintAvoidBackgroundClipping);
         return this;
      }

      public boolean getHintAvoidBackgroundClipping() {
         return (this.mFlags & 16) != 0;
      }

      public NotificationCompat.WearableExtender setHintScreenTimeout(int timeout) {
         this.mHintScreenTimeout = timeout;
         return this;
      }

      public int getHintScreenTimeout() {
         return this.mHintScreenTimeout;
      }

      public NotificationCompat.WearableExtender setHintAmbientBigPicture(boolean hintAmbientBigPicture) {
         this.setFlag(32, hintAmbientBigPicture);
         return this;
      }

      public boolean getHintAmbientBigPicture() {
         return (this.mFlags & 32) != 0;
      }

      public NotificationCompat.WearableExtender setHintContentIntentLaunchesActivity(boolean hintContentIntentLaunchesActivity) {
         this.setFlag(64, hintContentIntentLaunchesActivity);
         return this;
      }

      public boolean getHintContentIntentLaunchesActivity() {
         return (this.mFlags & 64) != 0;
      }

      public NotificationCompat.WearableExtender setDismissalId(String dismissalId) {
         this.mDismissalId = dismissalId;
         return this;
      }

      public String getDismissalId() {
         return this.mDismissalId;
      }

      public NotificationCompat.WearableExtender setBridgeTag(String bridgeTag) {
         this.mBridgeTag = bridgeTag;
         return this;
      }

      public String getBridgeTag() {
         return this.mBridgeTag;
      }

      private void setFlag(int mask, boolean value) {
         if (value) {
            this.mFlags |= mask;
         } else {
            this.mFlags &= ~mask;
         }

      }
   }

   public interface Extender {
      NotificationCompat.Builder extend(NotificationCompat.Builder var1);
   }

   public static class Action extends NotificationCompatBase.Action {
      final Bundle mExtras;
      private final RemoteInput[] mRemoteInputs;
      private final RemoteInput[] mDataOnlyRemoteInputs;
      private boolean mAllowGeneratedReplies;
      public int icon;
      public CharSequence title;
      public PendingIntent actionIntent;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public static final NotificationCompatBase.Action.Factory FACTORY = new NotificationCompatBase.Action.Factory() {
         public NotificationCompatBase.Action build(int icon, CharSequence title, PendingIntent actionIntent, Bundle extras, RemoteInputCompatBase.RemoteInput[] remoteInputs, RemoteInputCompatBase.RemoteInput[] dataOnlyRemoteInputs, boolean allowGeneratedReplies) {
            return new NotificationCompat.Action(icon, title, actionIntent, extras, (RemoteInput[])((RemoteInput[])remoteInputs), (RemoteInput[])((RemoteInput[])dataOnlyRemoteInputs), allowGeneratedReplies);
         }

         public NotificationCompat.Action[] newArray(int length) {
            return new NotificationCompat.Action[length];
         }
      };

      public Action(int icon, CharSequence title, PendingIntent intent) {
         this(icon, title, intent, new Bundle(), (RemoteInput[])null, (RemoteInput[])null, true);
      }

      Action(int icon, CharSequence title, PendingIntent intent, Bundle extras, RemoteInput[] remoteInputs, RemoteInput[] dataOnlyRemoteInputs, boolean allowGeneratedReplies) {
         this.icon = icon;
         this.title = NotificationCompat.Builder.limitCharSequenceLength(title);
         this.actionIntent = intent;
         this.mExtras = extras != null ? extras : new Bundle();
         this.mRemoteInputs = remoteInputs;
         this.mDataOnlyRemoteInputs = dataOnlyRemoteInputs;
         this.mAllowGeneratedReplies = allowGeneratedReplies;
      }

      public int getIcon() {
         return this.icon;
      }

      public CharSequence getTitle() {
         return this.title;
      }

      public PendingIntent getActionIntent() {
         return this.actionIntent;
      }

      public Bundle getExtras() {
         return this.mExtras;
      }

      public boolean getAllowGeneratedReplies() {
         return this.mAllowGeneratedReplies;
      }

      public RemoteInput[] getRemoteInputs() {
         return this.mRemoteInputs;
      }

      public RemoteInput[] getDataOnlyRemoteInputs() {
         return this.mDataOnlyRemoteInputs;
      }

      public static final class WearableExtender implements NotificationCompat.Action.Extender {
         private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
         private static final String KEY_FLAGS = "flags";
         private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
         private static final String KEY_CONFIRM_LABEL = "confirmLabel";
         private static final String KEY_CANCEL_LABEL = "cancelLabel";
         private static final int FLAG_AVAILABLE_OFFLINE = 1;
         private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
         private static final int FLAG_HINT_DISPLAY_INLINE = 4;
         private static final int DEFAULT_FLAGS = 1;
         private int mFlags = 1;
         private CharSequence mInProgressLabel;
         private CharSequence mConfirmLabel;
         private CharSequence mCancelLabel;

         public WearableExtender() {
         }

         public WearableExtender(NotificationCompat.Action action) {
            Bundle wearableBundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
            if (wearableBundle != null) {
               this.mFlags = wearableBundle.getInt("flags", 1);
               this.mInProgressLabel = wearableBundle.getCharSequence("inProgressLabel");
               this.mConfirmLabel = wearableBundle.getCharSequence("confirmLabel");
               this.mCancelLabel = wearableBundle.getCharSequence("cancelLabel");
            }

         }

         public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder builder) {
            Bundle wearableBundle = new Bundle();
            if (this.mFlags != 1) {
               wearableBundle.putInt("flags", this.mFlags);
            }

            if (this.mInProgressLabel != null) {
               wearableBundle.putCharSequence("inProgressLabel", this.mInProgressLabel);
            }

            if (this.mConfirmLabel != null) {
               wearableBundle.putCharSequence("confirmLabel", this.mConfirmLabel);
            }

            if (this.mCancelLabel != null) {
               wearableBundle.putCharSequence("cancelLabel", this.mCancelLabel);
            }

            builder.getExtras().putBundle("android.wearable.EXTENSIONS", wearableBundle);
            return builder;
         }

         public NotificationCompat.Action.WearableExtender clone() {
            NotificationCompat.Action.WearableExtender that = new NotificationCompat.Action.WearableExtender();
            that.mFlags = this.mFlags;
            that.mInProgressLabel = this.mInProgressLabel;
            that.mConfirmLabel = this.mConfirmLabel;
            that.mCancelLabel = this.mCancelLabel;
            return that;
         }

         public NotificationCompat.Action.WearableExtender setAvailableOffline(boolean availableOffline) {
            this.setFlag(1, availableOffline);
            return this;
         }

         public boolean isAvailableOffline() {
            return (this.mFlags & 1) != 0;
         }

         private void setFlag(int mask, boolean value) {
            if (value) {
               this.mFlags |= mask;
            } else {
               this.mFlags &= ~mask;
            }

         }

         public NotificationCompat.Action.WearableExtender setInProgressLabel(CharSequence label) {
            this.mInProgressLabel = label;
            return this;
         }

         public CharSequence getInProgressLabel() {
            return this.mInProgressLabel;
         }

         public NotificationCompat.Action.WearableExtender setConfirmLabel(CharSequence label) {
            this.mConfirmLabel = label;
            return this;
         }

         public CharSequence getConfirmLabel() {
            return this.mConfirmLabel;
         }

         public NotificationCompat.Action.WearableExtender setCancelLabel(CharSequence label) {
            this.mCancelLabel = label;
            return this;
         }

         public CharSequence getCancelLabel() {
            return this.mCancelLabel;
         }

         public NotificationCompat.Action.WearableExtender setHintLaunchesActivity(boolean hintLaunchesActivity) {
            this.setFlag(2, hintLaunchesActivity);
            return this;
         }

         public boolean getHintLaunchesActivity() {
            return (this.mFlags & 2) != 0;
         }

         public NotificationCompat.Action.WearableExtender setHintDisplayActionInline(boolean hintDisplayInline) {
            this.setFlag(4, hintDisplayInline);
            return this;
         }

         public boolean getHintDisplayActionInline() {
            return (this.mFlags & 4) != 0;
         }
      }

      public interface Extender {
         NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder var1);
      }

      public static final class Builder {
         private final int mIcon;
         private final CharSequence mTitle;
         private final PendingIntent mIntent;
         private boolean mAllowGeneratedReplies;
         private final Bundle mExtras;
         private ArrayList mRemoteInputs;

         public Builder(int icon, CharSequence title, PendingIntent intent) {
            this(icon, title, intent, new Bundle(), (RemoteInput[])null, true);
         }

         public Builder(NotificationCompat.Action action) {
            this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies());
         }

         private Builder(int icon, CharSequence title, PendingIntent intent, Bundle extras, RemoteInput[] remoteInputs, boolean allowGeneratedReplies) {
            this.mAllowGeneratedReplies = true;
            this.mIcon = icon;
            this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(title);
            this.mIntent = intent;
            this.mExtras = extras;
            this.mRemoteInputs = remoteInputs == null ? null : new ArrayList(Arrays.asList(remoteInputs));
            this.mAllowGeneratedReplies = allowGeneratedReplies;
         }

         public NotificationCompat.Action.Builder addExtras(Bundle extras) {
            if (extras != null) {
               this.mExtras.putAll(extras);
            }

            return this;
         }

         public Bundle getExtras() {
            return this.mExtras;
         }

         public NotificationCompat.Action.Builder addRemoteInput(RemoteInput remoteInput) {
            if (this.mRemoteInputs == null) {
               this.mRemoteInputs = new ArrayList();
            }

            this.mRemoteInputs.add(remoteInput);
            return this;
         }

         public NotificationCompat.Action.Builder setAllowGeneratedReplies(boolean allowGeneratedReplies) {
            this.mAllowGeneratedReplies = allowGeneratedReplies;
            return this;
         }

         public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Extender extender) {
            extender.extend(this);
            return this;
         }

         public NotificationCompat.Action build() {
            List dataOnlyInputs = new ArrayList();
            List textInputs = new ArrayList();
            if (this.mRemoteInputs != null) {
               Iterator var3 = this.mRemoteInputs.iterator();

               while(var3.hasNext()) {
                  RemoteInput input = (RemoteInput)var3.next();
                  if (input.isDataOnly()) {
                     dataOnlyInputs.add(input);
                  } else {
                     textInputs.add(input);
                  }
               }
            }

            RemoteInput[] dataOnlyInputsArr = dataOnlyInputs.isEmpty() ? null : (RemoteInput[])dataOnlyInputs.toArray(new RemoteInput[dataOnlyInputs.size()]);
            RemoteInput[] textInputsArr = textInputs.isEmpty() ? null : (RemoteInput[])textInputs.toArray(new RemoteInput[textInputs.size()]);
            return new NotificationCompat.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, textInputsArr, dataOnlyInputsArr, this.mAllowGeneratedReplies);
         }
      }
   }

   public static class DecoratedCustomViewStyle extends NotificationCompat.Style {
      private static final int MAX_ACTION_BUTTONS = 3;

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            builder.getBuilder().setStyle(new android.app.Notification.DecoratedCustomViewStyle());
         }

      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            return this.mBuilder.getContentView() == null ? null : this.createRemoteViews(this.mBuilder.getContentView(), false);
         }
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            RemoteViews bigContentView = this.mBuilder.getBigContentView();
            RemoteViews innerView = bigContentView != null ? bigContentView : this.mBuilder.getContentView();
            return innerView == null ? null : this.createRemoteViews(innerView, true);
         }
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            return null;
         } else {
            RemoteViews headsUp = this.mBuilder.getHeadsUpContentView();
            RemoteViews innerView = headsUp != null ? headsUp : this.mBuilder.getContentView();
            return headsUp == null ? null : this.createRemoteViews(innerView, true);
         }
      }

      private RemoteViews createRemoteViews(RemoteViews innerView, boolean showActions) {
         RemoteViews remoteViews = this.applyStandardTemplate(true, layout.notification_template_custom_big, false);
         remoteViews.removeAllViews(id.actions);
         boolean actionsVisible = false;
         int numActions;
         if (showActions && this.mBuilder.mActions != null) {
            numActions = Math.min(this.mBuilder.mActions.size(), 3);
            if (numActions > 0) {
               actionsVisible = true;

               for(int i = 0; i < numActions; ++i) {
                  RemoteViews button = this.generateActionButton((NotificationCompat.Action)this.mBuilder.mActions.get(i));
                  remoteViews.addView(id.actions, button);
               }
            }
         }

         numActions = actionsVisible ? 0 : 8;
         remoteViews.setViewVisibility(id.actions, numActions);
         remoteViews.setViewVisibility(id.action_divider, numActions);
         this.buildIntoRemoteViews(remoteViews, innerView);
         return remoteViews;
      }

      private RemoteViews generateActionButton(NotificationCompat.Action action) {
         boolean tombstone = action.actionIntent == null;
         RemoteViews button = new RemoteViews(this.mBuilder.mContext.getPackageName(), tombstone ? layout.notification_action_tombstone : layout.notification_action);
         button.setImageViewBitmap(id.action_image, this.createColoredBitmap(action.getIcon(), this.mBuilder.mContext.getResources().getColor(color.notification_action_color_filter)));
         button.setTextViewText(id.action_text, action.title);
         if (!tombstone) {
            button.setOnClickPendingIntent(id.action_container, action.actionIntent);
         }

         if (VERSION.SDK_INT >= 15) {
            button.setContentDescription(id.action_container, action.title);
         }

         return button;
      }
   }

   public static class InboxStyle extends NotificationCompat.Style {
      private ArrayList mTexts = new ArrayList();

      public InboxStyle() {
      }

      public InboxStyle(NotificationCompat.Builder builder) {
         this.setBuilder(builder);
      }

      public NotificationCompat.InboxStyle setBigContentTitle(CharSequence title) {
         this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(title);
         return this;
      }

      public NotificationCompat.InboxStyle setSummaryText(CharSequence cs) {
         this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(cs);
         this.mSummaryTextSet = true;
         return this;
      }

      public NotificationCompat.InboxStyle addLine(CharSequence cs) {
         this.mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(cs));
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 16) {
            NotificationCompatJellybean.addInboxStyle(builder, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mTexts);
         }

      }
   }

   public static class MessagingStyle extends NotificationCompat.Style {
      public static final int MAXIMUM_RETAINED_MESSAGES = 25;
      CharSequence mUserDisplayName;
      CharSequence mConversationTitle;
      List mMessages = new ArrayList();

      MessagingStyle() {
      }

      public MessagingStyle(@NonNull CharSequence userDisplayName) {
         this.mUserDisplayName = userDisplayName;
      }

      public CharSequence getUserDisplayName() {
         return this.mUserDisplayName;
      }

      public NotificationCompat.MessagingStyle setConversationTitle(CharSequence conversationTitle) {
         this.mConversationTitle = conversationTitle;
         return this;
      }

      public CharSequence getConversationTitle() {
         return this.mConversationTitle;
      }

      public NotificationCompat.MessagingStyle addMessage(CharSequence text, long timestamp, CharSequence sender) {
         this.mMessages.add(new NotificationCompat.MessagingStyle.Message(text, timestamp, sender));
         if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
         }

         return this;
      }

      public NotificationCompat.MessagingStyle addMessage(NotificationCompat.MessagingStyle.Message message) {
         this.mMessages.add(message);
         if (this.mMessages.size() > 25) {
            this.mMessages.remove(0);
         }

         return this;
      }

      public List getMessages() {
         return this.mMessages;
      }

      public static NotificationCompat.MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
         Bundle extras = NotificationCompat.getExtras(notification);
         NotificationCompat.MessagingStyle style;
         if (extras != null && !extras.containsKey("android.selfDisplayName")) {
            style = null;
         } else {
            try {
               style = new NotificationCompat.MessagingStyle();
               style.restoreFromCompatExtras(extras);
            } catch (ClassCastException var4) {
               style = null;
            }
         }

         return style;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 24) {
            List texts = new ArrayList();
            List timestamps = new ArrayList();
            List senders = new ArrayList();
            List dataMimeTypes = new ArrayList();
            List dataUris = new ArrayList();
            Iterator var7 = this.mMessages.iterator();

            while(var7.hasNext()) {
               NotificationCompat.MessagingStyle.Message message = (NotificationCompat.MessagingStyle.Message)var7.next();
               texts.add(message.getText());
               timestamps.add(message.getTimestamp());
               senders.add(message.getSender());
               dataMimeTypes.add(message.getDataMimeType());
               dataUris.add(message.getDataUri());
            }

            NotificationCompatApi24.addMessagingStyle(builder, this.mUserDisplayName, this.mConversationTitle, texts, timestamps, senders, dataMimeTypes, dataUris);
         } else {
            NotificationCompat.MessagingStyle.Message latestIncomingMessage = this.findLatestIncomingMessage();
            if (this.mConversationTitle != null) {
               builder.getBuilder().setContentTitle(this.mConversationTitle);
            } else if (latestIncomingMessage != null) {
               builder.getBuilder().setContentTitle(latestIncomingMessage.getSender());
            }

            if (latestIncomingMessage != null) {
               builder.getBuilder().setContentText(this.mConversationTitle != null ? this.makeMessageLine(latestIncomingMessage) : latestIncomingMessage.getText());
            }

            if (VERSION.SDK_INT >= 16) {
               SpannableStringBuilder completeMessage = new SpannableStringBuilder();
               boolean showNames = this.mConversationTitle != null || this.hasMessagesWithoutSender();

               for(int i = this.mMessages.size() - 1; i >= 0; --i) {
                  NotificationCompat.MessagingStyle.Message message = (NotificationCompat.MessagingStyle.Message)this.mMessages.get(i);
                  CharSequence line = showNames ? this.makeMessageLine(message) : message.getText();
                  if (i != this.mMessages.size() - 1) {
                     completeMessage.insert(0, "\n");
                  }

                  completeMessage.insert(0, line);
               }

               NotificationCompatJellybean.addBigTextStyle(builder, (CharSequence)null, false, (CharSequence)null, completeMessage);
            }
         }

      }

      @Nullable
      private NotificationCompat.MessagingStyle.Message findLatestIncomingMessage() {
         for(int i = this.mMessages.size() - 1; i >= 0; --i) {
            NotificationCompat.MessagingStyle.Message message = (NotificationCompat.MessagingStyle.Message)this.mMessages.get(i);
            if (!TextUtils.isEmpty(message.getSender())) {
               return message;
            }
         }

         if (!this.mMessages.isEmpty()) {
            return (NotificationCompat.MessagingStyle.Message)this.mMessages.get(this.mMessages.size() - 1);
         } else {
            return null;
         }
      }

      private boolean hasMessagesWithoutSender() {
         for(int i = this.mMessages.size() - 1; i >= 0; --i) {
            NotificationCompat.MessagingStyle.Message message = (NotificationCompat.MessagingStyle.Message)this.mMessages.get(i);
            if (message.getSender() == null) {
               return true;
            }
         }

         return false;
      }

      private CharSequence makeMessageLine(NotificationCompat.MessagingStyle.Message message) {
         BidiFormatter bidi = BidiFormatter.getInstance();
         SpannableStringBuilder sb = new SpannableStringBuilder();
         boolean afterLollipop = VERSION.SDK_INT >= 21;
         int color = afterLollipop ? -16777216 : -1;
         CharSequence replyName = message.getSender();
         if (TextUtils.isEmpty(message.getSender())) {
            replyName = this.mUserDisplayName == null ? "" : this.mUserDisplayName;
            color = afterLollipop && this.mBuilder.getColor() != 0 ? this.mBuilder.getColor() : color;
         }

         CharSequence senderText = bidi.unicodeWrap((CharSequence)replyName);
         sb.append(senderText);
         sb.setSpan(this.makeFontColorSpan(color), sb.length() - senderText.length(), sb.length(), 33);
         CharSequence text = message.getText() == null ? "" : message.getText();
         sb.append("  ").append(bidi.unicodeWrap((CharSequence)text));
         return sb;
      }

      @NonNull
      private TextAppearanceSpan makeFontColorSpan(int color) {
         return new TextAppearanceSpan((String)null, 0, 0, ColorStateList.valueOf(color), (ColorStateList)null);
      }

      public void addCompatExtras(Bundle extras) {
         super.addCompatExtras(extras);
         if (this.mUserDisplayName != null) {
            extras.putCharSequence("android.selfDisplayName", this.mUserDisplayName);
         }

         if (this.mConversationTitle != null) {
            extras.putCharSequence("android.conversationTitle", this.mConversationTitle);
         }

         if (!this.mMessages.isEmpty()) {
            extras.putParcelableArray("android.messages", NotificationCompat.MessagingStyle.Message.getBundleArrayForMessages(this.mMessages));
         }

      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      protected void restoreFromCompatExtras(Bundle extras) {
         this.mMessages.clear();
         this.mUserDisplayName = extras.getString("android.selfDisplayName");
         this.mConversationTitle = extras.getString("android.conversationTitle");
         Parcelable[] parcelables = extras.getParcelableArray("android.messages");
         if (parcelables != null) {
            this.mMessages = NotificationCompat.MessagingStyle.Message.getMessagesFromBundleArray(parcelables);
         }

      }

      public static final class Message {
         static final String KEY_TEXT = "text";
         static final String KEY_TIMESTAMP = "time";
         static final String KEY_SENDER = "sender";
         static final String KEY_DATA_MIME_TYPE = "type";
         static final String KEY_DATA_URI = "uri";
         static final String KEY_EXTRAS_BUNDLE = "extras";
         private final CharSequence mText;
         private final long mTimestamp;
         private final CharSequence mSender;
         private Bundle mExtras = new Bundle();
         private String mDataMimeType;
         private Uri mDataUri;

         public Message(CharSequence text, long timestamp, CharSequence sender) {
            this.mText = text;
            this.mTimestamp = timestamp;
            this.mSender = sender;
         }

         public NotificationCompat.MessagingStyle.Message setData(String dataMimeType, Uri dataUri) {
            this.mDataMimeType = dataMimeType;
            this.mDataUri = dataUri;
            return this;
         }

         public CharSequence getText() {
            return this.mText;
         }

         public long getTimestamp() {
            return this.mTimestamp;
         }

         public Bundle getExtras() {
            return this.mExtras;
         }

         public CharSequence getSender() {
            return this.mSender;
         }

         public String getDataMimeType() {
            return this.mDataMimeType;
         }

         public Uri getDataUri() {
            return this.mDataUri;
         }

         private Bundle toBundle() {
            Bundle bundle = new Bundle();
            if (this.mText != null) {
               bundle.putCharSequence("text", this.mText);
            }

            bundle.putLong("time", this.mTimestamp);
            if (this.mSender != null) {
               bundle.putCharSequence("sender", this.mSender);
            }

            if (this.mDataMimeType != null) {
               bundle.putString("type", this.mDataMimeType);
            }

            if (this.mDataUri != null) {
               bundle.putParcelable("uri", this.mDataUri);
            }

            if (this.mExtras != null) {
               bundle.putBundle("extras", this.mExtras);
            }

            return bundle;
         }

         static Bundle[] getBundleArrayForMessages(List messages) {
            Bundle[] bundles = new Bundle[messages.size()];
            int N = messages.size();

            for(int i = 0; i < N; ++i) {
               bundles[i] = ((NotificationCompat.MessagingStyle.Message)messages.get(i)).toBundle();
            }

            return bundles;
         }

         static List getMessagesFromBundleArray(Parcelable[] bundles) {
            List messages = new ArrayList(bundles.length);

            for(int i = 0; i < bundles.length; ++i) {
               if (bundles[i] instanceof Bundle) {
                  NotificationCompat.MessagingStyle.Message message = getMessageFromBundle((Bundle)bundles[i]);
                  if (message != null) {
                     messages.add(message);
                  }
               }
            }

            return messages;
         }

         static NotificationCompat.MessagingStyle.Message getMessageFromBundle(Bundle bundle) {
            try {
               if (bundle.containsKey("text") && bundle.containsKey("time")) {
                  NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
                  if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                     message.setData(bundle.getString("type"), (Uri)bundle.getParcelable("uri"));
                  }

                  if (bundle.containsKey("extras")) {
                     message.getExtras().putAll(bundle.getBundle("extras"));
                  }

                  return message;
               } else {
                  return null;
               }
            } catch (ClassCastException var2) {
               return null;
            }
         }
      }
   }

   public static class BigTextStyle extends NotificationCompat.Style {
      private CharSequence mBigText;

      public BigTextStyle() {
      }

      public BigTextStyle(NotificationCompat.Builder builder) {
         this.setBuilder(builder);
      }

      public NotificationCompat.BigTextStyle setBigContentTitle(CharSequence title) {
         this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(title);
         return this;
      }

      public NotificationCompat.BigTextStyle setSummaryText(CharSequence cs) {
         this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(cs);
         this.mSummaryTextSet = true;
         return this;
      }

      public NotificationCompat.BigTextStyle bigText(CharSequence cs) {
         this.mBigText = NotificationCompat.Builder.limitCharSequenceLength(cs);
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 16) {
            NotificationCompatJellybean.addBigTextStyle(builder, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mBigText);
         }

      }
   }

   public static class BigPictureStyle extends NotificationCompat.Style {
      private Bitmap mPicture;
      private Bitmap mBigLargeIcon;
      private boolean mBigLargeIconSet;

      public BigPictureStyle() {
      }

      public BigPictureStyle(NotificationCompat.Builder builder) {
         this.setBuilder(builder);
      }

      public NotificationCompat.BigPictureStyle setBigContentTitle(CharSequence title) {
         this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(title);
         return this;
      }

      public NotificationCompat.BigPictureStyle setSummaryText(CharSequence cs) {
         this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(cs);
         this.mSummaryTextSet = true;
         return this;
      }

      public NotificationCompat.BigPictureStyle bigPicture(Bitmap b) {
         this.mPicture = b;
         return this;
      }

      public NotificationCompat.BigPictureStyle bigLargeIcon(Bitmap b) {
         this.mBigLargeIcon = b;
         this.mBigLargeIconSet = true;
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
         if (VERSION.SDK_INT >= 16) {
            NotificationCompatJellybean.addBigPictureStyle(builder, this.mBigContentTitle, this.mSummaryTextSet, this.mSummaryText, this.mPicture, this.mBigLargeIcon, this.mBigLargeIconSet);
         }

      }
   }

   public abstract static class Style {
      @RestrictTo({Scope.LIBRARY_GROUP})
      protected NotificationCompat.Builder mBuilder;
      CharSequence mBigContentTitle;
      CharSequence mSummaryText;
      boolean mSummaryTextSet = false;

      public void setBuilder(NotificationCompat.Builder builder) {
         if (this.mBuilder != builder) {
            this.mBuilder = builder;
            if (this.mBuilder != null) {
               this.mBuilder.setStyle(this);
            }
         }

      }

      public Notification build() {
         Notification notification = null;
         if (this.mBuilder != null) {
            notification = this.mBuilder.build();
         }

         return notification;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void apply(NotificationBuilderWithBuilderAccessor builder) {
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
         return null;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
         return null;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
         return null;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void addCompatExtras(Bundle extras) {
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      protected void restoreFromCompatExtras(Bundle extras) {
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews applyStandardTemplate(boolean showSmallIcon, int resId, boolean fitIn1U) {
         Resources res = this.mBuilder.mContext.getResources();
         RemoteViews contentView = new RemoteViews(this.mBuilder.mContext.getPackageName(), resId);
         boolean showLine3 = false;
         boolean showLine2 = false;
         boolean minPriority = this.mBuilder.getPriority() < -1;
         if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT < 21) {
            if (minPriority) {
               contentView.setInt(id.notification_background, "setBackgroundResource", drawable.notification_bg_low);
               contentView.setInt(id.icon, "setBackgroundResource", drawable.notification_template_icon_low_bg);
            } else {
               contentView.setInt(id.notification_background, "setBackgroundResource", drawable.notification_bg);
               contentView.setInt(id.icon, "setBackgroundResource", drawable.notification_template_icon_bg);
            }
         }

         int backgroundSize;
         int tooBig;
         Bitmap smallBit;
         if (this.mBuilder.mLargeIcon != null) {
            if (VERSION.SDK_INT >= 16) {
               contentView.setViewVisibility(id.icon, 0);
               contentView.setImageViewBitmap(id.icon, this.mBuilder.mLargeIcon);
            } else {
               contentView.setViewVisibility(id.icon, 8);
            }

            if (showSmallIcon && this.mBuilder.mNotification.icon != 0) {
               backgroundSize = res.getDimensionPixelSize(dimen.notification_right_icon_size);
               tooBig = backgroundSize - res.getDimensionPixelSize(dimen.notification_small_icon_background_padding) * 2;
               if (VERSION.SDK_INT >= 21) {
                  smallBit = this.createIconWithBackground(this.mBuilder.mNotification.icon, backgroundSize, tooBig, this.mBuilder.getColor());
                  contentView.setImageViewBitmap(id.right_icon, smallBit);
               } else {
                  contentView.setImageViewBitmap(id.right_icon, this.createColoredBitmap(this.mBuilder.mNotification.icon, -1));
               }

               contentView.setViewVisibility(id.right_icon, 0);
            }
         } else if (showSmallIcon && this.mBuilder.mNotification.icon != 0) {
            contentView.setViewVisibility(id.icon, 0);
            if (VERSION.SDK_INT >= 21) {
               backgroundSize = res.getDimensionPixelSize(dimen.notification_large_icon_width) - res.getDimensionPixelSize(dimen.notification_big_circle_margin);
               tooBig = res.getDimensionPixelSize(dimen.notification_small_icon_size_as_large);
               smallBit = this.createIconWithBackground(this.mBuilder.mNotification.icon, backgroundSize, tooBig, this.mBuilder.getColor());
               contentView.setImageViewBitmap(id.icon, smallBit);
            } else {
               contentView.setImageViewBitmap(id.icon, this.createColoredBitmap(this.mBuilder.mNotification.icon, -1));
            }
         }

         if (this.mBuilder.mContentTitle != null) {
            contentView.setTextViewText(id.title, this.mBuilder.mContentTitle);
         }

         if (this.mBuilder.mContentText != null) {
            contentView.setTextViewText(id.text, this.mBuilder.mContentText);
            showLine3 = true;
         }

         boolean hasRightSide = VERSION.SDK_INT < 21 && this.mBuilder.mLargeIcon != null;
         if (this.mBuilder.mContentInfo != null) {
            contentView.setTextViewText(id.info, this.mBuilder.mContentInfo);
            contentView.setViewVisibility(id.info, 0);
            showLine3 = true;
            hasRightSide = true;
         } else if (this.mBuilder.mNumber > 0) {
            tooBig = res.getInteger(integer.status_bar_notification_info_maxnum);
            if (this.mBuilder.mNumber > tooBig) {
               contentView.setTextViewText(id.info, res.getString(string.status_bar_notification_info_overflow));
            } else {
               NumberFormat f = NumberFormat.getIntegerInstance();
               contentView.setTextViewText(id.info, f.format((long)this.mBuilder.mNumber));
            }

            contentView.setViewVisibility(id.info, 0);
            showLine3 = true;
            hasRightSide = true;
         } else {
            contentView.setViewVisibility(id.info, 8);
         }

         if (this.mBuilder.mSubText != null && VERSION.SDK_INT >= 16) {
            contentView.setTextViewText(id.text, this.mBuilder.mSubText);
            if (this.mBuilder.mContentText != null) {
               contentView.setTextViewText(id.text2, this.mBuilder.mContentText);
               contentView.setViewVisibility(id.text2, 0);
               showLine2 = true;
            } else {
               contentView.setViewVisibility(id.text2, 8);
            }
         }

         if (showLine2 && VERSION.SDK_INT >= 16) {
            if (fitIn1U) {
               float subTextSize = (float)res.getDimensionPixelSize(dimen.notification_subtext_size);
               contentView.setTextViewTextSize(id.text, 0, subTextSize);
            }

            contentView.setViewPadding(id.line1, 0, 0, 0, 0);
         }

         if (this.mBuilder.getWhenIfShowing() != 0L) {
            if (this.mBuilder.mUseChronometer && VERSION.SDK_INT >= 16) {
               contentView.setViewVisibility(id.chronometer, 0);
               contentView.setLong(id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
               contentView.setBoolean(id.chronometer, "setStarted", true);
            } else {
               contentView.setViewVisibility(id.time, 0);
               contentView.setLong(id.time, "setTime", this.mBuilder.getWhenIfShowing());
            }

            hasRightSide = true;
         }

         contentView.setViewVisibility(id.right_side, hasRightSide ? 0 : 8);
         contentView.setViewVisibility(id.line3, showLine3 ? 0 : 8);
         return contentView;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public Bitmap createColoredBitmap(int iconId, int color) {
         return this.createColoredBitmap(iconId, color, 0);
      }

      private Bitmap createColoredBitmap(int iconId, int color, int size) {
         Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(iconId);
         int width = size == 0 ? drawable.getIntrinsicWidth() : size;
         int height = size == 0 ? drawable.getIntrinsicHeight() : size;
         Bitmap resultBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
         drawable.setBounds(0, 0, width, height);
         if (color != 0) {
            drawable.mutate().setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_IN));
         }

         Canvas canvas = new Canvas(resultBitmap);
         drawable.draw(canvas);
         return resultBitmap;
      }

      private Bitmap createIconWithBackground(int iconId, int size, int iconSize, int color) {
         Bitmap coloredBitmap = this.createColoredBitmap(drawable.notification_icon_background, color == 0 ? 0 : color, size);
         Canvas canvas = new Canvas(coloredBitmap);
         Drawable icon = this.mBuilder.mContext.getResources().getDrawable(iconId).mutate();
         icon.setFilterBitmap(true);
         int inset = (size - iconSize) / 2;
         icon.setBounds(inset, inset, iconSize + inset, iconSize + inset);
         icon.setColorFilter(new PorterDuffColorFilter(-1, Mode.SRC_ATOP));
         icon.draw(canvas);
         return coloredBitmap;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public void buildIntoRemoteViews(RemoteViews outerView, RemoteViews innerView) {
         this.hideNormalContent(outerView);
         outerView.removeAllViews(id.notification_main_column);
         outerView.addView(id.notification_main_column, innerView.clone());
         outerView.setViewVisibility(id.notification_main_column, 0);
         if (VERSION.SDK_INT >= 21) {
            outerView.setViewPadding(id.notification_main_column_container, 0, this.calculateTopPadding(), 0, 0);
         }

      }

      private void hideNormalContent(RemoteViews outerView) {
         outerView.setViewVisibility(id.title, 8);
         outerView.setViewVisibility(id.text2, 8);
         outerView.setViewVisibility(id.text, 8);
      }

      private int calculateTopPadding() {
         Resources resources = this.mBuilder.mContext.getResources();
         int padding = resources.getDimensionPixelSize(dimen.notification_top_pad);
         int largePadding = resources.getDimensionPixelSize(dimen.notification_top_pad_large_text);
         float fontScale = resources.getConfiguration().fontScale;
         float largeFactor = (constrain(fontScale, 1.0F, 1.3F) - 1.0F) / 0.29999995F;
         return Math.round((1.0F - largeFactor) * (float)padding + largeFactor * (float)largePadding);
      }

      private static float constrain(float amount, float low, float high) {
         return amount < low ? low : (amount > high ? high : amount);
      }
   }

   public static class Builder {
      private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public Context mContext;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public CharSequence mContentTitle;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public CharSequence mContentText;
      PendingIntent mContentIntent;
      PendingIntent mFullScreenIntent;
      RemoteViews mTickerView;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public Bitmap mLargeIcon;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public CharSequence mContentInfo;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public int mNumber;
      int mPriority;
      boolean mShowWhen;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public boolean mUseChronometer;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public NotificationCompat.Style mStyle;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public CharSequence mSubText;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public CharSequence[] mRemoteInputHistory;
      int mProgressMax;
      int mProgress;
      boolean mProgressIndeterminate;
      String mGroupKey;
      boolean mGroupSummary;
      String mSortKey;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public ArrayList mActions;
      boolean mLocalOnly;
      boolean mColorized;
      boolean mColorizedSet;
      String mCategory;
      Bundle mExtras;
      int mColor;
      int mVisibility;
      Notification mPublicVersion;
      RemoteViews mContentView;
      RemoteViews mBigContentView;
      RemoteViews mHeadsUpContentView;
      String mChannelId;
      int mBadgeIcon;
      String mShortcutId;
      long mTimeout;
      private int mGroupAlertBehavior;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public Notification mNotification;
      public ArrayList mPeople;

      public Builder(@NonNull Context context, @NonNull String channelId) {
         this.mShowWhen = true;
         this.mActions = new ArrayList();
         this.mLocalOnly = false;
         this.mColor = 0;
         this.mVisibility = 0;
         this.mBadgeIcon = 0;
         this.mGroupAlertBehavior = 0;
         this.mNotification = new Notification();
         this.mContext = context;
         this.mChannelId = channelId;
         this.mNotification.when = System.currentTimeMillis();
         this.mNotification.audioStreamType = -1;
         this.mPriority = 0;
         this.mPeople = new ArrayList();
      }

      /** @deprecated */
      @Deprecated
      public Builder(Context context) {
         this(context, (String)null);
      }

      public NotificationCompat.Builder setWhen(long when) {
         this.mNotification.when = when;
         return this;
      }

      public NotificationCompat.Builder setShowWhen(boolean show) {
         this.mShowWhen = show;
         return this;
      }

      public NotificationCompat.Builder setUsesChronometer(boolean b) {
         this.mUseChronometer = b;
         return this;
      }

      public NotificationCompat.Builder setSmallIcon(int icon) {
         this.mNotification.icon = icon;
         return this;
      }

      public NotificationCompat.Builder setSmallIcon(int icon, int level) {
         this.mNotification.icon = icon;
         this.mNotification.iconLevel = level;
         return this;
      }

      public NotificationCompat.Builder setContentTitle(CharSequence title) {
         this.mContentTitle = limitCharSequenceLength(title);
         return this;
      }

      public NotificationCompat.Builder setContentText(CharSequence text) {
         this.mContentText = limitCharSequenceLength(text);
         return this;
      }

      public NotificationCompat.Builder setSubText(CharSequence text) {
         this.mSubText = limitCharSequenceLength(text);
         return this;
      }

      public NotificationCompat.Builder setRemoteInputHistory(CharSequence[] text) {
         this.mRemoteInputHistory = text;
         return this;
      }

      public NotificationCompat.Builder setNumber(int number) {
         this.mNumber = number;
         return this;
      }

      public NotificationCompat.Builder setContentInfo(CharSequence info) {
         this.mContentInfo = limitCharSequenceLength(info);
         return this;
      }

      public NotificationCompat.Builder setProgress(int max, int progress, boolean indeterminate) {
         this.mProgressMax = max;
         this.mProgress = progress;
         this.mProgressIndeterminate = indeterminate;
         return this;
      }

      public NotificationCompat.Builder setContent(RemoteViews views) {
         this.mNotification.contentView = views;
         return this;
      }

      public NotificationCompat.Builder setContentIntent(PendingIntent intent) {
         this.mContentIntent = intent;
         return this;
      }

      public NotificationCompat.Builder setDeleteIntent(PendingIntent intent) {
         this.mNotification.deleteIntent = intent;
         return this;
      }

      public NotificationCompat.Builder setFullScreenIntent(PendingIntent intent, boolean highPriority) {
         this.mFullScreenIntent = intent;
         this.setFlag(128, highPriority);
         return this;
      }

      public NotificationCompat.Builder setTicker(CharSequence tickerText) {
         this.mNotification.tickerText = limitCharSequenceLength(tickerText);
         return this;
      }

      public NotificationCompat.Builder setTicker(CharSequence tickerText, RemoteViews views) {
         this.mNotification.tickerText = limitCharSequenceLength(tickerText);
         this.mTickerView = views;
         return this;
      }

      public NotificationCompat.Builder setLargeIcon(Bitmap icon) {
         this.mLargeIcon = icon;
         return this;
      }

      public NotificationCompat.Builder setSound(Uri sound) {
         this.mNotification.sound = sound;
         this.mNotification.audioStreamType = -1;
         return this;
      }

      public NotificationCompat.Builder setSound(Uri sound, int streamType) {
         this.mNotification.sound = sound;
         this.mNotification.audioStreamType = streamType;
         return this;
      }

      public NotificationCompat.Builder setVibrate(long[] pattern) {
         this.mNotification.vibrate = pattern;
         return this;
      }

      public NotificationCompat.Builder setLights(@ColorInt int argb, int onMs, int offMs) {
         this.mNotification.ledARGB = argb;
         this.mNotification.ledOnMS = onMs;
         this.mNotification.ledOffMS = offMs;
         boolean showLights = this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0;
         this.mNotification.flags = this.mNotification.flags & -2 | (showLights ? 1 : 0);
         return this;
      }

      public NotificationCompat.Builder setOngoing(boolean ongoing) {
         this.setFlag(2, ongoing);
         return this;
      }

      public NotificationCompat.Builder setColorized(boolean colorize) {
         this.mColorized = colorize;
         this.mColorizedSet = true;
         return this;
      }

      public NotificationCompat.Builder setOnlyAlertOnce(boolean onlyAlertOnce) {
         this.setFlag(8, onlyAlertOnce);
         return this;
      }

      public NotificationCompat.Builder setAutoCancel(boolean autoCancel) {
         this.setFlag(16, autoCancel);
         return this;
      }

      public NotificationCompat.Builder setLocalOnly(boolean b) {
         this.mLocalOnly = b;
         return this;
      }

      public NotificationCompat.Builder setCategory(String category) {
         this.mCategory = category;
         return this;
      }

      public NotificationCompat.Builder setDefaults(int defaults) {
         this.mNotification.defaults = defaults;
         if ((defaults & 4) != 0) {
            this.mNotification.flags |= 1;
         }

         return this;
      }

      private void setFlag(int mask, boolean value) {
         if (value) {
            this.mNotification.flags |= mask;
         } else {
            this.mNotification.flags &= ~mask;
         }

      }

      public NotificationCompat.Builder setPriority(int pri) {
         this.mPriority = pri;
         return this;
      }

      public NotificationCompat.Builder addPerson(String uri) {
         this.mPeople.add(uri);
         return this;
      }

      public NotificationCompat.Builder setGroup(String groupKey) {
         this.mGroupKey = groupKey;
         return this;
      }

      public NotificationCompat.Builder setGroupSummary(boolean isGroupSummary) {
         this.mGroupSummary = isGroupSummary;
         return this;
      }

      public NotificationCompat.Builder setSortKey(String sortKey) {
         this.mSortKey = sortKey;
         return this;
      }

      public NotificationCompat.Builder addExtras(Bundle extras) {
         if (extras != null) {
            if (this.mExtras == null) {
               this.mExtras = new Bundle(extras);
            } else {
               this.mExtras.putAll(extras);
            }
         }

         return this;
      }

      public NotificationCompat.Builder setExtras(Bundle extras) {
         this.mExtras = extras;
         return this;
      }

      public Bundle getExtras() {
         if (this.mExtras == null) {
            this.mExtras = new Bundle();
         }

         return this.mExtras;
      }

      public NotificationCompat.Builder addAction(int icon, CharSequence title, PendingIntent intent) {
         this.mActions.add(new NotificationCompat.Action(icon, title, intent));
         return this;
      }

      public NotificationCompat.Builder addAction(NotificationCompat.Action action) {
         this.mActions.add(action);
         return this;
      }

      public NotificationCompat.Builder setStyle(NotificationCompat.Style style) {
         if (this.mStyle != style) {
            this.mStyle = style;
            if (this.mStyle != null) {
               this.mStyle.setBuilder(this);
            }
         }

         return this;
      }

      public NotificationCompat.Builder setColor(@ColorInt int argb) {
         this.mColor = argb;
         return this;
      }

      public NotificationCompat.Builder setVisibility(int visibility) {
         this.mVisibility = visibility;
         return this;
      }

      public NotificationCompat.Builder setPublicVersion(Notification n) {
         this.mPublicVersion = n;
         return this;
      }

      public NotificationCompat.Builder setCustomContentView(RemoteViews contentView) {
         this.mContentView = contentView;
         return this;
      }

      public NotificationCompat.Builder setCustomBigContentView(RemoteViews contentView) {
         this.mBigContentView = contentView;
         return this;
      }

      public NotificationCompat.Builder setCustomHeadsUpContentView(RemoteViews contentView) {
         this.mHeadsUpContentView = contentView;
         return this;
      }

      public NotificationCompat.Builder setChannelId(@NonNull String channelId) {
         this.mChannelId = channelId;
         return this;
      }

      public NotificationCompat.Builder setTimeoutAfter(long durationMs) {
         this.mTimeout = durationMs;
         return this;
      }

      public NotificationCompat.Builder setShortcutId(String shortcutId) {
         this.mShortcutId = shortcutId;
         return this;
      }

      public NotificationCompat.Builder setBadgeIconType(int icon) {
         this.mBadgeIcon = icon;
         return this;
      }

      public NotificationCompat.Builder setGroupAlertBehavior(int groupAlertBehavior) {
         this.mGroupAlertBehavior = groupAlertBehavior;
         return this;
      }

      public NotificationCompat.Builder extend(NotificationCompat.Extender extender) {
         extender.extend(this);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Notification getNotification() {
         return this.build();
      }

      public Notification build() {
         return NotificationCompat.IMPL.build(this, this.getExtender());
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      protected NotificationCompat.BuilderExtender getExtender() {
         return new NotificationCompat.BuilderExtender();
      }

      protected static CharSequence limitCharSequenceLength(CharSequence cs) {
         if (cs == null) {
            return cs;
         } else {
            if (cs.length() > 5120) {
               cs = cs.subSequence(0, 5120);
            }

            return cs;
         }
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews getContentView() {
         return this.mContentView;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews getBigContentView() {
         return this.mBigContentView;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public RemoteViews getHeadsUpContentView() {
         return this.mHeadsUpContentView;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public long getWhenIfShowing() {
         return this.mShowWhen ? this.mNotification.when : 0L;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public int getPriority() {
         return this.mPriority;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public int getColor() {
         return this.mColor;
      }
   }

   @RequiresApi(26)
   static class NotificationCompatApi26Impl extends NotificationCompat.NotificationCompatApi24Impl {
      public Notification build(NotificationCompat.Builder b, NotificationCompat.BuilderExtender extender) {
         NotificationCompatApi26.Builder builder = new NotificationCompatApi26.Builder(b.mContext, b.mNotification, b.mContentTitle, b.mContentText, b.mContentInfo, b.mTickerView, b.mNumber, b.mContentIntent, b.mFullScreenIntent, b.mLargeIcon, b.mProgressMax, b.mProgress, b.mProgressIndeterminate, b.mShowWhen, b.mUseChronometer, b.mPriority, b.mSubText, b.mLocalOnly, b.mCategory, b.mPeople, b.mExtras, b.mColor, b.mVisibility, b.mPublicVersion, b.mGroupKey, b.mGroupSummary, b.mSortKey, b.mRemoteInputHistory, b.mContentView, b.mBigContentView, b.mHeadsUpContentView, b.mChannelId, b.mBadgeIcon, b.mShortcutId, b.mTimeout, b.mColorized, b.mColorizedSet, b.mGroupAlertBehavior);
         NotificationCompat.addActionsToBuilder(builder, b.mActions);
         if (b.mStyle != null) {
            b.mStyle.apply(builder);
         }

         Notification notification = extender.build(b, builder);
         if (b.mStyle != null) {
            b.mStyle.addCompatExtras(NotificationCompat.getExtras(notification));
         }

         return notification;
      }
   }

   @RequiresApi(24)
   static class NotificationCompatApi24Impl extends NotificationCompat.NotificationCompatApi21Impl {
      public Notification build(NotificationCompat.Builder b, NotificationCompat.BuilderExtender extender) {
         NotificationCompatApi24.Builder builder = new NotificationCompatApi24.Builder(b.mContext, b.mNotification, b.mContentTitle, b.mContentText, b.mContentInfo, b.mTickerView, b.mNumber, b.mContentIntent, b.mFullScreenIntent, b.mLargeIcon, b.mProgressMax, b.mProgress, b.mProgressIndeterminate, b.mShowWhen, b.mUseChronometer, b.mPriority, b.mSubText, b.mLocalOnly, b.mCategory, b.mPeople, b.mExtras, b.mColor, b.mVisibility, b.mPublicVersion, b.mGroupKey, b.mGroupSummary, b.mSortKey, b.mRemoteInputHistory, b.mContentView, b.mBigContentView, b.mHeadsUpContentView, b.mGroupAlertBehavior);
         NotificationCompat.addActionsToBuilder(builder, b.mActions);
         if (b.mStyle != null) {
            b.mStyle.apply(builder);
         }

         Notification notification = extender.build(b, builder);
         if (b.mStyle != null) {
            b.mStyle.addCompatExtras(NotificationCompat.getExtras(notification));
         }

         return notification;
      }

      public NotificationCompat.Action getAction(Notification n, int actionIndex) {
         return (NotificationCompat.Action)NotificationCompatApi24.getAction(n, actionIndex, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
      }

      public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList parcelables) {
         return (NotificationCompat.Action[])((NotificationCompat.Action[])NotificationCompatApi24.getActionsFromParcelableArrayList(parcelables, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY));
      }

      public ArrayList getParcelableArrayListForActions(NotificationCompat.Action[] actions) {
         return NotificationCompatApi24.getParcelableArrayListForActions(actions);
      }
   }

   @RequiresApi(21)
   static class NotificationCompatApi21Impl extends NotificationCompat.NotificationCompatApi20Impl {
      public Notification build(NotificationCompat.Builder b, NotificationCompat.BuilderExtender extender) {
         NotificationCompatApi21.Builder builder = new NotificationCompatApi21.Builder(b.mContext, b.mNotification, b.mContentTitle, b.mContentText, b.mContentInfo, b.mTickerView, b.mNumber, b.mContentIntent, b.mFullScreenIntent, b.mLargeIcon, b.mProgressMax, b.mProgress, b.mProgressIndeterminate, b.mShowWhen, b.mUseChronometer, b.mPriority, b.mSubText, b.mLocalOnly, b.mCategory, b.mPeople, b.mExtras, b.mColor, b.mVisibility, b.mPublicVersion, b.mGroupKey, b.mGroupSummary, b.mSortKey, b.mContentView, b.mBigContentView, b.mHeadsUpContentView, b.mGroupAlertBehavior);
         NotificationCompat.addActionsToBuilder(builder, b.mActions);
         if (b.mStyle != null) {
            b.mStyle.apply(builder);
         }

         Notification notification = extender.build(b, builder);
         if (b.mStyle != null) {
            b.mStyle.addCompatExtras(NotificationCompat.getExtras(notification));
         }

         return notification;
      }

      public Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation uc) {
         return NotificationCompatApi21.getBundleForUnreadConversation(uc);
      }

      public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle b, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
         return NotificationCompatApi21.getUnreadConversationFromBundle(b, factory, remoteInputFactory);
      }
   }

   @RequiresApi(20)
   static class NotificationCompatApi20Impl extends NotificationCompat.NotificationCompatApi19Impl {
      public Notification build(NotificationCompat.Builder b, NotificationCompat.BuilderExtender extender) {
         NotificationCompatApi20.Builder builder = new NotificationCompatApi20.Builder(b.mContext, b.mNotification, b.mContentTitle, b.mContentText, b.mContentInfo, b.mTickerView, b.mNumber, b.mContentIntent, b.mFullScreenIntent, b.mLargeIcon, b.mProgressMax, b.mProgress, b.mProgressIndeterminate, b.mShowWhen, b.mUseChronometer, b.mPriority, b.mSubText, b.mLocalOnly, b.mPeople, b.mExtras, b.mGroupKey, b.mGroupSummary, b.mSortKey, b.mContentView, b.mBigContentView, b.mGroupAlertBehavior);
         NotificationCompat.addActionsToBuilder(builder, b.mActions);
         if (b.mStyle != null) {
            b.mStyle.apply(builder);
         }

         Notification notification = extender.build(b, builder);
         if (b.mStyle != null) {
            b.mStyle.addCompatExtras(NotificationCompat.getExtras(notification));
         }

         return notification;
      }

      public NotificationCompat.Action getAction(Notification n, int actionIndex) {
         return (NotificationCompat.Action)NotificationCompatApi20.getAction(n, actionIndex, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
      }

      public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList parcelables) {
         return (NotificationCompat.Action[])((NotificationCompat.Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(parcelables, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY));
      }

      public ArrayList getParcelableArrayListForActions(NotificationCompat.Action[] actions) {
         return NotificationCompatApi20.getParcelableArrayListForActions(actions);
      }
   }

   @RequiresApi(19)
   static class NotificationCompatApi19Impl extends NotificationCompat.NotificationCompatApi16Impl {
      public Notification build(NotificationCompat.Builder b, NotificationCompat.BuilderExtender extender) {
         NotificationCompatKitKat.Builder builder = new NotificationCompatKitKat.Builder(b.mContext, b.mNotification, b.mContentTitle, b.mContentText, b.mContentInfo, b.mTickerView, b.mNumber, b.mContentIntent, b.mFullScreenIntent, b.mLargeIcon, b.mProgressMax, b.mProgress, b.mProgressIndeterminate, b.mShowWhen, b.mUseChronometer, b.mPriority, b.mSubText, b.mLocalOnly, b.mPeople, b.mExtras, b.mGroupKey, b.mGroupSummary, b.mSortKey, b.mContentView, b.mBigContentView);
         NotificationCompat.addActionsToBuilder(builder, b.mActions);
         if (b.mStyle != null) {
            b.mStyle.apply(builder);
         }

         return extender.build(b, builder);
      }

      public NotificationCompat.Action getAction(Notification n, int actionIndex) {
         return (NotificationCompat.Action)NotificationCompatKitKat.getAction(n, actionIndex, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
      }
   }

   @RequiresApi(16)
   static class NotificationCompatApi16Impl extends NotificationCompat.NotificationCompatBaseImpl {
      public Notification build(NotificationCompat.Builder b, NotificationCompat.BuilderExtender extender) {
         NotificationCompatJellybean.Builder builder = new NotificationCompatJellybean.Builder(b.mContext, b.mNotification, b.mContentTitle, b.mContentText, b.mContentInfo, b.mTickerView, b.mNumber, b.mContentIntent, b.mFullScreenIntent, b.mLargeIcon, b.mProgressMax, b.mProgress, b.mProgressIndeterminate, b.mUseChronometer, b.mPriority, b.mSubText, b.mLocalOnly, b.mExtras, b.mGroupKey, b.mGroupSummary, b.mSortKey, b.mContentView, b.mBigContentView);
         NotificationCompat.addActionsToBuilder(builder, b.mActions);
         if (b.mStyle != null) {
            b.mStyle.apply(builder);
         }

         Notification notification = extender.build(b, builder);
         if (b.mStyle != null) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras != null) {
               b.mStyle.addCompatExtras(extras);
            }
         }

         return notification;
      }

      public NotificationCompat.Action getAction(Notification n, int actionIndex) {
         return (NotificationCompat.Action)NotificationCompatJellybean.getAction(n, actionIndex, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
      }

      public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList parcelables) {
         return (NotificationCompat.Action[])((NotificationCompat.Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(parcelables, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY));
      }

      public ArrayList getParcelableArrayListForActions(NotificationCompat.Action[] actions) {
         return NotificationCompatJellybean.getParcelableArrayListForActions(actions);
      }
   }

   static class NotificationCompatBaseImpl implements NotificationCompat.NotificationCompatImpl {
      public Notification build(NotificationCompat.Builder b, NotificationCompat.BuilderExtender extender) {
         NotificationCompat.NotificationCompatBaseImpl.BuilderBase builder = new NotificationCompat.NotificationCompatBaseImpl.BuilderBase(b.mContext, b.mNotification, b.mContentTitle, b.mContentText, b.mContentInfo, b.mTickerView, b.mNumber, b.mContentIntent, b.mFullScreenIntent, b.mLargeIcon, b.mProgressMax, b.mProgress, b.mProgressIndeterminate);
         return extender.build(b, builder);
      }

      public NotificationCompat.Action getAction(Notification n, int actionIndex) {
         return null;
      }

      public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList parcelables) {
         return null;
      }

      public ArrayList getParcelableArrayListForActions(NotificationCompat.Action[] actions) {
         return null;
      }

      public Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation uc) {
         return null;
      }

      public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle b, NotificationCompatBase.UnreadConversation.Factory factory, RemoteInputCompatBase.RemoteInput.Factory remoteInputFactory) {
         return null;
      }

      public static class BuilderBase implements NotificationBuilderWithBuilderAccessor {
         private android.app.Notification.Builder mBuilder;

         BuilderBase(Context context, Notification n, CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo, RemoteViews tickerView, int number, PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon, int progressMax, int progress, boolean progressIndeterminate) {
            this.mBuilder = (new android.app.Notification.Builder(context)).setWhen(n.when).setSmallIcon(n.icon, n.iconLevel).setContent(n.contentView).setTicker(n.tickerText, tickerView).setSound(n.sound, n.audioStreamType).setVibrate(n.vibrate).setLights(n.ledARGB, n.ledOnMS, n.ledOffMS).setOngoing((n.flags & 2) != 0).setOnlyAlertOnce((n.flags & 8) != 0).setAutoCancel((n.flags & 16) != 0).setDefaults(n.defaults).setContentTitle(contentTitle).setContentText(contentText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(n.deleteIntent).setFullScreenIntent(fullScreenIntent, (n.flags & 128) != 0).setLargeIcon(largeIcon).setNumber(number).setProgress(progressMax, progress, progressIndeterminate);
         }

         public android.app.Notification.Builder getBuilder() {
            return this.mBuilder;
         }

         public Notification build() {
            return this.mBuilder.getNotification();
         }
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   protected static class BuilderExtender {
      public Notification build(NotificationCompat.Builder b, NotificationBuilderWithBuilderAccessor builder) {
         RemoteViews styleContentView = b.mStyle != null ? b.mStyle.makeContentView(builder) : null;
         Notification n = builder.build();
         if (styleContentView != null) {
            n.contentView = styleContentView;
         } else if (b.mContentView != null) {
            n.contentView = b.mContentView;
         }

         RemoteViews styleHeadsUpContentView;
         if (VERSION.SDK_INT >= 16 && b.mStyle != null) {
            styleHeadsUpContentView = b.mStyle.makeBigContentView(builder);
            if (styleHeadsUpContentView != null) {
               n.bigContentView = styleHeadsUpContentView;
            }
         }

         if (VERSION.SDK_INT >= 21 && b.mStyle != null) {
            styleHeadsUpContentView = b.mStyle.makeHeadsUpContentView(builder);
            if (styleHeadsUpContentView != null) {
               n.headsUpContentView = styleHeadsUpContentView;
            }
         }

         return n;
      }
   }

   interface NotificationCompatImpl {
      Notification build(NotificationCompat.Builder var1, NotificationCompat.BuilderExtender var2);

      NotificationCompat.Action getAction(Notification var1, int var2);

      NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList var1);

      ArrayList getParcelableArrayListForActions(NotificationCompat.Action[] var1);

      Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation var1);

      NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle var1, NotificationCompatBase.UnreadConversation.Factory var2, RemoteInputCompatBase.RemoteInput.Factory var3);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface BadgeIconType {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface NotificationVisibility {
   }
}
