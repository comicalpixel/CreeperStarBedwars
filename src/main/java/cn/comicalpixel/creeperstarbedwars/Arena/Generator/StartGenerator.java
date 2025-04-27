package cn.comicalpixel.creeperstarbedwars.Arena.Generator;

import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Diamond;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Emerald;

public class StartGenerator {

    public StartGenerator() {

        Generators_Diamond.set(1);
        Generators_Emerald.set(1);
        new Generators_Diamond();
        new Generators_Emerald();

    }

}
