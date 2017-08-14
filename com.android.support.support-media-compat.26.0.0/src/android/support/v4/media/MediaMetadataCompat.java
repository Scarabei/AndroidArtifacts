package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Set;

public final class MediaMetadataCompat implements Parcelable {
   private static final String TAG = "MediaMetadata";
   public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
   public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
   public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
   public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
   public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
   public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
   public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
   public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
   public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
   public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
   public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
   public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
   public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
   public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
   public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
   public static final String METADATA_KEY_ART = "android.media.metadata.ART";
   public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
   public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
   public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
   public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
   public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
   public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
   public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
   public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
   public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
   public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
   public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
   public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
   public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
   public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
   public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
   static final int METADATA_TYPE_LONG = 0;
   static final int METADATA_TYPE_TEXT = 1;
   static final int METADATA_TYPE_BITMAP = 2;
   static final int METADATA_TYPE_RATING = 3;
   static final ArrayMap METADATA_KEYS_TYPE = new ArrayMap();
   private static final String[] PREFERRED_DESCRIPTION_ORDER;
   private static final String[] PREFERRED_BITMAP_ORDER;
   private static final String[] PREFERRED_URI_ORDER;
   final Bundle mBundle;
   private Object mMetadataObj;
   private MediaDescriptionCompat mDescription;
   public static final Creator CREATOR;

   MediaMetadataCompat(Bundle bundle) {
      this.mBundle = new Bundle(bundle);
   }

   MediaMetadataCompat(Parcel in) {
      this.mBundle = in.readBundle();
   }

   public boolean containsKey(String key) {
      return this.mBundle.containsKey(key);
   }

   public CharSequence getText(String key) {
      return this.mBundle.getCharSequence(key);
   }

   public String getString(String key) {
      CharSequence text = this.mBundle.getCharSequence(key);
      return text != null ? text.toString() : null;
   }

   public long getLong(String key) {
      return this.mBundle.getLong(key, 0L);
   }

   public RatingCompat getRating(String key) {
      RatingCompat rating = null;

      try {
         if (VERSION.SDK_INT >= 19) {
            rating = RatingCompat.fromRating(this.mBundle.getParcelable(key));
         } else {
            rating = (RatingCompat)this.mBundle.getParcelable(key);
         }
      } catch (Exception var4) {
         Log.w("MediaMetadata", "Failed to retrieve a key as Rating.", var4);
      }

      return rating;
   }

   public Bitmap getBitmap(String key) {
      Bitmap bmp = null;

      try {
         bmp = (Bitmap)this.mBundle.getParcelable(key);
      } catch (Exception var4) {
         Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", var4);
      }

      return bmp;
   }

   public MediaDescriptionCompat getDescription() {
      if (this.mDescription != null) {
         return this.mDescription;
      } else {
         String mediaId = this.getString("android.media.metadata.MEDIA_ID");
         CharSequence[] text = new CharSequence[3];
         Bitmap icon = null;
         Uri iconUri = null;
         CharSequence displayText = this.getText("android.media.metadata.DISPLAY_TITLE");
         int i;
         if (!TextUtils.isEmpty(displayText)) {
            text[0] = displayText;
            text[1] = this.getText("android.media.metadata.DISPLAY_SUBTITLE");
            text[2] = this.getText("android.media.metadata.DISPLAY_DESCRIPTION");
         } else {
            i = 0;
            int keyIndex = 0;

            while(i < text.length && keyIndex < PREFERRED_DESCRIPTION_ORDER.length) {
               CharSequence next = this.getText(PREFERRED_DESCRIPTION_ORDER[keyIndex++]);
               if (!TextUtils.isEmpty(next)) {
                  text[i++] = next;
               }
            }
         }

         for(i = 0; i < PREFERRED_BITMAP_ORDER.length; ++i) {
            Bitmap next = this.getBitmap(PREFERRED_BITMAP_ORDER[i]);
            if (next != null) {
               icon = next;
               break;
            }
         }

         String mediaUriStr;
         for(i = 0; i < PREFERRED_URI_ORDER.length; ++i) {
            mediaUriStr = this.getString(PREFERRED_URI_ORDER[i]);
            if (!TextUtils.isEmpty(mediaUriStr)) {
               iconUri = Uri.parse(mediaUriStr);
               break;
            }
         }

         Uri mediaUri = null;
         mediaUriStr = this.getString("android.media.metadata.MEDIA_URI");
         if (!TextUtils.isEmpty(mediaUriStr)) {
            mediaUri = Uri.parse(mediaUriStr);
         }

         MediaDescriptionCompat.Builder bob = new MediaDescriptionCompat.Builder();
         bob.setMediaId(mediaId);
         bob.setTitle(text[0]);
         bob.setSubtitle(text[1]);
         bob.setDescription(text[2]);
         bob.setIconBitmap(icon);
         bob.setIconUri(iconUri);
         bob.setMediaUri(mediaUri);
         Bundle bundle = new Bundle();
         if (this.mBundle.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            bundle.putLong("android.media.extra.BT_FOLDER_TYPE", this.getLong("android.media.metadata.BT_FOLDER_TYPE"));
         }

         if (this.mBundle.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            bundle.putLong("android.media.extra.DOWNLOAD_STATUS", this.getLong("android.media.metadata.DOWNLOAD_STATUS"));
         }

         if (!bundle.isEmpty()) {
            bob.setExtras(bundle);
         }

         this.mDescription = bob.build();
         return this.mDescription;
      }
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel dest, int flags) {
      dest.writeBundle(this.mBundle);
   }

   public int size() {
      return this.mBundle.size();
   }

   public Set keySet() {
      return this.mBundle.keySet();
   }

   public Bundle getBundle() {
      return this.mBundle;
   }

   public static MediaMetadataCompat fromMediaMetadata(Object metadataObj) {
      if (metadataObj != null && VERSION.SDK_INT >= 21) {
         Parcel p = Parcel.obtain();
         MediaMetadataCompatApi21.writeToParcel(metadataObj, p, 0);
         p.setDataPosition(0);
         MediaMetadataCompat metadata = (MediaMetadataCompat)CREATOR.createFromParcel(p);
         p.recycle();
         metadata.mMetadataObj = metadataObj;
         return metadata;
      } else {
         return null;
      }
   }

   public Object getMediaMetadata() {
      if (this.mMetadataObj == null && VERSION.SDK_INT >= 21) {
         Parcel p = Parcel.obtain();
         this.writeToParcel(p, 0);
         p.setDataPosition(0);
         this.mMetadataObj = MediaMetadataCompatApi21.createFromParcel(p);
         p.recycle();
      }

      return this.mMetadataObj;
   }

   static {
      METADATA_KEYS_TYPE.put("android.media.metadata.TITLE", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.ARTIST", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.DURATION", Integer.valueOf(0));
      METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.AUTHOR", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.WRITER", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.COMPOSER", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.COMPILATION", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.DATE", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.YEAR", Integer.valueOf(0));
      METADATA_KEYS_TYPE.put("android.media.metadata.GENRE", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.TRACK_NUMBER", Integer.valueOf(0));
      METADATA_KEYS_TYPE.put("android.media.metadata.NUM_TRACKS", Integer.valueOf(0));
      METADATA_KEYS_TYPE.put("android.media.metadata.DISC_NUMBER", Integer.valueOf(0));
      METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ARTIST", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.ART", Integer.valueOf(2));
      METADATA_KEYS_TYPE.put("android.media.metadata.ART_URI", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART", Integer.valueOf(2));
      METADATA_KEYS_TYPE.put("android.media.metadata.ALBUM_ART_URI", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.USER_RATING", Integer.valueOf(3));
      METADATA_KEYS_TYPE.put("android.media.metadata.RATING", Integer.valueOf(3));
      METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_TITLE", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_SUBTITLE", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_DESCRIPTION", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON", Integer.valueOf(2));
      METADATA_KEYS_TYPE.put("android.media.metadata.DISPLAY_ICON_URI", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_ID", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.BT_FOLDER_TYPE", Integer.valueOf(0));
      METADATA_KEYS_TYPE.put("android.media.metadata.MEDIA_URI", Integer.valueOf(1));
      METADATA_KEYS_TYPE.put("android.media.metadata.ADVERTISEMENT", Integer.valueOf(0));
      METADATA_KEYS_TYPE.put("android.media.metadata.DOWNLOAD_STATUS", Integer.valueOf(0));
      PREFERRED_DESCRIPTION_ORDER = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
      PREFERRED_BITMAP_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
      PREFERRED_URI_ORDER = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
      CREATOR = new Creator() {
         public MediaMetadataCompat createFromParcel(Parcel in) {
            return new MediaMetadataCompat(in);
         }

         public MediaMetadataCompat[] newArray(int size) {
            return new MediaMetadataCompat[size];
         }
      };
   }

   public static final class Builder {
      private final Bundle mBundle;

      public Builder() {
         this.mBundle = new Bundle();
      }

      public Builder(MediaMetadataCompat source) {
         this.mBundle = new Bundle(source.mBundle);
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public Builder(MediaMetadataCompat source, int maxBitmapSize) {
         this(source);
         Iterator var3 = this.mBundle.keySet().iterator();

         while(true) {
            String key;
            Bitmap bmp;
            do {
               Object value;
               do {
                  if (!var3.hasNext()) {
                     return;
                  }

                  key = (String)var3.next();
                  value = this.mBundle.get(key);
               } while(!(value instanceof Bitmap));

               bmp = (Bitmap)value;
            } while(bmp.getHeight() <= maxBitmapSize && bmp.getWidth() <= maxBitmapSize);

            this.putBitmap(key, this.scaleBitmap(bmp, maxBitmapSize));
         }
      }

      public MediaMetadataCompat.Builder putText(String key, CharSequence value) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(key)).intValue() != 1) {
            throw new IllegalArgumentException("The " + key + " key cannot be used to put a CharSequence");
         } else {
            this.mBundle.putCharSequence(key, value);
            return this;
         }
      }

      public MediaMetadataCompat.Builder putString(String key, String value) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(key)).intValue() != 1) {
            throw new IllegalArgumentException("The " + key + " key cannot be used to put a String");
         } else {
            this.mBundle.putCharSequence(key, value);
            return this;
         }
      }

      public MediaMetadataCompat.Builder putLong(String key, long value) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(key)).intValue() != 0) {
            throw new IllegalArgumentException("The " + key + " key cannot be used to put a long");
         } else {
            this.mBundle.putLong(key, value);
            return this;
         }
      }

      public MediaMetadataCompat.Builder putRating(String key, RatingCompat value) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(key)).intValue() != 3) {
            throw new IllegalArgumentException("The " + key + " key cannot be used to put a Rating");
         } else {
            if (VERSION.SDK_INT >= 19) {
               this.mBundle.putParcelable(key, (Parcelable)value.getRating());
            } else {
               this.mBundle.putParcelable(key, value);
            }

            return this;
         }
      }

      public MediaMetadataCompat.Builder putBitmap(String key, Bitmap value) {
         if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(key) && ((Integer)MediaMetadataCompat.METADATA_KEYS_TYPE.get(key)).intValue() != 2) {
            throw new IllegalArgumentException("The " + key + " key cannot be used to put a Bitmap");
         } else {
            this.mBundle.putParcelable(key, value);
            return this;
         }
      }

      public MediaMetadataCompat build() {
         return new MediaMetadataCompat(this.mBundle);
      }

      private Bitmap scaleBitmap(Bitmap bmp, int maxSize) {
         float maxSizeF = (float)maxSize;
         float widthScale = maxSizeF / (float)bmp.getWidth();
         float heightScale = maxSizeF / (float)bmp.getHeight();
         float scale = Math.min(widthScale, heightScale);
         int height = (int)((float)bmp.getHeight() * scale);
         int width = (int)((float)bmp.getWidth() * scale);
         return Bitmap.createScaledBitmap(bmp, width, height, true);
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface RatingKey {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface BitmapKey {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface LongKey {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface TextKey {
   }
}
