package net.yirmiri.dungeonsdelight.core.platform;

import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class DDServices {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        DungeonsDelight.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}