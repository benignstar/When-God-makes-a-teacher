package ahn.mirim.firstiamnotme;

import android.media.MediaPlayer;

/**
 * Created by 안성현 on 2017-10-17.
 */

class GameState {
    public int stage;
    public Container container;
    public Next next;

    public DeterminePhysique determinePhysique;
    public DetermineHeight determineHeight;
    public DetermineGender determineGender;
    public DetermineCharacter determineCharacter;
    public DetermineFavoriteColor determineFavoriteColor;
    public ShowResult showResult;

    public MediaPlayer player;
}
