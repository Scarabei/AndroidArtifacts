package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import java.io.File;

public abstract class DocumentFile {
   static final String TAG = "DocumentFile";
   private final DocumentFile mParent;

   DocumentFile(DocumentFile parent) {
      this.mParent = parent;
   }

   public static DocumentFile fromFile(File file) {
      return new RawDocumentFile((DocumentFile)null, file);
   }

   public static DocumentFile fromSingleUri(Context context, Uri singleUri) {
      return VERSION.SDK_INT >= 19 ? new SingleDocumentFile((DocumentFile)null, context, singleUri) : null;
   }

   public static DocumentFile fromTreeUri(Context context, Uri treeUri) {
      return VERSION.SDK_INT >= 21 ? new TreeDocumentFile((DocumentFile)null, context, DocumentsContractApi21.prepareTreeUri(treeUri)) : null;
   }

   public static boolean isDocumentUri(Context context, Uri uri) {
      return VERSION.SDK_INT >= 19 ? DocumentsContractApi19.isDocumentUri(context, uri) : false;
   }

   public abstract DocumentFile createFile(String var1, String var2);

   public abstract DocumentFile createDirectory(String var1);

   public abstract Uri getUri();

   public abstract String getName();

   public abstract String getType();

   public DocumentFile getParentFile() {
      return this.mParent;
   }

   public abstract boolean isDirectory();

   public abstract boolean isFile();

   public abstract boolean isVirtual();

   public abstract long lastModified();

   public abstract long length();

   public abstract boolean canRead();

   public abstract boolean canWrite();

   public abstract boolean delete();

   public abstract boolean exists();

   public abstract DocumentFile[] listFiles();

   public DocumentFile findFile(String displayName) {
      DocumentFile[] var2 = this.listFiles();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         DocumentFile doc = var2[var4];
         if (displayName.equals(doc.getName())) {
            return doc;
         }
      }

      return null;
   }

   public abstract boolean renameTo(String var1);
}
