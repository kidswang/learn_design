package com.waiwaiwai.mydesign.build;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourceConfig {
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

}
