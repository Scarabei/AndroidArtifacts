package android.support.v4.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class SimpleArrayMap {
   private static final boolean DEBUG = false;
   private static final String TAG = "ArrayMap";
   private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
   private static final int BASE_SIZE = 4;
   private static final int CACHE_SIZE = 10;
   static Object[] mBaseCache;
   static int mBaseCacheSize;
   static Object[] mTwiceBaseCache;
   static int mTwiceBaseCacheSize;
   int[] mHashes;
   Object[] mArray;
   int mSize;

   private static int binarySearchHashes(int[] hashes, int N, int hash) {
      try {
         return ContainerHelpers.binarySearch(hashes, N, hash);
      } catch (ArrayIndexOutOfBoundsException var4) {
         throw new ConcurrentModificationException();
      }
   }

   int indexOf(Object key, int hash) {
      int N = this.mSize;
      if (N == 0) {
         return -1;
      } else {
         int index = binarySearchHashes(this.mHashes, N, hash);
         if (index < 0) {
            return index;
         } else if (key.equals(this.mArray[index << 1])) {
            return index;
         } else {
            int end;
            for(end = index + 1; end < N && this.mHashes[end] == hash; ++end) {
               if (key.equals(this.mArray[end << 1])) {
                  return end;
               }
            }

            for(int i = index - 1; i >= 0 && this.mHashes[i] == hash; --i) {
               if (key.equals(this.mArray[i << 1])) {
                  return i;
               }
            }

            return ~end;
         }
      }
   }

   int indexOfNull() {
      int N = this.mSize;
      if (N == 0) {
         return -1;
      } else {
         int index = binarySearchHashes(this.mHashes, N, 0);
         if (index < 0) {
            return index;
         } else if (null == this.mArray[index << 1]) {
            return index;
         } else {
            int end;
            for(end = index + 1; end < N && this.mHashes[end] == 0; ++end) {
               if (null == this.mArray[end << 1]) {
                  return end;
               }
            }

            for(int i = index - 1; i >= 0 && this.mHashes[i] == 0; --i) {
               if (null == this.mArray[i << 1]) {
                  return i;
               }
            }

            return ~end;
         }
      }
   }

   private void allocArrays(int size) {
      Class var2;
      Object[] array;
      if (size == 8) {
         var2 = ArrayMap.class;
         synchronized(ArrayMap.class) {
            if (mTwiceBaseCache != null) {
               array = mTwiceBaseCache;
               this.mArray = array;
               mTwiceBaseCache = (Object[])((Object[])array[0]);
               this.mHashes = (int[])((int[])array[1]);
               array[0] = array[1] = null;
               --mTwiceBaseCacheSize;
               return;
            }
         }
      } else if (size == 4) {
         var2 = ArrayMap.class;
         synchronized(ArrayMap.class) {
            if (mBaseCache != null) {
               array = mBaseCache;
               this.mArray = array;
               mBaseCache = (Object[])((Object[])array[0]);
               this.mHashes = (int[])((int[])array[1]);
               array[0] = array[1] = null;
               --mBaseCacheSize;
               return;
            }
         }
      }

      this.mHashes = new int[size];
      this.mArray = new Object[size << 1];
   }

   private static void freeArrays(int[] hashes, Object[] array, int size) {
      Class var3;
      int i;
      if (hashes.length == 8) {
         var3 = ArrayMap.class;
         synchronized(ArrayMap.class) {
            if (mTwiceBaseCacheSize < 10) {
               array[0] = mTwiceBaseCache;
               array[1] = hashes;

               for(i = (size << 1) - 1; i >= 2; --i) {
                  array[i] = null;
               }

               mTwiceBaseCache = array;
               ++mTwiceBaseCacheSize;
            }
         }
      } else if (hashes.length == 4) {
         var3 = ArrayMap.class;
         synchronized(ArrayMap.class) {
            if (mBaseCacheSize < 10) {
               array[0] = mBaseCache;
               array[1] = hashes;

               for(i = (size << 1) - 1; i >= 2; --i) {
                  array[i] = null;
               }

               mBaseCache = array;
               ++mBaseCacheSize;
            }
         }
      }

   }

   public SimpleArrayMap() {
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      this.mSize = 0;
   }

   public SimpleArrayMap(int capacity) {
      if (capacity == 0) {
         this.mHashes = ContainerHelpers.EMPTY_INTS;
         this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      } else {
         this.allocArrays(capacity);
      }

      this.mSize = 0;
   }

   public SimpleArrayMap(SimpleArrayMap map) {
      this();
      if (map != null) {
         this.putAll(map);
      }

   }

   public void clear() {
      if (this.mSize > 0) {
         int[] ohashes = this.mHashes;
         Object[] oarray = this.mArray;
         int osize = this.mSize;
         this.mHashes = ContainerHelpers.EMPTY_INTS;
         this.mArray = ContainerHelpers.EMPTY_OBJECTS;
         this.mSize = 0;
         freeArrays(ohashes, oarray, osize);
      }

      if (this.mSize > 0) {
         throw new ConcurrentModificationException();
      }
   }

   public void ensureCapacity(int minimumCapacity) {
      int osize = this.mSize;
      if (this.mHashes.length < minimumCapacity) {
         int[] ohashes = this.mHashes;
         Object[] oarray = this.mArray;
         this.allocArrays(minimumCapacity);
         if (this.mSize > 0) {
            System.arraycopy(ohashes, 0, this.mHashes, 0, osize);
            System.arraycopy(oarray, 0, this.mArray, 0, osize << 1);
         }

         freeArrays(ohashes, oarray, osize);
      }

      if (this.mSize != osize) {
         throw new ConcurrentModificationException();
      }
   }

   public boolean containsKey(Object key) {
      return this.indexOfKey(key) >= 0;
   }

   public int indexOfKey(Object key) {
      return key == null ? this.indexOfNull() : this.indexOf(key, key.hashCode());
   }

   int indexOfValue(Object value) {
      int N = this.mSize * 2;
      Object[] array = this.mArray;
      int i;
      if (value == null) {
         for(i = 1; i < N; i += 2) {
            if (array[i] == null) {
               return i >> 1;
            }
         }
      } else {
         for(i = 1; i < N; i += 2) {
            if (value.equals(array[i])) {
               return i >> 1;
            }
         }
      }

      return -1;
   }

   public boolean containsValue(Object value) {
      return this.indexOfValue(value) >= 0;
   }

   public Object get(Object key) {
      int index = this.indexOfKey(key);
      return index >= 0 ? this.mArray[(index << 1) + 1] : null;
   }

   public Object keyAt(int index) {
      return this.mArray[index << 1];
   }

   public Object valueAt(int index) {
      return this.mArray[(index << 1) + 1];
   }

   public Object setValueAt(int index, Object value) {
      index = (index << 1) + 1;
      Object old = this.mArray[index];
      this.mArray[index] = value;
      return old;
   }

   public boolean isEmpty() {
      return this.mSize <= 0;
   }

   public Object put(Object key, Object value) {
      int osize = this.mSize;
      int hash;
      int index;
      if (key == null) {
         hash = 0;
         index = this.indexOfNull();
      } else {
         hash = key.hashCode();
         index = this.indexOf(key, hash);
      }

      if (index >= 0) {
         index = (index << 1) + 1;
         Object old = this.mArray[index];
         this.mArray[index] = value;
         return old;
      } else {
         index = ~index;
         if (osize >= this.mHashes.length) {
            int n = osize >= 8 ? osize + (osize >> 1) : (osize >= 4 ? 8 : 4);
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            this.allocArrays(n);
            if (osize != this.mSize) {
               throw new ConcurrentModificationException();
            }

            if (this.mHashes.length > 0) {
               System.arraycopy(ohashes, 0, this.mHashes, 0, ohashes.length);
               System.arraycopy(oarray, 0, this.mArray, 0, oarray.length);
            }

            freeArrays(ohashes, oarray, osize);
         }

         if (index < osize) {
            System.arraycopy(this.mHashes, index, this.mHashes, index + 1, osize - index);
            System.arraycopy(this.mArray, index << 1, this.mArray, index + 1 << 1, this.mSize - index << 1);
         }

         if (osize == this.mSize && index < this.mHashes.length) {
            this.mHashes[index] = hash;
            this.mArray[index << 1] = key;
            this.mArray[(index << 1) + 1] = value;
            ++this.mSize;
            return null;
         } else {
            throw new ConcurrentModificationException();
         }
      }
   }

   public void putAll(SimpleArrayMap array) {
      int N = array.mSize;
      this.ensureCapacity(this.mSize + N);
      if (this.mSize == 0) {
         if (N > 0) {
            System.arraycopy(array.mHashes, 0, this.mHashes, 0, N);
            System.arraycopy(array.mArray, 0, this.mArray, 0, N << 1);
            this.mSize = N;
         }
      } else {
         for(int i = 0; i < N; ++i) {
            this.put(array.keyAt(i), array.valueAt(i));
         }
      }

   }

   public Object remove(Object key) {
      int index = this.indexOfKey(key);
      return index >= 0 ? this.removeAt(index) : null;
   }

   public Object removeAt(int index) {
      Object old = this.mArray[(index << 1) + 1];
      int osize = this.mSize;
      int nsize;
      if (osize <= 1) {
         freeArrays(this.mHashes, this.mArray, osize);
         this.mHashes = ContainerHelpers.EMPTY_INTS;
         this.mArray = ContainerHelpers.EMPTY_OBJECTS;
         nsize = 0;
      } else {
         nsize = osize - 1;
         if (this.mHashes.length > 8 && this.mSize < this.mHashes.length / 3) {
            int n = osize > 8 ? osize + (osize >> 1) : 8;
            int[] ohashes = this.mHashes;
            Object[] oarray = this.mArray;
            this.allocArrays(n);
            if (osize != this.mSize) {
               throw new ConcurrentModificationException();
            }

            if (index > 0) {
               System.arraycopy(ohashes, 0, this.mHashes, 0, index);
               System.arraycopy(oarray, 0, this.mArray, 0, index << 1);
            }

            if (index < nsize) {
               System.arraycopy(ohashes, index + 1, this.mHashes, index, nsize - index);
               System.arraycopy(oarray, index + 1 << 1, this.mArray, index << 1, nsize - index << 1);
            }
         } else {
            if (index < nsize) {
               System.arraycopy(this.mHashes, index + 1, this.mHashes, index, nsize - index);
               System.arraycopy(this.mArray, index + 1 << 1, this.mArray, index << 1, nsize - index << 1);
            }

            this.mArray[nsize << 1] = null;
            this.mArray[(nsize << 1) + 1] = null;
         }
      }

      if (osize != this.mSize) {
         throw new ConcurrentModificationException();
      } else {
         this.mSize = nsize;
         return old;
      }
   }

   public int size() {
      return this.mSize;
   }

   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else {
         int i;
         Object key;
         Object mine;
         Object theirs;
         if (object instanceof SimpleArrayMap) {
            SimpleArrayMap map = (SimpleArrayMap)object;
            if (this.size() != map.size()) {
               return false;
            } else {
               try {
                  for(i = 0; i < this.mSize; ++i) {
                     key = this.keyAt(i);
                     mine = this.valueAt(i);
                     theirs = map.get(key);
                     if (mine == null) {
                        if (theirs != null || !map.containsKey(key)) {
                           return false;
                        }
                     } else if (!mine.equals(theirs)) {
                        return false;
                     }
                  }

                  return true;
               } catch (NullPointerException var7) {
                  return false;
               } catch (ClassCastException var8) {
                  return false;
               }
            }
         } else if (!(object instanceof Map)) {
            return false;
         } else {
            Map map = (Map)object;
            if (this.size() != map.size()) {
               return false;
            } else {
               try {
                  for(i = 0; i < this.mSize; ++i) {
                     key = this.keyAt(i);
                     mine = this.valueAt(i);
                     theirs = map.get(key);
                     if (mine == null) {
                        if (theirs != null || !map.containsKey(key)) {
                           return false;
                        }
                     } else if (!mine.equals(theirs)) {
                        return false;
                     }
                  }

                  return true;
               } catch (NullPointerException var9) {
                  return false;
               } catch (ClassCastException var10) {
                  return false;
               }
            }
         }
      }
   }

   public int hashCode() {
      int[] hashes = this.mHashes;
      Object[] array = this.mArray;
      int result = 0;
      int i = 0;
      int v = 1;

      for(int s = this.mSize; i < s; v += 2) {
         Object value = array[v];
         result += hashes[i] ^ (value == null ? 0 : value.hashCode());
         ++i;
      }

      return result;
   }

   public String toString() {
      if (this.isEmpty()) {
         return "{}";
      } else {
         StringBuilder buffer = new StringBuilder(this.mSize * 28);
         buffer.append('{');

         for(int i = 0; i < this.mSize; ++i) {
            if (i > 0) {
               buffer.append(", ");
            }

            Object key = this.keyAt(i);
            if (key != this) {
               buffer.append(key);
            } else {
               buffer.append("(this Map)");
            }

            buffer.append('=');
            Object value = this.valueAt(i);
            if (value != this) {
               buffer.append(value);
            } else {
               buffer.append("(this Map)");
            }
         }

         buffer.append('}');
         return buffer.toString();
      }
   }
}
