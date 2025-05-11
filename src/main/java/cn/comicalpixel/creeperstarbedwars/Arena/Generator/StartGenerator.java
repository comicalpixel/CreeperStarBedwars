package cn.comicalpixel.creeperstarbedwars.Arena.Generator;

import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.Holo.Diamondholographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.Holo.Emeraldholographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Diamond;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Emerald;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Team.Holo.TeamGenerators_holographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Team.Manager.*;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;

public class StartGenerator {

    public StartGenerator() {

        Generators_Diamond.set(ConfigData.generator_game_diamond, 1);
        Generators_Emerald.set(ConfigData.generator_game_emerald, 1);
        Generators_Diamond.start();
        Generators_Emerald.start();
        new Diamondholographic();
        new Emeraldholographic();

        if (TeamManager.teams.contains("RED")) {
            TeamGenerator_RED.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }
        if (TeamManager.teams.contains("BLUE")) {
            TeamGenerator_BLUE.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }
        if (TeamManager.teams.contains("GREEN")) {
            TeamGenerator_GREEN.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }
        if (TeamManager.teams.contains("YELLOW")) {
            TeamGenerator_YELLOW.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }
        if (TeamManager.teams.contains("AQUA")) {
            TeamGenerator_AQUA.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }
        if (TeamManager.teams.contains("WHITE")) {
            TeamGenerator_WHITE.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }
        if (TeamManager.teams.contains("PINK")) {
            TeamGenerator_PINK.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }
        if (TeamManager.teams.contains("GRAY")) {
            TeamGenerator_GRAY.set(ConfigData.generator_team_l0_iron, ConfigData.generator_team_l0_gold, ConfigData.generator_team_l0_emerald);
        }

        new TeamGenerators_holographic();


    }

}
