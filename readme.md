# Toolbox : Java工具类库
## 简介
toolbox是一个轻量java类库，jdk最低要求为17，封装了一些jdk没有提供的常用方法和实用类。最终目标是实现hutool和guava以及apache-commons中大部分常用的工具方法。设计理念是大部分常用的工具方法通过Toolbox对象操作，避免记各种工具类名。

示例代码:
```java
    //判断字符串为空
    Toolbox.isEmpty("");

    //打印格式化字符串
    Toolbox.println("this is {}", "toolbox");
```

## 待完成的特性
  - [ ] JSON对象的反序列化
  - [ ] TreeHepler支持对TreeNode快速构建Tree对象


## 依赖
- JDK17或更高
- 无其他依赖

## 安装

Gradle:
```gradle


implementation 'top.youlanqiang:toolbox:1.1.0'

```

Maven:
```xml
<dependency>
    <groupId>top.youlanqiang</groupId>
    <artifactId>toolbox</artifactId>
    <version>1.1.0</version>
</dependency>
```

