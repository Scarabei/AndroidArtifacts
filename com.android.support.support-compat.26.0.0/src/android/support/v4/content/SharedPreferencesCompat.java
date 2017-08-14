package android.support.v4.content;

import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

public final class SharedPreferencesCompat {
   public static final class EditorCompat {
      private static SharedPreferencesCompat.EditorCompat sInstance;
      private final SharedPreferencesCompat.EditorCompat.Helper mHelper = new SharedPreferencesCompat.EditorCompat.Helper();

      public static SharedPreferencesCompat.EditorCompat getInstance() {
         if (sInstance == null) {
            sInstance = new SharedPreferencesCompat.EditorCompat();
         }

         return sInstance;
      }

      public void apply(@NonNull Editor editor) {
         this.mHelper.apply(editor);
      }

      private static class Helper {
         public void apply(@NonNull Editor editor) {
            try {
               editor.apply();
            } catch (AbstractMethodError var3) {
               editor.commit();
            }

         }
      }
   }
}
