package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat {
   private EdgeEffect mEdgeEffect;
   private static final EdgeEffectCompat.EdgeEffectBaseImpl IMPL;

   /** @deprecated */
   @Deprecated
   public EdgeEffectCompat(Context context) {
      this.mEdgeEffect = new EdgeEffect(context);
   }

   /** @deprecated */
   @Deprecated
   public void setSize(int width, int height) {
      this.mEdgeEffect.setSize(width, height);
   }

   /** @deprecated */
   @Deprecated
   public boolean isFinished() {
      return this.mEdgeEffect.isFinished();
   }

   /** @deprecated */
   @Deprecated
   public void finish() {
      this.mEdgeEffect.finish();
   }

   /** @deprecated */
   @Deprecated
   public boolean onPull(float deltaDistance) {
      this.mEdgeEffect.onPull(deltaDistance);
      return true;
   }

   /** @deprecated */
   @Deprecated
   public boolean onPull(float deltaDistance, float displacement) {
      IMPL.onPull(this.mEdgeEffect, deltaDistance, displacement);
      return true;
   }

   public static void onPull(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
      IMPL.onPull(edgeEffect, deltaDistance, displacement);
   }

   /** @deprecated */
   @Deprecated
   public boolean onRelease() {
      this.mEdgeEffect.onRelease();
      return this.mEdgeEffect.isFinished();
   }

   /** @deprecated */
   @Deprecated
   public boolean onAbsorb(int velocity) {
      this.mEdgeEffect.onAbsorb(velocity);
      return true;
   }

   /** @deprecated */
   @Deprecated
   public boolean draw(Canvas canvas) {
      return this.mEdgeEffect.draw(canvas);
   }

   static {
      if (VERSION.SDK_INT >= 21) {
         IMPL = new EdgeEffectCompat.EdgeEffectApi21Impl();
      } else {
         IMPL = new EdgeEffectCompat.EdgeEffectBaseImpl();
      }

   }

   @RequiresApi(21)
   static class EdgeEffectApi21Impl extends EdgeEffectCompat.EdgeEffectBaseImpl {
      public void onPull(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
         edgeEffect.onPull(deltaDistance, displacement);
      }
   }

   static class EdgeEffectBaseImpl {
      public void onPull(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
         edgeEffect.onPull(deltaDistance);
      }
   }
}
