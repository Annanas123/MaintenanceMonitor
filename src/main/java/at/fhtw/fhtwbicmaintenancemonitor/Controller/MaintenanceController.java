package at.fhtw.fhtwbicmaintenancemonitor.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDateTime;

@Controller
public class MaintenanceController {
    public Boolean serviceOk = true;

    @RequestMapping(value = "/api/message", method = RequestMethod.GET)
    public String displayStatus(Model model) throws FileNotFoundException {
        serviceOk = readStatusFromFile();
        String msgText = messageText(serviceOk);
        String lastUpdate = lastUpdated();

        if (serviceOk) {
            model.addAttribute("color", "green");
        }
        else {
            model.addAttribute("color", "red");
        }
        model.addAttribute("messageText", msgText);
        model.addAttribute("update", lastUpdate);

        return "index";
    }

    @GetMapping( "/api/message/set")
    public String setStatus(@RequestParam String m, Model model) throws IOException {
        writeStatusToFile("false");
        writeMsgTextToFile(m.replace("+", " "));
        writeLastUpdatedToFile();
        return displayStatus(model);
    }

    @GetMapping("/api/message/reset")
    public String resetStatus(Model model) throws IOException {
        String m = messageText(true);
        writeStatusToFile("true");
        writeMsgTextToFile(m);
        writeLastUpdatedToFile();
        return displayStatus(model);
    }

    boolean readStatusFromFile() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:status.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            String status = sb.toString();
            return Boolean.parseBoolean(status);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeStatusToFile(String status) throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:status.txt");
        try (FileWriter out = new FileWriter(file, false)) { //overwrite file
            out.write(status);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String messageText(boolean serviceOk) throws FileNotFoundException {
        if (serviceOk) {
            return "Everything works as expected";
        }
            return readMsgTextFromFile();
    }

    String readMsgTextFromFile() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:message.txt");
        String sb = getStringFromFile(file);
        if (sb != null) return sb;
        return "";
    }

    private void writeMsgTextToFile(String m) throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:message.txt");
        try (FileWriter out = new FileWriter(file, false)) { //overwrite file
            out.write(m);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    String lastUpdated() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:updateTime.txt");
        String sb = getStringFromFile(file);
        if (sb != null) return sb;
        return "";
    }

    private String getStringFromFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeLastUpdatedToFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:updateTime.txt");
        try (FileWriter out = new FileWriter(file, false)) { //overwrite file
            String update = LocalDateTime.now().toString();
            out.write("last update:" + update);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
