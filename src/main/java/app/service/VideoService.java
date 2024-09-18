package app.service;

import app.configuration.ConstantConfiguration;
import app.dto.response.Response;
import app.util.VideoExtractor;
import lombok.RequiredArgsConstructor;
import org.bytedeco.javacv.FrameGrabber;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import static java.rmi.server.LogStream.log;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final ConstantConfiguration constant;

    public String extractVideo(String filepath) {
        File videoFile = new File(filepath);
//        System.out.println(filepath);
        try {
            if (videoFile.exists()) {
                VideoExtractor extractor = new VideoExtractor();
                extractor.extractImages(
                        filepath,
                        constant.LOCAL_IMAGES_PATH,
                        constant.IMAGES_EXTENSION_TYPE,
                        constant.EXTRACTOR_IMAGE_FRAME_JUMP
                );
                System.out.println("Processing video at: " + filepath);
                return "Video processed successfully!";
            }
            else {
                return "Video file not found.";
            }
        }
        catch (FrameGrabber.Exception | IOException e) {
            return "exception: "+ e.getMessage();
        }
    }
}
