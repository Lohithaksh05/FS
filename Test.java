class Base {
    void show(Object o) {
        System.out.println("Base.show(Object)");
    }

    void show(String s) {
        System.out.println("Base.show(String)");
    }
}

class Sub extends Base {
    @Override
    void show(String s) {
        System.out.println("Sub.show(String)");
    }

    void show(Integer i) {
        System.out.println("Sub.show(Integer)");
    }
}

public class Test {
    public static void main(String[] args) {
        Base b = new Sub();
        Sub s = new Sub();
        b.show("hello");
        b.show(100);
        s.show(100);
    }
}