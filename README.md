# Ever Winter

![Logo](/images/logo.png)

## Index

- [About](#about);
- [Commands](#commands);
- [Config](#config);
- [Download](#download);
- [Installation](#installation).

## About

This plugins temporarily changes world biomes to corresponding winter-biomes
when _ever-winter_ mode is enabled. When this mode is turned off biomes are
recovered. This mode can be enabled via command.

Biomes are changed as listed below:

+--------------------------+-------------------+
| Before                   | After             |
+--------------------------+-------------------+
| BAMBOO_JUNGLE            | SNOWY_TAIGA       |
| BEACH                    | SNOWY_BEACH       |
| BIRCH_FOREST             | SNOWY_TAIGA       |
| DARK_FOREST              | SNOWY_TAIGA       |
| DESERT                   | SNOWY_PLAINS      |
| DEEP_COLD_OCEAN          | DEEP_FROZEN_OCEAN |
| DEEP_LUKEWARM_OCEAN      | DEEP_FROZEN_OCEAN |
| DEEP_OCEAN               | DEEP_FROZEN_OCEAN |
| ERODED_BADLANDS          | SNOWY_PLAINS      |
| FLOWER_FOREST            | SNOWY_TAIGA       |
| FOREST                   | SNOWY_TAIGA       |
| JAGGED_PEAKS             | FROZEN_PEAKS      |
| JUNGLE                   | SNOWY_TAIGA       |
| MANGROVE_SWAMP           | SNOWY_TAIGA       |
| COLD_OCEAN               | FROZEN_OCEAN      |
| MEADOW                   | SNOWY_PLAINS      |
| OCEAN                    | FROZEN_OCEAN      |
| OLD_GROWTH_BIRCH_FOREST  | SNOWY_TAIGA       |
| OLD_GROWTH_PINE_TAIGA    | SNOWY_TAIGA       |
| OLD_GROWTH_SPRUCE_TAIGA  | SNOWY_TAIGA       |
| PLAINS                   | SNOWY_PLAINS      |
| SAVANNA                  | SNOWY_PLAINS      |
| SAVANNA_PLATEAU          | SNOWY_PLAINS      |
| SPARSE_JUNGLE            | SNOWY_TAIGA       |
| STONY_PEAKS              | FROZEN_PEAKS      |
| STONY_SHORE              | SNOWY_BEACH       |
| SUNFLOWER_PLAINS         | SNOWY_PLAINS      |
| SWAMP                    | SNOWY_TAIGA       |
| TAIGA                    | SNOWY_TAIGA       |
| WARM_OCEAN               | FROZEN_OCEAN      |
| WINDSWEPT_FOREST         | SNOWY_TAIGA       |
| WINDSWEPT_GRAVELLY_HILLS | SNOWY_PLAINS      |
| WINDSWEPT_HILLS          | SNOWY_PLAINS      |
| WINDSWEPT_SAVANNA        | SNOWY_PLAINS      |
| WOODED_BADLANDS          | SNOWY_PLAINS      |
| RIVER                    | FROZEN_RIVER      |
+--------------------------+-------------------+

## Commands

The plugin provides only one command `/everWinter` (or it's alias `/ew`).
Being called without arguments it shows current status of _ever-winter_ mode.
You can pass `enable` or `disable` as second argument to turn plugin on or off
respectively.

## Config

Configuration file contains only one entry - `enabled`. This entry controlles
the _ever-winter_ mode status.

## Download

Go to [releases](https://github.com/Maksim2498/mc-ever-winter/releases)
section and download desired one.

## Installation

Simply put downloaded .jar file in the plugins folder.
