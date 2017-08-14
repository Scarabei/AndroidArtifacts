package android.support.v7.media;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;

public final class MediaItemStatus {
   static final String KEY_TIMESTAMP = "timestamp";
   static final String KEY_PLAYBACK_STATE = "playbackState";
   static final String KEY_CONTENT_POSITION = "contentPosition";
   static final String KEY_CONTENT_DURATION = "contentDuration";
   static final String KEY_EXTRAS = "extras";
   final Bundle mBundle;
   public static final int PLAYBACK_STATE_PENDING = 0;
   public static final int PLAYBACK_STATE_PLAYING = 1;
   public static final int PLAYBACK_STATE_PAUSED = 2;
   public static final int PLAYBACK_STATE_BUFFERING = 3;
   public static final int PLAYBACK_STATE_FINISHED = 4;
   public static final int PLAYBACK_STATE_CANCELED = 5;
   public static final int PLAYBACK_STATE_INVALIDATED = 6;
   public static final int PLAYBACK_STATE_ERROR = 7;
   public static final String EXTRA_HTTP_STATUS_CODE = "android.media.status.extra.HTTP_STATUS_CODE";
   public static final String EXTRA_HTTP_RESPONSE_HEADERS = "android.media.status.extra.HTTP_RESPONSE_HEADERS";

   MediaItemStatus(Bundle bundle) {
      this.mBundle = bundle;
   }

   public long getTimestamp() {
      return this.mBundle.getLong("timestamp");
   }

   public int getPlaybackState() {
      return this.mBundle.getInt("playbackState", 7);
   }

   public long getContentPosition() {
      return this.mBundle.getLong("contentPosition", -1L);
   }

   public long getContentDuration() {
      return this.mBundle.getLong("contentDuration", -1L);
   }

   public Bundle getExtras() {
      return this.mBundle.getBundle("extras");
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("MediaItemStatus{ ");
      result.append("timestamp=");
      TimeUtils.formatDuration(SystemClock.elapsedRealtime() - this.getTimestamp(), result);
      result.append(" ms ago");
      result.append(", playbackState=").append(playbackStateToString(this.getPlaybackState()));
      result.append(", contentPosition=").append(this.getContentPosition());
      result.append(", contentDuration=").append(this.getContentDuration());
      result.append(", extras=").append(this.getExtras());
      result.append(" }");
      return result.toString();
   }

   private static String playbackStateToString(int playbackState) {
      switch(playbackState) {
      case 0:
         return "pending";
      case 1:
         return "playing";
      case 2:
         return "paused";
      case 3:
         return "buffering";
      case 4:
         return "finished";
      case 5:
         return "canceled";
      case 6:
         return "invalidated";
      case 7:
         return "error";
      default:
         return Integer.toString(playbackState);
      }
   }

   public Bundle asBundle() {
      return this.mBundle;
   }

   public static MediaItemStatus fromBundle(Bundle bundle) {
      return bundle != null ? new MediaItemStatus(bundle) : null;
   }

   public static final class Builder {
      private final Bundle mBundle;

      public Builder(int playbackState) {
         this.mBundle = new Bundle();
         this.setTimestamp(SystemClock.elapsedRealtime());
         this.setPlaybackState(playbackState);
      }

      public Builder(MediaItemStatus status) {
         if (status == null) {
            throw new IllegalArgumentException("status must not be null");
         } else {
            this.mBundle = new Bundle(status.mBundle);
         }
      }

      public MediaItemStatus.Builder setTimestamp(long elapsedRealtimeTimestamp) {
         this.mBundle.putLong("timestamp", elapsedRealtimeTimestamp);
         return this;
      }

      public MediaItemStatus.Builder setPlaybackState(int playbackState) {
         this.mBundle.putInt("playbackState", playbackState);
         return this;
      }

      public MediaItemStatus.Builder setContentPosition(long positionMilliseconds) {
         this.mBundle.putLong("contentPosition", positionMilliseconds);
         return this;
      }

      public MediaItemStatus.Builder setContentDuration(long durationMilliseconds) {
         this.mBundle.putLong("contentDuration", durationMilliseconds);
         return this;
      }

      public MediaItemStatus.Builder setExtras(Bundle extras) {
         this.mBundle.putBundle("extras", extras);
         return this;
      }

      public MediaItemStatus build() {
         return new MediaItemStatus(this.mBundle);
      }
   }
}
