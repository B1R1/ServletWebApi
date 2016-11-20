package ua.burko.servletapi.searcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

/**
 * Class contains two methods. First for text searching, second for extracting metaData from file.
 *
 * @author Roman Burko <roman.burko@gmail.com>
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class Searcher {

    private static final int THOUSAND_BYTES = 1000;
    private static final int ONE_KILOBYTE_SIZE = 1024;


    private static File getFile() {
        return new File(Searcher.class.getResource("/testFile.txt").getFile());
    }


    public static JSONArray search(String queryText, int queryCharLimit, int queryStringLength) {

        JSONArray result = new JSONArray();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(getFile()));
            int allCharacters = 0;

            if (!queryText.isEmpty()) {
                while (reader.ready()) {
                    String str = reader.readLine();

                    if (queryStringLength > 0 && str.length() > queryStringLength || str.isEmpty())
                        continue;

                    if (str.toLowerCase().contains(queryText)) {
                        allCharacters += str.length();
                        if (allCharacters > queryCharLimit)
                            break;
                        result.add(str);
                    }
                }
            } else {
                while (reader.ready()) {
                    String str = reader.readLine();

                    if (queryStringLength > 0 && str.length() > queryStringLength || str.isEmpty())
                        continue;

                    allCharacters += str.length();
                    if (allCharacters > queryCharLimit)
                        break;
                    result.add(str);
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject getMetaData() {

        JSONObject result = new JSONObject();

        Path file = getFile().toPath();
        String fileName = file.getFileName().toString();
        BasicFileAttributes attributes;
        SimpleDateFormat date = new SimpleDateFormat("MMMM d, yyyy 'at' HH:mm aaa");
        int kilobytes;

        try {
            attributes = Files.readAttributes(file, BasicFileAttributes.class);
            if (attributes.size() < THOUSAND_BYTES)
                kilobytes = 1;
            else
                kilobytes = (int)(attributes.size() / ONE_KILOBYTE_SIZE);

            result.put("fileName", fileName);
            result.put("fileSize", kilobytes + "KB");
            result.put("fileCreationDate", date.format(attributes.creationTime().toMillis()));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
