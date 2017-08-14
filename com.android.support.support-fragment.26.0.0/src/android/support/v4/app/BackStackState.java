package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class BackStackState implements Parcelable {
   final int[] mOps;
   final int mTransition;
   final int mTransitionStyle;
   final String mName;
   final int mIndex;
   final int mBreadCrumbTitleRes;
   final CharSequence mBreadCrumbTitleText;
   final int mBreadCrumbShortTitleRes;
   final CharSequence mBreadCrumbShortTitleText;
   final ArrayList mSharedElementSourceNames;
   final ArrayList mSharedElementTargetNames;
   final boolean mReorderingAllowed;
   public static final Creator CREATOR = new Creator() {
      public BackStackState createFromParcel(Parcel in) {
         return new BackStackState(in);
      }

      public BackStackState[] newArray(int size) {
         return new BackStackState[size];
      }
   };

   public BackStackState(BackStackRecord bse) {
      int numOps = bse.mOps.size();
      this.mOps = new int[numOps * 6];
      if (!bse.mAddToBackStack) {
         throw new IllegalStateException("Not on back stack");
      } else {
         int pos = 0;

         for(int opNum = 0; opNum < numOps; ++opNum) {
            BackStackRecord.Op op = (BackStackRecord.Op)bse.mOps.get(opNum);
            this.mOps[pos++] = op.cmd;
            this.mOps[pos++] = op.fragment != null ? op.fragment.mIndex : -1;
            this.mOps[pos++] = op.enterAnim;
            this.mOps[pos++] = op.exitAnim;
            this.mOps[pos++] = op.popEnterAnim;
            this.mOps[pos++] = op.popExitAnim;
         }

         this.mTransition = bse.mTransition;
         this.mTransitionStyle = bse.mTransitionStyle;
         this.mName = bse.mName;
         this.mIndex = bse.mIndex;
         this.mBreadCrumbTitleRes = bse.mBreadCrumbTitleRes;
         this.mBreadCrumbTitleText = bse.mBreadCrumbTitleText;
         this.mBreadCrumbShortTitleRes = bse.mBreadCrumbShortTitleRes;
         this.mBreadCrumbShortTitleText = bse.mBreadCrumbShortTitleText;
         this.mSharedElementSourceNames = bse.mSharedElementSourceNames;
         this.mSharedElementTargetNames = bse.mSharedElementTargetNames;
         this.mReorderingAllowed = bse.mReorderingAllowed;
      }
   }

   public BackStackState(Parcel in) {
      this.mOps = in.createIntArray();
      this.mTransition = in.readInt();
      this.mTransitionStyle = in.readInt();
      this.mName = in.readString();
      this.mIndex = in.readInt();
      this.mBreadCrumbTitleRes = in.readInt();
      this.mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
      this.mBreadCrumbShortTitleRes = in.readInt();
      this.mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
      this.mSharedElementSourceNames = in.createStringArrayList();
      this.mSharedElementTargetNames = in.createStringArrayList();
      this.mReorderingAllowed = in.readInt() != 0;
   }

   public BackStackRecord instantiate(FragmentManagerImpl fm) {
      BackStackRecord bse = new BackStackRecord(fm);
      int pos = 0;

      for(int num = 0; pos < this.mOps.length; ++num) {
         BackStackRecord.Op op = new BackStackRecord.Op();
         op.cmd = this.mOps[pos++];
         if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Instantiate " + bse + " op #" + num + " base fragment #" + this.mOps[pos]);
         }

         int findex = this.mOps[pos++];
         if (findex >= 0) {
            Fragment f = (Fragment)fm.mActive.get(findex);
            op.fragment = f;
         } else {
            op.fragment = null;
         }

         op.enterAnim = this.mOps[pos++];
         op.exitAnim = this.mOps[pos++];
         op.popEnterAnim = this.mOps[pos++];
         op.popExitAnim = this.mOps[pos++];
         bse.mEnterAnim = op.enterAnim;
         bse.mExitAnim = op.exitAnim;
         bse.mPopEnterAnim = op.popEnterAnim;
         bse.mPopExitAnim = op.popExitAnim;
         bse.addOp(op);
      }

      bse.mTransition = this.mTransition;
      bse.mTransitionStyle = this.mTransitionStyle;
      bse.mName = this.mName;
      bse.mIndex = this.mIndex;
      bse.mAddToBackStack = true;
      bse.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
      bse.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
      bse.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
      bse.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
      bse.mSharedElementSourceNames = this.mSharedElementSourceNames;
      bse.mSharedElementTargetNames = this.mSharedElementTargetNames;
      bse.mReorderingAllowed = this.mReorderingAllowed;
      bse.bumpBackStackNesting(1);
      return bse;
   }

   public int describeContents() {
      return 0;
   }

   public void writeToParcel(Parcel dest, int flags) {
      dest.writeIntArray(this.mOps);
      dest.writeInt(this.mTransition);
      dest.writeInt(this.mTransitionStyle);
      dest.writeString(this.mName);
      dest.writeInt(this.mIndex);
      dest.writeInt(this.mBreadCrumbTitleRes);
      TextUtils.writeToParcel(this.mBreadCrumbTitleText, dest, 0);
      dest.writeInt(this.mBreadCrumbShortTitleRes);
      TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, dest, 0);
      dest.writeStringList(this.mSharedElementSourceNames);
      dest.writeStringList(this.mSharedElementTargetNames);
      dest.writeInt(this.mReorderingAllowed ? 1 : 0);
   }
}
