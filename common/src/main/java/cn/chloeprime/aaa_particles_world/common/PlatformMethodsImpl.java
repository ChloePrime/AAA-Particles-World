package cn.chloeprime.aaa_particles_world.common;

import java.util.ServiceLoader;

class PlatformMethodsImpl {
    public static final PlatformMethods INSTANCE = ServiceLoader.load(PlatformMethods.class)
            .findFirst()
            .orElseThrow(ExceptionInInitializerError::new);
}
