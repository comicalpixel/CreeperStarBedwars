package cn.comicalpixel.creeperstarbedwars.Arena.Stats;

public class GameStats {

    /*
        -1: Loading**
        0 : SETUP*
        1 : waiting
        2 : playing
        3 : end
     */

    private static int i = -1;

    public static void set(int i) {
        GameStats.i = i;
    }

    public static int get() {
        return i;
    }
}
