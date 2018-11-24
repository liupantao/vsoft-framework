package com.vsoft.framework.context;

import com.vsoft.framework.context.annotation.Module;

@Module(
        name="vsoft-config",
        caption = "系統上下文",
        scanPackages = {"config","server"}

)
public class ModuleScanConfig {
}
