package com.example;


import com.example.facade.ConverterFacade;

public class Main {
    public static void main(String[] args) {
        ConverterFacade facade = new ConverterFacade();

        System.out.println("\n--- Example 1: Basic conversion ---");
        facade.convert("document.docx", "pdf", false, false, null);

        System.out.println("\n--- Example 2: Conversion with watermark ---");
        facade.convert("report.docx", "pdf", true, false, "© 2025 MyCompany");

        System.out.println("\n--- Example 3: Conversion with watermark + compression ---");
        facade.convert("contract.docx", "pdf", true, true, "Confidential");
    }
}
