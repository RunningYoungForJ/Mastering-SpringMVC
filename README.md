# Mastering-SpringMVC

> Pick up something

1. @RequestMapping

   ```java
   @Target({ElementType.METHOD, ElementType.TYPE})
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Mapping
   public @interface RequestMapping {
         String name() default "";
     
         @AliasFor("path")
         String[] value() default {};
         //The primary mapping expressed by this annotation. 
         @AliasFor("value")
         String[] path() default {};
         //The HTTP request methods to map to, narrowing the primary mapping: GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE.
         RequestMethod[] method() default {};
         //The parameters of the mapped request, narrowing the primary mapping.
         String[] params() default {};
         //The headers of the mapped request, narrowing the primary mapping.
         String[] headers() default {};
         //The consumable media types of the mapped request, narrowing the primary mapping.
         String[] consumes() default {};
         //The producible media types of the mapped request, narrowing the primary mapping.
         String[] produces() default {};
     }
   ```

   > @Target：设定注解的使用范围，取值为ElementType中的一个或
   >
   > 几个。
   >
   > | 取值                | 注解使用范围                  |
   > | ------------------- | ----------------------------- |
   > | METHOD              | 可用于方法上                  |
   > | TYPE                | 可用于类或者接口              |
   > | TYPEANNOTATION_TYPE | 可用于注解类型上              |
   > | CONSTRUCTOR         | 可用于构造方法上              |
   > | FIELD               | 可用于域上                    |
   > | LOCAL_VARIABLE      | 可用于局部变量                |
   > | PACKAGE             | 用户记录java文件的package信息 |
   > | PARAMETER           | 可用于参数上                  |
   >
   > @Retention：
   >
   > @Documented：

   **This annotation can be used both at the class and at the method level. In most cases, at the method level applications will prefer to use one of the HTTP method specific variants @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, or @PatchMapping.**

2. @ModuleAttribute（p97）

3. @SessionAttributes（p98）

4. @ConfigurationProperties（p94）

5. @EnableConfigurationProperties（p95）

6. Model（p98）

7. DefaultResourceLoader（p95）

8. 自定义注解（P73-3.2.2）

9. 国际化，地域解析LocaleResolver（P74-3.3）

10. Spring MVC拦截器Interceptor（p77）

11. enctype=multipart/form-data（p87）

12. Resource（p89）

13. MultipartFile（p89）

14. InputStream/OutputStream（p90）

15. IOUtils（p90）

16. try…with（p90）

17. RedirectAttributes（p92）

18. th：src（p93）

19. ClassPathResource（P93）

14. URLConnection（p93）