package com.cashbean.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEntryRequest {

    @NotNull
    private String title;

    @NotNull
    private int amount;

    private UUID groupId;

    public CreateEntryRequest(String title, int amount) {
        this.title = title;
        this.amount = amount;
        this.groupId = null;
    }

}
