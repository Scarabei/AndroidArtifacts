package android.support.v4.content;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;

public final class MimeTypeFilter {
   private static boolean mimeTypeAgainstFilter(@NonNull String[] mimeTypeParts, @NonNull String[] filterParts) {
      if (filterParts.length != 2) {
         throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
      } else if (!filterParts[0].isEmpty() && !filterParts[1].isEmpty()) {
         if (mimeTypeParts.length != 2) {
            return false;
         } else if (!"*".equals(filterParts[0]) && !filterParts[0].equals(mimeTypeParts[0])) {
            return false;
         } else {
            return "*".equals(filterParts[1]) || filterParts[1].equals(mimeTypeParts[1]);
         }
      } else {
         throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
      }
   }

   public static boolean matches(@Nullable String mimeType, @NonNull String filter) {
      if (mimeType == null) {
         return false;
      } else {
         String[] mimeTypeParts = mimeType.split("/");
         String[] filterParts = filter.split("/");
         return mimeTypeAgainstFilter(mimeTypeParts, filterParts);
      }
   }

   public static String matches(@Nullable String mimeType, @NonNull String[] filters) {
      if (mimeType == null) {
         return null;
      } else {
         String[] mimeTypeParts = mimeType.split("/");
         String[] var3 = filters;
         int var4 = filters.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String filter = var3[var5];
            String[] filterParts = filter.split("/");
            if (mimeTypeAgainstFilter(mimeTypeParts, filterParts)) {
               return filter;
            }
         }

         return null;
      }
   }

   public static String matches(@Nullable String[] mimeTypes, @NonNull String filter) {
      if (mimeTypes == null) {
         return null;
      } else {
         String[] filterParts = filter.split("/");
         String[] var3 = mimeTypes;
         int var4 = mimeTypes.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String mimeType = var3[var5];
            String[] mimeTypeParts = mimeType.split("/");
            if (mimeTypeAgainstFilter(mimeTypeParts, filterParts)) {
               return mimeType;
            }
         }

         return null;
      }
   }

   public static String[] matchesMany(@Nullable String[] mimeTypes, @NonNull String filter) {
      if (mimeTypes == null) {
         return new String[0];
      } else {
         ArrayList list = new ArrayList();
         String[] filterParts = filter.split("/");
         String[] var4 = mimeTypes;
         int var5 = mimeTypes.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String mimeType = var4[var6];
            String[] mimeTypeParts = mimeType.split("/");
            if (mimeTypeAgainstFilter(mimeTypeParts, filterParts)) {
               list.add(mimeType);
            }
         }

         return (String[])list.toArray(new String[list.size()]);
      }
   }
}
