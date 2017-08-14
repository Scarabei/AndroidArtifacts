package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

class AppCompatViewInflater {
   private static final Class[] sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
   private static final int[] sOnClickAttrs = new int[]{16843375};
   private static final String[] sClassPrefixList = new String[]{"android.widget.", "android.view.", "android.webkit."};
   private static final String LOG_TAG = "AppCompatViewInflater";
   private static final Map sConstructorMap = new ArrayMap();
   private final Object[] mConstructorArgs = new Object[2];

   public final View createView(View parent, String name, @NonNull Context context, @NonNull AttributeSet attrs, boolean inheritContext, boolean readAndroidTheme, boolean readAppTheme, boolean wrapContext) {
      if (inheritContext && parent != null) {
         context = parent.getContext();
      }

      if (readAndroidTheme || readAppTheme) {
         context = themifyContext(context, attrs, readAndroidTheme, readAppTheme);
      }

      if (wrapContext) {
         context = TintContextWrapper.wrap(context);
      }

      View view = null;
      byte var12 = -1;
      switch(name.hashCode()) {
      case -1946472170:
         if (name.equals("RatingBar")) {
            var12 = 11;
         }
         break;
      case -1455429095:
         if (name.equals("CheckedTextView")) {
            var12 = 8;
         }
         break;
      case -1346021293:
         if (name.equals("MultiAutoCompleteTextView")) {
            var12 = 10;
         }
         break;
      case -938935918:
         if (name.equals("TextView")) {
            var12 = 0;
         }
         break;
      case -937446323:
         if (name.equals("ImageButton")) {
            var12 = 5;
         }
         break;
      case -658531749:
         if (name.equals("SeekBar")) {
            var12 = 12;
         }
         break;
      case -339785223:
         if (name.equals("Spinner")) {
            var12 = 4;
         }
         break;
      case 776382189:
         if (name.equals("RadioButton")) {
            var12 = 7;
         }
         break;
      case 1125864064:
         if (name.equals("ImageView")) {
            var12 = 1;
         }
         break;
      case 1413872058:
         if (name.equals("AutoCompleteTextView")) {
            var12 = 9;
         }
         break;
      case 1601505219:
         if (name.equals("CheckBox")) {
            var12 = 6;
         }
         break;
      case 1666676343:
         if (name.equals("EditText")) {
            var12 = 3;
         }
         break;
      case 2001146706:
         if (name.equals("Button")) {
            var12 = 2;
         }
      }

      switch(var12) {
      case 0:
         view = new AppCompatTextView(context, attrs);
         break;
      case 1:
         view = new AppCompatImageView(context, attrs);
         break;
      case 2:
         view = new AppCompatButton(context, attrs);
         break;
      case 3:
         view = new AppCompatEditText(context, attrs);
         break;
      case 4:
         view = new AppCompatSpinner(context, attrs);
         break;
      case 5:
         view = new AppCompatImageButton(context, attrs);
         break;
      case 6:
         view = new AppCompatCheckBox(context, attrs);
         break;
      case 7:
         view = new AppCompatRadioButton(context, attrs);
         break;
      case 8:
         view = new AppCompatCheckedTextView(context, attrs);
         break;
      case 9:
         view = new AppCompatAutoCompleteTextView(context, attrs);
         break;
      case 10:
         view = new AppCompatMultiAutoCompleteTextView(context, attrs);
         break;
      case 11:
         view = new AppCompatRatingBar(context, attrs);
         break;
      case 12:
         view = new AppCompatSeekBar(context, attrs);
      }

      if (view == null && context != context) {
         view = this.createViewFromTag(context, name, attrs);
      }

      if (view != null) {
         this.checkOnClickListener((View)view, attrs);
      }

      return (View)view;
   }

   private View createViewFromTag(Context context, String name, AttributeSet attrs) {
      if (name.equals("view")) {
         name = attrs.getAttributeValue((String)null, "class");
      }

      View var4;
      try {
         View view;
         try {
            this.mConstructorArgs[0] = context;
            this.mConstructorArgs[1] = attrs;
            if (-1 == name.indexOf(46)) {
               for(int i = 0; i < sClassPrefixList.length; ++i) {
                  view = this.createView(context, name, sClassPrefixList[i]);
                  if (view != null) {
                     View var6 = view;
                     return var6;
                  }
               }

               var4 = null;
               return var4;
            }

            var4 = this.createView(context, name, (String)null);
         } catch (Exception var10) {
            view = null;
            return view;
         }
      } finally {
         this.mConstructorArgs[0] = null;
         this.mConstructorArgs[1] = null;
      }

      return var4;
   }

   private void checkOnClickListener(View view, AttributeSet attrs) {
      Context context = view.getContext();
      if (context instanceof ContextWrapper && (VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view))) {
         TypedArray a = context.obtainStyledAttributes(attrs, sOnClickAttrs);
         String handlerName = a.getString(0);
         if (handlerName != null) {
            view.setOnClickListener(new AppCompatViewInflater.DeclaredOnClickListener(view, handlerName));
         }

         a.recycle();
      }
   }

   private View createView(Context context, String name, String prefix) throws ClassNotFoundException, InflateException {
      Constructor constructor = (Constructor)sConstructorMap.get(name);

      try {
         if (constructor == null) {
            Class clazz = context.getClassLoader().loadClass(prefix != null ? prefix + name : name).asSubclass(View.class);
            constructor = clazz.getConstructor(sConstructorSignature);
            sConstructorMap.put(name, constructor);
         }

         constructor.setAccessible(true);
         return (View)constructor.newInstance(this.mConstructorArgs);
      } catch (Exception var6) {
         return null;
      }
   }

   private static Context themifyContext(Context context, AttributeSet attrs, boolean useAndroidTheme, boolean useAppTheme) {
      TypedArray a = ((Context)context).obtainStyledAttributes(attrs, styleable.View, 0, 0);
      int themeId = 0;
      if (useAndroidTheme) {
         themeId = a.getResourceId(styleable.View_android_theme, 0);
      }

      if (useAppTheme && themeId == 0) {
         themeId = a.getResourceId(styleable.View_theme, 0);
         if (themeId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
         }
      }

      a.recycle();
      if (themeId != 0 && (!(context instanceof ContextThemeWrapper) || ((ContextThemeWrapper)context).getThemeResId() != themeId)) {
         context = new ContextThemeWrapper((Context)context, themeId);
      }

      return (Context)context;
   }

   private static class DeclaredOnClickListener implements OnClickListener {
      private final View mHostView;
      private final String mMethodName;
      private Method mResolvedMethod;
      private Context mResolvedContext;

      public DeclaredOnClickListener(@NonNull View hostView, @NonNull String methodName) {
         this.mHostView = hostView;
         this.mMethodName = methodName;
      }

      public void onClick(@NonNull View v) {
         if (this.mResolvedMethod == null) {
            this.resolveMethod(this.mHostView.getContext(), this.mMethodName);
         }

         try {
            this.mResolvedMethod.invoke(this.mResolvedContext, v);
         } catch (IllegalAccessException var3) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", var3);
         } catch (InvocationTargetException var4) {
            throw new IllegalStateException("Could not execute method for android:onClick", var4);
         }
      }

      @NonNull
      private void resolveMethod(@Nullable Context context, @NonNull String name) {
         while(context != null) {
            try {
               if (!context.isRestricted()) {
                  Method method = context.getClass().getMethod(this.mMethodName, View.class);
                  if (method != null) {
                     this.mResolvedMethod = method;
                     this.mResolvedContext = context;
                     return;
                  }
               }
            } catch (NoSuchMethodException var5) {
               ;
            }

            if (context instanceof ContextWrapper) {
               context = ((ContextWrapper)context).getBaseContext();
            } else {
               context = null;
            }
         }

         int id = this.mHostView.getId();
         String idText = id == -1 ? "" : " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'";
         throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.mHostView.getClass() + idText);
      }
   }
}
