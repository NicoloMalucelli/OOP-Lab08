package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final DrawNumberView view;
    private static final String MIN = "minimum";
    private static final String MAX = "maximum";
    private static final String ATTEMPTS = "attempts";

    /**
     * @param configurationFile
     *          file to use to configurate settings
     */
    public DrawNumberApp(final File configurationFile) {
        final Map<String, Integer> settings = getSettings(configurationFile);
        this.model = new DrawNumberImpl(settings.get(DrawNumberApp.MIN),
                                        settings.get(DrawNumberApp.MAX), 
                                        settings.get(DrawNumberApp.ATTEMPTS));
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    private Map<String, Integer> getSettings(final File configurationFile) {
        final Map<String, Integer> outputMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(configurationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(": ")) {
                    throw new IllegalArgumentException("settings file has a wrong format");
                }
                outputMap.put(line.split(": ")[0], Integer.parseInt(line.split(": ")[1]));
            }
        } catch (IOException e) {
            System.out.println("error reading from settings file");
        }
        return outputMap;
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp(new File(ClassLoader.getSystemResource("config.yml").getPath()));
    }

}
