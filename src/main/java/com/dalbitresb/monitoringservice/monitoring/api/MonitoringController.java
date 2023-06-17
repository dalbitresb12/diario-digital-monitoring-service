package com.dalbitresb.monitoringservice.monitoring.api;

import com.dalbitresb.monitoringservice.monitoring.resources.MonitoringResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/v1/monitoring")
public class MonitoringController {
    private static String OS = System.getProperty("os.name").toLowerCase();

    @GetMapping
    public MonitoringResponse getMonitoringInfo() {
        long start = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        int cpuCount = runtime.availableProcessors();
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;

        File rootDrive = new File(OS.contains("win") ? "C:" : "/");
        double totalSpace = (double) (rootDrive.getTotalSpace() / 1073741824);
        double freeSpace = (double) (rootDrive.getFreeSpace() / 1073741824);

        MonitoringResponse response = new MonitoringResponse();
        response.setCpuCount(cpuCount);
        response.setMaxMemory(maxMemory);
        response.setTotalMemory(totalMemory);
        response.setFreeMemory(freeMemory);

        response.setTotalSpace(totalSpace);
        response.setFreeSpace(freeSpace);

        // 1 s = 1,000 ms = 1,000,000 µs = 1,000,000,000 ns
        double elapsedTime = (double) (System.nanoTime() - start) / 1_000_000_000; /// *1.0e-9;
        response.setElapsedTime(elapsedTime);

        return response;
    }
}
