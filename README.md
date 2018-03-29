# Mastering-SpringMVC

> Pick up something

[TOC]

## 注解篇

### 控制器

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

2. @RequestParam（p43）

3. @PathVariable

4. @RequestBody

5. @ResponseBody

6. @valid（p69）

2. @ModuleAttribute（p97）

   ```java
   //注解范围：参数、方法
   @Target({ElementType.PARAMETER, ElementType.METHOD})
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   public @interface ModelAttribute {
       //Alias for name().
       @AliasFor("name")
       String value() default "";
       //The name of the model attribute to bind to.
       @AliasFor("value")
       String name() default "";

       boolean binding() default true;
   }
   ```

   **Annotation that binds a method parameter or method return value to a named model attribute, exposed to a web view. Supported for controller classes with [`@RequestMapping`](https://docs.spring.io/spring/docs/5.0.5.BUILD-SNAPSHOT/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html) methods.**

   > 当同一个controller中有任意一个方法被@ModelAttribute注解标记，页面请求只要进入这个控制器，不管请求那个方法，均会先执行被@ModelAttribute标记的方法，所以我们**可以用@ModelAttribute注解的方法做一些初始化操作**。当同一个controller中有多个方法被@ModelAttribute注解标记，所有被@ModelAttribute标记的方法均会被执行，按先后顺序执行，然后再进入请求的方法。

3. @SessionAttributes（p98）

4. @ConfigurationProperties（p94）

5. @EnableConfigurationProperties（p95）

6. Model（p98）

7. DefaultResourceLoader（p95）

8. 自定义注解（P73-3.2.2）

9. 国际化，地域解析LocaleResolver（P74-3.3）

10. Spring MVC拦截器Interceptor（p77）

11. enctype=multipart/form-data（p87）

    enctype属性指定了表单数据的内容类型，并根据这个类型，在提交form表单到服务器的时候，对内容进行编码。

    > This attribute specifies the [content type](https://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#form-content-type) used to submit the form to the server (when the value of [method](https://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#adef-method) is "post"). The default value for this attribute is ["application/x-www-form-urlencoded"](). The value ["multipart/form-data"]() should be used in combination with the [INPUT](https://www.w3.org/TR/1999/REC-html401-19991224/interact/forms.html#edef-INPUT) element, type="file".

    enctype有三个属性值：

    - application/x-www-form-urlencoded（默认属性）
    - multipart/form-data（传输图片、文件等）
    - text/plain

12. Resource（p89）

    > 在日常程序开发中，处理外部资源是很繁琐的事情，我们可能需要处理URL资源、File资源资源、ClassPath相关资源、服务器相关资源（JBoss AS 5.x上的VFS资源）等等很多资源。因此处理这些资源需要使用不同的接口，这就增加了我们系统的复杂性；而且处理这些资源步骤都是类似的（打开资源、读取资源、关闭资源），因此如果能抽象出一个统一的接口来对这些底层资源进行统一访问，是不是很方便，而且使我们系统更加简洁，都是对不同的底层资源使用同一个接口进行访问。
    > Spring 提供一个Resource接口来统一这些底层资源一致的访问，而且提供了一些便利的接口。

    **Interface for a resource descriptor that abstracts from the actual type of underlying resource, such as a file or class path resource.**

    **An InputStream can be opened for every resource if it exists in physical form, but a URL or File handle can just be returned for certain resources. The actual behavior is implementation-specific.**

13. MultipartFile（p89）

    > MultipartFile接口用来接收upload的file对象，并提供了多个方法，来访问文件的名称、大小及其内容。

    **A representation of an uploaded file received in a multipart request. The file contents are either stored in memory or temporarily on disk. In either case, the user is responsible for copying file contents to a session-level or persistent store as and if desired. The temporary storage will be cleared at the end of request processing.**

    ```java
    //Return the name of the parameter in the multipart form.
    String getName();
    //Return the original filename in the client's filesystem.
    String getOriginalFilename();
    //Return an InputStream to read the contents of the file from.The user is responsible for closing the returned stream.
    InputStream getInputStream() throws IOException;
    ```

14. InputStream/OutputStream（p90）

15. IOUtils（p90）

16. try…with（p90）

17. RedirectAttributes（p92）

18. th：src（p93）

19. ClassPathResource（P93）

20. URLConnection（p93）

21. @ExceptionHandler（p99）

22. EmbeddedServletContainerCustomizer（p100）

23. MessageSource（p102）

24. HTTP会话

25. @Scope（p104）

31. redirect（p115）

    **The special `redirect:` prefix in a view name allows you to perform a redirect. The `UrlBasedViewResolver` (and sub-classes) recognize this as an instruction that a redirect is needed. The rest of the view name is the redirect URL.**

    The net effect is the same as if the controller had returned a `RedirectView`, but now the controller itself can simply operate in terms of logical view names. A logical view name such as `redirect:/myapp/some/resource` will redirect relative to the current Servlet context, while a name such as `redirect:http://myhost.com/some/arbitrary/path` will redirect to an absolute URL.**

32. @RestController（p129）

33. @Primary（p135ß）

### DAO

### Service

### Config 

1. @EnableGlobalMethodSecurity（p153）
2. WebSecurityConfigurerAdapter（p153）
3. @Order（p158）

### Entity

### AOP



1. 面向切面编程（AOP）