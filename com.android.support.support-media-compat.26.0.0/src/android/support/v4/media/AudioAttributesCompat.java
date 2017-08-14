package android.support.v4.media;

import android.media.AudioAttributes;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.SparseIntArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public class AudioAttributesCompat {
   private static final String TAG = "AudioAttributesCompat";
   public static final int CONTENT_TYPE_UNKNOWN = 0;
   public static final int CONTENT_TYPE_SPEECH = 1;
   public static final int CONTENT_TYPE_MUSIC = 2;
   public static final int CONTENT_TYPE_MOVIE = 3;
   public static final int CONTENT_TYPE_SONIFICATION = 4;
   public static final int USAGE_UNKNOWN = 0;
   public static final int USAGE_MEDIA = 1;
   public static final int USAGE_VOICE_COMMUNICATION = 2;
   public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
   public static final int USAGE_ALARM = 4;
   public static final int USAGE_NOTIFICATION = 5;
   public static final int USAGE_NOTIFICATION_RINGTONE = 6;
   public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
   public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
   public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
   public static final int USAGE_NOTIFICATION_EVENT = 10;
   public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
   public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
   public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
   public static final int USAGE_GAME = 14;
   private static final int USAGE_VIRTUAL_SOURCE = 15;
   public static final int USAGE_ASSISTANT = 16;
   private static final int SUPPRESSIBLE_NOTIFICATION = 1;
   private static final int SUPPRESSIBLE_CALL = 2;
   private static final SparseIntArray SUPPRESSIBLE_USAGES = new SparseIntArray();
   private static boolean sForceLegacyBehavior;
   private static final int[] SDK_USAGES;
   public static final int FLAG_AUDIBILITY_ENFORCED = 1;
   private static final int FLAG_SECURE = 2;
   private static final int FLAG_SCO = 4;
   private static final int FLAG_BEACON = 8;
   public static final int FLAG_HW_AV_SYNC = 16;
   private static final int FLAG_HW_HOTWORD = 32;
   private static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;
   private static final int FLAG_BYPASS_MUTE = 128;
   private static final int FLAG_LOW_LATENCY = 256;
   private static final int FLAG_DEEP_BUFFER = 512;
   private static final int FLAG_ALL = 1023;
   private static final int FLAG_ALL_PUBLIC = 273;
   int mUsage;
   int mContentType;
   int mFlags;
   Integer mLegacyStream;
   private AudioAttributesCompatApi21.Wrapper mAudioAttributesWrapper;

   private AudioAttributesCompat() {
      this.mUsage = 0;
      this.mContentType = 0;
      this.mFlags = 0;
   }

   public int getVolumeControlStream() {
      if (this == null) {
         throw new IllegalArgumentException("Invalid null audio attributes");
      } else {
         return VERSION.SDK_INT >= 26 && !sForceLegacyBehavior && this.unwrap() != null ? ((AudioAttributes)this.unwrap()).getVolumeControlStream() : toVolumeStreamType(true, this);
      }
   }

   @Nullable
   public Object unwrap() {
      return this.mAudioAttributesWrapper != null ? this.mAudioAttributesWrapper.unwrap() : null;
   }

   public int getLegacyStreamType() {
      if (this.mLegacyStream != null) {
         return this.mLegacyStream.intValue();
      } else {
         return VERSION.SDK_INT >= 21 && !sForceLegacyBehavior ? AudioAttributesCompatApi21.toLegacyStreamType(this.mAudioAttributesWrapper) : toVolumeStreamType(false, this.mFlags, this.mUsage);
      }
   }

   @Nullable
   public static AudioAttributesCompat wrap(@NonNull Object aa) {
      if (VERSION.SDK_INT >= 21 && !sForceLegacyBehavior) {
         AudioAttributesCompat aac = new AudioAttributesCompat();
         aac.mAudioAttributesWrapper = AudioAttributesCompatApi21.Wrapper.wrap((AudioAttributes)aa);
         return aac;
      } else {
         return null;
      }
   }

   public int getContentType() {
      return VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && this.mAudioAttributesWrapper != null ? this.mAudioAttributesWrapper.unwrap().getContentType() : this.mContentType;
   }

   public int getUsage() {
      return VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && this.mAudioAttributesWrapper != null ? this.mAudioAttributesWrapper.unwrap().getUsage() : this.mUsage;
   }

   public int getFlags() {
      if (VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && this.mAudioAttributesWrapper != null) {
         return this.mAudioAttributesWrapper.unwrap().getFlags();
      } else {
         int flags = this.mFlags;
         int legacyStream = this.getLegacyStreamType();
         if (legacyStream == 6) {
            flags |= 4;
         } else if (legacyStream == 7) {
            flags |= 1;
         }

         return flags & 273;
      }
   }

   public int hashCode() {
      return VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && this.mAudioAttributesWrapper != null ? this.mAudioAttributesWrapper.unwrap().hashCode() : Arrays.hashCode(new Object[]{this.mContentType, this.mFlags, this.mUsage, this.mLegacyStream});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
      if (this.unwrap() != null) {
         sb.append(" audioattributes=").append(this.unwrap());
      } else {
         if (this.mLegacyStream != null) {
            sb.append(" stream=").append(this.mLegacyStream);
            sb.append(" derived");
         }

         sb.append(" usage=").append(this.usageToString()).append(" content=").append(this.mContentType).append(" flags=0x").append(Integer.toHexString(this.mFlags).toUpperCase());
      }

      return sb.toString();
   }

   String usageToString() {
      return usageToString(this.mUsage);
   }

   static String usageToString(int usage) {
      switch(usage) {
      case 0:
         return new String("USAGE_UNKNOWN");
      case 1:
         return new String("USAGE_MEDIA");
      case 2:
         return new String("USAGE_VOICE_COMMUNICATION");
      case 3:
         return new String("USAGE_VOICE_COMMUNICATION_SIGNALLING");
      case 4:
         return new String("USAGE_ALARM");
      case 5:
         return new String("USAGE_NOTIFICATION");
      case 6:
         return new String("USAGE_NOTIFICATION_RINGTONE");
      case 7:
         return new String("USAGE_NOTIFICATION_COMMUNICATION_REQUEST");
      case 8:
         return new String("USAGE_NOTIFICATION_COMMUNICATION_INSTANT");
      case 9:
         return new String("USAGE_NOTIFICATION_COMMUNICATION_DELAYED");
      case 10:
         return new String("USAGE_NOTIFICATION_EVENT");
      case 11:
         return new String("USAGE_ASSISTANCE_ACCESSIBILITY");
      case 12:
         return new String("USAGE_ASSISTANCE_NAVIGATION_GUIDANCE");
      case 13:
         return new String("USAGE_ASSISTANCE_SONIFICATION");
      case 14:
         return new String("USAGE_GAME");
      case 15:
      default:
         return new String("unknown usage " + usage);
      case 16:
         return new String("USAGE_ASSISTANT");
      }
   }

   private static int usageForStreamType(int streamType) {
      switch(streamType) {
      case 0:
         return 2;
      case 1:
      case 7:
         return 13;
      case 2:
         return 6;
      case 3:
         return 1;
      case 4:
         return 4;
      case 5:
         return 5;
      case 6:
         return 2;
      case 8:
         return 3;
      case 9:
      default:
         return 0;
      case 10:
         return 11;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static void setForceLegacyBehavior(boolean force) {
      sForceLegacyBehavior = force;
   }

   static int toVolumeStreamType(boolean fromGetVolumeControlStream, AudioAttributesCompat aa) {
      return toVolumeStreamType(fromGetVolumeControlStream, aa.getFlags(), aa.getUsage());
   }

   static int toVolumeStreamType(boolean fromGetVolumeControlStream, int flags, int usage) {
      if ((flags & 1) == 1) {
         return fromGetVolumeControlStream ? 1 : 7;
      } else if ((flags & 4) == 4) {
         return fromGetVolumeControlStream ? 0 : 6;
      } else {
         switch(usage) {
         case 0:
            return fromGetVolumeControlStream ? Integer.MIN_VALUE : 3;
         case 1:
         case 12:
         case 14:
         case 16:
            return 3;
         case 2:
            return 0;
         case 3:
            return fromGetVolumeControlStream ? 0 : 8;
         case 4:
            return 4;
         case 5:
         case 7:
         case 8:
         case 9:
         case 10:
            return 5;
         case 6:
            return 2;
         case 11:
            return 10;
         case 13:
            return 1;
         case 15:
         default:
            if (fromGetVolumeControlStream) {
               throw new IllegalArgumentException("Unknown usage value " + usage + " in audio attributes");
            } else {
               return 3;
            }
         }
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         AudioAttributesCompat that = (AudioAttributesCompat)o;
         if (VERSION.SDK_INT >= 21 && !sForceLegacyBehavior && this.mAudioAttributesWrapper != null) {
            return this.mAudioAttributesWrapper.unwrap().equals(that.unwrap());
         } else {
            boolean var10000;
            label51: {
               if (this.mContentType == that.getContentType() && this.mFlags == that.getFlags() && this.mUsage == that.getUsage()) {
                  if (this.mLegacyStream != null) {
                     if (this.mLegacyStream.equals(that.mLegacyStream)) {
                        break label51;
                     }
                  } else if (that.mLegacyStream == null) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   AudioAttributesCompat(Object x0) {
      this();
   }

   static {
      SUPPRESSIBLE_USAGES.put(5, 1);
      SUPPRESSIBLE_USAGES.put(6, 2);
      SUPPRESSIBLE_USAGES.put(7, 2);
      SUPPRESSIBLE_USAGES.put(8, 1);
      SUPPRESSIBLE_USAGES.put(9, 1);
      SUPPRESSIBLE_USAGES.put(10, 1);
      SDK_USAGES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface AttributeContentType {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface AttributeUsage {
   }

   private abstract static class AudioManagerHidden {
      public static final int STREAM_BLUETOOTH_SCO = 6;
      public static final int STREAM_SYSTEM_ENFORCED = 7;
      public static final int STREAM_TTS = 9;
      public static final int STREAM_ACCESSIBILITY = 10;
   }

   public static class Builder {
      private int mUsage = 0;
      private int mContentType = 0;
      private int mFlags = 0;
      private Integer mLegacyStream;
      private Object mAAObject;

      public Builder() {
      }

      public Builder(AudioAttributesCompat aa) {
         this.mUsage = aa.mUsage;
         this.mContentType = aa.mContentType;
         this.mFlags = aa.mFlags;
         this.mLegacyStream = aa.mLegacyStream;
         this.mAAObject = aa.unwrap();
      }

      public AudioAttributesCompat build() {
         if (!AudioAttributesCompat.sForceLegacyBehavior && VERSION.SDK_INT >= 21) {
            if (this.mAAObject != null) {
               return AudioAttributesCompat.wrap(this.mAAObject);
            } else {
               android.media.AudioAttributes.Builder api21Builder = (new android.media.AudioAttributes.Builder()).setContentType(this.mContentType).setFlags(this.mFlags).setUsage(this.mUsage);
               if (this.mLegacyStream != null) {
                  api21Builder.setLegacyStreamType(this.mLegacyStream.intValue());
               }

               return AudioAttributesCompat.wrap(api21Builder.build());
            }
         } else {
            AudioAttributesCompat aac = new AudioAttributesCompat();
            aac.mContentType = this.mContentType;
            aac.mFlags = this.mFlags;
            aac.mUsage = this.mUsage;
            aac.mLegacyStream = this.mLegacyStream;
            aac.mAudioAttributesWrapper = null;
            return aac;
         }
      }

      public AudioAttributesCompat.Builder setUsage(int usage) {
         switch(usage) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
            this.mUsage = usage;
            break;
         case 16:
            if (!AudioAttributesCompat.sForceLegacyBehavior && VERSION.SDK_INT > 25) {
               this.mUsage = usage;
            } else {
               this.mUsage = 12;
            }
            break;
         default:
            this.mUsage = 0;
         }

         return this;
      }

      public AudioAttributesCompat.Builder setContentType(int contentType) {
         switch(contentType) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
            this.mContentType = contentType;
            break;
         default:
            this.mUsage = 0;
         }

         return this;
      }

      public AudioAttributesCompat.Builder setFlags(int flags) {
         flags &= 1023;
         this.mFlags |= flags;
         return this;
      }

      public AudioAttributesCompat.Builder setLegacyStreamType(int streamType) {
         if (streamType == 10) {
            throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
         } else {
            this.mLegacyStream = streamType;
            this.mUsage = AudioAttributesCompat.usageForStreamType(streamType);
            return this;
         }
      }
   }
}
