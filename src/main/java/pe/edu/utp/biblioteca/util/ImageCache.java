package pe.edu.utp.biblioteca.util;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import pe.edu.utp.biblioteca.model.Libro;

import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private static Map<String, Image> cache = new HashMap<>();

    public static Image getImage(String key, String url, double width, double height) {
        if(cache.containsKey(key)) {
            return cache.get(key);
        } else {
            Image result = new Image(url, width,height, true, true);
            cache.put(key, result);
            return result;
        }
    }

    public static Task<Image> getImageTask(Libro book, OnImageTaskCompleteListener listener) {
        Task<Image> loadImageTask = new Task<>() {
            @Override
            protected Image call() throws Exception {
                return ImageCache.getImage(book.getIsbn(), book.getPicture(), 80, 100);
            }
        };

        loadImageTask.setOnSucceeded(workerStateEvent -> {
            Image image = loadImageTask.getValue();
            listener.onImageTaskComplete(image);
        });
        return loadImageTask;
    }

    public interface OnImageTaskCompleteListener {
        public void onImageTaskComplete(Image image);
    }
}
