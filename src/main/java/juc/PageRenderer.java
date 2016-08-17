package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by kmhaswade on 8/15/16.
 */
public class PageRenderer {

    static class ImageData {

    }
    static class ImageInfo {

        public static ImageData downloadImage(ImageInfo imageInfo) {
            return null;
        }
    }
    public List<ImageInfo> scanForImageInfo(CharSequence source) {
        List<ImageInfo> infos = new ArrayList<>();
        return infos;
    }
    public void renderPage(CharSequence source) {
        List<ImageInfo> imageInfos = scanForImageInfo(source);
        // create a Callable representing the download of all images
        final Callable<List<ImageData>> imageDownloadTask = () ->
            imageInfos.stream()
                    .map(ImageInfo::downloadImage)
                    .collect(Collectors.toList());
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.
                newFixedThreadPool(processors);
        // get the handle called images
        Future<List<ImageData>> handleToImages = executor.submit(imageDownloadTask);
        // do your job now, since you have the handle from the download task
        // renderText(source);
        // renderText(source);
        try {
            List<ImageData> images = handleToImages.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            handleToImages.cancel(true);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
