package firemuffin303.wildfirefly.utils;

import firemuffin303.wildfirefly.WildFireFly;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = WildFireFly.MODID)
public class ModConfig implements ConfigData {
    @Comment("Fireflies will flickering ")
    public static boolean flickeringFirefly = true;

}
