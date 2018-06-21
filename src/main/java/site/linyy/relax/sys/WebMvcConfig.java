package site.linyy.relax.sys;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import site.linyy.relax.common.FileUtil;

/** 静态资源配置.
 */
@Configuration
@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String[] paths = FileUtil.getMenuFolder();
        for (int i = 0; i < paths.length; i++) {
            paths[i] = "file:" + paths[i];
        }

        // url和path虽然能写多个，但是不是一一对应的。（不是内联结，而是外联结full join）
        registry.addResourceHandler("/source/**").addResourceLocations(paths);
    }
}
