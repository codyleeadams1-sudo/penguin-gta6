# Penguin Mod (Fabric, Minecraft 1.21.11)

Adds a **Penguin** mob that spawns naturally in snowy and icy biomes, plus a
**Penguin Spawn Egg** (your custom image) in the *Spawn Eggs* creative tab.

* Minecraft **1.21.11**, Fabric Loader **0.18.1+**, **Fabric API** (0.141.x for 1.21.11), Java **21**
* Spawns in: snowy plains, ice spikes, snowy taiga, snowy slopes, snowy beach, frozen river,
  frozen/jagged peaks, grove, frozen oceans (+ any biome tagged `#c:is_snowy` / `#c:is_icy`)
* Behaviour: wanders, waddles, flaps its flippers, follows/is tempted by raw cod & salmon, breeds with them
* Model = the Blockbench penguin (`blockbench/penguin.bbmodel`)

## Build the jar with GitHub (nothing to install on your PC)

1. Create a new empty repository on GitHub.
2. Upload this project (unzip first). Easiest with git:
   ```
   git init
   git add .
   git commit -m "Penguin mod"
   git branch -M main
   git remote add origin https://github.com/YOUR_NAME/YOUR_REPO.git
   git push -u origin main
   ```
   (Make sure the hidden `.github/workflows/build.yml` file is included.)
3. Open the repo's **Actions** tab. The *Build mod jar* workflow starts by itself
   (about 5-10 minutes the first time, it downloads and remaps Minecraft).
4. When it turns green, open the run and download the **penguinmod-fabric-1.21.11-jar** artifact.
   Unzip it - `penguinmod-1.0.0.jar` is the mod.
   *(Or push a tag like `v1.0.0` and the jar is attached to a GitHub Release.)*

## Install

1. Install Fabric for Minecraft 1.21.11 (https://fabricmc.net/use/installer/).
2. Put **Fabric API** and `penguinmod-1.0.0.jar` in your `mods` folder.
3. In creative: Spawn Eggs tab -> Penguin Spawn Egg. Or `/summon penguinmod:penguin`.

## Tweaking

| What | Where |
|------|-------|
| Spawn biomes | `src/main/resources/data/penguinmod/tags/worldgen/biome/spawns_penguins.json` |
| Spawn weight / group size | `PenguinMod.onInitialize()` (`BiomeModifications.addSpawn`) |
| Ground the penguin may spawn on | `PenguinEntity.checkSpawnRules()` |
| Health / speed | `PenguinEntity.createAttributes()` |
| Size in-game | `MODEL_SCALE` in `PenguinRenderer` |
| Textures | `assets/penguinmod/textures/entity/penguin.png`, `.../item/penguin_spawn_egg.png` |

Like other passive animals, penguins mostly appear when new snowy chunks are generated,
so explore fresh snowy terrain to find them.
