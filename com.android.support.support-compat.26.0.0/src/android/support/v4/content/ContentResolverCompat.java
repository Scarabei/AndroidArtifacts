package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.OperationCanceledException;
import android.os.Build.VERSION;
import android.support.v4.os.CancellationSignal;

public final class ContentResolverCompat {
   public static Cursor query(ContentResolver resolver, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
      if (VERSION.SDK_INT >= 16) {
         try {
            android.os.CancellationSignal cancellationSignalObj = (android.os.CancellationSignal)((android.os.CancellationSignal)(cancellationSignal != null ? cancellationSignal.getCancellationSignalObject() : null));
            return resolver.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignalObj);
         } catch (Exception var8) {
            if (var8 instanceof OperationCanceledException) {
               throw new android.support.v4.os.OperationCanceledException();
            } else {
               throw var8;
            }
         }
      } else {
         if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
         }

         return resolver.query(uri, projection, selection, selectionArgs, sortOrder);
      }
   }
}
