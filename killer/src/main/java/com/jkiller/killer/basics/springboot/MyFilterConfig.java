package com.jkiller.killer.basics.springboot;

//@Configuration
//public class MyFilterConfig {
//
//    private final MyFilter myFilter;
//    private final MyFilter2 myFilter2;
//
//    public MyFilterConfig(MyFilter myFilter, MyFilter2 myFilter2) {
//        this.myFilter = myFilter;
//        this.myFilter2 = myFilter2;
//    }
//
//    @Bean
//    public FilterRegistrationBean<MyFilter> setUpMyFilter() {
//        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setOrder(2);
//        filterRegistrationBean.setFilter(myFilter);
//        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean<MyFilter2> setUpMyFilter2() {
//        FilterRegistrationBean<MyFilter2> filter2RegistrationBean = new FilterRegistrationBean<>();
//        filter2RegistrationBean.setOrder(1);
//        filter2RegistrationBean.setFilter(myFilter2);
//        filter2RegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));
//        return filter2RegistrationBean;
//    }
//}
