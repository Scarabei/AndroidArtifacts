package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
   private static final String TAG = "TypefaceCompatUtil";
   private static final String CACHE_FILE_PREFIX = ".font";

   public static File getTempFile(Context context) {
      String prefix = ".font" + Process.myPid() + "-" + Process.myTid() + "-";

      for(int i = 0; i < 100; ++i) {
         File file = new File(context.getCacheDir(), prefix + i);

         try {
            if (file.createNewFile()) {
               return file;
            }
         } catch (IOException var5) {
            ;
         }
      }

      return null;
   }

   @RequiresApi(19)
   private static ByteBuffer mmap(File file) {
      try {
         FileInputStream fis = new FileInputStream(file);
         Throwable var2 = null;

         MappedByteBuffer var6;
         try {
            FileChannel channel = fis.getChannel();
            long size = channel.size();
            var6 = channel.map(MapMode.READ_ONLY, 0L, size);
         } catch (Throwable var16) {
            var2 = var16;
            throw var16;
         } finally {
            if (fis != null) {
               if (var2 != null) {
                  try {
                     fis.close();
                  } catch (Throwable var15) {
                     var2.addSuppressed(var15);
                  }
               } else {
                  fis.close();
               }
            }

         }

         return var6;
      } catch (IOException var18) {
         return null;
      }
   }

   @RequiresApi(19)
   public static ByteBuffer mmap(Context context, CancellationSignal cancellationSignal, Uri uri) {
      ContentResolver resolver = context.getContentResolver();

      try {
         ParcelFileDescriptor pfd = resolver.openFileDescriptor(uri, "r", cancellationSignal);
         Throwable var5 = null;

         MappedByteBuffer var11;
         try {
            FileInputStream fis = new FileInputStream(pfd.getFileDescriptor());
            Throwable var7 = null;

            try {
               FileChannel channel = fis.getChannel();
               long size = channel.size();
               var11 = channel.map(MapMode.READ_ONLY, 0L, size);
            } catch (Throwable var36) {
               var7 = var36;
               throw var36;
            } finally {
               if (fis != null) {
                  if (var7 != null) {
                     try {
                        fis.close();
                     } catch (Throwable var35) {
                        var7.addSuppressed(var35);
                     }
                  } else {
                     fis.close();
                  }
               }

            }
         } catch (Throwable var38) {
            var5 = var38;
            throw var38;
         } finally {
            if (pfd != null) {
               if (var5 != null) {
                  try {
                     pfd.close();
                  } catch (Throwable var34) {
                     var5.addSuppressed(var34);
                  }
               } else {
                  pfd.close();
               }
            }

         }

         return var11;
      } catch (IOException var40) {
         return null;
      }
   }

   @RequiresApi(19)
   public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
      File tmpFile = getTempFile(context);
      if (tmpFile == null) {
         return null;
      } else {
         ByteBuffer var4;
         try {
            if (copyToFile(tmpFile, res, id)) {
               var4 = mmap(tmpFile);
               return var4;
            }

            var4 = null;
         } finally {
            tmpFile.delete();
         }

         return var4;
      }
   }

   public static boolean copyToFile(File file, InputStream is) {
      FileOutputStream os = null;

      boolean var4;
      try {
         os = new FileOutputStream(file, false);
         byte[] buffer = new byte[1024];

         int readLen;
         while((readLen = is.read(buffer)) != -1) {
            os.write(buffer, 0, readLen);
         }

         boolean var5 = true;
         return var5;
      } catch (IOException var9) {
         Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + var9.getMessage());
         var4 = false;
      } finally {
         closeQuietly(os);
      }

      return var4;
   }

   public static boolean copyToFile(File file, Resources res, int id) {
      InputStream is = null;

      boolean var4;
      try {
         is = res.openRawResource(id);
         var4 = copyToFile(file, is);
      } finally {
         closeQuietly(is);
      }

      return var4;
   }

   public static void closeQuietly(Closeable c) {
      if (c != null) {
         try {
            c.close();
         } catch (IOException var2) {
            ;
         }
      }

   }
}
