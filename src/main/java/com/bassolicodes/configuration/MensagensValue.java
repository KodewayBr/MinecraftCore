package com.bassolicodes.configuration;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;

/**
 * Created by @SrGabrieel
 */
@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("mensagens.yml")
@ConfigSection("Mensagens")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MensagensValue implements ConfigurationInjectable {

    @Getter
    private static final MensagensValue instance = new MensagensValue();

    // gamemode
    @ConfigField("Gamemode_Change") private String gamemode_change;
    @ConfigField("Gamemode_Change_Player") private String gamemode_change_player;
    @ConfigField("Gamemode_Invalid") private String gamemode_invalid;
    @ConfigField("Gamemode_Same_Player") private String gamemode_same_player;



    public static <T> T get(Function<MensagensValue, T> function) {
        return function.apply(instance);
    }

}
