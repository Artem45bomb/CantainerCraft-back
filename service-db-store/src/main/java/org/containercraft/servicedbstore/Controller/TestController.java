package org.containercraft.servicedbstore.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final StreamBridge bridge;

    @GetMapping("/test/rabbit")
    public void send(){
        bridge.send("processMessage-out-0","kkk");
    }
}
