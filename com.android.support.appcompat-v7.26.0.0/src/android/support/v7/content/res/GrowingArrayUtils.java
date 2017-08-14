package android.support.v7.content.res;

import java.lang.reflect.Array;

final class GrowingArrayUtils {
   public static Object[] append(Object[] array, int currentSize, Object element) {
      assert currentSize <= array.length;

      if (currentSize + 1 > array.length) {
         Object[] newArray = (Object[])((Object[])Array.newInstance(array.getClass().getComponentType(), growSize(currentSize)));
         System.arraycopy(array, 0, newArray, 0, currentSize);
         array = newArray;
      }

      array[currentSize] = element;
      return array;
   }

   public static int[] append(int[] array, int currentSize, int element) {
      assert currentSize <= array.length;

      if (currentSize + 1 > array.length) {
         int[] newArray = new int[growSize(currentSize)];
         System.arraycopy(array, 0, newArray, 0, currentSize);
         array = newArray;
      }

      array[currentSize] = element;
      return array;
   }

   public static long[] append(long[] array, int currentSize, long element) {
      assert currentSize <= array.length;

      if (currentSize + 1 > array.length) {
         long[] newArray = new long[growSize(currentSize)];
         System.arraycopy(array, 0, newArray, 0, currentSize);
         array = newArray;
      }

      array[currentSize] = element;
      return array;
   }

   public static boolean[] append(boolean[] array, int currentSize, boolean element) {
      assert currentSize <= array.length;

      if (currentSize + 1 > array.length) {
         boolean[] newArray = new boolean[growSize(currentSize)];
         System.arraycopy(array, 0, newArray, 0, currentSize);
         array = newArray;
      }

      array[currentSize] = element;
      return array;
   }

   public static Object[] insert(Object[] array, int currentSize, int index, Object element) {
      assert currentSize <= array.length;

      if (currentSize + 1 <= array.length) {
         System.arraycopy(array, index, array, index + 1, currentSize - index);
         array[index] = element;
         return array;
      } else {
         Object[] newArray = (Object[])((Object[])Array.newInstance(array.getClass().getComponentType(), growSize(currentSize)));
         System.arraycopy(array, 0, newArray, 0, index);
         newArray[index] = element;
         System.arraycopy(array, index, newArray, index + 1, array.length - index);
         return newArray;
      }
   }

   public static int[] insert(int[] array, int currentSize, int index, int element) {
      assert currentSize <= array.length;

      if (currentSize + 1 <= array.length) {
         System.arraycopy(array, index, array, index + 1, currentSize - index);
         array[index] = element;
         return array;
      } else {
         int[] newArray = new int[growSize(currentSize)];
         System.arraycopy(array, 0, newArray, 0, index);
         newArray[index] = element;
         System.arraycopy(array, index, newArray, index + 1, array.length - index);
         return newArray;
      }
   }

   public static long[] insert(long[] array, int currentSize, int index, long element) {
      assert currentSize <= array.length;

      if (currentSize + 1 <= array.length) {
         System.arraycopy(array, index, array, index + 1, currentSize - index);
         array[index] = element;
         return array;
      } else {
         long[] newArray = new long[growSize(currentSize)];
         System.arraycopy(array, 0, newArray, 0, index);
         newArray[index] = element;
         System.arraycopy(array, index, newArray, index + 1, array.length - index);
         return newArray;
      }
   }

   public static boolean[] insert(boolean[] array, int currentSize, int index, boolean element) {
      assert currentSize <= array.length;

      if (currentSize + 1 <= array.length) {
         System.arraycopy(array, index, array, index + 1, currentSize - index);
         array[index] = element;
         return array;
      } else {
         boolean[] newArray = new boolean[growSize(currentSize)];
         System.arraycopy(array, 0, newArray, 0, index);
         newArray[index] = element;
         System.arraycopy(array, index, newArray, index + 1, array.length - index);
         return newArray;
      }
   }

   public static int growSize(int currentSize) {
      return currentSize <= 4 ? 8 : currentSize * 2;
   }
}
