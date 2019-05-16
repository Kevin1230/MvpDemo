package com.example.along.mvpdemo.model.event;

import java.util.List;

/**
 * 说明: EventBus消息实体类 <br/>
 * Create at 2017/12/21-下午10:48 by long
 */
public class EventMessage<T> {

  private int code;
  private String msg;
  private T obj;
  private List<T> objList;
  private Object tag;

  public EventMessage() {
  }

  public EventMessage(int code) {
    this.code = code;
  }

  public EventMessage(int code, T obj) {
    this.code = code;
    this.obj = obj;
  }

  public Object getTag() {
    return tag;
  }

  public void setTag(Object tag) {
    this.tag = tag;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getObj() {
    return obj;
  }

  public void setObj(T obj) {
    this.obj = obj;
  }

  public List<T> getObjList() {
    return objList;
  }

  public void setObjList(List<T> objList) {
    this.objList = objList;
  }
}
