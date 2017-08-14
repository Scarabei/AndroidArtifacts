package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat extends VectorDrawableCommon {
   static final String LOGTAG = "VectorDrawableCompat";
   static final Mode DEFAULT_TINT_MODE;
   private static final String SHAPE_CLIP_PATH = "clip-path";
   private static final String SHAPE_GROUP = "group";
   private static final String SHAPE_PATH = "path";
   private static final String SHAPE_VECTOR = "vector";
   private static final int LINECAP_BUTT = 0;
   private static final int LINECAP_ROUND = 1;
   private static final int LINECAP_SQUARE = 2;
   private static final int LINEJOIN_MITER = 0;
   private static final int LINEJOIN_ROUND = 1;
   private static final int LINEJOIN_BEVEL = 2;
   private static final int MAX_CACHED_BITMAP_SIZE = 2048;
   private static final boolean DBG_VECTOR_DRAWABLE = false;
   private VectorDrawableCompat.VectorDrawableCompatState mVectorState;
   private PorterDuffColorFilter mTintFilter;
   private ColorFilter mColorFilter;
   private boolean mMutated;
   private boolean mAllowCaching = true;
   private ConstantState mCachedConstantStateDelegate;
   private final float[] mTmpFloats = new float[9];
   private final Matrix mTmpMatrix = new Matrix();
   private final Rect mTmpBounds = new Rect();

   VectorDrawableCompat() {
      this.mVectorState = new VectorDrawableCompat.VectorDrawableCompatState();
   }

   VectorDrawableCompat(@NonNull VectorDrawableCompat.VectorDrawableCompatState state) {
      this.mVectorState = state;
      this.mTintFilter = this.updateTintFilter(this.mTintFilter, state.mTint, state.mTintMode);
   }

   public Drawable mutate() {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.mutate();
         return this;
      } else {
         if (!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompat.VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
         }

         return this;
      }
   }

   Object getTargetByName(String name) {
      return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(name);
   }

   public ConstantState getConstantState() {
      if (this.mDelegateDrawable != null && VERSION.SDK_INT >= 24) {
         return new VectorDrawableCompat.VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
      } else {
         this.mVectorState.mChangingConfigurations = this.getChangingConfigurations();
         return this.mVectorState;
      }
   }

   public void draw(Canvas canvas) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.draw(canvas);
      } else {
         this.copyBounds(this.mTmpBounds);
         if (this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
            ColorFilter colorFilter = this.mColorFilter == null ? this.mTintFilter : this.mColorFilter;
            canvas.getMatrix(this.mTmpMatrix);
            this.mTmpMatrix.getValues(this.mTmpFloats);
            float canvasScaleX = Math.abs(this.mTmpFloats[0]);
            float canvasScaleY = Math.abs(this.mTmpFloats[4]);
            float canvasSkewX = Math.abs(this.mTmpFloats[1]);
            float canvasSkewY = Math.abs(this.mTmpFloats[3]);
            if (canvasSkewX != 0.0F || canvasSkewY != 0.0F) {
               canvasScaleX = 1.0F;
               canvasScaleY = 1.0F;
            }

            int scaledWidth = (int)((float)this.mTmpBounds.width() * canvasScaleX);
            int scaledHeight = (int)((float)this.mTmpBounds.height() * canvasScaleY);
            scaledWidth = Math.min(2048, scaledWidth);
            scaledHeight = Math.min(2048, scaledHeight);
            if (scaledWidth > 0 && scaledHeight > 0) {
               int saveCount = canvas.save();
               canvas.translate((float)this.mTmpBounds.left, (float)this.mTmpBounds.top);
               boolean needMirroring = this.needMirroring();
               if (needMirroring) {
                  canvas.translate((float)this.mTmpBounds.width(), 0.0F);
                  canvas.scale(-1.0F, 1.0F);
               }

               this.mTmpBounds.offsetTo(0, 0);
               this.mVectorState.createCachedBitmapIfNeeded(scaledWidth, scaledHeight);
               if (!this.mAllowCaching) {
                  this.mVectorState.updateCachedBitmap(scaledWidth, scaledHeight);
               } else if (!this.mVectorState.canReuseCache()) {
                  this.mVectorState.updateCachedBitmap(scaledWidth, scaledHeight);
                  this.mVectorState.updateCacheStates();
               }

               this.mVectorState.drawCachedBitmapWithRootAlpha(canvas, (ColorFilter)colorFilter, this.mTmpBounds);
               canvas.restoreToCount(saveCount);
            }
         }
      }
   }

   public int getAlpha() {
      return this.mDelegateDrawable != null ? DrawableCompat.getAlpha(this.mDelegateDrawable) : this.mVectorState.mVPathRenderer.getRootAlpha();
   }

   public void setAlpha(int alpha) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setAlpha(alpha);
      } else {
         if (this.mVectorState.mVPathRenderer.getRootAlpha() != alpha) {
            this.mVectorState.mVPathRenderer.setRootAlpha(alpha);
            this.invalidateSelf();
         }

      }
   }

   public void setColorFilter(ColorFilter colorFilter) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setColorFilter(colorFilter);
      } else {
         this.mColorFilter = colorFilter;
         this.invalidateSelf();
      }
   }

   PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter tintFilter, ColorStateList tint, Mode tintMode) {
      if (tint != null && tintMode != null) {
         int color = tint.getColorForState(this.getState(), 0);
         return new PorterDuffColorFilter(color, tintMode);
      } else {
         return null;
      }
   }

   public void setTint(int tint) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setTint(this.mDelegateDrawable, tint);
      } else {
         this.setTintList(ColorStateList.valueOf(tint));
      }
   }

   public void setTintList(ColorStateList tint) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setTintList(this.mDelegateDrawable, tint);
      } else {
         VectorDrawableCompat.VectorDrawableCompatState state = this.mVectorState;
         if (state.mTint != tint) {
            state.mTint = tint;
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, tint, state.mTintMode);
            this.invalidateSelf();
         }

      }
   }

   public void setTintMode(Mode tintMode) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setTintMode(this.mDelegateDrawable, tintMode);
      } else {
         VectorDrawableCompat.VectorDrawableCompatState state = this.mVectorState;
         if (state.mTintMode != tintMode) {
            state.mTintMode = tintMode;
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, state.mTint, tintMode);
            this.invalidateSelf();
         }

      }
   }

   public boolean isStateful() {
      if (this.mDelegateDrawable != null) {
         return this.mDelegateDrawable.isStateful();
      } else {
         return super.isStateful() || this.mVectorState != null && this.mVectorState.mTint != null && this.mVectorState.mTint.isStateful();
      }
   }

   protected boolean onStateChange(int[] stateSet) {
      if (this.mDelegateDrawable != null) {
         return this.mDelegateDrawable.setState(stateSet);
      } else {
         VectorDrawableCompat.VectorDrawableCompatState state = this.mVectorState;
         if (state.mTint != null && state.mTintMode != null) {
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, state.mTint, state.mTintMode);
            this.invalidateSelf();
            return true;
         } else {
            return false;
         }
      }
   }

   public int getOpacity() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getOpacity() : -3;
   }

   public int getIntrinsicWidth() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getIntrinsicWidth() : (int)this.mVectorState.mVPathRenderer.mBaseWidth;
   }

   public int getIntrinsicHeight() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getIntrinsicHeight() : (int)this.mVectorState.mVPathRenderer.mBaseHeight;
   }

   public boolean canApplyTheme() {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.canApplyTheme(this.mDelegateDrawable);
      }

      return false;
   }

   public boolean isAutoMirrored() {
      return this.mDelegateDrawable != null ? DrawableCompat.isAutoMirrored(this.mDelegateDrawable) : this.mVectorState.mAutoMirrored;
   }

   public void setAutoMirrored(boolean mirrored) {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.setAutoMirrored(this.mDelegateDrawable, mirrored);
      } else {
         this.mVectorState.mAutoMirrored = mirrored;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public float getPixelSize() {
      if (this.mVectorState != null && this.mVectorState.mVPathRenderer != null && this.mVectorState.mVPathRenderer.mBaseWidth != 0.0F && this.mVectorState.mVPathRenderer.mBaseHeight != 0.0F && this.mVectorState.mVPathRenderer.mViewportHeight != 0.0F && this.mVectorState.mVPathRenderer.mViewportWidth != 0.0F) {
         float intrinsicWidth = this.mVectorState.mVPathRenderer.mBaseWidth;
         float intrinsicHeight = this.mVectorState.mVPathRenderer.mBaseHeight;
         float viewportWidth = this.mVectorState.mVPathRenderer.mViewportWidth;
         float viewportHeight = this.mVectorState.mVPathRenderer.mViewportHeight;
         float scaleX = viewportWidth / intrinsicWidth;
         float scaleY = viewportHeight / intrinsicHeight;
         return Math.min(scaleX, scaleY);
      } else {
         return 1.0F;
      }
   }

   @Nullable
   public static VectorDrawableCompat create(@NonNull Resources res, @DrawableRes int resId, @Nullable Theme theme) {
      if (VERSION.SDK_INT >= 24) {
         VectorDrawableCompat drawable = new VectorDrawableCompat();
         drawable.mDelegateDrawable = ResourcesCompat.getDrawable(res, resId, theme);
         drawable.mCachedConstantStateDelegate = new VectorDrawableCompat.VectorDrawableDelegateState(drawable.mDelegateDrawable.getConstantState());
         return drawable;
      } else {
         try {
            XmlPullParser parser = res.getXml(resId);
            AttributeSet attrs = Xml.asAttributeSet(parser);

            int type;
            while((type = parser.next()) != 2 && type != 1) {
               ;
            }

            if (type != 2) {
               throw new XmlPullParserException("No start tag found");
            }

            return createFromXmlInner(res, parser, attrs, theme);
         } catch (XmlPullParserException var6) {
            Log.e("VectorDrawableCompat", "parser error", var6);
         } catch (IOException var7) {
            Log.e("VectorDrawableCompat", "parser error", var7);
         }

         return null;
      }
   }

   public static VectorDrawableCompat createFromXmlInner(Resources r, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
      VectorDrawableCompat drawable = new VectorDrawableCompat();
      drawable.inflate(r, parser, attrs, theme);
      return drawable;
   }

   static int applyAlpha(int color, float alpha) {
      int alphaBytes = Color.alpha(color);
      color &= 16777215;
      color |= (int)((float)alphaBytes * alpha) << 24;
      return color;
   }

   public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.inflate(res, parser, attrs);
      } else {
         this.inflate(res, parser, attrs, (Theme)null);
      }
   }

   public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
      if (this.mDelegateDrawable != null) {
         DrawableCompat.inflate(this.mDelegateDrawable, res, parser, attrs, theme);
      } else {
         VectorDrawableCompat.VectorDrawableCompatState state = this.mVectorState;
         VectorDrawableCompat.VPathRenderer pathRenderer = new VectorDrawableCompat.VPathRenderer();
         state.mVPathRenderer = pathRenderer;
         TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
         this.updateStateFromTypedArray(a, parser);
         a.recycle();
         state.mChangingConfigurations = this.getChangingConfigurations();
         state.mCacheDirty = true;
         this.inflateInternal(res, parser, attrs, theme);
         this.mTintFilter = this.updateTintFilter(this.mTintFilter, state.mTint, state.mTintMode);
      }
   }

   private static Mode parseTintModeCompat(int value, Mode defaultMode) {
      switch(value) {
      case 3:
         return Mode.SRC_OVER;
      case 4:
      case 6:
      case 7:
      case 8:
      case 10:
      case 11:
      case 12:
      case 13:
      default:
         return defaultMode;
      case 5:
         return Mode.SRC_IN;
      case 9:
         return Mode.SRC_ATOP;
      case 14:
         return Mode.MULTIPLY;
      case 15:
         return Mode.SCREEN;
      case 16:
         return VERSION.SDK_INT >= 11 ? Mode.ADD : defaultMode;
      }
   }

   private void updateStateFromTypedArray(TypedArray a, XmlPullParser parser) throws XmlPullParserException {
      VectorDrawableCompat.VectorDrawableCompatState state = this.mVectorState;
      VectorDrawableCompat.VPathRenderer pathRenderer = state.mVPathRenderer;
      int mode = TypedArrayUtils.getNamedInt(a, parser, "tintMode", 6, -1);
      state.mTintMode = parseTintModeCompat(mode, Mode.SRC_IN);
      ColorStateList tint = a.getColorStateList(1);
      if (tint != null) {
         state.mTint = tint;
      }

      state.mAutoMirrored = TypedArrayUtils.getNamedBoolean(a, parser, "autoMirrored", 5, state.mAutoMirrored);
      pathRenderer.mViewportWidth = TypedArrayUtils.getNamedFloat(a, parser, "viewportWidth", 7, pathRenderer.mViewportWidth);
      pathRenderer.mViewportHeight = TypedArrayUtils.getNamedFloat(a, parser, "viewportHeight", 8, pathRenderer.mViewportHeight);
      if (pathRenderer.mViewportWidth <= 0.0F) {
         throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
      } else if (pathRenderer.mViewportHeight <= 0.0F) {
         throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
      } else {
         pathRenderer.mBaseWidth = a.getDimension(3, pathRenderer.mBaseWidth);
         pathRenderer.mBaseHeight = a.getDimension(2, pathRenderer.mBaseHeight);
         if (pathRenderer.mBaseWidth <= 0.0F) {
            throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires width > 0");
         } else if (pathRenderer.mBaseHeight <= 0.0F) {
            throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires height > 0");
         } else {
            float alphaInFloat = TypedArrayUtils.getNamedFloat(a, parser, "alpha", 4, pathRenderer.getAlpha());
            pathRenderer.setAlpha(alphaInFloat);
            String name = a.getString(0);
            if (name != null) {
               pathRenderer.mRootName = name;
               pathRenderer.mVGTargetsMap.put(name, pathRenderer);
            }

         }
      }
   }

   private void inflateInternal(Resources res, XmlPullParser parser, AttributeSet attrs, Theme theme) throws XmlPullParserException, IOException {
      VectorDrawableCompat.VectorDrawableCompatState state = this.mVectorState;
      VectorDrawableCompat.VPathRenderer pathRenderer = state.mVPathRenderer;
      boolean noPathTag = true;
      Stack groupStack = new Stack();
      groupStack.push(pathRenderer.mRootGroup);
      int eventType = parser.getEventType();

      for(int innerDepth = parser.getDepth() + 1; eventType != 1 && (parser.getDepth() >= innerDepth || eventType != 3); eventType = parser.next()) {
         String tagName;
         if (eventType == 2) {
            tagName = parser.getName();
            VectorDrawableCompat.VGroup currentGroup = (VectorDrawableCompat.VGroup)groupStack.peek();
            if ("path".equals(tagName)) {
               VectorDrawableCompat.VFullPath path = new VectorDrawableCompat.VFullPath();
               path.inflate(res, attrs, theme, parser);
               currentGroup.mChildren.add(path);
               if (path.getPathName() != null) {
                  pathRenderer.mVGTargetsMap.put(path.getPathName(), path);
               }

               noPathTag = false;
               state.mChangingConfigurations |= path.mChangingConfigurations;
            } else if ("clip-path".equals(tagName)) {
               VectorDrawableCompat.VClipPath path = new VectorDrawableCompat.VClipPath();
               path.inflate(res, attrs, theme, parser);
               currentGroup.mChildren.add(path);
               if (path.getPathName() != null) {
                  pathRenderer.mVGTargetsMap.put(path.getPathName(), path);
               }

               state.mChangingConfigurations |= path.mChangingConfigurations;
            } else if ("group".equals(tagName)) {
               VectorDrawableCompat.VGroup newChildGroup = new VectorDrawableCompat.VGroup();
               newChildGroup.inflate(res, attrs, theme, parser);
               currentGroup.mChildren.add(newChildGroup);
               groupStack.push(newChildGroup);
               if (newChildGroup.getGroupName() != null) {
                  pathRenderer.mVGTargetsMap.put(newChildGroup.getGroupName(), newChildGroup);
               }

               state.mChangingConfigurations |= newChildGroup.mChangingConfigurations;
            }
         } else if (eventType == 3) {
            tagName = parser.getName();
            if ("group".equals(tagName)) {
               groupStack.pop();
            }
         }
      }

      if (noPathTag) {
         StringBuffer tag = new StringBuffer();
         if (tag.length() > 0) {
            tag.append(" or ");
         }

         tag.append("path");
         throw new XmlPullParserException("no " + tag + " defined");
      }
   }

   private void printGroupTree(VectorDrawableCompat.VGroup currentGroup, int level) {
      String indent = "";

      int i;
      for(i = 0; i < level; ++i) {
         indent = indent + "    ";
      }

      Log.v("VectorDrawableCompat", indent + "current group is :" + currentGroup.getGroupName() + " rotation is " + currentGroup.mRotate);
      Log.v("VectorDrawableCompat", indent + "matrix is :" + currentGroup.getLocalMatrix().toString());

      for(i = 0; i < currentGroup.mChildren.size(); ++i) {
         Object child = currentGroup.mChildren.get(i);
         if (child instanceof VectorDrawableCompat.VGroup) {
            this.printGroupTree((VectorDrawableCompat.VGroup)child, level + 1);
         } else {
            ((VectorDrawableCompat.VPath)child).printVPath(level + 1);
         }
      }

   }

   void setAllowCaching(boolean allowCaching) {
      this.mAllowCaching = allowCaching;
   }

   private boolean needMirroring() {
      if (VERSION.SDK_INT < 17) {
         return false;
      } else {
         return this.isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1;
      }
   }

   protected void onBoundsChange(Rect bounds) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.setBounds(bounds);
      }

   }

   public int getChangingConfigurations() {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.getChangingConfigurations() : super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
   }

   public void invalidateSelf() {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.invalidateSelf();
      } else {
         super.invalidateSelf();
      }
   }

   public void scheduleSelf(Runnable what, long when) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.scheduleSelf(what, when);
      } else {
         super.scheduleSelf(what, when);
      }
   }

   public boolean setVisible(boolean visible, boolean restart) {
      return this.mDelegateDrawable != null ? this.mDelegateDrawable.setVisible(visible, restart) : super.setVisible(visible, restart);
   }

   public void unscheduleSelf(Runnable what) {
      if (this.mDelegateDrawable != null) {
         this.mDelegateDrawable.unscheduleSelf(what);
      } else {
         super.unscheduleSelf(what);
      }
   }

   static {
      DEFAULT_TINT_MODE = Mode.SRC_IN;
   }

   private static class VFullPath extends VectorDrawableCompat.VPath {
      private int[] mThemeAttrs;
      int mStrokeColor = 0;
      float mStrokeWidth = 0.0F;
      int mFillColor = 0;
      float mStrokeAlpha = 1.0F;
      int mFillRule = 0;
      float mFillAlpha = 1.0F;
      float mTrimPathStart = 0.0F;
      float mTrimPathEnd = 1.0F;
      float mTrimPathOffset = 0.0F;
      Cap mStrokeLineCap;
      Join mStrokeLineJoin;
      float mStrokeMiterlimit;

      public VFullPath() {
         this.mStrokeLineCap = Cap.BUTT;
         this.mStrokeLineJoin = Join.MITER;
         this.mStrokeMiterlimit = 4.0F;
      }

      public VFullPath(VectorDrawableCompat.VFullPath copy) {
         super(copy);
         this.mStrokeLineCap = Cap.BUTT;
         this.mStrokeLineJoin = Join.MITER;
         this.mStrokeMiterlimit = 4.0F;
         this.mThemeAttrs = copy.mThemeAttrs;
         this.mStrokeColor = copy.mStrokeColor;
         this.mStrokeWidth = copy.mStrokeWidth;
         this.mStrokeAlpha = copy.mStrokeAlpha;
         this.mFillColor = copy.mFillColor;
         this.mFillRule = copy.mFillRule;
         this.mFillAlpha = copy.mFillAlpha;
         this.mTrimPathStart = copy.mTrimPathStart;
         this.mTrimPathEnd = copy.mTrimPathEnd;
         this.mTrimPathOffset = copy.mTrimPathOffset;
         this.mStrokeLineCap = copy.mStrokeLineCap;
         this.mStrokeLineJoin = copy.mStrokeLineJoin;
         this.mStrokeMiterlimit = copy.mStrokeMiterlimit;
      }

      private Cap getStrokeLineCap(int id, Cap defValue) {
         switch(id) {
         case 0:
            return Cap.BUTT;
         case 1:
            return Cap.ROUND;
         case 2:
            return Cap.SQUARE;
         default:
            return defValue;
         }
      }

      private Join getStrokeLineJoin(int id, Join defValue) {
         switch(id) {
         case 0:
            return Join.MITER;
         case 1:
            return Join.ROUND;
         case 2:
            return Join.BEVEL;
         default:
            return defValue;
         }
      }

      public boolean canApplyTheme() {
         return this.mThemeAttrs != null;
      }

      public void inflate(Resources r, AttributeSet attrs, Theme theme, XmlPullParser parser) {
         TypedArray a = TypedArrayUtils.obtainAttributes(r, theme, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
         this.updateStateFromTypedArray(a, parser);
         a.recycle();
      }

      private void updateStateFromTypedArray(TypedArray a, XmlPullParser parser) {
         this.mThemeAttrs = null;
         boolean hasPathData = TypedArrayUtils.hasAttribute(parser, "pathData");
         if (hasPathData) {
            String pathName = a.getString(0);
            if (pathName != null) {
               this.mPathName = pathName;
            }

            String pathData = a.getString(2);
            if (pathData != null) {
               this.mNodes = PathParser.createNodesFromPathData(pathData);
            }

            this.mFillColor = TypedArrayUtils.getNamedColor(a, parser, "fillColor", 1, this.mFillColor);
            this.mFillAlpha = TypedArrayUtils.getNamedFloat(a, parser, "fillAlpha", 12, this.mFillAlpha);
            int lineCap = TypedArrayUtils.getNamedInt(a, parser, "strokeLineCap", 8, -1);
            this.mStrokeLineCap = this.getStrokeLineCap(lineCap, this.mStrokeLineCap);
            int lineJoin = TypedArrayUtils.getNamedInt(a, parser, "strokeLineJoin", 9, -1);
            this.mStrokeLineJoin = this.getStrokeLineJoin(lineJoin, this.mStrokeLineJoin);
            this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(a, parser, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
            this.mStrokeColor = TypedArrayUtils.getNamedColor(a, parser, "strokeColor", 3, this.mStrokeColor);
            this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(a, parser, "strokeAlpha", 11, this.mStrokeAlpha);
            this.mStrokeWidth = TypedArrayUtils.getNamedFloat(a, parser, "strokeWidth", 4, this.mStrokeWidth);
            this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(a, parser, "trimPathEnd", 6, this.mTrimPathEnd);
            this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(a, parser, "trimPathOffset", 7, this.mTrimPathOffset);
            this.mTrimPathStart = TypedArrayUtils.getNamedFloat(a, parser, "trimPathStart", 5, this.mTrimPathStart);
            this.mFillRule = TypedArrayUtils.getNamedInt(a, parser, "fillType", 13, this.mFillRule);
         }
      }

      public void applyTheme(Theme t) {
         if (this.mThemeAttrs != null) {
            ;
         }
      }

      int getStrokeColor() {
         return this.mStrokeColor;
      }

      void setStrokeColor(int strokeColor) {
         this.mStrokeColor = strokeColor;
      }

      float getStrokeWidth() {
         return this.mStrokeWidth;
      }

      void setStrokeWidth(float strokeWidth) {
         this.mStrokeWidth = strokeWidth;
      }

      float getStrokeAlpha() {
         return this.mStrokeAlpha;
      }

      void setStrokeAlpha(float strokeAlpha) {
         this.mStrokeAlpha = strokeAlpha;
      }

      int getFillColor() {
         return this.mFillColor;
      }

      void setFillColor(int fillColor) {
         this.mFillColor = fillColor;
      }

      float getFillAlpha() {
         return this.mFillAlpha;
      }

      void setFillAlpha(float fillAlpha) {
         this.mFillAlpha = fillAlpha;
      }

      float getTrimPathStart() {
         return this.mTrimPathStart;
      }

      void setTrimPathStart(float trimPathStart) {
         this.mTrimPathStart = trimPathStart;
      }

      float getTrimPathEnd() {
         return this.mTrimPathEnd;
      }

      void setTrimPathEnd(float trimPathEnd) {
         this.mTrimPathEnd = trimPathEnd;
      }

      float getTrimPathOffset() {
         return this.mTrimPathOffset;
      }

      void setTrimPathOffset(float trimPathOffset) {
         this.mTrimPathOffset = trimPathOffset;
      }
   }

   private static class VClipPath extends VectorDrawableCompat.VPath {
      public VClipPath() {
      }

      public VClipPath(VectorDrawableCompat.VClipPath copy) {
         super(copy);
      }

      public void inflate(Resources r, AttributeSet attrs, Theme theme, XmlPullParser parser) {
         boolean hasPathData = TypedArrayUtils.hasAttribute(parser, "pathData");
         if (hasPathData) {
            TypedArray a = TypedArrayUtils.obtainAttributes(r, theme, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
            this.updateStateFromTypedArray(a);
            a.recycle();
         }
      }

      private void updateStateFromTypedArray(TypedArray a) {
         String pathName = a.getString(0);
         if (pathName != null) {
            this.mPathName = pathName;
         }

         String pathData = a.getString(1);
         if (pathData != null) {
            this.mNodes = PathParser.createNodesFromPathData(pathData);
         }

      }

      public boolean isClipPath() {
         return true;
      }
   }

   private static class VPath {
      protected PathDataNode[] mNodes = null;
      String mPathName;
      int mChangingConfigurations;

      public VPath() {
      }

      public void printVPath(int level) {
         String indent = "";

         for(int i = 0; i < level; ++i) {
            indent = indent + "    ";
         }

         Log.v("VectorDrawableCompat", indent + "current path is :" + this.mPathName + " pathData is " + this.nodesToString(this.mNodes));
      }

      public String nodesToString(PathDataNode[] nodes) {
         String result = " ";

         for(int i = 0; i < nodes.length; ++i) {
            result = result + nodes[i].mType + ":";
            float[] params = nodes[i].mParams;

            for(int j = 0; j < params.length; ++j) {
               result = result + params[j] + ",";
            }
         }

         return result;
      }

      public VPath(VectorDrawableCompat.VPath copy) {
         this.mPathName = copy.mPathName;
         this.mChangingConfigurations = copy.mChangingConfigurations;
         this.mNodes = PathParser.deepCopyNodes(copy.mNodes);
      }

      public void toPath(Path path) {
         path.reset();
         if (this.mNodes != null) {
            PathDataNode.nodesToPath(this.mNodes, path);
         }

      }

      public String getPathName() {
         return this.mPathName;
      }

      public boolean canApplyTheme() {
         return false;
      }

      public void applyTheme(Theme t) {
      }

      public boolean isClipPath() {
         return false;
      }

      public PathDataNode[] getPathData() {
         return this.mNodes;
      }

      public void setPathData(PathDataNode[] nodes) {
         if (!PathParser.canMorph(this.mNodes, nodes)) {
            this.mNodes = PathParser.deepCopyNodes(nodes);
         } else {
            PathParser.updateNodes(this.mNodes, nodes);
         }

      }
   }

   private static class VGroup {
      private final Matrix mStackedMatrix = new Matrix();
      final ArrayList mChildren = new ArrayList();
      float mRotate = 0.0F;
      private float mPivotX = 0.0F;
      private float mPivotY = 0.0F;
      private float mScaleX = 1.0F;
      private float mScaleY = 1.0F;
      private float mTranslateX = 0.0F;
      private float mTranslateY = 0.0F;
      private final Matrix mLocalMatrix = new Matrix();
      int mChangingConfigurations;
      private int[] mThemeAttrs;
      private String mGroupName = null;

      public VGroup(VectorDrawableCompat.VGroup copy, ArrayMap targetsMap) {
         this.mRotate = copy.mRotate;
         this.mPivotX = copy.mPivotX;
         this.mPivotY = copy.mPivotY;
         this.mScaleX = copy.mScaleX;
         this.mScaleY = copy.mScaleY;
         this.mTranslateX = copy.mTranslateX;
         this.mTranslateY = copy.mTranslateY;
         this.mThemeAttrs = copy.mThemeAttrs;
         this.mGroupName = copy.mGroupName;
         this.mChangingConfigurations = copy.mChangingConfigurations;
         if (this.mGroupName != null) {
            targetsMap.put(this.mGroupName, this);
         }

         this.mLocalMatrix.set(copy.mLocalMatrix);
         ArrayList children = copy.mChildren;

         for(int i = 0; i < children.size(); ++i) {
            Object copyChild = children.get(i);
            VectorDrawableCompat.VGroup copyGroup;
            if (copyChild instanceof VectorDrawableCompat.VGroup) {
               copyGroup = (VectorDrawableCompat.VGroup)copyChild;
               this.mChildren.add(new VectorDrawableCompat.VGroup(copyGroup, targetsMap));
            } else {
               copyGroup = null;
               Object newPath;
               if (copyChild instanceof VectorDrawableCompat.VFullPath) {
                  newPath = new VectorDrawableCompat.VFullPath((VectorDrawableCompat.VFullPath)copyChild);
               } else {
                  if (!(copyChild instanceof VectorDrawableCompat.VClipPath)) {
                     throw new IllegalStateException("Unknown object in the tree!");
                  }

                  newPath = new VectorDrawableCompat.VClipPath((VectorDrawableCompat.VClipPath)copyChild);
               }

               this.mChildren.add(newPath);
               if (((VectorDrawableCompat.VPath)newPath).mPathName != null) {
                  targetsMap.put(((VectorDrawableCompat.VPath)newPath).mPathName, newPath);
               }
            }
         }

      }

      public VGroup() {
      }

      public String getGroupName() {
         return this.mGroupName;
      }

      public Matrix getLocalMatrix() {
         return this.mLocalMatrix;
      }

      public void inflate(Resources res, AttributeSet attrs, Theme theme, XmlPullParser parser) {
         TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
         this.updateStateFromTypedArray(a, parser);
         a.recycle();
      }

      private void updateStateFromTypedArray(TypedArray a, XmlPullParser parser) {
         this.mThemeAttrs = null;
         this.mRotate = TypedArrayUtils.getNamedFloat(a, parser, "rotation", 5, this.mRotate);
         this.mPivotX = a.getFloat(1, this.mPivotX);
         this.mPivotY = a.getFloat(2, this.mPivotY);
         this.mScaleX = TypedArrayUtils.getNamedFloat(a, parser, "scaleX", 3, this.mScaleX);
         this.mScaleY = TypedArrayUtils.getNamedFloat(a, parser, "scaleY", 4, this.mScaleY);
         this.mTranslateX = TypedArrayUtils.getNamedFloat(a, parser, "translateX", 6, this.mTranslateX);
         this.mTranslateY = TypedArrayUtils.getNamedFloat(a, parser, "translateY", 7, this.mTranslateY);
         String groupName = a.getString(0);
         if (groupName != null) {
            this.mGroupName = groupName;
         }

         this.updateLocalMatrix();
      }

      private void updateLocalMatrix() {
         this.mLocalMatrix.reset();
         this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
         this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
         this.mLocalMatrix.postRotate(this.mRotate, 0.0F, 0.0F);
         this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
      }

      public float getRotation() {
         return this.mRotate;
      }

      public void setRotation(float rotation) {
         if (rotation != this.mRotate) {
            this.mRotate = rotation;
            this.updateLocalMatrix();
         }

      }

      public float getPivotX() {
         return this.mPivotX;
      }

      public void setPivotX(float pivotX) {
         if (pivotX != this.mPivotX) {
            this.mPivotX = pivotX;
            this.updateLocalMatrix();
         }

      }

      public float getPivotY() {
         return this.mPivotY;
      }

      public void setPivotY(float pivotY) {
         if (pivotY != this.mPivotY) {
            this.mPivotY = pivotY;
            this.updateLocalMatrix();
         }

      }

      public float getScaleX() {
         return this.mScaleX;
      }

      public void setScaleX(float scaleX) {
         if (scaleX != this.mScaleX) {
            this.mScaleX = scaleX;
            this.updateLocalMatrix();
         }

      }

      public float getScaleY() {
         return this.mScaleY;
      }

      public void setScaleY(float scaleY) {
         if (scaleY != this.mScaleY) {
            this.mScaleY = scaleY;
            this.updateLocalMatrix();
         }

      }

      public float getTranslateX() {
         return this.mTranslateX;
      }

      public void setTranslateX(float translateX) {
         if (translateX != this.mTranslateX) {
            this.mTranslateX = translateX;
            this.updateLocalMatrix();
         }

      }

      public float getTranslateY() {
         return this.mTranslateY;
      }

      public void setTranslateY(float translateY) {
         if (translateY != this.mTranslateY) {
            this.mTranslateY = translateY;
            this.updateLocalMatrix();
         }

      }
   }

   private static class VPathRenderer {
      private final Path mPath;
      private final Path mRenderPath;
      private static final Matrix IDENTITY_MATRIX = new Matrix();
      private final Matrix mFinalPathMatrix = new Matrix();
      private Paint mStrokePaint;
      private Paint mFillPaint;
      private PathMeasure mPathMeasure;
      private int mChangingConfigurations;
      final VectorDrawableCompat.VGroup mRootGroup;
      float mBaseWidth = 0.0F;
      float mBaseHeight = 0.0F;
      float mViewportWidth = 0.0F;
      float mViewportHeight = 0.0F;
      int mRootAlpha = 255;
      String mRootName = null;
      final ArrayMap mVGTargetsMap = new ArrayMap();

      public VPathRenderer() {
         this.mRootGroup = new VectorDrawableCompat.VGroup();
         this.mPath = new Path();
         this.mRenderPath = new Path();
      }

      public void setRootAlpha(int alpha) {
         this.mRootAlpha = alpha;
      }

      public int getRootAlpha() {
         return this.mRootAlpha;
      }

      public void setAlpha(float alpha) {
         this.setRootAlpha((int)(alpha * 255.0F));
      }

      public float getAlpha() {
         return (float)this.getRootAlpha() / 255.0F;
      }

      public VPathRenderer(VectorDrawableCompat.VPathRenderer copy) {
         this.mRootGroup = new VectorDrawableCompat.VGroup(copy.mRootGroup, this.mVGTargetsMap);
         this.mPath = new Path(copy.mPath);
         this.mRenderPath = new Path(copy.mRenderPath);
         this.mBaseWidth = copy.mBaseWidth;
         this.mBaseHeight = copy.mBaseHeight;
         this.mViewportWidth = copy.mViewportWidth;
         this.mViewportHeight = copy.mViewportHeight;
         this.mChangingConfigurations = copy.mChangingConfigurations;
         this.mRootAlpha = copy.mRootAlpha;
         this.mRootName = copy.mRootName;
         if (copy.mRootName != null) {
            this.mVGTargetsMap.put(copy.mRootName, this);
         }

      }

      private void drawGroupTree(VectorDrawableCompat.VGroup currentGroup, Matrix currentMatrix, Canvas canvas, int w, int h, ColorFilter filter) {
         currentGroup.mStackedMatrix.set(currentMatrix);
         currentGroup.mStackedMatrix.preConcat(currentGroup.mLocalMatrix);
         canvas.save();

         for(int i = 0; i < currentGroup.mChildren.size(); ++i) {
            Object child = currentGroup.mChildren.get(i);
            if (child instanceof VectorDrawableCompat.VGroup) {
               VectorDrawableCompat.VGroup childGroup = (VectorDrawableCompat.VGroup)child;
               this.drawGroupTree(childGroup, currentGroup.mStackedMatrix, canvas, w, h, filter);
            } else if (child instanceof VectorDrawableCompat.VPath) {
               VectorDrawableCompat.VPath childPath = (VectorDrawableCompat.VPath)child;
               this.drawPath(currentGroup, childPath, canvas, w, h, filter);
            }
         }

         canvas.restore();
      }

      public void draw(Canvas canvas, int w, int h, ColorFilter filter) {
         this.drawGroupTree(this.mRootGroup, IDENTITY_MATRIX, canvas, w, h, filter);
      }

      private void drawPath(VectorDrawableCompat.VGroup vGroup, VectorDrawableCompat.VPath vPath, Canvas canvas, int w, int h, ColorFilter filter) {
         float scaleX = (float)w / this.mViewportWidth;
         float scaleY = (float)h / this.mViewportHeight;
         float minScale = Math.min(scaleX, scaleY);
         Matrix groupStackedMatrix = vGroup.mStackedMatrix;
         this.mFinalPathMatrix.set(groupStackedMatrix);
         this.mFinalPathMatrix.postScale(scaleX, scaleY);
         float matrixScale = this.getMatrixScale(groupStackedMatrix);
         if (matrixScale != 0.0F) {
            vPath.toPath(this.mPath);
            Path path = this.mPath;
            this.mRenderPath.reset();
            if (vPath.isClipPath()) {
               this.mRenderPath.addPath(path, this.mFinalPathMatrix);
               canvas.clipPath(this.mRenderPath);
            } else {
               VectorDrawableCompat.VFullPath fullPath = (VectorDrawableCompat.VFullPath)vPath;
               float finalStrokeScale;
               if (fullPath.mTrimPathStart != 0.0F || fullPath.mTrimPathEnd != 1.0F) {
                  float start = (fullPath.mTrimPathStart + fullPath.mTrimPathOffset) % 1.0F;
                  finalStrokeScale = (fullPath.mTrimPathEnd + fullPath.mTrimPathOffset) % 1.0F;
                  if (this.mPathMeasure == null) {
                     this.mPathMeasure = new PathMeasure();
                  }

                  this.mPathMeasure.setPath(this.mPath, false);
                  float len = this.mPathMeasure.getLength();
                  start *= len;
                  finalStrokeScale *= len;
                  path.reset();
                  if (start > finalStrokeScale) {
                     this.mPathMeasure.getSegment(start, len, path, true);
                     this.mPathMeasure.getSegment(0.0F, finalStrokeScale, path, true);
                  } else {
                     this.mPathMeasure.getSegment(start, finalStrokeScale, path, true);
                  }

                  path.rLineTo(0.0F, 0.0F);
               }

               this.mRenderPath.addPath(path, this.mFinalPathMatrix);
               Paint strokePaint;
               if (fullPath.mFillColor != 0) {
                  if (this.mFillPaint == null) {
                     this.mFillPaint = new Paint();
                     this.mFillPaint.setStyle(Style.FILL);
                     this.mFillPaint.setAntiAlias(true);
                  }

                  strokePaint = this.mFillPaint;
                  strokePaint.setColor(VectorDrawableCompat.applyAlpha(fullPath.mFillColor, fullPath.mFillAlpha));
                  strokePaint.setColorFilter(filter);
                  this.mRenderPath.setFillType(fullPath.mFillRule == 0 ? FillType.WINDING : FillType.EVEN_ODD);
                  canvas.drawPath(this.mRenderPath, strokePaint);
               }

               if (fullPath.mStrokeColor != 0) {
                  if (this.mStrokePaint == null) {
                     this.mStrokePaint = new Paint();
                     this.mStrokePaint.setStyle(Style.STROKE);
                     this.mStrokePaint.setAntiAlias(true);
                  }

                  strokePaint = this.mStrokePaint;
                  if (fullPath.mStrokeLineJoin != null) {
                     strokePaint.setStrokeJoin(fullPath.mStrokeLineJoin);
                  }

                  if (fullPath.mStrokeLineCap != null) {
                     strokePaint.setStrokeCap(fullPath.mStrokeLineCap);
                  }

                  strokePaint.setStrokeMiter(fullPath.mStrokeMiterlimit);
                  strokePaint.setColor(VectorDrawableCompat.applyAlpha(fullPath.mStrokeColor, fullPath.mStrokeAlpha));
                  strokePaint.setColorFilter(filter);
                  finalStrokeScale = minScale * matrixScale;
                  strokePaint.setStrokeWidth(fullPath.mStrokeWidth * finalStrokeScale);
                  canvas.drawPath(this.mRenderPath, strokePaint);
               }
            }

         }
      }

      private static float cross(float v1x, float v1y, float v2x, float v2y) {
         return v1x * v2y - v1y * v2x;
      }

      private float getMatrixScale(Matrix groupStackedMatrix) {
         float[] unitVectors = new float[]{0.0F, 1.0F, 1.0F, 0.0F};
         groupStackedMatrix.mapVectors(unitVectors);
         float scaleX = (float)Math.hypot((double)unitVectors[0], (double)unitVectors[1]);
         float scaleY = (float)Math.hypot((double)unitVectors[2], (double)unitVectors[3]);
         float crossProduct = cross(unitVectors[0], unitVectors[1], unitVectors[2], unitVectors[3]);
         float maxScale = Math.max(scaleX, scaleY);
         float matrixScale = 0.0F;
         if (maxScale > 0.0F) {
            matrixScale = Math.abs(crossProduct) / maxScale;
         }

         return matrixScale;
      }
   }

   private static class VectorDrawableCompatState extends ConstantState {
      int mChangingConfigurations;
      VectorDrawableCompat.VPathRenderer mVPathRenderer;
      ColorStateList mTint = null;
      Mode mTintMode;
      boolean mAutoMirrored;
      Bitmap mCachedBitmap;
      int[] mCachedThemeAttrs;
      ColorStateList mCachedTint;
      Mode mCachedTintMode;
      int mCachedRootAlpha;
      boolean mCachedAutoMirrored;
      boolean mCacheDirty;
      Paint mTempPaint;

      public VectorDrawableCompatState(VectorDrawableCompat.VectorDrawableCompatState copy) {
         this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
         if (copy != null) {
            this.mChangingConfigurations = copy.mChangingConfigurations;
            this.mVPathRenderer = new VectorDrawableCompat.VPathRenderer(copy.mVPathRenderer);
            if (copy.mVPathRenderer.mFillPaint != null) {
               this.mVPathRenderer.mFillPaint = new Paint(copy.mVPathRenderer.mFillPaint);
            }

            if (copy.mVPathRenderer.mStrokePaint != null) {
               this.mVPathRenderer.mStrokePaint = new Paint(copy.mVPathRenderer.mStrokePaint);
            }

            this.mTint = copy.mTint;
            this.mTintMode = copy.mTintMode;
            this.mAutoMirrored = copy.mAutoMirrored;
         }

      }

      public void drawCachedBitmapWithRootAlpha(Canvas canvas, ColorFilter filter, Rect originalBounds) {
         Paint p = this.getPaint(filter);
         canvas.drawBitmap(this.mCachedBitmap, (Rect)null, originalBounds, p);
      }

      public boolean hasTranslucentRoot() {
         return this.mVPathRenderer.getRootAlpha() < 255;
      }

      public Paint getPaint(ColorFilter filter) {
         if (!this.hasTranslucentRoot() && filter == null) {
            return null;
         } else {
            if (this.mTempPaint == null) {
               this.mTempPaint = new Paint();
               this.mTempPaint.setFilterBitmap(true);
            }

            this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
            this.mTempPaint.setColorFilter(filter);
            return this.mTempPaint;
         }
      }

      public void updateCachedBitmap(int width, int height) {
         this.mCachedBitmap.eraseColor(0);
         Canvas tmpCanvas = new Canvas(this.mCachedBitmap);
         this.mVPathRenderer.draw(tmpCanvas, width, height, (ColorFilter)null);
      }

      public void createCachedBitmapIfNeeded(int width, int height) {
         if (this.mCachedBitmap == null || !this.canReuseBitmap(width, height)) {
            this.mCachedBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            this.mCacheDirty = true;
         }

      }

      public boolean canReuseBitmap(int width, int height) {
         return width == this.mCachedBitmap.getWidth() && height == this.mCachedBitmap.getHeight();
      }

      public boolean canReuseCache() {
         return !this.mCacheDirty && this.mCachedTint == this.mTint && this.mCachedTintMode == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.mCachedRootAlpha == this.mVPathRenderer.getRootAlpha();
      }

      public void updateCacheStates() {
         this.mCachedTint = this.mTint;
         this.mCachedTintMode = this.mTintMode;
         this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
         this.mCachedAutoMirrored = this.mAutoMirrored;
         this.mCacheDirty = false;
      }

      public VectorDrawableCompatState() {
         this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
         this.mVPathRenderer = new VectorDrawableCompat.VPathRenderer();
      }

      public Drawable newDrawable() {
         return new VectorDrawableCompat(this);
      }

      public Drawable newDrawable(Resources res) {
         return new VectorDrawableCompat(this);
      }

      public int getChangingConfigurations() {
         return this.mChangingConfigurations;
      }
   }

   @RequiresApi(24)
   private static class VectorDrawableDelegateState extends ConstantState {
      private final ConstantState mDelegateState;

      public VectorDrawableDelegateState(ConstantState state) {
         this.mDelegateState = state;
      }

      public Drawable newDrawable() {
         VectorDrawableCompat drawableCompat = new VectorDrawableCompat();
         drawableCompat.mDelegateDrawable = (VectorDrawable)this.mDelegateState.newDrawable();
         return drawableCompat;
      }

      public Drawable newDrawable(Resources res) {
         VectorDrawableCompat drawableCompat = new VectorDrawableCompat();
         drawableCompat.mDelegateDrawable = (VectorDrawable)this.mDelegateState.newDrawable(res);
         return drawableCompat;
      }

      public Drawable newDrawable(Resources res, Theme theme) {
         VectorDrawableCompat drawableCompat = new VectorDrawableCompat();
         drawableCompat.mDelegateDrawable = (VectorDrawable)this.mDelegateState.newDrawable(res, theme);
         return drawableCompat;
      }

      public boolean canApplyTheme() {
         return this.mDelegateState.canApplyTheme();
      }

      public int getChangingConfigurations() {
         return this.mDelegateState.getChangingConfigurations();
      }
   }
}
