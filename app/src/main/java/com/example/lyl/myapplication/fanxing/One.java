package com.example.lyl.myapplication.fanxing;

/**
 * @author lyl
 * @date 2017/12/13.
 * 如果该类是抽象类，不需要必须实现父类的抽象方法
 * <p>
 * <p>
 * <p>
 * <p>
 * 泛型：
 * 简单的说，意义和作用有：
 * 类型的参数化，就是可以把类型像方法的参数那样传递。这一点意义非凡。
 * 泛型使编译器可以在编译期间对类型进行检查以提高类型安全，减少运行时由于对象类型不匹配引发的异常。
 * 泛型方法，算法的复用。蛮神奇的。
 */

public abstract class One<T> extends Father<T> {
    abstract void aVoid(User t);

}
