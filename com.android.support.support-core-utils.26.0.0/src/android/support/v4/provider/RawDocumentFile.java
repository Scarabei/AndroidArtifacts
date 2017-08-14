package android.support.v4.provider;

import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class RawDocumentFile extends DocumentFile {
   private File mFile;

   RawDocumentFile(DocumentFile parent, File file) {
      super(parent);
      this.mFile = file;
   }

   public DocumentFile createFile(String mimeType, String displayName) {
      String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
      if (extension != null) {
         displayName = displayName + "." + extension;
      }

      File target = new File(this.mFile, displayName);

      try {
         target.createNewFile();
         return new RawDocumentFile(this, target);
      } catch (IOException var6) {
         Log.w("DocumentFile", "Failed to createFile: " + var6);
         return null;
      }
   }

   public DocumentFile createDirectory(String displayName) {
      File target = new File(this.mFile, displayName);
      return !target.isDirectory() && !target.mkdir() ? null : new RawDocumentFile(this, target);
   }

   public Uri getUri() {
      return Uri.fromFile(this.mFile);
   }

   public String getName() {
      return this.mFile.getName();
   }

   public String getType() {
      return this.mFile.isDirectory() ? null : getTypeForName(this.mFile.getName());
   }

   public boolean isDirectory() {
      return this.mFile.isDirectory();
   }

   public boolean isFile() {
      return this.mFile.isFile();
   }

   public boolean isVirtual() {
      return false;
   }

   public long lastModified() {
      return this.mFile.lastModified();
   }

   public long length() {
      return this.mFile.length();
   }

   public boolean canRead() {
      return this.mFile.canRead();
   }

   public boolean canWrite() {
      return this.mFile.canWrite();
   }

   public boolean delete() {
      deleteContents(this.mFile);
      return this.mFile.delete();
   }

   public boolean exists() {
      return this.mFile.exists();
   }

   public DocumentFile[] listFiles() {
      ArrayList results = new ArrayList();
      File[] files = this.mFile.listFiles();
      if (files != null) {
         File[] var3 = files;
         int var4 = files.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File file = var3[var5];
            results.add(new RawDocumentFile(this, file));
         }
      }

      return (DocumentFile[])results.toArray(new DocumentFile[results.size()]);
   }

   public boolean renameTo(String displayName) {
      File target = new File(this.mFile.getParentFile(), displayName);
      if (this.mFile.renameTo(target)) {
         this.mFile = target;
         return true;
      } else {
         return false;
      }
   }

   private static String getTypeForName(String name) {
      int lastDot = name.lastIndexOf(46);
      if (lastDot >= 0) {
         String extension = name.substring(lastDot + 1).toLowerCase();
         String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
         if (mime != null) {
            return mime;
         }
      }

      return "application/octet-stream";
   }

   private static boolean deleteContents(File dir) {
      File[] files = dir.listFiles();
      boolean success = true;
      if (files != null) {
         File[] var3 = files;
         int var4 = files.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File file = var3[var5];
            if (file.isDirectory()) {
               success &= deleteContents(file);
            }

            if (!file.delete()) {
               Log.w("DocumentFile", "Failed to delete " + file);
               success = false;
            }
         }
      }

      return success;
   }
}
