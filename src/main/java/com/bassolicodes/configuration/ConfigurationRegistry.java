package com.bassolicodes.configuration;

import com.bassolicodes.MinecraftCore;
import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import lombok.Data;

/**
 * Created by @SrGabrieel
 */
@Data(staticConstructor = "of")
public class ConfigurationRegistry {

    private final MinecraftCore core;

    public void setupConfiguration() {
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(core);

        configurationInjector.saveDefaultConfiguration(core,
                "mensagens.yml"
        );

        configurationInjector.injectConfiguration(
                MensagensValue.instance()
        );

    }


}
