package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RatingCompat implements Parcelable {
   private static final String TAG = "Rating";
   public static final int RATING_NONE = 0;
   public static final int RATING_HEART = 1;
   public static final int RATING_THUMB_UP_DOWN = 2;
   public static final int RATING_3_STARS = 3;
   public static final int RATING_4_STARS = 4;
   public static final int RATING_5_STARS = 5;
   public static final int RATING_PERCENTAGE = 6;
   private static final float RATING_NOT_RATED = -1.0F;
   private final int mRatingStyle;
   private final float mRatingValue;
   private Object mRatingObj;
   public static final Creator CREATOR = new Creator() {
      public RatingCompat createFromParcel(Parcel p) {
         return new RatingCompat(p.readInt(), p.readFloat());
      }

      public RatingCompat[] newArray(int size) {
         return new RatingCompat[size];
      }
   };

   RatingCompat(int ratingStyle, float rating) {
      this.mRatingStyle = ratingStyle;
      this.mRatingValue = rating;
   }

   public String toString() {
      return "Rating:style=" + this.mRatingStyle + " rating=" + (this.mRatingValue < 0.0F ? "unrated" : String.valueOf(this.mRatingValue));
   }

   public int describeContents() {
      return this.mRatingStyle;
   }

   public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(this.mRatingStyle);
      dest.writeFloat(this.mRatingValue);
   }

   public static RatingCompat newUnratedRating(int ratingStyle) {
      switch(ratingStyle) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
         return new RatingCompat(ratingStyle, -1.0F);
      default:
         return null;
      }
   }

   public static RatingCompat newHeartRating(boolean hasHeart) {
      return new RatingCompat(1, hasHeart ? 1.0F : 0.0F);
   }

   public static RatingCompat newThumbRating(boolean thumbIsUp) {
      return new RatingCompat(2, thumbIsUp ? 1.0F : 0.0F);
   }

   public static RatingCompat newStarRating(int starRatingStyle, float starRating) {
      float maxRating = -1.0F;
      switch(starRatingStyle) {
      case 3:
         maxRating = 3.0F;
         break;
      case 4:
         maxRating = 4.0F;
         break;
      case 5:
         maxRating = 5.0F;
         break;
      default:
         Log.e("Rating", "Invalid rating style (" + starRatingStyle + ") for a star rating");
         return null;
      }

      if (starRating >= 0.0F && starRating <= maxRating) {
         return new RatingCompat(starRatingStyle, starRating);
      } else {
         Log.e("Rating", "Trying to set out of range star-based rating");
         return null;
      }
   }

   public static RatingCompat newPercentageRating(float percent) {
      if (percent >= 0.0F && percent <= 100.0F) {
         return new RatingCompat(6, percent);
      } else {
         Log.e("Rating", "Invalid percentage-based rating value");
         return null;
      }
   }

   public boolean isRated() {
      return this.mRatingValue >= 0.0F;
   }

   public int getRatingStyle() {
      return this.mRatingStyle;
   }

   public boolean hasHeart() {
      if (this.mRatingStyle != 1) {
         return false;
      } else {
         return this.mRatingValue == 1.0F;
      }
   }

   public boolean isThumbUp() {
      if (this.mRatingStyle != 2) {
         return false;
      } else {
         return this.mRatingValue == 1.0F;
      }
   }

   public float getStarRating() {
      switch(this.mRatingStyle) {
      case 3:
      case 4:
      case 5:
         if (this.isRated()) {
            return this.mRatingValue;
         }
      default:
         return -1.0F;
      }
   }

   public float getPercentRating() {
      return this.mRatingStyle == 6 && this.isRated() ? this.mRatingValue : -1.0F;
   }

   public static RatingCompat fromRating(Object ratingObj) {
      if (ratingObj != null && VERSION.SDK_INT >= 19) {
         int ratingStyle = RatingCompatKitkat.getRatingStyle(ratingObj);
         RatingCompat rating;
         if (RatingCompatKitkat.isRated(ratingObj)) {
            switch(ratingStyle) {
            case 1:
               rating = newHeartRating(RatingCompatKitkat.hasHeart(ratingObj));
               break;
            case 2:
               rating = newThumbRating(RatingCompatKitkat.isThumbUp(ratingObj));
               break;
            case 3:
            case 4:
            case 5:
               rating = newStarRating(ratingStyle, RatingCompatKitkat.getStarRating(ratingObj));
               break;
            case 6:
               rating = newPercentageRating(RatingCompatKitkat.getPercentRating(ratingObj));
               break;
            default:
               return null;
            }
         } else {
            rating = newUnratedRating(ratingStyle);
         }

         rating.mRatingObj = ratingObj;
         return rating;
      } else {
         return null;
      }
   }

   public Object getRating() {
      if (this.mRatingObj == null && VERSION.SDK_INT >= 19) {
         if (this.isRated()) {
            switch(this.mRatingStyle) {
            case 1:
               this.mRatingObj = RatingCompatKitkat.newHeartRating(this.hasHeart());
               break;
            case 2:
               this.mRatingObj = RatingCompatKitkat.newThumbRating(this.isThumbUp());
               break;
            case 3:
            case 4:
            case 5:
               this.mRatingObj = RatingCompatKitkat.newStarRating(this.mRatingStyle, this.getStarRating());
               break;
            case 6:
               this.mRatingObj = RatingCompatKitkat.newPercentageRating(this.getPercentRating());
               break;
            default:
               return null;
            }
         } else {
            this.mRatingObj = RatingCompatKitkat.newUnratedRating(this.mRatingStyle);
         }
      }

      return this.mRatingObj;
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface StarStyle {
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface Style {
   }
}
