package com.example.ledexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class VideoController {

    @GetMapping("/video")
    public String getVideo() {
        //перед запустком нужно установить все по инсрукции https://www.instructables.com/Raspberry-Pi-Video-Streaming/
        // ну и выполнить следующие команды (они включают камеру)
        //$sudo uv4l -nopreview --auto-video_nr --driver raspicam --encoding mjpeg --width 640 --height 480 --framerate 20 --server-option '--port=8081' --server-option '--max-queued-connections=30' --server-option '--max-streams=25' --server-option '--max-threads=29'
        //Обращаем внимание на порт!! и на ip в html
        return "video";
    }

}
