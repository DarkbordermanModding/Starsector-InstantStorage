package mod.instantstorage;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;


public class InstantStorageModPlugin extends BaseModPlugin {

    @Override
    public void onGameLoad(boolean newGame){
        if(!Global.getSector().hasScript(InstantStorageScript.class)){
            Global.getSector().addScript(new InstantStorageScript());
        }
    }
}
