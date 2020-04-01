package sh.platform.sample;

import java.security.Principal;
import java.util.Objects;

public class CustomPrincipal implements Principal {

    private final String name;

    public CustomPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomPrincipal that = (CustomPrincipal) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CustomPrincipal{" +
                "name='" + name + '\'' +
                '}';
    }
}
