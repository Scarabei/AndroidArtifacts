package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlaybackStateCompat implements Parcelable {
   public static final long ACTION_STOP = 1L;
   public static final long ACTION_PAUSE = 2L;
   public static final long ACTION_PLAY = 4L;
   public static final long ACTION_REWIND = 8L;
   public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
   public static final long ACTION_SKIP_TO_NEXT = 32L;
   public static final long ACTION_FAST_FORWARD = 64L;
   public static final long ACTION_SET_RATING = 128L;
   public static final long ACTION_SEEK_TO = 256L;
   public static final long ACTION_PLAY_PAUSE = 512L;
   public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
   public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
   public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
   public static final long ACTION_PLAY_FROM_URI = 8192L;
   public static final long ACTION_PREPARE = 16384L;
   public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768L;
   public static final long ACTION_PREPARE_FROM_SEARCH = 65536L;
   public static final long ACTION_PREPARE_FROM_URI = 131072L;
   public static final long ACTION_SET_REPEAT_MODE = 262144L;
   public static final long ACTION_SET_SHUFFLE_MODE_ENABLED = 524288L;
   public static final long ACTION_SET_CAPTIONING_ENABLED = 1048576L;
   public static final int STATE_NONE = 0;
   public static final int STATE_STOPPED = 1;
   public static final int STATE_PAUSED = 2;
   public static final int STATE_PLAYING = 3;
   public static final int STATE_FAST_FORWARDING = 4;
   public static final int STATE_REWINDING = 5;
   public static final int STATE_BUFFERING = 6;
   public static final int STATE_ERROR = 7;
   public static final int STATE_CONNECTING = 8;
   public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
   public static final int STATE_SKIPPING_TO_NEXT = 10;
   public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
   public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
   public static final int REPEAT_MODE_NONE = 0;
   public static final int REPEAT_MODE_ONE = 1;
   public static final int REPEAT_MODE_ALL = 2;
   public static final int REPEAT_MODE_GROUP = 3;
   public static final int SHUFFLE_MODE_NONE = 0;
   public static final int SHUFFLE_MODE_ALL = 1;
   public static final int SHUFFLE_MODE_GROUP = 2;
   public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
   public static final int ERROR_CODE_APP_ERROR = 1;
   public static final int ERROR_CODE_NOT_SUPPORTED = 2;
   public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
   public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
   public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
   public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
   public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
   public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
   public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
   public static final int ERROR_CODE_ACTION_ABORTED = 10;
   public static final int ERROR_CODE_END_OF_QUEUE = 11;
   private static final int KEYCODE_MEDIA_PAUSE = 127;
   private static final int KEYCODE_MEDIA_PLAY = 126;
   final int mState;
   final long mPosition;
   final long mBufferedPosition;
   final float mSpeed;
   final long mActions;
   final int mErrorCode;
   final CharSequence mErrorMessage;
   final long mUpdateTime;
   List mCustomActions;
   final long mActiveItemId;
   final Bundle mExtras;
   private Object mStateObj;
   public static final Creator CREATOR = new Creator() {
      public PlaybackStateCompat createFromParcel(Parcel in) {
         return new PlaybackStateCompat(in);
      }

      public PlaybackStateCompat[] newArray(int size) {
         return new PlaybackStateCompat[size];
      }
   };

   public static int toKeyCode(long action) {
      if (action == 4L) {
         return 126;
      } else if (action == 2L) {
         return 127;
      } else if (action == 32L) {
         return 87;
      } else if (action == 16L) {
         return 88;
      } else if (action == 1L) {
         return 86;
      } else if (action == 64L) {
         return 90;
      } else if (action == 8L) {
         return 89;
      } else {
         return action == 512L ? 85 : 0;
      }
   }

   PlaybackStateCompat(int state, long position, long bufferedPosition, float rate, long actions, int errorCode, CharSequence errorMessage, long updateTime, List customActions, long activeItemId, Bundle extras) {
      this.mState = state;
      this.mPosition = position;
      this.mBufferedPosition = bufferedPosition;
      this.mSpeed = rate;
      this.mActions = actions;
      this.mErrorCode = errorCode;
      this.mErrorMessage = errorMessage;
      this.mUpdateTime = updateTime;
      this.mCustomActions = new ArrayList(customActions);
      this.mActiveItemId = activeItemId;
      this.mExtras = extras;
   }

   PlaybackStateCompat(Parcel in) {
      this.mState = in.readInt();
      this.mPosition = in.readLong();
      this.mSpeed = in.readFloat();
      this.mUpdateTime = in.readLong();
      this.mBufferedPosition = in.readLong();
      this.mActions = in.readLong();
      this.mErrorMessage = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
      this.mCustomActions = in.createTypedArrayList(PlaybackStateCompat.CustomAction.CREATOR);
      this.mActiveItemId = in.readLong();
      this.mExtras = in.readBundle();
      this.mErrorCode = in.readInt();
   }

   public String toString() {
      StringBuilder bob = new StringBuilder("PlaybackState {");
      bob.append("state=").append(this.mState);
      bob.append(", position=").append(this.mPosition);
      bob.append(", buffered position=").append(this.mBufferedPosition);
      bob.append(", speed=").append(this.mSpeed);
      bob.append(", updated=").append(this.mUpdateTime);
      bob.append(", actions=").append(this.mActions);
      bob.append(", error code=").append(this.mErrorCode);
      bob.append(", error message=").append(this.mErrorMessage);
      bob.append(", custom actions=").append(this.mCustomActions);
      bob.append(", active item id=").append(this.mActiveItemId);
      bob.append("}");
      return bob.toString();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(this.mState);
      dest.writeLong(this.mPosition);
      dest.writeFloat(this.mSpeed);
      dest.writeLong(this.mUpdateTime);
      dest.writeLong(this.mBufferedPosition);
      dest.writeLong(this.mActions);
      TextUtils.writeToParcel(this.mErrorMessage, dest, flags);
      dest.writeTypedList(this.mCustomActions);
      dest.writeLong(this.mActiveItemId);
      dest.writeBundle(this.mExtras);
      dest.writeInt(this.mErrorCode);
   }

   public int getState() {
      return this.mState;
   }

   public long getPosition() {
      return this.mPosition;
   }

   public long getBufferedPosition() {
      return this.mBufferedPosition;
   }

   public float getPlaybackSpeed() {
      return this.mSpeed;
   }

   public long getActions() {
      return this.mActions;
   }

   public List getCustomActions() {
      return this.mCustomActions;
   }

   public int getErrorCode() {
      return this.mErrorCode;
   }

   public CharSequence getErrorMessage() {
      return this.mErrorMessage;
   }

   public long getLastPositionUpdateTime() {
      return this.mUpdateTime;
   }

   public long getActiveQueueItemId() {
      return this.mActiveItemId;
   }

   @Nullable
   public Bundle getExtras() {
      return this.mExtras;
   }

   public static PlaybackStateCompat fromPlaybackState(Object stateObj) {
      if (stateObj != null && VERSION.SDK_INT >= 21) {
         List customActionObjs = PlaybackStateCompatApi21.getCustomActions(stateObj);
         List customActions = null;
         if (customActionObjs != null) {
            customActions = new ArrayList(customActionObjs.size());
            Iterator var3 = customActionObjs.iterator();

            while(var3.hasNext()) {
               Object customActionObj = var3.next();
               customActions.add(PlaybackStateCompat.CustomAction.fromCustomAction(customActionObj));
            }
         }

         Bundle extras;
         if (VERSION.SDK_INT >= 22) {
            extras = PlaybackStateCompatApi22.getExtras(stateObj);
         } else {
            extras = null;
         }

         PlaybackStateCompat state = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(stateObj), PlaybackStateCompatApi21.getPosition(stateObj), PlaybackStateCompatApi21.getBufferedPosition(stateObj), PlaybackStateCompatApi21.getPlaybackSpeed(stateObj), PlaybackStateCompatApi21.getActions(stateObj), 0, PlaybackStateCompatApi21.getErrorMessage(stateObj), PlaybackStateCompatApi21.getLastPositionUpdateTime(stateObj), customActions, PlaybackStateCompatApi21.getActiveQueueItemId(stateObj), extras);
         state.mStateObj = stateObj;
         return state;
      } else {
         return null;
      }
   }

   public Object getPlaybackState() {
      if (this.mStateObj == null && VERSION.SDK_INT >= 21) {
         List customActions = null;
         if (this.mCustomActions != null) {
            customActions = new ArrayList(this.mCustomActions.size());
            Iterator var2 = this.mCustomActions.iterator();

            while(var2.hasNext()) {
               PlaybackStateCompat.CustomAction customAction = (PlaybackStateCompat.CustomAction)var2.next();
               customActions.add(customAction.getCustomAction());
            }
         }

         if (VERSION.SDK_INT >= 22) {
            this.mStateObj = PlaybackStateCompatApi22.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, customActions, this.mActiveItemId, this.mExtras);
         } else {
            this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, customActions, this.mActiveItemId);
         }
      }

      return this.mStateObj;
   }

   public static final class Builder {
      private final List mCustomActions = new ArrayList();
      private int mState;
      private long mPosition;
      private long mBufferedPosition;
      private float mRate;
      private long mActions;
      private int mErrorCode;
      private CharSequence mErrorMessage;
      private long mUpdateTime;
      private long mActiveItemId = -1L;
      private Bundle mExtras;

      public Builder() {
      }

      public Builder(PlaybackStateCompat source) {
         this.mState = source.mState;
         this.mPosition = source.mPosition;
         this.mRate = source.mSpeed;
         this.mUpdateTime = source.mUpdateTime;
         this.mBufferedPosition = source.mBufferedPosition;
         this.mActions = source.mActions;
         this.mErrorCode = source.mErrorCode;
         this.mErrorMessage = source.mErrorMessage;
         if (source.mCustomActions != null) {
            this.mCustomActions.addAll(source.mCustomActions);
         }

         this.mActiveItemId = source.mActiveItemId;
         this.mExtras = source.mExtras;
      }

      public PlaybackStateCompat.Builder setState(int state, long position, float playbackSpeed) {
         return this.setState(state, position, playbackSpeed, SystemClock.elapsedRealtime());
      }

      public PlaybackStateCompat.Builder setState(int state, long position, float playbackSpeed, long updateTime) {
         this.mState = state;
         this.mPosition = position;
         this.mUpdateTime = updateTime;
         this.mRate = playbackSpeed;
         return this;
      }

      public PlaybackStateCompat.Builder setBufferedPosition(long bufferPosition) {
         this.mBufferedPosition = bufferPosition;
         return this;
      }

      public PlaybackStateCompat.Builder setActions(long capabilities) {
         this.mActions = capabilities;
         return this;
      }

      public PlaybackStateCompat.Builder addCustomAction(String action, String name, int icon) {
         return this.addCustomAction(new PlaybackStateCompat.CustomAction(action, name, icon, (Bundle)null));
      }

      public PlaybackStateCompat.Builder addCustomAction(PlaybackStateCompat.CustomAction customAction) {
         if (customAction == null) {
            throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
         } else {
            this.mCustomActions.add(customAction);
            return this;
         }
      }

      public PlaybackStateCompat.Builder setActiveQueueItemId(long id) {
         this.mActiveItemId = id;
         return this;
      }

      /** @deprecated */
      public PlaybackStateCompat.Builder setErrorMessage(CharSequence errorMessage) {
         this.mErrorMessage = errorMessage;
         return this;
      }

      public PlaybackStateCompat.Builder setErrorMessage(int errorCode, CharSequence errorMessage) {
         this.mErrorCode = errorCode;
         this.mErrorMessage = errorMessage;
         return this;
      }

      public PlaybackStateCompat.Builder setExtras(Bundle extras) {
         this.mExtras = extras;
         return this;
      }

      public PlaybackStateCompat build() {
         return new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorCode, this.mErrorMessage, this.mUpdateTime, this.mCustomActions, this.mActiveItemId, this.mExtras);
      }
   }

   public static final class CustomAction implements Parcelable {
      private final String mAction;
      private final CharSequence mName;
      private final int mIcon;
      private final Bundle mExtras;
      private Object mCustomActionObj;
      public static final Creator CREATOR = new Creator() {
         public PlaybackStateCompat.CustomAction createFromParcel(Parcel p) {
            return new PlaybackStateCompat.CustomAction(p);
         }

         public PlaybackStateCompat.CustomAction[] newArray(int size) {
            return new PlaybackStateCompat.CustomAction[size];
         }
      };

      CustomAction(String action, CharSequence name, int icon, Bundle extras) {
         this.mAction = action;
         this.mName = name;
         this.mIcon = icon;
         this.mExtras = extras;
      }

      CustomAction(Parcel in) {
         this.mAction = in.readString();
         this.mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
         this.mIcon = in.readInt();
         this.mExtras = in.readBundle();
      }

      public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(this.mAction);
         TextUtils.writeToParcel(this.mName, dest, flags);
         dest.writeInt(this.mIcon);
         dest.writeBundle(this.mExtras);
      }

      public int describeContents() {
         return 0;
      }

      public static PlaybackStateCompat.CustomAction fromCustomAction(Object customActionObj) {
         if (customActionObj != null && VERSION.SDK_INT >= 21) {
            PlaybackStateCompat.CustomAction customAction = new PlaybackStateCompat.CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(customActionObj), PlaybackStateCompatApi21.CustomAction.getName(customActionObj), PlaybackStateCompatApi21.CustomAction.getIcon(customActionObj), PlaybackStateCompatApi21.CustomAction.getExtras(customActionObj));
            customAction.mCustomActionObj = customActionObj;
            return customAction;
         } else {
            return null;
         }
      }

      public Object getCustomAction() {
         if (this.mCustomActionObj == null && VERSION.SDK_INT >= 21) {
            this.mCustomActionObj = PlaybackStateCompatApi21.CustomAction.newInstance(this.mAction, this.mName, this.mIcon, this.mExtras);
            return this.mCustomActionObj;
         } else {
            return this.mCustomActionObj;
         }
      }

      public String getAction() {
         return this.mAction;
      }

      public CharSequence getName() {
         return this.mName;
      }

      public int getIcon() {
         return this.mIcon;
      }

      public Bundle getExtras() {
         return this.mExtras;
      }

      public String toString() {
         return "Action:mName='" + this.mName + ", mIcon=" + this.mIcon + ", mExtras=" + this.mExtras;
      }

      public static final class Builder {
         private final String mAction;
         private final CharSequence mName;
         private final int mIcon;
         private Bundle mExtras;

         public Builder(String action, CharSequence name, int icon) {
            if (TextUtils.isEmpty(action)) {
               throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
            } else if (TextUtils.isEmpty(name)) {
               throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
            } else if (icon == 0) {
               throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
            } else {
               this.mAction = action;
               this.mName = name;
               this.mIcon = icon;
            }
         }

         public PlaybackStateCompat.CustomAction.Builder setExtras(Bundle extras) {
            this.mExtras = extras;
            return this;
         }

         public PlaybackStateCompat.CustomAction build() {
            return new PlaybackStateCompat.CustomAction(this.mAction, this.mName, this.mIcon, this.mExtras);
         }
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface ErrorCode {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface ShuffleMode {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface RepeatMode {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface State {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface MediaKeyAction {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface Actions {
   }
}
