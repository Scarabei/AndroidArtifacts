package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Arrays;

class BundleUtil {
   public static Bundle[] getBundleArrayFromBundle(Bundle bundle, String key) {
      Parcelable[] array = bundle.getParcelableArray(key);
      if (!(array instanceof Bundle[]) && array != null) {
         Bundle[] typedArray = (Bundle[])Arrays.copyOf(array, array.length, Bundle[].class);
         bundle.putParcelableArray(key, typedArray);
         return typedArray;
      } else {
         return (Bundle[])((Bundle[])array);
      }
   }
}
