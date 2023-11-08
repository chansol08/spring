package org.spring.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
public class FileController {

    @RequestMapping("/upload.do")
    public String upload(MultipartHttpServletRequest multipartRequest,
                         HttpServletRequest request,
                         Model model) throws Exception {
        String UPLOAD_DIR = "file_repository";
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;

        Map map = new HashMap<>();
        Enumeration<String> parameterNames = multipartRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String id = parameterNames.nextElement();
            String name = multipartRequest.getParameter(id);

            map.put(id, name);
        }

        Iterator<String> iterator = multipartRequest.getFileNames();
        List<String> fileList = new ArrayList<>();

        while (iterator.hasNext()) {
            String paramName = iterator.next();
            MultipartFile multiFile = multipartRequest.getFile(paramName);
            String originName = multiFile.getOriginalFilename();

            fileList.add(originName);
            File file = new File(uploadPath + "\\" + paramName);

            if (multiFile.getSize() != 0) {
                if (!file.exists()) {
                    if (file.getParentFile().mkdir()) {
                        file.createNewFile();
                    }
                } //파일 디렉토리 생성

                multiFile.transferTo(new File(uploadPath + "\\" + originName));
            }
        }

        map.put("fileList", fileList);
        model.addAttribute("map", map);

        return "result";
    }
}
