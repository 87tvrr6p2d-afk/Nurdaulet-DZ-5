import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurationManager {

    private static volatile ConfigurationManager instance;

    private final Map<String, String> settings = new ConcurrentHashMap<>();
    private final Object fileLock = new Object();

    private ConfigurationManager() {
        // private constructor
    }

    // Double-checked locking (thread-safe lazy init)
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    // Load settings from file (key=value per line)
    public void loadFromFile(String path) {
        synchronized (fileLock) {
            File file = new File(path);
            if (!file.exists()) return;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;
                    int idx = line.indexOf('=');
                    if (idx <= 0) continue;
                    String key = line.substring(0, idx).trim();
                    String value = line.substring(idx + 1).trim();
                    settings.put(key, value);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config: " + e.getMessage(), e);
            }
        }
    }

    // Save settings to file
    public void saveToFile(String path) {
        synchronized (fileLock) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
                for (Map.Entry<String, String> entry : settings.entrySet()) {
                    pw.println(entry.getKey() + "=" + entry.getValue());
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to save config: " + e.getMessage(), e);
            }
        }
    }

    public void set(String key, String value) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be empty");
        }
        settings.put(key, value);
    }

    public String get(String key) {
        String value = settings.get(key);
        if (value == null) {
            throw new SettingNotFoundException("Setting not found: " + key);
        }
        return value;
    }

    public boolean contains(String key) {
        return settings.containsKey(key);
    }

    public Map<String, String> getAll() {
        return Map.copyOf(settings);
    }
}
