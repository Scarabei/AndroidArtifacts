package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;

/** @deprecated */
@Deprecated
public final class ParcelableCompat {
   /** @deprecated */
   @Deprecated
   public static Creator newCreator(ParcelableCompatCreatorCallbacks callbacks) {
      return new ParcelableCompat.ParcelableCompatCreatorHoneycombMR2(callbacks);
   }

   static class ParcelableCompatCreatorHoneycombMR2 implements ClassLoaderCreator {
      private final ParcelableCompatCreatorCallbacks mCallbacks;

      ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks callbacks) {
         this.mCallbacks = callbacks;
      }

      public Object createFromParcel(Parcel in) {
         return this.mCallbacks.createFromParcel(in, (ClassLoader)null);
      }

      public Object createFromParcel(Parcel in, ClassLoader loader) {
         return this.mCallbacks.createFromParcel(in, loader);
      }

      public Object[] newArray(int size) {
         return this.mCallbacks.newArray(size);
      }
   }
}
