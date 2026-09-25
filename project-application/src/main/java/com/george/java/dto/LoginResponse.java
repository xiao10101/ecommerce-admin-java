package com.george.java.dto;

public record LoginResponse(String token, String type, long ttlMinute) {}
