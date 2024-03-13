package com.google.training.appdev;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Accounts {
    private List<String> accounts;
}
