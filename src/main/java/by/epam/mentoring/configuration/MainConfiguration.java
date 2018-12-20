package by.epam.mentoring.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.MultipartConfigElement;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("by.epam.mentoring")
@Import({DbConfiguration.class})
@ImportResource("file:**/WEB-INF/appconfig-security.xml")
public class MainConfiguration implements WebMvcConfigurer {

//    @Bean
//    public ThemeSource themeSource(){
//        ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
//        themeSource.setBasenamePrefix("theme-");
//        return themeSource;
//    }
//
//    @Bean
//    public ThemeResolver themeResolver(){
//        CookieThemeResolver themeResolver =  new CookieThemeResolver();
//        themeResolver.setDefaultThemeName("regular");
//        return themeResolver;
//    }
//
//    @Bean
//    public ThemeChangeInterceptor themeChangeInterceptor(){
//        ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
//        themeChangeInterceptor.setParamName("siteTheme");
//        return themeChangeInterceptor;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/");
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("language");
        return interceptor;
    }
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver resolver = new SessionLocaleResolver();
//        resolver.setDefaultLocale(Locale.ENGLISH);
//        return resolver;
//    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setCookieName("cookie-locale-info");
        // Set default locale value.
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        // Set cookie max exist time.
        cookieLocaleResolver.setCookieMaxAge(3600);

        return cookieLocaleResolver;
    }


    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setInterceptors(localeChangeInterceptor());
        return mapping;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(4);
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:internationalmessages");
//        messageSource.addBasenames();
//        messageSource.addBasenames("classpath:internationalmessages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }

//    @Bean
//    public FreeMarkerConfigurer setupFreeMarkerConfigurer() {
//        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        configurer.setTemplateLoaderPath("/WEB-INF/view/ftl");
//        return configurer;
//    }

    @Bean
    public CommonsMultipartResolver setupMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1000000);
        return resolver;
    }

    @Bean
    public BeanNameViewResolver setupBeanNameViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(3);
        return resolver;
    }

}
