package android.support.v7.media;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;

public final class MediaSessionStatus {
   static final String KEY_TIMESTAMP = "timestamp";
   static final String KEY_SESSION_STATE = "sessionState";
   static final String KEY_QUEUE_PAUSED = "queuePaused";
   static final String KEY_EXTRAS = "extras";
   final Bundle mBundle;
   public static final int SESSION_STATE_ACTIVE = 0;
   public static final int SESSION_STATE_ENDED = 1;
   public static final int SESSION_STATE_INVALIDATED = 2;

   MediaSessionStatus(Bundle bundle) {
      this.mBundle = bundle;
   }

   public long getTimestamp() {
      return this.mBundle.getLong("timestamp");
   }

   public int getSessionState() {
      return this.mBundle.getInt("sessionState", 2);
   }

   public boolean isQueuePaused() {
      return this.mBundle.getBoolean("queuePaused");
   }

   public Bundle getExtras() {
      return this.mBundle.getBundle("extras");
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("MediaSessionStatus{ ");
      result.append("timestamp=");
      TimeUtils.formatDuration(SystemClock.elapsedRealtime() - this.getTimestamp(), result);
      result.append(" ms ago");
      result.append(", sessionState=").append(sessionStateToString(this.getSessionState()));
      result.append(", queuePaused=").append(this.isQueuePaused());
      result.append(", extras=").append(this.getExtras());
      result.append(" }");
      return result.toString();
   }

   private static String sessionStateToString(int sessionState) {
      switch(sessionState) {
      case 0:
         return "active";
      case 1:
         return "ended";
      case 2:
         return "invalidated";
      default:
         return Integer.toString(sessionState);
      }
   }

   public Bundle asBundle() {
      return this.mBundle;
   }

   public static MediaSessionStatus fromBundle(Bundle bundle) {
      return bundle != null ? new MediaSessionStatus(bundle) : null;
   }

   public static final class Builder {
      private final Bundle mBundle;

      public Builder(int sessionState) {
         this.mBundle = new Bundle();
         this.setTimestamp(SystemClock.elapsedRealtime());
         this.setSessionState(sessionState);
      }

      public Builder(MediaSessionStatus status) {
         if (status == null) {
            throw new IllegalArgumentException("status must not be null");
         } else {
            this.mBundle = new Bundle(status.mBundle);
         }
      }

      public MediaSessionStatus.Builder setTimestamp(long elapsedRealtimeTimestamp) {
         this.mBundle.putLong("timestamp", elapsedRealtimeTimestamp);
         return this;
      }

      public MediaSessionStatus.Builder setSessionState(int sessionState) {
         this.mBundle.putInt("sessionState", sessionState);
         return this;
      }

      public MediaSessionStatus.Builder setQueuePaused(boolean queuePaused) {
         this.mBundle.putBoolean("queuePaused", queuePaused);
         return this;
      }

      public MediaSessionStatus.Builder setExtras(Bundle extras) {
         this.mBundle.putBundle("extras", extras);
         return this;
      }

      public MediaSessionStatus build() {
         return new MediaSessionStatus(this.mBundle);
      }
   }
}
