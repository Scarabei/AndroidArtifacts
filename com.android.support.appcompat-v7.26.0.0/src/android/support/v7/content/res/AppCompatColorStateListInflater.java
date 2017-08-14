package android.support.v7.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class AppCompatColorStateListInflater {
   private static final int DEFAULT_COLOR = -65536;

   @NonNull
   public static ColorStateList createFromXml(@NonNull Resources r, @NonNull XmlPullParser parser, @Nullable Theme theme) throws XmlPullParserException, IOException {
      AttributeSet attrs = Xml.asAttributeSet(parser);

      int type;
      while((type = parser.next()) != 2 && type != 1) {
         ;
      }

      if (type != 2) {
         throw new XmlPullParserException("No start tag found");
      } else {
         return createFromXmlInner(r, parser, attrs, theme);
      }
   }

   @NonNull
   private static ColorStateList createFromXmlInner(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) throws XmlPullParserException, IOException {
      String name = parser.getName();
      if (!name.equals("selector")) {
         throw new XmlPullParserException(parser.getPositionDescription() + ": invalid color state list tag " + name);
      } else {
         return inflate(r, parser, attrs, theme);
      }
   }

   private static ColorStateList inflate(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) throws XmlPullParserException, IOException {
      int innerDepth = parser.getDepth() + 1;
      int defaultColor = -65536;
      int[][] stateSpecList = new int[20][];
      int[] colorList = new int[stateSpecList.length];
      int listSize = 0;

      int depth;
      int type;
      while((type = parser.next()) != 1 && ((depth = parser.getDepth()) >= innerDepth || type != 3)) {
         if (type == 2 && depth <= innerDepth && parser.getName().equals("item")) {
            TypedArray a = obtainAttributes(r, theme, attrs, styleable.ColorStateListItem);
            int baseColor = a.getColor(styleable.ColorStateListItem_android_color, -65281);
            float alphaMod = 1.0F;
            if (a.hasValue(styleable.ColorStateListItem_android_alpha)) {
               alphaMod = a.getFloat(styleable.ColorStateListItem_android_alpha, alphaMod);
            } else if (a.hasValue(styleable.ColorStateListItem_alpha)) {
               alphaMod = a.getFloat(styleable.ColorStateListItem_alpha, alphaMod);
            }

            a.recycle();
            int j = 0;
            int numAttrs = attrs.getAttributeCount();
            int[] stateSpec = new int[numAttrs];

            int color;
            for(color = 0; color < numAttrs; ++color) {
               int stateResId = attrs.getAttributeNameResource(color);
               if (stateResId != 16843173 && stateResId != 16843551 && stateResId != attr.alpha) {
                  stateSpec[j++] = attrs.getAttributeBooleanValue(color, false) ? stateResId : -stateResId;
               }
            }

            stateSpec = StateSet.trimStateSet(stateSpec, j);
            color = modulateColorAlpha(baseColor, alphaMod);
            if (listSize == 0 || stateSpec.length == 0) {
               ;
            }

            colorList = GrowingArrayUtils.append(colorList, listSize, color);
            stateSpecList = (int[][])GrowingArrayUtils.append(stateSpecList, listSize, stateSpec);
            ++listSize;
         }
      }

      int[] colors = new int[listSize];
      int[][] stateSpecs = new int[listSize][];
      System.arraycopy(colorList, 0, colors, 0, listSize);
      System.arraycopy(stateSpecList, 0, stateSpecs, 0, listSize);
      return new ColorStateList(stateSpecs, colors);
   }

   private static TypedArray obtainAttributes(Resources res, Theme theme, AttributeSet set, int[] attrs) {
      return theme == null ? res.obtainAttributes(set, attrs) : theme.obtainStyledAttributes(set, attrs, 0, 0);
   }

   private static int modulateColorAlpha(int color, float alphaMod) {
      return ColorUtils.setAlphaComponent(color, Math.round((float)Color.alpha(color) * alphaMod));
   }
}
