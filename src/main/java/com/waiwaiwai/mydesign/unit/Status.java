package com.waiwaiwai.mydesign.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Status {
    public static String EXECUTED = "";
    public static String TO_BE_EXECUTE = "";
    public static String FAILED = "";
    public static String EXPIRED = "";
    public static String TO_BE_EXECUTD = "";
}
