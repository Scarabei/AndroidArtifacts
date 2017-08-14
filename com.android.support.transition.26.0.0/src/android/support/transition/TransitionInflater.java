package android.support.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.content.res.Resources.NotFoundException;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater {
   private static final Class[] CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class};
   private static final ArrayMap CONSTRUCTORS = new ArrayMap();
   private final Context mContext;

   private TransitionInflater(@NonNull Context context) {
      this.mContext = context;
   }

   public static TransitionInflater from(Context context) {
      return new TransitionInflater(context);
   }

   public Transition inflateTransition(int resource) {
      XmlResourceParser parser = this.mContext.getResources().getXml(resource);

      Transition var3;
      try {
         var3 = this.createTransitionFromXml(parser, Xml.asAttributeSet(parser), (Transition)null);
      } catch (XmlPullParserException var8) {
         throw new InflateException(var8.getMessage(), var8);
      } catch (IOException var9) {
         throw new InflateException(parser.getPositionDescription() + ": " + var9.getMessage(), var9);
      } finally {
         parser.close();
      }

      return var3;
   }

   public TransitionManager inflateTransitionManager(int resource, ViewGroup sceneRoot) {
      XmlResourceParser parser = this.mContext.getResources().getXml(resource);

      TransitionManager var4;
      try {
         InflateException ex;
         try {
            var4 = this.createTransitionManagerFromXml(parser, Xml.asAttributeSet(parser), sceneRoot);
         } catch (XmlPullParserException var10) {
            ex = new InflateException(var10.getMessage());
            ex.initCause(var10);
            throw ex;
         } catch (IOException var11) {
            ex = new InflateException(parser.getPositionDescription() + ": " + var11.getMessage());
            ex.initCause(var11);
            throw ex;
         }
      } finally {
         parser.close();
      }

      return var4;
   }

   private Transition createTransitionFromXml(XmlPullParser parser, AttributeSet attrs, Transition parent) throws XmlPullParserException, IOException {
      Transition transition = null;
      int depth = parser.getDepth();
      TransitionSet transitionSet = parent instanceof TransitionSet ? (TransitionSet)parent : null;

      int type;
      while(((type = parser.next()) != 3 || parser.getDepth() > depth) && type != 1) {
         if (type == 2) {
            String name = parser.getName();
            if ("fade".equals(name)) {
               transition = new Fade(this.mContext, attrs);
            } else if ("changeBounds".equals(name)) {
               transition = new ChangeBounds(this.mContext, attrs);
            } else if ("slide".equals(name)) {
               transition = new Slide(this.mContext, attrs);
            } else if ("explode".equals(name)) {
               transition = new Explode(this.mContext, attrs);
            } else if ("changeImageTransform".equals(name)) {
               transition = new ChangeImageTransform(this.mContext, attrs);
            } else if ("changeTransform".equals(name)) {
               transition = new ChangeTransform(this.mContext, attrs);
            } else if ("changeClipBounds".equals(name)) {
               transition = new ChangeClipBounds(this.mContext, attrs);
            } else if ("autoTransition".equals(name)) {
               transition = new AutoTransition(this.mContext, attrs);
            } else if ("changeScroll".equals(name)) {
               transition = new ChangeScroll(this.mContext, attrs);
            } else if ("transitionSet".equals(name)) {
               transition = new TransitionSet(this.mContext, attrs);
            } else if ("transition".equals(name)) {
               transition = (Transition)this.createCustom(attrs, Transition.class, "transition");
            } else if ("targets".equals(name)) {
               this.getTargetIds(parser, attrs, parent);
            } else if ("arcMotion".equals(name)) {
               if (parent == null) {
                  throw new RuntimeException("Invalid use of arcMotion element");
               }

               parent.setPathMotion(new ArcMotion(this.mContext, attrs));
            } else if ("pathMotion".equals(name)) {
               if (parent == null) {
                  throw new RuntimeException("Invalid use of pathMotion element");
               }

               parent.setPathMotion((PathMotion)this.createCustom(attrs, PathMotion.class, "pathMotion"));
            } else {
               if (!"patternPathMotion".equals(name)) {
                  throw new RuntimeException("Unknown scene name: " + parser.getName());
               }

               if (parent == null) {
                  throw new RuntimeException("Invalid use of patternPathMotion element");
               }

               parent.setPathMotion(new PatternPathMotion(this.mContext, attrs));
            }

            if (transition != null) {
               if (!parser.isEmptyElementTag()) {
                  this.createTransitionFromXml(parser, attrs, (Transition)transition);
               }

               if (transitionSet != null) {
                  transitionSet.addTransition((Transition)transition);
                  transition = null;
               } else if (parent != null) {
                  throw new InflateException("Could not add transition to another transition.");
               }
            }
         }
      }

      return (Transition)transition;
   }

   private Object createCustom(AttributeSet attrs, Class expectedType, String tag) {
      String className = attrs.getAttributeValue((String)null, "class");
      if (className == null) {
         throw new InflateException(tag + " tag must have a 'class' attribute");
      } else {
         try {
            ArrayMap var5 = CONSTRUCTORS;
            synchronized(CONSTRUCTORS) {
               Constructor constructor = (Constructor)CONSTRUCTORS.get(className);
               if (constructor == null) {
                  Class c = this.mContext.getClassLoader().loadClass(className).asSubclass(expectedType);
                  if (c != null) {
                     constructor = c.getConstructor(CONSTRUCTOR_SIGNATURE);
                     constructor.setAccessible(true);
                     CONSTRUCTORS.put(className, constructor);
                  }
               }

               return constructor.newInstance(this.mContext, attrs);
            }
         } catch (Exception var10) {
            throw new InflateException("Could not instantiate " + expectedType + " class " + className, var10);
         }
      }
   }

   private void getTargetIds(XmlPullParser parser, AttributeSet attrs, Transition transition) throws XmlPullParserException, IOException {
      int depth = parser.getDepth();

      int type;
      while(((type = parser.next()) != 3 || parser.getDepth() > depth) && type != 1) {
         if (type == 2) {
            String name = parser.getName();
            if (!name.equals("target")) {
               throw new RuntimeException("Unknown scene name: " + parser.getName());
            }

            TypedArray a = this.mContext.obtainStyledAttributes(attrs, Styleable.TRANSITION_TARGET);
            int id = TypedArrayUtils.getNamedResourceId(a, parser, "targetId", 1, 0);
            if (id != 0) {
               transition.addTarget(id);
            } else if ((id = TypedArrayUtils.getNamedResourceId(a, parser, "excludeId", 2, 0)) != 0) {
               transition.excludeTarget(id, true);
            } else {
               String transitionName;
               if ((transitionName = TypedArrayUtils.getNamedString(a, parser, "targetName", 4)) != null) {
                  transition.addTarget(transitionName);
               } else if ((transitionName = TypedArrayUtils.getNamedString(a, parser, "excludeName", 5)) != null) {
                  transition.excludeTarget(transitionName, true);
               } else {
                  String className = TypedArrayUtils.getNamedString(a, parser, "excludeClass", 3);

                  try {
                     Class clazz;
                     if (className != null) {
                        clazz = Class.forName(className);
                        transition.excludeTarget(clazz, true);
                     } else if ((className = TypedArrayUtils.getNamedString(a, parser, "targetClass", 0)) != null) {
                        clazz = Class.forName(className);
                        transition.addTarget(clazz);
                     }
                  } catch (ClassNotFoundException var12) {
                     a.recycle();
                     throw new RuntimeException("Could not create " + className, var12);
                  }
               }
            }

            a.recycle();
         }
      }

   }

   private TransitionManager createTransitionManagerFromXml(XmlPullParser parser, AttributeSet attrs, ViewGroup sceneRoot) throws XmlPullParserException, IOException {
      int depth = parser.getDepth();
      TransitionManager transitionManager = null;

      int type;
      while(((type = parser.next()) != 3 || parser.getDepth() > depth) && type != 1) {
         if (type == 2) {
            String name = parser.getName();
            if (name.equals("transitionManager")) {
               transitionManager = new TransitionManager();
            } else {
               if (!name.equals("transition") || transitionManager == null) {
                  throw new RuntimeException("Unknown scene name: " + parser.getName());
               }

               this.loadTransition(attrs, parser, sceneRoot, transitionManager);
            }
         }
      }

      return transitionManager;
   }

   private void loadTransition(AttributeSet attrs, XmlPullParser parser, ViewGroup sceneRoot, TransitionManager transitionManager) throws NotFoundException {
      TypedArray a = this.mContext.obtainStyledAttributes(attrs, Styleable.TRANSITION_MANAGER);
      int transitionId = TypedArrayUtils.getNamedResourceId(a, parser, "transition", 2, -1);
      int fromId = TypedArrayUtils.getNamedResourceId(a, parser, "fromScene", 0, -1);
      Scene fromScene = fromId < 0 ? null : Scene.getSceneForLayout(sceneRoot, fromId, this.mContext);
      int toId = TypedArrayUtils.getNamedResourceId(a, parser, "toScene", 1, -1);
      Scene toScene = toId < 0 ? null : Scene.getSceneForLayout(sceneRoot, toId, this.mContext);
      if (transitionId >= 0) {
         Transition transition = this.inflateTransition(transitionId);
         if (transition != null) {
            if (toScene == null) {
               throw new RuntimeException("No toScene for transition ID " + transitionId);
            }

            if (fromScene == null) {
               transitionManager.setTransition(toScene, transition);
            } else {
               transitionManager.setTransition(fromScene, toScene, transition);
            }
         }
      }

      a.recycle();
   }
}
