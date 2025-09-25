package game.adventure.main;

import game.adventure.util.Adventure;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class FileManager {
    private final String saveDirectory;
    private final List<Adventure> loadedAdventures = new ArrayList<>();

    public FileManager() {
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");
        if (os.contains("win")) {
            saveDirectory = userHome + "/Documents/Adventures/";
        } else if (os.contains("mac")) {
            throw new RuntimeException("mac not supported");
        } else {
            saveDirectory = userHome + "/.config/Adventures/";
        }
        loadAdventures();
    }

    private void loadAdventures() {
        File gameDirectory = new File(saveDirectory);
        if (gameDirectory.mkdirs()) return;
        File[] jarFiles = gameDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if (jarFiles == null || jarFiles.length == 0) {
            System.out.println("no jars found in " + saveDirectory);
        }

        for (File jar : jarFiles) {
            try {
                URL jarUrl = jar.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, Adventure.class.getClassLoader());
                ServiceLoader<Adventure> serviceLoader = ServiceLoader.load(Adventure.class, classLoader);
                for (Adventure adventure : serviceLoader) {
                    loadedAdventures.add(adventure);
                }
            } catch (MalformedURLException e) {
                System.err.println("failed to load jar " + jar.getName());
            }
        }
    }

    public List<Adventure> getAdventures() {
        return loadedAdventures;
    }

}
