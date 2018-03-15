package masterSpringMvc.chapter4.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

/**
 * Created by yangkun on 2018/3/15.
 */
@Controller
public class PictureUploadController {
    public static final Resource PICTURE_DIR=new FileSystemResource("./pictures");

    @RequestMapping("upload")
    public String uploadPage(){
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String onUpload(MultipartFile file,RedirectAttributes redirectAttributes) throws IOException{
        if (file.isEmpty()||!isImage(file)){
            redirectAttributes.addFlashAttribute("error","Incorrect file. Please upload a picture");
            return "redirect:/upload";
        }
        copyFileToPictures(file);
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws IOException{
        ClassPathResource resource=new ClassPathResource("/images/user.png");
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(resource.getFilename()));
        IOUtils.copy(resource.getInputStream(),response.getOutputStream());
    }

    private Resource copyFileToPictures(MultipartFile file) throws IOException{
        String fileExtension=getFileExtension(file.getOriginalFilename());
        File tempFile=File.createTempFile("pic",fileExtension,PICTURE_DIR.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {

            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }

    private boolean isImage(MultipartFile file){
        return file.getContentType().startsWith("image");
    }
}
