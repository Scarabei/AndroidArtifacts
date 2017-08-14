package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MediaBrowserCompatUtils {
   public static boolean areSameOptions(Bundle options1, Bundle options2) {
      if (options1 == options2) {
         return true;
      } else if (options1 == null) {
         return options2.getInt("android.media.browse.extra.PAGE", -1) == -1 && options2.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1;
      } else if (options2 == null) {
         return options1.getInt("android.media.browse.extra.PAGE", -1) == -1 && options1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1;
      } else {
         return options1.getInt("android.media.browse.extra.PAGE", -1) == options2.getInt("android.media.browse.extra.PAGE", -1) && options1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == options2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      }
   }

   public static boolean hasDuplicatedItems(Bundle options1, Bundle options2) {
      int page1 = options1 == null ? -1 : options1.getInt("android.media.browse.extra.PAGE", -1);
      int page2 = options2 == null ? -1 : options2.getInt("android.media.browse.extra.PAGE", -1);
      int pageSize1 = options1 == null ? -1 : options1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      int pageSize2 = options2 == null ? -1 : options2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      int startIndex1;
      int endIndex1;
      if (page1 != -1 && pageSize1 != -1) {
         startIndex1 = pageSize1 * page1;
         endIndex1 = startIndex1 + pageSize1 - 1;
      } else {
         startIndex1 = 0;
         endIndex1 = Integer.MAX_VALUE;
      }

      int startIndex2;
      int endIndex2;
      if (page2 != -1 && pageSize2 != -1) {
         startIndex2 = pageSize2 * page2;
         endIndex2 = startIndex2 + pageSize2 - 1;
      } else {
         startIndex2 = 0;
         endIndex2 = Integer.MAX_VALUE;
      }

      if (startIndex1 <= startIndex2 && startIndex2 <= endIndex1) {
         return true;
      } else {
         return startIndex1 <= endIndex2 && endIndex2 <= endIndex1;
      }
   }
}
