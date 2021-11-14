package lt.codeacademy.projects.chc.coronahatersclub.controller;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.projects.chc.coronahatersclub.model.User;
import lt.codeacademy.projects.chc.coronahatersclub.repository.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "userdetails")
public class UploadController {

    private final UserRepository userRepository;

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/images/profile/";
    private final String PROFILE_IMG_URL = "uploads/images/profile/";

    @GetMapping
    public String userDetailsPage() {
        return "userdetails";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Authentication authentication) {
        var user = (User)authentication.getPrincipal();
        var username = user.getUsername();
        Long id = user.getId();
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/userdetails";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = "."+FilenameUtils.getExtension(fileName);
        String profileImgPath = PROFILE_IMG_URL+username+id+"profile"+ext;

        var userInRepo = userRepository.findById(id).orElseThrow();
        userInRepo.setProfileImage(profileImgPath);
        userRepository.save(userInRepo);
        user.setProfileImage(profileImgPath);

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR +username+id+"profile"+ext);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/userdetails";
    }

}