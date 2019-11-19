package cc.catalysts.utils.test.model;

import java.util.List;
import java.util.Objects;

public class C {
    private String value;
    private List<String> multiValue;

    public C(String value, List<String> multiValue) {
        this.value = value;
        this.multiValue = multiValue;
    }

    public C() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getMultiValue() {
        return multiValue;
    }

    public void setMultiValue(List<String> multiValue) {
        this.multiValue = multiValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        C c = (C) o;
        return Objects.equals(value, c.value) &&
                Objects.equals(multiValue, c.multiValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, multiValue);
    }
}
