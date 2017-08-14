package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
   public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
   public static final long BT_FOLDER_TYPE_MIXED = 0L;
   public static final long BT_FOLDER_TYPE_TITLES = 1L;
   public static final long BT_FOLDER_TYPE_ALBUMS = 2L;
   public static final long BT_FOLDER_TYPE_ARTISTS = 3L;
   public static final long BT_FOLDER_TYPE_GENRES = 4L;
   public static final long BT_FOLDER_TYPE_PLAYLISTS = 5L;
   public static final long BT_FOLDER_TYPE_YEARS = 6L;
   public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
   public static final long STATUS_NOT_DOWNLOADED = 0L;
   public static final long STATUS_DOWNLOADING = 1L;
   public static final long STATUS_DOWNLOADED = 2L;
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
   private final String mMediaId;
   private final CharSequence mTitle;
   private final CharSequence mSubtitle;
   private final CharSequence mDescription;
   private final Bitmap mIcon;
   private final Uri mIconUri;
   private final Bundle mExtras;
   private final Uri mMediaUri;
   private Object mDescriptionObj;
   public static final Creator CREATOR = new Creator() {
      public MediaDescriptionCompat createFromParcel(Parcel in) {
         return VERSION.SDK_INT < 21 ? new MediaDescriptionCompat(in) : MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(in));
      }

      public MediaDescriptionCompat[] newArray(int size) {
         return new MediaDescriptionCompat[size];
      }
   };

   MediaDescriptionCompat(String mediaId, CharSequence title, CharSequence subtitle, CharSequence description, Bitmap icon, Uri iconUri, Bundle extras, Uri mediaUri) {
      this.mMediaId = mediaId;
      this.mTitle = title;
      this.mSubtitle = subtitle;
      this.mDescription = description;
      this.mIcon = icon;
      this.mIconUri = iconUri;
      this.mExtras = extras;
      this.mMediaUri = mediaUri;
   }

   MediaDescriptionCompat(Parcel in) {
      this.mMediaId = in.readString();
      this.mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
      this.mSubtitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
      this.mDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
      this.mIcon = (Bitmap)in.readParcelable((ClassLoader)null);
      this.mIconUri = (Uri)in.readParcelable((ClassLoader)null);
      this.mExtras = in.readBundle();
      this.mMediaUri = (Uri)in.readParcelable((ClassLoader)null);
   }

   @Nullable
   public String getMediaId() {
      return this.mMediaId;
   }

   @Nullable
   public CharSequence getTitle() {
      return this.mTitle;
   }

   @Nullable
   public CharSequence getSubtitle() {
      return this.mSubtitle;
   }

   @Nullable
   public CharSequence getDescription() {
      return this.mDescription;
   }

   @Nullable
   public Bitmap getIconBitmap() {
      return this.mIcon;
   }

   @Nullable
   public Uri getIconUri() {
      return this.mIconUri;
   }

   @Nullable
   public Bundle getExtras() {
      return this.mExtras;
   }

   @Nullable
   public Uri getMediaUri() {
      return this.mMediaUri;
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel dest, int flags) {
      if (VERSION.SDK_INT < 21) {
         dest.writeString(this.mMediaId);
         TextUtils.writeToParcel(this.mTitle, dest, flags);
         TextUtils.writeToParcel(this.mSubtitle, dest, flags);
         TextUtils.writeToParcel(this.mDescription, dest, flags);
         dest.writeParcelable(this.mIcon, flags);
         dest.writeParcelable(this.mIconUri, flags);
         dest.writeBundle(this.mExtras);
         dest.writeParcelable(this.mMediaUri, flags);
      } else {
         MediaDescriptionCompatApi21.writeToParcel(this.getMediaDescription(), dest, flags);
      }

   }

   public String toString() {
      return this.mTitle + ", " + this.mSubtitle + ", " + this.mDescription;
   }

   public Object getMediaDescription() {
      if (this.mDescriptionObj == null && VERSION.SDK_INT >= 21) {
         Object bob = MediaDescriptionCompatApi21.Builder.newInstance();
         MediaDescriptionCompatApi21.Builder.setMediaId(bob, this.mMediaId);
         MediaDescriptionCompatApi21.Builder.setTitle(bob, this.mTitle);
         MediaDescriptionCompatApi21.Builder.setSubtitle(bob, this.mSubtitle);
         MediaDescriptionCompatApi21.Builder.setDescription(bob, this.mDescription);
         MediaDescriptionCompatApi21.Builder.setIconBitmap(bob, this.mIcon);
         MediaDescriptionCompatApi21.Builder.setIconUri(bob, this.mIconUri);
         Bundle extras = this.mExtras;
         if (VERSION.SDK_INT < 23 && this.mMediaUri != null) {
            if (extras == null) {
               extras = new Bundle();
               extras.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }

            extras.putParcelable("android.support.v4.media.description.MEDIA_URI", this.mMediaUri);
         }

         MediaDescriptionCompatApi21.Builder.setExtras(bob, extras);
         if (VERSION.SDK_INT >= 23) {
            MediaDescriptionCompatApi23.Builder.setMediaUri(bob, this.mMediaUri);
         }

         this.mDescriptionObj = MediaDescriptionCompatApi21.Builder.build(bob);
         return this.mDescriptionObj;
      } else {
         return this.mDescriptionObj;
      }
   }

   public static MediaDescriptionCompat fromMediaDescription(Object descriptionObj) {
      if (descriptionObj != null && VERSION.SDK_INT >= 21) {
         MediaDescriptionCompat.Builder bob = new MediaDescriptionCompat.Builder();
         bob.setMediaId(MediaDescriptionCompatApi21.getMediaId(descriptionObj));
         bob.setTitle(MediaDescriptionCompatApi21.getTitle(descriptionObj));
         bob.setSubtitle(MediaDescriptionCompatApi21.getSubtitle(descriptionObj));
         bob.setDescription(MediaDescriptionCompatApi21.getDescription(descriptionObj));
         bob.setIconBitmap(MediaDescriptionCompatApi21.getIconBitmap(descriptionObj));
         bob.setIconUri(MediaDescriptionCompatApi21.getIconUri(descriptionObj));
         Bundle extras = MediaDescriptionCompatApi21.getExtras(descriptionObj);
         Uri mediaUri = extras == null ? null : (Uri)extras.getParcelable("android.support.v4.media.description.MEDIA_URI");
         if (mediaUri != null) {
            if (extras.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && extras.size() == 2) {
               extras = null;
            } else {
               extras.remove("android.support.v4.media.description.MEDIA_URI");
               extras.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            }
         }

         bob.setExtras(extras);
         if (mediaUri != null) {
            bob.setMediaUri(mediaUri);
         } else if (VERSION.SDK_INT >= 23) {
            bob.setMediaUri(MediaDescriptionCompatApi23.getMediaUri(descriptionObj));
         }

         MediaDescriptionCompat descriptionCompat = bob.build();
         descriptionCompat.mDescriptionObj = descriptionObj;
         return descriptionCompat;
      } else {
         return null;
      }
   }

   public static final class Builder {
      private String mMediaId;
      private CharSequence mTitle;
      private CharSequence mSubtitle;
      private CharSequence mDescription;
      private Bitmap mIcon;
      private Uri mIconUri;
      private Bundle mExtras;
      private Uri mMediaUri;

      public MediaDescriptionCompat.Builder setMediaId(@Nullable String mediaId) {
         this.mMediaId = mediaId;
         return this;
      }

      public MediaDescriptionCompat.Builder setTitle(@Nullable CharSequence title) {
         this.mTitle = title;
         return this;
      }

      public MediaDescriptionCompat.Builder setSubtitle(@Nullable CharSequence subtitle) {
         this.mSubtitle = subtitle;
         return this;
      }

      public MediaDescriptionCompat.Builder setDescription(@Nullable CharSequence description) {
         this.mDescription = description;
         return this;
      }

      public MediaDescriptionCompat.Builder setIconBitmap(@Nullable Bitmap icon) {
         this.mIcon = icon;
         return this;
      }

      public MediaDescriptionCompat.Builder setIconUri(@Nullable Uri iconUri) {
         this.mIconUri = iconUri;
         return this;
      }

      public MediaDescriptionCompat.Builder setExtras(@Nullable Bundle extras) {
         this.mExtras = extras;
         return this;
      }

      public MediaDescriptionCompat.Builder setMediaUri(@Nullable Uri mediaUri) {
         this.mMediaUri = mediaUri;
         return this;
      }

      public MediaDescriptionCompat build() {
         return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
      }
   }
}
