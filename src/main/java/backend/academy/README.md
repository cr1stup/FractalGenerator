## Сравнение производительности рендеров
### Параметры
- __width__: 2000
- __height__: 1000
- __iteration count__: 1000000
- __transformations__: DiskTransformation
- __affine count__: 5
- __samples count__: 5
- __symmetry__: 5
- __correction__: LogGamma

### Время работы
| Режим        | Время     | Ускорение |
|--------------|-----------|-----------|
| One thread   | 3300 ms   | 1x        |
| Multi thread | 1301 ms   | 2.54x     |
