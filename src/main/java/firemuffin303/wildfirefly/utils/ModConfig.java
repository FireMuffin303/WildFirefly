package firemuffin303.wildfirefly.utils;

import firemuffin303.wildfirefly.WildFireFly;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = WildFireFly.MODID)
public class ModConfig implements ConfigData {
    public boolean flickeringFirefly = true;
}
