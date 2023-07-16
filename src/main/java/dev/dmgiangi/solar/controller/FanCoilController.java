package dev.dmgiangi.solar.controller;

import dev.dmgiangi.solar.relay.FanCoilService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fancoil")
@AllArgsConstructor
public class FanCoilController {
    private final FanCoilService fanCoilService;

    @GetMapping("/step")
    public String step() {
        fanCoilService.step();

        return "ok";
    }
}
