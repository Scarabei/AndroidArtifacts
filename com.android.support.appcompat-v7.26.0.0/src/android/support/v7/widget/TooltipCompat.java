package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public class TooltipCompat {
   private static final TooltipCompat.ViewCompatImpl IMPL;

   public static void setTooltipText(@NonNull View view, @Nullable CharSequence tooltipText) {
      IMPL.setTooltipText(view, tooltipText);
   }

   static {
      if (VERSION.SDK_INT >= 26) {
         IMPL = new TooltipCompat.Api26ViewCompatImpl();
      } else {
         IMPL = new TooltipCompat.BaseViewCompatImpl();
      }

   }

   @TargetApi(26)
   private static class Api26ViewCompatImpl implements TooltipCompat.ViewCompatImpl {
      private Api26ViewCompatImpl() {
      }

      public void setTooltipText(@NonNull View view, @Nullable CharSequence tooltipText) {
         view.setTooltipText(tooltipText);
      }

      // $FF: synthetic method
      Api26ViewCompatImpl(Object x0) {
         this();
      }
   }

   private static class BaseViewCompatImpl implements TooltipCompat.ViewCompatImpl {
      private BaseViewCompatImpl() {
      }

      public void setTooltipText(@NonNull View view, @Nullable CharSequence tooltipText) {
         TooltipCompatHandler.setTooltipText(view, tooltipText);
      }

      // $FF: synthetic method
      BaseViewCompatImpl(Object x0) {
         this();
      }
   }

   private interface ViewCompatImpl {
      void setTooltipText(@NonNull View var1, @Nullable CharSequence var2);
   }
}
