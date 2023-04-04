package com.tradinggame.kalmar.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AudioController {


    @GetMapping("/audio")
    public void streamAudio(HttpServletResponse response) throws IOException {
        response.setContentType("audio/mpeg");

        File audioFile = new File("src/main/resources/sound/horror-hit-logo-142395.mp3");
        FileInputStream audioStream = new FileInputStream(audioFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = audioStream.read(buffer)) != -1) {
            response.getOutputStream().write(buffer, 0, bytesRead);
        }

        audioStream.close();
    }
}
