package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.provider.FontsContractCompat;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(21)
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
   private static final String TAG = "TypefaceCompatApi21Impl";

   private File getFile(ParcelFileDescriptor fd) {
      try {
         String path = Os.readlink("/proc/self/fd/" + fd.getFd());
         return OsConstants.S_ISREG(Os.stat(path).st_mode) ? new File(path) : null;
      } catch (ErrnoException var3) {
         return null;
      }
   }

   public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int style) {
      if (fonts.length < 1) {
         return null;
      } else {
         FontsContractCompat.FontInfo bestFont = this.findBestInfo(fonts, style);
         ContentResolver resolver = context.getContentResolver();

         try {
            ParcelFileDescriptor pfd = resolver.openFileDescriptor(bestFont.getUri(), "r", cancellationSignal);
            Throwable var8 = null;

            Object var12;
            try {
               File file = this.getFile(pfd);
               if (file != null && file.canRead()) {
                  Typeface var43 = Typeface.createFromFile(file);
                  return var43;
               }

               FileInputStream fis = new FileInputStream(pfd.getFileDescriptor());
               Throwable var11 = null;

               try {
                  var12 = super.createFromInputStream(context, fis);
               } catch (Throwable var38) {
                  var12 = var38;
                  var11 = var38;
                  throw var38;
               } finally {
                  if (fis != null) {
                     if (var11 != null) {
                        try {
                           fis.close();
                        } catch (Throwable var37) {
                           var11.addSuppressed(var37);
                        }
                     } else {
                        fis.close();
                     }
                  }

               }
            } catch (Throwable var40) {
               var8 = var40;
               throw var40;
            } finally {
               if (pfd != null) {
                  if (var8 != null) {
                     try {
                        pfd.close();
                     } catch (Throwable var36) {
                        var8.addSuppressed(var36);
                     }
                  } else {
                     pfd.close();
                  }
               }

            }

            return (Typeface)var12;
         } catch (IOException var42) {
            return null;
         }
      }
   }
}
