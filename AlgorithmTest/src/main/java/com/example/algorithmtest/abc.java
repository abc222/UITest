package com.example.algorithmtest;

public class abc {

    public static void main(String[] args) {
        SubSubClass ss = new SubSubClass(1, 2, 3);
        ss.show();
    }
}

class SuperClass {
    int a, b;
    public SuperClass(int aa, int bb) {
        a = aa;
        b = bb;
    }
}

class SubClass extends SuperClass {
    int c;
    SubClass(int aa, int bb, int cc) {
        super(aa, bb);
        c = cc;
    }
}

class SubSubClass extends SubClass {
    int d;
    SubSubClass(int aa, int bb, int cc) {
        super(aa, bb, cc);
        d = aa + bb + cc;
    }
    void show() {
        System.out.println("a=" + a + "\nb=" + b + "\nc=" + c + "\nd=" + d);
    }
}
