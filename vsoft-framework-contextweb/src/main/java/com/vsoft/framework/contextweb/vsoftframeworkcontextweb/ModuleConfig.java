package com.vsoft.framework.contextweb.vsoftframeworkcontextweb;


import com.vsoft.framework.context.annotation.Module;
/**
 * 模块配置
 * @author LiuPeng
 *
 */
@Module(
	name="yoreach-sys",
	caption = "系统管理",
	scanPackages={"conf", "com", "app", "job"}
)
public class ModuleConfig {
	
}
