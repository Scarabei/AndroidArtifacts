package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;

public abstract class AbsSavedState implements Parcelable {
   public static final AbsSavedState EMPTY_STATE = new AbsSavedState() {
   };
   private final Parcelable mSuperState;
   public static final Creator CREATOR = new ClassLoaderCreator() {
      public AbsSavedState createFromParcel(Parcel in, ClassLoader loader) {
         Parcelable superState = in.readParcelable(loader);
         if (superState != null) {
            throw new IllegalStateException("superState must be null");
         } else {
            return AbsSavedState.EMPTY_STATE;
         }
      }

      public AbsSavedState createFromParcel(Parcel in) {
         return this.createFromParcel(in, (ClassLoader)null);
      }

      public AbsSavedState[] newArray(int size) {
         return new AbsSavedState[size];
      }
   };

   private AbsSavedState() {
      this.mSuperState = null;
   }

   protected AbsSavedState(Parcelable superState) {
      if (superState == null) {
         throw new IllegalArgumentException("superState must not be null");
      } else {
         this.mSuperState = superState != EMPTY_STATE ? superState : null;
      }
   }

   protected AbsSavedState(Parcel source) {
      this(source, (ClassLoader)null);
   }

   protected AbsSavedState(Parcel source, ClassLoader loader) {
      Parcelable superState = source.readParcelable(loader);
      this.mSuperState = (Parcelable)(superState != null ? superState : EMPTY_STATE);
   }

   public final Parcelable getSuperState() {
      return this.mSuperState;
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel dest, int flags) {
      dest.writeParcelable(this.mSuperState, flags);
   }

   // $FF: synthetic method
   AbsSavedState(Object x0) {
      this();
   }
}
