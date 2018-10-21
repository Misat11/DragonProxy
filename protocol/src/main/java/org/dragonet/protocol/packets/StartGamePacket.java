package org.dragonet.protocol.packets;

import com.github.steveice10.mc.protocol.data.game.setting.Difficulty;
import org.dragonet.common.data.blocks.GlobalBlockPalette;
import org.dragonet.common.maths.BlockPosition;
import org.dragonet.common.maths.Vector3F;
import org.dragonet.common.utilities.GameRule;
import org.dragonet.protocol.PEPacket;
import org.dragonet.protocol.ProtocolInfo;

import java.util.Map;

/**
 * Created on 2017/10/21.
 */
public class StartGamePacket extends PEPacket {

    public long eid;
    public long rtid;
    public int gamemode;
    public Vector3F position;
    public float pitch;
    public float yaw;

    // world settings
    public int seed;
    public int dimension;
    public int generator = 1;
    public int worldGamemode;
    public Difficulty difficulty;
    public BlockPosition spawnPosition;
    public boolean achievementsDisabled = true;
    public int time = -1;
    public boolean eduMode = false;
    public boolean eduFeaturesEnabled = false;
    public float rainLevel;
    public float lightningLevel;
    public boolean multiplayerGame = true;
    public boolean lanBroadcast = true;
    public boolean xboxLiveBroadcast = true;
    public boolean commandsEnabled = true;
    public boolean texturePacksRequired = false;
    public Map<String, GameRule> gameRules;
    public boolean bonusChestEnabled = false;
    public boolean startWithMapEnabled = false;
    public boolean trustPlayersEnabled = false;
    public int defaultPlayerPermission = 1;
    public int gamePublishSetting = 4;
    public int serverChunkTickRadius = 4; //TODO (leave as default for now)
    public boolean hasPlatformBroadcast = false;
    public int platformBroadcastMode = 4;
    public boolean xboxLiveBroadcastIntent = true;
    public boolean hasLockedBehaviorPack = false;
    public boolean hasLockedResourcePack = false;
    public boolean isFromLockedWorldTemplate = false;
    public boolean useGamerTagsOnly = false;

    public String levelId;
    public String worldName;
    public String premiumWorldTemplateId;
    public boolean isTrial = false;
    public long currentTick;

    public int enchantmentSeed;

    public String multiplayerCorrelationId = "";

    public StartGamePacket() {

    }

    @Override
    public int pid() {
        return ProtocolInfo.START_GAME_PACKET;
    }

    @Override
    public void encodePayload() {
        putVarLong(eid);
        putUnsignedVarLong(rtid);
        putVarInt(gamemode);

        putVector3F(position);

        putLFloat(yaw);
        putLFloat(pitch);

        // Level settings
        putVarInt(seed);
        putVarInt(dimension);
        putVarInt(generator);
        putVarInt(worldGamemode);
        putVarInt(difficulty.ordinal());
        putBlockPosition(spawnPosition);
        putBoolean(achievementsDisabled);
        putVarInt(time);
        putBoolean(eduMode);
        putBoolean(eduFeaturesEnabled);
        putLFloat(rainLevel);
        putLFloat(lightningLevel);
        putBoolean(multiplayerGame);
        putBoolean(lanBroadcast);
        putBoolean(xboxLiveBroadcast);
        putBoolean(commandsEnabled);
        putBoolean(texturePacksRequired);
        putGameRules(gameRules);
        putBoolean(bonusChestEnabled);
        putBoolean(startWithMapEnabled);
        putBoolean(trustPlayersEnabled);
        putVarInt(defaultPlayerPermission);
        putVarInt(gamePublishSetting);
        putLInt(serverChunkTickRadius);
        putBoolean(hasPlatformBroadcast);
        putVarInt(platformBroadcastMode);
        putBoolean(xboxLiveBroadcastIntent);
        putBoolean(hasLockedBehaviorPack);
        putBoolean(hasLockedResourcePack);
        putBoolean(isFromLockedWorldTemplate);
        putBoolean(useGamerTagsOnly);

        putString(levelId);
        putString(worldName);
        putString(premiumWorldTemplateId);
        putBoolean(isTrial);
        putLLong(currentTick);
        putVarInt(enchantmentSeed);

        // Runtime ID table
        put(GlobalBlockPalette.getCompiledMappings());

        putString(multiplayerCorrelationId);
    }

    @Override
    public void decodePayload() {
        eid = getVarLong();
        rtid = getUnsignedVarLong();
        gamemode = getVarInt();

        position = getVector3F();

        yaw = getLFloat();
        pitch = getLFloat();

        // Level settings
        seed = getVarInt();
        dimension = getVarInt();
        generator = getVarInt();
        worldGamemode = getVarInt();
        difficulty = Difficulty.values()[getVarInt()];
        spawnPosition = getSignedBlockPosition();
        achievementsDisabled = getBoolean();
        time = getVarInt();
        eduMode = getBoolean();
        rainLevel = getLFloat();
        lightningLevel = getLFloat();
        multiplayerGame = getBoolean();
        lanBroadcast = getBoolean();
        xboxLiveBroadcast = getBoolean();
        commandsEnabled = getBoolean();
        texturePacksRequired = getBoolean();
        gameRules = getGameRules();
        bonusChestEnabled = getBoolean();
        startWithMapEnabled = getBoolean();
        trustPlayersEnabled = getBoolean();
        defaultPlayerPermission = getVarInt();
        gamePublishSetting = getVarInt();
        serverChunkTickRadius = getVarInt();
        hasPlatformBroadcast = getBoolean();
        platformBroadcastMode = getVarInt();
        xboxLiveBroadcastIntent = getBoolean();
        useGamerTagsOnly = getBoolean();

        levelId = getString();
        worldName = getString();
        premiumWorldTemplateId = getString();
        isTrial = getBoolean();
        currentTick = getLLong();

        enchantmentSeed = getVarInt();
    }
}
