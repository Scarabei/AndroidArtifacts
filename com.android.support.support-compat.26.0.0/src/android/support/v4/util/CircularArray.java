package android.support.v4.util;

public final class CircularArray {
   private Object[] mElements;
   private int mHead;
   private int mTail;
   private int mCapacityBitmask;

   private void doubleCapacity() {
      int n = this.mElements.length;
      int r = n - this.mHead;
      int newCapacity = n << 1;
      if (newCapacity < 0) {
         throw new RuntimeException("Max array capacity exceeded");
      } else {
         Object[] a = new Object[newCapacity];
         System.arraycopy(this.mElements, this.mHead, a, 0, r);
         System.arraycopy(this.mElements, 0, a, r, this.mHead);
         this.mElements = (Object[])a;
         this.mHead = 0;
         this.mTail = n;
         this.mCapacityBitmask = newCapacity - 1;
      }
   }

   public CircularArray() {
      this(8);
   }

   public CircularArray(int minCapacity) {
      if (minCapacity < 1) {
         throw new IllegalArgumentException("capacity must be >= 1");
      } else if (minCapacity > 1073741824) {
         throw new IllegalArgumentException("capacity must be <= 2^30");
      } else {
         int arrayCapacity;
         if (Integer.bitCount(minCapacity) != 1) {
            arrayCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
         } else {
            arrayCapacity = minCapacity;
         }

         this.mCapacityBitmask = arrayCapacity - 1;
         this.mElements = (Object[])(new Object[arrayCapacity]);
      }
   }

   public void addFirst(Object e) {
      this.mHead = this.mHead - 1 & this.mCapacityBitmask;
      this.mElements[this.mHead] = e;
      if (this.mHead == this.mTail) {
         this.doubleCapacity();
      }

   }

   public void addLast(Object e) {
      this.mElements[this.mTail] = e;
      this.mTail = this.mTail + 1 & this.mCapacityBitmask;
      if (this.mTail == this.mHead) {
         this.doubleCapacity();
      }

   }

   public Object popFirst() {
      if (this.mHead == this.mTail) {
         throw new ArrayIndexOutOfBoundsException();
      } else {
         Object result = this.mElements[this.mHead];
         this.mElements[this.mHead] = null;
         this.mHead = this.mHead + 1 & this.mCapacityBitmask;
         return result;
      }
   }

   public Object popLast() {
      if (this.mHead == this.mTail) {
         throw new ArrayIndexOutOfBoundsException();
      } else {
         int t = this.mTail - 1 & this.mCapacityBitmask;
         Object result = this.mElements[t];
         this.mElements[t] = null;
         this.mTail = t;
         return result;
      }
   }

   public void clear() {
      this.removeFromStart(this.size());
   }

   public void removeFromStart(int numOfElements) {
      if (numOfElements > 0) {
         if (numOfElements > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
         } else {
            int end = this.mElements.length;
            if (numOfElements < end - this.mHead) {
               end = this.mHead + numOfElements;
            }

            int removed;
            for(removed = this.mHead; removed < end; ++removed) {
               this.mElements[removed] = null;
            }

            removed = end - this.mHead;
            numOfElements -= removed;
            this.mHead = this.mHead + removed & this.mCapacityBitmask;
            if (numOfElements > 0) {
               for(int i = 0; i < numOfElements; ++i) {
                  this.mElements[i] = null;
               }

               this.mHead = numOfElements;
            }

         }
      }
   }

   public void removeFromEnd(int numOfElements) {
      if (numOfElements > 0) {
         if (numOfElements > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
         } else {
            int start = 0;
            if (numOfElements < this.mTail) {
               start = this.mTail - numOfElements;
            }

            int removed;
            for(removed = start; removed < this.mTail; ++removed) {
               this.mElements[removed] = null;
            }

            removed = this.mTail - start;
            numOfElements -= removed;
            this.mTail -= removed;
            if (numOfElements > 0) {
               this.mTail = this.mElements.length;
               int newTail = this.mTail - numOfElements;

               for(int i = newTail; i < this.mTail; ++i) {
                  this.mElements[i] = null;
               }

               this.mTail = newTail;
            }

         }
      }
   }

   public Object getFirst() {
      if (this.mHead == this.mTail) {
         throw new ArrayIndexOutOfBoundsException();
      } else {
         return this.mElements[this.mHead];
      }
   }

   public Object getLast() {
      if (this.mHead == this.mTail) {
         throw new ArrayIndexOutOfBoundsException();
      } else {
         return this.mElements[this.mTail - 1 & this.mCapacityBitmask];
      }
   }

   public Object get(int n) {
      if (n >= 0 && n < this.size()) {
         return this.mElements[this.mHead + n & this.mCapacityBitmask];
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public int size() {
      return this.mTail - this.mHead & this.mCapacityBitmask;
   }

   public boolean isEmpty() {
      return this.mHead == this.mTail;
   }
}
