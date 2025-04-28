package cn.comicalpixel.creeperstarbedwars.Arena.Generator;

import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.Holo.Diamondholographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.Holo.Emeraldholographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Diamond;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Emerald;

public class StartGenerator {

    public StartGenerator() {

        Generators_Diamond.set(1);
        Generators_Emerald.set(1);
        Generators_Diamond.start();
        Generators_Emerald.start();
        new Diamondholographic();
        new Emeraldholographic();

    }

}
