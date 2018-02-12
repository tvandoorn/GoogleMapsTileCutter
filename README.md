# GoogleMapsTileCutter

## Usage
``GoogleMapsTileCutter.jar {zoom} {full map} {output directory}``

## Dimensions vs zoom levels
| Zoom level | Dimensions |
| ---------- | ---------- |
| 1          | 256x256    |
| 2          | 512x512    |
| 3          | 1024x1024  |
| 4          | 2048x2048  |
| 5          | 4096x4096  |
| 6          | 8192x8192  |

Required dimensions for a zoom level can be calculated by the following formula: ``2^(zoom -1) * 256``
