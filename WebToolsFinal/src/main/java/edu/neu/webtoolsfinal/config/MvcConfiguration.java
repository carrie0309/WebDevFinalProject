package edu.neu.webtoolsfinal.config;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ImportResource("classpath:/kr/pe/kwonnam/freemarker/inheritance/freemarker-layout-directives.xml")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Resource(name="freemarkerLayoutDirectives")
    private Map freemarkerLayoutDirectives;

    /**
     * Creates {@link FreeMarkerConfigurer} that is responsible for selecting
     * global properties for templates.
     *
     * @param applicationContext
     * @return configured instance of {@link FreeMarkerConfigurer}
     * @throws IOException
     * @throws TemplateException
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(
            WebApplicationContext applicationContext) throws IOException,
            TemplateException {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        freemarker.template.Configuration configuration = configurer
                .createConfiguration();
        configuration.addAutoInclude("/macro/util.htm");
//        configuration.addAutoImport("layout", "/layout/layout.htm");
        configuration.setWhitespaceStripping(true);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setSharedVariable("layout", freemarkerLayoutDirectives);
        configuration.setServletContextForTemplateLoading(
                applicationContext.getServletContext(),
                "/WEB-INF/views/");
        configurer.setConfiguration(configuration);
        return configurer;
    }

    /**
     * Resolves templates by their names.
     *
     * @return
     */
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setSuffix(".htm");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setExposeSessionAttributes(true);
        return resolver;
    }

}
