package cn.chloeprime.aaa_particles_world.util;

public record BooleanField(
        Getter getter,
        Setter setter
) {
    public boolean get() {
        return getter.get();
    }

    public void set(boolean value) {
        setter.set(value);
    }

    public interface Getter {
        boolean get();
    }

    public interface Setter {
        void set(boolean value);
    }
}
