package com.check_stand.cmd.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


  /*  @Retention(RetentionPolicy.RUNTIME):
        注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
      @Target(ElementType.TYPE):
        @Target说明了Annotation所修饰的对象范围:
        用于描述类、接口(包括注解类型) 或enum声明
*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AdminCommand {

}
