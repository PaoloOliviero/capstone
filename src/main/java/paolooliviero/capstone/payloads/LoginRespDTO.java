package paolooliviero.capstone.payloads;

import java.util.List;

public record LoginRespDTO(String accessToken, List<String> ruoli) {
}

