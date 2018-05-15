# GoogleMapsTileCutter

## Usage
``GoogleMapsTileCutter.jar {zoom} {threads} {full map} {output directory}``

Zoom and threads should be an integer above zero. Full map should be a relative or absolute path to the full map (.png only!). The output directory does not have to exist at runtime. It will be created as long as the parent directory exists.

## Dimensions vs zoom levels vs tile count
| Zoom level | Dimensions | Tiles |
| ---------- | ---------- | ----- |
| 1          | 256x256    | 5     |
| 2          | 512x512    | 21    |
| 3          | 1024x1024  | 85    |
| 4          | 2048x2048  | 341   |
| 5          | 4096x4096  | 1365  |
| 6          | 8192x8192  | 5461  |

Required dimensions for a zoom level can be calculated by the following formula: ``2^(zoom -1) * 256``

You can calculate the tile count with the following formula: ``2^zoom * 2^zoom + x + 1`` where `x` is the sum of all previous tile counts for all zoom levels above zero.
