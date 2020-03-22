## collect总结
````
toList

List<T>

把流中所有元素收集到List中

示例:List<Menu> menus=Menu.getMenus.stream().collect(Collectors.toList())

toSet

Set<T>

把流中所有元素收集到Set中,删除重复项

示例:Set<Menu> menus=Menu.getMenus.stream().collect(Collectors.toSet())

toCollection

Collection<T>

把流中所有元素收集到给定的供应源创建的集合中

示例:ArrayList<Menu> menus=Menu.getMenus.stream().collect(Collectors.toCollection(ArrayList::new))

Counting

Long

计算流中元素个数

示例:Long count=Menu.getMenus.stream().collect(counting);

SummingInt

Integer

对流中元素的一个整数属性求和

示例:Integer count=Menu.getMenus.stream().collect(summingInt(Menu::getCalories))

averagingInt

Double

计算流中元素integer属性的平均值

示例:Double averaging=Menu.getMenus.stream().collect(averagingInt(Menu::getCalories))

Joining

String

连接流中每个元素的toString方法生成的字符串

示例:String name=Menu.getMenus.stream().map(Menu::getName).collect(joining(“, ”))

maxBy

Optional<T>

一个包裹了流中按照给定比较器选出的最大元素的optional
如果为空返回的是Optional.empty()

示例:Optional<Menu> fattest=Menu.getMenus.stream().collect(maxBy(Menu::getCalories))

minBy

Optional<T>

一个包裹了流中按照给定比较器选出的最大元素的optional
如果为空返回的是Optional.empty()

示例: Optional<Menu> lessest=Menu.getMenus.stream().collect(minBy(Menu::getCalories))

Reducing

归约操作产生的类型

从一个作为累加器的初始值开始,利用binaryOperator与流中的元素逐个结合,从而将流归约为单个值

示例:int count=Menu.getMenus.stream().collect(reducing(0,Menu::getCalories,Integer::sum));

collectingAndThen

转换函数返回的类型

包裹另一个转换器,对其结果应用转换函数

示例:Int count=Menu.getMenus.stream().collect(collectingAndThen(toList(),List::size))

groupingBy

Map<K,List<T>>

根据流中元素的某个值对流中的元素进行分组,并将属性值做为结果map的键

示例:Map<Type,List<Menu>> menuType=Menu.getMenus.stream().collect(groupingby(Menu::getType))

partitioningBy

Map<Boolean,List<T>>

根据流中每个元素应用谓语的结果来对项目进行分区

示例:Map<Boolean,List<Menu>> menuType=Menu.getMenus.stream().collect(partitioningBy(Menu::isType));
```