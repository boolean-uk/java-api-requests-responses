package com.booleanuk.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// Cannot parse to JSON -> endpoints with request bodies fails without this @NoArgsConstructor
// Probably has to do with only having one class variable?
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    private String name;
}
