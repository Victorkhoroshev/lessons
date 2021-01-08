package net.thumbtack.school.elections.server.service;

import java.io.*;

public class ServerService {
    private static File config;

    public static void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            try(Reader reader = new FileReader(savedDataFileName);
                Writer writer = new FileWriter(config)) {
                reader.transferTo(writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void stopServer(String saveDataFileName) {
        if (config != null) {
            try(Reader reader = new FileReader(config);
                Writer writer = new FileWriter(saveDataFileName)) {
                reader.transferTo(writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
