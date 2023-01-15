package at.fhtw.fhtwbicmaintenancemonitor.Controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MaintenanceControllerTest {
    @Mock
    Model model= mock(Model.class);

    @Before
    public void init() {
        model.addAttribute("dummy", "dummy");
    }

    @Test
    void displayStatus() throws FileNotFoundException {
        var controller = new MaintenanceController();
        var result = controller.displayStatus(model);

        assertEquals("index", result);
    }

    @Test
    void setStatus() throws IOException {
        LocalDateTime referenceDate = LocalDateTime.now();
        String m = "my message";
        var controller = new MaintenanceController();
        var result = controller.setStatus(m, model);
        var resultUpdateDate = LocalDateTime.parse(controller.lastUpdated().substring(12));
        var resultMessage = controller.readMsgTextFromFile();
        var resultStatus = controller.readStatusFromFile();

        assertEquals("index", result);
        assertTrue(referenceDate.compareTo(resultUpdateDate) < 0);
        assertEquals(m, resultMessage);
        assertFalse(resultStatus);
    }

    @Test
    void resetStatus() throws IOException {
        LocalDateTime referenceDate = LocalDateTime.now();
        var controller = new MaintenanceController();
        var result = controller.resetStatus(model);
        var resultUpdateDate = LocalDateTime.parse(controller.lastUpdated().substring(12));
        var resultMessage = controller.readMsgTextFromFile();
        var resultStatus = controller.readStatusFromFile();

        assertEquals("index", result);
        assertTrue(referenceDate.compareTo(resultUpdateDate) < 0);
        assertEquals("Everything works as expected", resultMessage);
        assertTrue(resultStatus);
    }
}