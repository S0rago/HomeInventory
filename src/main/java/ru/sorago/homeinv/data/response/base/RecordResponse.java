package ru.sorago.homeinv.data.response.base;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordResponse<T> extends Response {
    private T data;
    public RecordResponse(T data) {
        this.data = data;
    }
}
