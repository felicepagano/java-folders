package it.fpagano;


public class Main {

    public static void main(String[] args) {
	    String path = "branch/c331481/geko";
        Directory branch = Directory.ROOT.create("branch");
        Directory c331481 = branch.create("c331481");
        Directory geko = c331481.create("geko");
        Directory pippo = c331481.create("pippo");

        String[] split = path.split("/", 0);

        test(Directory.ROOT, split);

        System.out.println("Directory.ROOT = " + Directory.ROOT);

    }

    public static void test(Directory root, Object[] split) {
        Object head = head(split);
        if(head != null) {
            Directory directory = root.create((String) head);
            Object[] tail = tail(split);
            if(tail != null) {
                test(directory, tail);
            }
        }
    }

    public static Object head(Object...ts) {
        if(ts == null) {
            return null;
        }
        if(ts.length == 0) {
            return null;
        }
        return ts[0];
    }

    public static Object[] tail(Object...ts) {
        if(ts == null) {
            return null;
        }
        if(ts.length <= 1) {
            return null;
        }
        Object[] t = new Object[ts.length-1];
        System.arraycopy(ts, 1, t, 0, ts.length - 1);
        return t;
    }
}
