package com.softux.mitransporte;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MainControlador
{
    @GetMapping("/")
    public String getMessage()
    {
        return "Mi Transporte - SOFTUX 2024";
    }
}