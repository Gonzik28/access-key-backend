package com.example.accesskeybackend.template.controller;

import com.example.accesskeybackend.template.dto.RequestDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.*;

@RestController
@RequestMapping("/api/web")
public class InfoController {

    @PostMapping("/checkIpv6Support")
    public ResponseEntity<String> modify(@RequestBody RequestDto request) {
        boolean hasIPv6Address = false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("success - ");
        try {
            InetAddress[] inetAddresses = InetAddress.getAllByName(new URL(request.getUrl()).getHost());
            for (InetAddress address : inetAddresses) {
                if (address instanceof Inet6Address) {
                    hasIPv6Address = true;
                    break;
                }
            }
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL format: " + request);
        } catch (UnknownHostException e) {
            System.err.println("Host not found: " + request);
        }

        if (hasIPv6Address) {
            stringBuilder.append(true);
        } else {
            stringBuilder.append(false);
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatusCode.valueOf(200));
    }
}
