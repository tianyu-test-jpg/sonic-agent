package org.cloud.sonic.agent.transport;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(TransportController.DELEGATE_PREFIX)
public class TransportController {

    public final static String DELEGATE_PREFIX = "/uia";

    @Autowired
    private RoutingDelegate routingDelegate;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, produces = MediaType.ALL_VALUE)
    public ResponseEntity catchAll(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getRequestURI());
        log.info("收到  取到的请求路径"+request.getRequestURI());
        System.out.println("获取到的请求路径"+request.getRequestURI());
        return routingDelegate.redirect(request, response, "http://localhost:" + request.getRequestURI().replace(DELEGATE_PREFIX + "/", ""), request.getRequestURI());
    }
}