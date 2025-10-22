package com.fastflyrr.kurma.enums;

public enum PadStatus {
    FREE,
    OCCUPIED,
    MAINTENANCE,
    BLOCKED,
    ASSIGNED
}

//Flow: free-> assigned-> occupied-> free
