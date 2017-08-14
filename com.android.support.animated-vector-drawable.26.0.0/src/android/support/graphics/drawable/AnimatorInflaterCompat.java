package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import android.view.animation.Interpolator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class AnimatorInflaterCompat {
   private static final String TAG = "AnimatorInflater";
   private static final int TOGETHER = 0;
   private static final int MAX_NUM_POINTS = 100;
   private static final int VALUE_TYPE_FLOAT = 0;
   private static final int VALUE_TYPE_INT = 1;
   private static final int VALUE_TYPE_PATH = 2;
   private static final int VALUE_TYPE_COLOR = 3;
   private static final int VALUE_TYPE_UNDEFINED = 4;
   private static final boolean DBG_ANIMATOR_INFLATER = false;

   public static Animator loadAnimator(Context context, @AnimatorRes int id) throws NotFoundException {
      Animator objectAnimator;
      if (VERSION.SDK_INT >= 24) {
         objectAnimator = AnimatorInflater.loadAnimator(context, id);
      } else {
         objectAnimator = loadAnimator(context, context.getResources(), context.getTheme(), id);
      }

      return objectAnimator;
   }

   public static Animator loadAnimator(Context context, Resources resources, Theme theme, @AnimatorRes int id) throws NotFoundException {
      return loadAnimator(context, resources, theme, id, 1.0F);
   }

   public static Animator loadAnimator(Context context, Resources resources, Theme theme, @AnimatorRes int id, float pathErrorScale) throws NotFoundException {
      XmlResourceParser parser = null;

      Animator var7;
      try {
         NotFoundException rnf;
         try {
            parser = resources.getAnimation(id);
            Animator animator = createAnimatorFromXml(context, resources, theme, parser, pathErrorScale);
            var7 = animator;
         } catch (XmlPullParserException var13) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(var13);
            throw rnf;
         } catch (IOException var14) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(var14);
            throw rnf;
         }
      } finally {
         if (parser != null) {
            parser.close();
         }

      }

      return var7;
   }

   private static PropertyValuesHolder getPVH(TypedArray styledAttributes, int valueType, int valueFromId, int valueToId, String propertyName) {
      TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
      boolean hasFrom = tvFrom != null;
      int fromType = hasFrom ? tvFrom.type : 0;
      TypedValue tvTo = styledAttributes.peekValue(valueToId);
      boolean hasTo = tvTo != null;
      int toType = hasTo ? tvTo.type : 0;
      if (valueType == 4) {
         if ((!hasFrom || !isColorType(fromType)) && (!hasTo || !isColorType(toType))) {
            valueType = 0;
         } else {
            valueType = 3;
         }
      }

      boolean getFloats = valueType == 0;
      PropertyValuesHolder returnValue = null;
      if (valueType == 2) {
         String fromString = styledAttributes.getString(valueFromId);
         String toString = styledAttributes.getString(valueToId);
         PathDataNode[] nodesFrom = PathParser.createNodesFromPathData(fromString);
         PathDataNode[] nodesTo = PathParser.createNodesFromPathData(toString);
         if (nodesFrom != null || nodesTo != null) {
            AnimatorInflaterCompat.PathDataEvaluator evaluator;
            if (nodesFrom != null) {
               evaluator = new AnimatorInflaterCompat.PathDataEvaluator();
               if (nodesTo != null) {
                  if (!PathParser.canMorph(nodesFrom, nodesTo)) {
                     throw new InflateException(" Can't morph from " + fromString + " to " + toString);
                  }

                  returnValue = PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesFrom, nodesTo});
               } else {
                  returnValue = PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesFrom});
               }
            } else if (nodesTo != null) {
               evaluator = new AnimatorInflaterCompat.PathDataEvaluator();
               returnValue = PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesTo});
            }
         }
      } else {
         TypeEvaluator evaluator = null;
         if (valueType == 3) {
            evaluator = ArgbEvaluator.getInstance();
         }

         if (getFloats) {
            float valueTo;
            if (hasFrom) {
               float valueFrom;
               if (fromType == 5) {
                  valueFrom = styledAttributes.getDimension(valueFromId, 0.0F);
               } else {
                  valueFrom = styledAttributes.getFloat(valueFromId, 0.0F);
               }

               if (hasTo) {
                  if (toType == 5) {
                     valueTo = styledAttributes.getDimension(valueToId, 0.0F);
                  } else {
                     valueTo = styledAttributes.getFloat(valueToId, 0.0F);
                  }

                  returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueFrom, valueTo});
               } else {
                  returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueFrom});
               }
            } else {
               if (toType == 5) {
                  valueTo = styledAttributes.getDimension(valueToId, 0.0F);
               } else {
                  valueTo = styledAttributes.getFloat(valueToId, 0.0F);
               }

               returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueTo});
            }
         } else {
            int valueTo;
            if (hasFrom) {
               int valueFrom;
               if (fromType == 5) {
                  valueFrom = (int)styledAttributes.getDimension(valueFromId, 0.0F);
               } else if (isColorType(fromType)) {
                  valueFrom = styledAttributes.getColor(valueFromId, 0);
               } else {
                  valueFrom = styledAttributes.getInt(valueFromId, 0);
               }

               if (hasTo) {
                  if (toType == 5) {
                     valueTo = (int)styledAttributes.getDimension(valueToId, 0.0F);
                  } else if (isColorType(toType)) {
                     valueTo = styledAttributes.getColor(valueToId, 0);
                  } else {
                     valueTo = styledAttributes.getInt(valueToId, 0);
                  }

                  returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueFrom, valueTo});
               } else {
                  returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueFrom});
               }
            } else if (hasTo) {
               if (toType == 5) {
                  valueTo = (int)styledAttributes.getDimension(valueToId, 0.0F);
               } else if (isColorType(toType)) {
                  valueTo = styledAttributes.getColor(valueToId, 0);
               } else {
                  valueTo = styledAttributes.getInt(valueToId, 0);
               }

               returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueTo});
            }
         }

         if (returnValue != null && evaluator != null) {
            returnValue.setEvaluator(evaluator);
         }
      }

      return returnValue;
   }

   private static void parseAnimatorFromTypeArray(ValueAnimator anim, TypedArray arrayAnimator, TypedArray arrayObjectAnimator, float pixelSize, XmlPullParser parser) {
      long duration = (long)TypedArrayUtils.getNamedInt(arrayAnimator, parser, "duration", 1, 300);
      long startDelay = (long)TypedArrayUtils.getNamedInt(arrayAnimator, parser, "startOffset", 2, 0);
      int valueType = TypedArrayUtils.getNamedInt(arrayAnimator, parser, "valueType", 7, 4);
      if (TypedArrayUtils.hasAttribute(parser, "valueFrom") && TypedArrayUtils.hasAttribute(parser, "valueTo")) {
         if (valueType == 4) {
            valueType = inferValueTypeFromValues(arrayAnimator, 5, 6);
         }

         PropertyValuesHolder pvh = getPVH(arrayAnimator, valueType, 5, 6, "");
         if (pvh != null) {
            anim.setValues(new PropertyValuesHolder[]{pvh});
         }
      }

      anim.setDuration(duration);
      anim.setStartDelay(startDelay);
      anim.setRepeatCount(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatCount", 3, 0));
      anim.setRepeatMode(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatMode", 4, 1));
      if (arrayObjectAnimator != null) {
         setupObjectAnimator(anim, arrayObjectAnimator, valueType, pixelSize, parser);
      }

   }

   private static void setupObjectAnimator(ValueAnimator anim, TypedArray arrayObjectAnimator, int valueType, float pixelSize, XmlPullParser parser) {
      ObjectAnimator oa = (ObjectAnimator)anim;
      String pathData = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "pathData", 1);
      String propertyXName;
      if (pathData != null) {
         propertyXName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyXName", 2);
         String propertyYName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyYName", 3);
         if (valueType == 2 || valueType == 4) {
            boolean valueType1 = false;
         }

         if (propertyXName == null && propertyYName == null) {
            throw new InflateException(arrayObjectAnimator.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
         }

         Path path = PathParser.createPathFromPathData(pathData);
         setupPathMotion(path, oa, 0.5F * pixelSize, propertyXName, propertyYName);
      } else {
         propertyXName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyName", 0);
         oa.setPropertyName(propertyXName);
      }

   }

   private static void setupPathMotion(Path path, ObjectAnimator oa, float precision, String propertyXName, String propertyYName) {
      PathMeasure measureForTotalLength = new PathMeasure(path, false);
      float totalLength = 0.0F;
      ArrayList contourLengths = new ArrayList();
      contourLengths.add(0.0F);

      do {
         float pathLength = measureForTotalLength.getLength();
         totalLength += pathLength;
         contourLengths.add(totalLength);
      } while(measureForTotalLength.nextContour());

      PathMeasure pathMeasure = new PathMeasure(path, false);
      int numPoints = Math.min(100, (int)(totalLength / precision) + 1);
      float[] mX = new float[numPoints];
      float[] mY = new float[numPoints];
      float[] position = new float[2];
      int contourIndex = 0;
      float step = totalLength / (float)(numPoints - 1);
      float currentDistance = 0.0F;

      for(int i = 0; i < numPoints; ++i) {
         pathMeasure.getPosTan(currentDistance, position, (float[])null);
         pathMeasure.getPosTan(currentDistance, position, (float[])null);
         mX[i] = position[0];
         mY[i] = position[1];
         currentDistance += step;
         if (contourIndex + 1 < contourLengths.size() && currentDistance > ((Float)contourLengths.get(contourIndex + 1)).floatValue()) {
            currentDistance -= ((Float)contourLengths.get(contourIndex + 1)).floatValue();
            ++contourIndex;
            pathMeasure.nextContour();
         }
      }

      PropertyValuesHolder x = null;
      PropertyValuesHolder y = null;
      if (propertyXName != null) {
         x = PropertyValuesHolder.ofFloat(propertyXName, mX);
      }

      if (propertyYName != null) {
         y = PropertyValuesHolder.ofFloat(propertyYName, mY);
      }

      if (x == null) {
         oa.setValues(new PropertyValuesHolder[]{y});
      } else if (y == null) {
         oa.setValues(new PropertyValuesHolder[]{x});
      } else {
         oa.setValues(new PropertyValuesHolder[]{x, y});
      }

   }

   private static Animator createAnimatorFromXml(Context context, Resources res, Theme theme, XmlPullParser parser, float pixelSize) throws XmlPullParserException, IOException {
      return createAnimatorFromXml(context, res, theme, parser, Xml.asAttributeSet(parser), (AnimatorSet)null, 0, pixelSize);
   }

   private static Animator createAnimatorFromXml(Context context, Resources res, Theme theme, XmlPullParser parser, AttributeSet attrs, AnimatorSet parent, int sequenceOrdering, float pixelSize) throws XmlPullParserException, IOException {
      Animator anim = null;
      ArrayList childAnims = null;
      int depth = parser.getDepth();

      int type;
      while(((type = parser.next()) != 3 || parser.getDepth() > depth) && type != 1) {
         if (type == 2) {
            String name = parser.getName();
            boolean gotValues = false;
            if (name.equals("objectAnimator")) {
               anim = loadObjectAnimator(context, res, theme, attrs, pixelSize, parser);
            } else if (name.equals("animator")) {
               anim = loadAnimator(context, res, theme, attrs, (ValueAnimator)null, pixelSize, parser);
            } else if (name.equals("set")) {
               anim = new AnimatorSet();
               TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATOR_SET);
               int ordering = TypedArrayUtils.getNamedInt(a, parser, "ordering", 0, 0);
               createAnimatorFromXml(context, res, theme, parser, attrs, (AnimatorSet)anim, ordering, pixelSize);
               a.recycle();
            } else {
               if (!name.equals("propertyValuesHolder")) {
                  throw new RuntimeException("Unknown animator name: " + parser.getName());
               }

               PropertyValuesHolder[] values = loadValues(context, res, theme, parser, Xml.asAttributeSet(parser));
               if (values != null && anim != null && anim instanceof ValueAnimator) {
                  ((ValueAnimator)anim).setValues(values);
               }

               gotValues = true;
            }

            if (parent != null && !gotValues) {
               if (childAnims == null) {
                  childAnims = new ArrayList();
               }

               childAnims.add(anim);
            }
         }
      }

      if (parent != null && childAnims != null) {
         Animator[] animsArray = new Animator[childAnims.size()];
         int index = 0;

         Animator a;
         for(Iterator var19 = childAnims.iterator(); var19.hasNext(); animsArray[index++] = a) {
            a = (Animator)var19.next();
         }

         if (sequenceOrdering == 0) {
            parent.playTogether(animsArray);
         } else {
            parent.playSequentially(animsArray);
         }
      }

      return (Animator)anim;
   }

   private static PropertyValuesHolder[] loadValues(Context context, Resources res, Theme theme, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
      ArrayList values = null;

      int type;
      while((type = parser.getEventType()) != 3 && type != 1) {
         if (type != 2) {
            parser.next();
         } else {
            String name = parser.getName();
            if (name.equals("propertyValuesHolder")) {
               TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
               String propertyName = TypedArrayUtils.getNamedString(a, parser, "propertyName", 3);
               int valueType = TypedArrayUtils.getNamedInt(a, parser, "valueType", 2, 4);
               PropertyValuesHolder pvh = loadPvh(context, res, theme, parser, propertyName, valueType);
               if (pvh == null) {
                  pvh = getPVH(a, valueType, 0, 1, propertyName);
               }

               if (pvh != null) {
                  if (values == null) {
                     values = new ArrayList();
                  }

                  values.add(pvh);
               }

               a.recycle();
            }

            parser.next();
         }
      }

      PropertyValuesHolder[] valuesArray = null;
      if (values != null) {
         int count = values.size();
         valuesArray = new PropertyValuesHolder[count];

         for(int i = 0; i < count; ++i) {
            valuesArray[i] = (PropertyValuesHolder)values.get(i);
         }
      }

      return valuesArray;
   }

   private static int inferValueTypeOfKeyframe(Resources res, Theme theme, AttributeSet attrs, XmlPullParser parser) {
      TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
      TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, "value", 0);
      boolean hasValue = keyframeValue != null;
      byte valueType;
      if (hasValue && isColorType(keyframeValue.type)) {
         valueType = 3;
      } else {
         valueType = 0;
      }

      a.recycle();
      return valueType;
   }

   private static int inferValueTypeFromValues(TypedArray styledAttributes, int valueFromId, int valueToId) {
      TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
      boolean hasFrom = tvFrom != null;
      int fromType = hasFrom ? tvFrom.type : 0;
      TypedValue tvTo = styledAttributes.peekValue(valueToId);
      boolean hasTo = tvTo != null;
      int toType = hasTo ? tvTo.type : 0;
      byte valueType;
      if ((!hasFrom || !isColorType(fromType)) && (!hasTo || !isColorType(toType))) {
         valueType = 0;
      } else {
         valueType = 3;
      }

      return valueType;
   }

   private static void dumpKeyframes(Object[] keyframes, String header) {
      if (keyframes != null && keyframes.length != 0) {
         Log.d("AnimatorInflater", header);
         int count = keyframes.length;

         for(int i = 0; i < count; ++i) {
            Keyframe keyframe = (Keyframe)keyframes[i];
            Log.d("AnimatorInflater", "Keyframe " + i + ": fraction " + (keyframe.getFraction() < 0.0F ? "null" : keyframe.getFraction()) + ", " + ", value : " + (keyframe.hasValue() ? keyframe.getValue() : "null"));
         }

      }
   }

   private static PropertyValuesHolder loadPvh(Context context, Resources res, Theme theme, XmlPullParser parser, String propertyName, int valueType) throws XmlPullParserException, IOException {
      PropertyValuesHolder value = null;
      ArrayList keyframes = null;

      int type;
      Keyframe firstKeyframe;
      while((type = parser.next()) != 3 && type != 1) {
         String name = parser.getName();
         if (name.equals("keyframe")) {
            if (valueType == 4) {
               valueType = inferValueTypeOfKeyframe(res, theme, Xml.asAttributeSet(parser), parser);
            }

            firstKeyframe = loadKeyframe(context, res, theme, Xml.asAttributeSet(parser), valueType, parser);
            if (firstKeyframe != null) {
               if (keyframes == null) {
                  keyframes = new ArrayList();
               }

               keyframes.add(firstKeyframe);
            }

            parser.next();
         }
      }

      int count;
      if (keyframes != null && (count = keyframes.size()) > 0) {
         firstKeyframe = (Keyframe)keyframes.get(0);
         Keyframe lastKeyframe = (Keyframe)keyframes.get(count - 1);
         float endFraction = lastKeyframe.getFraction();
         if (endFraction < 1.0F) {
            if (endFraction < 0.0F) {
               lastKeyframe.setFraction(1.0F);
            } else {
               keyframes.add(keyframes.size(), createNewKeyframe(lastKeyframe, 1.0F));
               ++count;
            }
         }

         float startFraction = firstKeyframe.getFraction();
         if (startFraction != 0.0F) {
            if (startFraction < 0.0F) {
               firstKeyframe.setFraction(0.0F);
            } else {
               keyframes.add(0, createNewKeyframe(firstKeyframe, 0.0F));
               ++count;
            }
         }

         Keyframe[] keyframeArray = new Keyframe[count];
         keyframes.toArray(keyframeArray);

         for(int i = 0; i < count; ++i) {
            Keyframe keyframe = keyframeArray[i];
            if (keyframe.getFraction() < 0.0F) {
               if (i == 0) {
                  keyframe.setFraction(0.0F);
               } else if (i == count - 1) {
                  keyframe.setFraction(1.0F);
               } else {
                  int endIndex = i;

                  for(int j = i + 1; j < count - 1 && keyframeArray[j].getFraction() < 0.0F; endIndex = j++) {
                     ;
                  }

                  float gap = keyframeArray[endIndex + 1].getFraction() - keyframeArray[i - 1].getFraction();
                  distributeKeyframes(keyframeArray, gap, i, endIndex);
               }
            }
         }

         value = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArray);
         if (valueType == 3) {
            value.setEvaluator(ArgbEvaluator.getInstance());
         }
      }

      return value;
   }

   private static Keyframe createNewKeyframe(Keyframe sampleKeyframe, float fraction) {
      return sampleKeyframe.getType() == Float.TYPE ? Keyframe.ofFloat(fraction) : (sampleKeyframe.getType() == Integer.TYPE ? Keyframe.ofInt(fraction) : Keyframe.ofObject(fraction));
   }

   private static void distributeKeyframes(Keyframe[] keyframes, float gap, int startIndex, int endIndex) {
      int count = endIndex - startIndex + 2;
      float increment = gap / (float)count;

      for(int i = startIndex; i <= endIndex; ++i) {
         keyframes[i].setFraction(keyframes[i - 1].getFraction() + increment);
      }

   }

   private static Keyframe loadKeyframe(Context context, Resources res, Theme theme, AttributeSet attrs, int valueType, XmlPullParser parser) throws XmlPullParserException, IOException {
      TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
      Keyframe keyframe = null;
      float fraction = TypedArrayUtils.getNamedFloat(a, parser, "fraction", 3, -1.0F);
      TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, "value", 0);
      boolean hasValue = keyframeValue != null;
      if (valueType == 4) {
         if (hasValue && isColorType(keyframeValue.type)) {
            valueType = 3;
         } else {
            valueType = 0;
         }
      }

      if (hasValue) {
         switch(valueType) {
         case 0:
            float value = TypedArrayUtils.getNamedFloat(a, parser, "value", 0, 0.0F);
            keyframe = Keyframe.ofFloat(fraction, value);
            break;
         case 1:
         case 3:
            int intValue = TypedArrayUtils.getNamedInt(a, parser, "value", 0, 0);
            keyframe = Keyframe.ofInt(fraction, intValue);
         case 2:
         }
      } else {
         keyframe = valueType == 0 ? Keyframe.ofFloat(fraction) : Keyframe.ofInt(fraction);
      }

      int resID = TypedArrayUtils.getNamedResourceId(a, parser, "interpolator", 1, 0);
      if (resID > 0) {
         Interpolator interpolator = AnimationUtilsCompat.loadInterpolator(context, resID);
         keyframe.setInterpolator(interpolator);
      }

      a.recycle();
      return keyframe;
   }

   private static ObjectAnimator loadObjectAnimator(Context context, Resources res, Theme theme, AttributeSet attrs, float pathErrorScale, XmlPullParser parser) throws NotFoundException {
      ObjectAnimator anim = new ObjectAnimator();
      loadAnimator(context, res, theme, attrs, anim, pathErrorScale, parser);
      return anim;
   }

   private static ValueAnimator loadAnimator(Context context, Resources res, Theme theme, AttributeSet attrs, ValueAnimator anim, float pathErrorScale, XmlPullParser parser) throws NotFoundException {
      TypedArray arrayAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATOR);
      TypedArray arrayObjectAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
      if (anim == null) {
         anim = new ValueAnimator();
      }

      parseAnimatorFromTypeArray(anim, arrayAnimator, arrayObjectAnimator, pathErrorScale, parser);
      int resID = TypedArrayUtils.getNamedResourceId(arrayAnimator, parser, "interpolator", 0, 0);
      if (resID > 0) {
         Interpolator interpolator = AnimationUtilsCompat.loadInterpolator(context, resID);
         anim.setInterpolator(interpolator);
      }

      arrayAnimator.recycle();
      if (arrayObjectAnimator != null) {
         arrayObjectAnimator.recycle();
      }

      return anim;
   }

   private static boolean isColorType(int type) {
      return type >= 28 && type <= 31;
   }

   private static class PathDataEvaluator implements TypeEvaluator {
      private PathDataNode[] mNodeArray;

      private PathDataEvaluator() {
      }

      PathDataEvaluator(PathDataNode[] nodeArray) {
         this.mNodeArray = nodeArray;
      }

      public PathDataNode[] evaluate(float fraction, PathDataNode[] startPathData, PathDataNode[] endPathData) {
         if (!PathParser.canMorph(startPathData, endPathData)) {
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
         } else {
            if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, startPathData)) {
               this.mNodeArray = PathParser.deepCopyNodes(startPathData);
            }

            for(int i = 0; i < startPathData.length; ++i) {
               this.mNodeArray[i].interpolatePathDataNode(startPathData[i], endPathData[i], fraction);
            }

            return this.mNodeArray;
         }
      }

      // $FF: synthetic method
      PathDataEvaluator(Object x0) {
         this();
      }
   }
}
