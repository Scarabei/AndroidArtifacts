package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
   private static final String TAG = "LayoutInflaterCompatHC";
   private static Field sLayoutInflaterFactory2Field;
   private static boolean sCheckedField;
   static final LayoutInflaterCompat.LayoutInflaterCompatBaseImpl IMPL;

   static void forceSetFactory2(LayoutInflater inflater, Factory2 factory) {
      if (!sCheckedField) {
         try {
            sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
            sLayoutInflaterFactory2Field.setAccessible(true);
         } catch (NoSuchFieldException var4) {
            Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", var4);
         }

         sCheckedField = true;
      }

      if (sLayoutInflaterFactory2Field != null) {
         try {
            sLayoutInflaterFactory2Field.set(inflater, factory);
         } catch (IllegalAccessException var3) {
            Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + inflater + "; inflation may have unexpected results.", var3);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   public static void setFactory(@NonNull LayoutInflater inflater, @NonNull LayoutInflaterFactory factory) {
      IMPL.setFactory(inflater, factory);
   }

   public static void setFactory2(@NonNull LayoutInflater inflater, @NonNull Factory2 factory) {
      IMPL.setFactory2(inflater, factory);
   }

   /** @deprecated */
   @Deprecated
   public static LayoutInflaterFactory getFactory(LayoutInflater inflater) {
      return IMPL.getFactory(inflater);
   }

   static {
      if (VERSION.SDK_INT >= 21) {
         IMPL = new LayoutInflaterCompat.LayoutInflaterCompatApi21Impl();
      } else {
         IMPL = new LayoutInflaterCompat.LayoutInflaterCompatBaseImpl();
      }

   }

   @RequiresApi(21)
   static class LayoutInflaterCompatApi21Impl extends LayoutInflaterCompat.LayoutInflaterCompatBaseImpl {
      public void setFactory(LayoutInflater inflater, LayoutInflaterFactory factory) {
         inflater.setFactory2(factory != null ? new LayoutInflaterCompat.Factory2Wrapper(factory) : null);
      }

      public void setFactory2(LayoutInflater inflater, Factory2 factory) {
         inflater.setFactory2(factory);
      }
   }

   static class LayoutInflaterCompatBaseImpl {
      public void setFactory(LayoutInflater inflater, LayoutInflaterFactory factory) {
         Factory2 factory2 = factory != null ? new LayoutInflaterCompat.Factory2Wrapper(factory) : null;
         this.setFactory2(inflater, factory2);
      }

      public void setFactory2(LayoutInflater inflater, Factory2 factory) {
         inflater.setFactory2(factory);
         Factory f = inflater.getFactory();
         if (f instanceof Factory2) {
            LayoutInflaterCompat.forceSetFactory2(inflater, (Factory2)f);
         } else {
            LayoutInflaterCompat.forceSetFactory2(inflater, factory);
         }

      }

      public LayoutInflaterFactory getFactory(LayoutInflater inflater) {
         Factory factory = inflater.getFactory();
         return factory instanceof LayoutInflaterCompat.Factory2Wrapper ? ((LayoutInflaterCompat.Factory2Wrapper)factory).mDelegateFactory : null;
      }
   }

   static class Factory2Wrapper implements Factory2 {
      final LayoutInflaterFactory mDelegateFactory;

      Factory2Wrapper(LayoutInflaterFactory delegateFactory) {
         this.mDelegateFactory = delegateFactory;
      }

      public View onCreateView(String name, Context context, AttributeSet attrs) {
         return this.mDelegateFactory.onCreateView((View)null, name, context, attrs);
      }

      public View onCreateView(View parent, String name, Context context, AttributeSet attributeSet) {
         return this.mDelegateFactory.onCreateView(parent, name, context, attributeSet);
      }

      public String toString() {
         return this.getClass().getName() + "{" + this.mDelegateFactory + "}";
      }
   }
}
