package mod.instantstorage;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignUIAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;

import mod.instantstorage.utilities.JSONUtils;

import org.json.JSONObject;
import org.lwjgl.input.Keyboard;


final class InstantStorageScript implements EveryFrameScript
{

    @Override
    public boolean isDone(){return false;}

    @Override
    public boolean runWhilePaused(){return true;}

    @Override
    public void advance(float amount)
    {
        JSONObject modConfig = JSONUtils.getModConfig();
        int keyboardID = JSONUtils.loadInt(modConfig, "KeyboardID");
        String marketID = JSONUtils.laodString(modConfig, "MarketID");

        // Don't do anything while in a menu/dialog
        CampaignUIAPI ui = Global.getSector().getCampaignUI();
        if (Global.getSector().isInNewGameAdvance() || ui.isShowingDialog() || ui.isShowingMenu())
        {
            return;
        }
        if (Keyboard.isKeyDown(keyboardID))
        {
            ui.showInteractionDialog(
                new RuleBasedInteractionDialogPluginImpl(),
                Global.getSector().getEntityById(marketID)
            );
        }
    }
}