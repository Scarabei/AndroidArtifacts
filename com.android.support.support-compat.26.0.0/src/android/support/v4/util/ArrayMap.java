package android.support.v4.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ArrayMap extends SimpleArrayMap implements Map {
   MapCollections mCollections;

   public ArrayMap() {
   }

   public ArrayMap(int capacity) {
      super(capacity);
   }

   public ArrayMap(SimpleArrayMap map) {
      super(map);
   }

   private MapCollections getCollection() {
      if (this.mCollections == null) {
         this.mCollections = new MapCollections() {
            protected int colGetSize() {
               return ArrayMap.this.mSize;
            }

            protected Object colGetEntry(int index, int offset) {
               return ArrayMap.this.mArray[(index << 1) + offset];
            }

            protected int colIndexOfKey(Object key) {
               return ArrayMap.this.indexOfKey(key);
            }

            protected int colIndexOfValue(Object value) {
               return ArrayMap.this.indexOfValue(value);
            }

            protected Map colGetMap() {
               return ArrayMap.this;
            }

            protected void colPut(Object key, Object value) {
               ArrayMap.this.put(key, value);
            }

            protected Object colSetValue(int index, Object value) {
               return ArrayMap.this.setValueAt(index, value);
            }

            protected void colRemoveAt(int index) {
               ArrayMap.this.removeAt(index);
            }

            protected void colClear() {
               ArrayMap.this.clear();
            }
         };
      }

      return this.mCollections;
   }

   public boolean containsAll(Collection collection) {
      return MapCollections.containsAllHelper(this, collection);
   }

   public void putAll(Map map) {
      this.ensureCapacity(this.mSize + map.size());
      Iterator var2 = map.entrySet().iterator();

      while(var2.hasNext()) {
         Entry entry = (Entry)var2.next();
         this.put(entry.getKey(), entry.getValue());
      }

   }

   public boolean removeAll(Collection collection) {
      return MapCollections.removeAllHelper(this, collection);
   }

   public boolean retainAll(Collection collection) {
      return MapCollections.retainAllHelper(this, collection);
   }

   public Set entrySet() {
      return this.getCollection().getEntrySet();
   }

   public Set keySet() {
      return this.getCollection().getKeySet();
   }

   public Collection values() {
      return this.getCollection().getValues();
   }
}
