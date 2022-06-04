package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Моель для ответа строковых значений")
public class StringResponse {
    @Schema(description = "Строка-ответ", required = true)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringResponse() {
    }

    public StringResponse(String value) {
        this.value = value;
    }
}
