package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentManagerState implements Parcelable {
   FragmentState[] mActive;
   int[] mAdded;
   BackStackState[] mBackStack;
   int mPrimaryNavActiveIndex = -1;
   int mNextFragmentIndex;
   public static final Creator CREATOR = new Creator() {
      public FragmentManagerState createFromParcel(Parcel in) {
         return new FragmentManagerState(in);
      }

      public FragmentManagerState[] newArray(int size) {
         return new FragmentManagerState[size];
      }
   };

   public FragmentManagerState() {
   }

   public FragmentManagerState(Parcel in) {
      this.mActive = (FragmentState[])in.createTypedArray(FragmentState.CREATOR);
      this.mAdded = in.createIntArray();
      this.mBackStack = (BackStackState[])in.createTypedArray(BackStackState.CREATOR);
      this.mPrimaryNavActiveIndex = in.readInt();
      this.mNextFragmentIndex = in.readInt();
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel dest, int flags) {
      dest.writeTypedArray(this.mActive, flags);
      dest.writeIntArray(this.mAdded);
      dest.writeTypedArray(this.mBackStack, flags);
      dest.writeInt(this.mPrimaryNavActiveIndex);
      dest.writeInt(this.mNextFragmentIndex);
   }
}
