package android.support.v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

class PaintCompatApi14 {
   private static final String TOFU_STRING = "\udb3f\udffd";
   private static final String EM_STRING = "m";
   private static final ThreadLocal sRectThreadLocal = new ThreadLocal();

   static boolean hasGlyph(@NonNull Paint paint, @NonNull String string) {
      int length = string.length();
      if (length == 1 && Character.isWhitespace(string.charAt(0))) {
         return true;
      } else {
         float missingGlyphWidth = paint.measureText("\udb3f\udffd");
         float emGlyphWidth = paint.measureText("m");
         float width = paint.measureText(string);
         if (width == 0.0F) {
            return false;
         } else {
            if (string.codePointCount(0, string.length()) > 1) {
               if (width > 2.0F * emGlyphWidth) {
                  return false;
               }

               float sumWidth = 0.0F;

               int charCount;
               for(int i = 0; i < length; i += charCount) {
                  charCount = Character.charCount(string.codePointAt(i));
                  sumWidth += paint.measureText(string, i, i + charCount);
               }

               if (width >= sumWidth) {
                  return false;
               }
            }

            if (width != missingGlyphWidth) {
               return true;
            } else {
               Pair rects = obtainEmptyRects();
               paint.getTextBounds("\udb3f\udffd", 0, "\udb3f\udffd".length(), (Rect)rects.first);
               paint.getTextBounds(string, 0, length, (Rect)rects.second);
               return !((Rect)rects.first).equals(rects.second);
            }
         }
      }
   }

   private static Pair obtainEmptyRects() {
      Pair rects = (Pair)sRectThreadLocal.get();
      if (rects == null) {
         rects = new Pair(new Rect(), new Rect());
         sRectThreadLocal.set(rects);
      } else {
         ((Rect)rects.first).setEmpty();
         ((Rect)rects.second).setEmpty();
      }

      return rects;
   }
}
