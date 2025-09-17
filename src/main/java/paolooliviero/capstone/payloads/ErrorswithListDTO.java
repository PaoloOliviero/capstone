package paolooliviero.capstone.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorswithListDTO(String message, LocalDateTime timestamp, List<String> errorsList) {
}


