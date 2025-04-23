package bankapp;

import java.io.*;
import java.util.*;

/**
 * Manages transaction statements for customers, persisting them under data/user/{username}/{fileName}.
 */
public class Statement {
    private final String pathToData = "data/user/";
    private final String fileName;
    private final Map<String, List<Integer>> content = new HashMap<>();

    public Statement(String fileName) {
        this.fileName = fileName;
        loadStatement();
    }

    private void loadStatement() {
        File baseDir = new File(pathToData);
        if (!baseDir.exists() || !baseDir.isDirectory()) return;

        File[] userDirs = baseDir.listFiles(File::isDirectory);
        if (userDirs == null) return;

        for (File userDir : userDirs) {
            String username = userDir.getName();
            List<Integer> activity = new ArrayList<>();
            File stmtFile = new File(userDir, fileName);
            if (!stmtFile.exists()) {
                try { stmtFile.createNewFile(); } catch (IOException ignored) {}
            } else {
                try (BufferedReader br = new BufferedReader(new FileReader(stmtFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        if (!line.isEmpty()) {
                            try {
                                activity.add(Integer.parseInt(line));
                            } catch (NumberFormatException ignored) {}
                        }
                    }
                } catch (IOException ignored) {}
            }
            content.put(username, activity);
        }
    }

    /**
     * Appends a transaction to both memory and the on-disk statement file.
     * @param customer    the customer
     * @param transaction positive for deposit, negative for withdrawal
     */
    public void add(Customer customer, int transaction) {
        String username = customer.getUsername();
        List<Integer> activity = content.computeIfAbsent(username, k -> new ArrayList<>());
        activity.add(transaction);

        File userDir = new File(pathToData + username);
        if (!userDir.exists()) userDir.mkdirs();
        File stmtFile = new File(userDir, fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(stmtFile, true))) {
            bw.write(String.valueOf(transaction));
            bw.newLine();
        } catch (IOException ignored) {}
    }

    /**
     * Returns the formatted statement lines, e.g. "Deposit | 150" or "Withdraw | -50".
     */
    public List<String> getStatement(Customer customer) {
        List<Integer> activity = content.getOrDefault(customer.getUsername(), Collections.emptyList());
        List<String> result = new ArrayList<>();
        for (Integer amt : activity) {
            if (amt >= 0) {
                result.add("Deposit | " + amt);
            } else {
                result.add("Withdraw | " + amt);
            }
        }
        return result;
    }
}
