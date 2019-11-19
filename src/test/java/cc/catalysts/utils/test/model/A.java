package cc.catalysts.utils.test.model;

import java.util.Objects;

public class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public A() {
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return Objects.equals(b, a.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(b);
    }
}
