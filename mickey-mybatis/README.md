# mickey-mybatis

## 使用反射创建model对象
    //1.获取子类类型： StandardAction.class
    //this:当前运行时的实例
    Class clz = this.getClass();//this指的是当前运行的实例（子类实例）

    //2.获取类的泛型父类 : BaseAction<Standard>
    //Type: 是Java里面所有类型的父接口
    Type type = clz.getGenericSuperclass();//获取泛型父类，必须用该方法，此处的泛型父类不是指当前的类，而是具体继承的BaseAction<Standard>，当前类为BaseAction<T>泛型尚未确定
    
    //3.把Type转换为具体的类型: BaseAction<Standard>
    ParameterizedType pt = (ParameterizedType)type;//将泛型父类转换为具体的那种类型
    
    //4.从具体类型中获取泛型  : Standard.class
    //System.out.println(pt.getActualTypeArguments()[0]);
    
    Class modelClass = (Class)pt.getActualTypeArguments()[0];//获取具体泛型类Action中的泛型
    
    //5.创建泛型类的的对象
    model = (T) modelClass.getConstructor().newInstance();





