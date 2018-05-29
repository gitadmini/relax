package site.linyy.relax.sys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/** 静态资源配置.
 */
@Configuration
@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${static.source.url}")
    String staticUrl;

    @Value("${static.source.path}")
    String staticPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // url和path虽然能写多个，但是不是一一对应的。（不是内联结，而是外联结full join）
        registry.addResourceHandler(staticUrl)
            .addResourceLocations(staticPath.split(","));
    }
}
