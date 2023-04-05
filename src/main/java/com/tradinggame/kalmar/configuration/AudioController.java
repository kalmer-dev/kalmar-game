package com.tradinggame.kalmar.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AudioController {



    @GetMapping("/audioHome")
    public void streamAudioHome(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/coniferous-forest.mp3");
    }

    @GetMapping("/audioMap")
    public void streamAudioMap(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/french-jazz-music-HMM.mp3");
    }

    @GetMapping("/audioLobby")
    public void streamAudioLobby(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/epic-relaxing-flute-music.mp3");
    }

    @GetMapping("/audioCash")
    public void streamAudioCash(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/cash-register.mp3");
    }

    @GetMapping("/audioDoor")
    public void streamAudioDoor(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/dorm-door-opening.mp3");
    }

    @GetMapping("/audioDrum")
    public void streamAudioDrum(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/drum.mp3");
    }

    @GetMapping("/audioScissors")
    public void streamAudioScissors(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/scissors.mp3");
    }

    @GetMapping("/audioPaper")
    public void streamAudioPaper(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/paper.mp3");
    }

    @GetMapping("/audioRock")
    public void streamAudioRock(HttpServletResponse response) throws IOException {
        streamAudio(response, "src/main/resources/sound/rock.mp3");
    }

    public void streamAudio(HttpServletResponse response, String path) throws IOException {
        response.setContentType("audio/mpeg");

        File audioFile = new File(path);
        FileInputStream audioStream = new FileInputStream(audioFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = audioStream.read(buffer)) != -1) {
            response.getOutputStream().write(buffer, 0, bytesRead);
        }
        audioStream.close();
    }

}
