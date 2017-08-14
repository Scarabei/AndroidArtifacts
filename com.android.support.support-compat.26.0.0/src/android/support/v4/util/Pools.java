package android.support.v4.util;

public final class Pools {
   public static class SynchronizedPool extends Pools.SimplePool {
      private final Object mLock = new Object();

      public SynchronizedPool(int maxPoolSize) {
         super(maxPoolSize);
      }

      public Object acquire() {
         Object var1 = this.mLock;
         synchronized(this.mLock) {
            return super.acquire();
         }
      }

      public boolean release(Object element) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            return super.release(element);
         }
      }
   }

   public static class SimplePool implements Pools.Pool {
      private final Object[] mPool;
      private int mPoolSize;

      public SimplePool(int maxPoolSize) {
         if (maxPoolSize <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
         } else {
            this.mPool = new Object[maxPoolSize];
         }
      }

      public Object acquire() {
         if (this.mPoolSize > 0) {
            int lastPooledIndex = this.mPoolSize - 1;
            Object instance = this.mPool[lastPooledIndex];
            this.mPool[lastPooledIndex] = null;
            --this.mPoolSize;
            return instance;
         } else {
            return null;
         }
      }

      public boolean release(Object instance) {
         if (this.isInPool(instance)) {
            throw new IllegalStateException("Already in the pool!");
         } else if (this.mPoolSize < this.mPool.length) {
            this.mPool[this.mPoolSize] = instance;
            ++this.mPoolSize;
            return true;
         } else {
            return false;
         }
      }

      private boolean isInPool(Object instance) {
         for(int i = 0; i < this.mPoolSize; ++i) {
            if (this.mPool[i] == instance) {
               return true;
            }
         }

         return false;
      }
   }

   public interface Pool {
      Object acquire();

      boolean release(Object var1);
   }
}
