package com.dalbitresb.monitoringservice.monitoring.resources;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonitoringResponse {
    private int cpuCount;
    private long maxMemory;
    private long totalMemory;
    private long freeMemory;
    private double elapsedTime;
    private double totalSpace;
    private double freeSpace;
}
