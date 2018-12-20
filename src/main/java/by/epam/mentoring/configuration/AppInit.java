package by.epam.mentoring.configuration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    private String TMP_FOLDER = "D:/tmp";
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MainConfiguration.class);

     //   servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));

        dispatcher.setLoadOnStartup(1);
        //  dispatcher.setAsyncSupported(true);
        dispatcher.addMapping("/");

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);

        dispatcher.setMultipartConfig(multipartConfigElement);
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        securityFilter.addMappingForUrlPatterns(null, false, "/*");
        securityFilter.setAsyncSupported(true);
        context.setServletContext(servletContext);


    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                MainConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                MainConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return new Filter[]{characterEncodingFilter};
//    }
}