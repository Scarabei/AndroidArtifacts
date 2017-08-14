package android.support.transition;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Matrix.ScaleToFit;

class MatrixUtils {
   static final Matrix IDENTITY_MATRIX = new Matrix() {
      void oops() {
         throw new IllegalStateException("Matrix can not be modified");
      }

      public void set(Matrix src) {
         this.oops();
      }

      public void reset() {
         this.oops();
      }

      public void setTranslate(float dx, float dy) {
         this.oops();
      }

      public void setScale(float sx, float sy, float px, float py) {
         this.oops();
      }

      public void setScale(float sx, float sy) {
         this.oops();
      }

      public void setRotate(float degrees, float px, float py) {
         this.oops();
      }

      public void setRotate(float degrees) {
         this.oops();
      }

      public void setSinCos(float sinValue, float cosValue, float px, float py) {
         this.oops();
      }

      public void setSinCos(float sinValue, float cosValue) {
         this.oops();
      }

      public void setSkew(float kx, float ky, float px, float py) {
         this.oops();
      }

      public void setSkew(float kx, float ky) {
         this.oops();
      }

      public boolean setConcat(Matrix a, Matrix b) {
         this.oops();
         return false;
      }

      public boolean preTranslate(float dx, float dy) {
         this.oops();
         return false;
      }

      public boolean preScale(float sx, float sy, float px, float py) {
         this.oops();
         return false;
      }

      public boolean preScale(float sx, float sy) {
         this.oops();
         return false;
      }

      public boolean preRotate(float degrees, float px, float py) {
         this.oops();
         return false;
      }

      public boolean preRotate(float degrees) {
         this.oops();
         return false;
      }

      public boolean preSkew(float kx, float ky, float px, float py) {
         this.oops();
         return false;
      }

      public boolean preSkew(float kx, float ky) {
         this.oops();
         return false;
      }

      public boolean preConcat(Matrix other) {
         this.oops();
         return false;
      }

      public boolean postTranslate(float dx, float dy) {
         this.oops();
         return false;
      }

      public boolean postScale(float sx, float sy, float px, float py) {
         this.oops();
         return false;
      }

      public boolean postScale(float sx, float sy) {
         this.oops();
         return false;
      }

      public boolean postRotate(float degrees, float px, float py) {
         this.oops();
         return false;
      }

      public boolean postRotate(float degrees) {
         this.oops();
         return false;
      }

      public boolean postSkew(float kx, float ky, float px, float py) {
         this.oops();
         return false;
      }

      public boolean postSkew(float kx, float ky) {
         this.oops();
         return false;
      }

      public boolean postConcat(Matrix other) {
         this.oops();
         return false;
      }

      public boolean setRectToRect(RectF src, RectF dst, ScaleToFit stf) {
         this.oops();
         return false;
      }

      public boolean setPolyToPoly(float[] src, int srcIndex, float[] dst, int dstIndex, int pointCount) {
         this.oops();
         return false;
      }

      public void setValues(float[] values) {
         this.oops();
      }
   };
}
