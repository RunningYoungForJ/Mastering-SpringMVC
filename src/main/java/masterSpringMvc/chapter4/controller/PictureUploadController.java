package masterSpringMvc.chapter4.controller;

import masterSpringMvc.chapter2.config.PictureUploadProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Created by yangkun on 2018/3/15.
 */
@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {
    private final Resource pictureDir;
    private final Resource anonymousPicture;

    @Autowired
    public PictureUploadController(PictureUploadProperties properties) {
        this.pictureDir = properties.getUploadPath();
        this.anonymousPicture = properties.getAnonymousPicture();
    }

    @RequestMapping("upload")
    public String uploadPage(){
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes, Model model) throws IOException{
        throw new IOException("this is a exception message");
        /*if (file.isEmpty()||!isImage(file)){
            redirectAttributes.addFlashAttribute("error","Incorrect file. Please upload a picture");
            return "redirect:/upload";
        }
        Resource picturePath = copyFileToPictures(file);
        model.addAttribute("picturePath",picturePath);
        return "profile/uploadPage";*/
    }

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response, @ModelAttribute("picturePath") Resource picturePath) throws IOException{
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.getFilename()));
        IOUtils.copy(picturePath.getInputStream(),response.getOutputStream());
    }

    @RequestMapping(value = "/uploadError")
    public ModelAndView onUploadError(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error",request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE));
        return modelAndView;
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

    @ModelAttribute("picturePath")
    public Resource picturePath(){
        return anonymousPicture;
    }

    @ExceptionHandler(value ={IOException.class} )
    public ModelAndView handleException(IOException exception){
        ModelAndView modelAndView=new ModelAndView("profile/uploadPage");
        modelAndView.addObject("error",exception.getMessage());
        return modelAndView;
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }

    private boolean isImage(MultipartFile file){
        return file.getContentType().startsWith("image");
    }
}
