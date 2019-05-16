package com.example.along.mvpdemo.base;

/**
 * 说明: Interactor层: 本模块中原子性逻辑封装，非一个系列的逻辑，这样保证其他地方可以方便的调用。
 * Interactor层中不应该出现其他模块的引用
 * Interactor层的返回。如果是同步直接返回数据, 如果是异步在interactor中定义callback。<br/>
 */
public interface IBaseInteractor {

}