package android.support.v7.view.menu;

class BaseWrapper {
   final Object mWrappedObject;

   BaseWrapper(Object object) {
      if (null == object) {
         throw new IllegalArgumentException("Wrapped Object can not be null.");
      } else {
         this.mWrappedObject = object;
      }
   }

   public Object getWrappedObject() {
      return this.mWrappedObject;
   }
}
