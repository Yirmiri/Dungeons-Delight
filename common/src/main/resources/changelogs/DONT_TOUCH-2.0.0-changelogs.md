## Dungeons and Delights Update || v2.0.0 MC-1.20.1

---

## The Final Feast
We have reached the conclusion that culminating with this update will be the last major content added to Dungeon's Delight,
I, Yirmiri, [plan to work on new projects beyond Minecraft and no longer have the time for modding](https://discord.gg/F62YKRwP4q). I hope that this 
update is a worthy send-off to this project.
This update adds a handful of things that we had left on the back burner (**including separating the mod from Farmer's Delight!**),
as well as some other bits and goodies here and there.

That being said, this is no small update at all; *we've rewritten the entire mod from the ground-up to remove reliance on
Farmer's Delight (although you can still play with it and get the same experience!), **AND to make way for a Fabric port for
both 1.20.1 & 1.21.1!***

*Take a look at all the changes below!*

---

### Important Notes
With this update the entirety of Dungeon's Delight had to be rewritten from the ground up, this means several things:
- **MAKE BACKUPS OF YOUR WORLDS**, We've done the best we can to ensure worlds from previous versions work, but there's no guarantee that things won't break (please [report any issues or breaks here](https://github.com/Yirmiri/Dungeons-Delight/issues))
- Dungeon's Delight no longer requires Farmer's Delight to be played (however Dungeon's Delight does provide innate integration to include some removed features)
- With the rewrite being based on the latest 1.21.1 version, most changes and features from 1.21.1 have been ported to 1.20.1; refer to version 1.3 - 1.5 changelogs to see the majority of changes
- There may be a bunch of minor changes not noted due to rewriting everything from the ground up causes minor changes in how things work

***Additionally, RunicLib 6.0.0+ is now required in order to play Dungeon's Delight.***

### Removals & Breaking Changes
***Most of these will have their ID migrated to the next closest thing to prevent losing progress; regardless, update at your own risk!***
- Removed the following:
  - Gritty Flesh (will convert to Rotten Flesh)
  - Brined Flesh (will convert to Rotten Flesh)
  - Slime Slab (will convert to Slime Ball)
  - Salt Soaked Stew (will convert to Foul Skewer)
- Changed the following:
  - Heap of Ancient Eggs have been merged into Embedded Eggs (when converted it will reset its age)

---

---

### Fixes
- Fixed cleavers not activating on hit methods when colliding with a block
  - Retroactively fixes Targets not activating or Chorus Fruit destroying from thrown cleavers
- Biteable foods are no longer enchantable
- Thrown cleavers now can deal the same damage multiplier that melee cleavers deal against Monster Yams
- Stained Scrap Grates no longer decreases the item being used on it in Creative Mode
- Stained Scrap Grates now play sounds when inserting enhancements
- Fixed throw range tooltip of cleavers sometimes being incorrectly ordered
- Golden Cleavers are now tagged with `#minecraft:piglin_loved`
- Fixed missing Exudation Blast particle when Rancid Reduction hits the ground
- Enemies no longer target players with Putrid Scent when they are in Creative/Spectator mode
- Skeletons' behavior no longer breaks after killing an enemy with Putrid Scent
  - This seems to have been a Vanilla bug so this may have fixed some other scenarios

### Localization
- Updated wording for several subtitles & misc strings
- Updated lang keys for most advancements
- Updated lang keys for most tooltips or descriptions
    - Updated most tooltips or descriptions
- Updated name of what originally was named the Putrid Scent effect
- Updated name of what originally was named the Rotgut effect
- Updated wording of Exudation's death message

### Technical
- Updated the item id of `dungeonsdelight:smoked_spider_meat` to `dungeonsdelight:cooked_spider_meat`
- The throwing range of cleavers are now based on a new attribute known as `dungeonsdelight:cleaver.throwing_range`
- Added `#dungeonsdelight:cleaver_mineable` block tag, which determines what cleavers can efficiently mine (replaces previously used `#farmersdelight:knife_mineable`)
- Added `#dungeonsdelight:prevents_spider_climbing` block tag, blocks in this tag prevent Spiders from climbing them
- `#dungeonsddelight:cleavers` item tag now causes cleavers to disable shields instead of CleaverItem
- Added `#dungeonsdelight:has_effect_tooltip` item tag, this gives vanilla or other modded items food effect tooltips
- Added `#dungeonsdelight:monster_effects_which_preserve_amplifier` effect tag, effects in this tag will preserve the amplifier of their normal variant when monsterizing
- Added `#dungeonsdelight:keeps_homeward` damage tag, damage types in this tag will not remove Homeward from the player when damaged
- Updated the ids of some blocks (ids will be migrated to the new id safely)
  - Updated `dungeonsdelight:poisonous_potato_crate` to `dungeonsdelight:poisonous_potato_block`
  - Updated `dungeonsdelight:rotbulb_crate` to `dungeonsdelight:rotbulb_block`
  - Updated `dungeonsdelight:rotbulb_crop` to `dungeonsdelight:rotbulb`
  - Updated `dungeonsdelight:rotbulb_plant` to `dungeonsdelight:wild_rotbulb`
- Updated the ids of some items (ids will be migrated to the new id safely)
  - Updated `dungeonsdelight:fried_ghast_calamari` to `dungeonsdelight:cooked_ghast_calamari`
  - Updated `dungeonsdelight:soaked_skewer` to `dungeonsdelight:foul_skewer`
  - Updated `dungeonsdelight:spider_salmagundi` to `dungeonsdelight:salmagundi`
  - Updated `dungeonsdelight:spider_bubble_tea` to `dungeonsdelight:bubble_eye_tea`
  - Updated `dungeonsdelight:necronog` to `dungeonsdelight:eggnog`
  - Updated `dungeonsdelight:rotbulbling` to `dungeonsdelight:rotbulb_seeds`
- Updated the ids of some effects
  - Updated `dungeonsdelight:rotgut` to `dungeonsdelight:debridement`
- Updated the ids of some damage types
  - Updated `dungeonsdelight:skull_heart_blast` to `exudation_blast`
- Updated the ids of some particles
  - Updated `dungeonsdelight:skull_heart_blast` to `exudation_blast`
- Updated the ids of some tags
  - Updated block tag `#dungeonsdelight:rotbulb_growable_on` to `#dungeonsdelight:wild_crop_growable_on`
  - Updated item tag `#dungeonsdelight:flaming_knives` to `#dungeonsdelight:flaming_cleavers`
  - Updated effect tag `#dungeonsdelight:monster_effect` to `#dungeonsdelight:monster_effects`
- Updated directories of all sounds