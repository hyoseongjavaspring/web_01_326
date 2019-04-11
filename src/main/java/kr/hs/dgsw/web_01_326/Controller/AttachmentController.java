package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserService userService;

    @PostMapping("/attachment/{id}")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile, @PathVariable Long id) {
        String destFileName = "C:/Project/JAVA/DGSW_JAVA_SPRING__/web_01_326/upload/"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd"))
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();
        try {
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);

            User user = userService.view(id);
            user.setOriginalName(srcFile.getOriginalFilename());
            user.setStoredPath(destFileName);
            userService.update(user);
            return new AttachmentProtocol(destFileName, srcFile.getOriginalFilename());
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        if(type.equals("user")){
            AttachmentProtocol attachmentProtocol = this.userService.getPathById(id);

            try {
                File file = new File(attachmentProtocol.getStoredPath());
                if (file.exists() == false) return;

                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) mimeType = "application/octet-stream";

                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", "inline; filename=\"" + "" + file.getName() + "\"");
                response.setContentLength((int) file.length());

                InputStream is = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(is, response.getOutputStream());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if(type.equals("comment")){
            // TODO : 댓글에 이미지 추가 기능..
        }



    }

}
