package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class AnimationUtilsCompat {
   public static Interpolator loadInterpolator(Context context, int id) throws NotFoundException {
      if (VERSION.SDK_INT >= 21) {
         return AnimationUtils.loadInterpolator(context, id);
      } else {
         XmlResourceParser parser = null;

         Interpolator var3;
         try {
            NotFoundException rnf;
            try {
               if (id == 17563663) {
                  FastOutLinearInInterpolator var14 = new FastOutLinearInInterpolator();
                  return var14;
               }

               if (id == 17563661) {
                  FastOutSlowInInterpolator var13 = new FastOutSlowInInterpolator();
                  return var13;
               }

               if (id == 17563662) {
                  LinearOutSlowInInterpolator var12 = new LinearOutSlowInInterpolator();
                  return var12;
               }

               parser = context.getResources().getAnimation(id);
               var3 = createInterpolatorFromXml(context, context.getResources(), context.getTheme(), parser);
            } catch (XmlPullParserException var9) {
               rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
               rnf.initCause(var9);
               throw rnf;
            } catch (IOException var10) {
               rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
               rnf.initCause(var10);
               throw rnf;
            }
         } finally {
            if (parser != null) {
               parser.close();
            }

         }

         return var3;
      }
   }

   private static Interpolator createInterpolatorFromXml(Context context, Resources res, Theme theme, XmlPullParser parser) throws XmlPullParserException, IOException {
      Interpolator interpolator = null;
      int depth = parser.getDepth();

      int type;
      while(((type = parser.next()) != 3 || parser.getDepth() > depth) && type != 1) {
         if (type == 2) {
            AttributeSet attrs = Xml.asAttributeSet(parser);
            String name = parser.getName();
            if (name.equals("linearInterpolator")) {
               interpolator = new LinearInterpolator();
            } else if (name.equals("accelerateInterpolator")) {
               interpolator = new AccelerateInterpolator(context, attrs);
            } else if (name.equals("decelerateInterpolator")) {
               interpolator = new DecelerateInterpolator(context, attrs);
            } else if (name.equals("accelerateDecelerateInterpolator")) {
               interpolator = new AccelerateDecelerateInterpolator();
            } else if (name.equals("cycleInterpolator")) {
               interpolator = new CycleInterpolator(context, attrs);
            } else if (name.equals("anticipateInterpolator")) {
               interpolator = new AnticipateInterpolator(context, attrs);
            } else if (name.equals("overshootInterpolator")) {
               interpolator = new OvershootInterpolator(context, attrs);
            } else if (name.equals("anticipateOvershootInterpolator")) {
               interpolator = new AnticipateOvershootInterpolator(context, attrs);
            } else if (name.equals("bounceInterpolator")) {
               interpolator = new BounceInterpolator();
            } else {
               if (!name.equals("pathInterpolator")) {
                  throw new RuntimeException("Unknown interpolator name: " + parser.getName());
               }

               interpolator = new PathInterpolatorCompat(context, attrs, parser);
            }
         }
      }

      return (Interpolator)interpolator;
   }
}
