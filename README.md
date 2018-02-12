# GoogleMapsTileCutter

## Usage
``GoogleMapsTileCutter.jar {zoom} {full map} {output directory}``

## Dimensions vs zoom levels
| Zoom level | Dimensions | Tiles |
| ---------- | ---------- | ----- |
| 1          | 256x256    | 5     |
| 2          | 512x512    | 21    |
| 3          | 1024x1024  | 85    |
| 4          | 2048x2048  | 341   |
| 5          | 4096x4096  | 1365  |
| 6          | 8192x8192  | 5461  |

Required dimensions for a zoom level can be calculated by the following formula: ``2^(zoom -1) * 256``
