package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlockGroup {
    public static final BlockGroup WORMWOOD = new BlockGroup(
            "wormwood",
            List.of(BlockTags.MINEABLE_WITH_AXE),
            List.of()
    );

    public String name;
    public final List<TagKey<Block>> commonBlockTag;
    public final List<TagKey<Item>> commonItemTag;

    private final List<Supplier<Block>> registeredBlocks = new ArrayList<>();
    private final Map<Supplier<Block>, String> blockNames = new HashMap<>();
    private final Map<Supplier<Block>, ModelMode> modelModes = new HashMap<>();

    public BlockGroup(String name, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
        this.name = name;
        this.commonBlockTag = blockTags;
        this.commonItemTag = itemTags;
    }

    public void addQuick(Supplier<Block> block, String name, ModelMode mode) {
        registeredBlocks.add(block);

        String newname = name.replace("_", " ");

        String[] subs = newname.split(" ");
        StringBuilder finalset = new StringBuilder();
        int i = 0;
        for (String s : subs)
        {
            String pre = s.substring(0, 1);
            String suf = s.substring(1);
            pre = pre.toUpperCase();
            finalset.append(pre).append(suf);

            if (i < subs.length - 1)
            {
                finalset.append(" ");
                i++;
            }
        }

        blockNames.put(block, finalset.toString());
        modelModes.put(block, mode);
    }

    public enum ModelMode {
        BLOCK,
        PILLAR,
        MULTIFACE,
        DOOR,
        TRAPDOOR,
        SLAB,
        STAIRS,
        WALL,
        FENCE,
        FENCE_GATE,
        PLATE,
        BUTTON,
        BARS,

        MANUAL
    }
}
