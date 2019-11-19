package cc.catalysts.utils.test.model;

import java.util.Objects;

public class B {
    private C c;

    public B(C c) {
        this.c = c;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public B() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B b = (B) o;
        return Objects.equals(c, b.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c);
    }
}
