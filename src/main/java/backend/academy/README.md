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
| Количество потоков |    Время    | Ускорение |
|:------------------:|:-----------:|:---------:|
|         1          |   3300 ms   |    x1     |
|         8          |   1301 ms   |   x2.54   |
