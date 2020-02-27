//伴生类和伴生对象
//可以访问对方的私有方法和属性
//实现类似Java的一个类中既有实例成员又有静态成员的功能
object C11Companion {
  private var lastNumber = 0;
  private def getNumber():Int = {
    lastNumber += 1
    lastNumber
  }
}

class C11Companion {
  var number = C11Companion.getNumber()
}
