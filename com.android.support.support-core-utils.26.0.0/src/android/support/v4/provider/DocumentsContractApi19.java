package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

@RequiresApi(19)
class DocumentsContractApi19 {
   private static final String TAG = "DocumentFile";
   private static final int FLAG_VIRTUAL_DOCUMENT = 512;

   public static boolean isDocumentUri(Context context, Uri self) {
      return DocumentsContract.isDocumentUri(context, self);
   }

   public static boolean isVirtual(Context context, Uri self) {
      if (!isDocumentUri(context, self)) {
         return false;
      } else {
         return (getFlags(context, self) & 512L) != 0L;
      }
   }

   public static String getName(Context context, Uri self) {
      return queryForString(context, self, "_display_name", (String)null);
   }

   private static String getRawType(Context context, Uri self) {
      return queryForString(context, self, "mime_type", (String)null);
   }

   public static String getType(Context context, Uri self) {
      String rawType = getRawType(context, self);
      return "vnd.android.document/directory".equals(rawType) ? null : rawType;
   }

   public static long getFlags(Context context, Uri self) {
      return queryForLong(context, self, "flags", 0L);
   }

   public static boolean isDirectory(Context context, Uri self) {
      return "vnd.android.document/directory".equals(getRawType(context, self));
   }

   public static boolean isFile(Context context, Uri self) {
      String type = getRawType(context, self);
      return !"vnd.android.document/directory".equals(type) && !TextUtils.isEmpty(type);
   }

   public static long lastModified(Context context, Uri self) {
      return queryForLong(context, self, "last_modified", 0L);
   }

   public static long length(Context context, Uri self) {
      return queryForLong(context, self, "_size", 0L);
   }

   public static boolean canRead(Context context, Uri self) {
      if (context.checkCallingOrSelfUriPermission(self, 1) != 0) {
         return false;
      } else {
         return !TextUtils.isEmpty(getRawType(context, self));
      }
   }

   public static boolean canWrite(Context context, Uri self) {
      if (context.checkCallingOrSelfUriPermission(self, 2) != 0) {
         return false;
      } else {
         String type = getRawType(context, self);
         int flags = queryForInt(context, self, "flags", 0);
         if (TextUtils.isEmpty(type)) {
            return false;
         } else if ((flags & 4) != 0) {
            return true;
         } else if ("vnd.android.document/directory".equals(type) && (flags & 8) != 0) {
            return true;
         } else {
            return !TextUtils.isEmpty(type) && (flags & 2) != 0;
         }
      }
   }

   public static boolean delete(Context context, Uri self) {
      try {
         return DocumentsContract.deleteDocument(context.getContentResolver(), self);
      } catch (Exception var3) {
         return false;
      }
   }

   public static boolean exists(Context context, Uri self) {
      ContentResolver resolver = context.getContentResolver();
      Cursor c = null;

      boolean var5;
      try {
         c = resolver.query(self, new String[]{"document_id"}, (String)null, (String[])null, (String)null);
         boolean var4 = c.getCount() > 0;
         return var4;
      } catch (Exception var9) {
         Log.w("DocumentFile", "Failed query: " + var9);
         var5 = false;
      } finally {
         closeQuietly(c);
      }

      return var5;
   }

   private static String queryForString(Context context, Uri self, String column, String defaultValue) {
      ContentResolver resolver = context.getContentResolver();
      Cursor c = null;

      String var7;
      try {
         c = resolver.query(self, new String[]{column}, (String)null, (String[])null, (String)null);
         String var6;
         if (c.moveToFirst() && !c.isNull(0)) {
            var6 = c.getString(0);
            return var6;
         }

         var6 = defaultValue;
         return var6;
      } catch (Exception var11) {
         Log.w("DocumentFile", "Failed query: " + var11);
         var7 = defaultValue;
      } finally {
         closeQuietly(c);
      }

      return var7;
   }

   private static int queryForInt(Context context, Uri self, String column, int defaultValue) {
      return (int)queryForLong(context, self, column, (long)defaultValue);
   }

   private static long queryForLong(Context context, Uri self, String column, long defaultValue) {
      ContentResolver resolver = context.getContentResolver();
      Cursor c = null;

      long var7;
      try {
         c = resolver.query(self, new String[]{column}, (String)null, (String[])null, (String)null);
         if (!c.moveToFirst() || c.isNull(0)) {
            var7 = defaultValue;
            return var7;
         }

         var7 = c.getLong(0);
      } catch (Exception var13) {
         Log.w("DocumentFile", "Failed query: " + var13);
         long var8 = defaultValue;
         return var8;
      } finally {
         closeQuietly(c);
      }

      return var7;
   }

   private static void closeQuietly(AutoCloseable closeable) {
      if (closeable != null) {
         try {
            closeable.close();
         } catch (RuntimeException var2) {
            throw var2;
         } catch (Exception var3) {
            ;
         }
      }

   }
}
