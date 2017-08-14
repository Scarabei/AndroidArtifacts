package android.support.v4.util;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
   private final File mBaseName;
   private final File mBackupName;

   public AtomicFile(File baseName) {
      this.mBaseName = baseName;
      this.mBackupName = new File(baseName.getPath() + ".bak");
   }

   public File getBaseFile() {
      return this.mBaseName;
   }

   public void delete() {
      this.mBaseName.delete();
      this.mBackupName.delete();
   }

   public FileOutputStream startWrite() throws IOException {
      if (this.mBaseName.exists()) {
         if (!this.mBackupName.exists()) {
            if (!this.mBaseName.renameTo(this.mBackupName)) {
               Log.w("AtomicFile", "Couldn't rename file " + this.mBaseName + " to backup file " + this.mBackupName);
            }
         } else {
            this.mBaseName.delete();
         }
      }

      FileOutputStream str = null;

      try {
         str = new FileOutputStream(this.mBaseName);
      } catch (FileNotFoundException var6) {
         File parent = this.mBaseName.getParentFile();
         if (!parent.mkdirs()) {
            throw new IOException("Couldn't create directory " + this.mBaseName);
         }

         try {
            str = new FileOutputStream(this.mBaseName);
         } catch (FileNotFoundException var5) {
            throw new IOException("Couldn't create " + this.mBaseName);
         }
      }

      return str;
   }

   public void finishWrite(FileOutputStream str) {
      if (str != null) {
         sync(str);

         try {
            str.close();
            this.mBackupName.delete();
         } catch (IOException var3) {
            Log.w("AtomicFile", "finishWrite: Got exception:", var3);
         }
      }

   }

   public void failWrite(FileOutputStream str) {
      if (str != null) {
         sync(str);

         try {
            str.close();
            this.mBaseName.delete();
            this.mBackupName.renameTo(this.mBaseName);
         } catch (IOException var3) {
            Log.w("AtomicFile", "failWrite: Got exception:", var3);
         }
      }

   }

   public FileInputStream openRead() throws FileNotFoundException {
      if (this.mBackupName.exists()) {
         this.mBaseName.delete();
         this.mBackupName.renameTo(this.mBaseName);
      }

      return new FileInputStream(this.mBaseName);
   }

   public byte[] readFully() throws IOException {
      FileInputStream stream = this.openRead();

      try {
         int pos = 0;
         int avail = stream.available();
         byte[] data = new byte[avail];

         while(true) {
            int amt = stream.read(data, pos, data.length - pos);
            byte[] newData;
            if (amt <= 0) {
               newData = data;
               return newData;
            }

            pos += amt;
            avail = stream.available();
            if (avail > data.length - pos) {
               newData = new byte[pos + avail];
               System.arraycopy(data, 0, newData, 0, pos);
               data = newData;
            }
         }
      } finally {
         stream.close();
      }
   }

   static boolean sync(FileOutputStream stream) {
      try {
         if (stream != null) {
            stream.getFD().sync();
         }

         return true;
      } catch (IOException var2) {
         return false;
      }
   }
}
