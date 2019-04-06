package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserService userService;

    @PostMapping("/attachment/{id}")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile, @PathVariable Long id){
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

}
