package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.color;
import android.support.v7.appcompat.R.drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class AppCompatDrawableManager {
   private static final String TAG = "AppCompatDrawableManager";
   private static final boolean DEBUG = false;
   private static final Mode DEFAULT_MODE;
   private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
   private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
   private static AppCompatDrawableManager INSTANCE;
   private static final AppCompatDrawableManager.ColorFilterLruCache COLOR_FILTER_CACHE;
   private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
   private static final int[] TINT_COLOR_CONTROL_NORMAL;
   private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
   private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
   private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
   private static final int[] TINT_CHECKABLE_BUTTON_LIST;
   private WeakHashMap mTintLists;
   private ArrayMap mDelegates;
   private SparseArrayCompat mKnownDrawableIdTags;
   private final Object mDrawableCacheLock = new Object();
   private final WeakHashMap mDrawableCaches = new WeakHashMap(0);
   private TypedValue mTypedValue;
   private boolean mHasCheckedVectorDrawableSetup;

   public static AppCompatDrawableManager get() {
      if (INSTANCE == null) {
         INSTANCE = new AppCompatDrawableManager();
         installDefaultInflateDelegates(INSTANCE);
      }

      return INSTANCE;
   }

   private static void installDefaultInflateDelegates(@NonNull AppCompatDrawableManager manager) {
      if (VERSION.SDK_INT < 24) {
         manager.addDelegate("vector", new AppCompatDrawableManager.VdcInflateDelegate());
         if (VERSION.SDK_INT >= 11) {
            manager.addDelegate("animated-vector", new AppCompatDrawableManager.AvdcInflateDelegate());
         }
      }

   }

   public Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
      return this.getDrawable(context, resId, false);
   }

   Drawable getDrawable(@NonNull Context context, @DrawableRes int resId, boolean failIfNotKnown) {
      this.checkVectorDrawableSetup(context);
      Drawable drawable = this.loadDrawableFromDelegates(context, resId);
      if (drawable == null) {
         drawable = this.createDrawableIfNeeded(context, resId);
      }

      if (drawable == null) {
         drawable = ContextCompat.getDrawable(context, resId);
      }

      if (drawable != null) {
         drawable = this.tintDrawable(context, resId, failIfNotKnown, drawable);
      }

      if (drawable != null) {
         DrawableUtils.fixDrawable(drawable);
      }

      return drawable;
   }

   public void onConfigurationChanged(@NonNull Context context) {
      Object var2 = this.mDrawableCacheLock;
      synchronized(this.mDrawableCacheLock) {
         LongSparseArray cache = (LongSparseArray)this.mDrawableCaches.get(context);
         if (cache != null) {
            cache.clear();
         }

      }
   }

   private static long createCacheKey(TypedValue tv) {
      return (long)tv.assetCookie << 32 | (long)tv.data;
   }

   private Drawable createDrawableIfNeeded(@NonNull Context context, @DrawableRes int resId) {
      if (this.mTypedValue == null) {
         this.mTypedValue = new TypedValue();
      }

      TypedValue tv = this.mTypedValue;
      context.getResources().getValue(resId, tv, true);
      long key = createCacheKey(tv);
      Drawable dr = this.getCachedDrawable(context, key);
      if (dr != null) {
         return (Drawable)dr;
      } else {
         if (resId == drawable.abc_cab_background_top_material) {
            dr = new LayerDrawable(new Drawable[]{this.getDrawable(context, drawable.abc_cab_background_internal_bg), this.getDrawable(context, drawable.abc_cab_background_top_mtrl_alpha)});
         }

         if (dr != null) {
            ((Drawable)dr).setChangingConfigurations(tv.changingConfigurations);
            this.addDrawableToCache(context, key, (Drawable)dr);
         }

         return (Drawable)dr;
      }
   }

   private Drawable tintDrawable(@NonNull Context context, @DrawableRes int resId, boolean failIfNotKnown, @NonNull Drawable drawable) {
      ColorStateList tintList = this.getTintList(context, resId);
      if (tintList != null) {
         if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
         }

         drawable = DrawableCompat.wrap(drawable);
         DrawableCompat.setTintList(drawable, tintList);
         Mode tintMode = getTintMode(resId);
         if (tintMode != null) {
            DrawableCompat.setTintMode(drawable, tintMode);
         }
      } else {
         LayerDrawable ld;
         if (resId == drawable.abc_seekbar_track_material) {
            ld = (LayerDrawable)drawable;
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, attr.colorControlActivated), DEFAULT_MODE);
         } else if (resId != drawable.abc_ratingbar_material && resId != drawable.abc_ratingbar_indicator_material && resId != drawable.abc_ratingbar_small_material) {
            boolean tinted = tintDrawableUsingColorFilter(context, resId, drawable);
            if (!tinted && failIfNotKnown) {
               drawable = null;
            }
         } else {
            ld = (LayerDrawable)drawable;
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, attr.colorControlActivated), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, attr.colorControlActivated), DEFAULT_MODE);
         }
      }

      return drawable;
   }

   private Drawable loadDrawableFromDelegates(@NonNull Context context, @DrawableRes int resId) {
      if (this.mDelegates != null && !this.mDelegates.isEmpty()) {
         if (this.mKnownDrawableIdTags != null) {
            String cachedTagName = (String)this.mKnownDrawableIdTags.get(resId);
            if ("appcompat_skip_skip".equals(cachedTagName) || cachedTagName != null && this.mDelegates.get(cachedTagName) == null) {
               return null;
            }
         } else {
            this.mKnownDrawableIdTags = new SparseArrayCompat();
         }

         if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
         }

         TypedValue tv = this.mTypedValue;
         Resources res = context.getResources();
         res.getValue(resId, tv, true);
         long key = createCacheKey(tv);
         Drawable dr = this.getCachedDrawable(context, key);
         if (dr != null) {
            return dr;
         } else {
            if (tv.string != null && tv.string.toString().endsWith(".xml")) {
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

                  String tagName = parser.getName();
                  this.mKnownDrawableIdTags.append(resId, tagName);
                  AppCompatDrawableManager.InflateDelegate delegate = (AppCompatDrawableManager.InflateDelegate)this.mDelegates.get(tagName);
                  if (delegate != null) {
                     dr = delegate.createFromXmlInner(context, parser, attrs, context.getTheme());
                  }

                  if (dr != null) {
                     dr.setChangingConfigurations(tv.changingConfigurations);
                     if (this.addDrawableToCache(context, key, dr)) {
                        ;
                     }
                  }
               } catch (Exception var13) {
                  Log.e("AppCompatDrawableManager", "Exception while inflating drawable", var13);
               }
            }

            if (dr == null) {
               this.mKnownDrawableIdTags.append(resId, "appcompat_skip_skip");
            }

            return dr;
         }
      } else {
         return null;
      }
   }

   private Drawable getCachedDrawable(@NonNull Context context, long key) {
      Object var4 = this.mDrawableCacheLock;
      synchronized(this.mDrawableCacheLock) {
         LongSparseArray cache = (LongSparseArray)this.mDrawableCaches.get(context);
         if (cache == null) {
            return null;
         } else {
            WeakReference wr = (WeakReference)cache.get(key);
            if (wr != null) {
               ConstantState entry = (ConstantState)wr.get();
               if (entry != null) {
                  return entry.newDrawable(context.getResources());
               }

               cache.delete(key);
            }

            return null;
         }
      }
   }

   private boolean addDrawableToCache(@NonNull Context context, long key, @NonNull Drawable drawable) {
      ConstantState cs = drawable.getConstantState();
      if (cs != null) {
         Object var6 = this.mDrawableCacheLock;
         synchronized(this.mDrawableCacheLock) {
            LongSparseArray cache = (LongSparseArray)this.mDrawableCaches.get(context);
            if (cache == null) {
               cache = new LongSparseArray();
               this.mDrawableCaches.put(context, cache);
            }

            cache.put(key, new WeakReference(cs));
            return true;
         }
      } else {
         return false;
      }
   }

   Drawable onDrawableLoadedFromResources(@NonNull Context context, @NonNull VectorEnabledTintResources resources, @DrawableRes int resId) {
      Drawable drawable = this.loadDrawableFromDelegates(context, resId);
      if (drawable == null) {
         drawable = resources.superGetDrawable(resId);
      }

      return drawable != null ? this.tintDrawable(context, resId, false, drawable) : null;
   }

   static boolean tintDrawableUsingColorFilter(@NonNull Context context, @DrawableRes int resId, @NonNull Drawable drawable) {
      Mode tintMode = DEFAULT_MODE;
      boolean colorAttrSet = false;
      int colorAttr = 0;
      int alpha = -1;
      if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, resId)) {
         colorAttr = attr.colorControlNormal;
         colorAttrSet = true;
      } else if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, resId)) {
         colorAttr = attr.colorControlActivated;
         colorAttrSet = true;
      } else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, resId)) {
         colorAttr = 16842801;
         colorAttrSet = true;
         tintMode = Mode.MULTIPLY;
      } else if (resId == drawable.abc_list_divider_mtrl_alpha) {
         colorAttr = 16842800;
         colorAttrSet = true;
         alpha = Math.round(40.8F);
      } else if (resId == drawable.abc_dialog_material_background) {
         colorAttr = 16842801;
         colorAttrSet = true;
      }

      if (colorAttrSet) {
         if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
         }

         int color = ThemeUtils.getThemeAttrColor(context, colorAttr);
         drawable.setColorFilter(getPorterDuffColorFilter(color, tintMode));
         if (alpha != -1) {
            drawable.setAlpha(alpha);
         }

         return true;
      } else {
         return false;
      }
   }

   private void addDelegate(@NonNull String tagName, @NonNull AppCompatDrawableManager.InflateDelegate delegate) {
      if (this.mDelegates == null) {
         this.mDelegates = new ArrayMap();
      }

      this.mDelegates.put(tagName, delegate);
   }

   private void removeDelegate(@NonNull String tagName, @NonNull AppCompatDrawableManager.InflateDelegate delegate) {
      if (this.mDelegates != null && this.mDelegates.get(tagName) == delegate) {
         this.mDelegates.remove(tagName);
      }

   }

   private static boolean arrayContains(int[] array, int value) {
      int[] var2 = array;
      int var3 = array.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int id = var2[var4];
         if (id == value) {
            return true;
         }
      }

      return false;
   }

   static Mode getTintMode(int resId) {
      Mode mode = null;
      if (resId == drawable.abc_switch_thumb_material) {
         mode = Mode.MULTIPLY;
      }

      return mode;
   }

   ColorStateList getTintList(@NonNull Context context, @DrawableRes int resId) {
      ColorStateList tint = this.getTintListFromCache(context, resId);
      if (tint == null) {
         if (resId == drawable.abc_edit_text_material) {
            tint = AppCompatResources.getColorStateList(context, color.abc_tint_edittext);
         } else if (resId == drawable.abc_switch_track_mtrl_alpha) {
            tint = AppCompatResources.getColorStateList(context, color.abc_tint_switch_track);
         } else if (resId == drawable.abc_switch_thumb_material) {
            tint = this.createSwitchThumbColorStateList(context);
         } else if (resId == drawable.abc_btn_default_mtrl_shape) {
            tint = this.createDefaultButtonColorStateList(context);
         } else if (resId == drawable.abc_btn_borderless_material) {
            tint = this.createBorderlessButtonColorStateList(context);
         } else if (resId == drawable.abc_btn_colored_material) {
            tint = this.createColoredButtonColorStateList(context);
         } else if (resId != drawable.abc_spinner_mtrl_am_alpha && resId != drawable.abc_spinner_textfield_background_material) {
            if (arrayContains(TINT_COLOR_CONTROL_NORMAL, resId)) {
               tint = ThemeUtils.getThemeAttrColorStateList(context, attr.colorControlNormal);
            } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, resId)) {
               tint = AppCompatResources.getColorStateList(context, color.abc_tint_default);
            } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, resId)) {
               tint = AppCompatResources.getColorStateList(context, color.abc_tint_btn_checkable);
            } else if (resId == drawable.abc_seekbar_thumb_material) {
               tint = AppCompatResources.getColorStateList(context, color.abc_tint_seek_thumb);
            }
         } else {
            tint = AppCompatResources.getColorStateList(context, color.abc_tint_spinner);
         }

         if (tint != null) {
            this.addTintListToCache(context, resId, tint);
         }
      }

      return tint;
   }

   private ColorStateList getTintListFromCache(@NonNull Context context, @DrawableRes int resId) {
      if (this.mTintLists != null) {
         SparseArrayCompat tints = (SparseArrayCompat)this.mTintLists.get(context);
         return tints != null ? (ColorStateList)tints.get(resId) : null;
      } else {
         return null;
      }
   }

   private void addTintListToCache(@NonNull Context context, @DrawableRes int resId, @NonNull ColorStateList tintList) {
      if (this.mTintLists == null) {
         this.mTintLists = new WeakHashMap();
      }

      SparseArrayCompat themeTints = (SparseArrayCompat)this.mTintLists.get(context);
      if (themeTints == null) {
         themeTints = new SparseArrayCompat();
         this.mTintLists.put(context, themeTints);
      }

      themeTints.append(resId, tintList);
   }

   private ColorStateList createDefaultButtonColorStateList(@NonNull Context context) {
      return this.createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, attr.colorButtonNormal));
   }

   private ColorStateList createBorderlessButtonColorStateList(@NonNull Context context) {
      return this.createButtonColorStateList(context, 0);
   }

   private ColorStateList createColoredButtonColorStateList(@NonNull Context context) {
      return this.createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, attr.colorAccent));
   }

   private ColorStateList createButtonColorStateList(@NonNull Context context, @ColorInt int baseColor) {
      int[][] states = new int[4][];
      int[] colors = new int[4];
      int i = 0;
      int colorControlHighlight = ThemeUtils.getThemeAttrColor(context, attr.colorControlHighlight);
      int disabledColor = ThemeUtils.getDisabledThemeAttrColor(context, attr.colorButtonNormal);
      states[i] = ThemeUtils.DISABLED_STATE_SET;
      colors[i] = disabledColor;
      int i = i + 1;
      states[i] = ThemeUtils.PRESSED_STATE_SET;
      colors[i] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
      ++i;
      states[i] = ThemeUtils.FOCUSED_STATE_SET;
      colors[i] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
      ++i;
      states[i] = ThemeUtils.EMPTY_STATE_SET;
      colors[i] = baseColor;
      ++i;
      return new ColorStateList(states, colors);
   }

   private ColorStateList createSwitchThumbColorStateList(Context context) {
      int[][] states = new int[3][];
      int[] colors = new int[3];
      int i = 0;
      ColorStateList thumbColor = ThemeUtils.getThemeAttrColorStateList(context, attr.colorSwitchThumbNormal);
      int i;
      if (thumbColor != null && thumbColor.isStateful()) {
         states[i] = ThemeUtils.DISABLED_STATE_SET;
         colors[i] = thumbColor.getColorForState(states[i], 0);
         i = i + 1;
         states[i] = ThemeUtils.CHECKED_STATE_SET;
         colors[i] = ThemeUtils.getThemeAttrColor(context, attr.colorControlActivated);
         ++i;
         states[i] = ThemeUtils.EMPTY_STATE_SET;
         colors[i] = thumbColor.getDefaultColor();
         ++i;
      } else {
         states[i] = ThemeUtils.DISABLED_STATE_SET;
         colors[i] = ThemeUtils.getDisabledThemeAttrColor(context, attr.colorSwitchThumbNormal);
         i = i + 1;
         states[i] = ThemeUtils.CHECKED_STATE_SET;
         colors[i] = ThemeUtils.getThemeAttrColor(context, attr.colorControlActivated);
         ++i;
         states[i] = ThemeUtils.EMPTY_STATE_SET;
         colors[i] = ThemeUtils.getThemeAttrColor(context, attr.colorSwitchThumbNormal);
         ++i;
      }

      return new ColorStateList(states, colors);
   }

   static void tintDrawable(Drawable drawable, TintInfo tint, int[] state) {
      if (DrawableUtils.canSafelyMutateDrawable(drawable) && drawable.mutate() != drawable) {
         Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
      } else {
         if (!tint.mHasTintList && !tint.mHasTintMode) {
            drawable.clearColorFilter();
         } else {
            drawable.setColorFilter(createTintFilter(tint.mHasTintList ? tint.mTintList : null, tint.mHasTintMode ? tint.mTintMode : DEFAULT_MODE, state));
         }

         if (VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
         }

      }
   }

   private static PorterDuffColorFilter createTintFilter(ColorStateList tint, Mode tintMode, int[] state) {
      if (tint != null && tintMode != null) {
         int color = tint.getColorForState(state, 0);
         return getPorterDuffColorFilter(color, tintMode);
      } else {
         return null;
      }
   }

   public static PorterDuffColorFilter getPorterDuffColorFilter(int color, Mode mode) {
      PorterDuffColorFilter filter = COLOR_FILTER_CACHE.get(color, mode);
      if (filter == null) {
         filter = new PorterDuffColorFilter(color, mode);
         COLOR_FILTER_CACHE.put(color, mode, filter);
      }

      return filter;
   }

   private static void setPorterDuffColorFilter(Drawable d, int color, Mode mode) {
      if (DrawableUtils.canSafelyMutateDrawable(d)) {
         d = d.mutate();
      }

      d.setColorFilter(getPorterDuffColorFilter(color, mode == null ? DEFAULT_MODE : mode));
   }

   private void checkVectorDrawableSetup(@NonNull Context context) {
      if (!this.mHasCheckedVectorDrawableSetup) {
         this.mHasCheckedVectorDrawableSetup = true;
         Drawable d = this.getDrawable(context, drawable.abc_vector_test);
         if (d == null || !isVectorDrawable(d)) {
            this.mHasCheckedVectorDrawableSetup = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
         }
      }
   }

   private static boolean isVectorDrawable(@NonNull Drawable d) {
      return d instanceof VectorDrawableCompat || "android.graphics.drawable.VectorDrawable".equals(d.getClass().getName());
   }

   static {
      DEFAULT_MODE = Mode.SRC_IN;
      COLOR_FILTER_CACHE = new AppCompatDrawableManager.ColorFilterLruCache(6);
      COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[]{drawable.abc_textfield_search_default_mtrl_alpha, drawable.abc_textfield_default_mtrl_alpha, drawable.abc_ab_share_pack_mtrl_alpha};
      TINT_COLOR_CONTROL_NORMAL = new int[]{drawable.abc_ic_commit_search_api_mtrl_alpha, drawable.abc_seekbar_tick_mark_material, drawable.abc_ic_menu_share_mtrl_alpha, drawable.abc_ic_menu_copy_mtrl_am_alpha, drawable.abc_ic_menu_cut_mtrl_alpha, drawable.abc_ic_menu_selectall_mtrl_alpha, drawable.abc_ic_menu_paste_mtrl_am_alpha};
      COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[]{drawable.abc_textfield_activated_mtrl_alpha, drawable.abc_textfield_search_activated_mtrl_alpha, drawable.abc_cab_background_top_mtrl_alpha, drawable.abc_text_cursor_material, drawable.abc_text_select_handle_left_mtrl_dark, drawable.abc_text_select_handle_middle_mtrl_dark, drawable.abc_text_select_handle_right_mtrl_dark, drawable.abc_text_select_handle_left_mtrl_light, drawable.abc_text_select_handle_middle_mtrl_light, drawable.abc_text_select_handle_right_mtrl_light};
      COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[]{drawable.abc_popup_background_mtrl_mult, drawable.abc_cab_background_internal_bg, drawable.abc_menu_hardkey_panel_mtrl_mult};
      TINT_COLOR_CONTROL_STATE_LIST = new int[]{drawable.abc_tab_indicator_material, drawable.abc_textfield_search_material};
      TINT_CHECKABLE_BUTTON_LIST = new int[]{drawable.abc_btn_check_material, drawable.abc_btn_radio_material};
   }

   @RequiresApi(11)
   private static class AvdcInflateDelegate implements AppCompatDrawableManager.InflateDelegate {
      public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) {
         try {
            return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), parser, attrs, theme);
         } catch (Exception var6) {
            Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", var6);
            return null;
         }
      }
   }

   private static class VdcInflateDelegate implements AppCompatDrawableManager.InflateDelegate {
      public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) {
         try {
            return VectorDrawableCompat.createFromXmlInner(context.getResources(), parser, attrs, theme);
         } catch (Exception var6) {
            Log.e("VdcInflateDelegate", "Exception while inflating <vector>", var6);
            return null;
         }
      }
   }

   private static class ColorFilterLruCache extends LruCache {
      public ColorFilterLruCache(int maxSize) {
         super(maxSize);
      }

      PorterDuffColorFilter get(int color, Mode mode) {
         return (PorterDuffColorFilter)this.get(generateCacheKey(color, mode));
      }

      PorterDuffColorFilter put(int color, Mode mode, PorterDuffColorFilter filter) {
         return (PorterDuffColorFilter)this.put(generateCacheKey(color, mode), filter);
      }

      private static int generateCacheKey(int color, Mode mode) {
         int hashCode = 1;
         int hashCode = 31 * hashCode + color;
         hashCode = 31 * hashCode + mode.hashCode();
         return hashCode;
      }
   }

   private interface InflateDelegate {
      Drawable createFromXmlInner(@NonNull Context var1, @NonNull XmlPullParser var2, @NonNull AttributeSet var3, @Nullable Theme var4);
   }
}
