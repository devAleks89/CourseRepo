package models;

import java.io.File;

import static framework.Utils.random;

public class AppBuilder {
    public static Application generalAppBuilder() {
        Application generalApp = new Application();
        generalApp.setTitle("title_ " + random());
        generalApp.setDescription("description_ " + random());
        return generalApp;
    }

    public static Application appWithoutImages(String category) {
        Application application = generalAppBuilder();
        application.setCategory(category);
        return application;
    }

    public static Application appWithImageAndIcon(String category) {
        Application appWithImages = generalAppBuilder();
        appWithImages.setIconPath(new File("./src/test/resources/img/seleniumIcon.jpg").getAbsolutePath());
        appWithImages.setImagePath(new File("./src/test/resources/img/seleniumImage.jpg").getAbsolutePath());
        appWithImages.setCategory(category);
        return appWithImages;
    }
}

