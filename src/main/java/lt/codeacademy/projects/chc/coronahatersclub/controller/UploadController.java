package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.entity.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "profile")
public class UploadController {

    private final UserRepository userRepository;

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/images/profile/";
    private final String PROFILE_IMG_URL = "/uploads/images/profile/";

    @CrossOrigin
    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute(name = "loggedUser") User user, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        var username = user.getUsername();
        Long id = user.getId();
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/user/profile";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = "." + FilenameUtils.getExtension(fileName);
        String profileImgPath = PROFILE_IMG_URL + username + id + "profile" + ext;

        user.setProfileImage(profileImgPath);
        userRepository.save(user);

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + username + id + "profile" + ext);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/user/profile";
    }

}