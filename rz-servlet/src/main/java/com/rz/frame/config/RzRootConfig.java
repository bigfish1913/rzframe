package com.rz.frame.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//对于非Controller层进入Bean注入
//排除
@ComponentScan(value = "com.rz.frame", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})

})
public class RzRootConfig {
}
