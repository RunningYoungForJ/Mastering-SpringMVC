package masterSpringMvc.chapter4.controller;

import masterSpringMvc.chapter2.config.PictureUploadProperties;
import masterSpringMvc.chapter4.profile.UserProfileSession;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Locale;

/**
 * Created by yangkun on 2018/3/15.
 */
@Controller
public class PictureUploadController {
    private final Resource pictureDir;
    private final Resource anonymousPicture;
    private final MessageSource messageSource;
    private final UserProfileSession userProfileSession;

    @Autowired
    public PictureUploadController(PictureUploadProperties properties,MessageSource messageSource,UserProfileSession userProfileSession) {
        this.pictureDir = properties.getUploadPath();
        this.anonymousPicture = properties.getAnonymousPicture();
        this.messageSource=messageSource;
        this.userProfileSession=userProfileSession;
    }

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws IOException{
        Resource picturePath=userProfileSession.getPicturePath();
        if (picturePath==null){
            picturePath=anonymousPicture;
        }
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.getFilename()));
        IOUtils.copy(picturePath.getInputStream(),response.getOutputStream());
    }

    /*
    * redirect：A logical view name such as redirect:/myapp/some/resource
    * will redirect relative to the current Servlet context,
    * while a name such as redirect:http://myhost.com/some/arbitrary/path will redirect to an absolute URL.
    * 在下面的redirect中，当前Servlet上下文的根路径是http://localhost:8080
    * 因此，redirect：/profile将会重定位到localhost：8080/profile这个URL
    * */
    @RequestMapping(value = "/profile",method = RequestMethod.POST,params = {"upload"})
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes, Model model) throws IOException{
        if (file.isEmpty()||!isImage(file)){
            redirectAttributes.addFlashAttribute("error","Incorrect file. Please upload a picture");
            return "redirect:/profile";//localhost:8080/profile
        }
        Resource picturePath = copyFileToPictures(file);
        userProfileSession.setPicturePath(picturePath.getURL());
        return "redirect:/profile";//localhost:8080/profile
    }

    private Resource copyFileToPictures(MultipartFile file) throws IOException{
        String fileExtension=getFileExtension(file.getOriginalFilename());
        File tempFile=File.createTempFile("pic",fileExtension,pictureDir.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {

            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }

    @ExceptionHandler(value ={IOException.class} )
    public ModelAndView handleException(Locale locale){
        ModelAndView modelAndView=new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error",messageSource.getMessage("upload.io.exception",null,locale));
        return modelAndView;
    }

    @RequestMapping(value = "/uploadError")
    public ModelAndView onUploadError(Locale locale){
        ModelAndView modelAndView=new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error",messageSource.getMessage("upload.file.too.big",null,locale));
        return modelAndView;
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }

    private boolean isImage(MultipartFile file){
        return file.getContentType().startsWith("image");
    }
}
