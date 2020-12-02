package com.waiwaiwai.mydesign.prjaction1.improve;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestInfo {

    private String apiName;

    private double responseTime;

    private long timestamp;
}
