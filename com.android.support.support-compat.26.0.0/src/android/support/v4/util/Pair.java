package android.support.v4.util;

public class Pair {
   public final Object first;
   public final Object second;

   public Pair(Object first, Object second) {
      this.first = first;
      this.second = second;
   }

   public boolean equals(Object o) {
      if (!(o instanceof Pair)) {
         return false;
      } else {
         Pair p = (Pair)o;
         return objectsEqual(p.first, this.first) && objectsEqual(p.second, this.second);
      }
   }

   private static boolean objectsEqual(Object a, Object b) {
      return a == b || a != null && a.equals(b);
   }

   public int hashCode() {
      return (this.first == null ? 0 : this.first.hashCode()) ^ (this.second == null ? 0 : this.second.hashCode());
   }

   public String toString() {
      return "Pair{" + String.valueOf(this.first) + " " + this.second + "}";
   }

   public static Pair create(Object a, Object b) {
      return new Pair(a, b);
   }
}
