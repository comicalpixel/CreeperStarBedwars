package cn.comicalpixel.creeperstarbedwars.Arena.Generator;

import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.Holo.Diamondholographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.Holo.Emeraldholographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Diamond;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Emerald;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;

public class StartGenerator {

    public StartGenerator() {

        Generators_Diamond.set(ConfigData.generator_game_diamond, 1);
        Generators_Emerald.set(ConfigData.generator_game_emerald, 1);
        Generators_Diamond.start();
        Generators_Emerald.start();
        new Diamondholographic();
        new Emeraldholographic();

    }

}
