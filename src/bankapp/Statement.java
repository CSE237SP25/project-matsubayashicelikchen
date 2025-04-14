package bankapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statement {
    private final String pathToData = "data/user/";
    private String file;
    private Map<String, List<Integer>> content;
    
    public Statement(String file) {
        this.file = file;
        this.loadStatement();
    }
    
    public void loadStatement() {
        File baseDir = new File(pathToData);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            return;
        }
        
        File[] userDirs = baseDir.listFiles(File::isDirectory);
        if (userDirs == null) {
            return;
        }
        
        content = new HashMap<>();
        
        for (File userDir : userDirs) {
            File infoFile = new File(userDir, this.file);
            if (!infoFile.exists() || !infoFile.isFile()) {
                try {
                    infoFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
                content.put(userDir.getName(), new ArrayList<>());
                continue;
            }
            
            List<Integer> activity = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(infoFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        try {
                            int transaction = Integer.parseInt(line);
                            activity.add(transaction);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid number format in file " + infoFile.getPath() + ": " + line);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            content.put(userDir.getName(), activity);
        }
    }
    
    public void add(Customer customer, int transaction) {
        String username = customer.getUsername();
        if (content == null) {
            content = new HashMap<>();
        }
        List<Integer> activity = content.get(username);
        if (activity == null) {
            activity = new ArrayList<>();
            content.put(username, activity);
        }
        activity.add(transaction);
        
        File userDir = new File(pathToData + username);
        if (!userDir.exists()) {
            if (!userDir.mkdirs()) {
                System.err.println("Cannot create user directory: " + userDir.getPath());
                return;
            }
        }
        File statementFile = new File(userDir, this.file);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(statementFile, true))) {
            bw.write(String.valueOf(transaction));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Integer> getStatement(Customer customer) {
        String username = customer.getUsername();
        return this.content.get(username);
    }
}
